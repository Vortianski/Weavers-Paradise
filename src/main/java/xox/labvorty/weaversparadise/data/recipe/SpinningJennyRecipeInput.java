package xox.labvorty.weaversparadise.data.recipe;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class SpinningJennyRecipeInput extends SimpleContainer {
    public SpinningJennyRecipeInput(ItemStack ingredient) {
        super(1);
        setItem(0, ingredient);
    }

    public ItemStack mainIngredient() {
        return getItem(0);
    }
}