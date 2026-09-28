package xox.labvorty.weaversparadise.data.recipe;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class SpinningJennyRecipeInput extends SimpleContainer {
    public SpinningJennyRecipeInput(ItemStack mainIngredient, ItemStack catalyst) {
        super(2);
        setItem(0, mainIngredient);
        setItem(1, catalyst);
    }

    public ItemStack mainIngredient() {
        return getItem(0);
    }

    public ItemStack catalyst() {
        return getItem(1);
    }
}