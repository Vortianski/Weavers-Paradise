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
import xox.labvorty.weaversparadise.items.PlushieItem;
import xox.labvorty.weaversparadise.items.PureDyeItem;

import java.util.List;
import java.util.UUID;

public class WeaversParadiseCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "weaversparadise");
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> QUALITY_MATERIALS_TAB = CREATIVE_MODE_TABS.register("quality_materials_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.weaversparadise.quality_materials"))
            .icon(() -> WeaversParadiseItems.COTTON_CLOTH.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack cottonStack = new ItemStack(WeaversParadiseItems.COTTON_CLOTH.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, cottonStack, (tag) -> tag.putInt("quality", quality));

                    output.accept(cottonStack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack silkStack = new ItemStack(WeaversParadiseItems.SILK_CLOTH.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, silkStack, (tag) -> tag.putInt("quality", quality));

                    output.accept(silkStack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack woolStack = new ItemStack(WeaversParadiseItems.WOOL_CLOTH.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, woolStack, (tag) -> tag.putInt("quality", quality));

                    output.accept(woolStack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack jeansStack = new ItemStack(WeaversParadiseItems.JEANS_CLOTH.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, jeansStack, (tag) -> tag.putInt("quality", quality));

                    output.accept(jeansStack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_BUTTONS.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_ZIPPER.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_SLEEVE_SHORT.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_SLEEVE_LONG.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_PANT_LEG.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE_BUTTONS.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE_ZIPPER.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.SILK_SLEEVE_SHORT.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.SILK_SLEEVE_LONG.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.SILK_PANT_LEG.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.WOOL_UPPERWEAR_BASE.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.WOOL_UPPERWEAR_BASE_BUTTONS.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.WOOL_SLEEVE_SHORT.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.WOOL_SLEEVE_LONG.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }

                for (int i = 0; i <= 10; i++) {
                    final int quality = i;
                    ItemStack stack = new ItemStack(WeaversParadiseItems.JEANS_PANT_LEG.get());
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", quality));

                    output.accept(stack);
                }
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DYES_TAB = CREATIVE_MODE_TABS.register("dyes_tab", () -> CreativeModeTab.builder()
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
                        "sculk",
                        "glowstone",
                        "rainbow",
                        "biome",
                        "ender",
                        "speed",
                        "height_bedrock",
                        "height_sea",
                        "invisible",
                        "static",
                        "crystal"
                );
                ItemStack baseDye = new ItemStack(WeaversParadiseItems.BOTTLED_DYE.get());

                ItemStack baseDyeCore = new ItemStack(WeaversParadiseItems.DYE_CORE.get());

                ItemStack defaultDye = baseDye.copy();
                CustomData.update(DataComponents.CUSTOM_DATA, defaultDye, (tag) -> tag.putString("dyeType", "default"));
                output.accept(defaultDye);

                for (String entry : dyeTypes) {
                    ItemStack stack = defaultDye.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", entry);
                    });
                    output.accept(stack);
                }

                for (int i = 0; i <= 15; i++) {
                    final int lightValue = i;
                    final int color = Mth.lerpInt(lightValue / 15.0f, 100, 255);
                    ItemStack redstoneDye = baseDye.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, redstoneDye, (tag) -> {
                        tag.putString("dyeType", "redstone");
                        tag.putInt("lightValue", lightValue);
                        tag.putInt("colorRedOne", color);
                        tag.putInt("colorGreenOne", 0);
                        tag.putInt("colorBlueOne", 0);
                    });
                    output.accept(redstoneDye);
                }

                for (int i = 0; i <= 15; i++) {
                    final int lightValue = i;
                    final int color = Mth.lerpInt(lightValue / 15.0f, 100, 255);
                    ItemStack redstoneDye = baseDye.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, redstoneDye, (tag) -> {
                        tag.putString("dyeType", "lamp");
                        tag.putInt("lightValue", lightValue);
                        tag.putInt("colorRedOne", 255);
                        tag.putInt("colorGreenOne", 255);
                        tag.putInt("colorBlueOne", 255);
                    });
                    output.accept(redstoneDye);
                }

                ItemStack polychromaticDye = baseDye.copy();
                CustomData.update(DataComponents.CUSTOM_DATA, polychromaticDye, (tag) -> {
                    tag.putString("dyeType", "polychromatic");
                    tag.putInt("colorRedOne", 255);
                    tag.putInt("colorGreenOne", 0);
                    tag.putInt("colorBlueOne", 0);
                });
                output.accept(polychromaticDye);

                for (String entry : dyeTypes) {
                    ItemStack stack = baseDyeCore.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", entry);
                    });
                    output.accept(stack);
                }

                for (int i = 0; i <= 15; i++) {
                    final int lightValue = i;
                    final int color = Mth.lerpInt(lightValue / 15.0f, 100, 255);
                    ItemStack redstoneDye = baseDyeCore.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, redstoneDye, (tag) -> {
                        tag.putString("dyeType", "redstone");
                        tag.putInt("lightValue", lightValue);
                        tag.putInt("colorRedOne", color);
                        tag.putInt("colorGreenOne", 0);
                        tag.putInt("colorBlueOne", 0);
                    });
                    output.accept(redstoneDye);
                }

                for (int i = 0; i <= 15; i++) {
                    final int lightValue = i;
                    final int color = Mth.lerpInt(lightValue / 15.0f, 100, 255);
                    ItemStack redstoneDye = baseDyeCore.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, redstoneDye, (tag) -> {
                        tag.putString("dyeType", "lamp");
                        tag.putInt("lightValue", lightValue);
                        tag.putInt("colorRedOne", 255);
                        tag.putInt("colorGreenOne", 255);
                        tag.putInt("colorBlueOne", 255);
                    });
                    output.accept(redstoneDye);
                }

                ItemStack polychromaticCore = baseDyeCore.copy();
                CustomData.update(DataComponents.CUSTOM_DATA, polychromaticCore, (tag) -> {
                    tag.putString("dyeType", "polychromatic");
                    tag.putInt("colorRedOne", 255);
                    tag.putInt("colorGreenOne", 0);
                    tag.putInt("colorBlueOne", 0);
                });
                output.accept(polychromaticCore);
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CLOTHING_TAB = CREATIVE_MODE_TABS.register("clothing_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.weaversparadise.clothing"))
            .icon(() -> WeaversParadiseItems.THIGH_HIGHS_COTTON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                List<String> thighHighsTypes = List.of(
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
                ItemStack astolfoHead = new ItemStack(WeaversParadiseItems.ASTOLFO_ARMOR_WIG.get());
                ItemStack astolfoChestplate = new ItemStack(WeaversParadiseItems.ASTOLFO_ARMOR_CHESTPLATE.get());
                ItemStack astolfoLeggings = new ItemStack(WeaversParadiseItems.ASTOLFO_ARMOR_LEGGINGS.get());
                ItemStack astolfoBoots = new ItemStack(WeaversParadiseItems.ASTOLFO_ARMOR_BOOTS.get());

                ItemStack leatherGloves = new ItemStack(WeaversParadiseItems.LEATHER_GLOVES.get());

                ItemStack shirtCotton = new ItemStack(WeaversParadiseItems.SHIRT_COTTON.get());
                ItemStack shirtSilk = new ItemStack(WeaversParadiseItems.SHIRT_SILK.get());
                ItemStack sweaterWool = new ItemStack(WeaversParadiseItems.SWEATER_WOOL.get());

                ItemStack pantsJeans = new ItemStack(WeaversParadiseItems.PANTS_JEANS.get());
                ItemStack pantsCotton = new ItemStack(WeaversParadiseItems.PANTS_COTTON.get());
                ItemStack pantsSilk = new ItemStack(WeaversParadiseItems.PANTS_SILK.get());

                ItemStack handWarmersCotton = new ItemStack(WeaversParadiseItems.HAND_WARMERS_COTTON.get());
                ItemStack handWarmersSilk = new ItemStack(WeaversParadiseItems.HAND_WARMERS_SILK.get());
                ItemStack handWarmersWool = new ItemStack(WeaversParadiseItems.HAND_WARMERS_WOOL.get());

                ItemStack baseThighHighsCotton = new ItemStack(WeaversParadiseItems.THIGH_HIGHS_COTTON.get());
                ItemStack baseThighHighsSilk = new ItemStack(WeaversParadiseItems.THIGH_HIGHS_SILK.get());
                ItemStack baseThighHighsWool = new ItemStack(WeaversParadiseItems.THIGH_HIGHS_WOOL.get());

                ItemStack baseChoker = new ItemStack(WeaversParadiseItems.CHOKER.get());

                output.accept(baseThighHighsCotton);
                output.accept(baseThighHighsSilk);
                output.accept(baseThighHighsWool);

                for (String entry : thighHighsTypes) {
                    ItemStack thighHighsCotton = baseThighHighsCotton.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, thighHighsCotton, (tag) -> {
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack thighHighsSilk = baseThighHighsSilk.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, thighHighsSilk, (tag) -> {
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack thighHighsWool = baseThighHighsWool.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, thighHighsWool, (tag) -> {
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    output.accept(thighHighsCotton);
                    output.accept(thighHighsSilk);
                    output.accept(thighHighsWool);
                }

                output.accept(shirtCotton);
                output.accept(shirtSilk);
                output.accept(sweaterWool);

                for (String entry : thighHighsTypes) {
                    ItemStack shirtCottonS = shirtCotton.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, shirtCottonS, (tag) -> {
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    ItemStack shirtSilkS = shirtSilk.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, shirtSilkS, (tag) -> {
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    ItemStack sweaterWoolS = sweaterWool.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, sweaterWoolS, (tag) -> {
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    output.accept(shirtCottonS);
                    output.accept(shirtSilkS);
                    output.accept(sweaterWoolS);
                }

                output.accept(handWarmersCotton);
                output.accept(handWarmersSilk);
                output.accept(handWarmersWool);

                for (String entry : thighHighsTypes) {
                    ItemStack handWarmersCottonS = handWarmersCotton.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, handWarmersCottonS, (tag) -> {
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack handWarmersSilkS = handWarmersSilk.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, handWarmersSilkS, (tag) -> {
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack handWarmersWoolS = handWarmersWool.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, handWarmersWoolS, (tag) -> {
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    output.accept(handWarmersCottonS);
                    output.accept(handWarmersSilkS);
                    output.accept(handWarmersWoolS);
                }

                output.accept(pantsJeans);
                output.accept(pantsCotton);
                output.accept(pantsSilk);

                for (String entry : thighHighsTypes) {
                    ItemStack pantsJeansS = pantsJeans.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, pantsJeansS, (tag) -> {
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    ItemStack pantsCottonS = pantsCotton.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, pantsCottonS, (tag) -> {
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    ItemStack pantsSilkS = pantsSilk.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, pantsSilkS, (tag) -> {
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    output.accept(pantsJeansS);
                    output.accept(pantsCottonS);
                    output.accept(pantsSilkS);
                }

                output.accept(baseChoker);

                for (String entry : thighHighsTypes) {
                    ItemStack chokerS = baseChoker.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, chokerS, (tag) -> {
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
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


                    output.accept(chokerS);
                }

                List<Pair<String, Pair<Integer, Integer>>> metalTypes = List.of(
                        new Pair<>("minecraft:iron_ingot", new Pair<>(-1, 100)),
                        new Pair<>("minecraft:gold_ingot", new Pair<>(-1, 25)),
                        new Pair<>("minecraft:copper_ingot", new Pair<>(-4035764, 50))
                );

                for (Pair<String, Pair<Integer, Integer>> type : metalTypes) {
                    ItemStack bell = new ItemStack(WeaversParadiseItems.BELL.get());
                    ItemStack plate = new ItemStack(WeaversParadiseItems.PLATE.get());
                    ItemStack ring = new ItemStack(WeaversParadiseItems.RING.get());
                    ItemStack cat_ring = new ItemStack(WeaversParadiseItems.CAT_RING.get());
                    ItemStack heart = new ItemStack(WeaversParadiseItems.HEART.get());

                    CustomData.update(DataComponents.CUSTOM_DATA, bell, (tag) -> {
                        tag.putString("metalType", type.getA());
                        tag.putInt("color", type.getB().getA());
                        tag.putInt("damage", type.getB().getB());
                    });
                    CustomData.update(DataComponents.CUSTOM_DATA, plate, (tag) -> {
                        tag.putString("metalType", type.getA());
                        tag.putInt("color", type.getB().getA());
                        tag.putInt("damage", type.getB().getB());
                    });
                    CustomData.update(DataComponents.CUSTOM_DATA, ring, (tag) -> {
                        tag.putString("metalType", type.getA());
                        tag.putInt("color", type.getB().getA());
                        tag.putInt("damage", type.getB().getB());
                    });
                    CustomData.update(DataComponents.CUSTOM_DATA, cat_ring, (tag) -> {
                        tag.putString("metalType", type.getA());
                        tag.putInt("color", type.getB().getA());
                        tag.putInt("damage", type.getB().getB());
                    });
                    CustomData.update(DataComponents.CUSTOM_DATA, heart, (tag) -> {
                        tag.putString("metalType", type.getA());
                        tag.putInt("color", type.getB().getA());
                        tag.putInt("damage", type.getB().getB());
                    });

                    output.accept(bell);
                    output.accept(ring);
                    output.accept(cat_ring);
                    output.accept(heart);
                    output.accept(plate);
                }

                output.accept(astolfoHead);
                output.accept(astolfoChestplate);
                output.accept(astolfoLeggings);
                output.accept(astolfoBoots);

                output.accept(leatherGloves);
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

                    if (stack.getItem() instanceof PureDyeItem pureDyeItem) {
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
            }).build()
    );
}
