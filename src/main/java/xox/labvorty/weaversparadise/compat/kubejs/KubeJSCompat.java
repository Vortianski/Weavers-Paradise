package xox.labvorty.weaversparadise.compat.kubejs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.data.recipes.ClothcraftingRecipe;
import xox.labvorty.weaversparadise.data.recipes.SpinningJennyRecipe;

public class KubeJSCompat implements KubeJSPlugin {
    private static final RecipeKey<?> INPUT = IngredientComponent.INGREDIENT.inputKey("input");
    private static final RecipeKey<?> CATALYST = IngredientComponent.INGREDIENT.inputKey("catalyst");
    private static final RecipeKey<?> RESULT = ItemStackComponent.ITEM_STACK.outputKey("result");
    private static final RecipeKey<?> COUNT_REQUIRED = NumberComponent.INT.otherKey("count_required").optional(1);
    private static final RecipeKey<?> CRAFT_TIME = NumberComponent.INT.otherKey("craft_time").optional(100);

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry event) {
        RecipeKey<?> ingredient = IngredientComponent.INGREDIENT.inputKey("ingredient");
        RecipeKey<?> spoolReturn = ItemStackComponent.ITEM_STACK.outputKey("spool_return");
        RecipeKey<?> gameDuration = NumberComponent.INT.otherKey("game_duration");
        RecipeKey<?> tiers = OutputTierListComponent.OUTPUT_TIER_LIST.otherKey("tiers");
        RecipeKey<?> spoolCost = NumberComponent.INT.otherKey("spool_cost").optional(6);
        RecipeKey<?> spoolReturnCount = NumberComponent.INT.otherKey("spool_return_count").optional(6);

        event.register(
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "clothcrafting"),
                new RecipeSchema(ingredient, spoolReturn, gameDuration, tiers, spoolCost, spoolReturnCount)
                        .constructor(ingredient, spoolReturn, gameDuration, tiers)
                        .constructor(ingredient, spoolReturn, gameDuration, tiers, spoolCost, spoolReturnCount)
        );

        event.register(
                ResourceLocation.fromNamespaceAndPath("weaversparadise", "spinning_jenny"),
                new RecipeSchema(
                        INPUT,
                        CATALYST,
                        RESULT,
                        COUNT_REQUIRED,
                        CRAFT_TIME
                ).constructor(INPUT, CATALYST, RESULT)
                        .constructor(INPUT, CATALYST, RESULT, COUNT_REQUIRED, CRAFT_TIME)
        );
    }

    @Override
    public void registerBindings(BindingRegistry event) {

    }

    @Override
    public void registerEvents(EventGroupRegistry event) {

    }
}
