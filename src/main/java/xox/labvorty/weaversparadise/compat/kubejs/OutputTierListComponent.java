package xox.labvorty.weaversparadise.compat.kubejs;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.kubejs.util.JsonUtils;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.data.recipes.ClothcraftingRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record OutputTierListComponent(RecipeComponentType<?> type) implements RecipeComponent<List<ClothcraftingRecipe.OutputTier>> {
    public static final RecipeComponentType<List<ClothcraftingRecipe.OutputTier>> OUTPUT_TIER_LIST =
        RecipeComponentType.unit(
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "output_tier_list"),
            OutputTierListComponent::new
        );

    @Override
    public List<ClothcraftingRecipe.OutputTier> wrap(RecipeScriptContext cx, Object from) {
        List<ClothcraftingRecipe.OutputTier> out = new ArrayList<>();

        for (Object o : (Iterable<?>) from) {
            Map<?, ?> map = (Map<?, ?>) o;

            out.add(new ClothcraftingRecipe.OutputTier(
                    ((Number) map.get("min_score")).intValue(),
                    ((Number) map.get("max_score")).intValue(),
                    ItemStackComponent.ITEM_STACK.instance().wrap(cx, map.get("result")),
                    map.containsKey("count")
                            ? ((Number) map.get("count")).intValue()
                            : 1
            ));
        }

        return out;
    }

    @Override
    public Codec<List<ClothcraftingRecipe.OutputTier>> codec() {
        return ClothcraftingRecipe.OutputTier.CODEC.listOf();
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(List.class);
    }
}