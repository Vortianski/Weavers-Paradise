package xox.labvorty.weaversparadise.blocks;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;
import io.netty.buffer.Unpooled;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.EmptyCottonSpool;
import xox.labvorty.weaversparadise.items.EmptySilkSpool;
import xox.labvorty.weaversparadise.items.EmptySpool;
import xox.labvorty.weaversparadise.items.EmptyWoolSpool;

public class SpinningJennyBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public SpinningJennyBlock() {
        super(BlockBehaviour.Properties.of()
                .pushReaction(PushReaction.NORMAL)
                .sound(SoundType.WOOD)
                .strength(1f, 10f)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
        super.useWithoutItem(blockstate, world, pos, entity, hit);
        if (entity instanceof ServerPlayer player) {
            player.openMenu(new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("String Block");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new StringMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                }
            }, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 1);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
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
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);

        ItemStack mainStack = (new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                if (world instanceof ILevelExtension _ext) {
                    IItemHandler itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                    if (itemHandler != null)
                        return itemHandler.getStackInSlot(slotid).copy();
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack(world, pos, 0));

        ItemStack stack1 = (new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                if (world instanceof ILevelExtension _ext) {
                    IItemHandler itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                    if (itemHandler != null)
                        return itemHandler.getStackInSlot(slotid).copy();
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack(world, pos, 1));

        ItemStack stack2 = (new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                if (world instanceof ILevelExtension _ext) {
                    IItemHandler itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                    if (itemHandler != null)
                        return itemHandler.getStackInSlot(slotid).copy();
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack(world, pos, 2));

        ItemStack stack3 = (new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                if (world instanceof ILevelExtension _ext) {
                    IItemHandler itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                    if (itemHandler != null)
                        return itemHandler.getStackInSlot(slotid).copy();
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack(world, pos, 3));

        ItemStack stack4 = (new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                if (world instanceof ILevelExtension _ext) {
                    IItemHandler itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                    if (itemHandler != null)
                        return itemHandler.getStackInSlot(slotid).copy();
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack(world, pos, 4));

        ItemStack stack5 = (new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                if (world instanceof ILevelExtension _ext) {
                    IItemHandler itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                    if (itemHandler != null)
                        return itemHandler.getStackInSlot(slotid).copy();
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack(world, pos, 5));

        ItemStack stack6 = (new Object() {
            public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
                if (world instanceof ILevelExtension _ext) {
                    IItemHandler itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
                    if (itemHandler != null)
                        return itemHandler.getStackInSlot(slotid).copy();
                }
                return ItemStack.EMPTY;
            }
        }.getItemStack(world, pos, 6));

        BlockEntity bEntity = world.getBlockEntity(pos);
        int state = 0;
        boolean canChange = false;
        if (bEntity instanceof SpinningJennyBlockEntity spinningJennyBE) {
            state = spinningJennyBE.getWorkingState();

            if (hasCraftItems(stack1, mainStack)
                    || hasCraftItems(stack2, mainStack)
                    || hasCraftItems(stack3, mainStack)
                    || hasCraftItems(stack4, mainStack)
                    || hasCraftItems(stack5, mainStack)
                    || hasCraftItems(stack6, mainStack)
            ) {
                canChange = true;
            } else {
                state = 0;
                spinningJennyBE.setWorkingState(0);
            }
        }

        if (state >= 100) {
            if (mainStack.is(ItemTags.create(ResourceLocation.parse("minecraft:wool")))) {
                if (stack1.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 0) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(1, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                        mainStack.shrink(1);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack2.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 0) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(2, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                        mainStack.shrink(1);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack3.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 0) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(3, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                        mainStack.shrink(1);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack4.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 0) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(4, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                        mainStack.shrink(1);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack5.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 0) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(5, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                        mainStack.shrink(1);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack6.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 0) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(6, new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get()));
                        mainStack.shrink(1);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }
            }

            if (mainStack.is(Items.STRING)) {
                if (stack1.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 4) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(1, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                        mainStack.shrink(5);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack2.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 4) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(2, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                        mainStack.shrink(5);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack3.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 4) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(3, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                        mainStack.shrink(5);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack4.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 4) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(4, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                        mainStack.shrink(5);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack5.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 4) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(5, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                        mainStack.shrink(5);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack6.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 4) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(6, new ItemStack(WeaversParadiseItems.SILK_SPOOL.get()));
                        mainStack.shrink(5);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }
            }

            if (mainStack.is(WeaversParadiseItems.RAW_COTTON)) {
                if (stack1.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(1, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack2.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(2, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack3.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(3, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack4.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(4, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack5.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(5, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack6.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(6, new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }
            }

            if (mainStack.is(WeaversParadiseItems.COTTON_SPOOL)) {
                if (stack1.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(1, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack2.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(2, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack3.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(3, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack4.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(4, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack5.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(5, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }

                if (stack6.getItem() instanceof EmptySpool emptyWoolSpool && mainStack.getCount() > 3) {
                    if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null) instanceof IItemHandlerModifiable itemHandlerModifiable) {
                        itemHandlerModifiable.setStackInSlot(6, new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get()));
                        mainStack.shrink(4);
                        itemHandlerModifiable.setStackInSlot(0, mainStack);
                    }
                }
            }

            if (canChange) {
                if (bEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
                    spinningJennyBlockEntity.setWorkingState(0);
                }
            }
        } else if (canChange) {
            if (bEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
                spinningJennyBlockEntity.setWorkingState(state + 1);
            }
        }

        world.scheduleTick(pos, this, 1);
    }

    public boolean hasCraftItems(ItemStack stack, ItemStack mainStack) {
        if (mainStack.is(ItemTags.create(ResourceLocation.parse("minecraft:wool"))) && mainStack.getCount() > 0 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        } else if (mainStack.is(Items.STRING) && mainStack.getCount() > 4 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        } else if (mainStack.is(WeaversParadiseItems.RAW_COTTON.get()) && mainStack.getCount() > 3 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        } else if (mainStack.is(WeaversParadiseItems.COTTON_SPOOL) && mainStack.getCount() > 3 && stack.is(WeaversParadiseItems.EMPTY_SPOOL.get())) {
            return true;
        }

        return false;
    }


    @Override
    public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
        super.triggerEvent(state, world, pos, eventID, eventParam);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
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
    public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
        BlockEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof SpinningJennyBlockEntity be)
            return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        else
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
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }
}
