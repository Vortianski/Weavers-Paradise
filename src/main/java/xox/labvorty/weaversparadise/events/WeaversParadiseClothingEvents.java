package xox.labvorty.weaversparadise.events;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.clothing.LeatherGlovesItem;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    private static final Set<String> DAMAGEABLE_CURIO_SLOTS = Set.of(
            "legwear",
            "gloves",
            "pants",
            "upperwear",
            "necklace",
            "choker_trinket"
    );

    @SubscribeEvent
    public static void entityHurtNew(LivingDamageEvent.Post event) {
        ResourceKey<DamageType> damageType = event.getSource().typeHolder().getKey();
        LivingEntity entity = event.getEntity();
        float damage = event.getOriginalDamage();

        int durabilityDamageHead;
        int durabilityDamageBody;
        int durabilityDamageLegs;
        int durabilityDamageFeet;

        if (damageType.equals(DamageTypes.FALLING_ANVIL)
                || damageType.equals(DamageTypes.FALLING_BLOCK)
                || damageType.equals(DamageTypes.FALLING_STALACTITE)) {
            durabilityDamageHead = Mth.clamp((int)(damage * 0.3F), 1, Integer.MAX_VALUE);
            durabilityDamageBody = Mth.clamp((int)(damage * 0.1F), 1, Integer.MAX_VALUE);
            durabilityDamageLegs = Mth.clamp((int)(damage * 0.1F), 1, Integer.MAX_VALUE);
            durabilityDamageFeet = Mth.clamp((int)(damage * 0.1F), 1, Integer.MAX_VALUE);
        } else {
            durabilityDamageHead = Mth.clamp((int)(damage * 0.2F), 1, Integer.MAX_VALUE);
            durabilityDamageBody = Mth.clamp((int)(damage * 0.2F), 1, Integer.MAX_VALUE);
            durabilityDamageLegs = Mth.clamp((int)(damage * 0.2F), 1, Integer.MAX_VALUE);
            durabilityDamageFeet = Mth.clamp((int)(damage * 0.2F), 1, Integer.MAX_VALUE);
        }

        Optional<ICuriosItemHandler> optionalHandler = CuriosApi.getCuriosInventory(entity);
        if (optionalHandler.isEmpty()) {
            return;
        }

        ICuriosItemHandler handler = optionalHandler.get();

        if (!vanillaDurabilityFriendly.contains(damageType)) {
            // legwear
            if (!entity.hasItemInSlot(EquipmentSlot.FEET) && !entity.hasItemInSlot(EquipmentSlot.LEGS)) {
                damageCurioSlot(entity, handler, "legwear", durabilityDamageFeet);
                damageCurioSlot(entity, handler, "pants", durabilityDamageLegs);
            }

            // upper body
            if (!entity.hasItemInSlot(EquipmentSlot.CHEST)) {
                damageCurioSlot(entity, handler, "upperwear", durabilityDamageBody);
                damageCurioSlot(entity, handler, "gloves", durabilityDamageBody);
                damageCurioSlot(entity, handler, "necklace", durabilityDamageBody);
                damageCurioSlot(entity, handler, "choker_trinket", durabilityDamageBody);
            }
        }
    }

    private static void damageCurioSlot(
            LivingEntity entity,
            ICuriosItemHandler handler,
            String slotType,
            int durabilityDamage
    ) {
        ICurioStacksHandler stacksHandler = handler.getCurios().get(slotType);
        if (stacksHandler == null) {
            return;
        }

        RandomSource random = entity.getRandom();

        for (int i = 0; i < stacksHandler.getSlots(); i++) {
            ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);

            if (!canClothingTakeDurabilityDamage(stack)) {
                continue;
            }

            int finalDamage = applyUnbreakingReduction(stack, durabilityDamage, random);
            damageClothingButKeepOneDurability(stack, finalDamage);

            handler.setEquippedCurio(slotType, i, stack);
        }
    }

    private static void damageClothingButKeepOneDurability(ItemStack stack, int amount) {
        if (stack.isEmpty() || !stack.isDamageableItem() || amount <= 0) {
            return;
        }

        int currentDamage = stack.getDamageValue();
        int maxDamage = stack.getMaxDamage();

        int maxAllowedDamage = maxDamage - 1;

        if (currentDamage >= maxAllowedDamage) {
            return;
        }

        int newDamage = Math.min(currentDamage + amount, maxAllowedDamage);
        stack.setDamageValue(newDamage);
    }

    private static boolean canClothingTakeDurabilityDamage(ItemStack stack) {
        Set<Item> DAMAGEABLE_CLOTHING_ITEMS = Set.of(
                WeaversParadiseItems.THIGH_HIGHS_COTTON.get(),
                WeaversParadiseItems.THIGH_HIGHS_SILK.get(),
                WeaversParadiseItems.THIGH_HIGHS_WOOL.get(),
                WeaversParadiseItems.HAND_WARMERS_COTTON.get(),
                WeaversParadiseItems.HAND_WARMERS_SILK.get(),
                WeaversParadiseItems.HAND_WARMERS_WOOL.get(),
                WeaversParadiseItems.PANTS_COTTON.get(),
                WeaversParadiseItems.PANTS_SILK.get(),
                WeaversParadiseItems.PANTS_JEANS.get(),
                WeaversParadiseItems.SHIRT_COTTON.get(),
                WeaversParadiseItems.SHIRT_SILK.get(),
                WeaversParadiseItems.SWEATER_WOOL.get(),
                WeaversParadiseItems.CHOKER.get()
        );

        if (stack.isEmpty() || !stack.isDamageableItem()) {
            return false;
        }

        if (!DAMAGEABLE_CLOTHING_ITEMS.contains(stack.getItem())) {
            return false;
        }

        return !(stack.getItem() instanceof LeatherGlovesItem);
    }

    private static int applyUnbreakingReduction(ItemStack stack, int incomingDamage, RandomSource random) {
        if (incomingDamage <= 0) {
            return 0;
        }

        int unbreakingLevel = getUnbreakingLevel(stack);
        if (unbreakingLevel <= 0) {
            return incomingDamage;
        }

        int finalDamage = 0;
        for (int i = 0; i < incomingDamage; i++) {
            if (random.nextInt(unbreakingLevel + 1) == 0) {
                finalDamage++;
            }
        }

        return finalDamage;
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
}
