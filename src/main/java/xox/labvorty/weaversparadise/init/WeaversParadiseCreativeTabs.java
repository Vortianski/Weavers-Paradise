package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.data.creative_tab.ExpandableCreativeTab;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;
import xox.labvorty.weaversparadise.utilities.CreativeModeTabProviders;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Порт 4 оригинальных вкладок на ExpandableCreativeTab (фасад поверх ванильного билдера;
 * группы раскрываются по клику на иконке — «+»/«−», рамки и затемнение рисуют миксины).
 * withTabsBefore (NeoForge-only) убран: порядок = builder(Row.TOP, column 0..3) + порядок
 * регистрации полей. displayItems-генераторы обёрнуты в dedup (ваниль кидает
 * IllegalStateException на равных стеках в одной вкладке). Группа "pigments" включает
 * PURE_DYE с теми же компонентами, что и «голый» стак из списка ниже — в Output он не
 * попадает (группы обходят Output), но обёртка оставлена как страховка.
 */
public class WeaversParadiseCreativeTabs {

    public static final CreativeModeTab CLOTHING_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "clothing_tab"),
            ExpandableCreativeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .addGroup("thigh_highs_cotton", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_COTTON), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_COTTON, WeaversUtilities.dyeTypes))
                    .addGroup("thigh_highs_silk", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_SILK), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_SILK, WeaversUtilities.dyeTypes))
                    .addGroup("thigh_highs_wool", new ItemStack(WeaversParadiseItems.THIGH_HIGHS_WOOL), getVariantItem(WeaversParadiseItems.THIGH_HIGHS_WOOL, WeaversUtilities.dyeTypes))
                    .addGroup("hand_warmers_cotton", new ItemStack(WeaversParadiseItems.HAND_WARMERS_COTTON), getVariantItem(WeaversParadiseItems.HAND_WARMERS_COTTON, WeaversUtilities.dyeTypes))
                    .addGroup("hand_warmers_silk", new ItemStack(WeaversParadiseItems.HAND_WARMERS_SILK), getVariantItem(WeaversParadiseItems.HAND_WARMERS_SILK, WeaversUtilities.dyeTypes))
                    .addGroup("hand_warmers_wool", new ItemStack(WeaversParadiseItems.HAND_WARMERS_WOOL), getVariantItem(WeaversParadiseItems.HAND_WARMERS_WOOL, WeaversUtilities.dyeTypes))
                    .addGroup("t_shirt", new ItemStack(WeaversParadiseItems.T_SHIRT), getVariantItem(WeaversParadiseItems.T_SHIRT, WeaversUtilities.dyeTypes))
                    .addGroup("tank_top", new ItemStack(WeaversParadiseItems.TANK_TOP), getVariantItem(WeaversParadiseItems.TANK_TOP, WeaversUtilities.dyeTypes))
                    .addGroup("wool_vest", new ItemStack(WeaversParadiseItems.WOOL_VEST), getVariantItem(WeaversParadiseItems.WOOL_VEST, WeaversUtilities.dyeTypes))
                    .addGroup("shirt_cotton", new ItemStack(WeaversParadiseItems.SHIRT_COTTON), getVariantItem(WeaversParadiseItems.SHIRT_COTTON, WeaversUtilities.dyeTypes))
                    .addGroup("shirt_silk", new ItemStack(WeaversParadiseItems.SHIRT_SILK), getVariantItem(WeaversParadiseItems.SHIRT_SILK, WeaversUtilities.dyeTypes))
                    .addGroup("long_sleeve_cotton", new ItemStack(WeaversParadiseItems.LONG_SLEEVE_COTTON), getVariantItem(WeaversParadiseItems.LONG_SLEEVE_COTTON, WeaversUtilities.dyeTypes))
                    .addGroup("sweater_wool", new ItemStack(WeaversParadiseItems.SWEATER_WOOL), getVariantItem(WeaversParadiseItems.SWEATER_WOOL, WeaversUtilities.dyeTypes))
                    .addGroup("poncho", new ItemStack(WeaversParadiseItems.PONCHO), getVariantItem(WeaversParadiseItems.PONCHO, WeaversUtilities.dyeTypes))
                    .addGroup("cotton_crop_top_long_sleeved", new ItemStack(WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED), getVariantItem(WeaversParadiseItems.COTTON_CROP_TOP_LONG_SLEEVED, WeaversUtilities.dyeTypes))
                    .addGroup("pants_jeans", new ItemStack(WeaversParadiseItems.PANTS_JEANS), getVariantItem(WeaversParadiseItems.PANTS_JEANS, WeaversUtilities.dyeTypes))
                    .addGroup("pants_cotton", new ItemStack(WeaversParadiseItems.PANTS_COTTON), getVariantItem(WeaversParadiseItems.PANTS_COTTON, WeaversUtilities.dyeTypes))
                    .addGroup("pants_silk", new ItemStack(WeaversParadiseItems.PANTS_SILK), getVariantItem(WeaversParadiseItems.PANTS_SILK, WeaversUtilities.dyeTypes))
                    .addGroup("pants_wool", new ItemStack(WeaversParadiseItems.PANTS_WOOL), getVariantItem(WeaversParadiseItems.PANTS_WOOL, WeaversUtilities.dyeTypes))
                    .addGroup("skirt_cotton", new ItemStack(WeaversParadiseItems.COTTON_SKIRT), getVariantItem(WeaversParadiseItems.COTTON_SKIRT, WeaversUtilities.dyeTypes))
                    .addGroup("cape_cotton", new ItemStack(WeaversParadiseItems.COTTON_CAPE), getVariantItem(WeaversParadiseItems.COTTON_CAPE, WeaversUtilities.dyeTypes))
                    .addGroup("cape_silk", new ItemStack(WeaversParadiseItems.SILK_CAPE), getVariantItem(WeaversParadiseItems.SILK_CAPE, WeaversUtilities.dyeTypes))
                    .addGroup("cape_wool", new ItemStack(WeaversParadiseItems.WOOL_CAPE), getVariantItem(WeaversParadiseItems.WOOL_CAPE, WeaversUtilities.dyeTypes))
                    .addGroup("pompon_hat", new ItemStack(WeaversParadiseItems.POMPON_HAT), getVariantItem(WeaversParadiseItems.POMPON_HAT, WeaversUtilities.dyeTypes))
                    .addGroup("cap", new ItemStack(WeaversParadiseItems.CAP), getVariantItem(WeaversParadiseItems.CAP, WeaversUtilities.dyeTypes))
                    .addGroup("ushanka", new ItemStack(WeaversParadiseItems.USHANKA), getVariantItem(WeaversParadiseItems.USHANKA, WeaversUtilities.dyeTypes))
                    .addGroup("choker", new ItemStack(WeaversParadiseItems.CHOKER), getVariantItem(WeaversParadiseItems.CHOKER, WeaversUtilities.dyeTypes))
                    .addGroup("armor_cosmetics", new ItemStack(WeaversParadiseItems.ASTOLFO_COSMETICS), CreativeModeTabProviders.getArmorCosmetics())
                    .addGroup("bell", new ItemStack(WeaversParadiseItems.BELL), CreativeModeTabProviders.getTrinketList(WeaversParadiseItems.BELL))
                    .addGroup("plate", new ItemStack(WeaversParadiseItems.PLATE), CreativeModeTabProviders.getTrinketList(WeaversParadiseItems.PLATE))
                    .addGroup("ring", new ItemStack(WeaversParadiseItems.RING), CreativeModeTabProviders.getTrinketList(WeaversParadiseItems.RING))
                    .addGroup("cat_ring", new ItemStack(WeaversParadiseItems.CAT_RING), CreativeModeTabProviders.getTrinketList(WeaversParadiseItems.CAT_RING))
                    .addGroup("heart", new ItemStack(WeaversParadiseItems.HEART), CreativeModeTabProviders.getTrinketList(WeaversParadiseItems.HEART))
                    .addGroup("fish", new ItemStack(WeaversParadiseItems.FISH), CreativeModeTabProviders.getTrinketList(WeaversParadiseItems.FISH))
                    .title(Component.translatable("itemGroup.weaversparadise.clothing"))
                    .icon(() -> new ItemStack(WeaversParadiseItems.THIGH_HIGHS_COTTON))
                    .displayItems((parameters, output) -> {
                        CreativeModeTab.Output safe = dedup(output);
                        safe.accept(WeaversParadiseItems.LEATHER_GLOVES);
                    })
                    .build()
    );

    public static final CreativeModeTab QUALITY_MATERIALS_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "quality_materials_tab"),
            ExpandableCreativeTab.builder(CreativeModeTab.Row.TOP, 1)
                    .addGroup("cotton_cloth", new ItemStack(WeaversParadiseItems.COTTON_CLOTH), getQualityVariants(WeaversParadiseItems.COTTON_CLOTH))
                    .addGroup("silk_cloth", new ItemStack(WeaversParadiseItems.SILK_CLOTH), getQualityVariants(WeaversParadiseItems.SILK_CLOTH))
                    .addGroup("wool_cloth", new ItemStack(WeaversParadiseItems.WOOL_CLOTH), getQualityVariants(WeaversParadiseItems.WOOL_CLOTH))
                    .addGroup("jeans_cloth", new ItemStack(WeaversParadiseItems.JEANS_CLOTH), getQualityVariants(WeaversParadiseItems.JEANS_CLOTH))
                    .addGroup("cotton_upperwear_base", new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE), getQualityVariants(WeaversParadiseItems.COTTON_UPPERWEAR_BASE))
                    .addGroup("cotton_upperwear_base_buttons", new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_BUTTONS), getQualityVariants(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_BUTTONS))
                    .addGroup("cotton_upperwear_base_zipper", new ItemStack(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_ZIPPER), getQualityVariants(WeaversParadiseItems.COTTON_UPPERWEAR_BASE_ZIPPER))
                    .addGroup("cotton_sleeve_short", new ItemStack(WeaversParadiseItems.COTTON_SLEEVE_SHORT), getQualityVariants(WeaversParadiseItems.COTTON_SLEEVE_SHORT))
                    .addGroup("cotton_sleeve_long", new ItemStack(WeaversParadiseItems.COTTON_SLEEVE_LONG), getQualityVariants(WeaversParadiseItems.COTTON_SLEEVE_LONG))
                    .addGroup("cotton_pant_leg", new ItemStack(WeaversParadiseItems.COTTON_PANT_LEG), getQualityVariants(WeaversParadiseItems.COTTON_PANT_LEG))
                    .addGroup("silk_upperwear_base", new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE), getQualityVariants(WeaversParadiseItems.SILK_UPPERWEAR_BASE))
                    .addGroup("silk_upperwear_base_buttons", new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE_BUTTONS), getQualityVariants(WeaversParadiseItems.SILK_UPPERWEAR_BASE_BUTTONS))
                    .addGroup("silk_upperwear_base_zipper", new ItemStack(WeaversParadiseItems.SILK_UPPERWEAR_BASE_ZIPPER), getQualityVariants(WeaversParadiseItems.SILK_UPPERWEAR_BASE_ZIPPER))
                    .addGroup("silk_sleeve_short", new ItemStack(WeaversParadiseItems.SILK_SLEEVE_SHORT), getQualityVariants(WeaversParadiseItems.SILK_SLEEVE_SHORT))
                    .addGroup("silk_sleeve_long", new ItemStack(WeaversParadiseItems.SILK_SLEEVE_LONG), getQualityVariants(WeaversParadiseItems.SILK_SLEEVE_LONG))
                    .addGroup("silk_pant_leg", new ItemStack(WeaversParadiseItems.SILK_PANT_LEG), getQualityVariants(WeaversParadiseItems.SILK_PANT_LEG))
                    .addGroup("wool_upperwear_base", new ItemStack(WeaversParadiseItems.WOOL_UPPERWEAR_BASE), getQualityVariants(WeaversParadiseItems.WOOL_UPPERWEAR_BASE))
                    .addGroup("wool_upperwear_base_buttons", new ItemStack(WeaversParadiseItems.WOOL_UPPERWEAR_BASE_BUTTONS), getQualityVariants(WeaversParadiseItems.WOOL_UPPERWEAR_BASE_BUTTONS))
                    .addGroup("wool_sleeve_short", new ItemStack(WeaversParadiseItems.WOOL_SLEEVE_SHORT), getQualityVariants(WeaversParadiseItems.WOOL_SLEEVE_SHORT))
                    .addGroup("wool_sleeve_long", new ItemStack(WeaversParadiseItems.WOOL_SLEEVE_LONG), getQualityVariants(WeaversParadiseItems.WOOL_SLEEVE_LONG))
                    .addGroup("wool_pant_leg", new ItemStack(WeaversParadiseItems.WOOL_PANT_LEG), getQualityVariants(WeaversParadiseItems.WOOL_PANT_LEG))
                    .addGroup("jeans_pant_leg", new ItemStack(WeaversParadiseItems.JEANS_PANT_LEG), getQualityVariants(WeaversParadiseItems.JEANS_PANT_LEG))
                    .title(Component.translatable("itemGroup.weaversparadise.quality_materials"))
                    .icon(() -> new ItemStack(WeaversParadiseItems.COTTON_CLOTH))
                    .displayItems((parameters, output) -> {
                    })
                    .build()
    );

    public static final CreativeModeTab DYES_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "dyes_tab"),
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 2)
                    .title(Component.translatable("itemGroup.weaversparadise.dye"))
                    .icon(() -> new ItemStack(WeaversParadiseItems.BOTTLED_DYE))
                    .displayItems((parameters, output) -> {
                        CreativeModeTab.Output safe = dedup(output);
                        List<ItemStack> dyedItems = List.of(
                                new ItemStack(WeaversParadiseItems.BOTTLED_DYE),
                                new ItemStack(WeaversParadiseItems.DYE_CORE)
                        );
                        for (ItemStack stack : dyedItems) {
                            for (String type : WeaversUtilities.dyeTypes) {
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
                                        safe.accept(item);
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
                                        safe.accept(item);
                                    }
                                } else if (type.equals("polychromatic")) {
                                    ItemStack item = stack.copy();
                                    CustomData.update(DataComponents.CUSTOM_DATA, item, (tag) -> {
                                        tag.putString("dyeType", type);
                                        tag.putInt("colorRedOne", 255);
                                        tag.putInt("colorGreenOne", 0);
                                        tag.putInt("colorBlueOne", 0);
                                    });
                                    safe.accept(item);
                                } else {
                                    ItemStack item = stack.copy();
                                    CustomData.update(DataComponents.CUSTOM_DATA, item, (tag) -> {
                                        tag.putString("dyeType", type);
                                        tag.putInt("colorRedOne", 255);
                                        tag.putInt("colorGreenOne", 255);
                                        tag.putInt("colorBlueOne", 255);
                                    });
                                    safe.accept(item);
                                }
                            }
                        }
                    })
                    .build()
    );

    public static final CreativeModeTab ITEMS_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "items_tab"),
            ExpandableCreativeTab.builder(CreativeModeTab.Row.TOP, 3)
                    .addGroup("contributor_plushies", PlushieItem.createPlushie(Optional.of("Vortianski"), Optional.empty()), CreativeModeTabProviders.getPlayerPlushies())
                    .addGroup("blahaj", new ItemStack(WeaversParadiseItems.BLAHAJ), getVariantItem(WeaversParadiseItems.BLAHAJ, WeaversUtilities.dyeTypes))
                    .addGroup("pigments", new ItemStack(WeaversParadiseItems.PURE_DYE), CreativeModeTabProviders.getPigments())
                    .addGroup("flags", new ItemStack(WeaversParadiseItems.FLAG_BASIC), CreativeModeTabProviders.getFlags())
                    .addGroup("stencils", new ItemStack(WeaversParadiseItems.BASIC_STENCIL), CreativeModeTabProviders.getStencils())
                    .title(Component.translatable("itemGroup.weaversparadise.items"))
                    .icon(() -> new ItemStack(WeaversParadiseItems.MORTAR_AND_PESTLE))
                    .displayItems((parameters, output) -> {
                        CreativeModeTab.Output safe = dedup(output);
                        List<Item> list = List.of(
                                WeaversParadiseItems.MORTAR_AND_PESTLE,
                                WeaversParadiseItems.PESTLE,
                                WeaversParadiseItems.NEEDLE,
                                WeaversParadiseItems.NEEDLE_WITH_THREAD,
                                WeaversParadiseItems.WEAVERS_SHEARS,
                                WeaversParadiseItems.LEATHER_STRIPS,
                                WeaversParadiseItems.EMPTY_SPOOL,
                                WeaversParadiseItems.COTTON_SPOOL,
                                WeaversParadiseItems.COTTON_SPOOL_BLOCK,
                                WeaversParadiseItems.COTTON_CLOTH_BLOCK,
                                WeaversParadiseItems.SILK_SPOOL,
                                WeaversParadiseItems.SILK_SPOOL_BLOCK,
                                WeaversParadiseItems.SILK_CLOTH_BLOCK,
                                WeaversParadiseItems.WOOL_SPOOL,
                                WeaversParadiseItems.WOOL_SPOOL_BLOCK,
                                WeaversParadiseItems.WOOL_CLOTH_BLOCK,
                                WeaversParadiseItems.JEANS_SPOOL,
                                WeaversParadiseItems.BUTTON,
                                WeaversParadiseItems.CHROMATIC_BLOOM,
                                WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT,
                                WeaversParadiseItems.CHROMATIC_DUST,
                                WeaversParadiseItems.WITCHROOT,
                                WeaversParadiseItems.WITCHROOT_FRUIT,
                                WeaversParadiseItems.WITCHROOT_FRUIT_COOKED,
                                WeaversParadiseItems.STARBLOOM,
                                WeaversParadiseItems.STARBLOOM_FRUIT,
                                WeaversParadiseItems.COTTON_SEEDS,
                                WeaversParadiseItems.KOZINAKI,
                                WeaversParadiseItems.COTTON_BOLL,
                                WeaversParadiseItems.RAW_COTTON,
                                WeaversParadiseItems.WILD_COTTON_PLANT,
                                WeaversParadiseItems.SPINNING_JENNY,
                                WeaversParadiseItems.CLOTHCRAFTING_STATION,
                                WeaversParadiseItems.DYEMAKING_BLOCK,
                                WeaversParadiseItems.DYEING_BARREL_BLOCK
                        );
                        for (Item entry : list) {
                            safe.accept(new ItemStack(entry));
                        }
                        safe.accept(WeaversParadiseItems.PLAYER_PLUSHIE_RENAME_TOKEN);
                        safe.accept(WeaversParadiseItems.ARMOR_LOOTBOX);
                    })
                    .build()
    );

    public static void register() {
    }

    /** Дедуп: ваниль 1.21.1 кидает IllegalStateException на равных стеках в одной вкладке. */
    private static CreativeModeTab.Output dedup(CreativeModeTab.Output inner) {
        List<ItemStack> added = new ArrayList<>();
        return new CreativeModeTab.Output() {
            @Override
            public void accept(ItemStack stack, CreativeModeTab.TabVisibility visibility) {
                for (ItemStack s : added) {
                    if (ItemStack.isSameItemSameComponents(s, stack)) {
                        return;
                    }
                }
                added.add(stack);
                inner.accept(stack, visibility);
            }
        };
    }

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
        if (item instanceof DoubleSidedBlockItem doubleSidedBlockItem) {
            items = getDoubleSidedPlushieVariants(doubleSidedBlockItem, variants);
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

    private static List<ItemStack> getDoubleSidedPlushieVariants(DoubleSidedBlockItem doubleSidedBlockItem, List<String> variants) {
        List<ItemStack> items = new ArrayList<>();
        for (String variant : variants) {
            ItemStack itemStack = new ItemStack(doubleSidedBlockItem);
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
