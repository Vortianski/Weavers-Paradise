package xox.labvorty.weaversparadise.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import oshi.util.tuples.Pair;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

import java.util.List;
import java.util.UUID;

public class WeaversParadiseCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "weaversparadise");
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> QUALITY_MATERIALS_TAB = CREATIVE_MODE_TABS.register("quality_materials_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.weaversparadise.quality_materials"))
            .icon(() -> WeaversParadiseItems.COTTON_CLOTH.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                List<ItemStack> qualityItems = List.of(
                        WeaversParadiseItems.COTTON_CLOTH.toStack(),
                        WeaversParadiseItems.SILK_CLOTH.toStack(),
                        WeaversParadiseItems.WOOL_CLOTH.toStack(),
                        WeaversParadiseItems.JEANS_CLOTH.toStack(),
                        WeaversParadiseItems.COTTON_UPPERWEAR_BASE.toStack(),
                        WeaversParadiseItems.COTTON_UPPERWEAR_BASE_BUTTONS.toStack(),
                        WeaversParadiseItems.COTTON_UPPERWEAR_BASE_ZIPPER.toStack(),
                        WeaversParadiseItems.COTTON_SLEEVE_SHORT.toStack(),
                        WeaversParadiseItems.COTTON_SLEEVE_LONG.toStack(),
                        WeaversParadiseItems.COTTON_PANT_LEG.toStack(),
                        WeaversParadiseItems.SILK_UPPERWEAR_BASE.toStack(),
                        WeaversParadiseItems.SILK_UPPERWEAR_BASE_BUTTONS.toStack(),
                        WeaversParadiseItems.SILK_UPPERWEAR_BASE_ZIPPER.toStack(),
                        WeaversParadiseItems.SILK_SLEEVE_SHORT.toStack(),
                        WeaversParadiseItems.SILK_SLEEVE_LONG.toStack(),
                        WeaversParadiseItems.SILK_PANT_LEG.toStack(),
                        WeaversParadiseItems.WOOL_UPPERWEAR_BASE.toStack(),
                        WeaversParadiseItems.WOOL_UPPERWEAR_BASE_BUTTONS.toStack(),
                        WeaversParadiseItems.WOOL_SLEEVE_SHORT.toStack(),
                        WeaversParadiseItems.WOOL_SLEEVE_LONG.toStack(),
                        WeaversParadiseItems.JEANS_PANT_LEG.toStack()
                );

                for (ItemStack stack : qualityItems) {
                    for (int i = 0; i <= 10; i++) {
                        final int quality = i;
                        ItemStack itemStack = stack.copy();
                        CustomData.update(DataComponents.CUSTOM_DATA, itemStack, (tag) -> tag.putInt("quality", quality));
                        output.accept(itemStack);
                    }
                }
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DYES_TAB = CREATIVE_MODE_TABS.register("dyes_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.weaversparadise.dye"))
            .icon(() -> WeaversParadiseItems.BOTTLED_DYE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                List<String> dyeTypes = List.of(
                        "default",
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
                        "trans",
                        "redstone",
                        "lamp",
                        "sculk",
                        "colored_sculk",
                        "hunger",
                        "health",
                        "day_time",
                        "colored_day_time",
                        "glowstone",
                        "rainbow",
                        "biome",
                        "ender",
                        "speed",
                        "height_bedrock",
                        "height_sea",
                        "invisible",
                        "static",
                        "crystal",
                        "negative",
                        "true_negative",
                        "nebula",
                        "polychromatic"
                );
                List<ItemStack> dyedItems = List.of(
                        WeaversParadiseItems.BOTTLED_DYE.toStack(),
                        WeaversParadiseItems.DYE_CORE.toStack()
                );

                for (ItemStack stack : dyedItems) {
                    for (String type : dyeTypes) {
                        if (type.equals("redstone")) {
                            for (int i = 0; i <= 15; i++) {
                                ItemStack item = stack.copy();
                                final int color = Mth.lerpInt(i / 15.0f, 100, 255);
                                final int index = i;

                                CustomData.update(DataComponents.CUSTOM_DATA, item, (tag) -> {
                                    tag.putString("dyeType", type);
                                    tag.putInt("lightValue", index);
                                    tag.putInt("colorRedOne", color);
                                    tag.putInt("colorGreenOne", 0);
                                    tag.putInt("colorBlueOne", 0);
                                });

                                output.accept(item);
                            }
                        } else if (type.equals("lamp")) {
                            for (int i = 0; i <= 15; i++) {
                                ItemStack item = stack.copy();
                                final int index = i;

                                CustomData.update(DataComponents.CUSTOM_DATA, item, (tag) -> {
                                    tag.putString("dyeType", type);
                                    tag.putInt("lightValue", index);
                                    tag.putInt("colorRedOne", 255);
                                    tag.putInt("colorGreenOne", 255);
                                    tag.putInt("colorBlueOne", 255);
                                });

                                output.accept(item);
                            }
                        } else if (type.equals("polychromatic")) {
                            ItemStack item = stack.copy();

                            CustomData.update(DataComponents.CUSTOM_DATA, item, (tag) -> {
                                tag.putString("dyeType", type);
                                tag.putInt("colorRedOne", 255);
                                tag.putInt("colorGreenOne", 0);
                                tag.putInt("colorBlueOne", 0);
                            });

                            output.accept(item);
                        } else {
                            ItemStack item = stack.copy();

                            CustomData.update(DataComponents.CUSTOM_DATA, item, (tag) -> {
                                tag.putString("dyeType", type);
                                tag.putInt("colorRedOne", 255);
                                tag.putInt("colorGreenOne", 255);
                                tag.putInt("colorBlueOne", 255);
                            });

                            output.accept(item);
                        }
                    }
                }
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CLOTHING_TAB = CREATIVE_MODE_TABS.register("clothing_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.weaversparadise.clothing"))
            .icon(() -> WeaversParadiseItems.THIGH_HIGHS_COTTON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                List<String> types = List.of(
                        "default",
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
                List<Pair<String, Pair<Integer, Integer>>> metalTypes = List.of(
                        new Pair<>("minecraft:iron_ingot", new Pair<>(-1, 100)),
                        new Pair<>("minecraft:gold_ingot", new Pair<>(-1, 25)),
                        new Pair<>("minecraft:copper_ingot", new Pair<>(-4035764, 50))
                );
                List<Pair<String, List<ItemStack>>> dyedItems = List.of(
                        new Pair<>(
                                "double",
                                List.of(
                                        WeaversParadiseItems.THIGH_HIGHS_COTTON.toStack(),
                                        WeaversParadiseItems.THIGH_HIGHS_SILK.toStack(),
                                        WeaversParadiseItems.THIGH_HIGHS_WOOL.toStack()
                                )
                        ),
                        new Pair<>(
                                "double",
                                List.of(
                                        WeaversParadiseItems.HAND_WARMERS_COTTON.toStack(),
                                        WeaversParadiseItems.HAND_WARMERS_SILK.toStack(),
                                        WeaversParadiseItems.HAND_WARMERS_WOOL.toStack()
                                )
                        ),
                        new Pair<>(
                                "single",
                                List.of(
                                        WeaversParadiseItems.SHIRT_COTTON.toStack(),
                                        WeaversParadiseItems.SHIRT_SILK.toStack(),
                                        WeaversParadiseItems.SWEATER_WOOL.toStack()
                                )
                        ),
                        new Pair<>(
                                "single",
                                List.of(
                                        WeaversParadiseItems.PANTS_JEANS.toStack(),
                                        WeaversParadiseItems.PANTS_COTTON.toStack(),
                                        WeaversParadiseItems.PANTS_SILK.toStack()
                                )
                        ),
                        new Pair<>(
                                "single",
                                List.of(
                                        WeaversParadiseItems.COTTON_CAPE.toStack(),
                                        WeaversParadiseItems.SILK_CAPE.toStack(),
                                        WeaversParadiseItems.WOOL_CAPE.toStack()
                                )
                        ),
                        new Pair<>(
                                "double",
                                List.of(
                                        WeaversParadiseItems.CHOKER.toStack()
                                )
                        )
                );
                List<ItemStack> chokerTrinkets = List.of(
                        WeaversParadiseItems.BELL.toStack(),
                        WeaversParadiseItems.PLATE.toStack(),
                        WeaversParadiseItems.RING.toStack(),
                        WeaversParadiseItems.CAT_RING.toStack(),
                        WeaversParadiseItems.HEART.toStack()
                );
                for (Pair<String, List<ItemStack>> pair : dyedItems) {
                    String dyeingType = pair.getA();
                    List<ItemStack> stacks = pair.getB();

                    for (String type : types) {
                        for (ItemStack stack : stacks) {
                            ItemStack instance = stack.copy();

                            if (dyeingType.equals("double")) {
                                CustomData.update(DataComponents.CUSTOM_DATA, instance, (tag) -> {
                                    tag.putString("dyeTypeLeftOne", type);
                                    tag.putString("dyeTypeLeftTwo", type);
                                    tag.putString("dyeTypeRightOne", type);
                                    tag.putString("dyeTypeRightTwo", type);
                                    tag.putInt("colorPriRedLeftOne", 255);
                                    tag.putInt("colorPriGreenLeftOne", 255);
                                    tag.putInt("colorPriBlueLeftOne", 255);
                                    tag.putInt("colorPriRedLeftTwo", 255);
                                    tag.putInt("colorPriGreenLeftTwo", 255);
                                    tag.putInt("colorPriBlueLeftTwo", 255);
                                    tag.putInt("colorSecRedLeftOne", 255);
                                    tag.putInt("colorSecGreenLeftOne", 255);
                                    tag.putInt("colorSecBlueLeftOne", 255);
                                    tag.putInt("colorSecRedLeftTwo", 255);
                                    tag.putInt("colorSecGreenLeftTwo", 255);
                                    tag.putInt("colorSecBlueLeftTwo", 255);
                                    tag.putInt("colorPriRedRightOne", 255);
                                    tag.putInt("colorPriGreenRightOne", 255);
                                    tag.putInt("colorPriBlueRightOne", 255);
                                    tag.putInt("colorPriRedRightTwo", 255);
                                    tag.putInt("colorPriGreenRightTwo", 255);
                                    tag.putInt("colorPriBlueRightTwo", 255);
                                    tag.putInt("colorSecRedRightOne", 255);
                                    tag.putInt("colorSecGreenRightOne", 255);
                                    tag.putInt("colorSecBlueRightOne", 255);
                                    tag.putInt("colorSecRedRightTwo", 255);
                                    tag.putInt("colorSecGreenRightTwo", 255);
                                    tag.putInt("colorSecBlueRightTwo", 255);
                                });
                            } else if (dyeingType.equals("single")) {
                                CustomData.update(DataComponents.CUSTOM_DATA, instance, (tag) -> {
                                    tag.putString("dyeTypeOne", type);
                                    tag.putString("dyeTypeTwo", type);
                                    tag.putInt("colorPriRedOne", 255);
                                    tag.putInt("colorPriGreenOne", 255);
                                    tag.putInt("colorPriBlueOne", 255);
                                    tag.putInt("colorPriRedTwo", 255);
                                    tag.putInt("colorPriGreenTwo", 255);
                                    tag.putInt("colorPriBlueTwo", 255);
                                    tag.putInt("colorSecRedOne", 255);
                                    tag.putInt("colorSecGreenOne", 255);
                                    tag.putInt("colorSecBlueOne", 255);
                                    tag.putInt("colorSecRedTwo", 255);
                                    tag.putInt("colorSecGreenTwo", 255);
                                    tag.putInt("colorSecBlueTwo", 255);
                                });
                            }

                            output.accept(instance);
                        }
                    }
                }
                for (Pair<String, Pair<Integer, Integer>> type : metalTypes) {
                    for (ItemStack stack : chokerTrinkets) {
                        ItemStack instance = stack.copy();

                        CustomData.update(DataComponents.CUSTOM_DATA, instance, (tag) -> {
                            tag.putString("metalType", type.getA());
                            tag.putInt("color", type.getB().getA());
                            tag.putInt("damage", type.getB().getB());
                        });
                        output.accept(instance);
                    }
                }

                output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_WIG);
                output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE);
                output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_LEGGINGS);
                output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_BOOTS);

                output.accept(WeaversParadiseItems.BRIDGET_ARMOR_HAT);
                output.accept(WeaversParadiseItems.BRIDGET_ARMOR_JACKET);
                output.accept(WeaversParadiseItems.BRIDGET_ARMOR_SKIRT);
                output.accept(WeaversParadiseItems.BRIDGET_ARMOR_BOOTS);

                output.accept(WeaversParadiseItems.FELIX_ARMOR_HAT);
                output.accept(WeaversParadiseItems.FELIX_ARMOR_JACKET);
                output.accept(WeaversParadiseItems.FELIX_ARMOR_SKIRT);
                output.accept(WeaversParadiseItems.FELIX_ARMOR_BOOTS);

                output.accept(WeaversParadiseItems.LEATHER_GLOVES);
            })
    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("items_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.weaversparadise.items"))
            .icon(() -> WeaversParadiseItems.MORTAR_AND_PESTLE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                List<Item> items = List.of(
                        WeaversParadiseItems.PURE_DYE.get(),
                        WeaversParadiseItems.MORTAR_AND_PESTLE.get(),
                        WeaversParadiseItems.PESTLE.get(),
                        WeaversParadiseItems.NEEDLE.get(),
                        WeaversParadiseItems.NEEDLE_WITH_THREAD.get(),
                        WeaversParadiseItems.LEATHER_STRIPS.get(),
                        WeaversParadiseItems.EMPTY_SPOOL.get(),
                        WeaversParadiseItems.COTTON_SPOOL.get(),
                        WeaversParadiseItems.COTTON_SPOOL_BLOCK.get(),
                        WeaversParadiseItems.COTTON_CLOTH_BLOCK.get(),
                        WeaversParadiseItems.SILK_SPOOL.get(),
                        WeaversParadiseItems.SILK_SPOOL_BLOCK.get(),
                        WeaversParadiseItems.SILK_CLOTH_BLOCK.get(),
                        WeaversParadiseItems.WOOL_SPOOL.get(),
                        WeaversParadiseItems.WOOL_SPOOL_BLOCK.get(),
                        WeaversParadiseItems.WOOL_CLOTH_BLOCK.get(),
                        WeaversParadiseItems.JEANS_SPOOL.get(),
                        WeaversParadiseItems.BUTTON.get(),
                        WeaversParadiseItems.BASIC_STENCIL.get(),
                        WeaversParadiseItems.HALF_STENCIL.get(),
                        WeaversParadiseItems.CHECKERS_STENCIL.get(),
                        WeaversParadiseItems.CHECKERS_SMALL_STENCIL.get(),
                        WeaversParadiseItems.LINES_SMALL_STENCIL.get(),
                        WeaversParadiseItems.LINES_BIG_STENCIL.get(),
                        WeaversParadiseItems.LINES_VERTICAL_STENCIL.get(),
                        WeaversParadiseItems.CROSS_STENCIL.get(),
                        WeaversParadiseItems.PAWS_STENCIL.get(),
                        WeaversParadiseItems.DIRT_STENCIL.get(),
                        WeaversParadiseItems.FLOWER_STENCIL.get(),
                        WeaversParadiseItems.STAR_STENCIL.get(),
                        WeaversParadiseItems.CHROMATIC_BLOOM.get(),
                        WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT.get(),
                        WeaversParadiseItems.CHROMATIC_DUST.get(),
                        WeaversParadiseItems.COTTON_SEEDS.get(),
                        WeaversParadiseItems.KOZINAKI.get(),
                        WeaversParadiseItems.COTTON_BOLL.get(),
                        WeaversParadiseItems.RAW_COTTON.get(),
                        WeaversParadiseItems.WILD_COTTON_PLANT.get(),
                        WeaversParadiseItems.SPINNING_JENNY.get(),
                        WeaversParadiseItems.CLOTHCRAFTING_STATION.get(),
                        WeaversParadiseItems.DYEMAKING_BLOCK.get(),
                        WeaversParadiseItems.DYEING_BARREL_BLOCK.get(),
                        WeaversParadiseItems.FLAG_BASIC.get(),
                        WeaversParadiseItems.FLAG_AGENDER.get(),
                        WeaversParadiseItems.FLAG_AROACE.get(),
                        WeaversParadiseItems.FLAG_AROMANTIC.get(),
                        WeaversParadiseItems.FLAG_ASEXUAL.get(),
                        WeaversParadiseItems.FLAG_BISEXUAL.get(),
                        WeaversParadiseItems.FLAG_DEMIBOY.get(),
                        WeaversParadiseItems.FLAG_DEMIGENDER.get(),
                        WeaversParadiseItems.FLAG_DEMIGIRL.get(),
                        WeaversParadiseItems.FLAG_GAY.get(),
                        WeaversParadiseItems.FLAG_GENDERFLUID.get(),
                        WeaversParadiseItems.FLAG_GENDERQUEER.get(),
                        WeaversParadiseItems.FLAG_INTERSEX.get(),
                        WeaversParadiseItems.FLAG_LESBIAN.get(),
                        WeaversParadiseItems.FLAG_NONBINARY.get(),
                        WeaversParadiseItems.FLAG_PANSEXUAL.get(),
                        WeaversParadiseItems.FLAG_PRIDE.get(),
                        WeaversParadiseItems.FLAG_TRANS.get()
                );

                for (Item entry : items) {
                    ItemStack stack = new ItemStack(entry);

                    if (stack.getItem() instanceof PigmentItem pureDyeItem) {
                        List<Vec3> values = List.of(
                                new Vec3(0, 0, 0),
                                new Vec3(255, 0, 0),
                                new Vec3(0, 255, 0),
                                new Vec3(0, 0, 255),
                                new Vec3(255, 0, 255),
                                new Vec3(255, 255, 0),
                                new Vec3(255, 255, 255)
                        );

                        for (Vec3 vec3 : values) {
                            ItemStack s = stack.copy();
                            CustomData.update(DataComponents.CUSTOM_DATA, s, (tag) -> {
                                tag.putInt("red", (int)vec3.x);
                                tag.putInt("green", (int)vec3.y);
                                tag.putInt("blue", (int)vec3.z);
                            });
                            output.accept(s);
                        }
                    } else {
                        output.accept(stack);
                    }
                }

                //Can use both null and preset UUID. If none are given defaults
                output.accept(PlushieItem.createPreMadePlushieAsync("Vortianski", UUID.fromString("383b1ab0-ae8f-4342-b94d-b4f3d2cfc9c0")));
                output.accept(PlushieItem.createPreMadePlushieAsync("Pelemeshek", null));
                output.accept(WeaversParadiseItems.PLAYER_PLUSHIE_RENAME_TOKEN);
                output.accept(WeaversParadiseItems.ARMOR_LOOTBOX);
            }).build()
    );
}
