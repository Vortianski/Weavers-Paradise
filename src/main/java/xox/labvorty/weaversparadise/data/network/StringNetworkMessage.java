package xox.labvorty.weaversparadise.data.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;
import xox.labvorty.weaversparadise.net.WPNetwork;

public record StringNetworkMessage(int buttonID, int x, int y, int z, int data) implements CustomPacketPayload {
    public static final Type<StringNetworkMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "string_network_message"));
    public static final StreamCodec<RegistryFriendlyByteBuf, StringNetworkMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, StringNetworkMessage message) -> {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
        buffer.writeInt(message.data);
    }, (RegistryFriendlyByteBuf buffer) -> new StringNetworkMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
    @Override
    public Type<StringNetworkMessage> type() {
        return TYPE;
    }

    public static void handleData(final StringNetworkMessage message, final ServerPlayNetworking.Context context) {
        Player player = context.player();
        int buttonID = message.buttonID;
        int x = message.x;
        int y = message.y;
        int z = message.z;

        BlockEntity blockEntity = player.level().getBlockEntity(BlockPos.containing(x, y, z));
        if (blockEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
            if (player instanceof ServerPlayer serverPlayer) {
                WPNetwork.sendToPlayer(serverPlayer, new StringNetworkMessage(0, x, y, z, spinningJennyBlockEntity.getWorkingState()));
            }
        }
    }

    /** Клиентский приём (S2C): вызывается из WeaversParadiseFabricClient через ClientPlayNetworking. */
}
