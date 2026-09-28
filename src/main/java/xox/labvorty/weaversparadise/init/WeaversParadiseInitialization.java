package xox.labvorty.weaversparadise.init;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xox.labvorty.weaversparadise.data.initializers.BuiltInDyeTypes;
import xox.labvorty.weaversparadise.data.initializers.BuiltInStencils;
import xox.labvorty.weaversparadise.data.initializers.BuiltInTextures;
import xox.labvorty.weaversparadise.data.recipe.Compostables;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseInitialization {
    @SubscribeEvent
    public static void onCommon(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BuiltInStencils.register();
            Compostables.register();
        });
    }

    @SubscribeEvent
    public static void onClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BuiltInDyeTypes.register();
            BuiltInTextures.register();
        });
    }
}
