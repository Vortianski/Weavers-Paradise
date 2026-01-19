package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.DyemakingBlockEntity;
import xox.labvorty.weaversparadise.data.WeaversParadiseDyeHandler;
import xox.labvorty.weaversparadise.gui.menu.DyemakingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseInterfaces;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.PureDyeItem;

import java.util.ArrayList;
import java.util.List;

public class DyemakingBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public DyemakingBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> box(0, 0, 0, 16, 11, 16);
            case NORTH -> box(0, 0, 0, 16, 11, 16);
            case EAST -> box(0, 0, 0, 16, 11, 16);
            case WEST -> box(0, 0, 0, 16, 11, 16);
        };
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
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


    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public void onPlace(BlockState blockstate, Level level, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, level, pos, oldState, moving);
        level.scheduleTick(pos, this, 1);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
        super.useWithoutItem(blockstate, world, pos, entity, hit);
        if (entity instanceof ServerPlayer player) {
            player.openMenu(new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("Dyemaking");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new DyemakingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                }
            }, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        super.tick(blockstate, serverLevel, pos, random);

        BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
        if (blockEntity instanceof DyemakingBlockEntity dyemakingBlock) {
            ItemStack slot0 = dyemakingBlock.getItemHandler().getStackInSlot(0);

            ItemStack slot1 = dyemakingBlock.getItemHandler().getStackInSlot(1);

            ItemStack slot2 = dyemakingBlock.getItemHandler().getStackInSlot(2);

            ItemStack slot3 = dyemakingBlock.getItemHandler().getStackInSlot(3);

            ItemStack slot4 = dyemakingBlock.getItemHandler().getStackInSlot(4);

            ItemStack slot5 = dyemakingBlock.getItemHandler().getStackInSlot(5);

            ItemStack slot6 = dyemakingBlock.getItemHandler().getStackInSlot(6);

            ItemStack slot7 = dyemakingBlock.getItemHandler().getStackInSlot(7);

            ItemStack slot8 = dyemakingBlock.getItemHandler().getStackInSlot(8);

            ItemStack slot9 = dyemakingBlock.getItemHandler().getStackInSlot(9);

            List<ItemStack> itemStacks = new ArrayList<>(List.of(
                    slot1,
                    slot2,
                    slot3,
                    slot4,
                    slot5,
                    slot6,
                    slot7,
                    slot8,
                    slot9
            ));

            ItemStack slot10 = dyemakingBlock.getItemHandler().getStackInSlot(10);

            ItemStack slot11 = dyemakingBlock.getItemHandler().getStackInSlot(11);

            ItemStack slot12 = dyemakingBlock.getItemHandler().getStackInSlot(12);

            List<Integer> reds = new ArrayList<>();
            List<Integer> greens = new ArrayList<>();
            List<Integer> blues = new ArrayList<>();

            final int red;
            final int green;
            final int blue;
            final String dyeType;
            final boolean shouldOverrideDyeType;

            if (slot12.is(WeaversParadiseItems.DYE_CORE.get())) {
                dyeType = slot12.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getString("dyeType");
                shouldOverrideDyeType = true;
            } else {
                dyeType = "default";
                shouldOverrideDyeType = false;
            }

            for (ItemStack itemStack : itemStacks) {
                if (itemStack.is(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))) {
                    WeaversParadiseDyeHandler dyeHandler = WeaversParadiseDyeHandler.getOrDefault(itemStack.getItem());

                    reds.add(dyeHandler.getRed());
                    greens.add(dyeHandler.getGreen());
                    blues.add(dyeHandler.getBlue());
                } else if (itemStack.is(ItemTags.create(ResourceLocation.parse("weaversparadise:custom_dyes")))) {
                    if (itemStack.getItem() instanceof PureDyeItem pureDyeItem) {
                        reds.add(pureDyeItem.getRed(itemStack));
                        greens.add(pureDyeItem.getGreen(itemStack));
                        blues.add(pureDyeItem.getBlue(itemStack));
                    }
                }
            }

            if (reds.size() > 0 && greens.size() > 0 && blues.size() > 0) {
                int redinst = 0;
                for (int redcolor : reds) {
                    redinst += redcolor;
                }
                red = redinst / reds.size();

                int greeninst = 0;
                for (int greencolor : greens) {
                    greeninst += greencolor;
                }
                green = greeninst / greens.size();

                int blueinst = 0;
                for (int bluecolor : blues) {
                    blueinst += bluecolor;
                }
                blue = blueinst / blues.size();
            } else {
                red = 0;
                green = 0;
                blue = 0;
            }

            if (slot10.is(Items.GLASS_BOTTLE) && slot11.is(Items.POTION)) {
                ItemStack stack = new ItemStack(WeaversParadiseItems.BOTTLED_DYE.get());
                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                    tag.putInt("colorRedOne", red);
                    tag.putInt("colorGreenOne", green);
                    tag.putInt("colorBlueOne", blue);
                    if (shouldOverrideDyeType) {
                        tag.putString("dyeType", dyeType);
                    }
                });
                dyemakingBlock.getItemHandler().setStackInSlot(0, stack);
            } else if (slot10.is(Items.GLASS_BOTTLE) && slot11.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                ItemStack stack = slot11.copy();
                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                    tag.putInt("colorRedTwo", red);
                    tag.putInt("colorGreenTwo", green);
                    tag.putInt("colorBlueTwo", blue);
                    if (shouldOverrideDyeType) {
                        tag.putString("dyeType", dyeType);
                    }
                });
                dyemakingBlock.getItemHandler().setStackInSlot(0, stack);
            } else {
                dyemakingBlock.getItemHandler().setStackInSlot(0, ItemStack.EMPTY);
            }
        }

        serverLevel.scheduleTick(pos, this, 1);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DyemakingBlockEntity(pos, state);
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
            if (blockEntity instanceof DyemakingBlockEntity be) {
                for (int i = 1; i <= 12; i++) {
                    ItemStack stack = be.getItemHandler().getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    }
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
        if (tileentity instanceof DyemakingBlockEntity be)
            return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        else
            return 0;
    }
}
