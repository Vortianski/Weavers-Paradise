package xox.labvorty.weaversparadise.data;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class WeaversParadiseDyeCoreUpgradeRecipe extends ShapelessRecipe {
    public WeaversParadiseDyeCoreUpgradeRecipe(
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

        int redstones = 0;
        boolean isGlowstone = false;
        boolean isRedstone = false;
        boolean isAbleToModify = false;
        for (int i = 0; i < input.size(); i++) {
            if (input.getItem(i).is(Items.REDSTONE)) {
                redstones += 1;
            }

            if (input.getItem(i).is(WeaversParadiseItems.DYE_CORE) ) {
                CompoundTag compoundTag = input.getItem(i).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

                if (compoundTag.getString("dyeType").equals("lamp") || compoundTag.getString("dyeType").equals("redstone")) {
                    isAbleToModify = true;
                }

                if (compoundTag.getString("dyeType").equals("redstone")) {
                    isRedstone = true;
                }

                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                    tag.putString("dyeType", compoundTag.getString("dyeType"));
                    tag.putInt("lightValue", compoundTag.getInt("lightValue"));
                });
            }

            if (input.getItem(i).is(Items.GLOWSTONE_DUST)) {
                isGlowstone = true;
            }
        }

        if (isAbleToModify) {
            if ((isGlowstone && isRedstone) || redstones > 0) {
                CompoundTag compoundTag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                final String dyeType = (isGlowstone && isRedstone) ? "lamp" : compoundTag.getString("dyeType");
                final int modifiedLightValue = Mth.clamp(compoundTag.getInt("lightValue") + redstones, 0, 15);
                CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                    tag.putString("dyeType", dyeType);
                    tag.putInt("lightValue", modifiedLightValue);
                });
            }
        }

        return stack;
    }

    public static class DyeCoreUpgadeRecipeSerializer implements RecipeSerializer<WeaversParadiseDyeCoreUpgradeRecipe> {
        public static WeaversParadiseDyeCoreUpgradeRecipe.DyeCoreUpgadeRecipeSerializer INSTANCE = new WeaversParadiseDyeCoreUpgradeRecipe.DyeCoreUpgadeRecipeSerializer();

        private final StreamCodec<RegistryFriendlyByteBuf, WeaversParadiseDyeCoreUpgradeRecipe> STREAM_CODEC = StreamCodec.of(this::toNetwork, this::fromNetwork);

        @Override
        public MapCodec<WeaversParadiseDyeCoreUpgradeRecipe> codec() {
            return RecipeSerializer.SHAPELESS_RECIPE.codec().xmap(
                    recipe -> {
                        return new WeaversParadiseDyeCoreUpgradeRecipe(
                                recipe.getGroup(),
                                recipe.category(),
                                recipe.getResultItem(null),
                                recipe.getIngredients()
                        );
                    }, recipe -> recipe
            );
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WeaversParadiseDyeCoreUpgradeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private WeaversParadiseDyeCoreUpgradeRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            ShapelessRecipe vanilla = RecipeSerializer.SHAPELESS_RECIPE.streamCodec().decode(buffer);
            return new WeaversParadiseDyeCoreUpgradeRecipe(
                    vanilla.getGroup(),
                    vanilla.category(),
                    vanilla.getResultItem(null),
                    vanilla.getIngredients()
            );
        }

        public void toNetwork(RegistryFriendlyByteBuf buffer, WeaversParadiseDyeCoreUpgradeRecipe recipe) {
            RecipeSerializer.SHAPELESS_RECIPE.streamCodec().encode(buffer, recipe);
        }
    }
}
