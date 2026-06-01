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
import org.jetbrains.annotations.Nullable;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class SpinningJennyJEICategory implements IRecipeCategory<SpinningJennyJEIRecipe> {
    private final IDrawable icon;
    public static final RecipeType<SpinningJennyJEIRecipe> TYPE = RecipeType.create("weaversparadise", "spinning_jenny", SpinningJennyJEIRecipe.class);

    public SpinningJennyJEICategory(
            IGuiHelper guiHelper
    ) {
        icon = guiHelper.createDrawableItemStack(
                new ItemStack(
                        WeaversParadiseItems.SPINNING_JENNY.get()
                )
        );
    }


    @Override
    public RecipeType<SpinningJennyJEIRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.weaversparadise.spinning_jenny");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public int getWidth() {
        return 240;
    }

    @Override
    public int getHeight() {
        return 155;
    }

    @Override
    public void draw(SpinningJennyJEIRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath(
                        "weaversparadise",
                        "textures/recipes/spinning_jenny.png"
                ),
                0,
                0,
                240,
                155,
                0,
                0,
                240, 155,
                240, 155
        );

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft != null && minecraft.level != null) {
            int ticks = (int)minecraft.level.getGameTime();

            int cycleticks = ticks % 100;

            guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/arrow_full.png"), 152, 93, 0, 0, 31, 20, 31, 20);
            guiGraphics.blit(ResourceLocation.parse("weaversparadise:textures/screens/arrow_empty.png"), 152, 93, 0, 0, (int)(31 * (1 - (cycleticks / 100.0))), 20, 31, 20);
        }
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SpinningJennyJEIRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(36 + 8, 80 + 15).addIngredients(recipe.getInput().get(0));
        builder.addInputSlot(183 + 8, 82 + 15).addIngredients(recipe.getInput().get(1));

        builder.addOutputSlot(53 + 8, 80 + 15).addItemStack(recipe.getOutput());
    }
}
