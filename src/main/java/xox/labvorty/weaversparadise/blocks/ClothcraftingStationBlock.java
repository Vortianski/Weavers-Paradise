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
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.blocks.entities.ClothcraftingStationBlockEntity;
import xox.labvorty.weaversparadise.data.recipe.ClothcraftingRecipe;
import xox.labvorty.weaversparadise.data.recipe.ClothcraftingRecipeInput;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"deprecated"})
public class ClothcraftingStationBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public ClothcraftingStationBlock() {
        super(BlockBehaviour.Properties.of()
                .pushReaction(PushReaction.NORMAL)
                .sound(SoundType.WOOD)
                .strength(1f, 10f)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @NotNull InteractionResult use(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull Player player,
            @NotNull InteractionHand interactionHand,
            @NotNull BlockHitResult blockHitResult
    ) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(
                    serverPlayer,
                    new MenuProvider() {
                        @Override
                        public @NotNull Component getDisplayName() {
                            return Component.literal("Clothcrafting Station");
                        }

                        @Override
                        public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                            return new ClothcraftingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
                        }
                    },
                    blockPos
            );
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
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
        if (blockEntity instanceof ClothcraftingStationBlockEntity clothcraftingStationBlockEntity) {
            clothcraftingStationBlockEntity.setItems(new ArrayList<>());
            clothcraftingStationBlockEntity.setGameOn(false);
        }

        level.scheduleTick(blockPos, this, 1);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ClothcraftingStationBlockEntity(pos, state);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof ClothcraftingStationBlockEntity cloth)) {
            world.scheduleTick(pos, this, 1);
            return;
        }

        if (cloth.getGameOn()) {
            if (cloth.getGameTime() > 0) {
                cloth.setGameTime(cloth.getGameTime() - 1);
            } else {
                ItemStack spoolInSlot = cloth.getClothType();
                ClothcraftingRecipeInput input = new ClothcraftingRecipeInput(spoolInSlot);

                Optional<ClothcraftingRecipe> recipeOpt = world.getRecipeManager()
                        .getAllRecipesFor(WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get())
                        .stream()
                        .filter(r -> r.matches(input, world))
                        .findFirst();

                if (recipeOpt.isPresent()) {
                    ClothcraftingRecipe recipe = recipeOpt.get();
                    int score = cloth.getGameScore();
                    List<ItemStack> outputList = cloth.getItemsList();

                    ItemStack returnSpool = recipe.getSpoolReturn().copy();
                    returnSpool.setCount(recipe.getSpoolReturnCount());
                    mergeIntoOutputList(outputList, returnSpool);

                    recipe.resolve(score).ifPresent(tier -> {
                        ItemStack result = tier.result().copy();
                        result.setCount(tier.count());
                        mergeIntoOutputList(outputList, result);
                    });

                    cloth.setItems(outputList);
                    cloth.setGameScore(0);
                    cloth.setGameOn(false);
                }
            }
        } else {
            cloth.setGameScore(0);
            cloth.setGameTime(0);
        }

        BlockEntity below = world.getBlockEntity(pos.below());
        if (below instanceof HopperBlockEntity hopper) {
            List<ItemStack> items = cloth.getItemsList();
            for (int i = 0; i < hopper.getContainerSize(); i++) {
                if (hopper.getItem(i).isEmpty()) {
                    for (ItemStack entry : items) {
                        if (!entry.isEmpty()) {
                            ItemStack extract = entry.copy();
                            extract.setCount(1);
                            entry.shrink(1);
                            hopper.setItem(i, extract);
                            break;
                        }
                    }
                }
            }
            cloth.setItems(items);
        }

        world.scheduleTick(pos, this, 1);
    }

    private void mergeIntoOutputList(List<ItemStack> list, ItemStack incoming) {
        if (incoming.isEmpty()) return;
        for (ItemStack existing : list) {
            if (ItemStack.isSameItemSameTags(existing, incoming)) {
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
    public void onRemove(
            @NotNull BlockState blockState,
            @NotNull Level level,
            @NotNull BlockPos blockPos,
            @NotNull BlockState blockStateNew,
            boolean moving
    ) {
        if (blockState.getBlock() != blockStateNew.getBlock()) {
            BlockEntity be = level.getBlockEntity(blockPos);

            if (be instanceof ClothcraftingStationBlockEntity cloth) {
                Containers.dropContents(level, blockPos, cloth);

                List<ItemStack> items = cloth.getItemsList();
                if (items != null) {
                    for (ItemStack stack : items) {
                        level.addFreshEntity(new ItemEntity(
                                level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack
                        ));
                    }
                }
            }
        }

        super.onRemove(blockState, level, blockPos, blockStateNew, moving);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}