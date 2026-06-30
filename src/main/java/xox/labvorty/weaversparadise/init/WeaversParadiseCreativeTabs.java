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
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

import java.util.List;

public class WeaversParadiseCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WeaversParadiseMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WEAVERS_PARADISE_MATERIALS = CREATIVE_MODE_TABS.register(
            "weavers_paradise_materials",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.weaversparadise.quality_materials"))
                    .icon(() -> WeaversParadiseItems.COTTON_CLOTH.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        List<ItemStack> qualityItems = List.of(
                                WeaversParadiseItems.COTTON_CLOTH.get().getDefaultInstance(),
                                WeaversParadiseItems.SILK_CLOTH.get().getDefaultInstance(),
                                WeaversParadiseItems.WOOL_CLOTH.get().getDefaultInstance(),
                                WeaversParadiseItems.JEANS_CLOTH.get().getDefaultInstance(),
                                WeaversParadiseItems.COTTON_UPPERWEAR_BASE.get().getDefaultInstance(),
                                WeaversParadiseItems.COTTON_UPPERWEAR_BASE_BUTTONS.get().getDefaultInstance(),
                                WeaversParadiseItems.COTTON_UPPERWEAR_BASE_ZIPPER.get().getDefaultInstance(),
                                WeaversParadiseItems.COTTON_SLEEVE_SHORT.get().getDefaultInstance(),
                                WeaversParadiseItems.COTTON_SLEEVE_LONG.get().getDefaultInstance(),
                                WeaversParadiseItems.COTTON_PANT_LEG.get().getDefaultInstance(),
                                WeaversParadiseItems.SILK_UPPERWEAR_BASE.get().getDefaultInstance(),
                                WeaversParadiseItems.SILK_UPPERWEAR_BASE_BUTTONS.get().getDefaultInstance(),
                                WeaversParadiseItems.SILK_UPPERWEAR_BASE_ZIPPER.get().getDefaultInstance(),
                                WeaversParadiseItems.SILK_SLEEVE_SHORT.get().getDefaultInstance(),
                                WeaversParadiseItems.SILK_SLEEVE_LONG.get().getDefaultInstance(),
                                WeaversParadiseItems.SILK_PANT_LEG.get().getDefaultInstance(),
                                WeaversParadiseItems.WOOL_UPPERWEAR_BASE.get().getDefaultInstance(),
                                WeaversParadiseItems.WOOL_UPPERWEAR_BASE_BUTTONS.get().getDefaultInstance(),
                                WeaversParadiseItems.WOOL_SLEEVE_SHORT.get().getDefaultInstance(),
                                WeaversParadiseItems.WOOL_SLEEVE_LONG.get().getDefaultInstance(),
                                WeaversParadiseItems.JEANS_PANT_LEG.get().getDefaultInstance()
                        );

                        for (ItemStack itemStack : qualityItems) {
                            for (int i = 0; i <= 10; i++) {
                                ItemStack stack = itemStack.copy();

                                stack.getOrCreateTag().putInt("quality", i);
                                output.accept(stack);
                            }
                        }
                    })
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
            () -> CreativeModeTab.builder()
                    .icon(() -> WeaversParadiseItems.THIGH_HIGHS_COTTON.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.weaversparadise.clothing"))
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
                                                WeaversParadiseItems.THIGH_HIGHS_COTTON.get().getDefaultInstance(),
                                                WeaversParadiseItems.THIGH_HIGHS_SILK.get().getDefaultInstance(),
                                                WeaversParadiseItems.THIGH_HIGHS_WOOL.get().getDefaultInstance()
                                        )
                                ),
                                new Pair<>(
                                        "double",
                                        List.of(
                                                WeaversParadiseItems.HAND_WARMERS_COTTON.get().getDefaultInstance(),
                                                WeaversParadiseItems.HAND_WARMERS_SILK.get().getDefaultInstance(),
                                                WeaversParadiseItems.HAND_WARMERS_WOOL.get().getDefaultInstance()
                                        )
                                ),
                                new Pair<>(
                                        "single",
                                        List.of(
                                                WeaversParadiseItems.SHIRT_COTTON.get().getDefaultInstance(),
                                                WeaversParadiseItems.SHIRT_SILK.get().getDefaultInstance(),
                                                WeaversParadiseItems.SWEATER_WOOL.get().getDefaultInstance()
                                        )
                                ),
                                new Pair<>(
                                        "single",
                                        List.of(
                                                WeaversParadiseItems.PANTS_JEANS.get().getDefaultInstance(),
                                                WeaversParadiseItems.PANTS_COTTON.get().getDefaultInstance(),
                                                WeaversParadiseItems.PANTS_SILK.get().getDefaultInstance()
                                        )
                                ),
                                new Pair<>(
                                        "single",
                                        List.of(
                                                WeaversParadiseItems.COTTON_CAPE.get().getDefaultInstance(),
                                                WeaversParadiseItems.SILK_CAPE.get().getDefaultInstance(),
                                                WeaversParadiseItems.WOOL_CAPE.get().getDefaultInstance()
                                        )
                                ),
                                new Pair<>(
                                        "double",
                                        List.of(
                                                WeaversParadiseItems.CHOKER.get().getDefaultInstance()
                                        )
                                )
                        );
                        List<ItemStack> chokerTrinkets = List.of(
                                WeaversParadiseItems.BELL.get().getDefaultInstance(),
                                WeaversParadiseItems.PLATE.get().getDefaultInstance(),
                                WeaversParadiseItems.RING.get().getDefaultInstance(),
                                WeaversParadiseItems.CAT_RING.get().getDefaultInstance(),
                                WeaversParadiseItems.HEART.get().getDefaultInstance()
                        );

                        for (Pair<String, List<ItemStack>> pair : dyedItems) {
                            String dyeingType = pair.getA();
                            List<ItemStack> stacks = pair.getB();

                            for (String type : types) {
                                for (ItemStack stack : stacks) {
                                    ItemStack instance = stack.copy();

                                    if (dyeingType.equals("double")) {
                                        CompoundTag tag = instance.getOrCreateTag();

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

                                        instance.setTag(tag);
                                    } else if (dyeingType.equals("single")) {
                                        CompoundTag tag = instance.getOrCreateTag();

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

                                        instance.setTag(tag);
                                    }

                                    output.accept(instance);
                                }
                            }
                        }
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
}
