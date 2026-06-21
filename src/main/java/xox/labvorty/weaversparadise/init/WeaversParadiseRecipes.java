package xox.labvorty.weaversparadise.init;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.recipe.*;

public class WeaversParadiseRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<RecipeSerializer<QualityResultRecipe>> QUALITY_RECIPE = RECIPES.register(
            "quality_crafting",
            () -> QualityResultRecipe.WeaversParadiseQualityResultRecipeSerializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<StonecutterRecipe>> STONECUTTER_RECIPE = RECIPES.register(
            "stonecutter_crafting",
            () -> StonecutterRecipe.Serializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<DyeCoreUpgradeRecipe>> DYE_CORE_UPGRADE_RECIPE = RECIPES.register(
            "dye_core_upgrade",
            () -> DyeCoreUpgradeRecipe.Serializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<PlushieRenameRecipe>> PLUSHIE_RENAME_RECIPE = RECIPES.register(
            "plushie_renaming",
            () -> PlushieRenameRecipe.Serializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<ClothcraftingRecipe>> CLOTHCRAFTING_SERIALIZER = RECIPES.register(
            "clothcrafting",
            () -> ClothcraftingRecipe.Serializer.INSTANCE
    );
    public static final RegistryObject<RecipeSerializer<SpinningJennyRecipe>> SPINNING_JENNY_SERIALIZER = RECIPES.register(
            "spinning_jenny",
            () -> SpinningJennyRecipe.Serializer.INSTANCE
    );

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<RecipeType<ClothcraftingRecipe>> CLOTHCRAFTING_TYPE = RECIPE_TYPE.register(
            "clothcrafting",
            () -> new RecipeType<ClothcraftingRecipe>() {}
    );
    public static final RegistryObject<RecipeType<SpinningJennyRecipe>> SPINNING_JENNY_TYPE = RECIPE_TYPE.register(
            "spinning_jenny",
            () -> new RecipeType<SpinningJennyRecipe>() {}
    );
}
