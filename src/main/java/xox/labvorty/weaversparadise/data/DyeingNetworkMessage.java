package xox.labvorty.weaversparadise.data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.DyeingBarrelBlockEntity;
import xox.labvorty.weaversparadise.blocks.entities.DyemakingBlockEntity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

@EventBusSubscriber
public record DyeingNetworkMessage(int x, int y, int z, boolean leftdyes, boolean rightdyes) implements CustomPacketPayload {
    public static final Type<DyeingNetworkMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "dyeing_network_message"));
    public static final StreamCodec<RegistryFriendlyByteBuf, DyeingNetworkMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buf, DyeingNetworkMessage message) -> {
        buf.writeInt(message.x);
        buf.writeInt(message.y);
        buf.writeInt(message.z);
        buf.writeBoolean(message.leftdyes);
        buf.writeBoolean(message.rightdyes);
    }, (RegistryFriendlyByteBuf buf) -> new DyeingNetworkMessage(
            buf.readInt(),
            buf.readInt(),
            buf.readInt(),
            buf.readBoolean(),
            buf.readBoolean()
    ));

    @Override
    public Type<DyeingNetworkMessage> type() {
        return TYPE;
    }

    public static void handleData(final DyeingNetworkMessage message, final IPayloadContext context) {
        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                Player player = context.player();
                int x = message.x;
                int y = message.y;
                int z = message.z;
                boolean leftdyes = message.leftdyes;
                boolean rightdyes = message.rightdyes;

                BlockPos pos = BlockPos.containing(x, y, z);
                BlockEntity blockEntity = player.level().getBlockEntity(pos);
                if (blockEntity instanceof DyeingBarrelBlockEntity dyeingBarrelBlock) {
                    dyeingBarrelBlock.getItemHandler().setStackInSlot(0, ItemStack.EMPTY);

                    if (leftdyes) {
                        ItemStack slot4 = dyeingBarrelBlock.getItemHandler().getStackInSlot(4);
                        ItemStack slot5 = dyeingBarrelBlock.getItemHandler().getStackInSlot(5);

                        if (slot4.is(WeaversParadiseItems.BOTTLED_DYE)) {
                            CompoundTag tag4 = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                            final int amount4 = tag4.getInt("amount") - 1;
                            CustomData.update(DataComponents.CUSTOM_DATA, slot4, (tag) -> {
                                tag.putInt("amount", amount4);
                            });
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(4, slot4);
                        }

                        if (slot5.is(WeaversParadiseItems.BOTTLED_DYE)) {
                            CompoundTag tag5 = slot5.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                            final int amount5 = tag5.getInt("amount") - 1;
                            CustomData.update(DataComponents.CUSTOM_DATA, slot5, (tag) -> {
                                tag.putInt("amount", amount5);
                            });
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(5, slot5);
                        }
                    }

                    if (rightdyes) {
                        ItemStack slot6 = dyeingBarrelBlock.getItemHandler().getStackInSlot(6);
                        ItemStack slot7 = dyeingBarrelBlock.getItemHandler().getStackInSlot(7);

                        if (slot6.is(WeaversParadiseItems.BOTTLED_DYE)) {
                            CompoundTag tag6 = slot6.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                            final int amount6 = tag6.getInt("amount") - 1;
                            CustomData.update(DataComponents.CUSTOM_DATA, slot6, (tag) -> {
                                tag.putInt("amount", amount6);
                            });
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(6, slot6);
                        }

                        if (slot7.is(WeaversParadiseItems.BOTTLED_DYE)) {
                            CompoundTag tag7 = slot7.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                            final int amount7 = tag7.getInt("amount") - 1;
                            CustomData.update(DataComponents.CUSTOM_DATA, slot7, (tag) -> {
                                tag.putInt("amount", amount7);
                            });
                            dyeingBarrelBlock.getItemHandler().setStackInSlot(7, slot7);
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
                DyeingNetworkMessage.TYPE,
                DyeingNetworkMessage.STREAM_CODEC,
                DyeingNetworkMessage::handleData
        );
    }
}
