package xox.labvorty.weaversparadise.data;

import net.minecraft.network.chat.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WeaversParadiseDyeTextHandler {
    DEFAULT_TEXT("default",
            Component.translatable("weaversparadise.dye_text.default").getString()
    ),
    RAINBOW_TEXT("rainbow",
            Component.translatable("weaversparadise.dye_text.rainbow").getString()
    ),
    ENDER_TEXT("ender",
            Component.translatable("weaversparadise.dye_text.ender").getString()
    ),
    SCULK_TEXT("sculk",
            Component.translatable("weaversparadise.dye_text.sculk").getString()
    ),
    BIOME_TEXT("biome",
            Component.translatable("weaversparadise.dye_text.biome").getString()
    ),
    GLOWSTONE_TEXT("glowstone",
            Component.translatable("weaversparadise.dye_text.glowstone").getString()
    ),
    REDSTONE_TEXT("redstone",
            Component.translatable("weaversparadise.dye_text.redstone").getString()
    ),
    LAMP_TEXT("lamp",
            Component.translatable("weaversparadise.dye_text.lamp").getString()
    ),
    SPEED_TEXT("speed",
            Component.translatable("weaversparadise.dye_text.speed").getString()
    ),
    HEIGHT_BEDROCK_TEXT("height_bedrock",
            Component.translatable("weaversparadise.dye_text.height_bedrock").getString()
    ),
    HEIGHT_SEA_TEXT("height_sea",
            Component.translatable("weaversparadise.dye_text.height_sea").getString()
    ),
    NONE_TEXT("none",
            Component.translatable("weaversparadise.dye_text.none").getString()
    ),
    PRIDE_TEXT("pride",
            Component.translatable("weaversparadise.dye_text.pride").getString()
    ),
    ASEXUAL_TEXT("asexual",
            Component.translatable("weaversparadise.dye_text.asexual").getString()
    ),
    AROMANTIC_TEXT("aromantic",
            Component.translatable("weaversparadise.dye_text.aromantic").getString()
    ),
    AROACE_TEXT("aroace",
            Component.translatable("weaversparadise.dye_text.aroace").getString()
    ),
    AGENDER_TEXT("agender",
            Component.translatable("weaversparadise.dye_text.agender").getString()
    ),
    BISEXUAL_TEXT("bisexual",
            Component.translatable("weaversparadise.dye_text.bisexual").getString()
    ),
    DEMIBIY_TEXT("demiboy",
            Component.translatable("weaversparadise.dye_text.demiboy").getString()
    ),
    DEMIGENDER_TEXT("demigender",
            Component.translatable("weaversparadise.dye_text.demigender").getString()
    ),
    DEMIGIRL_TEXT("demigirl",
            Component.translatable("weaversparadise.dye_text.demigirl").getString()
    ),
    GAY_TEXT("gay",
            Component.translatable("weaversparadise.dye_text.gay").getString()
    ),
    GENDERFLUID_TEXT("genderfluid",
            Component.translatable("weaversparadise.dye_text.genderfluid").getString()
    ),
    GENDERQUEER_TEXT("genderqueer",
            Component.translatable("weaversparadise.dye_text.genderqueer").getString()
    ),
    INTERSEX_TEXT("intersex",
            Component.translatable("weaversparadise.dye_text.intersex").getString()
    ),
    LESBIAN_TEXT("lesbian",
            Component.translatable("weaversparadise.dye_text.lesbian").getString()
    ),
    NONBINARY_TEXT("nonbinary",
            Component.translatable("weaversparadise.dye_text.nonbinary").getString()
    ),
    PANSEXUAL_TEXT("pansexual",
            Component.translatable("weaversparadise.dye_text.pansexual").getString()
    ),
    TRANS_TEXT("trans",
            Component.translatable("weaversparadise.dye_text.trans").getString()
    ),
    POLYCHROMATIC_TEXT("polychromatic",
            Component.translatable("weaversparadise.dye_text.polychromatic").getString()
    ),
    INVISIBLE_TEXT("invisible",
            Component.translatable("weaversparadise.dye_text.invisible").getString()
    ),
    STATIC_TEXT("static",
            Component.translatable("weaversparadise.dye_text.static").getString()
    ),
    CRYSTAL_TEXT("crystal",
            Component.translatable("weaversparadise.dye_text.crystal").getString()
    );

    private static final Map<String, WeaversParadiseDyeTextHandler> HANDLER_MAP =
            Stream.of(values()).collect(Collectors.toMap(WeaversParadiseDyeTextHandler::getName, e -> e));

    private final String name;
    private final String text;

    WeaversParadiseDyeTextHandler(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public static WeaversParadiseDyeTextHandler getOrDefault(String name) {
        return HANDLER_MAP.getOrDefault(name, DEFAULT_TEXT);
    }
}
