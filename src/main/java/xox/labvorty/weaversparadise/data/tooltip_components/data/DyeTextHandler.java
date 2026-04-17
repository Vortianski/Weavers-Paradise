package xox.labvorty.weaversparadise.data.tooltip_components.data;

import net.minecraft.network.chat.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DyeTextHandler {
    DEFAULT("default"),
    RAINBOW("rainbow"),
    ENDER("ender"),
    SCULK("sculk"),
    BIOME("biome"),
    GLOWSTONE("glowstone"),
    REDSTONE("redstone"),
    LAMP("lamp"),
    SPEED("speed"),
    HEIGHT_BEDROCK("height_bedrock"),
    HEIGHT_SEA("height_sea"),
    PRIDE("pride"),
    ASEXUAL("asexual"),
    AROMANTIC("aromantic"),
    AROACE("aroace"),
    AGENDER("agender"),
    BISEXUAL("bisexual"),
    DEMIBOY("demiboy"),
    DEMIGENDER("demigender"),
    DEMIGIRL("demigirl"),
    GAY("gay"),
    GENDERFLUID("genderfluid"),
    GENDERQUEER("genderqueer"),
    INTERSEX("intersex"),
    LESBIAN("lesbian"),
    NONBINARY("nonbinary"),
    PANSEXUAL("pansexual"),
    TRANS("trans"),
    POLYCHROMATIC("polychromatic"),
    INVISIBLE("invisible"),
    STATIC("static"),
    CRYSTAL("crystal"),
    NEGATIVE("negative"),
    TRUE_NEGATIVE("true_negative"),
    NEBULA("nebula"),
    COLORED_SCULK("colored_sculk", Component.translatable("weaversparadise.dye_text.sculk")),
    HUNGER("hunger"),
    HEALTH("health"),
    DAY_TIME("day_time"),
    COLORED_DAY_TIME("colored_day_time", Component.translatable("weaversparadise.dye_text.day_time"));

    private final String name;
    private final Component text;

    private static final Map<String, DyeTextHandler> HANDLER_MAP = Stream.of(values()).collect(Collectors.toMap(DyeTextHandler::getName, e -> e));

    DyeTextHandler(
            String name,
            Component text
    ) {
        this.name = name;
        this.text = text;
    }

    DyeTextHandler(
            String name
    ) {
        this(
                name,
                Component.translatable("weaversparadise.dye_text." + name)
        );
    }

    public String getName() {
        return name;
    }

    public Component getText() {
        return text;
    }

    public static DyeTextHandler getOrDefault(String name) {
        return HANDLER_MAP.getOrDefault(name, DEFAULT);
    }
}
