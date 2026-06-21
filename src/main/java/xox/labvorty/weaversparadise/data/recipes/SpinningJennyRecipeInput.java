package xox.labvorty.weaversparadise.data.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record SpinningJennyRecipeInput(ItemStack mainIngredient) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int index) {
        if (index == 0) return mainIngredient;
        throw new IllegalArgumentException("No item for index " + index);
    }

    @Override
    public int size() {
        return 1;
    }
}
