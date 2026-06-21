package xox.labvorty.weaversparadise.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

@JeiPlugin
public class JeiCompat implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration categoryRegistration) {
        categoryRegistration.addRecipeCategories(
                new ClothcraftingJEICategory(categoryRegistration.getJeiHelpers().getGuiHelper())
        );
        categoryRegistration.addRecipeCategories(
                new DyemakingJEICategory(categoryRegistration.getJeiHelpers().getGuiHelper())
        );
        categoryRegistration.addRecipeCategories(
                new SpinningJennyJEICategory(categoryRegistration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration recipeRegistration) {
        recipeRegistration.addRecipes(
                ClothcraftingJEICategory.TYPE,
                ClothcraftingJEIRecipe.buildAll()
        );

        recipeRegistration.addRecipes(
                DyemakingJEICategory.TYPE,
                DyemakingJEIRecipe.makeDyeRecipes()
        );

        recipeRegistration.addRecipes(
                SpinningJennyJEICategory.TYPE,
                SpinningJennyJEIRecipe.generateRecipes()
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration recipeCatalystRegistration) {
        recipeCatalystRegistration.addRecipeCatalyst(
                new ItemStack(WeaversParadiseItems.CLOTHCRAFTING_STATION.get()),
                ClothcraftingJEICategory.TYPE
        );

        recipeCatalystRegistration.addRecipeCatalyst(
                new ItemStack(WeaversParadiseItems.DYEMAKING_BLOCK.get()),
                DyemakingJEICategory.TYPE
        );

        recipeCatalystRegistration.addRecipeCatalyst(
                new ItemStack(WeaversParadiseItems.SPINNING_JENNY.get()),
                SpinningJennyJEICategory.TYPE
        );
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath("weaversparadise", "jei_plugin");
    }
}
