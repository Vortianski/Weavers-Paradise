package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.items.armor.*;
import xox.labvorty.weaversparadise.items.clothing.*;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedBlockItem;
import xox.labvorty.weaversparadise.items.dye.BottledDyeItem;
import xox.labvorty.weaversparadise.items.dye.DyeCoreItem;
import xox.labvorty.weaversparadise.items.dye.PigmentItem;
import xox.labvorty.weaversparadise.items.flags.*;
import xox.labvorty.weaversparadise.items.food.KozinakiItem;
import xox.labvorty.weaversparadise.items.instruments.*;
import xox.labvorty.weaversparadise.items.materials.*;
import xox.labvorty.weaversparadise.items.misc.BlahajItem;
import xox.labvorty.weaversparadise.items.misc.PlayerPlushieRenameTokenItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;
import xox.labvorty.weaversparadise.items.stencil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class WeaversParadiseItems {
    private static final List<Item> ITEM_LIST = new ArrayList<>();

    private static <T extends Item> T registerItem(String name, Supplier<T> sup) {
        T item = sup.get();
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, name), item);
        ITEM_LIST.add(item);
        return item;
    }

    //clothing items
    public static final Item THIGH_HIGHS_COTTON = registerItem("thigh_highs_cotton", ThighHighsCottonItem::new);
    public static final Item THIGH_HIGHS_WOOL = registerItem("thigh_highs_wool", ThighHighsWoolItem::new);
    public static final Item THIGH_HIGHS_SILK = registerItem("thigh_highs_silk", ThighHighsSilkItem::new);

    public static final Item HAND_WARMERS_COTTON = registerItem("hand_warmers_cotton", HandWarmersCottonItem::new);
    public static final Item HAND_WARMERS_SILK = registerItem("hand_warmers_silk", HandWarmersSilkItem::new);
    public static final Item HAND_WARMERS_WOOL = registerItem("hand_warmers_wool", HandWarmersWoolItem::new);

    public static final Item SHIRT_COTTON = registerItem("shirt_cotton", ShirtCottonItem::new);
    public static final Item SHIRT_SILK = registerItem("shirt_silk", ShirtSilkItem::new);

    public static final Item LONG_SLEEVE_COTTON = registerItem("long_sleeve_cotton", LongSleeveCottonItem::new);
    public static final Item SWEATER_WOOL = registerItem("sweater_wool", SweaterWoolItem::new);

    public static final Item PONCHO = registerItem("poncho", PonchoItem::new);

    public static final Item COTTON_CROP_TOP_LONG_SLEEVED = registerItem("cotton_crop_top_long_sleeved", CottonCropTopLongSleevedItem::new);

    public static final Item T_SHIRT = registerItem("t_shirt", TShirtItem::new);
    public static final Item TANK_TOP = registerItem("tank_top", TankTopItem::new);
    public static final Item WOOL_VEST = registerItem("wool_vest", WoolVestItem::new);

    public static final Item PANTS_JEANS = registerItem("pants_jeans", PantsJeansItem::new);
    public static final Item PANTS_COTTON = registerItem("pants_cotton", PantsCottonItem::new);
    public static final Item PANTS_SILK = registerItem("pants_silk", PantsSilkItem::new);
    public static final Item PANTS_WOOL = registerItem("pants_wool", PantsWoolItem::new);

    public static final Item COTTON_SKIRT = registerItem("skirt_cotton", SkirtCottonItem::new);

    public static final Item POMPON_HAT = registerItem("pompon_hat", PomponHatItem::new);
    public static final Item CAP = registerItem("cap", CapItem::new);
    public static final Item USHANKA = registerItem("ushanka", UshankaItem::new);

    public static final Item LEATHER_GLOVES = registerItem("leather_gloves", LeatherGlovesItem::new);

    public static final Item CHOKER = registerItem("choker", ChokerItem::new);
    public static final Item BELL = registerItem("bell", BellItem::new);
    public static final Item HEART = registerItem("heart", HeartItem::new);
    public static final Item RING = registerItem("ring", RingItem::new);
    public static final Item CAT_RING = registerItem("cat_ring", CatRingItem::new);
    public static final Item PLATE = registerItem("plate", PlateItem::new);
    public static final Item FISH = registerItem("fish", FishItem::new);

    public static final Item COTTON_CAPE = registerItem("cotton_cape", CapeCottonItem::new);
    public static final Item SILK_CAPE = registerItem("silk_cape", CapeSilkItem::new);
    public static final Item WOOL_CAPE = registerItem("wool_cape", CapeWoolItem::new);

    public static final Item ASTOLFO_COSMETICS = registerItem("astolfo_cosmetics", AstolfoCosmeticItem::new);
    public static final Item BRIDGET_COSMETICS = registerItem("bridget_cosmetics", BridgetCosmeticItem::new);
    public static final Item FELIX_COSMETICS = registerItem("felix_cosmetics", FelixCosmeticItem::new);
    public static final Item GRIFFITH_COSMETICS = registerItem("griffith_cosmetics", GriffithCosmeticItem::new);
    public static final Item NIKO_COSMETICS = registerItem("niko_cosmetics", NikoCosmeticItem::new);
    public static final Item GABRIEL_COSMETICS = registerItem("gabriel_cosmetics", GabrielCosmeticItem::new);
    public static final Item GISELLE_COSMETICS = registerItem("giselle_cosmetics", GiselleCosmeticItem::new);
    public static final Item MIKKELA_COSMETICS = registerItem("mikkela_cosmetics", MikkelaCosmeticItem::new);
    public static final Item EXPIE_COSMETICS = registerItem("expie_cosmetics", ExpieCosmeticItem::new);
    public static final Item GASTER_COSMETICS = registerItem("gaster_cosmetics", GasterCosmeticItem::new);
    public static final Item RALSEI_COSMETICS = registerItem("ralsei_cosmetics", RalseiCosmeticItem::new);
    public static final Item JAYA_UTOMO_COSMETICS = registerItem("jaya_utomo_cosmetics", JayaUtomoCosmeticItem::new);
    public static final Item MINOS_PRIME_COSMETICS = registerItem("minos_prime_cosmetics", MinosPrimeCosmeticItem::new);

    //complex items
    public static final Item COTTON_UPPERWEAR_BASE = registerItem("cotton_upperwear_base", CottonUpperwearBaseItem::new);
    public static final Item COTTON_UPPERWEAR_BASE_BUTTONS = registerItem("cotton_upperwear_base_buttons", CottonUpperwearBaseButtonsItem::new);
    public static final Item COTTON_UPPERWEAR_BASE_ZIPPER = registerItem("cotton_upperwear_base_zipper", CottonUpperwearBaseZipperItem::new);
    public static final Item COTTON_SLEEVE_SHORT = registerItem("cotton_sleeve_short", CottonSleeveShortItem::new);
    public static final Item COTTON_SLEEVE_LONG = registerItem("cotton_sleeve_long", CottonSleeveLongItem::new);
    public static final Item COTTON_PANT_LEG = registerItem("cotton_pant_leg", CottonPantLegItem::new);

    public static final Item SILK_UPPERWEAR_BASE = registerItem("silk_upperwear_base", SilkUpperwearBaseItem::new);
    public static final Item SILK_UPPERWEAR_BASE_BUTTONS = registerItem("silk_upperwear_base_buttons", SilkUpperwearBaseButtonsItem::new);
    public static final Item SILK_UPPERWEAR_BASE_ZIPPER = registerItem("silk_upperwear_base_zipper", SilkUpperwearBaseZipperItem::new);
    public static final Item SILK_SLEEVE_SHORT = registerItem("silk_sleeve_short", SilkSleeveShortItem::new);
    public static final Item SILK_SLEEVE_LONG = registerItem("silk_sleeve_long", SilkSleeveLongItem::new);
    public static final Item SILK_PANT_LEG = registerItem("silk_pant_leg", SilkPantLegItem::new);

    public static final Item WOOL_UPPERWEAR_BASE = registerItem("wool_upperwear_base", WoolUpperwearBaseItem::new);
    public static final Item WOOL_UPPERWEAR_BASE_BUTTONS = registerItem("wool_upperwear_base_buttons", WoolUpperwearBaseButtonsItem::new);
    public static final Item WOOL_SLEEVE_SHORT = registerItem("wool_sleeve_short", WoolSleeveShortItem::new);
    public static final Item WOOL_SLEEVE_LONG = registerItem("wool_sleeve_long", WoolSleeveLongItem::new);
    public static final Item WOOL_PANT_LEG = registerItem("wool_pant_leg", WoolPantLegItem::new);

    public static final Item JEANS_PANT_LEG = registerItem("jeans_pant_leg", JeansPantLegItem::new);

    //stencils
    public static final Item BASIC_STENCIL = registerItem("base_stencil", StencilBaseItem::new);
    public static final Item HALF_STENCIL = registerItem("half_stencil", StencilHalfItem::new);
    public static final Item CHECKERS_STENCIL = registerItem("checkers_stencil", StencilCheckersItem::new);
    public static final Item CHECKERS_SMALL_STENCIL = registerItem("checkers_small_stencil", StencilCheckersSmallItem::new);
    public static final Item LINES_VERTICAL_STENCIL = registerItem("lines_vertical_stencil", StencilLinesVerticalItem::new);
    public static final Item LINES_SMALL_STENCIL = registerItem("lines_small_stencil", StencilLinesSmallItem::new);
    public static final Item LINES_BIG_STENCIL = registerItem("lines_big_stencil", StencilLinesBigItem::new);
    public static final Item CROSS_STENCIL = registerItem("cross_stencil", StencilCrossItem::new);
    public static final Item PAWS_STENCIL = registerItem("paws_stencil", StencilPawsItem::new);
    public static final Item STAR_STENCIL = registerItem("star_stencil", StarStencilItem::new);
    public static final Item DIRT_STENCIL = registerItem("dirt_stencil", DirtStencilItem::new);
    public static final Item FLOWER_STENCIL = registerItem("flower_stencil", FlowerStencilItem::new);

    //blocks
    public static final Item CHROMATIC_BLOOM = registerItem("chromatic_bloom", () -> new BlockItem(WeaversParadiseBlocks.CHROMATIC_BLOOM, new Item.Properties().rarity(Rarity.RARE)));
    public static final Item WILD_COTTON_PLANT = registerItem("wild_cotton", () -> new BlockItem(WeaversParadiseBlocks.WILD_COTTON_PLANT, new Item.Properties()));
    public static final Item SPINNING_JENNY = registerItem("spinning_jenny", () -> new BlockItem(WeaversParadiseBlocks.SPINNING_JENNY, new Item.Properties()));
    public static final Item CLOTHCRAFTING_STATION = registerItem("clothcrafting_station", () -> new BlockItem(WeaversParadiseBlocks.CLOTHCRAFTING_STATION, new Item.Properties()));
    public static final Item DYEMAKING_BLOCK = registerItem("dyemaking_block", () -> new BlockItem(WeaversParadiseBlocks.DYEMAKING_BLOCK, new Item.Properties()));
    public static final Item DYEING_BARREL_BLOCK = registerItem("dyeing_barrel", () -> new BlockItem(WeaversParadiseBlocks.DYEING_BARREL_BLOCK, new Item.Properties()));
    public static final Item COTTON_SEEDS = registerItem("cotton_seeds", () -> new ItemNameBlockItem(WeaversParadiseBlocks.COTTON_BUSH, new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final Item COTTON_SPOOL_BLOCK = registerItem("cotton_spool_block", () -> new BlockItem(WeaversParadiseBlocks.COTTON_SPOOL_BLOCK, new Item.Properties()));
    public static final Item COTTON_CLOTH_BLOCK = registerItem("cotton_cloth_block", () -> new BlockItem(WeaversParadiseBlocks.COTTON_CLOTH_BLOCK, new Item.Properties()));
    public static final Item SILK_SPOOL_BLOCK = registerItem("silk_spool_block", () -> new BlockItem(WeaversParadiseBlocks.SILK_SPOOL_BLOCK, new Item.Properties()));
    public static final Item SILK_CLOTH_BLOCK = registerItem("silk_cloth_block", () -> new BlockItem(WeaversParadiseBlocks.SILK_CLOTH_BLOCK, new Item.Properties()));
    public static final Item WOOL_SPOOL_BLOCK = registerItem("wool_spool_block", () -> new BlockItem(WeaversParadiseBlocks.WOOL_SPOOL_BLOCK, new Item.Properties()));
    public static final Item WOOL_CLOTH_BLOCK = registerItem("wool_cloth_block", () -> new BlockItem(WeaversParadiseBlocks.WOOL_CLOTH_BLOCK, new Item.Properties()));
    public static final Item WITCHROOT = registerItem("witchroot", () -> new BlockItem(WeaversParadiseBlocks.WITCHROOT, new Item.Properties().rarity(Rarity.RARE)));
    public static final Item STARBLOOM = registerItem("starbloom", () -> new BlockItem(WeaversParadiseBlocks.STARBLOOM, new Item.Properties().rarity(Rarity.RARE)));

    //flags
    public static final Item FLAG_BASIC = registerItem("flag_base", EmptyFlagItem::new);
    public static final Item FLAG_AGENDER = registerItem("flag_agender", AgenderFlagItem::new);
    public static final Item FLAG_AROACE = registerItem("flag_aroace", AroaceFlagItem::new);
    public static final Item FLAG_AROMANTIC = registerItem("flag_aromantic", AromanticFlagItem::new);
    public static final Item FLAG_ASEXUAL = registerItem("flag_asexual", AsexualFlagItem::new);
    public static final Item FLAG_BISEXUAL = registerItem("flag_bisexual", BisexualFlagItem::new);
    public static final Item FLAG_DEMIBOY = registerItem("flag_demiboy", DemiboyFlagItem::new);
    public static final Item FLAG_DEMIGENDER = registerItem("flag_demigender", DemigenderFlagItem::new);
    public static final Item FLAG_DEMIGIRL = registerItem("flag_demigirl", DemigirlFlagItem::new);
    public static final Item FLAG_GAY = registerItem("flag_gay", GayFlagItem::new);
    public static final Item FLAG_GENDERFLUID = registerItem("flag_genderfluid", GenderfluidFlagItem::new);
    public static final Item FLAG_GENDERQUEER = registerItem("flag_genderqueer", GenderqueerFlagItem::new);
    public static final Item FLAG_INTERSEX = registerItem("flag_intersex", IntersexFlagItem::new);
    public static final Item FLAG_LESBIAN = registerItem("flag_lesbian", LesbianFlagItem::new);
    public static final Item FLAG_NONBINARY = registerItem("flag_nonbinary", NonbinaryFlagItem::new);
    public static final Item FLAG_PANSEXUAL = registerItem("flag_pansexual", PansexualFlagItem::new);
    public static final Item FLAG_PRIDE = registerItem("flag_pride", PrideFlagItem::new);
    public static final Item FLAG_TRANS = registerItem("flag_trans", TransFlagItem::new);

    //misc
    public static final Item PURE_DYE = registerItem("pure_dye", PigmentItem::new);
    public static final Item KOZINAKI = registerItem("kozinaki", KozinakiItem::new);
    public static final Item MORTAR_AND_PESTLE = registerItem("mortar_and_pestle", MortarAndPestleItem::new);
    public static final Item PESTLE = registerItem("pestle", PestleItem::new);
    public static final Item CHROMATIC_BLOOM_FRUIT = registerItem("chromatic_bloom_fruit", ChromaticBloomFruitItem::new);
    public static final Item CHROMATIC_DUST = registerItem("chromatic_dust", ChromaticDustItem::new);
    public static final Item BOTTLED_DYE = registerItem("bottled_dye", BottledDyeItem::new);
    public static final Item DYE_CORE = registerItem("dye_core", DyeCoreItem::new);
    public static final Item COTTON_BOLL = registerItem("cotton_boll", CottonBollItem::new);
    public static final Item RAW_COTTON = registerItem("raw_cotton", RawCottonItem::new);
    public static final Item COTTON_CLOTH = registerItem("cotton_cloth", () -> new CottonClothItem(0));
    public static final Item WOOL_CLOTH = registerItem("wool_cloth", () -> new WoolClothItem(0));
    public static final Item SILK_CLOTH = registerItem("silk_cloth", () -> new SilkClothItem(0));
    public static final Item JEANS_CLOTH = registerItem("jeans_cloth", () -> new JeansClothItem(0));
    public static final Item EMPTY_SPOOL = registerItem("empty_spool", EmptySpoolItem::new);
    public static final Item COTTON_SPOOL = registerItem("cotton_spool", CottonSpoolItem::new);
    public static final Item WOOL_SPOOL = registerItem("wool_spool", WoolSpooltem::new);
    public static final Item SILK_SPOOL = registerItem("silk_spool", SilkSpoolItem::new);
    public static final Item JEANS_SPOOL = registerItem("jeans_spool", JeansSpoolItem::new);
    public static final Item NEEDLE = registerItem("needle", NeedleItem::new);
    public static final Item NEEDLE_WITH_THREAD = registerItem("needle_with_thread", NeedleWithThreadItem::new);
    public static final Item BUTTON = registerItem("button", ButtonItem::new);
    public static final Item PLAYER_PLUSHIE = registerItem("player_plushie", () -> new PlushieItem(WeaversParadiseBlocks.PLUSHIE));
    public static final Item PLAYER_PLUSHIE_RENAME_TOKEN = registerItem("player_plushie_rename_token", PlayerPlushieRenameTokenItem::new);
    public static final Item LEATHER_STRIPS = registerItem("leather_strips", LeatherStripsItem::new);
    public static final Item ARMOR_LOOTBOX = registerItem("armor_lootbox", ArmorLootboxItem::new);
    public static final Item WITCHROOT_FRUIT = registerItem("witchroot_fruit", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64)));
    public static final Item WITCHROOT_FRUIT_COOKED = registerItem("witchroot_fruit_cooked", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64)));
    public static final Item STARBLOOM_FRUIT = registerItem("starbloom_fruit", () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(64)));
    public static final Item WEAVERS_SHEARS = registerItem("weavers_shears", WeaversShearsItem::new);
    public static final DoubleSidedBlockItem BLAHAJ = registerItem("blahaj", () -> new BlahajItem(WeaversParadiseBlocks.BLAHAJ));

    public static void register() {
    }
}
