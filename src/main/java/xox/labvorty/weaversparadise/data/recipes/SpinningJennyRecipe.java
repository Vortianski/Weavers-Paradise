package xox.labvorty.weaversparadise.data.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

public class SpinningJennyRecipe implements Recipe<SpinningJennyRecipeInput> {
    private final Ingredient input;
    private final ItemStack result;
    private final int countRequired;
    private final int craftTime;

    public SpinningJennyRecipe(Ingredient input, ItemStack result, int countRequired, int craftTime) {
        this.input = input;
        this.result = result;
        this.countRequired = countRequired;
        this.craftTime = craftTime;
    }

    @Override
    public boolean matches(SpinningJennyRecipeInput input, @NotNull Level level) {
        return this.input.test(input.mainIngredient());
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SpinningJennyRecipeInput input, HolderLookup.@NotNull Provider registries) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider registries) {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return WeaversParadiseRecipes.SPINNING_JENNY_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return WeaversParadiseRecipes.SPINNING_JENNY_TYPE.get();
    }

    public Ingredient getInput() {
        return input;
    }

    public int getCountRequired() {
        return countRequired;
    }

    public int getCraftTime() {
        return craftTime;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(input);
        return list;
    }

    public static class Serializer implements RecipeSerializer<SpinningJennyRecipe> {
        public static Serializer INSTANCE = new Serializer();

        private static final MapCodec<SpinningJennyRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(SpinningJennyRecipe::getInput),
                        ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.INT.fieldOf("count_required").forGetter(SpinningJennyRecipe::getCountRequired),
                        Codec.INT.optionalFieldOf("craft_time", 100).forGetter(SpinningJennyRecipe::getCraftTime)
                ).apply(instance, SpinningJennyRecipe::new)
        );

        private static final StreamCodec<RegistryFriendlyByteBuf, SpinningJennyRecipe> STREAM_CODEC =
                StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public @NotNull MapCodec<SpinningJennyRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, SpinningJennyRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SpinningJennyRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            int countRequired = buffer.readInt();
            int craftTime = buffer.readInt();
            return new SpinningJennyRecipe(input, result, countRequired, craftTime);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, SpinningJennyRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getInput());
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeInt(recipe.getCountRequired());
            buffer.writeInt(recipe.getCraftTime());
        }
    }
}
