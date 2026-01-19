package xox.labvorty.weaversparadise.curios;

import top.theillusivec4.curios.api.SlotTypeMessage;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.InterModComms;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.resources.ResourceLocation;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class WeaversParadiseCustomCuriosSlots {
    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void enqueueIMC(final InterModEnqueueEvent event) {
        //InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("garter_left").icon(ResourceLocation.parse("curios:slot/legwear")).size(0).build());
        //InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("garter_right").icon(ResourceLocation.parse("curios:slot/legwear")).size(0).build());
        //InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("legwear").icon(ResourceLocation.parse("curios:slot/legwear")).size(1).build());
        //InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("gloves").icon(ResourceLocation.parse("curios:slot/gloves")).size(1).build());
    }
}
