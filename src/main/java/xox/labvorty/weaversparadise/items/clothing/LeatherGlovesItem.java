package xox.labvorty.weaversparadise.items.clothing;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.NotNull;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;

@SuppressWarnings("deprecation")
public class LeatherGlovesItem extends Item implements Trinket {
    public LeatherGlovesItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .durability(64)
        );
        TrinketsApi.registerTrinket(this, this);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack itemStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    public boolean supportsEnchantment(@NotNull ItemStack itemStack, Holder<Enchantment> enchantment) {
        return enchantment.is(Enchantments.UNBREAKING) || enchantment.is(Enchantments.VANISHING_CURSE) || enchantment.is(Enchantments.MENDING);
    }

    public boolean isBookEnchantable(@NotNull ItemStack itemStack, @NotNull ItemStack book) {
        return EnchantmentHelper.getEnchantmentsForCrafting(book).keySet().stream().anyMatch(holder -> holder.is(Enchantments.MENDING) || holder.is(Enchantments.UNBREAKING) || holder.is(Enchantments.VANISHING_CURSE) || holder.is(Enchantments.BINDING_CURSE));
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, @NotNull ItemStack repairCandidate) {
        return repairCandidate.is(Items.LEATHER);
    }

    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slotReference, LivingEntity entity) {
        LivingEntity livingEntity = entity;
        if (livingEntity instanceof Player player && player.isCreative()) {
            return true;
        }

        return EnchantmentHelper.getEnchantmentsForCrafting(stack)
                .keySet()
                .stream()
                .noneMatch(holder -> holder.is(Enchantments.BINDING_CURSE));
    }

    public boolean shouldReceiveDamage(ItemStack stack, RandomSource randomSource) {
        if (stack.isEnchanted()) {
            int level = 0;
            ItemEnchantments itemEnchantments = stack.getEnchantments();
            for (var entry : itemEnchantments.entrySet()) {
                Holder<Enchantment> enchantmentHolder = entry.getKey();
                if (enchantmentHolder.is(Enchantments.UNBREAKING)) {
                    level = EnchantmentHelper.getItemEnchantmentLevel(enchantmentHolder, stack);
                }
            }

            return randomSource.nextIntBetweenInclusive(0, level) == 0;
        }

        return true;
    }
}
