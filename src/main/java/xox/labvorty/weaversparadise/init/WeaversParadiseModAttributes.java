package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.WeaversParadise;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class WeaversParadiseModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, WeaversParadise.MODID);
    public static final DeferredHolder<Attribute, Attribute> LIFESTEAL = ATTRIBUTES.register("lifesteal",() -> new RangedAttribute(
            "attribute.name.weaversparadise.lifesteal",
            0.0D,
            0.0D,
            1.0D
    ).setSyncable(true));

    @SubscribeEvent
    public static void attributeAffix(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, WeaversParadiseModAttributes.LIFESTEAL);
    }
}
