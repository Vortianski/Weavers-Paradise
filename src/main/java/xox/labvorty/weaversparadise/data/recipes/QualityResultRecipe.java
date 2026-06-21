package xox.labvorty.weaversparadise.data.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

public class QualityResultRecipe extends ShapedRecipe {
    final ItemStack result;

    public QualityResultRecipe(
             String group,
             CraftingBookCategory category,
             ShapedRecipePattern pattern,
             ItemStack result,
             boolean showNotification
     ) {
         super(group, category, pattern, result, showNotification);
         this.result = result;
     }

     @Override
     public @NotNull ItemStack assemble(@NotNull CraftingInput input, HolderLookup.@NotNull Provider registrie) {
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

     public static class Serializer implements RecipeSerializer<QualityResultRecipe> {
         public static final Serializer INSTANCE = new Serializer();

         private final MapCodec<QualityResultRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) ->
                 instance.group(
                         ShapedRecipe.Serializer.CODEC.forGetter(recipe -> new ShapedRecipe(
                                 recipe.getGroup(),
                                 recipe.category(),
                                 recipe.pattern,
                                 recipe.result,
                                 recipe.showNotification()
                         ))
                 ).apply(instance, shapedRecipe -> new QualityResultRecipe(
                         shapedRecipe.getGroup(),
                         shapedRecipe.category(),
                         shapedRecipe.pattern,
                         shapedRecipe.getResultItem(null),
                         shapedRecipe.showNotification()
                 ))
         );

         private final StreamCodec<RegistryFriendlyByteBuf, QualityResultRecipe> STREAM_CODEC = StreamCodec.of(this::toNetwork, this::fromNetwork);

         @Override
         public @NotNull MapCodec<QualityResultRecipe> codec() {
             return CODEC;
         }

         @Override
         public @NotNull StreamCodec<RegistryFriendlyByteBuf, QualityResultRecipe> streamCodec() {
             return STREAM_CODEC;
         }

         public QualityResultRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
             ShapedRecipe shapedRecipe = RecipeSerializer.SHAPED_RECIPE.streamCodec().decode(buffer);

             return new QualityResultRecipe(
                     shapedRecipe.getGroup(),
                     shapedRecipe.category(),
                     shapedRecipe.pattern,
                     shapedRecipe.getResultItem(null),
                     shapedRecipe.showNotification()
             );
         }

         public void toNetwork(RegistryFriendlyByteBuf buffer, QualityResultRecipe recipe) {
             RecipeSerializer.SHAPED_RECIPE.streamCodec().encode(buffer, recipe);
         }
     }
}
