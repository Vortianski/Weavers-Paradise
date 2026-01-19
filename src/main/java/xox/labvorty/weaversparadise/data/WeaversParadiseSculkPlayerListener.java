package xox.labvorty.weaversparadise.data;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class WeaversParadiseSculkPlayerListener implements GameEventListener {
    private final PositionSource posSource;
    private final int radius;
    private final UUID playerId;
    private final ServerPlayer player;

    public WeaversParadiseSculkPlayerListener(ServerPlayer player, int radius) {
        this.posSource = new EntityPositionSource(player, player.getEyeHeight());
        this.radius = radius;
        this.playerId = player.getUUID();
        this.player = player;
    }

    @Override
    public PositionSource getListenerSource() {
        return posSource;
    }

    @Override
    public int getListenerRadius() {
        return radius;
    }

    @Override
    public boolean handleGameEvent(ServerLevel level, Holder<GameEvent> event, GameEvent.Context context, Vec3 pos) {
        if (context.sourceEntity() != player) {
            if (player.getPersistentData().getInt("weaversSculkPulse") <= 0) {
                player.getPersistentData().putInt("weaversSculkPulse", 60);
            }
        }

        return true;
    }
}
