package xox.labvorty.weaversparadise.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class WeaversParadiseAttributeEvents {
    @SubscribeEvent
    public static void entityHurt(LivingDamageEvent.Post event) {
        Entity source = event.getSource().getEntity();
        Entity receiver = event.getEntity();

        if (source instanceof Player player) {

        }
    }
}
