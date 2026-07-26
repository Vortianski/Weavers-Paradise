package xox.labvorty.weaversparadise.compat.jei;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import xox.labvorty.weaversparadise.data.recipes.SpinningJennyRecipe;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpinningJennyJEIRecipe {
    private final ResourceLocation id;
    private final Ingredient input;
    private final Ingredient catalyst;
    private final ItemStack output;
    private final int countRequired;
    private final int craftTime;

    public SpinningJennyJEIRecipe(ResourceLocation id, Ingredient input, Ingredient catalyst, ItemStack output, int countRequired, int craftTime) {
        this.id = id;
        this.input = input;
        this.catalyst = catalyst;
        this.output = output;
        this.countRequired = countRequired;
        this.craftTime = craftTime;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Ingredient getInput() {
        return input;
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getCountRequired() {
        return countRequired;
    }

    public int getCraftTime() {
        return craftTime;
    }

    public static List<SpinningJennyJEIRecipe> generateRecipes() {
        List<SpinningJennyJEIRecipe> result = new ArrayList<>();
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) return result;

        RecipeManager rm = minecraft.level.getRecipeManager();
        Collection<RecipeHolder<SpinningJennyRecipe>> holders =
                rm.getAllRecipesFor(WeaversParadiseRecipes.SPINNING_JENNY_TYPE.get());

        for (RecipeHolder<SpinningJennyRecipe> holder : holders) {
            SpinningJennyRecipe recipe = holder.value();

            result.add(new SpinningJennyJEIRecipe(
                    holder.id(),
                    recipe.getInput(),
                    recipe.getCatalyst(),
                    recipe.getResultItem(minecraft.level.registryAccess()).copy(),
                    recipe.getCountRequired(),
                    recipe.getCraftTime()
            ));
        }

        return result;
    }
}