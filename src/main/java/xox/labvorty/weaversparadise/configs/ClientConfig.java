package xox.labvorty.weaversparadise.configs;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

public class ClientConfig {
    private static final WPConfig CONFIG = WPConfig.create("weaversparadise-client");

    public static final WPConfig.Value<Boolean> PATCHOULI_WARNING =
            CONFIG.registerBool("patchouliWarning", true);

    public static final WPConfig.Value<Boolean> VERSION_WARNING =
            CONFIG.registerBool("versionWarning", true);

    public static final WPConfig.Value<List<String>> THIGH_HIGHS_RESTRICTOR =
            CONFIG.registerStringList("thighHighsRestrictor", cosmetics());

    public static final WPConfig.Value<List<String>> HAND_WARMERS_RESTRICTOR =
            CONFIG.registerStringList("handWarmersRestrictor", cosmetics());

    public static final WPConfig.Value<List<String>> PANTS_RESTRICTOR =
            CONFIG.registerStringList("pantsRestrictor", cosmetics());

    public static final WPConfig.Value<List<String>> SHIRT_RESTRICTOR =
            CONFIG.registerStringList("shirtRestrictor", cosmetics());

    public static final WPConfig.Value<List<String>> CHOKER_RESTRICTOR =
            CONFIG.registerStringList("chokerRestrictor", cosmetics());

    public static final WPConfig.Value<List<String>> HAT_RESTRICTOR =
            CONFIG.registerStringList("hatRestrictor", cosmetics());

    public static final WPConfig.Value<List<String>> SKIRT_RESTRICTOR =
            CONFIG.registerStringList("skirtRestrictor", cosmetics());

    public static final WPConfig.Value<Boolean> FULL_ARMOR =
            CONFIG.registerBool("fullArmor", false);

    public static boolean containsItem(List<? extends String> list, Item item) {
        for (String id : list) {
            Item i = BuiltInRegistries.ITEM.get(ResourceLocation.parse(id));
            if (i == item) return true;
        }

        return false;
    }

    private static List<String> cosmetics() {
        return List.of(
                "weaversparadise:astolfo_cosmetics", "weaversparadise:bridget_cosmetics",
                "weaversparadise:expie_cosmetics", "weaversparadise:felix_cosmetics",
                "weaversparadise:gabriel_cosmetics", "weaversparadise:gaster_cosmetics",
                "weaversparadise:giselle_cosmetics", "weaversparadise:griffith_cosmetics",
                "weaversparadise:jaya_utomo_cosmetics", "weaversparadise:mikkela_cosmetics",
                "weaversparadise:minos_prime_cosmetics", "weaversparadise:ralsei_cosmetics",
                "weaversparadise:niko_cosmetics");
    }
}
