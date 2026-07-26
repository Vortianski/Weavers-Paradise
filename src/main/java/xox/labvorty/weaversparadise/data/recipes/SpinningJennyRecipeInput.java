package xox.labvorty.weaversparadise.data.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record SpinningJennyRecipeInput(ItemStack mainIngredient, ItemStack catalyst) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int index) {
        switch (index) {
            case 0 -> {
                return mainIngredient;
            }

            case 1 -> {
                return catalyst;
            }

            default -> throw new IllegalArgumentException("No item for index " + index);
        }
    }

    @Override
    public int size() {
        return 2;
    }
}
