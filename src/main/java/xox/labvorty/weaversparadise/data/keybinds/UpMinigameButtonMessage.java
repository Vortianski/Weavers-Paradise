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
public record UpMinigameButtonMessage(int eventType, int pressedms) implements CustomPacketPayload {
    public static final Type<UpMinigameButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "up_minigame_keybind"));
    public static final StreamCodec<RegistryFriendlyByteBuf, UpMinigameButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, UpMinigameButtonMessage message) -> {
        buffer.writeInt(message.eventType);
        buffer.writeInt(message.pressedms);
    }, (RegistryFriendlyByteBuf buffer) -> new UpMinigameButtonMessage(buffer.readInt(), buffer.readInt()));

    @Override
    public Type<UpMinigameButtonMessage> type() {
        return TYPE;
    }

    public static void handleData(final UpMinigameButtonMessage message, final IPayloadContext context) {
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
            //entity.sendSystemMessage(Component.literal("UP"));
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        WeaversParadise.addNetworkMessage(UpMinigameButtonMessage.TYPE, UpMinigameButtonMessage.STREAM_CODEC, UpMinigameButtonMessage::handleData);
    }
}
