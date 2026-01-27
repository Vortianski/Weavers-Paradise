package xox.labvorty.weaversparadise.data;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.PlayerPlushieRenameTokenItem;
import xox.labvorty.weaversparadise.items.PlushieItem;

public class WeaversParadisePlushieRenameRecipe extends ShapelessRecipe {
    public WeaversParadisePlushieRenameRecipe(
            String group,
            CraftingBookCategory category,
            ItemStack result,
            NonNullList<Ingredient> ingredients
    ) {
        super(group, category, result, ingredients);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack stack = super.assemble(input, registries);

        for (int i = 0; i < input.size(); i++) {
            ItemStack inputItem = input.getItem(i);

            if (inputItem.getItem() instanceof PlayerPlushieRenameTokenItem playerPlushieRenameTokenItem) {
                String newName = inputItem.getOrDefault(DataComponents.CUSTOM_NAME, Component.literal("Steve")).getString();
                ItemStack plushie = PlushieItem.createPreMadePlushieAsync(newName, null);

                stack = plushie;
            }
        }

        return stack;
    }

    public static class WeaversParadisePlushieRenameRecipeSerializer implements RecipeSerializer<WeaversParadisePlushieRenameRecipe> {
        public static WeaversParadisePlushieRenameRecipeSerializer INSTANCE = new WeaversParadisePlushieRenameRecipeSerializer();

        private final StreamCodec<RegistryFriendlyByteBuf, WeaversParadisePlushieRenameRecipe> STREAM_CODEC = StreamCodec.of(this::toNetwork, this::fromNetwork);

        @Override
        public MapCodec<WeaversParadisePlushieRenameRecipe> codec() {
            return RecipeSerializer.SHAPELESS_RECIPE.codec().xmap(
                    recipe -> {
                        return new WeaversParadisePlushieRenameRecipe(
                                recipe.getGroup(),
                                recipe.category(),
                                recipe.getResultItem(null),
                                recipe.getIngredients()
                        );
                    }, recipe -> recipe
            );
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WeaversParadisePlushieRenameRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private WeaversParadisePlushieRenameRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            ShapelessRecipe vanilla = RecipeSerializer.SHAPELESS_RECIPE.streamCodec().decode(buffer);
            return new WeaversParadisePlushieRenameRecipe(
                    vanilla.getGroup(),
                    vanilla.category(),
                    vanilla.getResultItem(null),
                    vanilla.getIngredients()
            );
        }

        public void toNetwork(RegistryFriendlyByteBuf buffer, WeaversParadisePlushieRenameRecipe recipe) {
            RecipeSerializer.SHAPELESS_RECIPE.streamCodec().encode(buffer, recipe);
        }
    }
}
