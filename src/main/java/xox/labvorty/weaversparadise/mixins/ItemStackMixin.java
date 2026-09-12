package xox.labvorty.weaversparadise.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xox.labvorty.weaversparadise.mixin_helpers.WPItemExtensions;

/**
 * Routes ItemStack.getMaxDamage() through the Item extension so NBT-driven
 * durability (clothing quality/metal damage values) works like on NeoForge.
 */
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    /**
     * @author weaversparadise
     * @reason Route max-damage queries through the NeoForge-style Item extension
     */
    @Overwrite
    public int getMaxDamage() {
        return ((WPItemExtensions) this.getItem()).getMaxDamage((ItemStack) (Object) this);
    }
}
