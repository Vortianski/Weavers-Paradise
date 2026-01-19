package xox.labvorty.weaversparadise.renderers;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterRenderBuffersEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WeaversParadiseRegisterRenderers {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("weavparadise", "textures/entities/thighhighsbasetexture.png");

    @SubscribeEvent
    public static void init(RegisterRenderBuffersEvent event) {
        event.registerRenderBuffer(WeaversParadiseRenderTypes.getVoidArmor(TEXTURE, TEXTURE, TEXTURE));
        event.registerRenderBuffer(WeaversParadiseRenderTypes.getGlintyInstance());
    }
}
