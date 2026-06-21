package xox.labvorty.weaversparadise.configs;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class ClientConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<List<? extends String>> THIGH_HIGHS_RESTRICTOR = BUILDER
            .comment("Items that will prevent Thigh Highs from rendering")
            .defineListAllowEmpty(
                    "thighHighsRestrictors",
                    List.of(),
                    () -> "", ClientConfig::validateItemName
            );

    public static final ModConfigSpec.ConfigValue<List<? extends String>> HAND_WARMERS_RESTRICTOR = BUILDER
            .comment("Items that will prevent Hand Warmers from rendering")
            .defineListAllowEmpty(
                    "handWarmersRestrictors",
                    List.of(),
                    () -> "", ClientConfig::validateItemName
            );

    public static final ModConfigSpec.ConfigValue<List<? extends String>> PANTS_RESTRICTOR = BUILDER
            .comment("Items that will prevent Pants from rendering")
            .defineListAllowEmpty(
                    "pantsRestrictors",
                    List.of(),
                    () -> "", ClientConfig::validateItemName
            );

    public static final ModConfigSpec.ConfigValue<List<? extends String>> SHIRT_RESTRICTOR = BUILDER
            .comment("Items that will prevent Shirt from rendering")
            .defineListAllowEmpty(
                    "shirtRestrictors",
                    List.of(
                            "weaversparadise:astolfo_armor_chestplate",
                            "weaversparadise:bridget_armor_jacket",
                            "weaversparadise:felix_armor_jacket"
                    ),
                    () -> "", ClientConfig::validateItemName
            );

    public static final ModConfigSpec.ConfigValue<List<? extends String>> CHOKER_RESTRICTOR = BUILDER
            .comment("Items that will prevent Choker and it's trinkets from rendering")
            .defineListAllowEmpty(
                    "chokerRestrictors",
                    List.of(
                            "weaversparadise:felix_armor_jacket"
                    ),
                    () -> "", ClientConfig::validateItemName
            );

    public static final ModConfigSpec SPEC = BUILDER.build();

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
