package xox.labvorty.weaversparadise.mixins;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xox.labvorty.weaversparadise.items.clothing.defined.DoubleSidedClothingItem;
import xox.labvorty.weaversparadise.items.clothing.defined.SingleSidedClothingItem;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(
            method = "<init>(Lnet/minecraft/world/level/ItemLike;)V",
            at = @At("TAIL")
    )
    private void weaversparadise$initializeClothing(ItemLike pItem, CallbackInfo ci) {
        ItemStack itemStack = (ItemStack)(Object)this;

        if (itemStack != null && !itemStack.isEmpty()) {
            weaversparadise$initializeClothingInternal(itemStack);
        }
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/level/ItemLike;I)V",
            at = @At("TAIL")
    )
    private void weaversparadise$initializeClothing(ItemLike pItem, int pCount, CallbackInfo ci) {
        ItemStack itemStack = (ItemStack)(Object)this;

        if (itemStack != null && !itemStack.isEmpty()) {
            weaversparadise$initializeClothingInternal(itemStack);
        }
    }

    @Unique
    private void weaversparadise$initializeClothingInternal(ItemStack itemStack) {
        if (itemStack.getOrCreateTag().contains("quality")) return;

        if (itemStack.getItem() instanceof SingleSidedClothingItem singleSidedClothingItem) {
            itemStack.setTag(singleSidedClothingItem.obtainDefault());
        }

        if (itemStack.getItem() instanceof DoubleSidedClothingItem doubleSidedClothingItem) {
            itemStack.setTag(doubleSidedClothingItem.obtainDefault());
        }
    }
}
