package xox.labvorty.weaversparadise.data.loot_modifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

public class ArmorLootboxLootModifier extends LootModifier {
    public static final MapCodec<ArmorLootboxLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            LootModifier.codecStart(instance).apply(instance, ArmorLootboxLootModifier::new)
    );

    public ArmorLootboxLootModifier(LootItemCondition[] conditions) {
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
            if (context.getRandom().nextDouble() > CommonConfig.ARMOR_LOOTBOX_CHANCE.get()) return generatedLoot;

            generatedLoot.add(new ItemStack(WeaversParadiseItems.ARMOR_LOOTBOX.get()));
        }

        return generatedLoot;
    }
}
