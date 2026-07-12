package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;
import xox.labvorty.vortylib.data.creative_tab.ExpandableCreativeTab;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

import java.util.ArrayList;
import java.util.List;

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
            "starfall"
    );

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> WEAVERS_PARADISE_MATERIALS = CREATIVE_MODE_TABS.register(
            "weavers_paradise_materials",
            () -> ExpandableCreativeTab.builder()
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
                    .addGroup("jeans_pant_leg", new ItemStack(WeaversParadiseItems.JEANS_PANT_LEG.get()), getQualityVariants(WeaversParadiseItems.JEANS_PANT_LEG.get()))
                    .title(Component.translatable("itemGroup.weaversparadise.quality_materials"))
                    .icon(() -> WeaversParadiseItems.COTTON_CLOTH.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {})
                    .build()
    );

    public static final RegistryObject<CreativeModeTab> WEAVERS_PARADISE_DYES = CREATIVE_MODE_TABS.register(
            "weavers_paradise_dyes",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.weaversparadise.dye"))
                    .icon(() -> WeaversParadiseItems.BOTTLED_DYE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
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
                                "starfall"
                        );
                        List<ItemStack> items = List.of(
                                WeaversParadiseItems.BOTTLED_DYE.get().getDefaultInstance(),
                                WeaversParadiseItems.DYE_CORE.get().getDefaultInstance()
                        );

                        for (ItemStack stack : items) {
                            for (String type : dyeTypes) {
                                if (type.equals("redstone")) {
                                    for (int i = 0; i <= 15; i++) {
                                        ItemStack item = stack.copy();
                                        final int color = Mth.lerpInt(i / 15.0f, 100, 255);

                                        item.getOrCreateTag().putString("dyeType", type);
                                        item.getOrCreateTag().putInt("lightValue", i);
                                        item.getOrCreateTag().putInt("colorRedOne", color);
                                        item.getOrCreateTag().putInt("colorGreenOne", 0);
                                        item.getOrCreateTag().putInt("colorBlueOne", 0);

                                        output.accept(item);
                                    }
                                } else if (type.equals("lamp")) {
                                    for (int i = 0; i <= 15; i++) {
                                        ItemStack item = stack.copy();

                                        item.getOrCreateTag().putString("dyeType", type);
                                        item.getOrCreateTag().putInt("lightValue", i);
                                        item.getOrCreateTag().putInt("colorRedOne", 255);
                                        item.getOrCreateTag().putInt("colorGreenOne", 255);
                                        item.getOrCreateTag().putInt("colorBlueOne", 255);

                                        output.accept(item);
                                    }
                                } else if (type.equals("polychromatic")) {
                                    ItemStack item = stack.copy();

                                    item.getOrCreateTag().putString("dyeType", type);
                                    item.getOrCreateTag().putInt("colorRedOne", 255);
                                    item.getOrCreateTag().putInt("colorGreenOne", 0);
                                    item.getOrCreateTag().putInt("colorBlueOne", 0);

                                    output.accept(item);
                                } else {
                                    ItemStack item = stack.copy();

                                    item.getOrCreateTag().putString("dyeType", type);
                                    item.getOrCreateTag().putInt("colorRedOne", 255);
                                    item.getOrCreateTag().putInt("colorGreenOne", 255);
                                    item.getOrCreateTag().putInt("colorBlueOne", 255);

                                    output.accept(item);
                                }
                            }
                        }
                    })
                    .build()
    );

    public static final RegistryObject<CreativeModeTab> WEAVERS_PARADISE_CLOTHING = CREATIVE_MODE_TABS.register(
            "weavers_paradise_clothing",
            () -> ExpandableCreativeTab.builder()
                    .addGroup("thigh_highs_cotton", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_COTTON.get()), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_COTTON.get(), dyeTypes))
                    .addGroup("thigh_highs_silk", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_SILK.get()), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_SILK.get(), dyeTypes))
                    .addGroup("thigh_highs_wool", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_WOOL.get()), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_WOOL.get(), dyeTypes))
                    .addGroup("hand_warmers_cotton", new ItemStack(WeaversParadiseItems.HAND_WARMERS_COTTON.get()), getVariantItem(WeaversParadiseItems.HAND_WARMERS_COTTON.get(), dyeTypes))
                    .addGroup("hand_warmers_silk", new ItemStack(WeaversParadiseItems.HAND_WARMERS_SILK.get()), getVariantItem(WeaversParadiseItems.HAND_WARMERS_SILK.get(), dyeTypes))
                    .addGroup("hand_warmers_wool", new ItemStack(WeaversParadiseItems.HAND_WARMERS_WOOL.get()), getVariantItem(WeaversParadiseItems.HAND_WARMERS_WOOL.get(), dyeTypes))
                    .addGroup("shirt_cotton", new ItemStack(WeaversParadiseItems.SHIRT_COTTON.get()), getVariantItem(WeaversParadiseItems.SHIRT_COTTON.get(), dyeTypes))
                    .addGroup("shirt_silk", new ItemStack(WeaversParadiseItems.SHIRT_SILK.get()), getVariantItem(WeaversParadiseItems.SHIRT_SILK.get(), dyeTypes))
                    .addGroup("sweater_wool", new ItemStack(WeaversParadiseItems.SWEATER_WOOL.get()), getVariantItem(WeaversParadiseItems.SWEATER_WOOL.get(), dyeTypes))
                    .addGroup("pants_jeans", new ItemStack(WeaversParadiseItems.PANTS_JEANS.get()), getVariantItem(WeaversParadiseItems.PANTS_JEANS.get(), dyeTypes))
                    .addGroup("pants_cotton", new ItemStack(WeaversParadiseItems.PANTS_COTTON.get()), getVariantItem(WeaversParadiseItems.PANTS_COTTON.get(), dyeTypes))
                    .addGroup("pants_silk", new ItemStack(WeaversParadiseItems.PANTS_SILK.get()), getVariantItem(WeaversParadiseItems.PANTS_SILK.get(), dyeTypes))
                    .addGroup("cape_cotton", new ItemStack(WeaversParadiseItems.COTTON_CAPE.get()), getVariantItem(WeaversParadiseItems.COTTON_CAPE.get(), dyeTypes))
                    .addGroup("cape_silk", new ItemStack(WeaversParadiseItems.SILK_CAPE.get()), getVariantItem(WeaversParadiseItems.SILK_CAPE.get(), dyeTypes))
                    .addGroup("cape_wool", new ItemStack(WeaversParadiseItems.WOOL_CAPE.get()), getVariantItem(WeaversParadiseItems.WOOL_CAPE.get(), dyeTypes))
                    .addGroup("choker", new ItemStack(WeaversParadiseItems.CHOKER.get()), getVariantItem(WeaversParadiseItems.CHOKER.get(), dyeTypes))
                    .icon(() -> WeaversParadiseItems.THIGH_HIGHS_COTTON.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.weaversparadise.clothing"))
                    .displayItems((parameters, output) -> {
                        List<Pair<String, Pair<Integer, Integer>>> metalTypes = List.of(
                                new Pair<>("minecraft:iron_ingot", new Pair<>(-1, 100)),
                                new Pair<>("minecraft:gold_ingot", new Pair<>(-1, 25)),
                                new Pair<>("minecraft:copper_ingot", new Pair<>(-4035764, 50))
                        );
                        List<ItemStack> chokerTrinkets = List.of(
                                WeaversParadiseItems.BELL.get().getDefaultInstance(),
                                WeaversParadiseItems.PLATE.get().getDefaultInstance(),
                                WeaversParadiseItems.RING.get().getDefaultInstance(),
                                WeaversParadiseItems.CAT_RING.get().getDefaultInstance(),
                                WeaversParadiseItems.HEART.get().getDefaultInstance()
                        );
                        for (Pair<String, Pair<Integer, Integer>> type : metalTypes) {
                            for (ItemStack stack : chokerTrinkets) {
                                ItemStack instance = stack.copy();

                                CompoundTag tag = instance.getOrCreateTag();

                                tag.putString("metalType", type.getA());
                                tag.putInt("color", type.getB().getA());
                                tag.putInt("damage", type.getB().getB());

                                instance.setTag(tag);

                                output.accept(instance);
                            }
                        }

                        output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_WIG.get());
                        output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE.get());
                        output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_LEGGINGS.get());
                        output.accept(WeaversParadiseItems.ASTOLFO_ARMOR_BOOTS.get());

                        output.accept(WeaversParadiseItems.BRIDGET_ARMOR_HAT.get());
                        output.accept(WeaversParadiseItems.BRIDGET_ARMOR_JACKET.get());
                        output.accept(WeaversParadiseItems.BRIDGET_ARMOR_SKIRT.get());
                        output.accept(WeaversParadiseItems.BRIDGET_ARMOR_BOOTS.get());

                        output.accept(WeaversParadiseItems.FELIX_ARMOR_HAT.get());
                        output.accept(WeaversParadiseItems.FELIX_ARMOR_JACKET.get());
                        output.accept(WeaversParadiseItems.FELIX_ARMOR_SKIRT.get());
                        output.accept(WeaversParadiseItems.FELIX_ARMOR_BOOTS.get());

                        output.accept(WeaversParadiseItems.GRIFFITH_ARMOR_WIG.get());
                        output.accept(WeaversParadiseItems.GRIFFITH_ARMOR_CHESTPLATE.get());
                        output.accept(WeaversParadiseItems.GRIFFITH_ARMOR_LEGGINGS.get());
                        output.accept(WeaversParadiseItems.GRIFFITH_ARMOR_BOOTS.get());

                        output.accept(WeaversParadiseItems.NIKO_ARMOR_HAT.get());
                        output.accept(WeaversParadiseItems.NIKO_ARMOR_JACKET.get());
                        output.accept(WeaversParadiseItems.NIKO_ARMOR_LEGGINGS.get());
                        output.accept(WeaversParadiseItems.NIKO_ARMOR_BOOTS.get());

                        output.accept(WeaversParadiseItems.GABRIEL_ARMOR_HELMET.get());
                        output.accept(WeaversParadiseItems.GABRIEL_ARMOR_CHESTPLATE.get());
                        output.accept(WeaversParadiseItems.GABRIEL_ARMOR_LEGGINGS.get());
                        output.accept(WeaversParadiseItems.GABRIEL_ARMOR_BOOTS.get());

                        output.accept(WeaversParadiseItems.GISELLE_ARMOR_HAT.get());
                        output.accept(WeaversParadiseItems.GISELLE_ARMOR_JACKET.get());
                        output.accept(WeaversParadiseItems.GISELLE_ARMOR_LEGGINGS.get());
                        output.accept(WeaversParadiseItems.GISELLE_ARMOR_BOOTS.get());

                        output.accept(WeaversParadiseItems.MIKKELA_ARMOR_WIG.get());
                        output.accept(WeaversParadiseItems.MIKKELA_ARMOR_CHESTPLATE.get());
                        output.accept(WeaversParadiseItems.MIKKELA_ARMOR_LEGGINGS.get());

                        output.accept(WeaversParadiseItems.LEATHER_GLOVES.get());
                    })
                    .build()
    );

    public static final RegistryObject<CreativeModeTab> WEAVERS_PARADISE_MISC = CREATIVE_MODE_TABS.register(
            "weavers_paradise_misc",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.weaversparadise.items"))
                    .icon(() -> WeaversParadiseItems.MORTAR_AND_PESTLE.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        List<Item> items = List.of(
                                WeaversParadiseItems.PIGMENT.get(),
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
                                WeaversParadiseItems.FLAG_TRANS.get(),
                                WeaversParadiseItems.ARMOR_LOOTBOX.get(),
                                WeaversParadiseItems.PLAYER_PLUSHIE_RENAME_TOKEN.get()
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
                                    CompoundTag tag = s.getOrCreateTag();
                                    tag.putInt("red", (int)vec3.x);
                                    tag.putInt("green", (int)vec3.y);
                                    tag.putInt("blue", (int)vec3.z);
                                    s.setTag(tag);
                                    output.accept(s);
                                }
                            } else {
                                output.accept(stack);
                            }
                        }

                        output.accept(PlushieItem.createPreMadePlushieAsync("Vortianski", null));
                        output.accept(PlushieItem.createPreMadePlushieAsync("Pelemeshek", null));
                    })
                    .build()
    );

    private static List<ItemStack> getQualityVariants(Item item) {
        List<ItemStack> items = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ItemStack itemStack = new ItemStack(item);
            final int f = i;

            CompoundTag compoundTag = new CompoundTag();

            compoundTag.putInt("quality", f);

            itemStack.setTag(compoundTag);

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
            ItemStack itemStack = singleSidedClothingItem.getDefaultInstance();
            itemStack.setTag(singleSidedClothingItem.obtainDefault());

            CompoundTag compoundTag = itemStack.getOrCreateTag();

            compoundTag.putString("dyeTypeOne", variant);
            compoundTag.putString("dyeTypeTwo", variant);
            compoundTag.putInt("colorPriRedOne", 255);
            compoundTag.putInt("colorPriGreenOne", 255);
            compoundTag.putInt("colorPriBlueOne", 255);
            compoundTag.putInt("colorPriRedTwo", 255);
            compoundTag.putInt("colorPriGreenTwo", 255);
            compoundTag.putInt("colorPriBlueTwo", 255);
            compoundTag.putInt("colorSecRedOne", 255);
            compoundTag.putInt("colorSecGreenOne", 255);
            compoundTag.putInt("colorSecBlueOne", 255);
            compoundTag.putInt("colorSecRedTwo", 255);
            compoundTag.putInt("colorSecGreenTwo", 255);
            compoundTag.putInt("colorSecBlueTwo", 255);

            itemStack.setTag(compoundTag);

            items.add(itemStack);
        }

        return items;
    }

    private static List<ItemStack> getDoubleSidedVariants(DoubleSidedClothingItem doubleSidedClothingItem, List<String> variants) {
        List<ItemStack> items = new ArrayList<>();

        for (String variant : variants) {
            ItemStack itemStack = doubleSidedClothingItem.getDefaultInstance();
            itemStack.setTag(doubleSidedClothingItem.obtainDefault());

            CompoundTag compoundTag = itemStack.getOrCreateTag();

            compoundTag.putString("dyeTypeLeftOne", variant);
            compoundTag.putString("dyeTypeLeftTwo", variant);
            compoundTag.putString("dyeTypeRightOne", variant);
            compoundTag.putString("dyeTypeRightTwo", variant);
            compoundTag.putInt("colorPriRedLeftOne", 255);
            compoundTag.putInt("colorPriGreenLeftOne", 255);
            compoundTag.putInt("colorPriBlueLeftOne", 255);
            compoundTag.putInt("colorPriRedLeftTwo", 255);
            compoundTag.putInt("colorPriGreenLeftTwo", 255);
            compoundTag.putInt("colorPriBlueLeftTwo", 255);
            compoundTag.putInt("colorSecRedLeftOne", 255);
            compoundTag.putInt("colorSecGreenLeftOne", 255);
            compoundTag.putInt("colorSecBlueLeftOne", 255);
            compoundTag.putInt("colorSecRedLeftTwo", 255);
            compoundTag.putInt("colorSecGreenLeftTwo", 255);
            compoundTag.putInt("colorSecBlueLeftTwo", 255);
            compoundTag.putInt("colorPriRedRightOne", 255);
            compoundTag.putInt("colorPriGreenRightOne", 255);
            compoundTag.putInt("colorPriBlueRightOne", 255);
            compoundTag.putInt("colorPriRedRightTwo", 255);
            compoundTag.putInt("colorPriGreenRightTwo", 255);
            compoundTag.putInt("colorPriBlueRightTwo", 255);
            compoundTag.putInt("colorSecRedRightOne", 255);
            compoundTag.putInt("colorSecGreenRightOne", 255);
            compoundTag.putInt("colorSecBlueRightOne", 255);
            compoundTag.putInt("colorSecRedRightTwo", 255);
            compoundTag.putInt("colorSecGreenRightTwo", 255);
            compoundTag.putInt("colorSecBlueRightTwo", 255);

            itemStack.setTag(compoundTag);

            items.add(itemStack);
        }

        return items;
    }
}
