package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.data.DyeCrushingRecipe;
import xox.labvorty.weaversparadise.data.WeaversParadiseDyeCoreRecipe;
import xox.labvorty.weaversparadise.data.WeaversParadiseDyeCoreUpgradeRecipe;
import xox.labvorty.weaversparadise.data.WeaversParadiseQualityResultRecipe;

public class WeaversParadiseRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, WeaversParadise.MODID);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WeaversParadiseQualityResultRecipe>> QUALITY_RESULT_RECIPE = RECIPE_SERIALIZERS.register("quality_crafting", () -> WeaversParadiseQualityResultRecipe.QualityResultRecipeSerializer.INSTANCE);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WeaversParadiseDyeCoreRecipe>> DYE_CORE_RECIPE = RECIPE_SERIALIZERS.register("dye_core", () -> WeaversParadiseDyeCoreRecipe.DyeCoreRecipeSerializer.INSTANCE);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WeaversParadiseDyeCoreUpgradeRecipe>> DYE_CORE_UPGRADE_RECIPE = RECIPE_SERIALIZERS.register("dye_core_upgrade", () -> WeaversParadiseDyeCoreUpgradeRecipe.DyeCoreUpgadeRecipeSerializer.INSTANCE);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DyeCrushingRecipe>> DYE_CRUSHING_RECIPE = RECIPE_SERIALIZERS.register("dye_crushing", () -> DyeCrushingRecipe.DyeCrushingRecipeSerializer.INSTANCE);
}
