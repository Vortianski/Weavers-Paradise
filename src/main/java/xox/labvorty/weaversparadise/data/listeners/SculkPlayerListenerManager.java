package xox.labvorty.weaversparadise.data.listeners;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gameevent.DynamicGameEventListener;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SculkPlayerListenerManager {
    private static final Map<UUID, DynamicGameEventListener<SculkPlayerListener>> LISTENERS = new ConcurrentHashMap<>();

    public static void attach(ServerPlayer player) {
        if (LISTENERS.containsKey(player.getUUID())) return;

        SculkPlayerListener listener = new SculkPlayerListener(player, 8);
        DynamicGameEventListener<SculkPlayerListener> dynamic = new DynamicGameEventListener<>(listener);
        LISTENERS.put(player.getUUID(), dynamic);
        dynamic.add(player.serverLevel());
    }

    public static void detach(ServerPlayer player) {
        DynamicGameEventListener<SculkPlayerListener> dynamic = LISTENERS.remove(player.getUUID());
        if (dynamic != null) {
            dynamic.remove(player.serverLevel());
        }
    }

    public static void tick(ServerPlayer player) {
        DynamicGameEventListener<SculkPlayerListener> dynamic = LISTENERS.get(player.getUUID());
        if (dynamic != null) {
            dynamic.move(player.serverLevel());
        }
    }
}