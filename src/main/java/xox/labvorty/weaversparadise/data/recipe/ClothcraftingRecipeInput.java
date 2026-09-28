package xox.labvorty.weaversparadise.data.recipe;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class ClothcraftingRecipeInput extends SimpleContainer {
    public ClothcraftingRecipeInput(ItemStack spool) {
        super(1);
        setItem(0, spool);
    }

    public ItemStack spool() {
        return getItem(0);
    }
}