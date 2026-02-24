package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.data.*;

public class WeaversParadiseRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, WeaversParadise.MODID);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WeaversParadiseQualityResultRecipe>> QUALITY_RESULT_RECIPE = RECIPE_SERIALIZERS.register("quality_crafting", () -> WeaversParadiseQualityResultRecipe.QualityResultRecipeSerializer.INSTANCE);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WeaversParadiseDyeCoreUpgradeRecipe>> DYE_CORE_UPGRADE_RECIPE = RECIPE_SERIALIZERS.register("dye_core_upgrade", () -> WeaversParadiseDyeCoreUpgradeRecipe.DyeCoreUpgadeRecipeSerializer.INSTANCE);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WeaversParadisePlushieRenameRecipe>> PLUSHIE_RENAME_RECIPE = RECIPE_SERIALIZERS.register("plushie_renaming", () -> WeaversParadisePlushieRenameRecipe.WeaversParadisePlushieRenameRecipeSerializer.INSTANCE);
}
