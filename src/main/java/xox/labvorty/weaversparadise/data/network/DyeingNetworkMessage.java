package xox.labvorty.weaversparadise.data.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.entity.BlockEntity;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.blocks.entities.DyeingBarrelBlockEntity;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

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

    public static void handleData(final DyeingNetworkMessage message, final ServerPlayNetworking.Context context) {
        Player player = context.player();
        BlockPos pos = BlockPos.containing(message.x, message.y, message.z);
        BlockEntity blockEntity = player.level().getBlockEntity(pos);
        if (blockEntity instanceof DyeingBarrelBlockEntity dyeingBarrelBlock) {
            dyeingBarrelBlock.setItem(0, ItemStack.EMPTY);
            if (message.leftdyes) {
                ItemStack slot4 = dyeingBarrelBlock.getItem(4);
                ItemStack slot5 = dyeingBarrelBlock.getItem(5);
                if (slot4.is(WeaversParadiseItems.BOTTLED_DYE)) {
                    CompoundTag tag4 = slot4.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                    final int amount4 = tag4.getInt("amount") - 1;
                    CustomData.update(DataComponents.CUSTOM_DATA, slot4, (tag) -> {
                        tag.putInt("amount", amount4);
                    });
                    dyeingBarrelBlock.setItem(4, slot4);
                }
                if (slot5.is(WeaversParadiseItems.BOTTLED_DYE)) {
                    CompoundTag tag5 = slot5.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                    final int amount5 = tag5.getInt("amount") - 1;
                    CustomData.update(DataComponents.CUSTOM_DATA, slot5, (tag) -> {
                        tag.putInt("amount", amount5);
                    });
                    dyeingBarrelBlock.setItem(5, slot5);
                }
            }
            if (message.rightdyes) {
                ItemStack slot6 = dyeingBarrelBlock.getItem(6);
                ItemStack slot7 = dyeingBarrelBlock.getItem(7);
                if (slot6.is(WeaversParadiseItems.BOTTLED_DYE)) {
                    CompoundTag tag6 = slot6.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                    final int amount6 = tag6.getInt("amount") - 1;
                    CustomData.update(DataComponents.CUSTOM_DATA, slot6, (tag) -> {
                        tag.putInt("amount", amount6);
                    });
                    dyeingBarrelBlock.setItem(6, slot6);
                }
                if (slot7.is(WeaversParadiseItems.BOTTLED_DYE)) {
                    CompoundTag tag7 = slot7.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                    final int amount7 = tag7.getInt("amount") - 1;
                    CustomData.update(DataComponents.CUSTOM_DATA, slot7, (tag) -> {
                        tag.putInt("amount", amount7);
                    });
                    dyeingBarrelBlock.setItem(7, slot7);
                }
            }
        }
    }
}
