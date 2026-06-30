package xox.labvorty.weaversparadise.init;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xox.labvorty.weaversparadise.data.texture.*;
import xox.labvorty.weaversparadise.data.tooltip_components.helper.BuiltInDyeTypes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseInitialization {
    @SubscribeEvent
    public static void initializeCommon(FMLClientSetupEvent event) {
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
