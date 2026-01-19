package xox.labvorty.weaversparadise.data.keybinds;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import xox.labvorty.weaversparadise.WeaversParadise;

@EventBusSubscriber
public record LeftMinigameButtonMessage(int eventType, int pressedms) implements CustomPacketPayload {
    public static final Type<LeftMinigameButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "left_minigame_keybind"));
    public static final StreamCodec<RegistryFriendlyByteBuf, LeftMinigameButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, LeftMinigameButtonMessage message) -> {
        buffer.writeInt(message.eventType);
        buffer.writeInt(message.pressedms);
    }, (RegistryFriendlyByteBuf buffer) -> new LeftMinigameButtonMessage(buffer.readInt(), buffer.readInt()));

    @Override
    public Type<LeftMinigameButtonMessage> type() {
        return TYPE;
    }

    public static void handleData(final LeftMinigameButtonMessage message, final IPayloadContext context) {
        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                pressAction(context.player(), message.eventType, message.pressedms);
            }).exceptionally(e -> {
                context.connection().disconnect(Component.literal(e.getMessage()));
                return null;
            });
        }
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level level = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (type == 1) {
            //entity.sendSystemMessage(Component.literal("LEFT"));
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        WeaversParadise.addNetworkMessage(LeftMinigameButtonMessage.TYPE, LeftMinigameButtonMessage.STREAM_CODEC, LeftMinigameButtonMessage::handleData);
    }
}
