package xox.labvorty.weaversparadise.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.network.ChromaticShiftSyncPacket;
import xox.labvorty.weaversparadise.data.network.SculkPulseSyncPacket;
import xox.labvorty.weaversparadise.init.WeaversParadiseCapabilities;

@Mod.EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void startTracking(PlayerEvent.StartTracking event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getTarget() instanceof LivingEntity livingEntity) {
            livingEntity.getCapability(WeaversParadiseCapabilities.CHROMATIC_SHIFT_DATA).ifPresent(data -> {
                WeaversParadiseMod.PACKET_HANDLER.send(
                        PacketDistributor.PLAYER.with(() -> player),
                        new ChromaticShiftSyncPacket(livingEntity.getId(), data.isActive())
                );
            });

            livingEntity.getCapability(WeaversParadiseCapabilities.SCULK_PULSE_DATA).ifPresent(data -> {
                WeaversParadiseMod.PACKET_HANDLER.send(
                        PacketDistributor.PLAYER.with(() -> player),
                        new SculkPulseSyncPacket(livingEntity.getId(), data.getPulse())
                );
            });
        }
    }
}
