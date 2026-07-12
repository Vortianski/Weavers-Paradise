package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.blocks.entities.DyeingBarrelBlockEntity;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.stencil.Stencil;

@SuppressWarnings("deprecated")
public class DyeingBarrelBlock extends Block implements EntityBlock {
    public DyeingBarrelBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f));
    }

    @Override
    public int getLightBlock(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return 0;
    }

    @Override
    public void onPlace(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockStateOld, boolean moving) {
        super.onPlace(blockState, level, blockPos, blockStateOld, moving);
        level.scheduleTick(blockPos, this, 1);
    }

    @Override
    public void tick(@NotNull BlockState blockState, @NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        super.tick(blockState, serverLevel, blockPos, randomSource);

        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof DyeingBarrelBlockEntity dyeingBarrelBlock) {
            ItemStack slot0 = dyeingBarrelBlock.getItemHandler().getStackInSlot(0);
            ItemStack slot2 = dyeingBarrelBlock.getItemHandler().getStackInSlot(2);
            ItemStack slot3 = dyeingBarrelBlock.getItemHandler().getStackInSlot(3);
            ItemStack slot4 = dyeingBarrelBlock.getItemHandler().getStackInSlot(4);
            ItemStack slot5 = dyeingBarrelBlock.getItemHandler().getStackInSlot(5);
            ItemStack slot6 = dyeingBarrelBlock.getItemHandler().getStackInSlot(6);
            ItemStack slot7 = dyeingBarrelBlock.getItemHandler().getStackInSlot(7);

            if (slot4.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                if (slot4.getOrCreateTag().getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(4, new ItemStack(Items.GLASS_BOTTLE));
                    slot4 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (slot5.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                if (slot5.getOrCreateTag().getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(5, new ItemStack(Items.GLASS_BOTTLE));
                    slot5 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (slot6.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                if (slot6.getOrCreateTag().getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(6, new ItemStack(Items.GLASS_BOTTLE));
                    slot6 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (slot7.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                if (slot7.getOrCreateTag().getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(7, new ItemStack(Items.GLASS_BOTTLE));
                    slot7 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (slot0.getItem() instanceof SingleSidedClothingItem singleSidedClothingItem) {
                ItemStack itemStack = slot0.copy();

                final String stencil;
                boolean hasStencil = false;
                if (slot2.getItem() instanceof Stencil stencilItem) {
                    stencil = stencilItem.getType();
                    hasStencil = true;
                } else {
                    stencil = singleSidedClothingItem.getStensilType(slot0);
                }

                final String dyeTypeOne;
                final int redPriOne;
                final int redSecOne;
                final int greenPriOne;
                final int greenSecOne;
                final int bluePriOne;
                final int blueSecOne;
                final int lightOne;
                final String dyeTypeTwo;
                final int redPriTwo;
                final int redSecTwo;
                final int greenPriTwo;
                final int greenSecTwo;
                final int bluePriTwo;
                final int blueSecTwo;
                final int lightTwo;

                if (slot4.getItem() instanceof BottledDyeItem bottledDyeItem) {
                    dyeTypeOne = bottledDyeItem.getItemDyeType(slot4);
                    RGB primaryColor = splitColor(bottledDyeItem.getItemMainColor(slot4));
                    RGB secondaryColor = splitColor(bottledDyeItem.getItemSecondaryColor(slot4));
                    redPriOne = primaryColor.r;
                    greenPriOne = primaryColor.g;
                    bluePriOne = primaryColor.b;
                    redSecOne = secondaryColor.r;
                    greenSecOne = secondaryColor.g;
                    blueSecOne = secondaryColor.b;
                    lightOne = bottledDyeItem.getItemLightValue(slot4);
                } else {
                    dyeTypeOne = singleSidedClothingItem.getItemDyeType(itemStack, 1);
                    RGB primaryColor = splitColor(singleSidedClothingItem.getItemMainColor(itemStack, 1));
                    RGB secondaryColor = splitColor(singleSidedClothingItem.getItemSecondaryColor(itemStack, 1));
                    redPriOne = primaryColor.r;
                    greenPriOne = primaryColor.g;
                    bluePriOne = primaryColor.b;
                    redSecOne = secondaryColor.r;
                    greenSecOne = secondaryColor.g;
                    blueSecOne = secondaryColor.b;
                    lightOne = singleSidedClothingItem.getItemLightValue(slot0, 1);
                }

                if (hasStencil) {
                    if (slot5.getItem() instanceof BottledDyeItem bottledDyeItem) {
                        dyeTypeTwo = bottledDyeItem.getItemDyeType(slot5);
                        RGB primaryColor = splitColor(bottledDyeItem.getItemMainColor(slot5));
                        RGB secondaryColor = splitColor(bottledDyeItem.getItemSecondaryColor(slot5));
                        redPriTwo = primaryColor.r;
                        greenPriTwo = primaryColor.g;
                        bluePriTwo = primaryColor.b;
                        redSecTwo = secondaryColor.r;
                        greenSecTwo = secondaryColor.g;
                        blueSecTwo = secondaryColor.b;
                        lightTwo = bottledDyeItem.getItemLightValue(slot5);
                    } else {
                        dyeTypeTwo = singleSidedClothingItem.getItemDyeType(itemStack, 2);
                        RGB primaryColor = splitColor(singleSidedClothingItem.getItemMainColor(itemStack, 2));
                        RGB secondaryColor = splitColor(singleSidedClothingItem.getItemSecondaryColor(itemStack, 2));
                        redPriTwo = primaryColor.r;
                        greenPriTwo = primaryColor.g;
                        bluePriTwo = primaryColor.b;
                        redSecTwo = secondaryColor.r;
                        greenSecTwo = secondaryColor.g;
                        blueSecTwo = secondaryColor.b;
                        lightTwo = singleSidedClothingItem.getItemLightValue(slot0, 2);
                    }
                } else {
                    if (!slot5.isEmpty()) {
                        dropItem(slot5.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                        dyeingBarrelBlock.getItemHandler().setStackInSlot(5, ItemStack.EMPTY);
                    }

                    dyeTypeTwo = singleSidedClothingItem.getItemDyeType(itemStack, 2);
                    RGB primaryColor = splitColor(singleSidedClothingItem.getItemMainColor(itemStack, 2));
                    RGB secondaryColor = splitColor(singleSidedClothingItem.getItemSecondaryColor(itemStack, 2));
                    redPriTwo = primaryColor.r;
                    greenPriTwo = primaryColor.g;
                    bluePriTwo = primaryColor.b;
                    redSecTwo = secondaryColor.r;
                    greenSecTwo = secondaryColor.g;
                    blueSecTwo = secondaryColor.b;
                    lightTwo = singleSidedClothingItem.getItemLightValue(slot0, 2);
                }

                CompoundTag compoundTag = itemStack.getOrCreateTag();
                compoundTag.putString("dyeTypeOne", dyeTypeOne);
                compoundTag.putString("dyeTypeTwo", dyeTypeTwo);
                compoundTag.putString("stensilType", stencil);
                compoundTag.putInt("colorPriRedOne", redPriOne);
                compoundTag.putInt("colorPriGreenOne", greenPriOne);
                compoundTag.putInt("colorPriBlueOne", bluePriOne);
                compoundTag.putInt("colorPriRedTwo", redPriTwo);
                compoundTag.putInt("colorPriGreenTwo", greenPriTwo);
                compoundTag.putInt("colorPriBlueTwo", bluePriTwo);
                compoundTag.putInt("colorSecRedOne", redSecOne);
                compoundTag.putInt("colorSecGreenOne", greenSecOne);
                compoundTag.putInt("colorSecBlueOne", blueSecOne);
                compoundTag.putInt("colorSecRedTwo", redSecTwo);
                compoundTag.putInt("colorSecGreenTwo", greenSecTwo);
                compoundTag.putInt("colorSecBlueTwo", blueSecTwo);
                compoundTag.putInt("lightValueOne", lightOne);
                compoundTag.putInt("lightValueTwo", lightTwo);

                itemStack.setTag(compoundTag);

                dyeingBarrelBlock.getItemHandler().setStackInSlot(1, itemStack);
            } else if (slot0.getItem() instanceof DoubleSidedClothingItem doubleSidedClothingItem) {
                ItemStack itemStack = slot0.copy();

                final String stencilLeft;
                boolean hasLeftStencil = false;
                if (slot2.getItem() instanceof Stencil stencilItem) {
                    stencilLeft = stencilItem.getType();
                    hasLeftStencil = true;
                } else {
                    stencilLeft = doubleSidedClothingItem.getStensilType(itemStack, "left");
                }

                final String stencilRight;
                boolean hasRightStencil = false;
                if (slot3.getItem() instanceof Stencil stencilItem) {
                    stencilRight = stencilItem.getType();
                    hasRightStencil = true;
                } else {
                    stencilRight = doubleSidedClothingItem.getStensilType(itemStack, "right");
                }

                final String dyeTypeLeftOne;
                final int redLeftPriOne;
                final int redLeftSecOne;
                final int greenLeftPriOne;
                final int greenLeftSecOne;
                final int blueLeftPriOne;
                final int blueLeftSecOne;
                final int lightValueLeftOne;
                final String dyeTypeLeftTwo;
                final int redLeftPriTwo;
                final int redLeftSecTwo;
                final int greenLeftPriTwo;
                final int greenLeftSecTwo;
                final int blueLeftPriTwo;
                final int blueLeftSecTwo;
                final int lightValueLeftTwo;
                final String dyeTypeRightOne;
                final int redRightPriOne;
                final int redRightSecOne;
                final int greenRightPriOne;
                final int greenRightSecOne;
                final int blueRightPriOne;
                final int blueRightSecOne;
                final int lightValueRightOne;
                final String dyeTypeRightTwo;
                final int redRightPriTwo;
                final int redRightSecTwo;
                final int greenRightPriTwo;
                final int greenRightSecTwo;
                final int blueRightPriTwo;
                final int blueRightSecTwo;
                final int lightValueRightTwo;

                if (slot4.getItem() instanceof BottledDyeItem bottledDyeItem) {
                    dyeTypeLeftOne = bottledDyeItem.getItemDyeType(slot4);
                    RGB primaryColor = splitColor(bottledDyeItem.getItemMainColor(slot4));
                    RGB secondaryColor = splitColor(bottledDyeItem.getItemSecondaryColor(slot4));
                    redLeftPriOne = primaryColor.r;
                    greenLeftPriOne = primaryColor.g;
                    blueLeftPriOne = primaryColor.b;
                    redLeftSecOne = secondaryColor.r;
                    greenLeftSecOne = secondaryColor.g;
                    blueLeftSecOne = secondaryColor.b;
                    lightValueLeftOne = bottledDyeItem.getItemLightValue(slot4);
                } else {
                    dyeTypeLeftOne = doubleSidedClothingItem.getItemDyeType(itemStack, "left", 1);
                    RGB primaryColor = splitColor(doubleSidedClothingItem.getItemMainColor(itemStack, "left", 1));
                    RGB secondaryColor = splitColor(doubleSidedClothingItem.getItemSecondaryColor(itemStack, "left", 1));
                    redLeftPriOne = primaryColor.r;
                    greenLeftPriOne = primaryColor.g;
                    blueLeftPriOne = primaryColor.b;
                    redLeftSecOne = secondaryColor.r;
                    greenLeftSecOne = secondaryColor.g;
                    blueLeftSecOne = secondaryColor.b;
                    lightValueLeftOne = doubleSidedClothingItem.getItemLightValue(itemStack, "left", 1);
                }

                if (hasLeftStencil) {
                    if (slot5.getItem() instanceof BottledDyeItem bottledDyeItem) {
                        dyeTypeLeftTwo = bottledDyeItem.getItemDyeType(slot5);
                        RGB primaryColor = splitColor(bottledDyeItem.getItemMainColor(slot5));
                        RGB secondaryColor = splitColor(bottledDyeItem.getItemSecondaryColor(slot5));
                        redLeftPriTwo = primaryColor.r;
                        greenLeftPriTwo = primaryColor.g;
                        blueLeftPriTwo = primaryColor.b;
                        redLeftSecTwo = secondaryColor.r;
                        greenLeftSecTwo = secondaryColor.g;
                        blueLeftSecTwo = secondaryColor.b;
                        lightValueLeftTwo = bottledDyeItem.getItemLightValue(slot5);
                    } else {
                        dyeTypeLeftTwo = doubleSidedClothingItem.getItemDyeType(itemStack, "left", 2);
                        RGB primaryColor = splitColor(doubleSidedClothingItem.getItemMainColor(itemStack, "left", 2));
                        RGB secondaryColor = splitColor(doubleSidedClothingItem.getItemSecondaryColor(itemStack, "left", 2));
                        redLeftPriTwo = primaryColor.r;
                        greenLeftPriTwo = primaryColor.g;
                        blueLeftPriTwo = primaryColor.b;
                        redLeftSecTwo = secondaryColor.r;
                        greenLeftSecTwo = secondaryColor.g;
                        blueLeftSecTwo = secondaryColor.b;
                        lightValueLeftTwo = doubleSidedClothingItem.getItemLightValue(itemStack, "left", 2);
                    }
                } else {
                    if (!slot5.isEmpty()) {
                        dropItem(slot5.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                        dyeingBarrelBlock.getItemHandler().setStackInSlot(5, ItemStack.EMPTY);
                    }

                    dyeTypeLeftTwo = doubleSidedClothingItem.getItemDyeType(itemStack, "left", 2);
                    RGB primaryColor = splitColor(doubleSidedClothingItem.getItemMainColor(itemStack, "left", 2));
                    RGB secondaryColor = splitColor(doubleSidedClothingItem.getItemSecondaryColor(itemStack, "left", 2));
                    redLeftPriTwo = primaryColor.r;
                    greenLeftPriTwo = primaryColor.g;
                    blueLeftPriTwo = primaryColor.b;
                    redLeftSecTwo = secondaryColor.r;
                    greenLeftSecTwo = secondaryColor.g;
                    blueLeftSecTwo = secondaryColor.b;
                    lightValueLeftTwo = doubleSidedClothingItem.getItemLightValue(itemStack, "left", 2);
                }

                if (slot6.getItem() instanceof BottledDyeItem bottledDyeItem) {
                    dyeTypeRightOne = bottledDyeItem.getItemDyeType(slot6);
                    RGB primaryColor = splitColor(bottledDyeItem.getItemMainColor(slot6));
                    RGB secondaryColor = splitColor(bottledDyeItem.getItemSecondaryColor(slot6));
                    redRightPriOne = primaryColor.r;
                    greenRightPriOne = primaryColor.g;
                    blueRightPriOne = primaryColor.b;
                    redRightSecOne = secondaryColor.r;
                    greenRightSecOne = secondaryColor.g;
                    blueRightSecOne = secondaryColor.b;
                    lightValueRightOne = bottledDyeItem.getItemLightValue(slot6);
                } else {
                    dyeTypeRightOne = doubleSidedClothingItem.getItemDyeType(itemStack, "right", 1);
                    RGB primaryColor = splitColor(doubleSidedClothingItem.getItemMainColor(itemStack, "right", 1));
                    RGB secondaryColor = splitColor(doubleSidedClothingItem.getItemSecondaryColor(itemStack, "right", 1));
                    redRightPriOne = primaryColor.r;
                    greenRightPriOne = primaryColor.g;
                    blueRightPriOne = primaryColor.b;
                    redRightSecOne = secondaryColor.r;
                    greenRightSecOne = secondaryColor.g;
                    blueRightSecOne = secondaryColor.b;
                    lightValueRightOne = doubleSidedClothingItem.getItemLightValue(itemStack, "right", 1);
                }

                if (hasRightStencil) {
                    if (slot7.getItem() instanceof BottledDyeItem bottledDyeItem) {
                        dyeTypeRightTwo = bottledDyeItem.getItemDyeType(slot7);
                        RGB primaryColor = splitColor(bottledDyeItem.getItemMainColor(slot7));
                        RGB secondaryColor = splitColor(bottledDyeItem.getItemSecondaryColor(slot7));
                        redRightPriTwo = primaryColor.r;
                        greenRightPriTwo = primaryColor.g;
                        blueRightPriTwo = primaryColor.b;
                        redRightSecTwo = secondaryColor.r;
                        greenRightSecTwo = secondaryColor.g;
                        blueRightSecTwo = secondaryColor.b;
                        lightValueRightTwo = bottledDyeItem.getItemLightValue(slot7);
                    } else {
                        dyeTypeRightTwo = doubleSidedClothingItem.getItemDyeType(itemStack, "right", 2);
                        RGB primaryColor = splitColor(doubleSidedClothingItem.getItemMainColor(itemStack, "right", 2));
                        RGB secondaryColor = splitColor(doubleSidedClothingItem.getItemSecondaryColor(itemStack, "right", 2));
                        redRightPriTwo = primaryColor.r;
                        greenRightPriTwo = primaryColor.g;
                        blueRightPriTwo = primaryColor.b;
                        redRightSecTwo = secondaryColor.r;
                        greenRightSecTwo = secondaryColor.g;
                        blueRightSecTwo = secondaryColor.b;
                        lightValueRightTwo = doubleSidedClothingItem.getItemLightValue(itemStack, "right", 2);
                    }
                } else {
                    if (!slot7.isEmpty()) {
                        dropItem(slot7.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                        dyeingBarrelBlock.getItemHandler().setStackInSlot(7, ItemStack.EMPTY);
                    }

                    dyeTypeRightTwo = doubleSidedClothingItem.getItemDyeType(itemStack, "right", 2);
                    RGB primaryColor = splitColor(doubleSidedClothingItem.getItemMainColor(itemStack, "right", 2));
                    RGB secondaryColor = splitColor(doubleSidedClothingItem.getItemSecondaryColor(itemStack, "right", 2));
                    redRightPriTwo = primaryColor.r;
                    greenRightPriTwo = primaryColor.g;
                    blueRightPriTwo = primaryColor.b;
                    redRightSecTwo = secondaryColor.r;
                    greenRightSecTwo = secondaryColor.g;
                    blueRightSecTwo = secondaryColor.b;
                    lightValueRightTwo = doubleSidedClothingItem.getItemLightValue(itemStack, "right", 2);
                }

                CompoundTag compoundTag = itemStack.getOrCreateTag();

                compoundTag.putString("dyeTypeLeftOne", dyeTypeLeftOne);
                compoundTag.putString("dyeTypeRightOne", dyeTypeRightOne);
                compoundTag.putString("dyeTypeLeftTwo", dyeTypeLeftTwo);
                compoundTag.putString("dyeTypeRightTwo", dyeTypeRightTwo);
                compoundTag.putString("stensilTypeLeft", stencilLeft);
                compoundTag.putString("stensilTypeRight", stencilRight);
                compoundTag.putInt("colorPriRedLeftOne", redLeftPriOne);
                compoundTag.putInt("colorPriGreenLeftOne", greenLeftPriOne);
                compoundTag.putInt("colorPriBlueLeftOne", blueLeftPriOne);
                compoundTag.putInt("colorPriRedLeftTwo", redLeftPriTwo);
                compoundTag.putInt("colorPriGreenLeftTwo", greenLeftPriTwo);
                compoundTag.putInt("colorPriBlueLeftTwo", blueLeftPriTwo);
                compoundTag.putInt("colorSecRedLeftOne", redLeftSecOne);
                compoundTag.putInt("colorSecGreenLeftOne", greenLeftSecOne);
                compoundTag.putInt("colorSecBlueLeftOne", blueLeftSecOne);
                compoundTag.putInt("colorSecRedLeftTwo", redLeftSecTwo);
                compoundTag.putInt("colorSecGreenLeftTwo", greenLeftSecTwo);
                compoundTag.putInt("colorSecBlueLeftTwo", blueLeftSecTwo);
                compoundTag.putInt("colorPriRedRightOne", redRightPriOne);
                compoundTag.putInt("colorPriGreenRightOne", greenRightPriOne);
                compoundTag.putInt("colorPriBlueRightOne", blueRightPriOne);
                compoundTag.putInt("colorPriRedRightTwo", redRightPriTwo);
                compoundTag.putInt("colorPriGreenRightTwo", greenRightPriTwo);
                compoundTag.putInt("colorPriBlueRightTwo", blueRightPriTwo);
                compoundTag.putInt("colorSecRedRightOne", redRightSecOne);
                compoundTag.putInt("colorSecGreenRightOne", greenRightSecOne);
                compoundTag.putInt("colorSecBlueRightOne", blueRightSecOne);
                compoundTag.putInt("colorSecRedRightTwo", redRightSecTwo);
                compoundTag.putInt("colorSecGreenRightTwo", greenRightSecTwo);
                compoundTag.putInt("colorSecBlueRightTwo", blueRightSecTwo);
                compoundTag.putInt("lightValueLeftOne", lightValueLeftOne);
                compoundTag.putInt("lightValueLeftTwo", lightValueLeftTwo);
                compoundTag.putInt("lightValueRightOne", lightValueRightOne);
                compoundTag.putInt("lightValueRightTwo", lightValueRightTwo);

                itemStack.setTag(compoundTag);

                dyeingBarrelBlock.getItemHandler().setStackInSlot(1, itemStack);
            } else {
                if (!slot2.isEmpty()) {
                    dropItem(slot2.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(2, ItemStack.EMPTY);
                }

                if (!slot3.isEmpty()) {
                    dropItem(slot3.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(3, ItemStack.EMPTY);
                }

                if (!slot4.isEmpty()) {
                    dropItem(slot4.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(4, ItemStack.EMPTY);
                }

                if (!slot5.isEmpty()) {
                    dropItem(slot5.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(5, ItemStack.EMPTY);
                }

                if (!slot6.isEmpty()) {
                    dropItem(slot6.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(6, ItemStack.EMPTY);
                }

                if (!slot7.isEmpty()) {
                    dropItem(slot7.copy(), serverLevel, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(7, ItemStack.EMPTY);
                }

                dyeingBarrelBlock.getItemHandler().setStackInSlot(1, ItemStack.EMPTY);
            }
        }

        serverLevel.scheduleTick(blockPos, this, 1);
    }

    private static void dropItem(ItemStack itemStack, ServerLevel serverLevel, int x, int y, int z) {
        serverLevel.addFreshEntity(new ItemEntity(serverLevel, x, y, z, itemStack));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    return Component.literal("Dyeing Barrel");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                    return new DyeingMenu(id, inventory,
                            new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
                }
            }, blockPos);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        return blockEntity instanceof MenuProvider provider ? provider : null;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new DyeingBarrelBlockEntity(blockPos, blockState);
    }

    @Override
    public boolean triggerEvent(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, int id, int param) {
        super.triggerEvent(blockState, level, blockPos, id, param);

        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        return blockEntity != null && blockEntity.triggerEvent(id, param);
    }

    @Override
    public void onRemove(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockStateNew, boolean moving) {
        if (blockState.getBlock() != blockStateNew.getBlock()) {
            BlockEntity be = level.getBlockEntity(blockPos);

            if (be instanceof DyeingBarrelBlockEntity barrel) {
                for (int i = 0; i <= 7; i++) {
                    if (i == 1) continue;
                    ItemStack stack = barrel.getItemHandler().getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                    }
                }
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(blockState, level, blockPos, blockStateNew, moving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState blockState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos) {
        BlockEntity be = level.getBlockEntity(blockPos);

        return be instanceof DyeingBarrelBlockEntity barrel ? AbstractContainerMenu.getRedstoneSignalFromContainer(barrel) : 0;
    }

    record RGB(int r, int g, int b) {}

    private static RGB splitColor(int color) {
        return new RGB(
                (color >> 16) & 255,
                (color >> 8) & 255,
                color & 255
        );
    }
}