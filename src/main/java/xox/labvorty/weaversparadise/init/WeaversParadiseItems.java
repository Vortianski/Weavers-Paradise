package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.items.armor.ArmorLootboxItem;
import xox.labvorty.weaversparadise.items.armor.AstolfoArmorItem;
import xox.labvorty.weaversparadise.items.armor.BridgetClothingArmorItem;
import xox.labvorty.weaversparadise.items.armor.FelixClothingArmorItem;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.DyeCoreItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.flags.*;
import xox.labvorty.weaversparadise.items.food.KozinakiItem;
import xox.labvorty.weaversparadise.items.instruments.MortarAndPestleItem;
import xox.labvorty.weaversparadise.items.instruments.NeedleItem;
import xox.labvorty.weaversparadise.items.instruments.NeedleWithThreadItem;
import xox.labvorty.weaversparadise.items.instruments.PestleItem;
import xox.labvorty.weaversparadise.items.materials.*;
import xox.labvorty.weaversparadise.items.misc.PlayerPlushieRenameTokenItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;
import xox.labvorty.weaversparadise.items.stencil.*;

public class WeaversParadiseItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, WeaversParadiseMod.MOD_ID);

    //materials
    public static final RegistryObject<CottonClothItem> COTTON_CLOTH = ITEMS.register("cotton_cloth", CottonClothItem::new);
    public static final RegistryObject<SilkClothItem> SILK_CLOTH = ITEMS.register("silk_cloth", SilkClothItem::new);
    public static final RegistryObject<WoolClothItem> WOOL_CLOTH = ITEMS.register("wool_cloth", WoolClothItem::new);
    public static final RegistryObject<JeansClothItem> JEANS_CLOTH = ITEMS.register("jeans_cloth", JeansClothItem::new);

    //complex items
    public static final RegistryObject<CottonUpperwearBaseItem> COTTON_UPPERWEAR_BASE = ITEMS.register("cotton_upperwear_base", CottonUpperwearBaseItem::new);
    public static final RegistryObject<CottonUpperwearBaseButtonsItem> COTTON_UPPERWEAR_BASE_BUTTONS = ITEMS.register("cotton_upperwear_base_buttons", CottonUpperwearBaseButtonsItem::new);
    public static final RegistryObject<CottonUpperwearBaseZipperItem> COTTON_UPPERWEAR_BASE_ZIPPER = ITEMS.register("cotton_upperwear_base_zipper", CottonUpperwearBaseZipperItem::new);
    public static final RegistryObject<CottonSleeveShortItem> COTTON_SLEEVE_SHORT = ITEMS.register("cotton_sleeve_short", CottonSleeveShortItem::new);
    public static final RegistryObject<CottonSleeveLongItem> COTTON_SLEEVE_LONG = ITEMS.register("cotton_sleeve_long", CottonSleeveLongItem::new);
    public static final RegistryObject<CottonPantLegItem> COTTON_PANT_LEG = ITEMS.register("cotton_pant_leg", CottonPantLegItem::new);

    public static final RegistryObject<SilkUpperwearBaseItem> SILK_UPPERWEAR_BASE = ITEMS.register("silk_upperwear_base", SilkUpperwearBaseItem::new);
    public static final RegistryObject<SilkUpperwearBaseButtonsItem> SILK_UPPERWEAR_BASE_BUTTONS = ITEMS.register("silk_upperwear_base_buttons", SilkUpperwearBaseButtonsItem::new);
    public static final RegistryObject<SilkUpperwearBaseZipperItem> SILK_UPPERWEAR_BASE_ZIPPER = ITEMS.register("silk_upperwear_base_zipper", SilkUpperwearBaseZipperItem::new);
    public static final RegistryObject<SilkSleeveShortItem> SILK_SLEEVE_SHORT = ITEMS.register("silk_sleeve_short", SilkSleeveShortItem::new);
    public static final RegistryObject<SilkSleeveLongItem> SILK_SLEEVE_LONG = ITEMS.register("silk_sleeve_long", SilkSleeveLongItem::new);
    public static final RegistryObject<SilkPantLegItem> SILK_PANT_LEG = ITEMS.register("silk_pant_leg", SilkPantLegItem::new);

    public static final RegistryObject<WoolUpperwearBaseItem> WOOL_UPPERWEAR_BASE = ITEMS.register("wool_upperwear_base", WoolUpperwearBaseItem::new);
    public static final RegistryObject<WoolUpperwearBaseButtonsItem> WOOL_UPPERWEAR_BASE_BUTTONS = ITEMS.register("wool_upperwear_base_buttons", WoolUpperwearBaseButtonsItem::new);
    public static final RegistryObject<WoolSleeveShortItem> WOOL_SLEEVE_SHORT = ITEMS.register("wool_sleeve_short", WoolSleeveShortItem::new);
    public static final RegistryObject<WoolSleeveLongItem> WOOL_SLEEVE_LONG = ITEMS.register("wool_sleeve_long", WoolSleeveLongItem::new);

    public static final RegistryObject<JeansPantLegItem> JEANS_PANT_LEG = ITEMS.register("jeans_pant_leg", JeansPantLegItem::new);

    //clothing
    public static final RegistryObject<ThighHighsCottonItem> THIGH_HIGHS_COTTON = ITEMS.register("thigh_highs_cotton", ThighHighsCottonItem::new);
    public static final RegistryObject<ThighHighsWoolItem> THIGH_HIGHS_WOOL = ITEMS.register("thigh_highs_wool", ThighHighsWoolItem::new);
    public static final RegistryObject<ThighHighsSilkItem> THIGH_HIGHS_SILK = ITEMS.register("thigh_highs_silk", ThighHighsSilkItem::new);

    public static final RegistryObject<HandWarmersCottonItem> HAND_WARMERS_COTTON = ITEMS.register("hand_warmers_cotton", HandWarmersCottonItem::new);
    public static final RegistryObject<HandWarmersWoolItem> HAND_WARMERS_WOOL = ITEMS.register("hand_warmers_wool", HandWarmersWoolItem::new);
    public static final RegistryObject<HandWarmersSilkItem> HAND_WARMERS_SILK = ITEMS.register("hand_warmers_silk", HandWarmersSilkItem::new);

    public static final RegistryObject<ShirtCottonItem> SHIRT_COTTON = ITEMS.register("shirt_cotton", ShirtCottonItem::new);
    public static final RegistryObject<ShirtSilkItem> SHIRT_SILK = ITEMS.register("shirt_silk", ShirtSilkItem::new);
    public static final RegistryObject<SweaterWoolItem> SWEATER_WOOL = ITEMS.register("sweater_wool", SweaterWoolItem::new);

    public static final RegistryObject<PantsCottonItem> PANTS_COTTON = ITEMS.register("pants_cotton", PantsCottonItem::new);
    public static final RegistryObject<PantsSilkItem> PANTS_SILK = ITEMS.register("pants_silk", PantsSilkItem::new);
    public static final RegistryObject<PantsJeansItem> PANTS_JEANS = ITEMS.register("pants_jeans", PantsJeansItem::new);

    public static final RegistryObject<CapeCottonItem> COTTON_CAPE = ITEMS.register("cotton_cape", CapeCottonItem::new);
    public static final RegistryObject<CapeSilkItem> SILK_CAPE = ITEMS.register("silk_cape", CapeSilkItem::new);
    public static final RegistryObject<CapeWoolItem> WOOL_CAPE = ITEMS.register("wool_cape", CapeWoolItem::new);

    public static final RegistryObject<ChokerItem> CHOKER = ITEMS.register("choker", ChokerItem::new);
    public static final RegistryObject<BellItem> BELL = ITEMS.register("bell", BellItem::new);
    public static final RegistryObject<HeartItem> HEART = ITEMS.register("heart", HeartItem::new);
    public static final RegistryObject<RingItem> RING = ITEMS.register("ring", RingItem::new);
    public static final RegistryObject<CatRingItem> CAT_RING = ITEMS.register("cat_ring", CatRingItem::new);
    public static final RegistryObject<PlateItem> PLATE = ITEMS.register("plate", PlateItem::new);

    public static final RegistryObject<LeatherGlovesItem> LEATHER_GLOVES = ITEMS.register("leather_gloves", LeatherGlovesItem::new);

    public static final RegistryObject<AstolfoArmorItem.Helmet> ASTOLFO_ARMOR_WIG = ITEMS.register("astolfo_armor_wig", AstolfoArmorItem.Helmet::new);
    public static final RegistryObject<AstolfoArmorItem.Chestplate> ASTOLFO_ARMOR_CHESTPLATE = ITEMS.register("astolfo_armor_chestplate", AstolfoArmorItem.Chestplate::new);
    public static final RegistryObject<AstolfoArmorItem.Leggings> ASTOLFO_ARMOR_LEGGINGS = ITEMS.register("astolfo_armor_skirt", AstolfoArmorItem.Leggings::new);
    public static final RegistryObject<AstolfoArmorItem.Boots> ASTOLFO_ARMOR_BOOTS = ITEMS.register("astolfo_armor_boots", AstolfoArmorItem.Boots::new);

    public static final RegistryObject<BridgetClothingArmorItem.Helmet> BRIDGET_ARMOR_HAT = ITEMS.register("bridget_armor_hat", BridgetClothingArmorItem.Helmet::new);
    public static final RegistryObject<BridgetClothingArmorItem.Chestplate> BRIDGET_ARMOR_JACKET = ITEMS.register("bridget_armor_jacket", BridgetClothingArmorItem.Chestplate::new);
    public static final RegistryObject<BridgetClothingArmorItem.Leggings> BRIDGET_ARMOR_SKIRT = ITEMS.register("bridget_armor_skirt", BridgetClothingArmorItem.Leggings::new);
    public static final RegistryObject<BridgetClothingArmorItem.Boots> BRIDGET_ARMOR_BOOTS = ITEMS.register("bridget_armor_boots", BridgetClothingArmorItem.Boots::new);

    public static final RegistryObject<FelixClothingArmorItem.Helmet> FELIX_ARMOR_HAT = ITEMS.register("felix_armor_hat", FelixClothingArmorItem.Helmet::new);
    public static final RegistryObject<FelixClothingArmorItem.Chestplate> FELIX_ARMOR_JACKET = ITEMS.register("felix_armor_jacket", FelixClothingArmorItem.Chestplate::new);
    public static final RegistryObject<FelixClothingArmorItem.Leggings> FELIX_ARMOR_SKIRT = ITEMS.register("felix_armor_skirt", FelixClothingArmorItem.Leggings::new);
    public static final RegistryObject<FelixClothingArmorItem.Boots> FELIX_ARMOR_BOOTS = ITEMS.register("felix_armor_boots", FelixClothingArmorItem.Boots::new);

    //stencils
    public static final RegistryObject<StencilBaseItem> BASIC_STENCIL = ITEMS.register("base_stencil", StencilBaseItem::new);
    public static final RegistryObject<StencilHalfItem> HALF_STENCIL = ITEMS.register("half_stencil", StencilHalfItem::new);
    public static final RegistryObject<StencilCheckersItem> CHECKERS_STENCIL = ITEMS.register("checkers_stencil", StencilCheckersItem::new);
    public static final RegistryObject<StencilCheckersSmallItem> CHECKERS_SMALL_STENCIL = ITEMS.register("checkers_small_stencil", StencilCheckersSmallItem::new);
    public static final RegistryObject<StencilLinesVerticalItem> LINES_VERTICAL_STENCIL = ITEMS.register("lines_vertical_stencil", StencilLinesVerticalItem::new);
    public static final RegistryObject<StencilLinesSmallItem> LINES_SMALL_STENCIL = ITEMS.register("lines_small_stencil", StencilLinesSmallItem::new);
    public static final RegistryObject<StencilLinesBigItem> LINES_BIG_STENCIL = ITEMS.register("lines_big_stencil", StencilLinesBigItem::new);
    public static final RegistryObject<StencilCrossItem> CROSS_STENCIL = ITEMS.register("cross_stencil", StencilCrossItem::new);
    public static final RegistryObject<StencilPawsItem> PAWS_STENCIL = ITEMS.register("paws_stencil", StencilPawsItem::new);
    public static final RegistryObject<StarStencilItem> STAR_STENCIL = ITEMS.register("star_stencil", StarStencilItem::new);
    public static final RegistryObject<DirtStencilItem> DIRT_STENCIL = ITEMS.register("dirt_stencil", DirtStencilItem::new);
    public static final RegistryObject<FlowerStencilItem> FLOWER_STENCIL = ITEMS.register("flower_stencil", FlowerStencilItem::new);

    //flags
    public static final RegistryObject<EmptyFlagItem> FLAG_BASIC = ITEMS.register("flag_base", EmptyFlagItem::new);
    public static final RegistryObject<AgenderFlagItem> FLAG_AGENDER = ITEMS.register("flag_agender", AgenderFlagItem::new);
    public static final RegistryObject<AroaceFlagItem> FLAG_AROACE = ITEMS.register("flag_aroace", AroaceFlagItem::new);
    public static final RegistryObject<AromanticFlagItem> FLAG_AROMANTIC = ITEMS.register("flag_aromantic", AromanticFlagItem::new);
    public static final RegistryObject<AsexualFlagItem> FLAG_ASEXUAL = ITEMS.register("flag_asexual", AsexualFlagItem::new);
    public static final RegistryObject<BisexualFlagItem> FLAG_BISEXUAL = ITEMS.register("flag_bisexual", BisexualFlagItem::new);
    public static final RegistryObject<DemiboyFlagItem> FLAG_DEMIBOY = ITEMS.register("flag_demiboy", DemiboyFlagItem::new);
    public static final RegistryObject<DemigenderFlagItem> FLAG_DEMIGENDER = ITEMS.register("flag_demigender", DemigenderFlagItem::new);
    public static final RegistryObject<DemigirlFlagItem> FLAG_DEMIGIRL = ITEMS.register("flag_demigirl", DemigirlFlagItem::new);
    public static final RegistryObject<GayFlagItem> FLAG_GAY = ITEMS.register("flag_gay", GayFlagItem::new);
    public static final RegistryObject<GenderfluidFlagItem> FLAG_GENDERFLUID = ITEMS.register("flag_genderfluid", GenderfluidFlagItem::new);
    public static final RegistryObject<GenderqueerFlagItem> FLAG_GENDERQUEER = ITEMS.register("flag_genderqueer", GenderqueerFlagItem::new);
    public static final RegistryObject<IntersexFlagItem> FLAG_INTERSEX = ITEMS.register("flag_intersex", IntersexFlagItem::new);
    public static final RegistryObject<LesbianFlagItem> FLAG_LESBIAN = ITEMS.register("flag_lesbian", LesbianFlagItem::new);
    public static final RegistryObject<NonbinaryFlagItem> FLAG_NONBINARY = ITEMS.register("flag_nonbinary", NonbinaryFlagItem::new);
    public static final RegistryObject<PansexualFlagItem> FLAG_PANSEXUAL = ITEMS.register("flag_pansexual", PansexualFlagItem::new);
    public static final RegistryObject<PrideFlagItem> FLAG_PRIDE = ITEMS.register("flag_pride", PrideFlagItem::new);
    public static final RegistryObject<TransFlagItem> FLAG_TRANS = ITEMS.register("flag_trans", TransFlagItem::new);

    //misc
    public static final RegistryObject<BottledDyeItem> BOTTLED_DYE = ITEMS.register("bottled_dye", BottledDyeItem::new);
    public static final RegistryObject<DyeCoreItem> DYE_CORE = ITEMS.register("dye_core", DyeCoreItem::new);
    public static final RegistryObject<EmptySpoolItem> EMPTY_SPOOL = ITEMS.register("empty_spool", EmptySpoolItem::new);
    public static final RegistryObject<CottonSpoolItem> COTTON_SPOOL = ITEMS.register("cotton_spool", CottonSpoolItem::new);
    public static final RegistryObject<WoolSpooltem> WOOL_SPOOL = ITEMS.register("wool_spool", WoolSpooltem::new);
    public static final RegistryObject<SilkSpoolItem> SILK_SPOOL = ITEMS.register("silk_spool", SilkSpoolItem::new);
    public static final RegistryObject<JeansSpoolItem> JEANS_SPOOL = ITEMS.register("jeans_spool", JeansSpoolItem::new);
    public static final RegistryObject<RawCottonItem> RAW_COTTON = ITEMS.register("raw_cotton", RawCottonItem::new);
    public static final RegistryObject<PigmentItem> PIGMENT = ITEMS.register("pigment", PigmentItem::new);
    public static final RegistryObject<PlushieItem> PLAYER_PLUSHIE = ITEMS.register("player_plushie", PlushieItem::new);
    public static final RegistryObject<MortarAndPestleItem> MORTAR_AND_PESTLE = ITEMS.register("mortar_and_pestle", MortarAndPestleItem::new);
    public static final RegistryObject<PestleItem> PESTLE = ITEMS.register("pestle", PestleItem::new);
    public static final RegistryObject<NeedleItem> NEEDLE = ITEMS.register("needle", NeedleItem::new);
    public static final RegistryObject<NeedleWithThreadItem> NEEDLE_WITH_THREAD = ITEMS.register("needle_with_thread", NeedleWithThreadItem::new);
    public static final RegistryObject<PlayerPlushieRenameTokenItem> PLAYER_PLUSHIE_RENAME_TOKEN = ITEMS.register("player_plushie_rename_token", PlayerPlushieRenameTokenItem::new);
    public static final RegistryObject<BlockItem> SPINNING_JENNY = ITEMS.register(WeaversParadiseBlocks.SPINNING_JENNY_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SPINNING_JENNY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CLOTHCRAFTING_STATION = ITEMS.register(WeaversParadiseBlocks.CLOTHCRAFTING_STATION_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.CLOTHCRAFTING_STATION_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DYEMAKING_BLOCK = ITEMS.register(WeaversParadiseBlocks.DYEMAKING_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.DYEMAKING_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DYEING_BARREL_BLOCK = ITEMS.register(WeaversParadiseBlocks.DYEING_BARREL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.DYEING_BARREL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds", () -> new ItemNameBlockItem(WeaversParadiseBlocks.COTTON_BUSH.get(), new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> WILD_COTTON_PLANT = ITEMS.register(WeaversParadiseBlocks.WILD_COTTON_PLANT.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WILD_COTTON_PLANT.get(), new Item.Properties()));
    public static final RegistryObject<Item> KOZINAKI = ITEMS.register("kozinaki", KozinakiItem::new);
    public static final RegistryObject<CottonBollItem> COTTON_BOLL = ITEMS.register("cotton_boll", CottonBollItem::new);
    public static final RegistryObject<ChromaticBloomFruitItem> CHROMATIC_BLOOM_FRUIT = ITEMS.register("chromatic_bloom_fruit", ChromaticBloomFruitItem::new);
    public static final RegistryObject<ChromaticDustItem> CHROMATIC_DUST = ITEMS.register("chromatic_dust", ChromaticDustItem::new);
    public static final RegistryObject<ArmorLootboxItem> ARMOR_LOOTBOX = ITEMS.register("armor_lootbox", ArmorLootboxItem::new);
    public static final RegistryObject<ButtonItem> BUTTON = ITEMS.register("button", ButtonItem::new);
    public static final RegistryObject<LeatherStripsItem> LEATHER_STRIPS = ITEMS.register("leather_strips", LeatherStripsItem::new);
    public static final RegistryObject<Item> CHROMATIC_BLOOM = ITEMS.register(WeaversParadiseBlocks.CHROMATIC_BLOOM.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.CHROMATIC_BLOOM.get(), new Item.Properties()));
    public static final RegistryObject<Item> COTTON_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.COTTON_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.COTTON_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> COTTON_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.COTTON_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.COTTON_CLOTH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILK_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.SILK_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SILK_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILK_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.SILK_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SILK_CLOTH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOOL_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.WOOL_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WOOL_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOOL_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.WOOL_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WOOL_CLOTH_BLOCK.get(), new Item.Properties()));
}