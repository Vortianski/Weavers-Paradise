package xox.labvorty.weaversparadise.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class ClothcraftingEmiRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;

    public ClothcraftingEmiRecipe(ResourceLocation id, List<EmiIngredient> inputs, List<EmiStack> outputs) {
        this.id = id;
        this.input = inputs;
        this.output = outputs;
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
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
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
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/recipes/clothcrafting_recipe_support.png"
                ),
                0,
                0,
                192,
                118,
                0,
                0,
                192, 118,
                192, 118
        );
        widgets.addSlot(input.get(0), 46, 0).drawBack(false);

        CompoundTag tag = output.get(0).getItemStack().getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        int score = Mth.clamp(tag.getInt("quality") * 2, 0, 40);
        int maxscore = score + 1;

        widgets.addText(Component.literal(Component.translatable("weaversparadise.clothcrafting.score").getString() + " " + score + "-" + (maxscore > 20 ? "20+" : maxscore)), 47, 54, 0, false);

        widgets.addSlot(output.get(0), 146, 101).recipeContext(this);
    }
}
