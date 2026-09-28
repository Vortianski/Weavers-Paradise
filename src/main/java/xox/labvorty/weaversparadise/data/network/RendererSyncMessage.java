package xox.labvorty.weaversparadise.data.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import xox.labvorty.weaversparadise.data.rendering.GlobalRenderingData;

import java.util.function.Supplier;

public class RendererSyncMessage {
    private final int pulse;

    public RendererSyncMessage(int pulse) {
        this.pulse = pulse;
    }

    public RendererSyncMessage(FriendlyByteBuf friendlyByteBuf) {
        this.pulse = friendlyByteBuf.readInt();
    }

    public static void buffer(RendererSyncMessage message, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(message.pulse);
    }

    public static void handler(RendererSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isClient()) {
                GlobalRenderingData.updateSculkPulse(message.pulse);
            }
        });
        context.setPacketHandled(true);
    }
}
