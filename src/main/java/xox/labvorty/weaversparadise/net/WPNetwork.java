package xox.labvorty.weaversparadise.net;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import xox.labvorty.weaversparadise.data.network.ClothcraftingNetworkMultiMessage;
import xox.labvorty.weaversparadise.data.network.DyeingNetworkMessage;
import xox.labvorty.weaversparadise.data.network.DyemakingNetworkMessage;
import xox.labvorty.weaversparadise.data.network.OpenUpperWearMessage;
import xox.labvorty.weaversparadise.data.network.StringNetworkMessage;
import xox.labvorty.weaversparadise.data.network.TrinketSoundMessage;

/** Server-safe network helpers (payload registration + server receivers + S2C sends). */
public final class WPNetwork {
    private WPNetwork() {}

    public static <T extends CustomPacketPayload> void registerC2S(
            CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        PayloadTypeRegistry.playC2S().register(type, codec);
    }

    public static <T extends CustomPacketPayload> void registerS2C(
            CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        PayloadTypeRegistry.playS2C().register(type, codec);
    }

    public static <T extends CustomPacketPayload> void registerServerReceiver(
            CustomPacketPayload.Type<T> type, ServerPlayNetworking.PlayPayloadHandler<T> handler) {
        ServerPlayNetworking.registerGlobalReceiver(type, handler);
    }

    /** Replacement for PacketDistributor.sendToPlayer(player, ...). */
    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    public static void registerPayloads() {
        // C2S
        registerC2S(OpenUpperWearMessage.TYPE, OpenUpperWearMessage.STREAM_CODEC);
        registerC2S(DyeingNetworkMessage.TYPE, DyeingNetworkMessage.STREAM_CODEC);
        registerC2S(DyemakingNetworkMessage.TYPE, DyemakingNetworkMessage.STREAM_CODEC);
        registerC2S(TrinketSoundMessage.TYPE, TrinketSoundMessage.STREAM_CODEC);
        // bidirectional
        registerC2S(ClothcraftingNetworkMultiMessage.TYPE, ClothcraftingNetworkMultiMessage.STREAM_CODEC);
        registerS2C(ClothcraftingNetworkMultiMessage.TYPE, ClothcraftingNetworkMultiMessage.STREAM_CODEC);
        registerC2S(StringNetworkMessage.TYPE, StringNetworkMessage.STREAM_CODEC);
        registerS2C(StringNetworkMessage.TYPE, StringNetworkMessage.STREAM_CODEC);

        registerServerReceiver(OpenUpperWearMessage.TYPE, OpenUpperWearMessage::handleData);
        registerServerReceiver(DyeingNetworkMessage.TYPE, DyeingNetworkMessage::handleData);
        registerServerReceiver(DyemakingNetworkMessage.TYPE, DyemakingNetworkMessage::handleData);
        registerServerReceiver(TrinketSoundMessage.TYPE, TrinketSoundMessage::handle);
        registerServerReceiver(ClothcraftingNetworkMultiMessage.TYPE, ClothcraftingNetworkMultiMessage::handleData);
        registerServerReceiver(StringNetworkMessage.TYPE, StringNetworkMessage::handleData);
    }
}
