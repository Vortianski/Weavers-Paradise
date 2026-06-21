package xox.labvorty.weaversparadise.data.tooltip_components.helper;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeIcon;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.ItemDyeIcon;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class BuiltInDyeTypes {
    public static void register() {
        DyeTypeRegistry.registerDyeType(
                "rainbow",
                new ItemDyeIcon(WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT.get()),
                Component.translatable("weaversparadise.dye_text.rainbow")
        );

        DyeTypeRegistry.registerDyeType(
                "ender",
                new ItemDyeIcon(Items.ENDER_EYE),
                Component.translatable("weaversparadise.dye_text.ender")
        );

        DyeTypeRegistry.registerDyeType(
                "sculk",
                new ItemDyeIcon(Items.SCULK),
                Component.translatable("weaversparadise.dye_text.sculk")
        );

        DyeTypeRegistry.registerDyeType(
                "biome",
                new ItemDyeIcon(Items.MOSS_BLOCK),
                Component.translatable("weaversparadise.dye_text.biome")
        );

        DyeTypeRegistry.registerDyeType(
                "glowstone",
                new ItemDyeIcon(Items.GLOWSTONE),
                Component.translatable("weaversparadise.dye_text.glowstone")
        );

        DyeTypeRegistry.registerDyeType(
                "redstone",
                new ItemDyeIcon(Items.REDSTONE),
                Component.translatable("weaversparadise.dye_text.redstone")
        );

        DyeTypeRegistry.registerDyeType(
                "lamp",
                new ItemDyeIcon(Items.REDSTONE_LAMP),
                Component.translatable("weaversparadise.dye_text.lamp")
        );

        DyeTypeRegistry.registerDyeType(
                "speed",
                new ItemDyeIcon(Items.SUGAR),
                Component.translatable("weaversparadise.dye_text.speed")
        );

        DyeTypeRegistry.registerDyeType(
                "height_bedrock",
                new ItemDyeIcon(Items.PHANTOM_MEMBRANE),
                Component.translatable("weaversparadise.dye_text.height_bedrock")
        );

        DyeTypeRegistry.registerDyeType(
                "height_sea",
                new ItemDyeIcon(Items.PRISMARINE_CRYSTALS),
                Component.translatable("weaversparadise.dye_text.height_sea")
        );

        DyeTypeRegistry.registerDyeType(
                "pride",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_PRIDE.get()),
                Component.translatable("weaversparadise.dye_text.pride")
        );

        DyeTypeRegistry.registerDyeType(
                "asexual",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_ASEXUAL.get()),
                Component.translatable("weaversparadise.dye_text.asexual")
        );

        DyeTypeRegistry.registerDyeType(
                "aromantic",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AROMANTIC.get()),
                Component.translatable("weaversparadise.dye_text.aromantic")
        );

        DyeTypeRegistry.registerDyeType(
                "aroace",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AROACE.get()),
                Component.translatable("weaversparadise.dye_text.aroace")
        );

        DyeTypeRegistry.registerDyeType(
                "agender",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_AGENDER.get()),
                Component.translatable("weaversparadise.dye_text.agender")
        );

        DyeTypeRegistry.registerDyeType(
                "bisexual",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_BISEXUAL.get()),
                Component.translatable("weaversparadise.dye_text.bisexual")
        );

        DyeTypeRegistry.registerDyeType(
                "demiboy",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIBOY.get()),
                Component.translatable("weaversparadise.dye_text.demiboy")
        );

        DyeTypeRegistry.registerDyeType(
                "demigender",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIGENDER.get()),
                Component.translatable("weaversparadise.dye_text.demigender")
        );

        DyeTypeRegistry.registerDyeType(
                "demigirl",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_DEMIGIRL.get()),
                Component.translatable("weaversparadise.dye_text.demigirl")
        );

        DyeTypeRegistry.registerDyeType(
                "gay",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GAY.get()),
                Component.translatable("weaversparadise.dye_text.gay")
        );

        DyeTypeRegistry.registerDyeType(
                "genderfluid",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GENDERFLUID.get()),
                Component.translatable("weaversparadise.dye_text.genderfluid")
        );

        DyeTypeRegistry.registerDyeType(
                "genderqueer",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_GENDERQUEER.get()),
                Component.translatable("weaversparadise.dye_text.genderqueer")
        );

        DyeTypeRegistry.registerDyeType(
                "intersex",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_INTERSEX.get()),
                Component.translatable("weaversparadise.dye_text.intersex")
        );

        DyeTypeRegistry.registerDyeType(
                "lesbian",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_LESBIAN.get()),
                Component.translatable("weaversparadise.dye_text.lesbian")
        );

        DyeTypeRegistry.registerDyeType(
                "nonbinary",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_NONBINARY.get()),
                Component.translatable("weaversparadise.dye_text.nonbinary")
        );

        DyeTypeRegistry.registerDyeType(
                "pansexual",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_PANSEXUAL.get()),
                Component.translatable("weaversparadise.dye_text.pansexual")
        );

        DyeTypeRegistry.registerDyeType(
                "trans",
                new ItemDyeIcon(WeaversParadiseItems.FLAG_TRANS.get()),
                Component.translatable("weaversparadise.dye_text.trans")
        );

        DyeTypeRegistry.registerDyeType(
                "polychromatic",
                new ItemDyeIcon(WeaversParadiseItems.CHROMATIC_DUST.get()),
                Component.translatable("weaversparadise.dye_text.polychromatic")
        );

        DyeTypeRegistry.registerDyeType(
                "invisible",
                new ItemDyeIcon(Items.GLASS),
                Component.translatable("weaversparadise.dye_text.invisible")
        );

        DyeTypeRegistry.registerDyeType(
                "static",
                new ItemDyeIcon(Items.POWDER_SNOW_BUCKET),
                Component.translatable("weaversparadise.dye_text.static")
        );

        DyeTypeRegistry.registerDyeType(
                "crystal",
                new ItemDyeIcon(Items.AMETHYST_SHARD),
                Component.translatable("weaversparadise.dye_text.crystal")
        );

        DyeTypeRegistry.registerDyeType(
                "negative",
                new ItemDyeIcon(Items.BARRIER),
                Component.translatable("weaversparadise.dye_text.negative")
        );

        DyeTypeRegistry.registerDyeType(
                "true_negative",
                new ItemDyeIcon(Items.BARRIER),
                Component.translatable("weaversparadise.dye_text.true_negative")
        );

        DyeTypeRegistry.registerDyeType(
                "nebula",
                new ItemDyeIcon(Items.DRAGON_BREATH),
                Component.translatable("weaversparadise.dye_text.nebula")
        );

        DyeTypeRegistry.registerDyeType(
                "colored_sculk",
                new ItemDyeIcon(Items.ECHO_SHARD),
                Component.translatable("weaversparadise.dye_text.sculk")
        );

        DyeTypeRegistry.registerDyeType(
                "hunger",
                new DyeIcon(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/tooltips/food_full.png")),
                Component.translatable("weaversparadise.dye_text.hunger")
        );

        DyeTypeRegistry.registerDyeType(
                "health",
                new DyeIcon(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/tooltips/heart_full.png")),
                Component.translatable("weaversparadise.dye_text.health")
        );

        DyeTypeRegistry.registerDyeType(
                "day_time",
                new ItemDyeIcon(Items.SUNFLOWER),
                Component.translatable("weaversparadise.dye_text.day_time")
        );

        DyeTypeRegistry.registerDyeType(
                "colored_day_time",
                new ItemDyeIcon(Items.CLOCK),
                Component.translatable("weaversparadise.dye_text.day_time")
        );
    }
}
