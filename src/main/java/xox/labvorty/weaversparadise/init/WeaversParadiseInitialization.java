package xox.labvorty.weaversparadise.init;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import xox.labvorty.weaversparadise.data.texture.*;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.BuiltInDyeTypes;

@EventBusSubscriber
public class WeaversParadiseInitialization {
    @SubscribeEvent
    public static void onCommon(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BuiltInDyeTypes.register();
            ThighHighsTextures.register();
            HandWarmersTextures.register();
            ShirtTextures.register();
            ShirtOpenTextures.register();
            CapeTextures.register();
            PantsTextures.register();
        });
    }
}
