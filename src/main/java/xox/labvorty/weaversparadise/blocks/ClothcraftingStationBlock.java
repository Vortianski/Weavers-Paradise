package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
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
import xox.labvorty.weaversparadise.blocks.entities.ClothcraftingStationBlockEntity;
import xox.labvorty.weaversparadise.gui.menu.ClothcraftingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.ArrayList;
import java.util.List;

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
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(
                    serverPlayer,
                    new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.literal("Clothcrafting Station");
                        }

                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
                            return new ClothcraftingMenu(id, inv, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                        }
                    },
                    pos
            );
        }

        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos,
                        BlockState oldState, boolean moving) {

        super.onPlace(state, world, pos, oldState, moving);

        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof ClothcraftingStationBlockEntity cloth) {
            cloth.setItems(new ArrayList<>());
            cloth.setGameOn(false);
            cloth.setClothType("");
        }

        world.scheduleTick(pos, this, 1);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ClothcraftingStationBlockEntity(pos, state);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BlockEntity be = world.getBlockEntity(pos);

        if (be instanceof ClothcraftingStationBlockEntity cloth) {

            if (cloth.getGameOn()) {
                if (cloth.getGameTime() > 0) {
                    cloth.setGameTime(cloth.getGameTime() - 1);
                } else {
                    List<ItemStack> items = cloth.getItemsList();
                    if (items == null) items = new ArrayList<>();

                    String clothType = cloth.getClothType();

                    ItemStack spool = new ItemStack(WeaversParadiseItems.EMPTY_SPOOL.get(), 6);
                    ItemStack result = ItemStack.EMPTY;

                    int quality = Mth.clamp(cloth.getGameScore() / 2, 0, 10);

                    if ("WOOL".equals(clothType)) {
                        result = new ItemStack(WeaversParadiseItems.WOOL_CLOTH.get());
                    } else if ("SILK".equals(clothType)) {
                        result = new ItemStack(WeaversParadiseItems.SILK_CLOTH.get());
                    } else if ("COTTON".equals(clothType)) {
                        result = new ItemStack(WeaversParadiseItems.COTTON_CLOTH.get());
                    } else if ("JEANS".equals(clothType)) {
                        result = new ItemStack(WeaversParadiseItems.JEANS_CLOTH.get());
                    }

                    if (!result.isEmpty()) {
                        result.getOrCreateTag().putInt("quality", quality);
                    }

                    boolean spoolAdded = false;
                    boolean resultAdded = false;

                    for (ItemStack stack : items) {

                        if (!spoolAdded && stack.is(spool.getItem()) && stack.getCount() <= 58) {
                            stack.grow(6);
                            spoolAdded = true;
                        }

                        if (!resultAdded && stack.is(result.getItem())) {
                            int existingQuality = stack.getOrCreateTag().getInt("quality");

                            if (existingQuality == quality) {
                                stack.grow(1);
                                resultAdded = true;
                            }
                        }

                        if (spoolAdded && resultAdded) break;
                    }

                    if (!resultAdded && !result.isEmpty()) {
                        items.add(result);
                    }

                    if (!spoolAdded) {
                        items.add(spool);
                    }

                    cloth.setItems(items);
                    cloth.setGameScore(0);
                    cloth.setGameOn(false);
                }
            } else {
                cloth.setGameScore(0);
                cloth.setGameTime(0);
            }

            // Hopper interaction
            BlockEntity below = world.getBlockEntity(pos.below());

            if (below instanceof HopperBlockEntity hopper) {
                List<ItemStack> items = cloth.getItemsList();
                if (items == null) items = new ArrayList<>();

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
        }

        world.scheduleTick(pos, this, 1);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = world.getBlockEntity(pos);

            if (be instanceof ClothcraftingStationBlockEntity cloth) {
                Containers.dropContents(world, pos, cloth);

                List<ItemStack> items = cloth.getItemsList();
                if (items != null) {
                    for (ItemStack stack : items) {
                        world.addFreshEntity(new ItemEntity(
                                world, pos.getX(), pos.getY(), pos.getZ(), stack
                        ));
                    }
                }
            }
        }

        super.onRemove(state, world, pos, newState, moving);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite());
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