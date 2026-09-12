package xox.labvorty.weaversparadise.data.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

public record ClothcraftingRecipeInput(ItemStack spool) implements RecipeInput {
    @Override
    public @NotNull ItemStack getItem(int slot) {
        return slot == 0 ? spool : ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 1;
    }
}