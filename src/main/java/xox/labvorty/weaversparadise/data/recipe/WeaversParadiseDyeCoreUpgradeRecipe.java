package xox.labvorty.weaversparadise.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.NonNullList;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class WeaversParadiseDyeCoreUpgradeRecipe extends ShapelessRecipe {
    public WeaversParadiseDyeCoreUpgradeRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(id, group, CraftingBookCategory.MISC, result, ingredients);
    }

    @Override
    public boolean matches(CraftingContainer pInv, Level pLevel) {
        return super.matches(pInv, pLevel);
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess) {

        ItemStack stack = this.getResultItem(registryAccess).copy();

        int redstones = 0;
        boolean glow = false;
        boolean red = false;
        boolean canModify = false;

        for (int i = 0; i < inv.getContainerSize(); i++) {

            ItemStack input = inv.getItem(i);

            if (input.is(Items.REDSTONE)) {
                redstones++;
            }

            if (input.is(WeaversParadiseItems.DYE_CORE.get())) {

                CompoundTag tag = input.getTag() != null ? input.getTag().copy() : new CompoundTag();

                String dyeType = tag.getString("dyeType");

                if (dyeType.equals("lamp") || dyeType.equals("redstone")) {
                    canModify = true;
                }

                if (dyeType.equals("redstone")) {
                    red = true;
                }

                stack.getOrCreateTag().putString("dyeType", dyeType);
                stack.getOrCreateTag().putInt("lightValue", tag.getInt("lightValue"));
            }

            if (input.is(Items.GLOWSTONE_DUST)) {
                glow = true;
            }
        }

        if (canModify) {
            if ((glow && red) || redstones > 0) {

                CompoundTag tag = stack.getOrCreateTag();

                String dyeType = (glow && red) ? "lamp" : tag.getString("dyeType");

                int light = Mth.clamp(tag.getInt("lightValue") + redstones, 0, 15);

                tag.putString("dyeType", dyeType);
                tag.putInt("lightValue", light);
            }
        }

        return stack;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    public static class Serializer implements RecipeSerializer<WeaversParadiseDyeCoreUpgradeRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public WeaversParadiseDyeCoreUpgradeRecipe fromJson(ResourceLocation id, JsonObject json) {

            String group = GsonHelper.getAsString(json, "group", "");

            JsonArray ingredientsJson = GsonHelper.getAsJsonArray(json, "ingredients");

            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < ingredientsJson.size(); i++) {
                ingredients.add(Ingredient.fromJson(ingredientsJson.get(i)));
            }

            JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");

            ItemStack result = ShapedRecipe.itemStackFromJson(resultJson);

            return new WeaversParadiseDyeCoreUpgradeRecipe(id, group, result, ingredients);
        }

        @Override
        public WeaversParadiseDyeCoreUpgradeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {

            String group = buf.readUtf();

            int size = buf.readVarInt();

            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < size; i++) {
                ingredients.add(Ingredient.fromNetwork(buf));
            }

            ItemStack result = buf.readItem();

            return new WeaversParadiseDyeCoreUpgradeRecipe(id, group, result, ingredients);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, WeaversParadiseDyeCoreUpgradeRecipe recipe) {
            buf.writeUtf(recipe.getGroup());

            NonNullList<Ingredient> ingredients = recipe.getIngredients();

            buf.writeVarInt(ingredients.size());

            for (Ingredient ingredient : ingredients) {
                ingredient.toNetwork(buf);
            }

            buf.writeItem(recipe.getResultItem(null));
        }
    }
}