package xox.labvorty.weaversparadise.data.listeners;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gameevent.EntityPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseAttachmentTypes;

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
    public boolean handleGameEvent(@NotNull ServerLevel level, @NotNull Holder<GameEvent> event, GameEvent.Context context, @NotNull Vec3 pos) {
        if (context.sourceEntity() != player) {
            int pulse = player.getData(WeaversParadiseAttachmentTypes.SCULK_PULSE);

            if (pulse <= 0) {
                player.setData(WeaversParadiseAttachmentTypes.SCULK_PULSE, 60);
            }
        }

        return true;
    }
}
