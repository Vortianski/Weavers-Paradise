package xox.labvorty.weaversparadise.data.listeners;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.capabilities.SculkPulseData;
import xox.labvorty.weaversparadise.data.network.SculkPulseSyncPacket;
import xox.labvorty.weaversparadise.init.WeaversParadiseCapabilities;

import java.util.UUID;

public class SculkPlayerListener implements GameEventListener {
    private final PositionSource posSource;
    private final int radius;
    private final ServerPlayer player;

    public SculkPlayerListener(ServerPlayer player, int radius) {
        this.posSource = new EntityPositionSource(player, player.getEyeHeight());
        this.radius = radius;
        this.player = player;
    }

    @Override
    public @NotNull PositionSource getListenerSource() {
        return posSource;
    }

    @Override
    public int getListenerRadius() {
        return radius;
    }

    @Override
    public boolean handleGameEvent(@NotNull ServerLevel serverLevel, @NotNull GameEvent gameEvent, GameEvent.Context context, @NotNull Vec3 vec3) {
        if (context.sourceEntity() != player) {
            SculkPulseData sculkPulseData = player.getCapability(WeaversParadiseCapabilities.SCULK_PULSE_DATA).orElse(new SculkPulseData());

            if (sculkPulseData.getPulse() <= 0) {
                player.getCapability(WeaversParadiseCapabilities.SCULK_PULSE_DATA).ifPresent(sculkPulseDataI -> {
                    sculkPulseDataI.setPulse(60);
                    WeaversParadiseMod.PACKET_HANDLER.send(
                            PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),
                            new SculkPulseSyncPacket(
                                    player.getId(),
                                    60
                            )
                    );
                });
            }
        }

        return true;
    }
}
