package xox.labvorty.weaversparadise.init;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import xox.labvorty.weaversparadise.blocks.entities.renderer.PlushieBlockEntityRenderer;
import xox.labvorty.weaversparadise.renderers.entity.HangingFlagEntityRenderer;

@EventBusSubscriber
public class WeaversParadiseRenderers {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY.get(), HangingFlagEntityRenderer::new);

        event.registerBlockEntityRenderer(WeaversParadiseBlockEntities.PLUSHIE_BE.get(), PlushieBlockEntityRenderer::new);
    }
}
