package xox.labvorty.weaversparadise.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/** Client-only network helpers (C2S sends + client receivers). Never touch from common code. */
public final class WPClientNetwork {
    private WPClientNetwork() {}

    public static <T extends CustomPacketPayload> void registerClientReceiver(
            CustomPacketPayload.Type<T> type, ClientPlayNetworking.PlayPayloadHandler<T> handler) {
        ClientPlayNetworking.registerGlobalReceiver(type, handler);
    }

    /** Replacement for PacketDistributor.sendToServer(...). */
    public static void sendToServer(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }
}
