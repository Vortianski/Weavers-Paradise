package xox.labvorty.weaversparadise.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class DyemakingJEICategory implements IRecipeCategory<DyemakingJEIRecipe> {
    private final IDrawable icon;
    public static final RecipeType<DyemakingJEIRecipe> TYPE = RecipeType.create("weaversparadise", "dyemaking", DyemakingJEIRecipe.class);

    public DyemakingJEICategory(
            IGuiHelper guiHelper
    ) {
        icon = guiHelper.createDrawableItemStack(
                new ItemStack(
                        WeaversParadiseItems.DYEMAKING_BLOCK.get()
                )
        );
    }

    @Override
    public @NotNull RecipeType<DyemakingJEIRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("jei.weaversparadise.dyemaking");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return 176;
    }

    @Override
    public int getHeight() {
        return 121;
    }

    @Override
    public void draw(@NotNull DyemakingJEIRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.blit(ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/recipes/dyemaking.png"), 0, 0, 176, 111, 0, 0, 176, 111, 176, 111);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DyemakingJEIRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addInputSlot(138, 70).addIngredients(recipe.getInputs().get(0));
        builder.addInputSlot(21, 76).addIngredients(recipe.getInputs().get(1));
        builder.addInputSlot(21, 58).addIngredients(recipe.getInputs().get(2));

        builder.addInputSlot(95, 47).addIngredients(recipe.getInputs().get(3));
        builder.addInputSlot(77, 47).addIngredients(recipe.getInputs().get(4));
        builder.addInputSlot(59, 47).addIngredients(recipe.getInputs().get(5));
        builder.addInputSlot(95, 29).addIngredients(recipe.getInputs().get(6));
        builder.addInputSlot(77, 29).addIngredients(recipe.getInputs().get(7));
        builder.addInputSlot(59, 29).addIngredients(recipe.getInputs().get(8));
        builder.addInputSlot(95, 11).addIngredients(recipe.getInputs().get(9));
        builder.addInputSlot(77, 11).addIngredients(recipe.getInputs().get(10));
        builder.addInputSlot(59, 11).addIngredients(recipe.getInputs().get(11));

        builder.addOutputSlot(146, 32).addItemStack(recipe.getOutput());
    }
}
