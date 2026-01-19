package xox.labvorty.weaversparadise.data;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.DyemakingBlockEntity;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record DyemakingNetworkMessage(int slotID, int x, int y, int z, int changeType, int meta) implements CustomPacketPayload {
    public static final Type<DyemakingNetworkMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "dyemaking_network_message"));
    public static final StreamCodec<RegistryFriendlyByteBuf, DyemakingNetworkMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buf, DyemakingNetworkMessage message) -> {
        buf.writeInt(message.slotID);
        buf.writeInt(message.x);
        buf.writeInt(message.y);
        buf.writeInt(message.z);
        buf.writeInt(message.changeType);
        buf.writeInt(message.meta);
    }, (RegistryFriendlyByteBuf buf) -> new DyemakingNetworkMessage(
            buf.readInt(),
            buf.readInt(),
            buf.readInt(),
            buf.readInt(),
            buf.readInt(),
            buf.readInt()
    ));

    @Override
    public Type<DyemakingNetworkMessage> type() {
        return TYPE;
    }

    public static void handleData(final DyemakingNetworkMessage message, final IPayloadContext context) {
        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                Player player = context.player();
                int slotID = message.slotID;
                int changeType = message.changeType;
                int meta = message.meta;
                int x = message.x;
                int y = message.y;
                int z = message.z;

                if (slotID == 0 && changeType == 1) {
                    BlockPos pos = BlockPos.containing(x, y, z);
                    BlockEntity blockEntity = player.level().getBlockEntity(pos);
                    if (blockEntity instanceof DyemakingBlockEntity dyemakingBlock) {
                        for  (int i = 1; i < 13; i++) {
                            ItemStack stack = dyemakingBlock.getItemHandler().getStackInSlot(i);
                            stack.shrink(1);
                            if (i == 11) {
                                stack = new ItemStack(Items.GLASS_BOTTLE);
                            }
                            dyemakingBlock.getItemHandler().setStackInSlot(i, stack);
                        }
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
                int slotID = message.slotID;
                int changeType = message.changeType;
                int meta = message.meta;
                int x = message.x;
                int y = message.y;
                int z = message.z;


            }).exceptionally(e -> {
                context.connection().disconnect(Component.literal(e.getMessage()));
                return null;
            });
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        WeaversParadise.addNetworkMessage(
                DyemakingNetworkMessage.TYPE,
                DyemakingNetworkMessage.STREAM_CODEC,
                DyemakingNetworkMessage::handleData
        );
    }
}
