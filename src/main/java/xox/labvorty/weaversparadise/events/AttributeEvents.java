package xox.labvorty.weaversparadise.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersCottonItem;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersSilkItem;

import java.util.Optional;

@Mod.EventBusSubscriber
public class AttributeEvents {
    @SubscribeEvent
    public static void entityHurt(LivingDamageEvent event) {
        Entity source = event.getSource().getEntity();
        Entity receiver = event.getEntity();

        if (source instanceof Player player && CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);

            if (handler.isPresent()) {
                handler.ifPresent(curiosHandler -> {
                    if (curiosHandler.isEquipped(stack -> stack.getItem() instanceof HandWarmersCottonItem handWarmersCottonItem)) {
                        Optional<SlotResult> optionalSlotResult = curiosHandler.findFirstCurio(stack -> stack.getItem() instanceof HandWarmersCottonItem handWarmersCottonItem);

                        if (optionalSlotResult.isPresent()) {
                            SlotResult slotResult = optionalSlotResult.get();
                            ItemStack itemStack = slotResult.stack();

                            if (!itemStack.isEmpty()) {
                                int durabilityLeft = itemStack.getMaxDamage() - itemStack.getDamageValue();
                                if (!(durabilityLeft > 1)) return;

                                int level = itemStack.getEnchantmentLevel(WeaversParadiseEnchantments.VAMPIRISM.get());

                                if (level > 0) {
                                    float a = level / 15.0f;

                                    player.heal(event.getAmount() * a);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void modifyMiningSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        LazyOptional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(player);

        if (handler.isPresent() && CommonConfig.ITEM_SPECIAL_ABILITIES.get()) {
            handler.ifPresent(curiosHandler -> {
                Optional<SlotResult> optionalSlotResult = curiosHandler.findFirstCurio(stack -> stack.getItem() instanceof HandWarmersSilkItem handWarmersSilkItem);

                if (optionalSlotResult.isPresent()) {
                    SlotResult slotResult = optionalSlotResult.get();
                    ItemStack itemStack = slotResult.stack();

                    if (!itemStack.isEmpty()) {
                        int durabilityLeft = itemStack.getMaxDamage() - itemStack.getDamageValue();
                        if (!(durabilityLeft > 1)) return;

                        int level = itemStack.getEnchantmentLevel(WeaversParadiseEnchantments.GRACEFUL.get());

                        if (level > 0) {
                            float a = level / 5.0f;

                            event.setNewSpeed(event.getOriginalSpeed() + (0.1f * a));
                        }
                    }
                }
            });
        }
    }
}
