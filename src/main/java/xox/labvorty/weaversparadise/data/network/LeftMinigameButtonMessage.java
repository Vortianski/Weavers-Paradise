package xox.labvorty.weaversparadise.data.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LeftMinigameButtonMessage {
    private final int eventType;
    private final int pressedMs;

    public LeftMinigameButtonMessage(
            int eventType,
            int pressedMs
    ) {
        this.eventType = eventType;
        this.pressedMs = pressedMs;
    }

    public LeftMinigameButtonMessage(FriendlyByteBuf friendlyByteBuf) {
        this.eventType = friendlyByteBuf.readInt();
        this.pressedMs = friendlyByteBuf.readInt();
    }

    public static void buffer(LeftMinigameButtonMessage message, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(message.eventType);
        friendlyByteBuf.writeInt(message.pressedMs);
    }

    public static void handler(LeftMinigameButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {

        });
        context.setPacketHandled(true);
    }
}
