package xox.labvorty.weaversparadise.curios;

import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.model.ModelHandWarmers;
import xox.labvorty.weaversparadise.model.ModelUpperWear;
import xox.labvorty.weaversparadise.model.Modelastolfo_armor;
import xox.labvorty.weaversparadise.renderers.SweaterWoolRenderer;
import xox.labvorty.weaversparadise.renderers.models.*;
import xox.labvorty.weaversparadise.model.ThighHighsModel;
import xox.labvorty.weaversparadise.curios.WeaversParadiseMobLayers;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class WeaversParadiseCurioRenderers {
    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(WeaversParadiseMobLayers.THIGH_HIGHS, ThighHighsModel::createBodyLayer);
        evt.registerLayerDefinition(Modelastolfo_armor.LAYER_LOCATION, Modelastolfo_armor::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.HAND_WARMERS, ModelHandWarmers::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.UPPER_WEAR, ModelUpperWear::createBodyLayer);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent evt) {
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS.get(), ThighHighsRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_COTTON.get(), ThighsHighsCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_WOOL.get(), ThighsHighsWoolRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_SILK.get(), ThighsHighsSilkRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.LEATHER_GLOVES.get(), LeatherGlovesRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_COTTON.get(), HandWarmersCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_SILK.get(), HandWarmersSilkRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_WOOL.get(), HandWarmersWoolRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_COTTON.get(), ShirtCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_SILK.get(), ShirtSilkRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SWEATER_WOOL.get(), SweaterWoolRenderer::new);
    }
}
