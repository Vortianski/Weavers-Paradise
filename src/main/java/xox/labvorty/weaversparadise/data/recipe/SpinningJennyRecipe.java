package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

public class SpinningJennyRecipe implements Recipe<SpinningJennyRecipeInput> {

    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack result;
    private final int countRequired;
    private final int craftTime;

    public SpinningJennyRecipe(ResourceLocation id, Ingredient input, ItemStack result, int countRequired, int craftTime) {
        this.id = id;
        this.input = input;
        this.result = result;
        this.countRequired = countRequired;
        this.craftTime = craftTime;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
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
    public boolean matches(SpinningJennyRecipeInput input, Level level) {
        return this.input.test(input.mainIngredient());
    }

    @Override
    public ItemStack assemble(SpinningJennyRecipeInput input, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) { return true; }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) { return result; }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(input);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WeaversParadiseRecipes.SPINNING_JENNY_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return WeaversParadiseRecipes.SPINNING_JENNY_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<SpinningJennyRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public Codec<SpinningJennyRecipe> codec() {
            throw new UnsupportedOperationException("Use fromJson");
        }

        @Override
        public SpinningJennyRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.get("input"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            int countRequired = GsonHelper.getAsInt(json, "count_required");
            int craftTime = json.has("craft_time") ? GsonHelper.getAsInt(json, "craft_time") : 100;

            return new SpinningJennyRecipe(id, input, result, countRequired, craftTime);
        }

        @Override
        public SpinningJennyRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            int countRequired = buf.readInt();
            int craftTime = buf.readInt();

            return new SpinningJennyRecipe(id, input, result, countRequired, craftTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, SpinningJennyRecipe recipe) {
            recipe.input.toNetwork(buf);
            buf.writeItem(recipe.result);
            buf.writeInt(recipe.countRequired);
            buf.writeInt(recipe.craftTime);
        }
    }
}