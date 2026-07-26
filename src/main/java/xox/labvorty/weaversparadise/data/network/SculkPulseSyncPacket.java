package xox.labvorty.weaversparadise.data.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import xox.labvorty.weaversparadise.init.WeaversParadiseCapabilities;

import java.util.function.Supplier;

public class SculkPulseSyncPacket {
    private final int entityID;
    private final int pulse;

    public SculkPulseSyncPacket(int entityID, int pulse) {
        this.entityID = entityID;
        this.pulse = pulse;
    }

    public SculkPulseSyncPacket(FriendlyByteBuf friendlyByteBuf) {
        this(
                friendlyByteBuf.readInt(),
                friendlyByteBuf.readInt()
        );
    }

    public static void encode(SculkPulseSyncPacket packet, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(packet.entityID);
        friendlyByteBuf.writeInt(packet.pulse);
    }

    public static void handle(SculkPulseSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.level == null) {
                return;
            }

            Entity entity = mc.level.getEntity(packet.entityID);

            if (entity != null) {
                entity.getCapability(
                        WeaversParadiseCapabilities.SCULK_PULSE_DATA
                ).ifPresent(data -> {
                    data.setPulse(packet.pulse);
                });
            }
        });

        contextSupplier.get().setPacketHandled(true);
    }
}