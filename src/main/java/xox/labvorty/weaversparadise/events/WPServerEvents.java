package xox.labvorty.weaversparadise.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import xox.labvorty.weaversparadise.data.listeners.SculkPlayerListenerManager;
import xox.labvorty.weaversparadise.init.WeaversParadiseAttachmentTypes;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.init.WeaversParadiseMobEffects;
import xox.labvorty.weaversparadise.items.clothing.HandWarmersCottonItem;
import xox.labvorty.weaversparadise.items.clothing.LeatherGlovesItem;
import xox.labvorty.weaversparadise.util.WPTrinkets;

import java.util.Set;

/** Порт AttributeEvents + ClothingEvents + TickEvents + PlayerEvents (join/quit). */
public final class WPServerEvents {
    private WPServerEvents() {}

    private static boolean isDurabilityFriendly(DamageSource source) {
        return source.is(DamageTypes.CAMPFIRE) || source.is(DamageTypes.CRAMMING)
                || source.is(DamageTypes.DRAGON_BREATH) || source.is(DamageTypes.DROWN)
                || source.is(DamageTypes.FALL) || source.is(DamageTypes.FIREBALL)
                || source.is(DamageTypes.FLY_INTO_WALL) || source.is(DamageTypes.FREEZE)
                || source.is(DamageTypes.GENERIC) || source.is(DamageTypes.GENERIC_KILL)
                || source.is(DamageTypes.HOT_FLOOR) || source.is(DamageTypes.IN_FIRE)
                || source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.INDIRECT_MAGIC)
                || source.is(DamageTypes.LAVA) || source.is(DamageTypes.MAGIC)
                || source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.FELL_OUT_OF_WORLD)
                || source.is(DamageTypes.OUTSIDE_BORDER) || source.is(DamageTypes.STALAGMITE)
                || source.is(DamageTypes.STARVE) || source.is(DamageTypes.UNATTRIBUTED_FIREBALL)
                || source.is(DamageTypes.WITHER);
    }

    public static void register() {
        // ===== AttributeEvents: вампиризм хлопковых перчаток =====
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamage, damageTaken, blocked) ->
                attributeEvents(entity, source, damageTaken));

        // ===== ClothingEvents: износ одежды =====
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamage, damageTaken, blocked) ->
                clothingEvents(entity, source, baseDamage));

        // ===== TickEvents =====
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Порт PlayerTickEvent.Post
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                SculkPlayerListenerManager.tick(player);
                int pulse = player.getAttachedOrElse(WeaversParadiseAttachmentTypes.SCULK_PULSE, 0);
                if (pulse > 0) {
                    player.setAttached(WeaversParadiseAttachmentTypes.SCULK_PULSE, pulse - 1);
                }
            }

            // Порт EntityTickEvent.Pre (сервер)
            for (ServerLevel level : server.getAllLevels()) {
                for (Entity entity : level.getAllEntities()) {
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.setAttached(
                                WeaversParadiseAttachmentTypes.CHROMATIC_SHIFT_ACTIVE,
                                livingEntity.hasEffect(WeaversParadiseMobEffects.CHROMATIC_SHIFT)
                        );
                    }
                }
            }
        });

        // ===== PlayerEvents: SculkPlayerListenerManager attach/detach =====
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
                SculkPlayerListenerManager.attach(handler.player));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) ->
                SculkPlayerListenerManager.detach(handler.player));
    }

    private static void attributeEvents(LivingEntity entity, DamageSource source, float newDamage) {
        Entity attacker = source.getEntity();
        if (!(attacker instanceof Player player)) return;
        if (entity == player) return;

        if (!WPTrinkets.isEquipped(player, stack -> stack.getItem() instanceof HandWarmersCottonItem)) return;

        ItemStack itemStack = WPTrinkets.findFirstCurioStack(player,
                stack -> stack.getItem() instanceof HandWarmersCottonItem).orElse(null);
        if (itemStack == null || itemStack.isEmpty()) return;

        int durabilityLeft = itemStack.getMaxDamage() - itemStack.getDamageValue();
        if (durabilityLeft <= 1) return;

        Holder<Enchantment> vampHolder = player.level().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(WeaversParadiseEnchantments.VAMPIRISM);
        int level = EnchantmentHelper.getItemEnchantmentLevel(vampHolder, itemStack);
        if (level > 0) {
            float a = level / 15.0f;
            player.heal(newDamage * a);
        }
    }

    private static void clothingEvents(LivingEntity entity, DamageSource source, float originalDamage) {
        if (isDurabilityFriendly(source)) return;

        float damage = originalDamage;

        int durabilityDamageHead;
        int durabilityDamageBody;
        int durabilityDamageLegs;
        int durabilityDamageFeet;

        if (source.is(DamageTypes.FALLING_ANVIL)
                || source.is(DamageTypes.FALLING_BLOCK)
                || source.is(DamageTypes.FALLING_STALACTITE)) {
            durabilityDamageHead = Mth.clamp((int) (damage * 0.3F), 1, Integer.MAX_VALUE);
            durabilityDamageBody = Mth.clamp((int) (damage * 0.1F), 1, Integer.MAX_VALUE);
            durabilityDamageLegs = Mth.clamp((int) (damage * 0.1F), 1, Integer.MAX_VALUE);
            durabilityDamageFeet = Mth.clamp((int) (damage * 0.1F), 1, Integer.MAX_VALUE);
        } else {
            durabilityDamageHead = Mth.clamp((int) (damage * 0.2F), 1, Integer.MAX_VALUE);
            durabilityDamageBody = Mth.clamp((int) (damage * 0.2F), 1, Integer.MAX_VALUE);
            durabilityDamageLegs = Mth.clamp((int) (damage * 0.2F), 1, Integer.MAX_VALUE);
            durabilityDamageFeet = Mth.clamp((int) (damage * 0.2F), 1, Integer.MAX_VALUE);
        }

        if (!entity.hasItemInSlot(EquipmentSlot.HEAD)) {
            damageCurioSlot(entity, "head", durabilityDamageHead);
        }
        if (!entity.hasItemInSlot(EquipmentSlot.FEET)) {
            damageCurioSlot(entity, "legwear", durabilityDamageFeet);
        }
        if (!entity.hasItemInSlot(EquipmentSlot.LEGS)) {
            damageCurioSlot(entity, "pants", durabilityDamageLegs);
        }
        if (!entity.hasItemInSlot(EquipmentSlot.CHEST)) {
            damageCurioSlot(entity, "upperwear", durabilityDamageBody);
            damageCurioSlot(entity, "gloves", durabilityDamageBody);
            damageCurioSlot(entity, "necklace", durabilityDamageBody);
            damageCurioSlot(entity, "choker_trinket", durabilityDamageBody);
        }
    }

    private static void damageCurioSlot(LivingEntity entity, String curiosSlotId, int durabilityDamage) {
        var invOpt = WPTrinkets.getInventoryByCuriosId(entity, curiosSlotId);
        if (invOpt.isEmpty()) return;

        var inventory = invOpt.get();
        RandomSource random = entity.getRandom();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);

            if (!canClothingTakeDurabilityDamage(stack)) continue;

            int finalDamage = applyUnbreakingReduction(stack, durabilityDamage, random);
            damageClothingButKeepOneDurability(stack, finalDamage);

            // Перезапись тем же стаком помечает слот Trinkets изменённым (синхронизация клиенту)
            inventory.setItem(i, stack);
        }
    }

    private static void damageClothingButKeepOneDurability(ItemStack stack, int amount) {
        if (stack.isEmpty() || !stack.isDamageableItem() || amount <= 0) return;

        int currentDamage = stack.getDamageValue();
        int maxDamage = stack.getMaxDamage();
        int maxAllowedDamage = maxDamage - 1;

        if (currentDamage >= maxAllowedDamage) return;

        int newDamage = Math.min(currentDamage + amount, maxAllowedDamage);
        stack.setDamageValue(newDamage);
    }

    private static boolean canClothingTakeDurabilityDamage(ItemStack stack) {
        Set<Item> DAMAGEABLE_CLOTHING_ITEMS = Set.of(
                WeaversParadiseItems.THIGH_HIGHS_COTTON,
                WeaversParadiseItems.THIGH_HIGHS_SILK,
                WeaversParadiseItems.THIGH_HIGHS_WOOL,
                WeaversParadiseItems.HAND_WARMERS_COTTON,
                WeaversParadiseItems.HAND_WARMERS_SILK,
                WeaversParadiseItems.HAND_WARMERS_WOOL,
                WeaversParadiseItems.PANTS_COTTON,
                WeaversParadiseItems.PANTS_SILK,
                WeaversParadiseItems.PANTS_JEANS,
                WeaversParadiseItems.SHIRT_COTTON,
                WeaversParadiseItems.SHIRT_SILK,
                WeaversParadiseItems.SWEATER_WOOL,
                WeaversParadiseItems.CHOKER
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
            ItemEnchantments itemEnchantments = stack.getEnchantments();

            for (var entry : itemEnchantments.entrySet()) {
                Holder<Enchantment> enchantmentHolder = entry.getKey();
                if (enchantmentHolder.is(Enchantments.UNBREAKING)) {
                    level = EnchantmentHelper.getItemEnchantmentLevel(enchantmentHolder, stack);
                    break;
                }
            }

            return level;
        }

        return 0;
    }
}
