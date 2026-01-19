package xox.labvorty.weaversparadise.data;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WeaversParadiseDyeHandler {
    WHITE("White", Items.WHITE_DYE,249, 255, 254), //#F9FFFE
    LIGHT_GRAY("Light Gray", Items.LIGHT_GRAY_DYE, 157, 157, 151), //#9D9D97
    GRAY("Gray", Items.GRAY_DYE, 71, 79, 82), //#474F52
    BLACK("Black", Items.BLACK_DYE, 29, 29, 33), //#1D1D21
    BROWN("Brown", Items.BROWN_DYE, 131, 84, 50), //#835432
    RED("Red", Items.RED_DYE, 176, 46, 38), //#B02E26
    ORANGE("Orange", Items.ORANGE_DYE, 249, 128, 29), //#F9801D
    YELLOW("Yellow", Items.YELLOW_DYE, 254, 216, 61), //#FED83D
    LIME("Lime", Items.LIME_DYE, 128, 199, 31), //#80C71F
    GREEN("Green", Items.GREEN_DYE,94, 124, 22), //#5E7C16
    CYAN("Cyan", Items.CYAN_DYE, 22, 156, 156), //#169C9C
    LIGHT_BLUE("Light Blue", Items.LIGHT_BLUE_DYE, 58, 179, 218), //#3AB3DA
    BLUE("Blue", Items.BLUE_DYE, 60, 68, 170), //#3C44AA
    PURPLE("Purple", Items.PURPLE_DYE, 137, 50, 184), //#8932B8
    MAGENTA("Magenta", Items.MAGENTA_DYE, 199, 78, 189), //#C74EBD
    PINK("Pink", Items.PINK_DYE, 243, 139, 170); //#F38BAA


    private final String name;
    private final int red;
    private final int green;
    private final int blue;
    private final Item instance;

    private static final Map<Item, WeaversParadiseDyeHandler> HANDLER_MAP =
            Stream.of(values()).collect(Collectors.toMap(WeaversParadiseDyeHandler::getInstance, e -> e));

    WeaversParadiseDyeHandler(String name, Item instance, int red, int green, int blue) {
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.instance = instance;
    }

    public String getName() {
        return name;
    }

    public Item getInstance() {
        return instance;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getColor() {
        return 255 << 24 | red << 16 | green << 8 | blue;
    }

    public static WeaversParadiseDyeHandler getOrDefault(Item item) {
        return HANDLER_MAP.getOrDefault(item, WHITE);
    }
}
