package xox.labvorty.weaversparadise.data;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;

@EventBusSubscriber
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

    public static void handleData(final StringNetworkMessage message, final IPayloadContext context) {
        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                Player player = context.player();
                int buttonID = message.buttonID;
                int x = message.x;
                int y = message.y;
                int z = message.z;

                BlockEntity blockEntity = player.level().getBlockEntity(BlockPos.containing(x,y,z));
                if (blockEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        PacketDistributor.sendToPlayer(serverPlayer, new StringNetworkMessage(0,x,y,z,spinningJennyBlockEntity.getWorkingState()));
                    }
                }
            }).exceptionally(e -> {
                context.connection().disconnect(Component.literal(e.getMessage()));
                return null;
            });
        }

        if (context.flow() == PacketFlow.CLIENTBOUND) {
            context.enqueueWork(() -> {
                Player player = context.player();
                int buttonID = message.buttonID;
                int x = message.x;
                int y = message.y;
                int z = message.z;
                int data = message.data;

                StringScreen.updateProgress(data);
            });
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        WeaversParadise.addNetworkMessage(StringNetworkMessage.TYPE, StringNetworkMessage.STREAM_CODEC, StringNetworkMessage::handleData);
    }
}
