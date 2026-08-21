package xox.labvorty.weaversparadise.init;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import xox.labvorty.weaversparadise.data.initializers.BuiltInDyeTypes;
import xox.labvorty.weaversparadise.data.initializers.BuiltInStencils;
import xox.labvorty.weaversparadise.data.initializers.BuiltInTextures;
import xox.labvorty.weaversparadise.data.texture.StencilRegistry;
import xox.labvorty.weaversparadise.data.texture.TextureRegistry;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTypeRegistry;
import xox.labvorty.weaversparadise.events.ReloadEvent;

@EventBusSubscriber
public class WeaversParadiseInitialization {
    @SubscribeEvent
    public static void onCommon(FMLCommonSetupEvent event) {
        event.enqueueWork(WeaversParadiseInitialization::reloadCommon);
    }

    @SubscribeEvent
    public static void onClient(FMLClientSetupEvent event) {
        event.enqueueWork(WeaversParadiseInitialization::reloadClient);
    }

    public static void reloadCommon() {
        StencilRegistry.clear();
        BuiltInStencils.register();
        NeoForge.EVENT_BUS.post(new ReloadEvent.Common());
    }

    public static void reloadClient() {
        DyeTypeRegistry.clear();
        TextureRegistry.clear();
        BuiltInDyeTypes.register();
        BuiltInTextures.register();
        NeoForge.EVENT_BUS.post(new ReloadEvent.Client());
    }
}