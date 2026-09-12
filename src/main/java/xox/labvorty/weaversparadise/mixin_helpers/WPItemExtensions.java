package xox.labvorty.weaversparadise.mixin_helpers;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * NeoForge-style Item extensions, ported to Fabric.
 * Implemented by {@link xox.labvorty.weaversparadise.mixins.ItemExtensionMixin}
 * so all vanilla and modded items expose these methods.
 */
public interface WPItemExtensions {
    int getMaxDamage(ItemStack stack);

    int getEnchantmentValue(ItemStack stack);

    boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment);

    boolean isBookEnchantable(ItemStack stack, ItemStack book);

    ItemStack getCraftingRemainingItem(ItemStack stack);

    boolean hasCraftingRemainingItem(ItemStack stack);
}
