package xox.labvorty.weaversparadise.init;

import xox.labvorty.weaversparadise.data.initializers.BuiltInStencils;
import xox.labvorty.weaversparadise.data.texture.StencilRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.data.initializers.BuiltInDyeTypes;
import xox.labvorty.weaversparadise.data.initializers.BuiltInTextures;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.events.WPReloadEvents;

public class WeaversParadiseInitialization {

    public static void reloadCommon() {
        StencilRegistry.clear();
        BuiltInStencils.register();
        WPReloadEvents.postCommon();
    }

    public static void reloadClient() {
        DyeTypeRegistry.clear();
        TextureRegistry.clear();
        BuiltInDyeTypes.register();
        BuiltInTextures.register();
        WPReloadEvents.postClient();
    }
}
