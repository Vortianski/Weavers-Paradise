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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class ClothcraftingJEICategory implements IRecipeCategory<ClothcraftingJEIRecipe> {
    private final IDrawable icon;
    public static final RecipeType<ClothcraftingJEIRecipe> TYPE = RecipeType.create("weaversparadise", "clothcrafting", ClothcraftingJEIRecipe.class);

    public ClothcraftingJEICategory(
        IGuiHelper guiHelper
    ) {
        icon = guiHelper.createDrawableItemStack(
                new ItemStack(
                        WeaversParadiseItems.CLOTHCRAFTING_STATION.get()
                )
        );
    }

    @Override
    public RecipeType<ClothcraftingJEIRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
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
    public void draw(ClothcraftingJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        CompoundTag tag = recipe.getOutput().getOrCreateTag();

        int score = Mth.clamp(
                tag.getInt("quality") * 2,
                0,
                40
        );

        int maxscore = score + 1;

        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/recipes/clothcrafting_recipe_support.png"
                ),
                0,
                0,
                0,
                0,
                192,
                118,
                192,
                118
        );

        guiGraphics.drawString(
                Minecraft.getInstance().font,
                Component.literal(
                        "Score: " +
                                score +
                                "-" +
                                (maxscore > 20 ? "20+" : maxscore)
                ),
                47,
                54,
                0xFFFFFF
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ClothcraftingJEIRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(46, 0).addIngredients(recipe.getInput());
        builder.addOutputSlot(146, 101).addItemStack(recipe.getOutput());
    }
}
