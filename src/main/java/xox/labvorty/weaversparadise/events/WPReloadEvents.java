package xox.labvorty.weaversparadise.events;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Замена NeoForge-события ReloadEvent. */
public final class WPReloadEvents {
    private static final List<Runnable> COMMON_LISTENERS = new CopyOnWriteArrayList<>();
    private static final List<Runnable> CLIENT_LISTENERS = new CopyOnWriteArrayList<>();

    private WPReloadEvents() {}

    public static void onCommon(Runnable listener) {
        COMMON_LISTENERS.add(listener);
    }

    public static void onClient(Runnable listener) {
        CLIENT_LISTENERS.add(listener);
    }

    public static void postCommon() {
        COMMON_LISTENERS.forEach(Runnable::run);
    }

    public static void postClient() {
        CLIENT_LISTENERS.forEach(Runnable::run);
    }
}
