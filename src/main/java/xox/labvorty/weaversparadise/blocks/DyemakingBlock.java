package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.minecraftforge.network.NetworkHooks;
import xox.labvorty.weaversparadise.blocks.entities.DyemakingBlockEntity;
import xox.labvorty.weaversparadise.data.tooltip_components.data.DyeHandler;
import xox.labvorty.weaversparadise.gui.menu.DyemakingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;

import java.util.ArrayList;
import java.util.List;

public class DyemakingBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public DyemakingBlock() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.WOOD)
                .strength(1f, 10f));

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return box(0, 0, 0, 16, 11, 16);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(state, level, pos, oldState, moving);
        level.scheduleTick(pos, this, 1);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(
                    serverPlayer,
                    new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.literal("Dyemaking");
                        }

                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
                            return new DyemakingMenu(id, inv,
                                    new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                        }
                    },
                    pos
            );
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof DyemakingBlockEntity dyemakingBlock) {

            List<ItemStack> inputs = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                inputs.add(dyemakingBlock.getItemHandler().getStackInSlot(i));
            }

            ItemStack slot10 = dyemakingBlock.getItemHandler().getStackInSlot(10);
            ItemStack slot11 = dyemakingBlock.getItemHandler().getStackInSlot(11);
            ItemStack slot12 = dyemakingBlock.getItemHandler().getStackInSlot(12);

            List<Integer> reds = new ArrayList<>();
            List<Integer> greens = new ArrayList<>();
            List<Integer> blues = new ArrayList<>();

            int red, green, blue;

            String dyeType;
            boolean overrideDyeType;

            CompoundTag coreTag = slot12.getTag();
            if (slot12.is(WeaversParadiseItems.DYE_CORE.get()) && coreTag != null) {
                dyeType = coreTag.getString("dyeType");
                overrideDyeType = true;
            } else {
                dyeType = "default";
                overrideDyeType = false;
            }

            ResourceLocation vanillaTag = new ResourceLocation("weaversparadise", "vanilla_dyes");
            ResourceLocation customTag = new ResourceLocation("weaversparadise", "custom_dyes");

            for (ItemStack stack : inputs) {
                if (stack.is(ItemTags.create(vanillaTag))) {
                    DyeHandler handler = DyeHandler.getOrDefault(stack.getItem());
                    reds.add(handler.getRed());
                    greens.add(handler.getGreen());
                    blues.add(handler.getBlue());
                } else if (stack.is(ItemTags.create(customTag))) {
                    if (stack.getItem() instanceof PigmentItem pigment) {
                        reds.add(pigment.getRed(stack));
                        greens.add(pigment.getGreen(stack));
                        blues.add(pigment.getBlue(stack));
                    }
                }
            }

            if (!reds.isEmpty()) {
                red = reds.stream().mapToInt(Integer::intValue).sum() / reds.size();
                green = greens.stream().mapToInt(Integer::intValue).sum() / greens.size();
                blue = blues.stream().mapToInt(Integer::intValue).sum() / blues.size();
            } else {
                red = green = blue = 0;
            }

            if (slot10.is(Items.GLASS_BOTTLE) && slot11.is(Items.POTION)) {
                ItemStack result = WeaversParadiseItems.BOTTLED_DYE.get().getDefaultInstance();
                CompoundTag tag = result.getOrCreateTag();

                tag.putInt("colorRedOne", red);
                tag.putInt("colorGreenOne", green);
                tag.putInt("colorBlueOne", blue);

                if (overrideDyeType) {
                    tag.putString("dyeType", dyeType);
                }

                dyemakingBlock.getItemHandler().setStackInSlot(0, result);

            } else if (slot10.is(Items.GLASS_BOTTLE) && slot11.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                ItemStack result = slot11.copy();
                CompoundTag tag = result.getOrCreateTag();

                tag.putInt("colorRedTwo", red);
                tag.putInt("colorGreenTwo", green);
                tag.putInt("colorBlueTwo", blue);

                if (overrideDyeType) {
                    tag.putString("dyeType", dyeType);
                }

                dyemakingBlock.getItemHandler().setStackInSlot(0, result);

            } else {
                dyemakingBlock.getItemHandler().setStackInSlot(0, ItemStack.EMPTY);
            }
        }

        level.scheduleTick(pos, this, 1);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        return be instanceof MenuProvider provider ? provider : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DyemakingBlockEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof DyemakingBlockEntity dye) {
                for (int i = 1; i <= 12; i++) {
                    ItemStack stack = dye.getItemHandler().getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        Containers.dropItemStack(level,
                                pos.getX(), pos.getY(), pos.getZ(),
                                stack);
                    }
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, moving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        return be instanceof DyemakingBlockEntity dye
                ? AbstractContainerMenu.getRedstoneSignalFromContainer(dye)
                : 0;
    }
}