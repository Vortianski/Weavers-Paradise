package xox.labvorty.weaversparadise.data.loot_modifiers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;
import xox.labvorty.weaversparadise.items.misc.PlushieItem;
import xox.labvorty.weaversparadise.utilities.WeaversUtilities;

import java.util.List;
import java.util.Optional;

/** Порт логики PlushieLootModifier.doApply как LootItemFunction. */
public class WPPlushieFunction implements LootItemFunction {
    public static final WPPlushieFunction INSTANCE = new WPPlushieFunction();

    public static final MapCodec<WPPlushieFunction> CODEC = MapCodec.unit(INSTANCE);

    private static LootItemFunctionType TYPE;

    private final List<String> applicableNames = List.of(
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

    private WPPlushieFunction() {}

    /** Вызвать один раз из WPLoot.register() до первого использования. */
    public static void registerCodec() {
        TYPE = Registry.register(
                BuiltInRegistries.LOOT_FUNCTION_TYPE,
                ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "random_plushie"),
                new LootItemFunctionType(CODEC)
        );
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext context) {
        RandomSource randomSource = context.getRandom();

        return randomSource.nextBoolean()
                ? WeaversUtilities.createRandomDoubleSidedPlushie(WeaversParadiseItems.BLAHAJ)
                : PlushieItem.createPlushie(
                        Optional.of(applicableNames.get(randomSource.nextIntBetweenInclusive(0, applicableNames.size() - 1))),
                        Optional.empty());
    }

    @Override
    public LootItemFunctionType getType() {
        return TYPE;
    }
}
