package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class StonecutterRecipe extends net.minecraft.world.item.crafting.StonecutterRecipe {
    private final CompoundTag tag;

    public StonecutterRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, CompoundTag tag) {
        super(id, group, ingredient, result);
        this.tag = tag;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull Container container, @NotNull RegistryAccess registryAccess) {
        ItemStack stack = super.assemble(container, registryAccess).copy();

        if (tag != null) {
            stack.setTag(tag.copy());
        }

        return stack;
    }

    public static class Serializer implements RecipeSerializer<StonecutterRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public @NotNull StonecutterRecipe fromJson(@NotNull ResourceLocation resourceLocation, @NotNull JsonObject jsonObject) {
            String group = GsonHelper.getAsString(jsonObject, "group", "");

            Ingredient ingredient = Ingredient.fromJson(
                    GsonHelper.getAsJsonObject(jsonObject, "ingredient")
            );

            String resultId = GsonHelper.getAsString(jsonObject, "result");
            int count = GsonHelper.getAsInt(jsonObject, "count", 1);

            Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(resultId));
            if (item == null) {
                item = Items.AIR;
            }

            ItemStack result = new ItemStack(
                    item,
                    count
            );

            CompoundTag tag = null;

            if (jsonObject.has("nbt")) {
                try {
                    tag = TagParser.parseTag(GsonHelper.getAsString(jsonObject, "nbt"));
                } catch (Exception e) {
                    throw new JsonSyntaxException("Invalid NBT in stonecutting recipe", e);
                }
            }

            return new StonecutterRecipe(resourceLocation, group, ingredient, result, tag);
        }

        @Override
        public StonecutterRecipe fromNetwork(@NotNull ResourceLocation resourceLocation, FriendlyByteBuf buf) {
            String group = buf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            CompoundTag tag = buf.readNbt();

            return new StonecutterRecipe(resourceLocation, group, ingredient, result, tag);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, StonecutterRecipe recipe) {
            buf.writeUtf(recipe.getGroup());
            recipe.getIngredients().get(0).toNetwork(buf);
            buf.writeItem(recipe.getResultItem(RegistryAccess.EMPTY));
            buf.writeNbt(recipe.tag);
        }
    }
}