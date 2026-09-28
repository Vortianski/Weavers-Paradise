package xox.labvorty.weaversparadise.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xox.labvorty.weaversparadise.blocks.entities.renderer.BlahajBlockEntityRenderer;
import xox.labvorty.weaversparadise.blocks.entities.renderer.PlushieBlockEntityRenderer;
import xox.labvorty.weaversparadise.renderers.entity.HangingFlagEntityRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WeaversParadiseRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WeaversParadiseEntityTypes.HANGING_FLAG_ENTITY.get(), HangingFlagEntityRenderer::new);

        event.registerBlockEntityRenderer(WeaversParadiseBlockEntities.PLUSHIE_BE.get(), PlushieBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(WeaversParadiseBlockEntities.BLAHAJ_BE.get(), BlahajBlockEntityRenderer::new);
    }
}
