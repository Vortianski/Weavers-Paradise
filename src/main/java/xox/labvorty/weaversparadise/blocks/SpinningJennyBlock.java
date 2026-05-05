package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.materials.EmptySpoolItem;

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
            serverPlayer.openMenu(new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("String Block");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player menuPlayer) {
                    return new StringMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                }
            });
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

        ItemStack mainStack = getItemStack(world, pos, 0);
        ItemStack stack1 = getItemStack(world, pos, 1);
        ItemStack stack2 = getItemStack(world, pos, 2);
        ItemStack stack3 = getItemStack(world, pos, 3);
        ItemStack stack4 = getItemStack(world, pos, 4);
        ItemStack stack5 = getItemStack(world, pos, 5);
        ItemStack stack6 = getItemStack(world, pos, 6);

        BlockEntity blockEntity = world.getBlockEntity(pos);
        int workingState = 0;
        boolean canChange = false;

        if (blockEntity instanceof SpinningJennyBlockEntity spinningJennyBE) {
            workingState = spinningJennyBE.getWorkingState();

            if (hasCraftItems(stack1, mainStack)
                    || hasCraftItems(stack2, mainStack)
                    || hasCraftItems(stack3, mainStack)
                    || hasCraftItems(stack4, mainStack)
                    || hasCraftItems(stack5, mainStack)
                    || hasCraftItems(stack6, mainStack)) {
                canChange = true;
            } else {
                workingState = 0;
                spinningJennyBE.setWorkingState(0);
            }
        }

        if (workingState >= 100) {
            if (mainStack.is(ItemTags.create(new ResourceLocation("minecraft", "wool")))) {
                tryCraft(world, pos, 1, mainStack, 1, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                tryCraft(world, pos, 2, mainStack, 1, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                tryCraft(world, pos, 3, mainStack, 1, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                tryCraft(world, pos, 4, mainStack, 1, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                tryCraft(world, pos, 5, mainStack, 1, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                tryCraft(world, pos, 6, mainStack, 1, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
            }

            if (mainStack.is(Items.STRING)) {
                tryCraft(world, pos, 1, mainStack, 5, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                tryCraft(world, pos, 2, mainStack, 5, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                tryCraft(world, pos, 3, mainStack, 5, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                tryCraft(world, pos, 4, mainStack, 5, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                tryCraft(world, pos, 5, mainStack, 5, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                tryCraft(world, pos, 6, mainStack, 5, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
            }

            if (mainStack.is(WeaversParadiseItems.RAW_COTTON.get())) {
                tryCraft(world, pos, 1, mainStack, 4, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                tryCraft(world, pos, 2, mainStack, 4, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                tryCraft(world, pos, 3, mainStack, 4, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                tryCraft(world, pos, 4, mainStack, 4, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                tryCraft(world, pos, 5, mainStack, 4, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                tryCraft(world, pos, 6, mainStack, 4, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
            }

            if (mainStack.is(WeaversParadiseItems.COTTON_SPOOL.get())) {
                tryCraft(world, pos, 1, mainStack, 4, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                tryCraft(world, pos, 2, mainStack, 4, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                tryCraft(world, pos, 3, mainStack, 4, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                tryCraft(world, pos, 4, mainStack, 4, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                tryCraft(world, pos, 5, mainStack, 4, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                tryCraft(world, pos, 6, mainStack, 4, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
            }

            if (canChange && blockEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
                spinningJennyBlockEntity.setWorkingState(0);
            }
        } else if (canChange) {
            if (blockEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
                spinningJennyBlockEntity.setWorkingState(workingState + 1);
            }
        }

        world.scheduleTick(pos, this, 1);
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

    private static void tryCraft(Level world, BlockPos pos, int outputSlot, ItemStack mainStack, int cost, ItemStack result) {
        if (!(getItemStack(world, pos, outputSlot).getItem() instanceof EmptySpoolItem)) {
            return;
        }

        if (mainStack.getCount() < cost) {
            return;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (be == null) {
            return;
        }

        LazyOptional<IItemHandler> cap = be.getCapability(ForgeCapabilities.ITEM_HANDLER, null);
        cap.ifPresent(handler -> {
            if (handler instanceof IItemHandlerModifiable modifiable) {
                modifiable.setStackInSlot(outputSlot, result.copy());
                mainStack.shrink(cost);
                modifiable.setStackInSlot(0, mainStack.copy());
            }
        });
    }

    public boolean hasCraftItems(ItemStack stack, ItemStack mainStack) {
        if (mainStack.is(ItemTags.create(new ResourceLocation("minecraft", "wool"))) && mainStack.getCount() > 0 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        } else if (mainStack.is(Items.STRING) && mainStack.getCount() > 4 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        } else if (mainStack.is(WeaversParadiseItems.RAW_COTTON.get()) && mainStack.getCount() > 3 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        } else if (mainStack.is(WeaversParadiseItems.COTTON_SPOOL.get()) && mainStack.getCount() > 3 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        }

        return false;
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