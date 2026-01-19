package xox.labvorty.weaversparadise.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;

public class DyeCrushingRecipe extends ShapelessRecipe {
    private final int red;
    private final int green;
    private final int blue;

    public DyeCrushingRecipe(
            String group,
            CraftingBookCategory category,
            ItemStack result,
            NonNullList<Ingredient> ingredients,
            int red,
            int green,
            int blue
    ) {
        super(group, category, result, ingredients);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack stack = super.assemble(input, registries).copy();

        CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
            tag.putInt("red", this.red);
            tag.putInt("green", this.green);
            tag.putInt("blue", this.blue);
        });

        return stack;
    }



    public static class DyeCrushingRecipeSerializer implements RecipeSerializer<DyeCrushingRecipe> {
        public static final DyeCrushingRecipeSerializer INSTANCE = new DyeCrushingRecipeSerializer();
        private static final MapCodec<DyeCrushingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(ShapelessRecipe::getGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ShapelessRecipe::category),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.getResultItem(null)),
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                .flatXmap(ingredients -> {
                                    if (ingredients.isEmpty())
                                        return DataResult.error(() -> "No ingredients for shapeless recipe");
                                    if (ingredients.size() > 9)
                                        return DataResult.error(() -> "Too many ingredients for shapeless recipe. Maximum is 9");
                                    return DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients.toArray(Ingredient[]::new)));
                                }, DataResult::success).forGetter(ShapelessRecipe::getIngredients),
                        Codec.INT.fieldOf("red").forGetter(DyeCrushingRecipe::getRed),
                        Codec.INT.fieldOf("green").forGetter(DyeCrushingRecipe::getGreen),
                        Codec.INT.fieldOf("blue").forGetter(DyeCrushingRecipe::getBlue)
                ).apply(instance, DyeCrushingRecipe::new)
        );

        private final StreamCodec<RegistryFriendlyByteBuf, DyeCrushingRecipe> STREAM_CODEC = StreamCodec.of(
                this::toNetwork, this::fromNetwork
        );

        @Override
        public MapCodec<DyeCrushingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DyeCrushingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private DyeCrushingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String group = buffer.readUtf();
            CraftingBookCategory category = buffer.readEnum(CraftingBookCategory.class);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            int ingredientsSize = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientsSize, Ingredient.EMPTY);

            for (int i = 0; i < ingredientsSize; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }

            int red = buffer.readVarInt();
            int green = buffer.readVarInt();
            int blue = buffer.readVarInt();

            return new DyeCrushingRecipe(group, category, result, ingredients, red, green, blue);
        }

        private void toNetwork(RegistryFriendlyByteBuf buffer, DyeCrushingRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.category());
            ItemStack.STREAM_CODEC.encode(buffer, recipe.getResultItem(null));

            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            buffer.writeVarInt(recipe.getRed());
            buffer.writeVarInt(recipe.getGreen());
            buffer.writeVarInt(recipe.getBlue());
        }
    }
}
