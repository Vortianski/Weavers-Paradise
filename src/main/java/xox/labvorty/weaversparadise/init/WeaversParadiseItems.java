package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.items.armor.*;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.DyeCoreItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.food.KozinakiItem;
import xox.labvorty.weaversparadise.items.instruments.MortarAndPestleItem;
import xox.labvorty.weaversparadise.items.instruments.NeedleItem;
import xox.labvorty.weaversparadise.items.instruments.NeedleWithThreadItem;
import xox.labvorty.weaversparadise.items.instruments.PestleItem;
import xox.labvorty.weaversparadise.items.materials.*;
import xox.labvorty.weaversparadise.items.misc.*;
import xox.labvorty.weaversparadise.items.stencil.*;

public class WeaversParadiseItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, WeaversParadiseMod.MOD_ID);

    //materials
    public static final RegistryObject<Item> COTTON_CLOTH = ITEMS.register("cotton_cloth", CottonClothItem::new);
    public static final RegistryObject<Item> SILK_CLOTH = ITEMS.register("silk_cloth", SilkClothItem::new);
    public static final RegistryObject<Item> WOOL_CLOTH = ITEMS.register("wool_cloth", WoolClothItem::new);
    public static final RegistryObject<Item> JEANS_CLOTH = ITEMS.register("jeans_cloth", JeansClothItem::new);

    //complex items
    public static final RegistryObject<Item> COTTON_UPPERWEAR_BASE = ITEMS.register("cotton_upperwear_base", CottonUpperwearBaseItem::new);
    public static final RegistryObject<Item> COTTON_UPPERWEAR_BASE_BUTTONS = ITEMS.register("cotton_upperwear_base_buttons", CottonUpperwearBaseButtonsItem::new);
    public static final RegistryObject<Item> COTTON_UPPERWEAR_BASE_ZIPPER = ITEMS.register("cotton_upperwear_base_zipper", CottonUpperwearBaseZipperItem::new);
    public static final RegistryObject<Item> COTTON_SLEEVE_SHORT = ITEMS.register("cotton_sleeve_short", CottonSleeveShortItem::new);
    public static final RegistryObject<Item> COTTON_SLEEVE_LONG = ITEMS.register("cotton_sleeve_long", CottonSleeveLongItem::new);
    public static final RegistryObject<Item> COTTON_PANT_LEG = ITEMS.register("cotton_pant_leg", CottonPantLegItem::new);

    public static final RegistryObject<Item> SILK_UPPERWEAR_BASE = ITEMS.register("silk_upperwear_base", SilkUpperwearBaseItem::new);
    public static final RegistryObject<Item> SILK_UPPERWEAR_BASE_BUTTONS = ITEMS.register("silk_upperwear_base_buttons", SilkUpperwearBaseButtonsItem::new);
    public static final RegistryObject<Item> SILK_UPPERWEAR_BASE_ZIPPER = ITEMS.register("silk_upperwear_base_zipper", SilkUpperwearBaseZipperItem::new);
    public static final RegistryObject<Item> SILK_SLEEVE_SHORT = ITEMS.register("silk_sleeve_short", SilkSleeveShortItem::new);
    public static final RegistryObject<Item> SILK_SLEEVE_LONG = ITEMS.register("silk_sleeve_long", SilkSleeveLongItem::new);
    public static final RegistryObject<Item> SILK_PANT_LEG = ITEMS.register("silk_pant_leg", SilkPantLegItem::new);

    public static final RegistryObject<Item> WOOL_UPPERWEAR_BASE = ITEMS.register("wool_upperwear_base", WoolUpperwearBaseItem::new);
    public static final RegistryObject<Item> WOOL_UPPERWEAR_BASE_BUTTONS = ITEMS.register("wool_upperwear_base_buttons", WoolUpperwearBaseButtonsItem::new);
    public static final RegistryObject<Item> WOOL_SLEEVE_SHORT = ITEMS.register("wool_sleeve_short", WoolSleeveShortItem::new);
    public static final RegistryObject<Item> WOOL_SLEEVE_LONG = ITEMS.register("wool_sleeve_long", WoolSleeveLongItem::new);
    public static final RegistryObject<Item> WOOL_PANT_LEG = ITEMS.register("wool_pant_leg", WoolPantLegItem::new);

    public static final RegistryObject<Item> JEANS_PANT_LEG = ITEMS.register("jeans_pant_leg", JeansPantLegItem::new);

    //clothing
    public static final RegistryObject<Item> THIGH_HIGHS_COTTON = ITEMS.register("thigh_highs_cotton", ThighHighsCottonItem::new);
    public static final RegistryObject<Item> THIGH_HIGHS_WOOL = ITEMS.register("thigh_highs_wool", ThighHighsWoolItem::new);
    public static final RegistryObject<Item> THIGH_HIGHS_SILK = ITEMS.register("thigh_highs_silk", ThighHighsSilkItem::new);

    public static final RegistryObject<Item> HAND_WARMERS_COTTON = ITEMS.register("hand_warmers_cotton", HandWarmersCottonItem::new);
    public static final RegistryObject<Item> HAND_WARMERS_WOOL = ITEMS.register("hand_warmers_wool", HandWarmersWoolItem::new);
    public static final RegistryObject<Item> HAND_WARMERS_SILK = ITEMS.register("hand_warmers_silk", HandWarmersSilkItem::new);

    public static final RegistryObject<Item> SHIRT_COTTON = ITEMS.register("shirt_cotton", ShirtCottonItem::new);
    public static final RegistryObject<Item> SHIRT_SILK = ITEMS.register("shirt_silk", ShirtSilkItem::new);

    public static final RegistryObject<Item> LONG_SLEEVE_COTTON = ITEMS.register("long_sleeve_cotton", LongSleeveCottonItem::new);
    public static final RegistryObject<Item> SWEATER_WOOL = ITEMS.register("sweater_wool", SweaterWoolItem::new);

    public static final RegistryObject<Item> PONCHO = ITEMS.register("poncho", PonchoItem::new);

    public static final RegistryObject<Item> COTTON_CROP_TOP_LONG_SLEEVED = ITEMS.register("cotton_crop_top_long_sleeved", CottonCropTopLongSleevedItem::new);

    public static final RegistryObject<Item> T_SHIRT = ITEMS.register("t_shirt", TShirtItem::new);
    public static final RegistryObject<Item> TANK_TOP = ITEMS.register("tank_top", TankTopItem::new);
    public static final RegistryObject<Item> WOOL_VEST = ITEMS.register("wool_vest", WoolVestItem::new);

    public static final RegistryObject<Item> PANTS_COTTON = ITEMS.register("pants_cotton", PantsCottonItem::new);
    public static final RegistryObject<Item> PANTS_SILK = ITEMS.register("pants_silk", PantsSilkItem::new);
    public static final RegistryObject<Item> PANTS_JEANS = ITEMS.register("pants_jeans", PantsJeansItem::new);
    public static final RegistryObject<Item> PANTS_WOOL = ITEMS.register("pants_wool", PantsWoolItem::new);

    public static final RegistryObject<Item> COTTON_SKIRT = ITEMS.register("skirt_cotton", SkirtCottonItem::new);

    public static final RegistryObject<Item> COTTON_CAPE = ITEMS.register("cotton_cape", CapeCottonItem::new);
    public static final RegistryObject<Item> SILK_CAPE = ITEMS.register("silk_cape", CapeSilkItem::new);
    public static final RegistryObject<Item> WOOL_CAPE = ITEMS.register("wool_cape", CapeWoolItem::new);

    public static final RegistryObject<Item> POMPON_HAT = ITEMS.register("pompon_hat", PomponHatItem::new);
    public static final RegistryObject<Item> CAP = ITEMS.register("cap", CapItem::new);
    public static final RegistryObject<Item> USHANKA = ITEMS.register("ushanka", UshankaItem::new);

    public static final RegistryObject<Item> CHOKER = ITEMS.register("choker", ChokerItem::new);
    public static final RegistryObject<Item> BELL = ITEMS.register("bell", BellItem::new);
    public static final RegistryObject<Item> HEART = ITEMS.register("heart", HeartItem::new);
    public static final RegistryObject<Item> RING = ITEMS.register("ring", RingItem::new);
    public static final RegistryObject<Item> CAT_RING = ITEMS.register("cat_ring", CatRingItem::new);
    public static final RegistryObject<Item> PLATE = ITEMS.register("plate", PlateItem::new);
    public static final RegistryObject<Item> FISH = ITEMS.register("fish", FishItem::new);

    public static final RegistryObject<Item> LEATHER_GLOVES = ITEMS.register("leather_gloves", LeatherGlovesItem::new);

    public static final RegistryObject<Item> ASTOLFO_COSMETICS = ITEMS.register("astolfo_cosmetics", AstolfoCosmeticItem::new);
    public static final RegistryObject<Item> BRIDGET_COSMETICS = ITEMS.register("bridget_cosmetics", BridgetCosmeticItem::new);
    public static final RegistryObject<Item> FELIX_COSMETICS = ITEMS.register("felix_cosmetics", FelixCosmeticItem::new);
    public static final RegistryObject<Item> GRIFFITH_COSMETICS = ITEMS.register("griffith_cosmetics", GriffithCosmeticItem::new);
    public static final RegistryObject<Item> NIKO_COSMETICS = ITEMS.register("niko_cosmetics", NikoCosmeticItem::new);
    public static final RegistryObject<Item> GABRIEL_COSMETICS = ITEMS.register("gabriel_cosmetics", GabrielCosmeticItem::new);
    public static final RegistryObject<Item> GISELLE_COSMETICS = ITEMS.register("giselle_cosmetics", GiselleCosmeticItem::new);
    public static final RegistryObject<Item> MIKKELA_COSMETICS = ITEMS.register("mikkela_cosmetics", MikkelaCosmeticItem::new);
    public static final RegistryObject<Item> EXPIE_COSMETICS = ITEMS.register("expie_cosmetics", ExpieCosmeticItem::new);
    public static final RegistryObject<Item> GASTER_COSMETICS = ITEMS.register("gaster_cosmetics", GasterCosmeticItem::new);
    public static final RegistryObject<Item> RALSEI_COSMETICS = ITEMS.register("ralsei_cosmetics", RalseiCosmeticItem::new);
    public static final RegistryObject<Item> JAYA_UTOMO_COSMETICS = ITEMS.register("jaya_utomo_cosmetics", JayaUtomoCosmeticItem::new);
    public static final RegistryObject<Item> MINOS_PRIME_COSMETICS = ITEMS.register("minos_prime_cosmetics", MinosPrimeCosmeticItem::new);

    //stencils
    public static final RegistryObject<Item> BASIC_STENCIL = ITEMS.register("base_stencil", StencilBaseItem::new);
    public static final RegistryObject<Item> HALF_STENCIL = ITEMS.register("half_stencil", StencilHalfItem::new);
    public static final RegistryObject<Item> CHECKERS_STENCIL = ITEMS.register("checkers_stencil", StencilCheckersItem::new);
    public static final RegistryObject<Item> CHECKERS_SMALL_STENCIL = ITEMS.register("checkers_small_stencil", StencilCheckersSmallItem::new);
    public static final RegistryObject<Item> LINES_VERTICAL_STENCIL = ITEMS.register("lines_vertical_stencil", StencilLinesVerticalItem::new);
    public static final RegistryObject<Item> LINES_SMALL_STENCIL = ITEMS.register("lines_small_stencil", StencilLinesSmallItem::new);
    public static final RegistryObject<Item> LINES_BIG_STENCIL = ITEMS.register("lines_big_stencil", StencilLinesBigItem::new);
    public static final RegistryObject<Item> CROSS_STENCIL = ITEMS.register("cross_stencil", StencilCrossItem::new);
    public static final RegistryObject<Item> PAWS_STENCIL = ITEMS.register("paws_stencil", StencilPawsItem::new);
    public static final RegistryObject<Item> STAR_STENCIL = ITEMS.register("star_stencil", StarStencilItem::new);
    public static final RegistryObject<Item> DIRT_STENCIL = ITEMS.register("dirt_stencil", DirtStencilItem::new);
    public static final RegistryObject<Item> FLOWER_STENCIL = ITEMS.register("flower_stencil", FlowerStencilItem::new);

    //flags
    public static final RegistryObject<Item> FLAG_BASIC = ITEMS.register("flag_base", () -> new FlagItem("default"));
    public static final RegistryObject<Item> FLAG_AGENDER = ITEMS.register("flag_agender", () -> new FlagItem("agender"));
    public static final RegistryObject<Item> FLAG_AROACE = ITEMS.register("flag_aroace", () -> new FlagItem("aroace"));
    public static final RegistryObject<Item> FLAG_AROMANTIC = ITEMS.register("flag_aromantic", () -> new FlagItem("aromantic"));
    public static final RegistryObject<Item> FLAG_ASEXUAL = ITEMS.register("flag_asexual", () -> new FlagItem("asexual"));
    public static final RegistryObject<Item> FLAG_BISEXUAL = ITEMS.register("flag_bisexual", () -> new FlagItem("bisexual"));
    public static final RegistryObject<Item> FLAG_DEMIBOY = ITEMS.register("flag_demiboy", () -> new FlagItem("demiboy"));
    public static final RegistryObject<Item> FLAG_DEMIGENDER = ITEMS.register("flag_demigender", () -> new FlagItem("demigender"));
    public static final RegistryObject<Item> FLAG_DEMIGIRL = ITEMS.register("flag_demigirl", () -> new FlagItem("demigirl"));
    public static final RegistryObject<Item> FLAG_GAY = ITEMS.register("flag_gay", () -> new FlagItem("gay"));
    public static final RegistryObject<Item> FLAG_GENDERFLUID = ITEMS.register("flag_genderfluid", () -> new FlagItem("genderfluid"));
    public static final RegistryObject<Item> FLAG_GENDERQUEER = ITEMS.register("flag_genderqueer", () -> new FlagItem("genderqueer"));
    public static final RegistryObject<Item> FLAG_INTERSEX = ITEMS.register("flag_intersex", () -> new FlagItem("intersex"));
    public static final RegistryObject<Item> FLAG_LESBIAN = ITEMS.register("flag_lesbian", () -> new FlagItem("lesbian"));
    public static final RegistryObject<Item> FLAG_NONBINARY = ITEMS.register("flag_nonbinary", () -> new FlagItem("nonbinary"));
    public static final RegistryObject<Item> FLAG_PANSEXUAL = ITEMS.register("flag_pansexual", () -> new FlagItem("pansexual"));
    public static final RegistryObject<Item> FLAG_PRIDE = ITEMS.register("flag_pride", () -> new FlagItem("pride"));
    public static final RegistryObject<Item> FLAG_TRANS = ITEMS.register("flag_trans", () -> new FlagItem("trans"));
    public static final RegistryObject<Item> FLAG_TRANSFEMININE = ITEMS.register("flag_transfeminine", () -> new FlagItem("transfeminine"));
    public static final RegistryObject<Item> FLAG_TRANSMASCULINE = ITEMS.register("flag_transmasculine", () -> new FlagItem("transmasculine"));

    //misc
    public static final RegistryObject<Item> BOTTLED_DYE = ITEMS.register("bottled_dye", BottledDyeItem::new);
    public static final RegistryObject<Item> DYE_CORE = ITEMS.register("dye_core", DyeCoreItem::new);
    public static final RegistryObject<Item> EMPTY_SPOOL = ITEMS.register("empty_spool", EmptySpoolItem::new);
    public static final RegistryObject<Item> COTTON_SPOOL = ITEMS.register("cotton_spool", CottonSpoolItem::new);
    public static final RegistryObject<Item> WOOL_SPOOL = ITEMS.register("wool_spool", WoolSpooltem::new);
    public static final RegistryObject<Item> SILK_SPOOL = ITEMS.register("silk_spool", SilkSpoolItem::new);
    public static final RegistryObject<Item> JEANS_SPOOL = ITEMS.register("jeans_spool", JeansSpoolItem::new);
    public static final RegistryObject<Item> RAW_COTTON = ITEMS.register("raw_cotton", RawCottonItem::new);
    public static final RegistryObject<Item> PIGMENT = ITEMS.register("pigment", PigmentItem::new);
    public static final RegistryObject<Item> PLAYER_PLUSHIE = ITEMS.register("player_plushie", () -> new PlushieItem(WeaversParadiseBlocks.PLUSHIE.get()));
    public static final RegistryObject<Item> MORTAR_AND_PESTLE = ITEMS.register("mortar_and_pestle", MortarAndPestleItem::new);
    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle", PestleItem::new);
    public static final RegistryObject<Item> NEEDLE = ITEMS.register("needle", NeedleItem::new);
    public static final RegistryObject<Item> NEEDLE_WITH_THREAD = ITEMS.register("needle_with_thread", NeedleWithThreadItem::new);
    public static final RegistryObject<Item> PLAYER_PLUSHIE_RENAME_TOKEN = ITEMS.register("player_plushie_rename_token", PlayerPlushieRenameTokenItem::new);
    public static final RegistryObject<Item> SPINNING_JENNY = ITEMS.register(WeaversParadiseBlocks.SPINNING_JENNY_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SPINNING_JENNY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CLOTHCRAFTING_STATION = ITEMS.register(WeaversParadiseBlocks.CLOTHCRAFTING_STATION_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.CLOTHCRAFTING_STATION_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DYEMAKING_BLOCK = ITEMS.register(WeaversParadiseBlocks.DYEMAKING_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.DYEMAKING_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DYEING_BARREL_BLOCK = ITEMS.register(WeaversParadiseBlocks.DYEING_BARREL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.DYEING_BARREL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds", () -> new ItemNameBlockItem(WeaversParadiseBlocks.COTTON_BUSH.get(), new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> WILD_COTTON_PLANT = ITEMS.register(WeaversParadiseBlocks.WILD_COTTON_PLANT.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WILD_COTTON_PLANT.get(), new Item.Properties()));
    public static final RegistryObject<Item> KOZINAKI = ITEMS.register("kozinaki", KozinakiItem::new);
    public static final RegistryObject<Item> COTTON_BOLL = ITEMS.register("cotton_boll", CottonBollItem::new);
    public static final RegistryObject<Item> CHROMATIC_BLOOM_FRUIT = ITEMS.register("chromatic_bloom_fruit", ChromaticBloomFruitItem::new);
    public static final RegistryObject<Item> CHROMATIC_DUST = ITEMS.register("chromatic_dust", ChromaticDustItem::new);
    public static final RegistryObject<Item> ARMOR_LOOTBOX = ITEMS.register("armor_lootbox", ArmorLootboxItem::new);
    public static final RegistryObject<Item> BUTTON = ITEMS.register("button", ButtonItem::new);
    public static final RegistryObject<Item> LEATHER_STRIPS = ITEMS.register("leather_strips", LeatherStripsItem::new);
    public static final RegistryObject<Item> CHROMATIC_BLOOM = ITEMS.register(WeaversParadiseBlocks.CHROMATIC_BLOOM.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.CHROMATIC_BLOOM.get(), new Item.Properties()));
    public static final RegistryObject<Item> COTTON_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.COTTON_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.COTTON_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> COTTON_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.COTTON_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.COTTON_CLOTH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILK_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.SILK_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SILK_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILK_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.SILK_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SILK_CLOTH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOOL_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.WOOL_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WOOL_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOOL_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.WOOL_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WOOL_CLOTH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> WITCHROOT_FRUIT = ITEMS.register("witchroot_fruit", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64)));
    public static final RegistryObject<Item> WITCHROOT_FRUIT_COOKED = ITEMS.register("witchroot_fruit_cooked", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64)));
    public static final RegistryObject<Item> STARBLOOM_FRUIT = ITEMS.register("starbloom_fruit", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64)));
    public static final RegistryObject<DoubleSidedBlockItem> BLAHAJ = ITEMS.register(WeaversParadiseBlocks.BLAHAJ.getId().getPath(), () -> new BlahajItem(WeaversParadiseBlocks.BLAHAJ.get()));
    public static final RegistryObject<Item> WITCHROOT = ITEMS.register(WeaversParadiseBlocks.WITCHROOT.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WITCHROOT.get(), new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> STARBLOOM = ITEMS.register(WeaversParadiseBlocks.STARBLOOM.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.STARBLOOM.get(), new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> WEAVERS_SHEARS = ITEMS.register("weavers_shears", WeaversShearsItem::new);
}