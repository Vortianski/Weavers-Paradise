package xox.labvorty.weaversparadise.api;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import xox.labvorty.weaversparadise.data.texture.ItemTexture;
import xox.labvorty.weaversparadise.data.texture.StencilRegistry;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeIcon;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeData;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeDataColor;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.DyeInstance;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.RenderData;

import java.util.List;
import java.util.function.Function;

/**
 * Public addon-facing API for Weavers Paradise.
 * Classes outside this package may change without notice, only build against this facade.
 */
public final class WeaversParadiseAPI {
    private WeaversParadiseAPI() {}

    /**
     * Client Module
     * Registry - recommended to call only on {@link xox.labvorty.weaversparadise.events.ReloadEvent.Client}
     */
    public static class Client {
        public static void registerDyeType(
                String name, DyeIcon dyeIcon, Component component,
                Function<DyeData, MutableComponent> nameParser,
                Function<DyeDataColor, Integer> colorParser
        ) {
            DyeTypeRegistry.registerDyeType(name, dyeIcon, component, nameParser, colorParser);
        }

        public static void registerDyeType(
                String name, DyeIcon dyeIcon, Component component,
                Function<DyeData, MutableComponent> nameParser,
                Function<DyeDataColor, Integer> colorParser,
                Function<RenderData, RenderType> renderTypeParser
        ) {
            DyeTypeRegistry.registerDyeType(name, dyeIcon, component, nameParser, colorParser, renderTypeParser);
        }

        public static DyeInstance getDyeType(String name) {
            return DyeTypeRegistry.getDyeType(name);
        }

        public static void registerTexture(
                String clothingType, String stencil, String material,
                ResourceLocation textureOne, ResourceLocation textureTwo,
                boolean renderType
        ) {
            TextureRegistry.registerTexture(clothingType, stencil, material, textureOne, textureTwo, renderType);
        }

        public static ItemTexture findTexture(String clothingType, String stencil, String material) {
            return TextureRegistry.find(clothingType, stencil, material);
        }
    }

    /**
     * Common Module
     * Registry - recommended to call only on {@link xox.labvorty.weaversparadise.events.ReloadEvent.Common}
     */
    public static class Common {
        public static void registerStencil(String name, Item item) {
            StencilRegistry.registerStencil(name, item);
        }

        public static List<StencilRegistry.Stencil> getStencilsForType(String name) {
            return StencilRegistry.getStencilsForType(name);
        }

        public static boolean acceptsStencil(String name, Item item) {
            return StencilRegistry.acceptsStencil(name, item);
        }
    }
}