package xox.labvorty.weaversparadise.compat.jei;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import xox.labvorty.weaversparadise.data.recipe.ClothcraftingRecipe;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

import java.util.ArrayList;
import java.util.List;

public class ClothcraftingJEIRecipe {
    private final ResourceLocation id;
    private final Ingredient input;
    private final int inputCount;
    private final ItemStack output;
    private final ItemStack spoolReturn;
    private final int minScore;
    private final int maxScore;

    public ClothcraftingJEIRecipe(
            ResourceLocation id,
            Ingredient input,
            int inputCount,
            ItemStack output,
            ItemStack spoolReturn,
            int minScore,
            int maxScore
    ) {
        this.id = id;
        this.input = input;
        this.inputCount = inputCount;
        this.output = output;
        this.spoolReturn = spoolReturn;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getInputCount() {
        return inputCount;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ItemStack getSpoolReturn() {
        return spoolReturn;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public static List<ClothcraftingJEIRecipe> buildAll() {
        List<ClothcraftingJEIRecipe> result = new ArrayList<>();

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) return result;

        Minecraft.getInstance().level.getRecipeManager()
                .getAllRecipesFor(WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get())
                .forEach(recipe -> {
                    ItemStack returnSpool = recipe.getSpoolReturn().copy();
                    returnSpool.setCount(recipe.getSpoolReturnCount());

                    List<ClothcraftingRecipe.OutputTier> tiers = recipe.getTiers();
                    for (int i = 0; i < tiers.size(); i++) {
                        ClothcraftingRecipe.OutputTier tier = tiers.get(i);

                        ItemStack output = tier.result().copy();
                        output.setCount(tier.count());

                        result.add(new ClothcraftingJEIRecipe(
                                ResourceLocation.fromNamespaceAndPath(recipe.getId().getNamespace(), recipe.getId().getPath() + "_tier_" + i),
                                recipe.getIngredient(),
                                recipe.getSpoolCost(),
                                output,
                                returnSpool.copy(),
                                tier.minScore(),
                                tier.maxScore()
                        ));
                    }
                });

        return result;
    }
}