package xox.labvorty.weaversparadise.compat.jei;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.ArrayList;
import java.util.List;

public class ClothcraftingJEIRecipe {
    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;

    public ClothcraftingJEIRecipe(
            ResourceLocation id,
            Ingredient input,
            ItemStack output
    ) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public static List<ClothcraftingJEIRecipe> makeCottonRecipes() {
        List<ClothcraftingJEIRecipe> recipes = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            final int j = i;
            ItemStack stack = new ItemStack(WeaversParadiseItems.COTTON_CLOTH.get());

            stack.getOrCreateTag().putInt("quality", j);

            recipes.add(
                    new ClothcraftingJEIRecipe(
                            ResourceLocation.fromNamespaceAndPath(
                                    "weaversparadise",
                                    "clothcrafting_cotton_" + i
                            ),
                            Ingredient.of(
                                    WeaversParadiseItems.COTTON_SPOOL.get()
                            ),
                            stack
                    )
            );
        }

        return recipes;
    }

    public static List<ClothcraftingJEIRecipe> makeSilkRecipes() {
        List<ClothcraftingJEIRecipe> recipes = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            final int j = i;
            ItemStack stack = new ItemStack(WeaversParadiseItems.SILK_CLOTH.get());

            stack.getOrCreateTag().putInt("quality", j);

            recipes.add(
                    new ClothcraftingJEIRecipe(
                            ResourceLocation.fromNamespaceAndPath(
                                    "weaversparadise",
                                    "clothcrafting_silk_" + i
                            ),
                            Ingredient.of(
                                    WeaversParadiseItems.SILK_SPOOL.get()
                            ),
                            stack
                    )
            );
        }

        return recipes;
    }

    public static List<ClothcraftingJEIRecipe> makeWoolRecipes() {
        List<ClothcraftingJEIRecipe> recipes = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            final int j = i;
            ItemStack stack = new ItemStack(WeaversParadiseItems.WOOL_CLOTH.get());

            stack.getOrCreateTag().putInt("quality", j);

            recipes.add(
                    new ClothcraftingJEIRecipe(
                            ResourceLocation.fromNamespaceAndPath(
                                    "weaversparadise",
                                    "clothcrafting_wool_" + i
                            ),
                            Ingredient.of(
                                    WeaversParadiseItems.WOOL_SPOOL.get()
                            ),
                            stack
                    )
            );
        }

        return recipes;
    }

    public static List<ClothcraftingJEIRecipe> makeJeansRecipes() {
        List<ClothcraftingJEIRecipe> recipes = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            final int j = i;
            ItemStack stack = new ItemStack(WeaversParadiseItems.JEANS_CLOTH.get());

            stack.getOrCreateTag().putInt("quality", j);

            recipes.add(
                    new ClothcraftingJEIRecipe(
                            ResourceLocation.fromNamespaceAndPath(
                                    "weaversparadise",
                                    "clothcrafting_jeans_" + i
                            ),
                            Ingredient.of(
                                    WeaversParadiseItems.JEANS_SPOOL.get()
                            ),
                            stack
                    )
            );
        }

        return recipes;
    }
}
