package xox.labvorty.weaversparadise.data.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class OpenUpperWearMessage {
    private final int eventType;
    private final int pressedms;

    public OpenUpperWearMessage(int eventType, int pressedms) {
        this.eventType = eventType;
        this.pressedms = pressedms;
    }

    public OpenUpperWearMessage(FriendlyByteBuf buf) {
        this.eventType = buf.readInt();
        this.pressedms = buf.readInt();
    }

    public static void buffer(OpenUpperWearMessage message, FriendlyByteBuf buf) {
        buf.writeInt(message.eventType);
        buf.writeInt(message.pressedms);
    }

    public static void handler(OpenUpperWearMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            if (!context.getDirection().getReceptionSide().isServer())
                return;

            ServerPlayer player = context.getSender();
            if (player == null)
                return;

            pressAction(player, message.eventType, message.pressedms);
        });

        context.setPacketHandled(true);
    }

    private static void pressAction(ServerPlayer player, int type, int pressedms) {
        if (type != 1)
            return;

        LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);

        handler.ifPresent((curiosHandler) -> {
            String slotType = "upperwear";

            List<SlotResult> curios = handler.map(h -> {
                ICurioStacksHandler stacksHandler = h.getCurios().get(slotType);

                if (stacksHandler == null)
                    return Collections.<SlotResult>emptyList();

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
                ItemStack stack = entry.stack();

                CompoundTag tag = stack.getOrCreateTag();
                boolean open = tag.getBoolean("is_open");
                tag.putBoolean("is_open", !open);

                curiosHandler.setEquippedCurio(
                        entry.slotContext().identifier(),
                        entry.slotContext().index(),
                        stack
                );
            }
        });
    }
}