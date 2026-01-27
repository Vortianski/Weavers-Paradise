package xox.labvorty.weaversparadise.data.loot_modifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.PlushieItem;

import java.util.List;

public class PlushieLootModifier extends LootModifier {
    List<String> applicableNames = List.of(
            "Vortianski",
            "Pelemeshek",
            "Steve",
            "Notch",
            "Herobrine"
    );

    public static final MapCodec<PlushieLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            LootModifier.codecStart(instance).apply(instance, PlushieLootModifier::new)
    );

    public PlushieLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation location = context.getQueriedLootTableId();
        if (location.getPath().startsWith("chests/")) {
            RandomSource randomSource = RandomSource.create();

            ItemStack stack = PlushieItem.createPreMadePlushieAsync(applicableNames.get(randomSource.nextIntBetweenInclusive(0, applicableNames.size() - 1)), null);

            generatedLoot.add(stack);
        }

        return generatedLoot;
    }
}
