package xox.labvorty.weaversparadise.data.keybinds;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.items.ShirtInterface;
import xox.labvorty.weaversparadise.items.ShirtSilk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber
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

    public static void handleData(final OpenUpperWearMessage message, final IPayloadContext context) {
        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                pressAction(context.player(), message.eventType, message.pressedms);
            }).exceptionally(e -> {
                context.connection().disconnect(Component.literal(e.getMessage()));
                return null;
            });
        }
    }

    public static void pressAction(Player player, int type, int pressedms) {
        Level level = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (type == 1) {
            Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);
            if (handler.isPresent()) {
                String slotType = "upperwear";
                List<SlotResult> curios = handler.map(h -> {
                    ICurioStacksHandler stacksHandler = h.getCurios().get(slotType);
                    if (stacksHandler == null) {
                        return Collections.<SlotResult>emptyList();
                    }
                    List<SlotResult> results = new ArrayList<>();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        results.add(new SlotResult(
                                new SlotContext(slotType, player, i, false, true),
                                stacksHandler.getStacks().getStackInSlot(i)
                        ));
                    }
                    return results;
                }).orElse(Collections.emptyList());
                for (SlotResult entry : curios) {
                    ItemStack itemStack = entry.stack();
                    boolean isOpen = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("is_open");

                    CustomData.update(DataComponents.CUSTOM_DATA, itemStack, (tag) -> {
                        tag.putBoolean("is_open", !isOpen);
                    });
                    handler.get().setEquippedCurio(entry.slotContext().identifier(), entry.slotContext().index(), itemStack);
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        WeaversParadise.addNetworkMessage(OpenUpperWearMessage.TYPE, OpenUpperWearMessage.STREAM_CODEC, OpenUpperWearMessage::handleData);
    }
}
