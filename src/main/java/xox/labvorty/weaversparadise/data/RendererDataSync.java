package xox.labvorty.weaversparadise.data;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.renderers.WeaversParadiseGlobalRendererDataHolder;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record RendererDataSync(int dataID, int x, int y, int z, int sculkPulse) implements CustomPacketPayload {
    public static final Type<RendererDataSync> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "renderer_data_sync"));
    public static final StreamCodec<RegistryFriendlyByteBuf, RendererDataSync> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buf, RendererDataSync message) -> {
        buf.writeInt(message.dataID);
        buf.writeInt(message.x);
        buf.writeInt(message.y);
        buf.writeInt(message.z);
        buf.writeInt(message.sculkPulse);
    }, (RegistryFriendlyByteBuf buf) -> new RendererDataSync(
            buf.readInt(),
            buf.readInt(),
            buf.readInt(),
            buf.readInt(),
            buf.readInt()
    ));

    @Override
    public Type<RendererDataSync> type() {
        return TYPE;
    }

    public static void handleData(final RendererDataSync message, final IPayloadContext context) {
        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                Player player = context.player();
                int dataID = message.dataID;
                int x = message.x;
                int y = message.y;
                int z = message.z;

                if (dataID == 1) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        PacketDistributor.sendToPlayer(serverPlayer, new RendererDataSync(1, x, y, z, player.getPersistentData().getInt("weaversSculkPulse")));
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
                int dataID = message.dataID;
                int sculkPulse = message.sculkPulse;

                if (dataID == 1) {
                    WeaversParadiseGlobalRendererDataHolder.updateSculkPulse(sculkPulse);
                }
            }).exceptionally(e -> {
                context.connection().disconnect(Component.literal(e.getMessage()));
                return null;
            });
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        WeaversParadise.addNetworkMessage(
                RendererDataSync.TYPE,
                RendererDataSync.STREAM_CODEC,
                RendererDataSync::handleData
        );
    }
}
