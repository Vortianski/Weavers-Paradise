package xox.labvorty.weaversparadise.compat.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import xox.labvorty.weaversparadise.data.recipes.ClothcraftingRecipe;
import xox.labvorty.weaversparadise.data.recipes.SpinningJennyRecipe;
import xox.labvorty.weaversparadise.gui.screen.StringScreen;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EmiEntrypoint
public class EmiCompat implements EmiPlugin {
    public static final ResourceLocation CLOTHCRAFTING_SPRITESHEET = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/emi_simplified_textures.png");
    public static final EmiStack CLOTHCRAFTING_WORKSTATION = EmiStack.of(WeaversParadiseItems.CLOTHCRAFTING_STATION);
    public static final EmiRecipeCategory CLOTHCRAFTING_RECIPE_CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "clothcrafting"),
            CLOTHCRAFTING_WORKSTATION,
            new EmiTexture(CLOTHCRAFTING_SPRITESHEET, 0, 0, 16, 16)
    );

    public static final ResourceLocation SPINNINGJENNY_SPRITESHEET = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/emi_simplified_textures.png");
    public static final EmiStack SPINNINGJENNY_WORKSTATION = EmiStack.of(WeaversParadiseItems.SPINNING_JENNY);
    public static final EmiRecipeCategory SPINNINGJENNY_RECIPE_CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "spinning_jenny"),
            SPINNINGJENNY_WORKSTATION,
            new EmiTexture(SPINNINGJENNY_SPRITESHEET, 0, 0, 16, 16)
    );

    public static final ResourceLocation DYEMAKING_SPRITESHEET = ResourceLocation.fromNamespaceAndPath("weaversparadise", "textures/gui/emi_simplified_textures.png");
    public static final EmiStack DYEMAKING_WORKSTATION = EmiStack.of(WeaversParadiseItems.DYEMAKING_BLOCK);
    public static final EmiRecipeCategory DYEMAKING_RECIPE_CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "dyemaking"),
            DYEMAKING_WORKSTATION,
            new EmiTexture(DYEMAKING_SPRITESHEET, 0, 0, 16, 16)
    );

    @Override
    public void register(EmiRegistry registry) {
        registry.addExclusionArea(StringScreen.class, (screen, consumer) -> {
            int sx = screen.getGuiLeft() + 184 - 8;
            int sy = screen.getGuiTop() + 39 - 16;

            consumer.accept(new Bounds(
                    sx,
                    sy,
                    56,
                    105
            ));
        });

        registry.addCategory(CLOTHCRAFTING_RECIPE_CATEGORY);
        registry.addCategory(SPINNINGJENNY_RECIPE_CATEGORY);
        registry.addCategory(DYEMAKING_RECIPE_CATEGORY);

        registry.addWorkstation(CLOTHCRAFTING_RECIPE_CATEGORY, CLOTHCRAFTING_WORKSTATION);
        registry.addWorkstation(SPINNINGJENNY_RECIPE_CATEGORY, SPINNINGJENNY_WORKSTATION);
        registry.addWorkstation(DYEMAKING_RECIPE_CATEGORY, DYEMAKING_WORKSTATION);

        registry.addDeferredRecipes(deferred -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.level == null) return;
            RecipeManager rm = minecraft.level.getRecipeManager();
            Collection<RecipeHolder<ClothcraftingRecipe>> holders = rm.getAllRecipesFor(WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get());

            for (RecipeHolder<ClothcraftingRecipe> holder : holders) {
                ClothcraftingRecipe recipe = holder.value();
                ResourceLocation recipeId = holder.id();

                EmiIngredient ingredient = EmiIngredient.of(recipe.getIngredient()).setAmount(recipe.getSpoolCost());

                List<ClothcraftingRecipe.OutputTier> tiers = recipe.getTiers();
                for (int i = 0; i < tiers.size(); i++) {
                    ClothcraftingRecipe.OutputTier tier = tiers.get(i);

                    ItemStack result = tier.result().copy();
                    result.setCount(tier.count());

                    List<EmiStack> outputs = new ArrayList<>();
                    outputs.add(EmiStack.of(result));

                    ItemStack returnSpool = recipe.getSpoolReturn().copy();
                    returnSpool.setCount(recipe.getSpoolReturnCount());
                    outputs.add(EmiStack.of(returnSpool));

                    ResourceLocation emiId = recipeId.withPrefix("/").withSuffix("_tier_" + i);

                    deferred.accept(new ClothcraftingEMIRecipe(
                            emiId,
                            List.of(ingredient),
                            outputs,
                            tier.minScore(),
                            tier.maxScore()
                    ));
                }
            }
        });

        registry.addDeferredRecipes(deferred -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.level == null) return;
            RecipeManager rm = minecraft.level.getRecipeManager();
            Collection<RecipeHolder<SpinningJennyRecipe>> holders =
                    rm.getAllRecipesFor(WeaversParadiseRecipes.SPINNING_JENNY_TYPE.get());

            for (RecipeHolder<SpinningJennyRecipe> holder : holders) {
                SpinningJennyRecipe recipe = holder.value();

                EmiIngredient baseIngredient = EmiIngredient.of(recipe.getInput());
                EmiIngredient countedIngredient = EmiIngredient.of(recipe.getInput())
                        .setAmount(recipe.getCountRequired());

                ItemStack output = recipe.getResultItem(minecraft.level.registryAccess()).copy();

                deferred.accept(new SpinningJennyEMIRecipe(
                        holder.id(),
                        List.of(baseIngredient, countedIngredient),
                        List.of(EmiStack.of(output))
                ));
            }
        });

        List<String> dyeTypes = List.of(
                "default",
                "agender",
                "aroace",
                "aromantic",
                "asexual",
                "bisexual",
                "demiboy",
                "demigender",
                "demigirl",
                "gay",
                "genderfluid",
                "genderqueer",
                "intersex",
                "lesbian",
                "nonbinary",
                "pansexual",
                "pride",
                "trans",
                "redstone",
                "lamp",
                "sculk",
                "colored_sculk",
                "hunger",
                "health",
                "day_time",
                "colored_day_time",
                "glowstone",
                "rainbow",
                "biome",
                "ender",
                "speed",
                "height_bedrock",
                "height_sea",
                "invisible",
                "static",
                "crystal",
                "negative",
                "true_negative",
                "nebula",
                "polychromatic"
        );

        List<ItemStack> dyeCores = new ArrayList<>();
        List<ItemStack> bottledDyes = new ArrayList<>();

        ItemStack baseDyeCore = new ItemStack(WeaversParadiseItems.DYE_CORE.get());
        ItemStack baseBottledDye = new ItemStack(WeaversParadiseItems.BOTTLED_DYE.get());

        for (String entry : dyeTypes) {
            ItemStack dyeCore = baseDyeCore.copy();
            CustomData.update(DataComponents.CUSTOM_DATA, dyeCore, (tag) -> {
                tag.putString("dyeType", entry);
            });
            dyeCores.add(dyeCore);

            ItemStack bottledDye = baseBottledDye.copy();
            CustomData.update(DataComponents.CUSTOM_DATA, bottledDye, (tag) -> {
                tag.putString("dyeType", entry);
            });
            bottledDyes.add(bottledDye);
        }

        for (int i = 0; i < dyeCores.size(); i++) {
            final int j = i;
            registry.addDeferredRecipes((deferred) -> {
                deferred.accept(new DyemakingEMIRecipe(
                        ResourceLocation.fromNamespaceAndPath("weaversparadise", "/dyemaking_" + dyeTypes.get(j)),
                        List.of(
                                EmiIngredient.of(Ingredient.of(dyeCores.get(j))),
                                EmiIngredient.of(Ingredient.of(Items.POTION)),
                                EmiIngredient.of(Ingredient.of(Items.GLASS_BOTTLE)),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))),
                                EmiIngredient.of(Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))))

                        ),
                        List.of(
                                EmiIngredient.of(Ingredient.of(bottledDyes.get(j)))
                        )
                ));
            });
        }
    }
}
