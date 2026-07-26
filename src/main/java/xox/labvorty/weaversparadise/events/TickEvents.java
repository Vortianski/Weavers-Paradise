package xox.labvorty.weaversparadise.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.capabilities.SculkPulseData;
import xox.labvorty.weaversparadise.data.network.ChromaticShiftSyncPacket;
import xox.labvorty.weaversparadise.data.network.RendererSyncMessage;
import xox.labvorty.weaversparadise.data.network.SculkPulseSyncPacket;
import xox.labvorty.weaversparadise.init.WeaversParadiseCapabilities;
import xox.labvorty.weaversparadise.init.WeaversParadiseMobEffects;

@Mod.EventBusSubscriber
public class TickEvents {
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.CLIENT || event.phase == TickEvent.Phase.END) return;
        Player player = event.player;

        player.getCapability(WeaversParadiseCapabilities.SCULK_PULSE_DATA).ifPresent(sculkPulseDataI -> {
            boolean changed = false;
            int newValue = 0;

            if (sculkPulseDataI.getPulse() > 0) {
                newValue = sculkPulseDataI.getPulse() - 1;
                sculkPulseDataI.setPulse(newValue);
                changed = true;
            }

            if (changed) {
                WeaversParadiseMod.PACKET_HANDLER.send(
                        PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                        new SculkPulseSyncPacket(
                                player.getId(),
                                newValue
                        )
                );
            }
        });
    }

    @SubscribeEvent
    public static void entity(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();

        if (livingEntity != null && !livingEntity.level().isClientSide) {
            livingEntity.getCapability(WeaversParadiseCapabilities.CHROMATIC_SHIFT_DATA).ifPresent((data) -> {
                boolean oldValue = data.isActive();
                boolean newValue = livingEntity.hasEffect(WeaversParadiseMobEffects.CHROMATIC_SHIFT.get());

                data.setActive(newValue);

                if (oldValue != newValue) {
                    WeaversParadiseMod.PACKET_HANDLER.send(
                            PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> livingEntity),
                            new ChromaticShiftSyncPacket(
                                    livingEntity.getId(),
                                    newValue
                            )
                    );
                }
            });
        }
    }
}
