package xox.labvorty.weaversparadise.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.WeaversParadise;

public class WeaversParadiseDataComponents {
    public static final DataComponentType<String> GROUP_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "group_component"),
            DataComponentType.<String>builder()
                    .persistent(Codec.STRING)
                    .build()
    );

    public static final DataComponentType<String> GROUP_ITEM_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "group_item_component"),
            DataComponentType.<String>builder()
                    .persistent(Codec.STRING)
                    .build()
    );

    public static void touch() {
    }
}
