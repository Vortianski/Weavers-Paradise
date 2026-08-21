package xox.labvorty.weaversparadise.data.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.vortylib.utilities.VortyLibCurioUtilities;
import xox.labvorty.weaversparadise.items.clothing.defined.ChokerTrinketInterface;

import java.util.List;

public record TrinketSoundMessage(double x, double y, double z) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<TrinketSoundMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("weaversparadise", "trinket_sound_message"));
    public static final StreamCodec<RegistryFriendlyByteBuf, TrinketSoundMessage> STREAM_CODEC = StreamCodec.of(
            (RegistryFriendlyByteBuf registryFriendlyByteBuf, TrinketSoundMessage message) -> {
                registryFriendlyByteBuf.writeDouble(message.x);
                registryFriendlyByteBuf.writeDouble(message.y);
                registryFriendlyByteBuf.writeDouble(message.z);
            },
            (RegistryFriendlyByteBuf registryFriendlyByteBuf) -> {
                return new TrinketSoundMessage(
                        registryFriendlyByteBuf.readDouble(),
                        registryFriendlyByteBuf.readDouble(),
                        registryFriendlyByteBuf.readDouble()
                );
            });

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final TrinketSoundMessage message, final IPayloadContext payloadContext) {
        if (payloadContext.flow() == PacketFlow.SERVERBOUND) {
            payloadContext.enqueueWork(() -> {
                Player player = payloadContext.player();

                VortyLibCurioUtilities.CurioMatch<ChokerTrinketInterface> match = VortyLibCurioUtilities.findFirstCurioOfType(player, ChokerTrinketInterface.class);
                if (match != null) {
                    List<SoundEvent> soundEvents = match.value().getSounds();
                    if (!soundEvents.isEmpty()) {
                        RandomSource randomSource = player.getRandom();
                        player.level().playSound(
                                null,
                                message.x,
                                message.y,
                                message.z,
                                soundEvents.get(randomSource.nextInt(soundEvents.size())),
                                SoundSource.NEUTRAL,
                                1.0F,
                                0.9F + randomSource.nextFloat() * 0.2F
                        );
                    }
                }
            });
        }
    }
}
