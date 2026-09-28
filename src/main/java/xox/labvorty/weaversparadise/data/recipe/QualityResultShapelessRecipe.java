package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;

public class QualityResultShapelessRecipe extends ShapelessRecipe {
    public QualityResultShapelessRecipe(
            ResourceLocation id,
            String group,
            CraftingBookCategory category,
            ItemStack result,
            NonNullList<Ingredient> ingredients
    ) {
        super(id, group, category, result, ingredients);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer container, @NotNull RegistryAccess registryAccess) {
        ItemStack stack = super.assemble(container, registryAccess);

        int qualityItems = 0;
        int totalQuality = 0;

        for (int i = 0; i < container.getContainerSize(); i++) {
            CompoundTag tag = container.getItem(i).getTag();
            if (tag != null && tag.contains("quality")) {
                qualityItems++;
                totalQuality += tag.getInt("quality");
            }
        }

        int finalQuality = qualityItems > 0 ? totalQuality / qualityItems : 0;
        stack.getOrCreateTag().putInt("quality", finalQuality);
        return stack;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Serializer implements RecipeSerializer<QualityResultShapelessRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull QualityResultShapelessRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            ShapelessRecipe base = RecipeSerializer.SHAPELESS_RECIPE.fromJson(id, json);
            return wrap(id, base);
        }

        @Override
        public QualityResultShapelessRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
            ShapelessRecipe base = RecipeSerializer.SHAPELESS_RECIPE.fromNetwork(id, buf);
            return base == null ? null : wrap(id, base);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull QualityResultShapelessRecipe recipe) {
            RecipeSerializer.SHAPELESS_RECIPE.toNetwork(buf, recipe);
        }

        private static QualityResultShapelessRecipe wrap(ResourceLocation id, ShapelessRecipe base) {
            return new QualityResultShapelessRecipe(
                    id,
                    base.getGroup(),
                    base.category(),
                    base.getResultItem(RegistryAccess.EMPTY),
                    base.getIngredients()
            );
        }
    }
}