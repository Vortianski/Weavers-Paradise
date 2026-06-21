package xox.labvorty.weaversparadise.init;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import xox.labvorty.weaversparadise.data.tooltip_components.ClothingTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.DyeTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.QualityTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.ClothingClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.DyeClientTooltipComponent;
import xox.labvorty.weaversparadise.data.tooltip_components.client.QualityClientTooltipComponent;

@EventBusSubscriber(value = Dist.CLIENT)
public class WeaversParadiseClientRegisterEvents {
    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ClothingTooltipComponent.class, data -> new ClothingClientTooltipComponent(
                data.getQualityTextures(),
                data.getEntries()
        ));

        event.register(DyeTooltipComponent.class, data -> new DyeClientTooltipComponent(data.getDyeIcon(), data.getText(), data.getType(), data.getLightValue(), data.getPrimaryColor(), data.getSecondaryColor()));
        event.register(QualityTooltipComponent.class, data -> new QualityClientTooltipComponent(data.getTextures()));
    }
}

