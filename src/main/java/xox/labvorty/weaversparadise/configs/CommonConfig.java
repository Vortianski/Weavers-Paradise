package xox.labvorty.weaversparadise.configs;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ITEM_SPECIAL_ABILITIES = BUILDER
            .comment("Whether items have special abilities when enchanted")
            .define("itemAbilities", true);

    public static final ModConfigSpec.DoubleValue ARMOR_LOOTBOX_CHANCE = BUILDER
            .comment("Chance for armor lootbox to spawn")
            .defineInRange(
                    "armorLootboxChance",
                    0.05,
                    0,
                    1
            );

    public static final ModConfigSpec.DoubleValue PLUSHIE_CHANCE = BUILDER
            .comment("Chance for plushie to spawn")
            .defineInRange(
                    "plushieChance",
                    0.05,
                    0,
                    1
            );

    public static final ModConfigSpec SPEC = BUILDER.build();
}
