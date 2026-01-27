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
import xox.labvorty.weaversparadise.items.PlushieItem;

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
                        "height_sea"
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

                ItemStack handWarmersCotton = new ItemStack(WeaversParadiseItems.HAND_WARMERS_COTTON.get());
                ItemStack handWarmersSilk = new ItemStack(WeaversParadiseItems.HAND_WARMERS_SILK.get());
                ItemStack handWarmersWool = new ItemStack(WeaversParadiseItems.HAND_WARMERS_WOOL.get());

                ItemStack baseThighHighsCotton = new ItemStack(WeaversParadiseItems.THIGH_HIGHS_COTTON.get());
                ItemStack baseThighHighsSilk = new ItemStack(WeaversParadiseItems.THIGH_HIGHS_SILK.get());
                ItemStack baseThighHighsWool = new ItemStack(WeaversParadiseItems.THIGH_HIGHS_WOOL.get());

                output.accept(baseThighHighsCotton);
                output.accept(baseThighHighsSilk);
                output.accept(baseThighHighsWool);

                for (String entry : thighHighsTypes) {
                    ItemStack thighHighsCotton = baseThighHighsCotton.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, thighHighsCotton, (tag) -> {
                        tag.putString("stensilTypeLeft", entry);
                        tag.putString("stensilTypeRight", entry);
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack thighHighsSilk = baseThighHighsSilk.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, thighHighsSilk, (tag) -> {
                        tag.putString("stensilTypeLeft", entry);
                        tag.putString("stensilTypeRight", entry);
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack thighHighsWool = baseThighHighsWool.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, thighHighsWool, (tag) -> {
                        tag.putString("stensilTypeLeft", entry);
                        tag.putString("stensilTypeRight", entry);
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
                        tag.putString("stensilType", entry);
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    ItemStack shirtSilkS = shirtSilk.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, shirtSilkS, (tag) -> {
                        tag.putString("stensilType", entry);
                        tag.putString("dyeTypeOne", entry);
                        tag.putString("dyeTypeTwo", entry);
                    });

                    ItemStack sweaterWoolS = sweaterWool.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, sweaterWoolS, (tag) -> {
                        tag.putString("stensilType", entry);
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
                        tag.putString("stensilTypeLeft", entry);
                        tag.putString("stensilTypeRight", entry);
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack handWarmersSilkS = handWarmersSilk.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, handWarmersSilkS, (tag) -> {
                        tag.putString("stensilTypeLeft", entry);
                        tag.putString("stensilTypeRight", entry);
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    ItemStack handWarmersWoolS = handWarmersWool.copy();
                    CustomData.update(DataComponents.CUSTOM_DATA, handWarmersWoolS, (tag) -> {
                        tag.putString("stensilTypeLeft", entry);
                        tag.putString("stensilTypeRight", entry);
                        tag.putString("dyeTypeLeftOne", entry);
                        tag.putString("dyeTypeLeftTwo", entry);
                        tag.putString("dyeTypeRightOne", entry);
                        tag.putString("dyeTypeRightTwo", entry);
                    });

                    output.accept(handWarmersCottonS);
                    output.accept(handWarmersSilkS);
                    output.accept(handWarmersWoolS);
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
                        WeaversParadiseItems.BUTTON.get(),
                        WeaversParadiseItems.BASIC_STENCIL.get(),
                        WeaversParadiseItems.HALF_STENCIL.get(),
                        WeaversParadiseItems.CHECKERS_STENCIL.get(),
                        WeaversParadiseItems.LINES_SMALL_STENCIL.get(),
                        WeaversParadiseItems.LINES_BIG_STENCIL.get(),
                        WeaversParadiseItems.LINES_VERTICAL_STENCIL.get(),
                        WeaversParadiseItems.CROSS_STENCIL.get(),
                        WeaversParadiseItems.PAWS_STENCIL.get(),
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
                    output.accept(stack);
                }

                //Can use both null and preset UUID. If none are given defaults
                output.accept(PlushieItem.createPreMadePlushieAsync("Vortianski", UUID.fromString("383b1ab0-ae8f-4342-b94d-b4f3d2cfc9c0")));
                output.accept(PlushieItem.createPreMadePlushieAsync("Pelemeshek", null));
            }).build()
    );
}
