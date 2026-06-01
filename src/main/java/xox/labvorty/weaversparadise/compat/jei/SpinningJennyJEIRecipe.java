package xox.labvorty.weaversparadise.compat.jei;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

import java.util.ArrayList;
import java.util.List;

public class SpinningJennyJEIRecipe {
    private final ResourceLocation id;
    private final List<Ingredient> input;
    private final ItemStack output;

    public SpinningJennyJEIRecipe(ResourceLocation id, List<Ingredient> input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    public ResourceLocation getId() {
        return id;
    }

    public List<Ingredient> getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public static List<SpinningJennyJEIRecipe> generateRecipes() {
        List<SpinningJennyJEIRecipe> recipes = new ArrayList<>();

        recipes.add(
                new SpinningJennyJEIRecipe(
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "cotton_spool"
                        ),
                        List.of(
                                Ingredient.of(WeaversParadiseItems.EMPTY_SPOOL.get()),
                                Ingredient.of(WeaversParadiseItems.RAW_COTTON.get())
                        ),
                        new ItemStack(WeaversParadiseItems.COTTON_SPOOL.get())
                )
        );

        recipes.add(
                new SpinningJennyJEIRecipe(
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "silk_spool"
                        ),
                        List.of(
                                Ingredient.of(WeaversParadiseItems.EMPTY_SPOOL.get()),
                                Ingredient.of(Items.STRING)
                        ),
                        new ItemStack(WeaversParadiseItems.SILK_SPOOL.get())
                )
        );

        recipes.add(
                new SpinningJennyJEIRecipe(
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "wool_spool"
                        ),
                        List.of(
                                Ingredient.of(WeaversParadiseItems.EMPTY_SPOOL.get()),
                                Ingredient.of(Items.WHITE_WOOL)
                        ),
                        new ItemStack(WeaversParadiseItems.WOOL_SPOOL.get())
                )
        );

        recipes.add(
                new SpinningJennyJEIRecipe(
                        ResourceLocation.fromNamespaceAndPath(
                                "weaversparadise",
                                "jeans_spool"
                        ),
                        List.of(
                                Ingredient.of(WeaversParadiseItems.EMPTY_SPOOL.get()),
                                Ingredient.of(WeaversParadiseItems.COTTON_SPOOL.get())
                        ),
                        new ItemStack(WeaversParadiseItems.JEANS_SPOOL.get())
                )
        );

        return recipes;
    }
}
