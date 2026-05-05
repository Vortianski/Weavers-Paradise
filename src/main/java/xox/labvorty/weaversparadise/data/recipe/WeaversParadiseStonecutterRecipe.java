package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.StonecutterRecipe;

public class WeaversParadiseStonecutterRecipe extends StonecutterRecipe {
    private final CompoundTag tag;

    public WeaversParadiseStonecutterRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, CompoundTag tag) {
        super(id, group, ingredient, result);
        this.tag = tag;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access) {
        ItemStack stack = super.assemble(container, access).copy();

        if (tag != null) {
            stack.setTag(tag.copy());
        }

        return stack;
    }

    public static class Serializer implements RecipeSerializer<WeaversParadiseStonecutterRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public WeaversParadiseStonecutterRecipe fromJson(ResourceLocation id, JsonObject json) {
            String group = GsonHelper.getAsString(json, "group", "");

            Ingredient ingredient = Ingredient.fromJson(
                    GsonHelper.getAsJsonObject(json, "ingredient")
            );

            String resultId = GsonHelper.getAsString(json, "result");
            int count = GsonHelper.getAsInt(json, "count", 1);

            ItemStack result = new ItemStack(
                    BuiltInRegistries.ITEM.get(new ResourceLocation(resultId)),
                    count
            );

            CompoundTag tag = null;

            if (json.has("nbt")) {
                try {
                    tag = TagParser.parseTag(GsonHelper.getAsString(json, "nbt"));
                } catch (Exception e) {
                    throw new JsonSyntaxException("Invalid NBT in stonecutting recipe", e);
                }
            }

            return new WeaversParadiseStonecutterRecipe(id, group, ingredient, result, tag);
        }

        @Override
        public WeaversParadiseStonecutterRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            String group = buf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            CompoundTag tag = buf.readNbt();

            return new WeaversParadiseStonecutterRecipe(id, group, ingredient, result, tag);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, WeaversParadiseStonecutterRecipe recipe) {
            buf.writeUtf(recipe.getGroup());
            recipe.getIngredients().get(0).toNetwork(buf);
            buf.writeItem(recipe.getResultItem(RegistryAccess.EMPTY));
            buf.writeNbt(recipe.tag);
        }
    }
}