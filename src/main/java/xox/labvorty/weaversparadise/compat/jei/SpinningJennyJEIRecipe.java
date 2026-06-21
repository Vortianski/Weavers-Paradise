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
    private final List<Ingredient> inputs;
    private final ItemStack output;
    private final int countRequired;

    public SpinningJennyJEIRecipe(ResourceLocation id, List<Ingredient> inputs, ItemStack output, int countRequired) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.countRequired = countRequired;
    }

    public ResourceLocation getId() {
        return id;
    }

    public List<Ingredient> getInput() {
        return inputs;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getCountRequired() {
        return countRequired;
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

            Ingredient ingredient = recipe.getInput();
            Ingredient countedIngredient = recipe.getInput();

            result.add(new SpinningJennyJEIRecipe(
                    holder.id(),
                    List.of(ingredient, countedIngredient),
                    recipe.getResultItem(minecraft.level.registryAccess()).copy(),
                    recipe.getCountRequired()
            ));
        }

        return result;
    }
}