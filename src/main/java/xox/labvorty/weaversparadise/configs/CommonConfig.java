package xox.labvorty.weaversparadise.configs;

public class CommonConfig {
    private static final WPConfig CONFIG = WPConfig.create("weaversparadise-common");

    public static final WPConfig.Value<Boolean> ITEM_SPECIAL_ABILITIES =
            CONFIG.registerBool("itemAbilities", true);

    public static final WPConfig.Value<Double> ARMOR_LOOTBOX_CHANCE =
            CONFIG.registerDouble("armorLootboxChance", 0.05, 0.0, 1.0);

    public static final WPConfig.Value<Double> PLUSHIE_CHANCE =
            CONFIG.registerDouble("plushieChance", 0.05, 0.0, 1.0);
}
