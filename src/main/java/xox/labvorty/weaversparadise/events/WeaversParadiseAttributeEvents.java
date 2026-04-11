package xox.labvorty.weaversparadise.events;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.HandWarmersCotton;

import java.util.Optional;

@EventBusSubscriber
public class WeaversParadiseAttributeEvents {
    @SubscribeEvent
    public static void entityHurt(LivingDamageEvent.Post event) {
        Entity source = event.getSource().getEntity();
        Entity receiver = event.getEntity();

        if (source instanceof Player player) {
            Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);

            if (handler.isPresent()) {
                if (handler.get().isEquipped(stack -> {
                    return stack.getItem() instanceof HandWarmersCotton handWarmersCotton;
                })) {
                    Optional<SlotResult> slotResult = handler.get().findFirstCurio(stack -> {
                        return stack.getItem() instanceof HandWarmersCotton handWarmersCotton;
                    });

                    if (slotResult.isPresent()) {
                        SlotResult slot = slotResult.get();
                        ItemStack itemStack = slot.stack();

                        if (!itemStack.isEmpty()) {
                            int durabilityLeft = itemStack.getMaxDamage() - itemStack.getDamageValue();
                            if (!(durabilityLeft > 1)) return;

                            Holder<Enchantment> vampHolder = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(WeaversParadiseEnchantments.VAMPIRISM);

                            int level = itemStack.getEnchantmentLevel(vampHolder);

                            if (level > 0) {
                                float a = level / 15.0f;

                                player.heal(event.getNewDamage() * a);
                            }
                        }
                    }
                }
            }
        }
    }
}
