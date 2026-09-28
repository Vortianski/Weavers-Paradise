package xox.labvorty.weaversparadise.data.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import xox.labvorty.weaversparadise.init.WeaversParadiseCapabilities;

import java.util.function.Supplier;

public class ChromaticShiftSyncPacket {
    private final int entityID;
    private final boolean active;

    public ChromaticShiftSyncPacket(int entityID, boolean active) {
        this.entityID = entityID;
        this.active = active;
    }

    public ChromaticShiftSyncPacket(FriendlyByteBuf friendlyByteBuf) {
        this(
                friendlyByteBuf.readInt(),
                friendlyByteBuf.readBoolean()
        );
    }

    public static void encode(ChromaticShiftSyncPacket packet, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(packet.entityID);
        friendlyByteBuf.writeBoolean(packet.active);
    }

    public static void handle(ChromaticShiftSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.level == null) {
                return;
            }

            Entity entity = mc.level.getEntity(packet.entityID);

            if (entity != null) {
                entity.getCapability(
                        WeaversParadiseCapabilities.CHROMATIC_SHIFT_DATA
                ).ifPresent(data -> {
                    data.setActive(packet.active);
                });
            }
        });

        contextSupplier.get().setPacketHandled(true);
    }
}