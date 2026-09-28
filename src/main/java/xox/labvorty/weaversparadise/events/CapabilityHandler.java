package xox.labvorty.weaversparadise.events;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.capabilities.ChromaticShiftData;
import xox.labvorty.weaversparadise.data.capabilities.SculkPulseData;

@Mod.EventBusSubscriber(modid = WeaversParadiseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityHandler {
    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ChromaticShiftData.class);
        event.register(SculkPulseData.class);
    }
}