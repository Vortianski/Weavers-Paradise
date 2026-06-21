package xox.labvorty.weaversparadise.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class ClothcraftingJEICategory implements IRecipeCategory<ClothcraftingJEIRecipe> {
    public static final RecipeType<ClothcraftingJEIRecipe> TYPE =
            RecipeType.create("weaversparadise", "clothcrafting", ClothcraftingJEIRecipe.class);

    private final IDrawable icon;

    public ClothcraftingJEICategory(IGuiHelper guiHelper) {
        icon = guiHelper.createDrawableItemStack(
                new ItemStack(WeaversParadiseItems.CLOTHCRAFTING_STATION.get()));
    }

    @Override
    public @NotNull RecipeType<ClothcraftingJEIRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("jei.weaversparadise.clothcrafting");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return 192;
    }

    @Override
    public int getHeight() {
        return 118;
    }

    @Override
    public void draw(ClothcraftingJEIRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/recipes/clothcrafting_recipe_support.png"),
                0, 0, 0, 0, 192, 118, 192, 118
        );

        int max = recipe.getMaxScore();
        String scoreText = Component.translatable("weaversparadise.clothcrafting.score").getString()
                + " " + recipe.getMinScore() + "-" + (max >= 999 ? "∞" : max);

        guiGraphics.drawString(Minecraft.getInstance().font, scoreText, 47, 54, 0x000000, false);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ClothcraftingJEIRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addInputSlot(46, 0).addIngredients(recipe.getInput()).setSlotName("input");
        builder.addOutputSlot(146, 101).addItemStack(recipe.getOutput()).setSlotName("output");
        builder.addOutputSlot(120, 101).addItemStack(recipe.getSpoolReturn()).setSlotName("spool_return");
    }
}