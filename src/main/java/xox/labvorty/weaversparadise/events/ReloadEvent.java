package xox.labvorty.weaversparadise.events;

import net.neoforged.bus.api.Event;

public abstract class ReloadEvent extends Event {
    public static class Common extends ReloadEvent {}
    public static class Client extends ReloadEvent {}
}