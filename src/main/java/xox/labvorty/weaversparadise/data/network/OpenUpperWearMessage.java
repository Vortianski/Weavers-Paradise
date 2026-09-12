package xox.labvorty.weaversparadise.data.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import dev.emi.trinkets.api.TrinketInventory;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.util.WPTrinkets;

import java.util.Optional;

public record OpenUpperWearMessage(int eventType, int pressedms) implements CustomPacketPayload {
    public static final Type<OpenUpperWearMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "open_upper_wear"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenUpperWearMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, OpenUpperWearMessage message) -> {
        buffer.writeInt(message.eventType);
        buffer.writeInt(message.pressedms);
    }, (RegistryFriendlyByteBuf buffer) -> new OpenUpperWearMessage(buffer.readInt(), buffer.readInt()));

    @Override
    public Type<OpenUpperWearMessage> type() {
        return TYPE;
    }

    public static void handleData(final OpenUpperWearMessage message, final ServerPlayNetworking.Context context) {
        pressAction(context.player(), message.eventType, message.pressedms);
    }

    public static void pressAction(Player player, int type, int pressedms) {
        Level level = player.level();
        if (type == 1) {
            Optional<TrinketInventory> invOpt = WPTrinkets.getInventoryByCuriosId(player, "upperwear");
            if (invOpt.isPresent()) {
                TrinketInventory inventory = invOpt.get();
                for (int i = 0; i < inventory.getContainerSize(); i++) {
                    ItemStack itemStack = inventory.getItem(i);
                    boolean isOpen = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open");

                    CustomData.update(DataComponents.CUSTOM_DATA, itemStack, (tag) -> {
                        tag.putBoolean("is_open", !isOpen);
                    });
                    inventory.setItem(i, itemStack);
                }
            }
        }
    }
}
