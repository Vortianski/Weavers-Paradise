package xox.labvorty.weaversparadise.init;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import xox.labvorty.weaversparadise.models.*;
import xox.labvorty.weaversparadise.renderers.curio.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseCurioRenderers {
    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ThighHighsModel.LAYER_LOCATION, ThighHighsModel::createBodyLayer);
        event.registerLayerDefinition(AstolfoArmorModel.LAYER_LOCATION, AstolfoArmorModel::createBodyLayer);
        event.registerLayerDefinition(BridgetClothingModel.LAYER_LOCATION, BridgetClothingModel::createBodyLayer);
        event.registerLayerDefinition(FelixClothingModel.LAYER_LOCATION, FelixClothingModel::createBodyLayer);
        event.registerLayerDefinition(HandWarmersModel.LAYER_LOCATION, HandWarmersModel::createBodyLayer);
        event.registerLayerDefinition(UpperWearModel.LAYER_LOCATION, UpperWearModel::createBodyLayer);
        event.registerLayerDefinition(SlimPlushieModel.LAYER_LOCATION, SlimPlushieModel::createBodyLayer);
        event.registerLayerDefinition(WidePlushieModel.LAYER_LOCATION, WidePlushieModel::createBodyLayer);
        event.registerLayerDefinition(ChokerModel.LAYER_LOCATION, ChokerModel::createBodyLayer);
        event.registerLayerDefinition(BellModel.LAYER_LOCATION, BellModel::createBodyLayer);
        event.registerLayerDefinition(PantsModel.LAYER_LOCATION, PantsModel::createBodyLayer);
        event.registerLayerDefinition(BasicRingModel.LAYER_LOCATION, BasicRingModel::createBodyLayer);
        event.registerLayerDefinition(CatRingModel.LAYER_LOCATION, CatRingModel::createBodyLayer);
        event.registerLayerDefinition(HeartModel.LAYER_LOCATION, HeartModel::createBodyLayer);
        event.registerLayerDefinition(BasicPlateModel.LAYER_LOCATION, BasicPlateModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_COTTON.get(), ThighHighsCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_WOOL.get(), ThighHighsWoolRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.THIGH_HIGHS_SILK.get(), ThighHighsSilkRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_COTTON.get(), HandWarmersCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_WOOL.get(), HandWarmersWoolRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HAND_WARMERS_SILK.get(), HandWarmersSilkRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_COTTON.get(), ShirtCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SHIRT_SILK.get(), ShirtSilkRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.SWEATER_WOOL.get(), SweaterWoolRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_COTTON.get(), PantsCottonRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_SILK.get(), PantsSilkRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PANTS_JEANS.get(), PantsJeansRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.CHOKER.get(), ChokerRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.BELL.get(), BellRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.RING.get(), RingRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.CAT_RING.get(), CatRingRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.HEART.get(), HeartRenderer::new);
        CuriosRendererRegistry.register(WeaversParadiseItems.PLATE.get(), PlateRenderer::new);

        CuriosRendererRegistry.register(WeaversParadiseItems.LEATHER_GLOVES.get(), LeatherGlovesRenderer::new);
    }
}
