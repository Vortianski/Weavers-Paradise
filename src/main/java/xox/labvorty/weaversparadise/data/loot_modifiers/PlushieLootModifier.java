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
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

import java.util.List;
import java.util.Optional;

public class PlushieLootModifier extends LootModifier {
    List<String> applicableNames = List.of(
            "Vortianski",
            "Pelemeshek",
            "Steve",
            "Notch",
            "Herobrine",
            "Elifian",
            "_Alazi_",
            "IRON_carat",
            "FIT_FOX"
    );

    public static final MapCodec<PlushieLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            LootModifier.codecStart(instance).apply(instance, PlushieLootModifier::new)
    );

    public PlushieLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation location = context.getQueriedLootTableId();
        if (location.getPath().startsWith("chests/")) {
            if (context.getRandom().nextDouble() > CommonConfig.PLUSHIE_CHANCE.get()) return generatedLoot;
            RandomSource randomSource = context.getRandom();

            ItemStack stack = randomSource.nextBoolean() ? WeaversUtilities.createRandomDoubleSidedPlushie(WeaversParadiseItems.BLAHAJ.get()) : PlushieItem.createPlushie(Optional.of(applicableNames.get(randomSource.nextIntBetweenInclusive(0, applicableNames.size() - 1))), Optional.empty());

            generatedLoot.add(stack);
        }

        return generatedLoot;
    }
}
