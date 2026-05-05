package xox.labvorty.weaversparadise.data.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DownMinigameButtonMessage {
    private final int eventType;
    private final int pressedMs;

    public DownMinigameButtonMessage(
            int eventType,
            int pressedMs
    ) {
        this.eventType = eventType;
        this.pressedMs = pressedMs;
    }

    public DownMinigameButtonMessage(FriendlyByteBuf friendlyByteBuf) {
        this.eventType = friendlyByteBuf.readInt();
        this.pressedMs = friendlyByteBuf.readInt();
    }

    public static void buffer(DownMinigameButtonMessage message, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(message.eventType);
        friendlyByteBuf.writeInt(message.pressedMs);
    }

    public static void handler(DownMinigameButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {

        });
        context.setPacketHandled(true);
    }
}
