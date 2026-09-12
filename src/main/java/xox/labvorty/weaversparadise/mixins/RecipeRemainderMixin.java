package xox.labvorty.weaversparadise.mixins;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xox.labvorty.weaversparadise.mixin_helpers.WPItemExtensions;

/**
 * Consults the Item extension for stack-sensitive crafting remainders
 * (NeoForge parity for mortar/shears/cotton-boll behavior).
 */
@Mixin(Recipe.class)
public interface RecipeRemainderMixin {
    @Inject(method = "getRemainingItems", at = @At("HEAD"), cancellable = true)
    private void weaversparadise$customRemainders(RecipeInput input, CallbackInfoReturnable<NonNullList<ItemStack>> cir) {
        boolean custom = false;
        for (int i = 0; i < input.size(); i++) {
            Item item = input.getItem(i).getItem();
            if (item instanceof WPItemExtensions ext && ext.hasCraftingRemainingItem(input.getItem(i))) {
                custom = true;
                break;
            }
        }
        if (!custom) {
            return;
        }
        NonNullList<ItemStack> list = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < list.size(); ++i) {
            ItemStack stack = input.getItem(i);
            Item item = stack.getItem();
            if (item instanceof WPItemExtensions ext && ext.hasCraftingRemainingItem(stack)) {
                list.set(i, ext.getCraftingRemainingItem(stack));
            } else if (item.hasCraftingRemainingItem()) {
                list.set(i, new ItemStack(item.getCraftingRemainingItem()));
            }
        }
        cir.setReturnValue(list);
    }
}
