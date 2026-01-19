package xox.labvorty.weaversparadise.blocks;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xox.labvorty.weaversparadise.blocks.entities.DyeingBarrelBlockEntity;
import xox.labvorty.weaversparadise.gui.menu.DyeingMenu;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.List;

public class DyeingBarrelBlock extends Block implements EntityBlock {
    public DyeingBarrelBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 1);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        super.tick(blockstate, serverLevel, pos, random);

        BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
        if (blockEntity instanceof DyeingBarrelBlockEntity dyeingBarrelBlock) {
            ItemStack slot0 = dyeingBarrelBlock.getItemHandler().getStackInSlot(0);
            ItemStack slot1 = dyeingBarrelBlock.getItemHandler().getStackInSlot(1);
            ItemStack slot2 = dyeingBarrelBlock.getItemHandler().getStackInSlot(2);
            ItemStack slot3 = dyeingBarrelBlock.getItemHandler().getStackInSlot(3);
            ItemStack slot4 = dyeingBarrelBlock.getItemHandler().getStackInSlot(4);
            ItemStack slot5 = dyeingBarrelBlock.getItemHandler().getStackInSlot(5);
            ItemStack slot6 = dyeingBarrelBlock.getItemHandler().getStackInSlot(6);
            ItemStack slot7 = dyeingBarrelBlock.getItemHandler().getStackInSlot(7);

            List<String> dyeTypes = List.of(
                    "agender",
                    "aroace",
                    "aromantic",
                    "asexual",
                    "bisexual",
                    "demiboy",
                    "demigender",
                    "demigirl",
                    "gay",
                    "genderfluid",
                    "genderqueer",
                    "intersex",
                    "lesbian",
                    "nonbinary",
                    "pansexual",
                    "pride",
                    "trans"
            );

            if (slot4.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag slotdata = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (slotdata.getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(4, new ItemStack(Items.GLASS_BOTTLE));
                    slot4 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (slot5.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag slotdata = slot5.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (slotdata.getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(5, new ItemStack(Items.GLASS_BOTTLE));
                    slot5 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (slot6.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag slotdata = slot6.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (slotdata.getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(6, new ItemStack(Items.GLASS_BOTTLE));
                    slot6 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (slot7.is(WeaversParadiseItems.BOTTLED_DYE)) {
                CompoundTag slotdata = slot7.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (slotdata.getInt("amount") < 1) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(7, new ItemStack(Items.GLASS_BOTTLE));
                    slot7 = new ItemStack(Items.GLASS_BOTTLE);
                }
            }

            if (
                    slot0.is(WeaversParadiseItems.THIGH_HIGHS_COTTON)
                            || slot0.is(WeaversParadiseItems.THIGH_HIGHS_SILK)
                            || slot0.is(WeaversParadiseItems.THIGH_HIGHS_WOOL)
                            || slot0.is(WeaversParadiseItems.HAND_WARMERS_COTTON)
                            || slot0.is(WeaversParadiseItems.HAND_WARMERS_SILK)
                            || slot0.is(WeaversParadiseItems.HAND_WARMERS_WOOL)
            ) {
                ItemStack stack = slot0.copy();

                if (slot2.isEmpty()) {
                    if (!slot5.isEmpty()) {
                        Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot5);
                        dyeingBarrelBlock.getItemHandler().setStackInSlot(5, ItemStack.EMPTY);
                        slot5 = ItemStack.EMPTY;
                    }

                    if (slot4.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                        CompoundTag tag = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        if (tag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(4, new ItemStack(Items.GLASS_BOTTLE));
                            slot4 = new ItemStack(Items.GLASS_BOTTLE);
                        } else {

                            boolean isPride = false;

                            for (String entry : dyeTypes) {
                                if (tag.getString("dyeType").equals(entry)) {
                                    isPride = true;
                                    break;
                                }
                            }

                            if (isPride) {
                                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                                    tags.putInt("colorPriRedLeftOne", 255);
                                    tags.putInt("colorPriGreenLeftOne", 255);
                                    tags.putInt("colorPriBlueLeftOne", 255);
                                    tags.putInt("colorSecRedLeftOne", 255);
                                    tags.putInt("colorSecGreenLeftOne", 255);
                                    tags.putInt("colorSecBlueLeftOne", 255);
                                    tags.putInt("colorPriRedLeftTwo", 255);
                                    tags.putInt("colorPriGreenLeftTwo", 255);
                                    tags.putInt("colorPriBlueLeftTwo", 255);
                                    tags.putInt("colorSecRedLeftTwo", 255);
                                    tags.putInt("colorSecGreenLeftTwo", 255);
                                    tags.putInt("colorSecBlueLeftTwo", 255);
                                    tags.putString("stensilTypeLeft", tag.getString("dyeType"));
                                    tags.putString("dyeTypeLeftOne", tag.getString("dyeType"));
                                    tags.putString("dyeTypeLeftTwo", tag.getString("dyeType"));
                                    tags.putInt("lightValueLeftOne", 15);
                                    tags.putInt("lightValueLeftTwo", 15);
                                });
                            } else {
                                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                                    tags.putInt("colorPriRedLeftOne", tag.getInt("colorRedOne"));
                                    tags.putInt("colorPriGreenLeftOne", tag.getInt("colorGreenOne"));
                                    tags.putInt("colorPriBlueLeftOne", tag.getInt("colorBlueOne"));
                                    tags.putInt("colorSecRedLeftOne", tag.getInt("colorRedTwo"));
                                    tags.putInt("colorSecGreenLeftOne", tag.getInt("colorGreenTwo"));
                                    tags.putInt("colorSecBlueLeftOne", tag.getInt("colorBlueTwo"));
                                    tags.putInt("colorPriRedLeftTwo", tag.getInt("colorRedOne"));
                                    tags.putInt("colorPriGreenLeftTwo", tag.getInt("colorGreenOne"));
                                    tags.putInt("colorPriBlueLeftTwo", tag.getInt("colorBlueOne"));
                                    tags.putInt("colorSecRedLeftTwo", tag.getInt("colorRedTwo"));
                                    tags.putInt("colorSecGreenLeftTwo", tag.getInt("colorGreenTwo"));
                                    tags.putInt("colorSecBlueLeftTwo", tag.getInt("colorBlueTwo"));
                                    tags.putString("dyeTypeLeftOne", tag.getString("dyeType"));
                                    tags.putString("dyeTypeLeftTwo", tag.getString("dyeType"));
                                    tags.putInt("lightValueLeftOne", tag.getInt("lightValue"));
                                    tags.putInt("lightValueLeftTwo", tag.getInt("lightValue"));
                                });
                            }
                        }
                    }
                } else if (slot2.is(ItemTags.create(ResourceLocation.parse("weaversparadise:thigh_highs_stensils")))) {
                    final String stensilLeft;
                    if (slot2.is(WeaversParadiseItems.HALF_STENSIL)) {
                        stensilLeft = "half";
                    } else if (slot2.is(WeaversParadiseItems.CHECKERS_STENSIL)) {
                        stensilLeft = "checkers";
                    } else if (slot2.is(WeaversParadiseItems.LINES_VERTICAL_STENSIL)) {
                        stensilLeft = "vertical_lines";
                    } else if (slot2.is(WeaversParadiseItems.LINES_SMALL_STENSIL)) {
                        stensilLeft = "small_lines";
                    } else if (slot2.is(WeaversParadiseItems.LINES_BIG_STENSIL)) {
                        stensilLeft = "big_lines";
                    } else if (slot2.is(WeaversParadiseItems.CROSS_STENSIL)) {
                        stensilLeft = "cross";
                    } else if (slot2.is(WeaversParadiseItems.PAWS_STENSIL)) {
                        stensilLeft = "paws";
                    } else {
                        stensilLeft = "default";
                    }

                    final String dyeTypeLeftOne;

                    final int redLeftPriOne;
                    final int redLeftSecOne;
                    final int greenLeftPriOne;
                    final int greenLeftSecOne;
                    final int blueLeftPriOne;
                    final int blueLeftSecOne;

                    final String dyeTypeLeftTwo;

                    final int redLeftPriTwo;
                    final int redLeftSecTwo;
                    final int greenLeftPriTwo;
                    final int greenLeftSecTwo;
                    final int blueLeftPriTwo;
                    final int blueLeftSecTwo;

                    boolean isPride4 = false;
                    if (slot4.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        CompoundTag dyetag = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        if (dyetag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(4, new ItemStack(Items.GLASS_BOTTLE));
                            slot4 = new ItemStack(Items.GLASS_BOTTLE);

                            CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                            redLeftPriOne = datatag.getInt("colorPriRedLeftOne");
                            redLeftSecOne = datatag.getInt("colorSecRedLeftOne");
                            greenLeftPriOne = datatag.getInt("colorPriGreenLeftOne");
                            greenLeftSecOne = datatag.getInt("colorSecGreenLeftOne");
                            blueLeftPriOne = datatag.getInt("colorPriBlueLeftOne");
                            blueLeftSecOne = datatag.getInt("colorSecBlueLeftOne");
                            dyeTypeLeftOne = datatag.getString("dyeTypeLeftOne");
                        } else {

                            for (String entry : dyeTypes) {
                                if (dyetag.getString("dyeType").equals(entry)) {
                                    isPride4 = true;
                                    break;
                                }
                            }

                            if (isPride4) {
                                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot4);
                                dyeingBarrelBlock.getItemHandler().setStackInSlot(4, ItemStack.EMPTY);
                                slot4 = ItemStack.EMPTY;
                                redLeftPriOne = 255;
                                redLeftSecOne = 255;
                                greenLeftPriOne = 255;
                                greenLeftSecOne = 255;
                                blueLeftPriOne = 255;
                                blueLeftSecOne = 255;
                                dyeTypeLeftOne = "default";
                            } else {
                                redLeftPriOne = dyetag.getInt("colorRedOne");
                                redLeftSecOne = dyetag.getInt("colorRedTwo");
                                greenLeftPriOne = dyetag.getInt("colorGreenOne");
                                greenLeftSecOne = dyetag.getInt("colorGreenTwo");
                                blueLeftPriOne = dyetag.getInt("colorBlueOne");
                                blueLeftSecOne = dyetag.getInt("colorBlueTwo");
                                dyeTypeLeftOne = dyetag.getString("dyeType");
                            }
                        }
                    } else {
                        CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        redLeftPriOne = datatag.getInt("colorPriRedLeftOne");
                        redLeftSecOne = datatag.getInt("colorSecRedLeftOne");
                        greenLeftPriOne = datatag.getInt("colorPriGreenLeftOne");
                        greenLeftSecOne = datatag.getInt("colorSecGreenLeftOne");
                        blueLeftPriOne = datatag.getInt("colorPriBlueLeftOne");
                        blueLeftSecOne = datatag.getInt("colorSecBlueLeftOne");
                        dyeTypeLeftOne = datatag.getString("dyeTypeLeftOne");
                    }

                    boolean isPride5 = false;
                    if (slot5.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        CompoundTag dyetag = slot5.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        if (dyetag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(5, new ItemStack(Items.GLASS_BOTTLE));
                            slot5 = new ItemStack(Items.GLASS_BOTTLE);

                            CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                            redLeftPriTwo = datatag.getInt("colorPriRedLeftTwo");
                            redLeftSecTwo = datatag.getInt("colorSecRedLeftTwo");
                            greenLeftPriTwo = datatag.getInt("colorPriGreenLeftTwo");
                            greenLeftSecTwo = datatag.getInt("colorSecGreenLeftTwo");
                            blueLeftPriTwo = datatag.getInt("colorPriBlueLeftTwo");
                            blueLeftSecTwo = datatag.getInt("colorSecBlueLeftTwo");
                            dyeTypeLeftTwo = datatag.getString("dyeTypeLeftTwo");
                        } else {

                            for (String entry : dyeTypes) {
                                if (dyetag.getString("dyeType").equals(entry)) {
                                    isPride5 = true;
                                    break;
                                }
                            }

                            if (isPride5) {
                                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot5);
                                dyeingBarrelBlock.getItemHandler().setStackInSlot(5, ItemStack.EMPTY);
                                slot5 = ItemStack.EMPTY;
                                redLeftPriTwo = 255;
                                redLeftSecTwo = 255;
                                greenLeftPriTwo = 255;
                                greenLeftSecTwo = 255;
                                blueLeftPriTwo = 255;
                                blueLeftSecTwo = 255;
                                dyeTypeLeftTwo = "default";
                            } else {
                                redLeftPriTwo = dyetag.getInt("colorRedOne");
                                redLeftSecTwo = dyetag.getInt("colorRedTwo");
                                greenLeftPriTwo = dyetag.getInt("colorGreenOne");
                                greenLeftSecTwo = dyetag.getInt("colorGreenTwo");
                                blueLeftPriTwo = dyetag.getInt("colorBlueOne");
                                blueLeftSecTwo = dyetag.getInt("colorBlueTwo");
                                dyeTypeLeftTwo = dyetag.getString("dyeType");
                            }
                        }
                    } else {
                        CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        redLeftPriTwo = datatag.getInt("colorPriRedLeftTwo");
                        redLeftSecTwo = datatag.getInt("colorSecRedLeftTwo");
                        greenLeftPriTwo = datatag.getInt("colorPriGreenLeftTwo");
                        greenLeftSecTwo = datatag.getInt("colorSecGreenLeftTwo");
                        blueLeftPriTwo = datatag.getInt("colorPriBlueLeftTwo");
                        blueLeftSecTwo = datatag.getInt("colorSecBlueLeftTwo");
                        dyeTypeLeftTwo = datatag.getString("dyeTypeLeftTwo");
                    }

                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                        tags.putString("dyeTypeLeftOne", dyeTypeLeftOne);
                        tags.putString("dyeTypeLeftTwo", dyeTypeLeftTwo);
                        tags.putString("stensilTypeLeft", stensilLeft);
                        tags.putInt("colorPriRedLeftOne", redLeftPriOne);
                        tags.putInt("colorPriGreenLeftOne", greenLeftPriOne);
                        tags.putInt("colorPriBlueLeftOne", blueLeftPriOne);
                        tags.putInt("colorPriRedLeftTwo", redLeftPriTwo);
                        tags.putInt("colorPriGreenLeftTwo", greenLeftPriTwo);
                        tags.putInt("colorPriBlueLeftTwo", blueLeftPriTwo);
                        tags.putInt("colorSecRedLeftOne", redLeftSecOne);
                        tags.putInt("colorSecGreenLeftOne", greenLeftSecOne);
                        tags.putInt("colorSecBlueLeftOne", blueLeftSecOne);
                        tags.putInt("colorSecRedLeftTwo", redLeftSecTwo);
                        tags.putInt("colorSecGreenLeftTwo", greenLeftSecTwo);
                        tags.putInt("colorSecBlueLeftTwo", blueLeftSecTwo);
                    });
                }

                if (slot3.isEmpty()) {
                    if (!slot7.isEmpty()) {
                        serverLevel.addFreshEntity(new ItemEntity(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot7));
                        dyeingBarrelBlock.getItemHandler().setStackInSlot(7, ItemStack.EMPTY);
                        slot7 = ItemStack.EMPTY;
                    }

                    if (slot6.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                        CompoundTag tag = slot6.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        if (tag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(6, new ItemStack(Items.GLASS_BOTTLE));
                            slot6 = new ItemStack(Items.GLASS_BOTTLE);
                        } else {
                            boolean isPride = false;

                            for (String entry : dyeTypes) {
                                if (tag.getString("dyeType").equals(entry)) {
                                    isPride = true;
                                    break;
                                }
                            }

                            if (isPride) {
                                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                                    tags.putInt("colorPriRedRightOne", 255);
                                    tags.putInt("colorPriGreenRightOne", 255);
                                    tags.putInt("colorPriBlueRightOne", 255);
                                    tags.putInt("colorSecRedRightOne", 255);
                                    tags.putInt("colorSecGreenRightOne", 255);
                                    tags.putInt("colorSecBlueRightOne", 255);
                                    tags.putInt("colorPriRedRightTwo", 255);
                                    tags.putInt("colorPriGreenRightTwo", 255);
                                    tags.putInt("colorPriBlueRightTwo", 255);
                                    tags.putInt("colorSecRedRightTwo", 255);
                                    tags.putInt("colorSecGreenRightTwo", 255);
                                    tags.putInt("colorSecBlueRightTwo", 255);
                                    tags.putString("stensilTypeRight", tag.getString("dyeType"));
                                    tags.putString("dyeTypeRightOne", tag.getString("dyeType"));
                                    tags.putString("dyeTypeRightTwo", tag.getString("dyeType"));
                                    tags.putInt("lightValueRightOne", 15);
                                    tags.putInt("lightValueRightTwo", 15);
                                });
                            } else {
                                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                                    tags.putInt("colorPriRedRightOne", tag.getInt("colorRedOne"));
                                    tags.putInt("colorPriGreenRightOne", tag.getInt("colorGreenOne"));
                                    tags.putInt("colorPriBlueRightOne", tag.getInt("colorBlueOne"));
                                    tags.putInt("colorSecRedRightOne", tag.getInt("colorRedTwo"));
                                    tags.putInt("colorSecGreenRightOne", tag.getInt("colorGreenTwo"));
                                    tags.putInt("colorSecBlueRightOne", tag.getInt("colorBlueTwo"));
                                    tags.putInt("colorPriRedRightTwo", tag.getInt("colorRedOne"));
                                    tags.putInt("colorPriGreenRightTwo", tag.getInt("colorGreenOne"));
                                    tags.putInt("colorPriBlueRightTwo", tag.getInt("colorBlueOne"));
                                    tags.putInt("colorSecRedRightTwo", tag.getInt("colorRedTwo"));
                                    tags.putInt("colorSecGreenRightTwo", tag.getInt("colorGreenTwo"));
                                    tags.putInt("colorSecBlueRightTwo", tag.getInt("colorBlueTwo"));
                                    tags.putString("dyeTypeRightOne", tag.getString("dyeType"));
                                    tags.putString("dyeTypeRightTwo", tag.getString("dyeType"));
                                    tags.putInt("lightValueRightOne", tag.getInt("lightValue"));
                                    tags.putInt("lightValueRightTwo", tag.getInt("lightValue"));
                                });
                            }
                        }
                    }
                } else if (slot3.is(ItemTags.create(ResourceLocation.parse("weaversparadise:thigh_highs_stensils")))) {
                    final String stensilRight;
                    if (slot3.is(WeaversParadiseItems.HALF_STENSIL)) {
                        stensilRight = "half";
                    } else if (slot3.is(WeaversParadiseItems.CHECKERS_STENSIL)) {
                        stensilRight = "checkers";
                    } else if (slot3.is(WeaversParadiseItems.LINES_VERTICAL_STENSIL)) {
                        stensilRight = "vertical_lines";
                    } else if (slot3.is(WeaversParadiseItems.LINES_SMALL_STENSIL)) {
                        stensilRight = "small_lines";
                    } else if (slot3.is(WeaversParadiseItems.LINES_BIG_STENSIL)) {
                        stensilRight = "big_lines";
                    } else if (slot3.is(WeaversParadiseItems.CROSS_STENSIL)) {
                        stensilRight = "cross";
                    } else if (slot3.is(WeaversParadiseItems.PAWS_STENSIL)) {
                        stensilRight = "paws";
                    } else {
                        stensilRight = "default";
                    }

                    final String dyeTypeRightOne;

                    final int redRightPriOne;
                    final int redRightSecOne;
                    final int greenRightPriOne;
                    final int greenRightSecOne;
                    final int blueRightPriOne;
                    final int blueRightSecOne;

                    final String dyeTypeRightTwo;

                    final int redRightPriTwo;
                    final int redRightSecTwo;
                    final int greenRightPriTwo;
                    final int greenRightSecTwo;
                    final int blueRightPriTwo;
                    final int blueRightSecTwo;

                    boolean isPride6 = false;
                    if (slot6.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        CompoundTag dyetag = slot6.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                        if (dyetag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(6, new ItemStack(Items.GLASS_BOTTLE));
                            slot6 = new ItemStack(Items.GLASS_BOTTLE);

                            CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                            redRightPriOne = datatag.getInt("colorPriRedRightOne");
                            redRightSecOne = datatag.getInt("colorSecRedRightOne");
                            greenRightPriOne = datatag.getInt("colorPriGreenRightOne");
                            greenRightSecOne = datatag.getInt("colorSecGreenRightOne");
                            blueRightPriOne = datatag.getInt("colorPriBlueRightOne");
                            blueRightSecOne = datatag.getInt("colorSecBlueRightOne");
                            dyeTypeRightOne = datatag.getString("dyeTypeRightOne");
                        } else {

                            for (String entry : dyeTypes) {
                                if (dyetag.getString("dyeType").equals(entry)) {
                                    isPride6 = true;
                                    break;
                                }
                            }

                            if (isPride6) {
                                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot6);
                                dyeingBarrelBlock.getItemHandler().setStackInSlot(6, ItemStack.EMPTY);
                                slot6 = ItemStack.EMPTY;
                                redRightPriOne = 255;
                                redRightSecOne = 255;
                                greenRightPriOne = 255;
                                greenRightSecOne = 255;
                                blueRightPriOne = 255;
                                blueRightSecOne = 255;
                                dyeTypeRightOne = "default";
                            } else {
                                redRightPriOne = dyetag.getInt("colorRedOne");
                                redRightSecOne = dyetag.getInt("colorRedTwo");
                                greenRightPriOne = dyetag.getInt("colorGreenOne");
                                greenRightSecOne = dyetag.getInt("colorGreenTwo");
                                blueRightPriOne = dyetag.getInt("colorBlueOne");
                                blueRightSecOne = dyetag.getInt("colorBlueTwo");
                                dyeTypeRightOne = dyetag.getString("dyeType");
                            }
                        }
                    } else {
                        CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        redRightPriOne = datatag.getInt("colorPriRedRightOne");
                        redRightSecOne = datatag.getInt("colorSecRedRightOne");
                        greenRightPriOne = datatag.getInt("colorPriGreenRightOne");
                        greenRightSecOne = datatag.getInt("colorSecGreenRightOne");
                        blueRightPriOne = datatag.getInt("colorPriBlueRightOne");
                        blueRightSecOne = datatag.getInt("colorSecBlueRightOne");
                        dyeTypeRightOne = datatag.getString("dyeTypeRightOne");
                    }

                    boolean isPride7 = false;
                    if (slot7.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        CompoundTag dyetag = slot7.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        if (dyetag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(7, new ItemStack(Items.GLASS_BOTTLE));
                            slot7 = new ItemStack(Items.GLASS_BOTTLE);

                            CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                            redRightPriTwo = datatag.getInt("colorPriRedRightTwo");
                            redRightSecTwo = datatag.getInt("colorSecRedRightTwo");
                            greenRightPriTwo = datatag.getInt("colorPriGreenRightTwo");
                            greenRightSecTwo = datatag.getInt("colorSecGreenRightTwo");
                            blueRightPriTwo = datatag.getInt("colorPriBlueRightTwo");
                            blueRightSecTwo = datatag.getInt("colorSecBlueRightTwo");
                            dyeTypeRightTwo = datatag.getString("dyeTypeRightTwo");
                        } else {

                            for (String entry : dyeTypes) {
                                if (dyetag.getString("dyeType").equals(entry)) {
                                    isPride7 = true;
                                    break;
                                }
                            }

                            if (isPride7) {
                                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot7);
                                dyeingBarrelBlock.getItemHandler().setStackInSlot(7, ItemStack.EMPTY);
                                slot7 = ItemStack.EMPTY;
                                redRightPriTwo = 255;
                                redRightSecTwo = 255;
                                greenRightPriTwo = 255;
                                greenRightSecTwo = 255;
                                blueRightPriTwo = 255;
                                blueRightSecTwo = 255;
                                dyeTypeRightTwo = "default";
                            } else {
                                redRightPriTwo = dyetag.getInt("colorRedOne");
                                redRightSecTwo = dyetag.getInt("colorRedTwo");
                                greenRightPriTwo = dyetag.getInt("colorGreenOne");
                                greenRightSecTwo = dyetag.getInt("colorGreenTwo");
                                blueRightPriTwo = dyetag.getInt("colorBlueOne");
                                blueRightSecTwo = dyetag.getInt("colorBlueTwo");
                                dyeTypeRightTwo = dyetag.getString("dyeType");
                            }
                        }
                    } else {
                        CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        redRightPriTwo = datatag.getInt("colorPriRedRightTwo");
                        redRightSecTwo = datatag.getInt("colorSecRedRightTwo");
                        greenRightPriTwo = datatag.getInt("colorPriGreenRightTwo");
                        greenRightSecTwo = datatag.getInt("colorSecGreenRightTwo");
                        blueRightPriTwo = datatag.getInt("colorPriBlueRightTwo");
                        blueRightSecTwo = datatag.getInt("colorSecBlueRightTwo");
                        dyeTypeRightTwo = datatag.getString("dyeTypeRightTwo");
                    }

                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                        tags.putString("dyeTypeRightOne", dyeTypeRightOne);
                        tags.putString("dyeTypeRightTwo", dyeTypeRightTwo);
                        tags.putString("stensilTypeRight", stensilRight);
                        tags.putInt("colorPriRedRightOne", redRightPriOne);
                        tags.putInt("colorPriGreenRightOne", greenRightPriOne);
                        tags.putInt("colorPriBlueRightOne", blueRightPriOne);
                        tags.putInt("colorPriRedRightTwo", redRightPriTwo);
                        tags.putInt("colorPriGreenRightTwo", greenRightPriTwo);
                        tags.putInt("colorPriBlueRightTwo", blueRightPriTwo);
                        tags.putInt("colorSecRedRightOne", redRightSecOne);
                        tags.putInt("colorSecGreenRightOne", greenRightSecOne);
                        tags.putInt("colorSecBlueRightOne", blueRightSecOne);
                        tags.putInt("colorSecRedRightTwo", redRightSecTwo);
                        tags.putInt("colorSecGreenRightTwo", greenRightSecTwo);
                        tags.putInt("colorSecBlueRightTwo", blueRightSecTwo);
                    });
                }

                dyeingBarrelBlock.getItemHandler().setStackInSlot(1, stack);
            } else if (slot0.is(WeaversParadiseItems.SHIRT_COTTON) || slot0.is(WeaversParadiseItems.SHIRT_SILK) || slot0.is(WeaversParadiseItems.SWEATER_WOOL)) {
                ItemStack stack = slot0.copy();

                if (slot2.is(WeaversParadiseItems.PAWS_STENSIL) || slot2.is(WeaversParadiseItems.CROSS_STENSIL)) {
                    Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot2);
                    slot2 = ItemStack.EMPTY;
                }
                if (!slot3.isEmpty()) {
                    Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot3);
                    slot3 = ItemStack.EMPTY;
                }

                if (!slot6.isEmpty()) {
                    Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot6);
                    slot6 = ItemStack.EMPTY;
                }

                if (!slot7.isEmpty()) {
                    Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot7);
                    slot7 = ItemStack.EMPTY;
                }

                if (slot2.isEmpty()) {
                    if (!slot5.isEmpty()) {
                        Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot5);
                        dyeingBarrelBlock.getItemHandler().setStackInSlot(5, ItemStack.EMPTY);
                        slot5 = ItemStack.EMPTY;
                    }

                    if (slot4.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                        CompoundTag tag = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        if (tag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(4, new ItemStack(Items.GLASS_BOTTLE));
                            slot4 = new ItemStack(Items.GLASS_BOTTLE);
                        } else {
                            boolean isPride = false;

                            for (String entry : dyeTypes) {
                                if (tag.getString("dyeType").equals(entry)) {
                                    isPride = true;
                                    break;
                                }
                            }

                            if (isPride) {
                                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                                    // Primary colors for both halves
                                    tags.putInt("colorPriRedOne", 255);
                                    tags.putInt("colorPriGreenOne", 255);
                                    tags.putInt("colorPriBlueOne", 255);
                                    tags.putInt("colorPriRedTwo", 255);
                                    tags.putInt("colorPriGreenTwo", 255);
                                    tags.putInt("colorPriBlueTwo", 255);

                                    // Secondary colors for both halves
                                    tags.putInt("colorSecRedOne", 255);
                                    tags.putInt("colorSecGreenOne", 255);
                                    tags.putInt("colorSecBlueOne", 255);
                                    tags.putInt("colorSecRedTwo", 255);
                                    tags.putInt("colorSecGreenTwo", 255);
                                    tags.putInt("colorSecBlueTwo", 255);

                                    tags.putString("stensilType", tag.getString("dyeType"));
                                    tags.putString("dyeTypeOne", tag.getString("dyeType"));
                                    tags.putString("dyeTypeTwo", tag.getString("dyeType"));
                                    tags.putInt("lightValueOne", 15);
                                    tags.putInt("lightValueTwo", 15);
                                });
                            } else {
                                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                                    // First half colors
                                    tags.putInt("colorPriRedOne", tag.getInt("colorRedOne"));
                                    tags.putInt("colorPriGreenOne", tag.getInt("colorGreenOne"));
                                    tags.putInt("colorPriBlueOne", tag.getInt("colorBlueOne"));
                                    tags.putInt("colorSecRedOne", tag.getInt("colorRedTwo"));
                                    tags.putInt("colorSecGreenOne", tag.getInt("colorGreenTwo"));
                                    tags.putInt("colorSecBlueOne", tag.getInt("colorBlueTwo"));

                                    // Second half colors (same as first for solid dye)
                                    tags.putInt("colorPriRedTwo", tag.getInt("colorRedOne"));
                                    tags.putInt("colorPriGreenTwo", tag.getInt("colorGreenOne"));
                                    tags.putInt("colorPriBlueTwo", tag.getInt("colorBlueOne"));
                                    tags.putInt("colorSecRedTwo", tag.getInt("colorRedTwo"));
                                    tags.putInt("colorSecGreenTwo", tag.getInt("colorGreenTwo"));
                                    tags.putInt("colorSecBlueTwo", tag.getInt("colorBlueTwo"));

                                    tags.putString("dyeTypeOne", tag.getString("dyeType"));
                                    tags.putString("dyeTypeTwo", tag.getString("dyeType"));
                                    tags.putInt("lightValueOne", tag.getInt("lightValue"));
                                    tags.putInt("lightValueTwo", tag.getInt("lightValue"));
                                });
                            }
                        }
                    }
                } else if (slot2.is(ItemTags.create(ResourceLocation.parse("weaversparadise:shirts_stensils")))) {
                    final String stensil;
                    if (slot2.is(WeaversParadiseItems.HALF_STENSIL)) {
                        stensil = "half";
                    } else if (slot2.is(WeaversParadiseItems.CHECKERS_STENSIL)) {
                        stensil = "checkers";
                    } else if (slot2.is(WeaversParadiseItems.LINES_VERTICAL_STENSIL)) {
                        stensil = "vertical_lines";
                    } else if (slot2.is(WeaversParadiseItems.LINES_SMALL_STENSIL)) {
                        stensil = "small_lines";
                    } else if (slot2.is(WeaversParadiseItems.LINES_BIG_STENSIL)) {
                        stensil = "big_lines";
                    } else {
                        stensil = "default";
                    }

                    final String dyeTypeOne;

                    final int redPriOne;
                    final int redSecOne;
                    final int greenPriOne;
                    final int greenSecOne;
                    final int bluePriOne;
                    final int blueSecOne;

                    final String dyeTypeTwo;

                    final int redPriTwo;
                    final int redSecTwo;
                    final int greenPriTwo;
                    final int greenSecTwo;
                    final int bluePriTwo;
                    final int blueSecTwo;

                    boolean isPride4 = false;
                    if (slot4.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        CompoundTag dyetag = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                        if (dyetag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(4, new ItemStack(Items.GLASS_BOTTLE));
                            slot4 = new ItemStack(Items.GLASS_BOTTLE);

                            CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                            redPriOne = datatag.getInt("colorPriRedOne");
                            redSecOne = datatag.getInt("colorSecRedOne");
                            greenPriOne = datatag.getInt("colorPriGreenOne");
                            greenSecOne = datatag.getInt("colorSecGreenOne");
                            bluePriOne = datatag.getInt("colorPriBlueOne");
                            blueSecOne = datatag.getInt("colorSecBlueOne");
                            dyeTypeOne = datatag.getString("dyeTypeOne");
                        } else {

                            for (String entry : dyeTypes) {
                                if (dyetag.getString("dyeType").equals(entry)) {
                                    isPride4 = true;
                                    break;
                                }
                            }

                            if (isPride4) {
                                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot4);
                                dyeingBarrelBlock.getItemHandler().setStackInSlot(4, ItemStack.EMPTY);
                                slot4 = ItemStack.EMPTY;
                                redPriOne = 255;
                                redSecOne = 255;
                                greenPriOne = 255;
                                greenSecOne = 255;
                                bluePriOne = 255;
                                blueSecOne = 255;
                                dyeTypeOne = "default";
                            } else {
                                redPriOne = dyetag.getInt("colorRedOne");
                                redSecOne = dyetag.getInt("colorRedTwo");
                                greenPriOne = dyetag.getInt("colorGreenOne");
                                greenSecOne = dyetag.getInt("colorGreenTwo");
                                bluePriOne = dyetag.getInt("colorBlueOne");
                                blueSecOne = dyetag.getInt("colorBlueTwo");
                                dyeTypeOne = dyetag.getString("dyeType");
                            }
                        }
                    } else {
                        CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        redPriOne = datatag.getInt("colorPriRedOne");
                        redSecOne = datatag.getInt("colorSecRedOne");
                        greenPriOne = datatag.getInt("colorPriGreenOne");
                        greenSecOne = datatag.getInt("colorSecGreenOne");
                        bluePriOne = datatag.getInt("colorPriBlueOne");
                        blueSecOne = datatag.getInt("colorSecBlueOne");
                        dyeTypeOne = datatag.getString("dyeTypeOne");
                    }

                    boolean isPride5 = false;
                    if (slot5.is(WeaversParadiseItems.BOTTLED_DYE)) {
                        CompoundTag dyetag = slot5.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        if (dyetag.getInt("amount") < 1) {
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(5, new ItemStack(Items.GLASS_BOTTLE));
                            slot5 = new ItemStack(Items.GLASS_BOTTLE);

                            CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                            redPriTwo = datatag.getInt("colorPriRedTwo");
                            redSecTwo = datatag.getInt("colorSecRedTwo");
                            greenPriTwo = datatag.getInt("colorPriGreenTwo");
                            greenSecTwo = datatag.getInt("colorSecGreenTwo");
                            bluePriTwo = datatag.getInt("colorPriBlueTwo");
                            blueSecTwo = datatag.getInt("colorSecBlueTwo");
                            dyeTypeTwo = datatag.getString("dyeTypeTwo");
                        } else {

                            for (String entry : dyeTypes) {
                                if (dyetag.getString("dyeType").equals(entry)) {
                                    isPride5 = true;
                                    break;
                                }
                            }

                            if (isPride5) {
                                Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), slot7);
                                dyeingBarrelBlock.getItemHandler().setStackInSlot(5, ItemStack.EMPTY);
                                slot5 = ItemStack.EMPTY;
                                redPriTwo = 255;
                                redSecTwo = 255;
                                greenPriTwo = 255;
                                greenSecTwo = 255;
                                bluePriTwo = 255;
                                blueSecTwo = 255;
                                dyeTypeTwo = "default";
                            } else {
                                redPriTwo = dyetag.getInt("colorRedOne");
                                redSecTwo = dyetag.getInt("colorRedTwo");
                                greenPriTwo = dyetag.getInt("colorGreenOne");
                                greenSecTwo = dyetag.getInt("colorGreenTwo");
                                bluePriTwo = dyetag.getInt("colorBlueOne");
                                blueSecTwo = dyetag.getInt("colorBlueTwo");
                                dyeTypeTwo = dyetag.getString("dyeType");
                            }
                        }
                    } else {
                        CompoundTag datatag = slot0.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                        redPriTwo = datatag.getInt("colorPriRedTwo");
                        redSecTwo = datatag.getInt("colorSecRedTwo");
                        greenPriTwo = datatag.getInt("colorPriGreenTwo");
                        greenSecTwo = datatag.getInt("colorSecGreenTwo");
                        bluePriTwo = datatag.getInt("colorPriBlueTwo");
                        blueSecTwo = datatag.getInt("colorSecBlueTwo");
                        dyeTypeTwo = datatag.getString("dyeTypeTwo");
                    }

                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tags) -> {
                        tags.putString("dyeTypeOne", dyeTypeOne);
                        tags.putString("dyeTypeTwo", dyeTypeTwo);
                        tags.putString("stensilType", stensil);
                        tags.putInt("colorPriRedOne", redPriOne);
                        tags.putInt("colorPriGreenOne", greenPriOne);
                        tags.putInt("colorPriBlueOne", bluePriOne);
                        tags.putInt("colorPriRedTwo", redPriTwo);
                        tags.putInt("colorPriGreenTwo", greenPriTwo);
                        tags.putInt("colorPriBlueTwo", bluePriTwo);
                        tags.putInt("colorSecRedOne", redSecOne);
                        tags.putInt("colorSecGreenOne", greenSecOne);
                        tags.putInt("colorSecBlueOne", blueSecOne);
                        tags.putInt("colorSecRedTwo", redSecTwo);
                        tags.putInt("colorSecGreenTwo", greenSecTwo);
                        tags.putInt("colorSecBlueTwo", blueSecTwo);
                    });
                }

                dyeingBarrelBlock.getItemHandler().setStackInSlot(1, stack);
            } else {
                dyeingBarrelBlock.getItemHandler().setStackInSlot(1, ItemStack.EMPTY);
            }
        }

        serverLevel.scheduleTick(pos, this, 1);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
        super.useWithoutItem(blockstate, world, pos, entity, hit);
        if (entity instanceof ServerPlayer player) {
            player.openMenu(new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("Dyeing Barrel");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new DyeingMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                }
            }, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DyeingBarrelBlockEntity(pos, state);
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
            if (blockEntity instanceof DyeingBarrelBlockEntity be) {
                for (int i = 0; i <= 7; i++) {
                    if (!(i == 1)) {
                        ItemStack stack = be.getItemHandler().getStackInSlot(i);

                        if (!stack.isEmpty()) {
                            Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                        }
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
        if (tileentity instanceof DyeingBarrelBlockEntity be)
            return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
        else
            return 0;
    }
}
