package xox.labvorty.weaversparadise.mixins;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import xox.labvorty.weaversparadise.mixin_helpers.WPItemExtensions;

/**
 * Adds NeoForge-style Item extension methods to vanilla Item (NeoForge parity).
 * Mod items override these with per-item logic (the @Override annotations in
 * item classes were stripped for compilation; overrides still apply at runtime).
 */
@Mixin(Item.class)
public abstract class ItemExtensionMixin implements WPItemExtensions {
    @Override
    public int getMaxDamage(ItemStack stack) {
        // Exact vanilla ItemStack.getMaxDamage() logic (no-args variant source)
        return stack.getOrDefault(DataComponents.MAX_DAMAGE, 0);
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return ((Item) (Object) this).getEnchantmentValue();
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        Item remainder = ((Item) (Object) this).getCraftingRemainingItem();
        return remainder == null ? ItemStack.EMPTY : new ItemStack(remainder);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return ((Item) (Object) this).hasCraftingRemainingItem();
    }
}
