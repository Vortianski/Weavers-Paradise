package xox.labvorty.weaversparadise.data.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class QualityResultShapelessRecipe extends ShapelessRecipe {
    final ItemStack result;

    public QualityResultShapelessRecipe(
            String group,
            CraftingBookCategory category,
            ItemStack result,
            NonNullList<Ingredient> ingredients
    ) {
        super(group, category, result, ingredients);
        this.result = result;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput input, HolderLookup.@NotNull Provider registrie) {
        ItemStack stack = super.assemble(input, registrie);
        int qualityItems = 0;
        int resultQuality = 0;
        for (int i = 0; i < input.size(); i++) {
            ItemStack inputStack = input.getItem(i);
            CompoundTag compound = inputStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
            if (compound.contains("quality")) {
                qualityItems += 1;
                resultQuality += compound.getInt("quality");
            }
        }

        int finalQuality = qualityItems > 0 ? resultQuality / qualityItems : 0;
        CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", finalQuality));
        return stack;
    }

    public static class Serializer implements RecipeSerializer<QualityResultShapelessRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        private final MapCodec<QualityResultShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(QualityResultShapelessRecipe::getGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(QualityResultShapelessRecipe::category),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter((recipe) -> recipe.result),
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((list) -> {
                            Ingredient[] ingredientArray = list.toArray(new Ingredient[0]);
                            if (ingredientArray.length == 0) {
                                return DataResult.error(() -> "No ingredients for shapeless recipe");
                            } else if (ingredientArray.length > 3 * 3) {
                                return DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(3 * 3));
                            } else {
                                return DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredientArray));
                            }
                        }, DataResult::success).forGetter(QualityResultShapelessRecipe::getIngredients)
                ).apply(instance, QualityResultShapelessRecipe::new)
        );

        private final StreamCodec<RegistryFriendlyByteBuf, QualityResultShapelessRecipe> STREAM_CODEC = StreamCodec.of(this::toNetwork, this::fromNetwork);

        @Override
        public @NotNull MapCodec<QualityResultShapelessRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, QualityResultShapelessRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public QualityResultShapelessRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CraftingBookCategory category = buffer.readEnum(CraftingBookCategory.class);
            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            ingredients.replaceAll((ignored) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            return new QualityResultShapelessRecipe(group, category, result, ingredients);
        }

        public void toNetwork(RegistryFriendlyByteBuf buffer, QualityResultShapelessRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.category());
            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }
    }
}