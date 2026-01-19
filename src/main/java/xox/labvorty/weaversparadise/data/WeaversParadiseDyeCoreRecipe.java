package xox.labvorty.weaversparadise.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class WeaversParadiseDyeCoreRecipe extends ShapelessRecipe {
    public WeaversParadiseDyeCoreRecipe(
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

            if (inputItem.is(ItemTags.create(ResourceLocation.parse("weaversparadise:dye_core_ingredients")))) {
                if (inputItem.is(Items.REDSTONE)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "redstone");
                        tag.putInt("lightValue", 0);
                    });
                    break;
                }

                if (inputItem.is(Items.ECHO_SHARD)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "sculk");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.CHROMATIC_BLOOM_FRUIT)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "rainbow");
                    });
                    break;
                }

                if (inputItem.is(Items.ENDER_EYE)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "ender");
                    });
                    break;
                }

                if (inputItem.is(Items.MOSS_BLOCK)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "biome");
                    });
                    break;
                }

                if (inputItem.is(Items.GLOWSTONE_DUST)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "glowstone");
                        tag.putInt("lightValue", 15);
                    });
                    break;
                }

                if (inputItem.is(Items.SUGAR)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "speed");
                    });
                    break;
                }

                if (inputItem.is(Items.PHANTOM_MEMBRANE)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "height_bedrock");
                    });
                    break;
                }

                if (inputItem.is(Items.PRISMARINE_CRYSTALS)) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "height_sea");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_AGENDER.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "agender");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_AROACE.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "aroace");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_AROMANTIC.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "aromantic");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_ASEXUAL.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "asexual");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_BISEXUAL.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "bisexual");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_DEMIBOY.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "demiboy");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_DEMIGENDER.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "demigender");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_DEMIGIRL.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "demigirl");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_GAY.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "gay");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_GENDERFLUID.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "genderfluid");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_GENDERQUEER.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "genderqueer");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_INTERSEX.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "intersex");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_LESBIAN.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "lesbian");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_NONBINARY.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "nonbinary");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_PANSEXUAL.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "pansexual");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_PRIDE.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "pride");
                    });
                    break;
                }

                if (inputItem.is(WeaversParadiseItems.FLAG_TRANS.get())) {
                    CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> {
                        tag.putString("dyeType", "trans");
                    });
                    break;
                }
            }
        }

        return stack;
    }

    public static class DyeCoreRecipeSerializer implements RecipeSerializer<WeaversParadiseDyeCoreRecipe> {
        public static DyeCoreRecipeSerializer INSTANCE = new DyeCoreRecipeSerializer();

        private final StreamCodec<RegistryFriendlyByteBuf, WeaversParadiseDyeCoreRecipe> STREAM_CODEC = StreamCodec.of(this::toNetwork, this::fromNetwork);

        @Override
        public MapCodec<WeaversParadiseDyeCoreRecipe> codec() {
            return RecipeSerializer.SHAPELESS_RECIPE.codec().xmap(
              recipe -> {
                  return new WeaversParadiseDyeCoreRecipe(
                          recipe.getGroup(),
                          recipe.category(),
                          recipe.getResultItem(null),
                          recipe.getIngredients()
                  );
              }, recipe -> recipe
            );
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WeaversParadiseDyeCoreRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private WeaversParadiseDyeCoreRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            ShapelessRecipe vanilla = RecipeSerializer.SHAPELESS_RECIPE.streamCodec().decode(buffer);
            return new WeaversParadiseDyeCoreRecipe(
                    vanilla.getGroup(),
                    vanilla.category(),
                    vanilla.getResultItem(null),
                    vanilla.getIngredients()
            );
        }

        public void toNetwork(RegistryFriendlyByteBuf buffer, WeaversParadiseDyeCoreRecipe recipe) {
            RecipeSerializer.SHAPELESS_RECIPE.streamCodec().encode(buffer, recipe);
        }
    }
}
