package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClothcraftingRecipe implements Recipe<ClothcraftingRecipeInput> {
    public record OutputTier(int minScore, int maxScore, ItemStack result, int count) {
        public static final Codec<OutputTier> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                Codec.INT.fieldOf("min_score").forGetter(OutputTier::minScore),
                Codec.INT.fieldOf("max_score").forGetter(OutputTier::maxScore),
                ItemStack.CODEC.fieldOf("result").forGetter(OutputTier::result),
                Codec.INT.optionalFieldOf("count", 1).forGetter(OutputTier::count)
        ).apply(inst, OutputTier::new));

        public static void toNetwork(FriendlyByteBuf buf, OutputTier tier) {
            buf.writeInt(tier.minScore);
            buf.writeInt(tier.maxScore);
            buf.writeItem(tier.result);
            buf.writeInt(tier.count);
        }

        public static OutputTier fromNetwork(FriendlyByteBuf buf) {
            return new OutputTier(buf.readInt(), buf.readInt(), buf.readItem(), buf.readInt());
        }
    }

    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final int spoolCost;
    private final ItemStack spoolReturn;
    private final int spoolReturnCount;
    private final int gameDuration;
    private final List<OutputTier> tiers;

    public ClothcraftingRecipe(
            ResourceLocation id,
            Ingredient ingredient,
            int spoolCost,
            ItemStack spoolReturn,
            int spoolReturnCount,
            int gameDuration,
            List<OutputTier> tiers
    ) {
        this.id = id;
        this.ingredient = ingredient;
        this.spoolCost = spoolCost;
        this.spoolReturn = spoolReturn;
        this.spoolReturnCount = spoolReturnCount;
        this.gameDuration = gameDuration;
        this.tiers = tiers;
    }

    public Optional<OutputTier> resolve(int score) {
        return tiers.stream()
                .filter(t -> score >= t.minScore() && score <= t.maxScore())
                .findFirst();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getSpoolCost() {
        return spoolCost;
    }

    public ItemStack getSpoolReturn() {
        return spoolReturn;
    }

    public int getSpoolReturnCount() {
        return spoolReturnCount;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public List<OutputTier> getTiers() {
        return tiers;
    }

    @Override
    public boolean matches(ClothcraftingRecipeInput input, Level level) {
        return ingredient.test(input.spool());
    }

    @Override
    public ItemStack assemble(ClothcraftingRecipeInput input, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(ingredient);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WeaversParadiseRecipes.CLOTHCRAFTING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<ClothcraftingRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public ClothcraftingRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));

            int spoolCost = json.has("spool_cost") ? GsonHelper.getAsInt(json, "spool_cost") : 6;
            ItemStack spoolReturn = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "spool_return"));
            int spoolReturnCount = json.has("spool_return_count") ? GsonHelper.getAsInt(json, "spool_return_count") : 6;
            int gameDuration = GsonHelper.getAsInt(json, "game_duration");

            JsonArray tiersArray = GsonHelper.getAsJsonArray(json, "tiers");
            List<OutputTier> tiers = new ArrayList<>();
            for (JsonElement element : tiersArray) {
                JsonObject tierObj = element.getAsJsonObject();
                int minScore = GsonHelper.getAsInt(tierObj, "min_score");
                int maxScore = GsonHelper.getAsInt(tierObj, "max_score");
                ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(tierObj, "result"));
                int count = tierObj.has("count") ? GsonHelper.getAsInt(tierObj, "count") : 1;
                result.setCount(count);
                tiers.add(new OutputTier(minScore, maxScore, result, count));
            }

            return new ClothcraftingRecipe(id, ingredient, spoolCost, spoolReturn, spoolReturnCount, gameDuration, tiers);
        }

        @Override
        public ClothcraftingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            int spoolCost = buf.readInt();
            ItemStack spoolReturn = buf.readItem();
            int spoolReturnCount = buf.readInt();
            int gameDuration = buf.readInt();
            int tierCount = buf.readInt();
            List<OutputTier> tiers = new ArrayList<>();
            for (int i = 0; i < tierCount; i++) {
                tiers.add(OutputTier.fromNetwork(buf));
            }

            return new ClothcraftingRecipe(id, ingredient, spoolCost, spoolReturn, spoolReturnCount, gameDuration, tiers);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ClothcraftingRecipe recipe) {
            recipe.ingredient.toNetwork(buf);
            buf.writeInt(recipe.spoolCost);
            buf.writeItem(recipe.spoolReturn);
            buf.writeInt(recipe.spoolReturnCount);
            buf.writeInt(recipe.gameDuration);
            buf.writeInt(recipe.tiers.size());

            for (OutputTier tier : recipe.tiers) {
                OutputTier.toNetwork(buf, tier);
            }
        }
    }
}