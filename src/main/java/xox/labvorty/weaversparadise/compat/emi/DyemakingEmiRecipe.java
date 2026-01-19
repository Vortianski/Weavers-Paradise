package xox.labvorty.weaversparadise.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.List;

public class DyemakingEmiRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> input;
    private final List<EmiIngredient> output;

    public DyemakingEmiRecipe(ResourceLocation id, List<EmiIngredient> inputs, List<EmiIngredient> outputs) {
        this.id = id;
        this.input = inputs;
        this.output = outputs;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EmiCompat.DYEMAKING_RECIPE_CATEGORY;
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
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(EmiStack.of(WeaversParadiseItems.BOTTLED_DYE.get()));
    }

    @Override
    public int getDisplayWidth() {
        return 176;
    }

    @Override
    public int getDisplayHeight() {
        return 121;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/recipes/dyemaking.png"
                ),
                0,
                0,
                176,
                111,
                0,
                0,
                176, 111,
                176, 111
        );

        widgets.addSlot(input.get(0), 138, 70).drawBack(false);

        widgets.addSlot(input.get(1), 21, 76).drawBack(false);

        widgets.addSlot(input.get(2), 21, 58).drawBack(false);

        //dyes
        widgets.addSlot(input.get(3), 95, 47).drawBack(false);
        widgets.addSlot(input.get(4), 77, 47).drawBack(false);
        widgets.addSlot(input.get(5), 59, 47).drawBack(false);

        widgets.addSlot(input.get(6), 95, 29).drawBack(false);
        widgets.addSlot(input.get(7), 77, 29).drawBack(false);
        widgets.addSlot(input.get(8), 59, 29).drawBack(false);

        widgets.addSlot(input.get(9), 95, 11).drawBack(false);
        widgets.addSlot(input.get(10), 77, 11).drawBack(false);
        widgets.addSlot(input.get(11), 59, 11).drawBack(false);

        widgets.addSlot(output.get(0), 146, 32).drawBack(false);
    }
}
