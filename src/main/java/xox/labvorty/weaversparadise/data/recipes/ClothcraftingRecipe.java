package xox.labvorty.weaversparadise.data.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseRecipes;

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

        public static final StreamCodec<RegistryFriendlyByteBuf, OutputTier> STREAM_CODEC =
                StreamCodec.composite(
                        ByteBufCodecs.INT, OutputTier::minScore,
                        ByteBufCodecs.INT, OutputTier::maxScore,
                        ItemStack.STREAM_CODEC, OutputTier::result,
                        ByteBufCodecs.INT, OutputTier::count,
                        OutputTier::new
                );
    }

    private final Ingredient ingredient;
    private final int spoolCost;
    private final ItemStack spoolReturn;
    private final int spoolReturnCount;
    private final int gameDuration;
    private final List<OutputTier> tiers;

    public ClothcraftingRecipe(
            Ingredient ingredient,
            int spoolCost,
            ItemStack spoolReturn,
            int spoolReturnCount,
            int gameDuration,
            List<OutputTier> tiers
    ) {
        this.ingredient = ingredient;
        this.spoolCost = spoolCost;
        this.spoolReturn = spoolReturn;
        this.spoolReturnCount = spoolReturnCount;
        this.gameDuration = gameDuration;
        this.tiers = tiers;
    }

    public Optional<OutputTier> resolve(int score) {
        return tiers.stream().filter(t -> score >= t.minScore() && score <= t.maxScore()).findFirst();
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
    public boolean matches(ClothcraftingRecipeInput input, @NotNull Level level) {
        return ingredient.test(input.spool());
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull ClothcraftingRecipeInput input, HolderLookup.@NotNull Provider registries) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return WeaversParadiseRecipes.CLOTHCRAFTING_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return WeaversParadiseRecipes.CLOTHCRAFTING_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<ClothcraftingRecipe> {
        public static Serializer INSTANCE = new Serializer();
        public static final MapCodec<ClothcraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(r -> r.ingredient),
                Codec.INT.optionalFieldOf("spool_cost", 6).forGetter(r -> r.spoolCost),
                ItemStack.CODEC.fieldOf("spool_return").forGetter(r -> r.spoolReturn),
                Codec.INT.optionalFieldOf("spool_return_count", 6).forGetter(r -> r.spoolReturnCount),
                Codec.INT.fieldOf("game_duration").forGetter(r -> r.gameDuration),
                OutputTier.CODEC.listOf().fieldOf("tiers").forGetter(r -> r.tiers)
        ).apply(inst, ClothcraftingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ClothcraftingRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC,r -> r.ingredient,
                        ByteBufCodecs.INT,r -> r.spoolCost,
                        ItemStack.STREAM_CODEC,r -> r.spoolReturn,
                        ByteBufCodecs.INT,r -> r.spoolReturnCount,
                        ByteBufCodecs.INT,r -> r.gameDuration,
                        OutputTier.STREAM_CODEC.apply(ByteBufCodecs.list()),r -> r.tiers,
                        ClothcraftingRecipe::new
                );

        @Override
        public @NotNull MapCodec<ClothcraftingRecipe> codec() {
            return CODEC;
        }
        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, ClothcraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}