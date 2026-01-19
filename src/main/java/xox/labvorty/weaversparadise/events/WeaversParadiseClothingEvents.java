package xox.labvorty.weaversparadise.events;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.LeatherGloves;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber
public class WeaversParadiseClothingEvents {
    private static List<ResourceKey<DamageType>> vanillaDurabilityFriendly = List.of(
            DamageTypes.CAMPFIRE,
            DamageTypes.CRAMMING,
            DamageTypes.DRAGON_BREATH,
            DamageTypes.DROWN,
            DamageTypes.FALL,
            DamageTypes.FIREBALL,
            DamageTypes.FLY_INTO_WALL,
            DamageTypes.FREEZE,
            DamageTypes.GENERIC,
            DamageTypes.GENERIC_KILL,
            DamageTypes.HOT_FLOOR,
            DamageTypes.IN_FIRE,
            DamageTypes.IN_WALL,
            DamageTypes.INDIRECT_MAGIC,
            DamageTypes.LAVA,
            DamageTypes.MAGIC,
            DamageTypes.ON_FIRE,
            DamageTypes.FELL_OUT_OF_WORLD,
            DamageTypes.OUTSIDE_BORDER,
            DamageTypes.STALAGMITE,
            DamageTypes.STARVE,
            DamageTypes.UNATTRIBUTED_FIREBALL,
            DamageTypes.WITHER
    );

    @SubscribeEvent
    public static void entityHurt(LivingDamageEvent.Post event) {
        ResourceKey<DamageType> damageType = event.getSource().typeHolder().getKey();
        LivingEntity receivingEntity = event.getEntity();
        Entity sourceEntity = event.getSource().getEntity();
        float damage = event.getOriginalDamage();
        int durabilityDamageHead = 0;
        int durabilityDamageBody = 0;
        int durabilityDamageLegs = 0;
        int durabilityDamageFeet = 0;
        Optional<ICuriosItemHandler> curiohandler = CuriosApi.getCuriosInventory(receivingEntity);

        if (damageType.equals(DamageTypes.FALLING_ANVIL)
                || damageType.equals(DamageTypes.FALLING_BLOCK)
                || damageType.equals(DamageTypes.FALLING_STALACTITE)
        ) {
            durabilityDamageHead = Mth.clamp((int)(damage * 0.3), 1, Integer.MAX_VALUE);
            durabilityDamageBody = Mth.clamp((int)(damage * 0.1), 1, Integer.MAX_VALUE);
            durabilityDamageLegs = Mth.clamp((int)(damage * 0.1), 1, Integer.MAX_VALUE);
            durabilityDamageFeet = Mth.clamp((int)(damage * 0.1), 1, Integer.MAX_VALUE);
        } else {
            durabilityDamageHead = Mth.clamp((int)(damage * 0.2), 1, Integer.MAX_VALUE);
            durabilityDamageBody = Mth.clamp((int)(damage * 0.2), 1, Integer.MAX_VALUE);
            durabilityDamageLegs = Mth.clamp((int)(damage * 0.2), 1, Integer.MAX_VALUE);
            durabilityDamageFeet = Mth.clamp((int)(damage * 0.2), 1, Integer.MAX_VALUE);
        }


        if (!vanillaDurabilityFriendly.contains(damageType)) {
            if (!receivingEntity.hasItemInSlot(EquipmentSlot.FEET) && !receivingEntity.hasItemInSlot(EquipmentSlot.LEGS)) {
                if (curiohandler.isPresent()) {
                    Optional<ICuriosItemHandler> itemInventory = CuriosApi.getCuriosInventory(receivingEntity);
                    String slotType = "legwear";
                    List<SlotResult> curios = itemInventory.map(handler -> {
                        ICurioStacksHandler stacksHandler = handler.getCurios().get(slotType);
                        if (stacksHandler == null) {
                            return Collections.<SlotResult>emptyList();
                        }
                        List<SlotResult> results = new ArrayList<>();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            results.add(new SlotResult(
                                    new SlotContext(slotType, receivingEntity, i, false, true),
                                    stacksHandler.getStacks().getStackInSlot(i)
                            ));
                        }
                        return results;
                    }).orElse(Collections.emptyList());
                    for (SlotResult entry : curios) {
                        ItemStack itemStack = entry.stack();
                        int receivedDamage = durabilityDamageFeet;

                        if (hasUnbreaking(itemStack)) {
                            receivedDamage = 0;
                            int unbreakingLevel = getUnbreakingLevel(itemStack);
                            for (int i = 0; i < unbreakingLevel; i++) {
                                RandomSource randomSource = RandomSource.create();
                                if (shouldReceiveDamage(itemStack, randomSource)) {
                                    receivedDamage += 1;
                                }
                            }
                        }

                        itemStack.hurtAndBreak(receivedDamage, receivingEntity, EquipmentSlot.MAINHAND);
                        curiohandler.get().setEquippedCurio(entry.slotContext().identifier(), entry.slotContext().index(), itemStack);
                    }
                }
            }

            if (!receivingEntity.hasItemInSlot(EquipmentSlot.CHEST)) {
                if (curiohandler.isPresent()) {
                    Optional<ICuriosItemHandler> itemInventory = CuriosApi.getCuriosInventory(receivingEntity);
                    String slotType = "upperwear";
                    List<SlotResult> curios = itemInventory.map(handler -> {
                        ICurioStacksHandler stacksHandler = handler.getCurios().get(slotType);
                        if (stacksHandler == null) {
                            return Collections.<SlotResult>emptyList();
                        }
                        List<SlotResult> results = new ArrayList<>();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            results.add(new SlotResult(
                                    new SlotContext(slotType, receivingEntity, i, false, true),
                                    stacksHandler.getStacks().getStackInSlot(i)
                            ));
                        }
                        return results;
                    }).orElse(Collections.emptyList());
                    for (SlotResult entry : curios) {
                        ItemStack itemStack = entry.stack();
                        int receivedDamage = durabilityDamageFeet;

                        if (hasUnbreaking(itemStack)) {
                            receivedDamage = 0;
                            int unbreakingLevel = getUnbreakingLevel(itemStack);
                            for (int i = 0; i < unbreakingLevel; i++) {
                                RandomSource randomSource = RandomSource.create();
                                if (shouldReceiveDamage(itemStack, randomSource)) {
                                    receivedDamage += 1;
                                }
                            }
                        }

                        itemStack.hurtAndBreak(receivedDamage, receivingEntity, EquipmentSlot.MAINHAND);
                        curiohandler.get().setEquippedCurio(entry.slotContext().identifier(), entry.slotContext().index(), itemStack);
                    }

                    String glovesslotType = "gloves";
                    List<SlotResult> glovescurios = itemInventory.map(handler -> {
                        ICurioStacksHandler stacksHandler = handler.getCurios().get(glovesslotType);
                        if (stacksHandler == null) {
                            return Collections.<SlotResult>emptyList();
                        }
                        List<SlotResult> results = new ArrayList<>();
                        for (int i = 0; i < stacksHandler.getSlots(); i++) {
                            results.add(new SlotResult(
                                    new SlotContext(glovesslotType, receivingEntity, i, false, true),
                                    stacksHandler.getStacks().getStackInSlot(i)
                            ));
                        }
                        return results;
                    }).orElse(Collections.emptyList());
                    for (SlotResult entry : glovescurios) {
                        ItemStack itemStack = entry.stack();

                        if (!(itemStack.getItem() instanceof LeatherGloves leatherGloves)) {
                            int receivedDamage = durabilityDamageFeet;

                            if (hasUnbreaking(itemStack)) {
                                receivedDamage = 0;
                                int unbreakingLevel = getUnbreakingLevel(itemStack);
                                for (int i = 0; i < unbreakingLevel; i++) {
                                    RandomSource randomSource = RandomSource.create();
                                    if (shouldReceiveDamage(itemStack, randomSource)) {
                                        receivedDamage += 1;
                                    }
                                }
                            }

                            itemStack.hurtAndBreak(receivedDamage, receivingEntity, EquipmentSlot.MAINHAND);
                            curiohandler.get().setEquippedCurio(entry.slotContext().identifier(), entry.slotContext().index(), itemStack);
                        }
                    }
                }
            }
        }

        if (damageType.equals(DamageTypes.FALL)) {
            if (curiohandler.isPresent()) {
                Optional<ICuriosItemHandler> itemInventory = CuriosApi.getCuriosInventory(receivingEntity);
                String slotType = "legwear";
                List<SlotResult> curios = itemInventory.map(handler -> {
                    ICurioStacksHandler stacksHandler = handler.getCurios().get(slotType);
                    if (stacksHandler == null) {
                        return Collections.<SlotResult>emptyList();
                    }
                    List<SlotResult> results = new ArrayList<>();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        results.add(new SlotResult(
                                new SlotContext(slotType, receivingEntity, i, false, true),
                                stacksHandler.getStacks().getStackInSlot(i)
                        ));
                    }
                    return results;
                }).orElse(Collections.emptyList());
            }
        }
    }

    public static boolean hasUnbreaking(ItemStack stack) {
        if (stack.isEnchanted()) {
            boolean hasUnbreaking = false;
            ItemEnchantments itemEnchantments = stack.getTagEnchantments();

            for (var entry : itemEnchantments.entrySet()) {
                Holder<Enchantment> enchantmentHolder = entry.getKey();
                if (enchantmentHolder.is(Enchantments.UNBREAKING)) {
                    hasUnbreaking = true;
                    break;
                }
            }

            return hasUnbreaking;
        }

        return false;
    }

    public static int getUnbreakingLevel(ItemStack stack) {
        if (stack.isEnchanted()) {
            int level = 0;
            ItemEnchantments itemEnchantments = stack.getTagEnchantments();

            for (var entry : itemEnchantments.entrySet()) {
                Holder<Enchantment> enchantmentHolder = entry.getKey();
                if (enchantmentHolder.is(Enchantments.UNBREAKING)) {
                    level = stack.getEnchantmentLevel(enchantmentHolder);
                    break;
                }
            }

            return level;
        }

        return 0;
    }

    public static boolean shouldReceiveDamage(ItemStack stack, RandomSource randomSource) {
        if (stack.isEnchanted()) {
            int level = 0;
            ItemEnchantments itemEnchantments = stack.getTagEnchantments();
            for (var entry : itemEnchantments.entrySet()) {
                Holder<Enchantment> enchantmentHolder = entry.getKey();
                if (enchantmentHolder.is(Enchantments.UNBREAKING)) {
                    level = stack.getEnchantmentLevel(enchantmentHolder);
                }
            }

            return randomSource.nextIntBetweenInclusive(0, level) == 0;
        }

        return true;
    }
}
