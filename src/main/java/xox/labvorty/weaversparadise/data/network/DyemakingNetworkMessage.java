package xox.labvorty.weaversparadise.data.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import xox.labvorty.weaversparadise.blocks.entities.DyemakingBlockEntity;

import java.util.function.Supplier;

public class DyemakingNetworkMessage {
    private final int slotID;
    private final int x;
    private final int y;
    private final int z;
    private final int changeType;
    private final int meta;

    public DyemakingNetworkMessage(int slotID, int x, int y, int z, int changeType, int meta) {
        this.slotID = slotID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.changeType = changeType;
        this.meta = meta;
    }

    public static void buffer(DyemakingNetworkMessage msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.slotID);
        buf.writeInt(msg.x);
        buf.writeInt(msg.y);
        buf.writeInt(msg.z);
        buf.writeInt(msg.changeType);
        buf.writeInt(msg.meta);
    }

    public DyemakingNetworkMessage(FriendlyByteBuf buf) {
        this(
                buf.readInt(),
                buf.readInt(),
                buf.readInt(),
                buf.readInt(),
                buf.readInt(),
                buf.readInt()
        );
    }

    public static void handler(DyemakingNetworkMessage msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();

        ctx.enqueueWork(() -> {
            Player player = ctx.getSender();
            if (player == null) return;

            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            BlockEntity be = player.level().getBlockEntity(pos);

            if (!(be instanceof DyemakingBlockEntity dyemaking)) return;

            if (msg.slotID == 0 && msg.changeType == 1) {

                for (int i = 1; i < 13; i++) {
                    ItemStack stack = dyemaking.getItemHandler().getStackInSlot(i);

                    if (!stack.isEmpty()) {
                        stack.shrink(1);
                    }

                    if (i == 11) {
                        stack = new ItemStack(Items.GLASS_BOTTLE);
                    }

                    dyemaking.getItemHandler().setStackInSlot(i, stack);
                }
            }
        });

        ctx.setPacketHandled(true);
    }
}
