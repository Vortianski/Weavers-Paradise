package xox.labvorty.weaversparadise.init;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.datagens.WeaversParadiseCuriosDataProvider;

@Mod.EventBusSubscriber(modid = WeaversParadiseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaversParadiseDatagens {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var provider = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new WeaversParadiseCuriosDataProvider(output, helper, provider));
    }
}
