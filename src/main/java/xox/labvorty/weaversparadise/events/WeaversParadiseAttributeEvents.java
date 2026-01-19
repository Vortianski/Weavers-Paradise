package xox.labvorty.weaversparadise.events;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import xox.labvorty.weaversparadise.data.WeaversParadiseStatHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseModAttributes;

@EventBusSubscriber
public class WeaversParadiseAttributeEvents {
    @SubscribeEvent
    public static void entityHurt(LivingDamageEvent.Post event) {
        Entity source = event.getSource().getEntity();
        Entity receiver = event.getEntity();

        if (source instanceof Player player) {
            double lifesteal = player.getAttribute(WeaversParadiseModAttributes.LIFESTEAL).getValue();
            Pair<String, Double> ability = WeaversParadiseStatHandler.calculateStaticType((LivingEntity)player);

            if (lifesteal > 0 && ability.getFirst().equals("cotton") && ability.getSecond() > 0) {
                float amount = event.getNewDamage();
                float healAmount = (float)(amount * lifesteal);
                player.heal(healAmount);
            }
        }
    }
}
