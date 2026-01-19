package xox.labvorty.weaversparadise.init;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import xox.labvorty.weaversparadise.tooltips.BottledDyeClientTooltipComponent;
import xox.labvorty.weaversparadise.tooltips.BottledDyeTooltipComponent;
import xox.labvorty.weaversparadise.tooltips.ImageTooltipComponent;
import xox.labvorty.weaversparadise.tooltips.ImageClientTooltipComponent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WeaversParadiseClientRegisterEvents {
    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ImageTooltipComponent.class, data -> new ImageClientTooltipComponent(
                data.getTextures(),
                data.getTexture("left", 1),
                data.getText("left", 1),
                data.getType("left", 1),
                data.getLightValue("left", 1),
                data.getPrimaryColor("left", 1),
                data.getSecondaryColor("left", 1),
                data.getTexture("left", 2),
                data.getText("left", 2),
                data.getType("left", 2),
                data.getLightValue("left", 2),
                data.getPrimaryColor("left", 2),
                data.getSecondaryColor("left", 2),
                data.getTexture("right", 1),
                data.getText("right", 1),
                data.getType("right", 1),
                data.getLightValue("right", 1),
                data.getPrimaryColor("right", 1),
                data.getSecondaryColor("right", 1),
                data.getTexture("right", 2),
                data.getText("right", 2),
                data.getType("right", 2),
                data.getLightValue("right", 2),
                data.getPrimaryColor("right", 2),
                data.getSecondaryColor("right", 2)
        ));


        event.register(BottledDyeTooltipComponent.class, data -> new BottledDyeClientTooltipComponent(data.getTexture(), data.getText(), data.getType(), data.getLightValue(), data.getPrimaryColor(), data.getSecondaryColor()));
    }
}

