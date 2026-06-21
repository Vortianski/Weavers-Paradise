package xox.labvorty.weaversparadise.data.recipes;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.items.misc.PlayerPlushieRenameTokenItem;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;

public class PlushieRenameRecipe extends ShapelessRecipe {
    public PlushieRenameRecipe(
            String group,
            CraftingBookCategory category,
            ItemStack result,
            NonNullList<Ingredient> ingredients
    ) {
        super(group, category, result, ingredients);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput input, HolderLookup.@NotNull Provider registries) {
        ItemStack stack = super.assemble(input, registries);

        for (int i = 0; i < input.size(); i++) {
            ItemStack inputItem = input.getItem(i);

            if (inputItem.getItem() instanceof PlayerPlushieRenameTokenItem playerPlushieRenameTokenItem) {
                String newName = inputItem.getOrDefault(DataComponents.CUSTOM_NAME, Component.literal("Steve")).getString();

                stack = PlushieItem.createPreMadePlushieAsync(newName, null);
            }
        }

        return stack;
    }

    public static class Serializer implements RecipeSerializer<PlushieRenameRecipe> {
        public static Serializer INSTANCE = new Serializer();

        private final StreamCodec<RegistryFriendlyByteBuf, PlushieRenameRecipe> STREAM_CODEC = StreamCodec.of(this::toNetwork, this::fromNetwork);

        @Override
        public @NotNull MapCodec<PlushieRenameRecipe> codec() {
            return RecipeSerializer.SHAPELESS_RECIPE.codec().xmap(
                    recipe -> {
                        return new PlushieRenameRecipe(
                                recipe.getGroup(),
                                recipe.category(),
                                recipe.getResultItem(null),
                                recipe.getIngredients()
                        );
                    }, recipe -> recipe
            );
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, PlushieRenameRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private PlushieRenameRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            ShapelessRecipe vanilla = RecipeSerializer.SHAPELESS_RECIPE.streamCodec().decode(buffer);
            return new PlushieRenameRecipe(
                    vanilla.getGroup(),
                    vanilla.category(),
                    vanilla.getResultItem(null),
                    vanilla.getIngredients()
            );
        }

        public void toNetwork(RegistryFriendlyByteBuf buffer, PlushieRenameRecipe recipe) {
            RecipeSerializer.SHAPELESS_RECIPE.streamCodec().encode(buffer, recipe);
        }
    }
}
