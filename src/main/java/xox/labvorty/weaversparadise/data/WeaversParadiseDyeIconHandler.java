package xox.labvorty.weaversparadise.data;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WeaversParadiseDyeIconHandler {
    DEFAULT_ICON("default",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_empty.png")
    ),
    SCULK_ICON("sculk",
            ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/item/echo_shard.png")
    ),
    ENDER_ICON("ender",
            ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/item/ender_eye.png"
            )
    ),
    REDSTONE_ICON("redstone",
            ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/item/redstone.png")
    ),
    GLOWSTONE_ICON("glowstone",
            ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/item/glowstone_dust.png")
    ),
    NONE_ICON("none",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/empty.png")
    ),
    PRIDE_ICON("pride",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_pride.png")
    ),
    ASEXUAL_ICON("asexual",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_asexual.png")
    ),
    AROMANTIC_ICON("aromantic",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_aromantic.png")
    ),
    AROACE_ICON("aroace",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_aroace.png")
    ),
    AGENDER_ICON("agender",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_agender.png")
    ),
    BISEXUAL_ICON("bisexual",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_bisexual.png")
    ),
    DEMIBOY_ICON("demiboy",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_demiboy.png")
    ),
    DEMIGENDER_ICON("demigender",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_demigender.png")
    ),
    DEMIGIRL_ICON("demigirl",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_demigirl.png")
    ),
    GAY_ICON("gay",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_gay.png")
    ),
    GENDERFLUID_ICON("genderfluid",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_genderfluid.png")
    ),
    GENDERQUEER_ICON("genderqueer",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_genderqueer.png")
    ),
    INTERSEX_ICON("intersex",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_intersex.png")
    ),
    LESBIAN_ICON("lesbian",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_lesbian.png")
    ),
    NONBINARY_ICON("nonbinary",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_nonbinary.png")
    ),
    PANSEXUAL_ICON("pansexual",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_pansexual.png")
    ),
    TRANS_ICON("trans",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_trans.png")
    ),
    POLYCHROMATIC_ICON("polychromatic",
            ResourceLocation.fromNamespaceAndPath(
                    "weaversparadise",
                    "textures/tooltips/flag_empty.png")
    ),
    CRYSTAL_ICON("crystal",
            ResourceLocation.fromNamespaceAndPath(
                    "minecraft",
                    "textures/item/amethyst_shard.png")
    );

    private static final Map<String, WeaversParadiseDyeIconHandler> HANDLER_MAP =
            Stream.of(values()).collect(Collectors.toMap(WeaversParadiseDyeIconHandler::getName, e -> e));

    private final String name;
    private final ResourceLocation texture;

    WeaversParadiseDyeIconHandler(String name, ResourceLocation texture) {
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public static WeaversParadiseDyeIconHandler getOrDefault(String name) {
        return HANDLER_MAP.getOrDefault(name, DEFAULT_ICON);
    }
}
