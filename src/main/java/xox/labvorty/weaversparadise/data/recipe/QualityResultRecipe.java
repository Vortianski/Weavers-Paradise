package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class QualityResultRecipe extends ShapedRecipe {
    public QualityResultRecipe(
            ResourceLocation id,
            String group,
            CraftingBookCategory category,
            int width,
            int height,
            net.minecraft.core.NonNullList<net.minecraft.world.item.crafting.Ingredient> items,
            ItemStack result,
            boolean showNotification
    ) {
        super(id, group, category, width, height, items, result, showNotification);
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack result = super.assemble(container, registryAccess).getItem().getDefaultInstance();

        int total = 0;
        int count = 0;

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);

            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("quality")) {
                total += tag.getInt("quality");
                count++;
            }
        }

        if (count > 0) {
            CompoundTag resultTag = result.getOrCreateTag();
            resultTag.putInt("quality", total / count);
        }

        return result;
    }

    public static class WeaversParadiseQualityResultRecipeSerializer implements RecipeSerializer<QualityResultRecipe> {
        public static final WeaversParadiseQualityResultRecipeSerializer INSTANCE = new WeaversParadiseQualityResultRecipeSerializer();

        @Override
        public QualityResultRecipe fromJson(ResourceLocation id, JsonObject json) {
            ShapedRecipe base = RecipeSerializer.SHAPED_RECIPE.fromJson(id, json);

            return new QualityResultRecipe(
                    id,
                    base.getGroup(),
                    base.category(),
                    base.getWidth(),
                    base.getHeight(),
                    base.getIngredients(),
                    base.getResultItem(null),
                    base.showNotification()
            );
        }

        @Override
        public QualityResultRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ShapedRecipe base = RecipeSerializer.SHAPED_RECIPE.fromNetwork(id, buf);

            return new QualityResultRecipe(
                    id,
                    base.getGroup(),
                    base.category(),
                    base.getWidth(),
                    base.getHeight(),
                    base.getIngredients(),
                    base.getResultItem(null),
                    base.showNotification()
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, QualityResultRecipe recipe) {
            RecipeSerializer.SHAPED_RECIPE.toNetwork(buf, recipe);
        }
    }
}