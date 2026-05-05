package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import xox.labvorty.weaversparadise.items.misc.PlayerPlushieRenameTokenItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

public class WeaversParadisePlushieRenameRecipe extends ShapelessRecipe {
    public WeaversParadisePlushieRenameRecipe(
            ResourceLocation id,
            String group,
            CraftingBookCategory category,
            ItemStack result,
            NonNullList<Ingredient> ingredients
    ) {
        super(id, group, category, result, ingredients);
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        ItemStack result = super.assemble(container, access);

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack input = container.getItem(i);

            if (input.getItem() instanceof PlayerPlushieRenameTokenItem) {

                String newName = input.hasCustomHoverName()
                        ? input.getHoverName().getString()
                        : "Steve";

                return PlushieItem.createPreMadePlushieAsync(newName, null);
            }
        }

        return result;
    }

    public static class Serializer implements RecipeSerializer<WeaversParadisePlushieRenameRecipe> {
        public static final WeaversParadisePlushieRenameRecipe.Serializer INSTANCE = new WeaversParadisePlushieRenameRecipe.Serializer();

        @Override
        public WeaversParadisePlushieRenameRecipe fromJson(ResourceLocation id, JsonObject json) {
            ShapelessRecipe vanilla = RecipeSerializer.SHAPELESS_RECIPE.fromJson(id, json);

            return new WeaversParadisePlushieRenameRecipe(
                    id,
                    vanilla.getGroup(),
                    vanilla.category(),
                    vanilla.getResultItem(null),
                    vanilla.getIngredients()
            );
        }

        @Override
        public WeaversParadisePlushieRenameRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            ShapelessRecipe vanilla = RecipeSerializer.SHAPELESS_RECIPE.fromNetwork(id, buffer);

            return new WeaversParadisePlushieRenameRecipe(
                    id,
                    vanilla.getGroup(),
                    vanilla.category(),
                    vanilla.getResultItem(null),
                    vanilla.getIngredients()
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, WeaversParadisePlushieRenameRecipe recipe) {
            RecipeSerializer.SHAPELESS_RECIPE.toNetwork(buffer, recipe);
        }
    }
}