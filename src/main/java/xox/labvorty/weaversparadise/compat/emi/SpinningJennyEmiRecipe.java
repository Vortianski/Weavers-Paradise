package xox.labvorty.weaversparadise.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class SpinningJennyEmiRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;

    public SpinningJennyEmiRecipe(ResourceLocation id, List<EmiIngredient> inputs, List<EmiStack> outputs) {
        this.id = id;
        this.input = inputs;
        this.output = outputs;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EmiCompat.SPINNINGJENNY_RECIPE_CATEGORY;
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
        return 240;
    }

    @Override
    public int getDisplayHeight() {
        return 155;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/recipes/spinning_jenny.png"
                ),
                0,
                0,
                240,
                145,
                0,
                0,
                240, 145,
                240, 145
        );

        widgets.add(new Widget() {
            @Override
            public Bounds getBounds() {
                return new Bounds(0, 0, 31, 20);
            }

            @Override
            public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
                Minecraft minecraft = Minecraft.getInstance();
                int ticks = (int)minecraft.level.getGameTime();

                int cycleticks = ticks % 100;

                guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/arrow_full.png"), 152, 88, 0, 0, 31, 20, 31, 20);
                guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/arrow_empty.png"), 152, 88, 0, 0, (int)(31 * (1 - (cycleticks / 100.0))), 20, 31, 20);
            }
        });

        widgets.addSlot(input.get(0), 36 + 7, 73 + 15).drawBack(false);

        widgets.addSlot(output.get(0), 53 + 7, 73 + 15).drawBack(false).recipeContext(this);

        widgets.addSlot(input.get(1), 183 + 7, 74 + 15).drawBack(false);
    }
}
