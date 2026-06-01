package xox.labvorty.weaversparadise.compat.jei;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.ArrayList;
import java.util.List;

public class DyemakingJEIRecipe {
    private final ResourceLocation id;
    private final List<Ingredient> inputs;
    private final ItemStack output;

    public DyemakingJEIRecipe(
            ResourceLocation id,
            List<Ingredient> inputs,
            ItemStack output
    ) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    public ResourceLocation getId() {
        return id;
    }

    public List<Ingredient> getInputs() {
        return inputs;
    }

    public ItemStack getOutput() {
        return output;
    }

    public static List<DyemakingJEIRecipe> makeDyeRecipes() {
        List<DyemakingJEIRecipe> recipes = new ArrayList<>();

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
            dyeCore.getOrCreateTag().putString("dyeType", entry);
            dyeCores.add(dyeCore);

            ItemStack bottledDye = baseBottledDye.copy();
            bottledDye.getOrCreateTag().putString("dyeType", entry);
            bottledDyes.add(bottledDye);
        }

        for (int i = 0; i < dyeCores.size(); i++) {
            recipes.add(
                    new DyemakingJEIRecipe(
                            ResourceLocation.fromNamespaceAndPath(
                                    "weaversparadise",
                                    "dyemaking_recipe_" + dyeTypes.get(i)
                            ),
                            List.of(
                                    Ingredient.of(dyeCores.get(i)),
                                    Ingredient.of(Items.POTION),
                                    Ingredient.of(Items.GLASS_BOTTLE),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes"))),
                                    Ingredient.of(ItemTags.create(ResourceLocation.parse("weaversparadise:vanilla_dyes")))
                            ),
                            bottledDyes.get(i)
                    )
            );
        }

        return recipes;
    }
}