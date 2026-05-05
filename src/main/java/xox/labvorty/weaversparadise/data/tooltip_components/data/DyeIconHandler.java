package xox.labvorty.weaversparadise.data.tooltip_components.data;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DyeIconHandler {
    DEFAULT_ICON("default",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_empty.png")
    ),
    SCULK_ICON("sculk",
            new ResourceLocation(
                    "minecraft",
                    "textures/item/echo_shard.png")
    ),
    ENDER_ICON("ender",
            new ResourceLocation(
                    "minecraft",
                    "textures/item/ender_eye.png"
            )
    ),
    REDSTONE_ICON("redstone",
            new ResourceLocation(
                    "minecraft",
                    "textures/item/redstone.png")
    ),
    GLOWSTONE_ICON("glowstone",
            new ResourceLocation(
                    "minecraft",
                    "textures/item/glowstone_dust.png")
    ),
    PRIDE_ICON("pride",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_pride.png")
    ),
    ASEXUAL_ICON("asexual",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_asexual.png")
    ),
    AROMANTIC_ICON("aromantic",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_aromantic.png")
    ),
    AROACE_ICON("aroace",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_aroace.png")
    ),
    AGENDER_ICON("agender",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_agender.png")
    ),
    BISEXUAL_ICON("bisexual",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_bisexual.png")
    ),
    DEMIBOY_ICON("demiboy",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_demiboy.png")
    ),
    DEMIGENDER_ICON("demigender",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_demigender.png")
    ),
    DEMIGIRL_ICON("demigirl",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_demigirl.png")
    ),
    GAY_ICON("gay",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_gay.png")
    ),
    GENDERFLUID_ICON("genderfluid",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_genderfluid.png")
    ),
    GENDERQUEER_ICON("genderqueer",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_genderqueer.png")
    ),
    INTERSEX_ICON("intersex",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_intersex.png")
    ),
    LESBIAN_ICON("lesbian",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_lesbian.png")
    ),
    NONBINARY_ICON("nonbinary",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_nonbinary.png")
    ),
    PANSEXUAL_ICON("pansexual",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_pansexual.png")
    ),
    TRANS_ICON("trans",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_trans.png")
    ),
    POLYCHROMATIC_ICON("polychromatic",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/flag_empty.png")
    ),
    CRYSTAL_ICON("crystal",
            new ResourceLocation(
                    "minecraft",
                    "textures/item/amethyst_shard.png")
    ),
    COLORED_SCULK_ICON("colored_sculk",
            new ResourceLocation(
                    "minecraft",
                    "textures/item/echo_shard.png")
    ),
    HUNGER("hunger",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/food_full.png"
            )
    ),
    HEALTH("health",
            new ResourceLocation(
                    "weaversparadise",
                    "textures/tooltips/heart_full.png"
            )
    ),
    DAY_TIME("day_time",
            new ResourceLocation(
                    "minecraft",
                    "textures/block/sunflower_front.png"
            )
    ),
    COLORED_DAY_TIME("colored_day_time",
            new ResourceLocation(
                    "minecraft",
                    "textures/item/clock_00.png"
            )
    );

    private static final Map<String, DyeIconHandler> HANDLER_MAP =
            Stream.of(values()).collect(Collectors.toMap(DyeIconHandler::getName, e -> e));

    private final String name;
    private final ResourceLocation texture;

    DyeIconHandler(String name, ResourceLocation texture) {
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public static DyeIconHandler getOrDefault(String name) {
        return HANDLER_MAP.getOrDefault(name, DEFAULT_ICON);
    }
}
