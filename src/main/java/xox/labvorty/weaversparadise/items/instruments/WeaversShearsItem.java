package xox.labvorty.weaversparadise.items.instruments;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WeaversShearsItem extends Item {
    public WeaversShearsItem() {
        super(
                new Properties()
                        .stacksTo(1)
                        .durability(100)
        );
    }

    public @NotNull ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack remainder = stack.copy();

        int damage = remainder.getDamageValue() + 1;

        if (damage >= remainder.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        remainder.setDamageValue(damage);
        return remainder;
    }

    public boolean hasCraftingRemainingItem(@NotNull ItemStack itemStack) {
        return true;
    }
}
