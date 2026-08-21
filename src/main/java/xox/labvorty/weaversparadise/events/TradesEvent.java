package xox.labvorty.weaversparadise.events;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

@EventBusSubscriber
public class TradesEvent {
    @SubscribeEvent
    public static void wanderingTrader(WandererTradesEvent event) {
        event.getRareTrades().add(
                new VillagerTrades.ItemsForEmeralds(
                        WeaversParadiseItems.ARMOR_LOOTBOX.get(),
                        32,
                        1,
                        1,
                        0
                )
        );
    }
}
