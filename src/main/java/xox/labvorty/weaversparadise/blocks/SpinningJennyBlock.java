package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.network.NetworkHooks;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.data.recipe.SpinningJennyRecipe;
import xox.labvorty.weaversparadise.data.recipe.SpinningJennyRecipeInput;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;
import xox.labvorty.weaversparadise.items.materials.EmptySpoolItem;

import java.util.Optional;

public class SpinningJennyBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public SpinningJennyBlock() {
        super(Properties.of()
                .pushReaction(PushReaction.NORMAL)
                .sound(SoundType.WOOD)
                .strength(1f, 10f)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 0;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, net.minecraft.world.InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(
                    serverPlayer,
                    new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.literal("String Block");
                        }

                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory inventory, Player menuPlayer) {
                            return new StringMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                        }
                    },
                    pos
            );
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(state, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SpinningJennyBlockEntity(pos, state);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof SpinningJennyBlockEntity spinningJennyBE)) {
            world.scheduleTick(pos, this, 1);
            return;
        }

        IItemHandlerModifiable itemHandler = blockEntity
                .getCapability(ForgeCapabilities.ITEM_HANDLER, null)
                .filter(h -> h instanceof IItemHandlerModifiable)
                .map(h -> (IItemHandlerModifiable) h)
                .orElse(null);

        if (itemHandler == null) {
            world.scheduleTick(pos, this, 1);
            return;
        }

        ItemStack mainStack = itemHandler.getStackInSlot(0);

        int emptySpoolSlot = -1;
        for (int i = 1; i <= 6; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() instanceof EmptySpoolItem) {
                emptySpoolSlot = i;
                break;
            }
        }

        Optional<SpinningJennyRecipe> recipeOpt = getCurrentRecipe(world, mainStack);

        if (recipeOpt.isPresent() && emptySpoolSlot != -1) {
            SpinningJennyRecipe recipe = recipeOpt.get();

            if (mainStack.getCount() >= recipe.getCountRequired()) {
                int progress = spinningJennyBE.getWorkingState();

                if (progress >= recipe.getCraftTime()) {
                    mainStack.shrink(recipe.getCountRequired());
                    itemHandler.setStackInSlot(0, mainStack);

                    ItemStack result = recipe.getResultItem(world.registryAccess()).copy();
                    result.setCount(1);
                    itemHandler.setStackInSlot(emptySpoolSlot, result);

                    spinningJennyBE.setWorkingState(0);
                    spinningJennyBE.setChanged();
                } else {
                    spinningJennyBE.setWorkingState(progress + 1);
                    spinningJennyBE.setChanged();
                }
            } else {
                spinningJennyBE.setWorkingState(0);
            }
        } else {
            spinningJennyBE.setWorkingState(0);
        }

        world.scheduleTick(pos, this, 1);
    }

    public Optional<SpinningJennyRecipe> getCurrentRecipe(Level level, ItemStack input) {
        if (level == null || input.isEmpty()) return Optional.empty();

        return level.getRecipeManager()
                .getAllRecipesFor(WeaversParadiseRecipes.SPINNING_JENNY_TYPE.get())
                .stream()
                .filter(recipe -> recipe.matches(new SpinningJennyRecipeInput(input), level))
                .findFirst();
    }

    private static ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slot) {
        if (world instanceof Level level) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be != null) {
                LazyOptional<IItemHandler> cap = be.getCapability(ForgeCapabilities.ITEM_HANDLER, null);
                if (cap.isPresent()) {
                    return cap.map(handler -> handler.getStackInSlot(slot).copy()).orElse(ItemStack.EMPTY);
                }
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
        super.triggerEvent(state, world, pos, eventID, eventParam);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SpinningJennyBlockEntity be) {
                Containers.dropContents(world, pos, be);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SpinningJennyBlockEntity be) {
            return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        }
        return 0;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> box(1, 0.01, 0, 15, 13, 15);
            case NORTH -> box(1, 0.01, 1, 15, 13, 16);
            case EAST -> box(0, 0.01, 1, 15, 13, 15);
            case WEST -> box(1, 0.01, 1, 16, 13, 15);
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}