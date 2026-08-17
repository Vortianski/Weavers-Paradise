package xox.labvorty.weaversparadise.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import oshi.util.tuples.Pair;
import xox.labvorty.vortylib.data.creative_tab.ExpandableCreativeTab;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;
import xox.labvorty.weaversparadise.utilities.CreativeModeTabProviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WeaversParadiseCreativeTabs {
    private static final List<String> dyeTypes = List.of(
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
            "polychromatic",
            "starfall",
            "chromatic_aberration",
            "boykisser",
            "forcefield",
            "hypnotic"
    );

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "weaversparadise");

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CLOTHING_TAB = CREATIVE_MODE_TABS.register("clothing_tab", () -> ExpandableCreativeTab.builder()
            .addGroup("thigh_highs_cotton", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_COTTON.get()), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_COTTON.get(), dyeTypes))
            .addGroup("thigh_highs_silk", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_SILK.get()), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_SILK.get(), dyeTypes))
            .addGroup("thigh_highs_wool", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_WOOL.get()), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_WOOL.get(), dyeTypes))
            .addGroup("hand_warmers_cotton", new ItemStack(WeaversParadiseItems.HAND_WARMERS_COTTON.get()), getVariantItem(WeaversParadiseItems.HAND_WARMERS_COTTON.get(), dyeTypes))
            .addGroup("hand_warmers_silk", new ItemStack(WeaversParadiseItems.HAND_WARMERS_SILK.get()), getVariantItem(WeaversParadiseItems.HAND_WARMERS_SILK.get(), dyeTypes))
            .addGroup("hand_warmers_wool", new ItemStack(WeaversParadiseItems.HAND_WARMERS_WOOL.get()), getVariantItem(WeaversParadiseItems.HAND_WARMERS_WOOL.get(), dyeTypes))
            .addGroup("t_shirt", new ItemStack(WeaversParadiseItems.T_SHIRT.get()), getVariantItem(WeaversParadiseItems.T_SHIRT.get(), dyeTypes))
            .addGroup("tank_top", new ItemStack(WeaversParadiseItems.TANK_TOP.get()), getVariantItem(WeaversParadiseItems.TANK_TOP.get(), dyeTypes))
            .addGroup("wool_vest", new ItemStack(WeaversParadiseItems.WOOL_VEST.get()), getVariantItem(WeaversParadiseItems.WOOL_VEST.get(), dyeTypes))
            .addGroup("shirt_cotton", new ItemStack(WeaversParadiseItems.SHIRT_COTTON.get()), getVariantItem(WeaversParadiseItems.SHIRT_COTTON.get(), dyeTypes))
            .addGroup("shirt_silk", new ItemStack(WeaversParadiseItems.SHIRT_SILK.get()), getVariantItem(WeaversParadiseItems.SHIRT_SILK.get(), dyeTypes))
            .addGroup("long_sleeve_cotton", new ItemStack(WeaversParadiseItems.LONG_SLEEVE_COTTON.get()), getVariantItem(WeaversParadiseItems.LONG_SLEEVE_COTTON.get(), dyeTypes))
            .addGroup("sweater_wool", new ItemStack(WeaversParadiseItems.SWEATER_WOOL.get()), getVariantItem(WeaversParadiseItems.SWEATER_WOOL.get(), dyeTypes))
            .addGroup("poncho", new ItemStack(WeaversParadiseItems.PONCHO.get()), getVariantItem(WeaversParadiseItems.PONCHO.get(), dyeTypes))
            .addGroup("cotton_crop_top_long_sleeved", new ItemStack(WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED.get()), getVariantItem(WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED.get(), dyeTypes))
            .addGroup("pants_jeans", new ItemStack(WeaversParadiseItems.PANTS_JEANS.get()), getVariantItem(WeaversParadiseItems.PANTS_JEANS.get(), dyeTypes))
            .addGroup("pants_cotton", new ItemStack(WeaversParadiseItems.PANTS_COTTON.get()), getVariantItem(WeaversParadiseItems.PANTS_COTTON.get(), dyeTypes))
            .addGroup("pants_silk", new ItemStack(WeaversParadiseItems.PANTS_SILK.get()), getVariantItem(WeaversParadiseItems.PANTS_SILK.get(), dyeTypes))
            .addGroup("pants_wool", new ItemStack(WeaversParadiseItems.PANTS_WOOL.get()), getVariantItem(WeaversParadiseItems.PANTS_WOOL.get(), dyeTypes))
            .addGroup("skirt_cotton", new ItemStack(WeaversParadiseItems.COTTON_SKIRT.get()), getVariantItem(WeaversParadiseItems.COTTON_SKIRT.get(), dyeTypes))
            .addGroup("cape_cotton", new ItemStack(WeaversParadiseItems.COTTON_CAPE.get()), getVariantItem(WeaversParadiseItems.COTTON_CAPE.get(), dyeTypes))
            .addGroup("cape_silk", new ItemStack(WeaversParadiseItems.SILK_CAPE.get()), getVariantItem(WeaversParadiseItems.SILK_CAPE.get(), dyeTypes))
            .addGroup("cape_wool", new ItemStack(WeaversParadiseItems.WOOL_CAPE.get()), getVariantItem(WeaversParadiseItems.WOOL_CAPE.get(), dyeTypes))
            .addGroup("pompon_hat", new ItemStack(WeaversParadiseItems.POMPON_HAT.get()), getVariantItem(WeaversParadiseItems.POMPON_HAT.get(), dyeTypes))
            .addGroup("cap", new ItemStack(WeaversParadiseItems.CAP.get()), getVariantItem(WeaversParadiseItems.CAP.get(), dyeTypes))
            .addGroup("ushanka", new ItemStack(WeaversParadiseItems.USHANKA.get()), getVariantItem(WeaversParadiseItems.USHANKA.get(), dyeTypes))
            .addGroup("choker", new ItemStack(WeaversParadiseItems.CHOKER.get()), getVariantItem(WeaversParadiseItems.CHOKER.get(), dyeTypes))
            .addGroup("armor_cosmetics", new ItemStack(WeaversParadiseItems.ASTOLFO_COSMETICS.get()), CreativeModeTabProviders.getArmorCosmetics())
            .title(Component.translatable("itemGroup.weaversparadise.clothing"))
            .icon(() -> WeaversParadiseItems.THIGH_HIGHS_COTTON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                List<Pair<String, Pair<Integer, Integer>>> metalTypes = List.of(
                        new Pair<>("minecraft:iron_ingot", new Pair<>(-1, 100)),
                        new Pair<>("minecraft:gold_ingot", new Pair<>(-1, 25)),
                        new Pair<>("minecraft:copper_ingot", new Pair<>(-34267, 50))
                );

                List<ItemStack> chokerTrinkets = List.of(
                        WeaversParadiseItems.BELL.toStack(),
                        WeaversParadiseItems.PLATE.toStack(),
                        WeaversParadiseItems.RING.toStack(),
                        WeaversParadiseItems.CAT_RING.toStack(),
                        WeaversParadiseItems.HEART.toStack()
                );

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

                output.accept(WeaversParadiseItems.LEATHER_GLOVES);
            })
            .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> QUALITY_MATERIALS_TAB = CREATIVE_MODE_TABS.register("quality_materials_tab", () -> ExpandableCreativeTab.builder()
            .addGroup("cotton_cloth", new ItemStack(WeaversParadiseItems.COTTON_CLOTH.get()), getQualityVariants(WeaversParadiseItems.COTTON_CLOTH.get()))
            .addGroup("silk_cloth", new ItemStack(WeaversParadiseItems.SILK_CLOTH.get()), getQualityVariants(WeaversParadiseItems.SILK_CLOTH.get()))
            .addGroup("wool_cloth", new ItemStack(WeaversParadiseItems.WOOL_CLOTH.get()), getQualityVariants(WeaversParadiseItems.WOOL_CLOTH.get()))
            .addGroup("jeans_cloth", new ItemStack(WeaversParadiseItems.JEANS_CLOTH.get()), getQualityVariants(WeaversParadiseItems.JEANS_CLOTH.get()))
            .addGroup("cotton_upperwear_base", new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE.get()), getQualityVariants(WeaversParadiseItems.COTTON_UPPERWEAR_BASE.get()))
            .addGroup("cotton_upperwear_base_buttons", new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_BUTTONS.get()), getQualityVariants(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_BUTTONS.get()))
            .addGroup("cotton_upperwear_base_zipper", new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_ZIPPER.get()), getQualityVariants(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_ZIPPER.get()))
            .addGroup("cotton_sleeve_short", new ItemStack(WeaversParadiseItems.COTTON_SLEEVE_SHORT.get()), getQualityVariants(WeaversParadiseItems.COTTON_SLEEVE_SHORT.get()))
            .addGroup("cotton_sleeve_long", new ItemStack(WeaversParadiseItems.COTTON_SLEEVE_LONG.get()), getQualityVariants(WeaversParadiseItems.COTTON_SLEEVE_LONG.get()))
            .addGroup("cotton_pant_leg", new ItemStack(WeaversParadiseItems.COTTON_PANT_LEG.get()), getQualityVariants(WeaversParadiseItems.COTTON_PANT_LEG.get()))
            .addGroup("silk_upperwear_base", new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE.get()), getQualityVariants(WeaversParadiseItems.SILK_UPPERWEAR_BASE.get()))
            .addGroup("silk_upperwear_base_buttons", new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE_BUTTONS.get()), getQualityVariants(WeaversParadiseItems.SILK_UPPERWEAR_BASE_BUTTONS.get()))
            .addGroup("silk_upperwear_base_zipper", new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE_ZIPPER.get()), getQualityVariants(WeaversParadiseItems.SILK_UPPERWEAR_BASE_ZIPPER.get()))
            .addGroup("silk_sleeve_short", new ItemStack(WeaversParadiseItems.SILK_SLEEVE_SHORT.get()), getQualityVariants(WeaversParadiseItems.SILK_SLEEVE_SHORT.get()))
            .addGroup("silk_sleeve_long", new ItemStack(WeaversParadiseItems.SILK_SLEEVE_LONG.get()), getQualityVariants(WeaversParadiseItems.SILK_SLEEVE_LONG.get()))
            .addGroup("silk_pant_leg", new ItemStack(WeaversParadiseItems.SILK_PANT_LEG.get()), getQualityVariants(WeaversParadiseItems.SILK_PANT_LEG.get()))
            .addGroup("wool_upperwear_base", new ItemStack(WeaversParadiseItems.WOOL_UPPERWEAR_BASE.get()), getQualityVariants(WeaversParadiseItems.WOOL_UPPERWEAR_BASE.get()))
            .addGroup("wool_upperwear_base_buttons", new ItemStack(WeaversParadiseItems.WOOL_UPPERWEAR_BASE_BUTTONS.get()), getQualityVariants(WeaversParadiseItems.WOOL_UPPERWEAR_BASE_BUTTONS.get()))
            .addGroup("wool_sleeve_short", new ItemStack(WeaversParadiseItems.WOOL_SLEEVE_SHORT.get()), getQualityVariants(WeaversParadiseItems.WOOL_SLEEVE_SHORT.get()))
            .addGroup("wool_sleeve_long", new ItemStack(WeaversParadiseItems.WOOL_SLEEVE_LONG.get()), getQualityVariants(WeaversParadiseItems.WOOL_SLEEVE_LONG.get()))
            .addGroup("wool_pant_leg", new ItemStack(WeaversParadiseItems.WOOL_PANT_LEG.get()), getQualityVariants(WeaversParadiseItems.WOOL_PANT_LEG.get()))
            .addGroup("jeans_pant_leg", new ItemStack(WeaversParadiseItems.JEANS_PANT_LEG.get()), getQualityVariants(WeaversParadiseItems.JEANS_PANT_LEG.get()))
            .title(Component.translatable("itemGroup.weaversparadise.quality_materials"))
            .icon(() -> WeaversParadiseItems.COTTON_CLOTH.get().getDefaultInstance())
            .displayItems((parameters, output) -> {})
            .build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DYES_TAB = CREATIVE_MODE_TABS.register("dyes_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.weaversparadise.dye"))
            .icon(() -> WeaversParadiseItems.BOTTLED_DYE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
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
            }).build()
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("items_tab", () -> ExpandableCreativeTab.builder()
            .addGroup("contributor_plushies", PlushieItem.createPlushie(Optional.of("Vortianski"), Optional.empty()), CreativeModeTabProviders.getPlayerPlushies())
            .addGroup("pigments", WeaversParadiseItems.PURE_DYE.toStack(), CreativeModeTabProviders.getPigments())
            .addGroup("flags", WeaversParadiseItems.FLAG_BASIC.get().getDefaultInstance(), CreativeModeTabProviders.getFlags())
            .addGroup("stencils", WeaversParadiseItems.BASIC_STENCIL.toStack(), CreativeModeTabProviders.getStencils())
            .title(Component.translatable("itemGroup.weaversparadise.items"))
            .icon(() -> WeaversParadiseItems.MORTAR_AND_PESTLE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                List<Item> items = List.of(
                        WeaversParadiseItems.MORTAR_AND_PESTLE.get(),
                        WeaversParadiseItems.PESTLE.get(),
                        WeaversParadiseItems.NEEDLE.get(),
                        WeaversParadiseItems.NEEDLE_WITH_THREAD.get(),
                        WeaversParadiseItems.WEAVERS_SHEARS.get(),
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
                        WeaversParadiseItems.CHROMATIC_BLOOM.get(),
                        WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT.get(),
                        WeaversParadiseItems.CHROMATIC_DUST.get(),
                        WeaversParadiseItems.WITCHROOT.get(),
                        WeaversParadiseItems.WITCHROOT_FRUIT.get(),
                        WeaversParadiseItems.WITCHROOT_FRUIT_COOKED.get(),
                        WeaversParadiseItems.STARBLOOM.get(),
                        WeaversParadiseItems.STARBLOOM_FRUIT.get(),
                        WeaversParadiseItems.COTTON_SEEDS.get(),
                        WeaversParadiseItems.KOZINAKI.get(),
                        WeaversParadiseItems.COTTON_BOLL.get(),
                        WeaversParadiseItems.RAW_COTTON.get(),
                        WeaversParadiseItems.WILD_COTTON_PLANT.get(),
                        WeaversParadiseItems.SPINNING_JENNY.get(),
                        WeaversParadiseItems.CLOTHCRAFTING_STATION.get(),
                        WeaversParadiseItems.DYEMAKING_BLOCK.get(),
                        WeaversParadiseItems.DYEING_BARREL_BLOCK.get()
                );

                for (Item entry : items) {
                    ItemStack stack = new ItemStack(entry);

                    output.accept(stack);
                }

                output.accept(WeaversParadiseItems.PLAYER_PLUSHIE_RENAME_TOKEN);
                output.accept(WeaversParadiseItems.ARMOR_LOOTBOX);
            }).build()
    );

    private static List<ItemStack> getQualityVariants(Item item) {
        List<ItemStack> items = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ItemStack itemStack = new ItemStack(item);
            final int f = i;

            CustomData.update(DataComponents.CUSTOM_DATA, itemStack, (tag) -> {
                tag.putInt("quality", f);
            });

            items.add(itemStack);
        }

        return items;
    }

    private static List<ItemStack> getVariantItem(Item item, List<String> variants) {
        List<ItemStack> items = new ArrayList<>();

        if (item instanceof SingleSidedClothingItem singleSidedClothingItem) {
            items = getSingleSidedVariants(singleSidedClothingItem, variants);
        }

        if (item instanceof DoubleSidedClothingItem doubleSidedClothingItem) {
            items = getDoubleSidedVariants(doubleSidedClothingItem, variants);
        }

        return items;
    }

    private static List<ItemStack> getSingleSidedVariants(SingleSidedClothingItem singleSidedClothingItem, List<String> variants) {
        List<ItemStack> items = new ArrayList<>();

        for (String variant : variants) {
            ItemStack itemStack = new ItemStack(singleSidedClothingItem);

            CustomData.update(DataComponents.CUSTOM_DATA, itemStack, (tag) -> {
                tag.putString("dyeTypeOne", variant);
                tag.putString("dyeTypeTwo", variant);
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

            items.add(itemStack);
        }

        return items;
    }

    private static List<ItemStack> getDoubleSidedVariants(DoubleSidedClothingItem doubleSidedClothingItem, List<String> variants) {
        List<ItemStack> items = new ArrayList<>();

        for (String variant : variants) {
            ItemStack itemStack = new ItemStack(doubleSidedClothingItem);

            CustomData.update(DataComponents.CUSTOM_DATA, itemStack, (tag) -> {
                tag.putString("dyeTypeLeftOne", variant);
                tag.putString("dyeTypeLeftTwo", variant);
                tag.putString("dyeTypeRightOne", variant);
                tag.putString("dyeTypeRightTwo", variant);
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

            items.add(itemStack);
        }

        return items;
    }
}
