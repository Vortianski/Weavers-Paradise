package xox.labvorty.weaversparadise.renderers;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import net.neoforged.bus.api.EventPriority;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WeaversParadiseRegisterCustomShaders {
    @SubscribeEvent
            (priority = EventPriority.HIGHEST)
    public static void init(RegisterShadersEvent event) {
        WeaversParadiseCustomShaders.onRegisterShaders(event);
    }
}