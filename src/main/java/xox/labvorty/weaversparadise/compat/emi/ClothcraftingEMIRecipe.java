package xox.labvorty.weaversparadise.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ClothcraftingEMIRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;
    private final int minScore;
    private final int maxScore;

    public ClothcraftingEMIRecipe(
            ResourceLocation id,
            List<EmiIngredient> inputs,
            List<EmiStack> outputs,
            int minScore,
            int maxScore
    ) {
        this.id = id;
        this.inputs = inputs;
        this.outputs = outputs;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EmiCompat.CLOTHCRAFTING_RECIPE_CATEGORY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    @Override
    public int getDisplayWidth() {
        return 192;
    }

    @Override
    public int getDisplayHeight() {
        return 118;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/recipes/clothcrafting_recipe_support.png"),
                0, 0, 192, 118, 0, 0, 192, 118, 192, 118
        );

        widgets.addSlot(inputs.getFirst(), 46, 0).drawBack(false);
        widgets.addSlot(outputs.get(0), 146, 101).recipeContext(this);

        if (outputs.size() > 1) {
            widgets.addSlot(outputs.get(1), 120, 101).recipeContext(this);
        }

        String scoreText = Component.translatable("weaversparadise.clothcrafting.score").getString() + " " + minScore + "-" + (maxScore >= 999 ? "∞" : maxScore);
        widgets.addText(Component.literal(scoreText), 47, 54, 0x000000, false);
    }
}