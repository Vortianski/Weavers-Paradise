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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.data.recipe.SpinningJennyRecipe;
import xox.labvorty.weaversparadise.data.recipe.SpinningJennyRecipeInput;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;
import xox.labvorty.weaversparadise.items.materials.EmptySpoolItem;

import java.util.Optional;

@SuppressWarnings("deprecation")
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
    public int getLightBlock(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return 0;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(
                    serverPlayer,
                    new MenuProvider() {
                        @Override
                        public @NotNull Component getDisplayName() {
                            return Component.literal("String Block");
                        }

                        @Override
                        public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player playerInternal) {
                            return new StringMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
                        }
                    },
                    blockPos
            );
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void onPlace(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockStateOld, boolean moving) {
        super.onPlace(blockState, level, blockPos, blockStateOld, moving);
        level.scheduleTick(blockPos, this, 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SpinningJennyBlockEntity(blockPos, blockState);
    }

    @Override
    public void tick(@NotNull BlockState blockState, @NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        super.tick(blockState, serverLevel, blockPos, randomSource);

        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (!(blockEntity instanceof SpinningJennyBlockEntity spinningJennyBE)) {
            serverLevel.scheduleTick(blockPos, this, 1);
            return;
        }

        IItemHandlerModifiable itemHandler = (IItemHandlerModifiable) blockEntity
                .getCapability(ForgeCapabilities.ITEM_HANDLER, null)
                .filter(h -> h instanceof IItemHandlerModifiable).orElse(null);

        if (itemHandler == null) {
            serverLevel.scheduleTick(blockPos, this, 1);
            return;
        }

        ItemStack mainStack = itemHandler.getStackInSlot(0);

        Optional<SpinningJennyRecipe> recipeOpt = Optional.empty();
        int emptySpoolSlot = -1;
        for (int i = 1; i <= 6; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);

            Optional<SpinningJennyRecipe> recipe = getCurrentRecipe(serverLevel, mainStack, stack);
            if (recipe.isPresent()) {
                emptySpoolSlot = i;
                recipeOpt = recipe;
                break;
            }

            if (!stack.isEmpty() && stack.getItem() instanceof EmptySpoolItem) {
                emptySpoolSlot = i;
                break;
            }
        }

        if (recipeOpt.isPresent()) {
            SpinningJennyRecipe recipe = recipeOpt.get();

            if (mainStack.getCount() >= recipe.getCountRequired()) {
                int progress = spinningJennyBE.getWorkingState();

                if (progress >= recipe.getCraftTime()) {
                    mainStack.shrink(recipe.getCountRequired());
                    itemHandler.setStackInSlot(0, mainStack);

                    ItemStack result = recipe.getResultItem(serverLevel.registryAccess()).copy();
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

        serverLevel.scheduleTick(blockPos, this, 1);
    }

    public Optional<SpinningJennyRecipe> getCurrentRecipe(Level level, ItemStack input, ItemStack catalyst) {
        if (level == null) return Optional.empty();

        return level.getRecipeManager()
                .getAllRecipesFor(WeaversParadiseRecipes.SPINNING_JENNY_TYPE.get())
                .stream()
                .filter(recipe -> recipe.matches(new SpinningJennyRecipeInput(input, catalyst), level))
                .findFirst();
    }

    @Override
    public boolean triggerEvent(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, int eventID, int eventParam) {
        super.triggerEvent(blockState, level, blockPos, eventID, eventParam);
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
    }

    @Override
    public void onRemove(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockStateNew, boolean isMoving) {
        if (blockState.getBlock() != blockStateNew.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);

            if (blockEntity instanceof SpinningJennyBlockEntity be) {
                Containers.dropContents(level, blockPos, be);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(blockState, level, blockPos, blockStateNew, isMoving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState blockState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        if (blockEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
            return AbstractContainerMenu.getRedstoneSignalFromContainer(spinningJennyBlockEntity);
        }

        return 0;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return switch (blockState.getValue(FACING)) {
            case NORTH -> box(1, 0.01, 1, 15, 13, 16);
            case EAST -> box(0, 0.01, 1, 15, 13, 15);
            case WEST -> box(1, 0.01, 1, 16, 13, 15);
            default -> box(1, 0.01, 0, 15, 13, 15);
        };
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull BlockState rotate(@NotNull BlockState blockState, @NotNull Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(@NotNull BlockState blockState, @NotNull Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }
}