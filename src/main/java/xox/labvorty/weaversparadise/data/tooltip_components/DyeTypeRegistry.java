package xox.labvorty.weaversparadise.data.tooltip_components;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import oshi.util.tuples.Pair;

import java.util.HashMap;
import java.util.Map;

public class DyeTypeRegistry {
    private static final Map<String, Pair<DyeIcon, Component>> dyeType = new HashMap<>();
    private static final Pair<DyeIcon, Component> DEFAULT = new Pair<>(
            new DyeIcon(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/tooltips/flag_empty.png")),
            Component.translatable("weaversparadise.dye_text.default")
    );

    public static void registerDyeType(
            String name,
            DyeIcon dyeIcon,
            Component component
    ) {
        dyeType.put(
                name,
                new Pair<>(
                        dyeIcon,
                        component
                )
        );
    }

    public static Pair<DyeIcon, Component> getDyeType(String s) {
        return dyeType.getOrDefault(s, DEFAULT);
    }
}
