package xox.labvorty.weaversparadise.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xox.labvorty.weaversparadise.init.WeaversParadiseCustomShaders;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShaderData {
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) return;

        if (!Minecraft.getInstance().isPaused() ) {
            ++WeaversParadiseCustomShaders.renderTime;
        }
    }

    @SubscribeEvent
    public static void renderTick(RenderLevelStageEvent event) {
        if (!Minecraft.getInstance().isPaused()) {
            WeaversParadiseCustomShaders.renderFrame = event.getPartialTick();
        }
    }
}
