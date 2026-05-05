package xox.labvorty.weaversparadise.data.network;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import xox.labvorty.weaversparadise.blocks.entities.DyeingBarrelBlockEntity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.function.Supplier;

public class DyeingNetworkMessage {
    private final int x;
    private final int y;
    private final int z;
    private final boolean left_dyes;
    private final boolean right_dyes;

    public DyeingNetworkMessage(
            int x,
            int y,
            int z,
            boolean left_dyes,
            boolean right_dyes
    ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.left_dyes = left_dyes;
        this.right_dyes = right_dyes;
    }

    public DyeingNetworkMessage(
            FriendlyByteBuf friendlyByteBuf
    ) {
        this.x = friendlyByteBuf.readInt();
        this.y = friendlyByteBuf.readInt();
        this.z = friendlyByteBuf.readInt();
        this.left_dyes = friendlyByteBuf.readBoolean();
        this.right_dyes = friendlyByteBuf.readBoolean();
    }

    public static void buffer(DyeingNetworkMessage message, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(message.x);
        friendlyByteBuf.writeInt(message.y);
        friendlyByteBuf.writeInt(message.z);
        friendlyByteBuf.writeBoolean(message.left_dyes);
        friendlyByteBuf.writeBoolean(message.right_dyes);
    }

    public static void handler(DyeingNetworkMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer()) return;

            ServerPlayer player = context.getSender();
            if (player == null) return;

            BlockPos pos = new BlockPos(message.x, message.y, message.z);
            BlockEntity be = player.level().getBlockEntity(pos);

            if (!(be instanceof DyeingBarrelBlockEntity barrel)) return;
            barrel.getItemHandler().setStackInSlot(0, ItemStack.EMPTY);

            if (message.left_dyes) {
                ItemStack slot4 = barrel.getItemHandler().getStackInSlot(4);
                ItemStack slot5 = barrel.getItemHandler().getStackInSlot(5);

                if (slot4.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                    CompoundTag tag = slot4.getOrCreateTag();
                    int amount = tag.getInt("amount") - 1;
                    tag.putInt("amount", amount);
                    barrel.getItemHandler().setStackInSlot(4, slot4);
                }

                if (slot5.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                    CompoundTag tag = slot5.getOrCreateTag();
                    int amount = tag.getInt("amount") - 1;
                    tag.putInt("amount", amount);
                    barrel.getItemHandler().setStackInSlot(5, slot5);
                }
            }

            if (message.right_dyes) {
                ItemStack slot6 = barrel.getItemHandler().getStackInSlot(6);
                ItemStack slot7 = barrel.getItemHandler().getStackInSlot(7);

                if (slot6.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                    CompoundTag tag = slot6.getOrCreateTag();
                    int amount = tag.getInt("amount") - 1;
                    tag.putInt("amount", amount);
                    barrel.getItemHandler().setStackInSlot(6, slot6);
                }

                if (slot7.is(WeaversParadiseItems.BOTTLED_DYE.get())) {
                    CompoundTag tag = slot7.getOrCreateTag();
                    int amount = tag.getInt("amount") - 1;
                    tag.putInt("amount", amount);
                    barrel.getItemHandler().setStackInSlot(7, slot7);
                }
            }

            barrel.setChanged();
            player.level().sendBlockUpdated(pos, barrel.getBlockState(), barrel.getBlockState(), 3);
        });
        context.setPacketHandled(true);
    }
}
