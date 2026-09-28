package xox.labvorty.weaversparadise.configs;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class ClientConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> THIGH_HIGHS_RESTRICTOR;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HAND_WARMERS_RESTRICTOR;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> PANTS_RESTRICTOR;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> SHIRT_RESTRICTOR;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CHOKER_RESTRICTOR;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> HAT_RESTRICTOR;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> SKIRT_RESTRICTOR;
    public static final ForgeConfigSpec.ConfigValue<Boolean> PATCHOULI_WARNING;
    public static final ForgeConfigSpec.BooleanValue FULL_ARMOR;

    static {
        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

        BUILDER.push("client_settings");

        THIGH_HIGHS_RESTRICTOR = BUILDER
                .comment("Items that will prevent Thigh Highs from rendering")
                .defineListAllowEmpty(
                        "thighHighsRestrictors",
                        List.of(
                                ""
                        ),
                        ClientConfig::validateItemName
                );

        HAND_WARMERS_RESTRICTOR = BUILDER
                .comment("Items that will prevent Hand Warmers from rendering")
                .defineListAllowEmpty(
                        "handWarmersRestrictors",
                        List.of(
                                ""
                        ),
                        ClientConfig::validateItemName
                );

        PANTS_RESTRICTOR = BUILDER
                .comment("Items that will prevent Pants from rendering")
                .defineListAllowEmpty(
                        "pantsRestrictors",
                        List.of(
                                ""
                        ),
                        ClientConfig::validateItemName
                );

        SHIRT_RESTRICTOR = BUILDER
                .comment("Items that will prevent Shirt from rendering")
                .defineListAllowEmpty(
                        "shirtRestrictors",
                        List.of(
                                "weaversparadise:astolfo_armor_chestplate",
                                "weaversparadise:bridget_armor_jacket",
                                "weaversparadise:felix_armor_jacket"
                        ),
                        ClientConfig::validateItemName
                );

        CHOKER_RESTRICTOR = BUILDER
                .comment("Items that will prevent Choker and it's trinkets from rendering")
                .defineListAllowEmpty(
                        "chokerRestrictors",
                        List.of(
                                "weaversparadise:felix_armor_jacket"
                        ),
                        ClientConfig::validateItemName
                );

        HAT_RESTRICTOR = BUILDER
                .comment("Items that will prevent Hats from rendering")
                .defineListAllowEmpty(
                        "hatsRestrictors",
                        List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"),
                        ClientConfig::validateItemName
                );

        SKIRT_RESTRICTOR = BUILDER
                .comment("Items that will prevent Skirts from rendering")
                .defineListAllowEmpty(
                        "skirtsRestrictors",
                        List.of("weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics", "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics", "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics", "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics", "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics", "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics", "weaversparadise:niko_cosmetics"),
                        ClientConfig::validateItemName
                );

        PATCHOULI_WARNING = BUILDER
                .comment("Display a message if Patchouli is not installed when joining the game")
                .define("patchouliWarning", true);

        FULL_ARMOR = BUILDER
                .comment("Should cosplay armor render fully, or use equipped armor to determine visible pieces")
                .define("fullArmor", false);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    private static boolean validateItemName(final Object obj) {
        if (!(obj instanceof String itemName)) return false;

        if (itemName.isBlank()) return false;

        ResourceLocation rl;
        try {
            rl = ResourceLocation.parse(itemName);
        } catch (Exception e) {
            return false;
        }

        return BuiltInRegistries.ITEM.containsKey(rl);
    }

    public static boolean containsItem(List<? extends String> list, Item item) {
        for (String id : list) {
            Item i = BuiltInRegistries.ITEM.get(ResourceLocation.parse(id));
            if (i == item) return true;
        }

        return false;
    }
}
