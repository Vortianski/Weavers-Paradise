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
import net.minecraft.world.item.enchantment.Enchantments;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xox.labvorty.weaversparadise.init.WeaversParadiseEnchantments;

public class LeatherGlovesItem extends Item implements ICurioItem {
    public LeatherGlovesItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .durability(64)
        );
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment holder) {
        return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return book.getAllEnchantments().keySet().stream().allMatch(
                holder -> {
                    return holder == Enchantments.MENDING || holder == Enchantments.UNBREAKING || holder == Enchantments.VANISHING_CURSE || holder == Enchantments.BINDING_CURSE;
                }
        );
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (livingEntity instanceof Player player && player.isCreative()) {
            return true;
        }

        return stack.getAllEnchantments()
                .keySet()
                .stream()
                .noneMatch(holder -> holder == Enchantments.BINDING_CURSE);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 15;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    public boolean shouldReceiveDamage(ItemStack stack, RandomSource randomSource) {
        if (stack.isEnchanted()) {
            int level = 0;

            for (var entry : stack.getAllEnchantments().entrySet()) {
                Enchantment enchantment = entry.getKey();
                if (enchantment == Enchantments.UNBREAKING) {
                    level = stack.getEnchantmentLevel(enchantment);
                }
            }

            return randomSource.nextIntBetweenInclusive(0, level) == 0;
        }

        return true;
    }
}
