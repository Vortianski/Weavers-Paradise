package xox.labvorty.weaversparadise.data.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.blocks.entities.SpinningJennyBlockEntity;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;

import java.util.function.Supplier;

public class StringNetworkMessage {
    private final int buttonID;
    private final int x;
    private final int y;
    private final int z;
    private final int data;

    public StringNetworkMessage(
            int buttonID,
            int x,
            int y,
            int z,
            int data
    ) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
    }

    public StringNetworkMessage(FriendlyByteBuf friendlyByteBuf) {
        this.buttonID = friendlyByteBuf.readInt();
        this.x = friendlyByteBuf.readInt();
        this.y = friendlyByteBuf.readInt();
        this.z = friendlyByteBuf.readInt();
        this.data = friendlyByteBuf.readInt();
    }

    public static void buffer(StringNetworkMessage message, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(message.buttonID);
        friendlyByteBuf.writeInt(message.x);
        friendlyByteBuf.writeInt(message.y);
        friendlyByteBuf.writeInt(message.z);
        friendlyByteBuf.writeInt(message.data);
    }

    public static void handler(StringNetworkMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isServer()) {
                Player player = context.getSender();
                int x = message.x;
                int y = message.y;
                int z = message.z;

                if (context.getSender() == null) {
                    return;
                }

                BlockEntity blockEntity = player.level().getBlockEntity(BlockPos.containing(x,y,z));
                if (blockEntity instanceof SpinningJennyBlockEntity spinningJennyBlockEntity) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        WeaversParadiseMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(context::getSender), new StringNetworkMessage(0,x,y,z,spinningJennyBlockEntity.getWorkingState()));
                    }
                }
            }

            if (context.getDirection().getReceptionSide().isClient()) {
                StringScreen.updateProgress(message.data);
            }
        });
        context.setPacketHandled(true);
    }
}
