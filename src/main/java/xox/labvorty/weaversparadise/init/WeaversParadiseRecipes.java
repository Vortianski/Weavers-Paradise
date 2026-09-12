package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.data.recipes.*;

public class WeaversParadiseRecipes {
    public static final RecipeSerializer<QualityResultRecipe> QUALITY_RESULT_SERIALIZER = registerSerializer(
            "quality_crafting", QualityResultRecipe.Serializer.INSTANCE);
    public static final RecipeSerializer<QualityResultShapelessRecipe> QUALITY_SHAPELESS_RESULT_SERIALIZER = registerSerializer(
            "quality_shapeless_crafting", QualityResultShapelessRecipe.Serializer.INSTANCE);
    public static final RecipeSerializer<DyeCoreUpgradeRecipe> DYE_CORE_UPGRADE_SERIALIZER = registerSerializer(
            "dye_core_upgrade", DyeCoreUpgradeRecipe.Serializer.INSTANCE);
    public static final RecipeSerializer<PlushieRenameRecipe> PLUSHIE_RENAME_SERIALIZER = registerSerializer(
            "plushie_renaming", PlushieRenameRecipe.Serializer.INSTANCE);
    public static final RecipeSerializer<SpinningJennyRecipe> SPINNING_JENNY_SERIALIZER = registerSerializer(
            "spinning_jenny", SpinningJennyRecipe.Serializer.INSTANCE);
    public static final RecipeSerializer<ClothcraftingRecipe> CLOTHCRAFTING_SERIALIZER = registerSerializer(
            "clothcrafting", ClothcraftingRecipe.Serializer.INSTANCE);

    public static final RecipeType<SpinningJennyRecipe> SPINNING_JENNY_TYPE = registerType(
            "spinning_jenny", new RecipeType<>() {});
    public static final RecipeType<ClothcraftingRecipe> CLOTHCRAFTING_TYPE = registerType(
            "clothcrafting", new RecipeType<>() {});

    private static <T extends RecipeSerializer<?>> T registerSerializer(String name, T serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
                ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, name), serializer);
    }

    private static <T extends RecipeType<?>> T registerType(String name, T type) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE,
                ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, name), type);
    }

    public static void register() {
    }
}
