package xox.labvorty.weaversparadise.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue ITEM_SPECIAL_ABILITIES;

    static {
        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        BUILDER.push("common_settings");

        ITEM_SPECIAL_ABILITIES = BUILDER
                .comment("Whether items have special abilities when enchanted")
                .define("itemAbilities", true);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
