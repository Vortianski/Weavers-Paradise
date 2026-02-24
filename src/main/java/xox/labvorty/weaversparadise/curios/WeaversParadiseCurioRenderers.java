package xox.labvorty.weaversparadise.curios;

import xox.labvorty.weaversparadise.data.PantsRenderer;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.model.*;
import xox.labvorty.weaversparadise.renderers.models.SweaterWoolRenderer;
import xox.labvorty.weaversparadise.renderers.models.*;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber
public class WeaversParadiseCurioRenderers {
    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(WeaversParadiseMobLayers.THIGH_HIGHS, ThighHighsModel::createBodyLayer);
        evt.registerLayerDefinition(Modelastolfo_armor.LAYER_LOCATION, Modelastolfo_armor::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.HAND_WARMERS, ModelHandWarmers::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.UPPER_WEAR, ModelUpperWear::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.SLIM_PLUSHIE, SlimPlushieModel::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.WIDE_PLUSHIE, WidePlushieModel::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.CHOKER, ModelChoker::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.BELL, BellModel::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.PANTS, PantsModel::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.RING, BasicRingModel::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.CAT_RING, CatRingModel::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.HEART, HeartModel::createBodyLayer);
        evt.registerLayerDefinition(WeaversParadiseMobLayers.PLATE, BasicPlateModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent evt) {
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS.get(), ThighHighsRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_COTTON.get(), ThighsHighsCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_WOOL.get(), ThighsHighsWoolRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_SILK.get(), ThighsHighsSilkRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.LEATHER_GLOVES.get(), LeatherGlovesRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.CHOKER.get(), ChokerRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.BELL.get(), BellRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.RING.get(), RingRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.CAT_RING.get(), CatRingRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HEART.get(), HeartRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PLATE.get(), PlateRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_COTTON.get(), HandWarmersCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_SILK.get(), HandWarmersSilkRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_WOOL.get(), HandWarmersWoolRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_COTTON.get(), ShirtCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_SILK.get(), ShirtSilkRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SWEATER_WOOL.get(), SweaterWoolRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_JEANS.get(), PantsJeansRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_COTTON.get(), PantsCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_SILK.get(), PantsSilkRenderer::new);
    }
}
