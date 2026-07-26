package xox.labvorty.weaversparadise.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import xox.labvorty.weaversparadise.data.listeners.SculkPlayerListenerManager;
import xox.labvorty.weaversparadise.init.WeaversParadiseAttachmentTypes;
import xox.labvorty.weaversparadise.init.WeaversParadiseMobEffects;

@EventBusSubscriber
public class TickEvents {
    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            SculkPlayerListenerManager.tick(serverPlayer);

            int pulse = player.getData(WeaversParadiseAttachmentTypes.SCULK_PULSE);
            if (pulse > 0) {
                player.setData(WeaversParadiseAttachmentTypes.SCULK_PULSE, pulse - 1);
            }
        }
    }

    @SubscribeEvent
    public static void entityTick(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();

        if (entity instanceof LivingEntity livingEntity && !livingEntity.level().isClientSide) {
            livingEntity.setData(WeaversParadiseAttachmentTypes.CHROMATIC_SHIFT_ACTIVE.get(), livingEntity.hasEffect(WeaversParadiseMobEffects.CHROMATIC_SHIFT));
        }
    }
}
