package xox.labvorty.weaversparadise.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.data.ThighHighsRenderer;
import xox.labvorty.weaversparadise.items.*;
import xox.labvorty.weaversparadise.items.armor.AstolfoArmor;
import xox.labvorty.weaversparadise.items.flags.*;

public class WeaversParadiseItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("weaversparadise");
    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register("test_item", TestItem::new);
    //clothing items
    public static final DeferredItem<Item> THIGH_HIGHS = ITEMS.register("thigh_highs", ThighHighsBaseItem::new);
    public static final DeferredItem<Item> THIGH_HIGHS_COTTON = ITEMS.register("thigh_highs_cotton", ThighHighsCotton::new);
    public static final DeferredItem<Item> THIGH_HIGHS_WOOL = ITEMS.register("thigh_highs_wool", ThighHighsWool::new);
    public static final DeferredItem<Item> THIGH_HIGHS_SILK = ITEMS.register("thigh_highs_silk", ThighHighsSilk::new);

    public static final DeferredItem<Item> HAND_WARMERS_COTTON = ITEMS.register("hand_warmers_cotton", HandWarmersCotton::new);
    public static final DeferredItem<Item> HAND_WARMERS_SILK = ITEMS.register("hand_warmers_silk", HandWarmersSilk::new);
    public static final DeferredItem<Item> HAND_WARMERS_WOOL = ITEMS.register("hand_warmers_wool", HandWarmersWool::new);

    public static final DeferredItem<Item> SHIRT_COTTON = ITEMS.register("shirt_cotton", ShirtCotton::new);
    public static final DeferredItem<Item> SHIRT_SILK = ITEMS.register("shirt_silk", ShirtSilk::new);
    public static final DeferredItem<Item> SWEATER_WOOL = ITEMS.register("sweater_wool", SweaterWool::new);

    public static final DeferredItem<Item> LEATHER_GLOVES = ITEMS.register("leather_gloves", LeatherGloves::new);

    public static final DeferredItem<Item> ASTOLFO_ARMOR_WIG = ITEMS.register("astolfo_armor_wig", AstolfoArmor.Helmet::new);
    public static final DeferredItem<Item> ASTOLFO_ARMOR_CHESTPLATE = ITEMS.register("astolfo_armor_chestplate", AstolfoArmor.Chestplate::new);
    public static final DeferredItem<Item> ASTOLFO_ARMOR_LEGGINGS = ITEMS.register("astolfo_armor_skirt", AstolfoArmor.Leggings::new);
    public static final DeferredItem<Item> ASTOLFO_ARMOR_BOOTS = ITEMS.register("astolfo_armor_boots", AstolfoArmor.Boots::new);

    //complex items
    public static final DeferredItem<Item> COTTON_UPPERWEAR_BASE = ITEMS.register("cotton_upperwear_base", CottonUpperwearBase::new);
    public static final DeferredItem<Item> COTTON_UPPERWEAR_BASE_BUTTONS = ITEMS.register("cotton_upperwear_base_buttons", CottonUpperwearBaseButtons::new);
    public static final DeferredItem<Item> COTTON_UPPERWEAR_BASE_ZIPPER = ITEMS.register("cotton_upperwear_base_zipper", CottonUpperwearBaseZipper::new);
    public static final DeferredItem<Item> COTTON_SLEEVE_SHORT = ITEMS.register("cotton_sleeve_short", CottonSleeveShort::new);
    public static final DeferredItem<Item> COTTON_SLEEVE_LONG = ITEMS.register("cotton_sleeve_long", CottonSleeveLong::new);

    public static final DeferredItem<Item> SILK_UPPERWEAR_BASE = ITEMS.register("silk_upperwear_base", SilkUpperwearBase::new);
    public static final DeferredItem<Item> SILK_UPPERWEAR_BASE_BUTTONS = ITEMS.register("silk_upperwear_base_buttons", SilkUpperwearBaseButtons::new);
    public static final DeferredItem<Item> SILK_UPPERWEAR_BASE_ZIPPER = ITEMS.register("silk_upperwear_base_zipper", SilkUpperwearBaseZipper::new);
    public static final DeferredItem<Item> SILK_SLEEVE_SHORT = ITEMS.register("silk_sleeve_short", SilkSleeveShort::new);
    public static final DeferredItem<Item> SILK_SLEEVE_LONG = ITEMS.register("silk_sleeve_long", SilkSleeveLong::new);

    public static final DeferredItem<Item> WOOL_UPPERWEAR_BASE = ITEMS.register("wool_upperwear_base", WoolUpperwearBase::new);
    public static final DeferredItem<Item> WOOL_UPPERWEAR_BASE_BUTTONS = ITEMS.register("wool_upperwear_base_buttons", WoolUpperwearBaseButtons::new);
    public static final DeferredItem<Item> WOOL_SLEEVE_SHORT = ITEMS.register("wool_sleeve_short", WoolSleeveShort::new);
    public static final DeferredItem<Item> WOOL_SLEEVE_LONG = ITEMS.register("wool_sleeve_long", WoolSleeveLong::new);

    //stensils
    public static final DeferredItem<Item> BASIC_STENSIL = ITEMS.register("base_stensil", StensilBase::new);
    public static final DeferredItem<Item> HALF_STENSIL = ITEMS.register("half_stensil", StensilHalf::new);
    public static final DeferredItem<Item> CHECKERS_STENSIL = ITEMS.register("checkers_stensil", StensilCheckers::new);
    public static final DeferredItem<Item> LINES_VERTICAL_STENSIL = ITEMS.register("lines_vertical_stensil", StensilLinesVertical::new);
    public static final DeferredItem<Item> LINES_SMALL_STENSIL = ITEMS.register("lines_small_stensil", StensilLinesSmall::new);
    public static final DeferredItem<Item> LINES_BIG_STENSIL = ITEMS.register("lines_big_stensil", StensilLinesBig::new);
    public static final DeferredItem<Item> CROSS_STENSIL = ITEMS.register("cross_stensil", StensilCross::new);
    public static final DeferredItem<Item> PAWS_STENSIL = ITEMS.register("paws_stensil", StensilPaws::new);

    //blocks
    public static final DeferredItem<Item> CHROMATIC_BLOOM = ITEMS.register(WeaversParadiseBlocks.CHROMATIC_BLOOM.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.CHROMATIC_BLOOM.get(), new Item.Properties()));
    public static final DeferredItem<Item> WILD_COTTON_PLANT = ITEMS.register(WeaversParadiseBlocks.WILD_COTTON_PLANT.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WILD_COTTON_PLANT.get(), new Item.Properties()));
    public static final DeferredItem<Item> SPINNING_JENNY = ITEMS.register(WeaversParadiseBlocks.SPINNING_JENNY.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SPINNING_JENNY.get(), new Item.Properties()));
    public static final DeferredItem<Item> CLOTHCRAFTING_STATION = ITEMS.register(WeaversParadiseBlocks.CLOTHCRAFTING_STATION.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.CLOTHCRAFTING_STATION.get(), new Item.Properties()));
    public static final DeferredItem<Item> DYEMAKING_BLOCK = ITEMS.register(WeaversParadiseBlocks.DYEMAKING_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.DYEMAKING_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> DYEING_BARREL_BLOCK = ITEMS.register(WeaversParadiseBlocks.DYEING_BARREL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.DYEING_BARREL_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds", () -> new ItemNameBlockItem(WeaversParadiseBlocks.COTTON_BUSH.get(), new Item.Properties().stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> COTTON_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.COTTON_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.COTTON_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> COTTON_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.COTTON_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.COTTON_CLOTH_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> SILK_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.SILK_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SILK_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> SILK_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.SILK_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.SILK_CLOTH_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> WOOL_SPOOL_BLOCK = ITEMS.register(WeaversParadiseBlocks.WOOL_SPOOL_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WOOL_SPOOL_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> WOOL_CLOTH_BLOCK = ITEMS.register(WeaversParadiseBlocks.WOOL_CLOTH_BLOCK.getId().getPath(), () -> new BlockItem(WeaversParadiseBlocks.WOOL_CLOTH_BLOCK.get(), new Item.Properties()));

    //flags
    public static final DeferredItem<Item> FLAG_BASIC = ITEMS.register("flag_base", EmptyFlag::new);
    public static final DeferredItem<Item> FLAG_AGENDER = ITEMS.register("flag_agender", AgenderFlag::new);
    public static final DeferredItem<Item> FLAG_AROACE = ITEMS.register("flag_aroace", AroaceFlag::new);
    public static final DeferredItem<Item> FLAG_AROMANTIC = ITEMS.register("flag_aromantic", AromanticFlag::new);
    public static final DeferredItem<Item> FLAG_ASEXUAL = ITEMS.register("flag_asexual", AsexualFlag::new);
    public static final DeferredItem<Item> FLAG_BISEXUAL = ITEMS.register("flag_bisexual", BisexualFlag::new);
    public static final DeferredItem<Item> FLAG_DEMIBOY = ITEMS.register("flag_demiboy", DemiboyFlag::new);
    public static final DeferredItem<Item> FLAG_DEMIGENDER = ITEMS.register("flag_demigender", DemigenderFlag::new);
    public static final DeferredItem<Item> FLAG_DEMIGIRL = ITEMS.register("flag_demigirl", DemigirlFlag::new);
    public static final DeferredItem<Item> FLAG_GAY = ITEMS.register("flag_gay", GayFlag::new);
    public static final DeferredItem<Item> FLAG_GENDERFLUID = ITEMS.register("flag_genderfluid", GenderfluidFlag::new);
    public static final DeferredItem<Item> FLAG_GENDERQUEER = ITEMS.register("flag_genderqueer", GenderqueerFlag::new);
    public static final DeferredItem<Item> FLAG_INTERSEX = ITEMS.register("flag_intersex", IntersexFlag::new);
    public static final DeferredItem<Item> FLAG_LESBIAN = ITEMS.register("flag_lesbian", LesbianFlag::new);
    public static final DeferredItem<Item> FLAG_NONBINARY = ITEMS.register("flag_nonbinary", NonbinaryFlag::new);
    public static final DeferredItem<Item> FLAG_PANSEXUAL = ITEMS.register("flag_pansexual", PansexualFlag::new);
    public static final DeferredItem<Item> FLAG_PRIDE = ITEMS.register("flag_pride", PrideFlag::new);
    public static final DeferredItem<Item> FLAG_TRANS = ITEMS.register("flag_trans", TransFlag::new);

    //misc
    public static final DeferredItem<Item> PURE_DYE = ITEMS.register("pure_dye", PureDyeItem::new);
    public static final DeferredItem<Item> KOZINAKI = ITEMS.register("kozinaki", Kozinaki::new);
    public static final DeferredItem<Item> MORTAR_AND_PESTLE = ITEMS.register("mortar_and_pestle", MortarAndPestle::new);
    public static final DeferredItem<Item> PESTLE = ITEMS.register("pestle", Pestle::new);
    public static final DeferredItem<Item> CHROMATIC_BLOOM_FRUIT = ITEMS.register("chromatic_bloom_fruit", ChromaticBloomFruit::new);
    public static final DeferredItem<Item> BOTTLED_DYE = ITEMS.register("bottled_dye", BottledDyeItem::new);
    public static final DeferredItem<Item> DYE_CORE = ITEMS.register("dye_core", DyeCore::new);
    public static final DeferredItem<Item> COTTON_BOLL = ITEMS.register("cotton_boll", CottonBoll::new);
    public static final DeferredItem<Item> RAW_COTTON = ITEMS.register("raw_cotton", RawCotton::new);
    public static final DeferredItem<Item> COTTON_CLOTH = ITEMS.register("cotton_cloth", () -> new CottonCloth(0));
    public static final DeferredItem<Item> WOOL_CLOTH = ITEMS.register("wool_cloth", () -> new WoolCloth(0));
    public static final DeferredItem<Item> SILK_CLOTH = ITEMS.register("silk_cloth", () -> new SilkCloth(0));
    public static final DeferredItem<Item> EMPTY_SPOOL = ITEMS.register("empty_spool", EmptySpool::new);
    public static final DeferredItem<Item> COTTON_SPOOL = ITEMS.register("cotton_spool", CottonSpool::new);
    public static final DeferredItem<Item> WOOL_SPOOL = ITEMS.register("wool_spool", WoolSpool::new);
    public static final DeferredItem<Item> SILK_SPOOL = ITEMS.register("silk_spool", SilkSpool::new);
    public static final DeferredItem<Item> NEEDLE = ITEMS.register("needle", Needle::new);
    public static final DeferredItem<Item> NEEDLE_WITH_THREAD = ITEMS.register("needle_with_thread", NeedleWithThread::new);
    public static final DeferredItem<Item> BUTTON = ITEMS.register("button", Button::new);
}
