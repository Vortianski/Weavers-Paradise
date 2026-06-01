package xox.labvorty.weaversparadise.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.recipe.WeaversParadiseDyeCoreUpgradeRecipe;
import xox.labvorty.weaversparadise.data.recipe.WeaversParadisePlushieRenameRecipe;
import xox.labvorty.weaversparadise.data.recipe.WeaversParadiseQualityResultRecipe;
import xox.labvorty.weaversparadise.data.recipe.WeaversParadiseStonecutterRecipe;

public class WeaversParadiseRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<RecipeSerializer<WeaversParadiseQualityResultRecipe>> QUALITY_RECIPE = RECIPES.register(
            "quality_crafting",
            () -> WeaversParadiseQualityResultRecipe.WeaversParadiseQualityResultRecipeSerializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<WeaversParadiseStonecutterRecipe>> STONECUTTER_RECIPE = RECIPES.register(
            "stonecutter_crafting",
            () -> WeaversParadiseStonecutterRecipe.Serializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<WeaversParadiseDyeCoreUpgradeRecipe>> DYE_CORE_UPGRADE_RECIPE = RECIPES.register(
            "dye_core_upgrade",
            () -> WeaversParadiseDyeCoreUpgradeRecipe.Serializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<WeaversParadisePlushieRenameRecipe>> PLUSHIE_RENAME_RECIPE = RECIPES.register(
            "plushie_renaming",
            () -> WeaversParadisePlushieRenameRecipe.Serializer.INSTANCE
    );
}
