package xox.labvorty.weaversparadise.events;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import xox.labvorty.weaversparadise.data.listeners.SculkPlayerListenerManager;

@EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            SculkPlayerListenerManager.attach(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            SculkPlayerListenerManager.detach(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            SculkPlayerListenerManager.detach(serverPlayer);
            SculkPlayerListenerManager.attach(serverPlayer);
        }
    }
}
