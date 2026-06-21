package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.data.recipes.*;

public class WeaversParadiseRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, WeaversParadise.MODID);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<QualityResultRecipe>> QUALITY_RESULT_SERIALIZER = RECIPE_SERIALIZERS.register(
            "quality_crafting",
            () -> QualityResultRecipe.Serializer.INSTANCE
    );
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DyeCoreUpgradeRecipe>> DYE_CORE_UPGRADE_SERIALIZER = RECIPE_SERIALIZERS.register(
            "dye_core_upgrade",
            () -> DyeCoreUpgradeRecipe.Serializer.INSTANCE
    );
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PlushieRenameRecipe>> PLUSHIE_RENAME_SERIALIZER = RECIPE_SERIALIZERS.register(
            "plushie_renaming",
            () -> PlushieRenameRecipe.Serializer.INSTANCE
    );
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SpinningJennyRecipe>> SPINNING_JENNY_SERIALIZER = RECIPE_SERIALIZERS.register(
            "spinning_jenny",
            () -> SpinningJennyRecipe.Serializer.INSTANCE
    );
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ClothcraftingRecipe>> CLOTHCRAFTING_SERIALIZER = RECIPE_SERIALIZERS.register(
            "clothcrafting",
            () -> ClothcraftingRecipe.Serializer.INSTANCE
    );

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, WeaversParadise.MODID);
    public static final DeferredHolder<RecipeType<?>, RecipeType<SpinningJennyRecipe>> SPINNING_JENNY_TYPE = RECIPE_TYPES.register(
            "spinning_jenny",
            () -> new RecipeType<>() {}
    );
    public static final DeferredHolder<RecipeType<?>, RecipeType<ClothcraftingRecipe>> CLOTHCRAFTING_TYPE = RECIPE_TYPES.register(
            "clothcrafting",
            () -> new RecipeType<ClothcraftingRecipe>() {}
    );
}
