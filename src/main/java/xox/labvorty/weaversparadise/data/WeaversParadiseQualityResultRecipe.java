package xox.labvorty.weaversparadise.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import xox.labvorty.weaversparadise.WeaversParadise;

public class WeaversParadiseQualityResultRecipe extends ShapedRecipe {
     public WeaversParadiseQualityResultRecipe(
             String group,
             CraftingBookCategory category,
             ShapedRecipePattern pattern,
             ItemStack result,
             boolean showNotification
     ) {
         super(group, category, pattern, result, showNotification);
     }

     @Override
     public ItemStack assemble(CraftingInput input, HolderLookup.Provider registrie) {
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

         int finalQuality = resultQuality / qualityItems;
         CustomData.update(DataComponents.CUSTOM_DATA, stack, (tag) -> tag.putInt("quality", finalQuality));
         return stack;
     }

     public static class QualityResultRecipeSerializer implements RecipeSerializer<WeaversParadiseQualityResultRecipe> {
         public static final QualityResultRecipeSerializer INSTANCE = new QualityResultRecipeSerializer();

         private final MapCodec<WeaversParadiseQualityResultRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                 instance.group(
                         ShapedRecipe.Serializer.CODEC.forGetter(recipe -> new ShapedRecipe(
                                 recipe.getGroup(),
                                 recipe.category(),
                                 recipe.pattern,
                                 recipe.getResultItem(null),
                                 recipe.showNotification()
                         ))
                 ).apply(instance, shapedRecipe -> new WeaversParadiseQualityResultRecipe(
                         shapedRecipe.getGroup(),
                         shapedRecipe.category(),
                         shapedRecipe.pattern,
                         shapedRecipe.getResultItem(null),
                         shapedRecipe.showNotification()
                 ))
         );

         private final StreamCodec<RegistryFriendlyByteBuf, WeaversParadiseQualityResultRecipe> STREAM_CODEC = StreamCodec.of(this::toNetwork, this::fromNetwork);

         @Override
         public MapCodec<WeaversParadiseQualityResultRecipe> codec() {
             return CODEC;
         }

         @Override
         public StreamCodec<RegistryFriendlyByteBuf, WeaversParadiseQualityResultRecipe> streamCodec() {
             return STREAM_CODEC;
         }

         public WeaversParadiseQualityResultRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
             ShapedRecipe shapedRecipe = RecipeSerializer.SHAPED_RECIPE.streamCodec().decode(buffer);

             return new WeaversParadiseQualityResultRecipe(
                     shapedRecipe.getGroup(),
                     shapedRecipe.category(),
                     shapedRecipe.pattern,
                     shapedRecipe.getResultItem(null),
                     shapedRecipe.showNotification()
             );
         }

         public void toNetwork(RegistryFriendlyByteBuf buffer, WeaversParadiseQualityResultRecipe recipe) {
             RecipeSerializer.SHAPED_RECIPE.streamCodec().encode(buffer, recipe);
         }
     }
}
