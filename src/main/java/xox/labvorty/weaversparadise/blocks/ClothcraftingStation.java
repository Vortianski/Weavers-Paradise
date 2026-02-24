package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.ClothcraftingStationBlockEntity;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.gui.menu.StringMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ClothcraftingStation extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public ClothcraftingStation() {
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
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ClothcraftingStationBlockEntity clothEntity) {
            //
        }
        if (entity instanceof ServerPlayer player) {
            player.openMenu(new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("Clothcrafting Station");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new ClothcraftingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                }
            }, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ClothcraftingStationBlockEntity clothEntity) {
            List<ItemStack> items = new ArrayList<>();
            clothEntity.setItems(items);
            clothEntity.setGameOn(false);
            clothEntity.setClothType("");
        }
        world.scheduleTick(pos, this, 1);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ClothcraftingStationBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ClothcraftingStationBlockEntity clothEntity) {
            if (clothEntity.getGameOn()) {
                if (clothEntity.getGameTime() > 0) {
                    clothEntity.setGameTime(clothEntity.getGameTime() - 1);
                } else {
                    List<ItemStack> itemStackList = clothEntity.getItemsList();
                    String clothType = clothEntity.getClothType();
                    ItemStack spoolStack = ItemStack.EMPTY;
                    ItemStack stack = ItemStack.EMPTY;
                    int quality = Mth.clamp(clothEntity.getGameScore() / 2, 0, 10);
                    if (clothType.equals("WOOL")) {
                        spoolStack = new ItemStack(WeaversParadiseItems.EMPTY_SPOOL.get());
                        spoolStack.setCount(6);
                        stack = new ItemStack(WeaversParadiseItems.WOOL_CLOTH.get());
                        CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));
                    } else if (clothType.equals("SILK")) {
                        spoolStack = new ItemStack(WeaversParadiseItems.EMPTY_SPOOL.get());
                        spoolStack.setCount(6);
                        stack = new ItemStack(WeaversParadiseItems.SILK_CLOTH.get());
                        CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));
                    } else if (clothType.equals("COTTON")) {
                        spoolStack = new ItemStack(WeaversParadiseItems.EMPTY_SPOOL.get());
                        spoolStack.setCount(6);
                        stack = new ItemStack(WeaversParadiseItems.COTTON_CLOTH.get());
                        CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));
                    } else if (clothType.equals("JEANS")) {
                        spoolStack = new ItemStack(WeaversParadiseItems.EMPTY_SPOOL.get());
                        spoolStack.setCount(6);
                        stack = new ItemStack(WeaversParadiseItems.JEANS_CLOTH.get());
                        CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));
                    }

                    boolean spoolInjected = false;
                    boolean injected = false;
                    for (ItemStack itemStack : itemStackList) {
                        if (itemStack.getItem().equals(spoolStack.getItem()) && !spoolInjected) {
                            if (itemStack.getCount() <= 58) {
                                itemStack.grow(6);
                                spoolInjected = true;
                            }
                        }

                        if (itemStack.getItem().equals(stack.getItem()) && !injected) {
                            if (quality == itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getInt("quality")) {
                                itemStack.grow(1);
                                injected = true;
                            }
                        }

                        if (spoolInjected && injected) {
                            break;
                        }
                    }
                    if (!injected) {
                        itemStackList.add(stack);
                    }

                    if (!spoolInjected) {
                        itemStackList.add(spoolStack);
                    }
                    clothEntity.setItems(itemStackList);
                    clothEntity.setGameScore(0);
                    clothEntity.setGameOn(false);
                }
            } else {
                clothEntity.setGameScore(0);
                clothEntity.setGameTime(0);
            }

            if (!world.isClientSide) {
                BlockEntity below = world.getBlockEntity(pos.below());
                if (below instanceof HopperBlockEntity hopperBlockEntity) {
                    for (int i = 0; i < hopperBlockEntity.getContainerSize(); i++) {
                        if (hopperBlockEntity.getItem(i).isEmpty() && !hopperBlockEntity.isOnCustomCooldown()) {
                            List<ItemStack> items = clothEntity.getItemsList();

                            ItemStack extractStack = ItemStack.EMPTY;
                            for (ItemStack entryStack : items) {
                                if (!entryStack.isEmpty()) {
                                    extractStack = entryStack.copy();
                                    extractStack.setCount(1);
                                    entryStack.shrink(1);
                                    break;
                                }
                            }

                            hopperBlockEntity.setItem(i, extractStack);

                            clothEntity.setItems(items);
                        }
                    }
                }
            }
        }

        world.scheduleTick(pos, this, 1);
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
            if (blockEntity instanceof ClothcraftingStationBlockEntity be) {
                Containers.dropContents(world, pos, be);
                List<ItemStack> itemStackList = be.getItemsList();
                for (ItemStack stack : itemStackList) {
                    ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    world.addFreshEntity(itemEntity);
                }
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
        if (tileentity instanceof ClothcraftingStationBlockEntity be)
            return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        else
            return 0;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> box(1, 0.01, 1, 15, 7, 15);
            case NORTH -> box(1, 0.01, 1, 15, 7, 15);
            case EAST -> box(1, 0.01, 1, 15, 7, 15);
            case WEST -> box(1, 0.01, 1, 15, 7, 15);
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
