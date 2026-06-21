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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
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
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.blocks.entities.ClothcraftingStationBlockEntity;
import xox.labvorty.weaversparadise.data.recipes.ClothcraftingRecipe;
import xox.labvorty.weaversparadise.data.recipes.ClothcraftingRecipeInput;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

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
    public int getLightBlock(
            @NotNull BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos
    ) {
        return 0;
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull BlockHitResult blockHitResult
    ) {
        super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    return Component.literal("Clothcrafting Station");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                    return new ClothcraftingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
                }
            }, blockPos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onPlace(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull BlockState blockStateOld,
            boolean moving
    ) {
        super.onPlace(blockState, level, blockPos, blockStateOld, moving);
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        if (blockEntity instanceof ClothcraftingStationBlockEntity clothEntity) {
            List<ItemStack> items = new ArrayList<>();
            clothEntity.setItems(items);
            clothEntity.setGameOn(false);
        }

        level.scheduleTick(blockPos, this, 1);
    }

    @Override
    public MenuProvider getMenuProvider(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos
    ) {
        BlockEntity tileEntity = level.getBlockEntity(blockPos);

        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    @Override
    public BlockEntity newBlockEntity(
            @NotNull BlockPos blockPos,
            @NotNull BlockState blockState
    ) {
        return new ClothcraftingStationBlockEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);

        builder.add(FACING);
    }

    @Override
    public void tick(
            @NotNull BlockState blockState,
            @NotNull ServerLevel serverLevel,
            @NotNull BlockPos blockPos,
            @NotNull RandomSource randomSource
    ) {
        super.tick(blockState, serverLevel, blockPos, randomSource);

        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof ClothcraftingStationBlockEntity clothEntity) {
            if (clothEntity.getGameOn()) {
                if (clothEntity.getGameTime() > 0) {
                    clothEntity.setGameTime(clothEntity.getGameTime() - 1);
                } else {
                    ItemStack spoolInSlot = clothEntity.getClothType();
                    ClothcraftingRecipeInput input = new ClothcraftingRecipeInput(spoolInSlot);

                    serverLevel.getRecipeManager()
                            .getRecipeFor(WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get(), input, serverLevel)
                            .ifPresent(holder -> {
                                ClothcraftingRecipe recipe = holder.value();
                                int score = clothEntity.getGameScore();
                                List<ItemStack> outputList = clothEntity.getItemsList();

                                ItemStack returnSpool = recipe.getSpoolReturn().copy();
                                returnSpool.setCount(recipe.getSpoolReturnCount());
                                mergeIntoOutputList(outputList, returnSpool);

                                recipe.resolve(score).ifPresent(tier -> {
                                    ItemStack result = tier.result().copy();
                                    result.setCount(tier.count());
                                    mergeIntoOutputList(outputList, result);
                                });

                                clothEntity.setItems(outputList);
                                clothEntity.setGameScore(0);
                                clothEntity.setGameOn(false);
                            });

                    if (clothEntity.getGameOn()) {
                        clothEntity.setGameScore(0);
                        clothEntity.setGameOn(false);
                    }
                }
            }

            if (!serverLevel.isClientSide) {
                BlockEntity below = serverLevel.getBlockEntity(blockPos.below());
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

        serverLevel.scheduleTick(blockPos, this, 1);
    }

    private void mergeIntoOutputList(List<ItemStack> list, ItemStack incoming) {
        if (incoming.isEmpty()) return;
        for (ItemStack existing : list) {
            if (ItemStack.isSameItemSameComponents(existing, incoming)) {
                int space = existing.getMaxStackSize() - existing.getCount();
                if (space > 0) {
                    int toAdd = Math.min(space, incoming.getCount());
                    existing.grow(toAdd);
                    incoming.shrink(toAdd);
                    if (incoming.isEmpty()) return;
                }
            }
        }

        list.add(incoming.copy());
    }

    @Override
    public boolean triggerEvent(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            int eventID,
            int eventParam
    ) {
        super.triggerEvent(blockState, level, blockPos, eventID, eventParam);

        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
    }

    @Override
    public void onRemove(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull BlockState blockStateNew,
            boolean isMoving
    ) {
        if (blockState.getBlock() != blockStateNew.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof ClothcraftingStationBlockEntity be) {
                Containers.dropContents(level, blockPos, be);
                List<ItemStack> itemStackList = be.getItemsList();

                for (ItemStack stack : itemStackList) {
                    ItemEntity itemEntity = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                    level.addFreshEntity(itemEntity);
                }

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
    public int getAnalogOutputSignal(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos
    ) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        if (blockEntity instanceof ClothcraftingStationBlockEntity clothcraftingStationBlockEntity)
            return AbstractContainerMenu.getRedstoneSignalFromContainer(clothcraftingStationBlockEntity);
        else
            return 0;
    }

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState blockState,
            @NotNull BlockGetter blockGetter,
            @NotNull BlockPos blockPos,
            @NotNull CollisionContext collisionContext
    ) {
        return box(1, 0.01, 1, 15, 7, 15);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockPlaceContext) {
        BlockState blockState = super.getStateForPlacement(blockPlaceContext);
        if (blockState != null) {
            return blockState.setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
        }

        return super.getStateForPlacement(blockPlaceContext);
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        Direction facing = blockState.getValue(FACING);

        return blockState.setValue(FACING, mirror.mirror(facing));
    }
}
