package xox.labvorty.weaversparadise.data.loot_modifiers;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import xox.labvorty.weaversparadise.configs.CommonConfig;
import xox.labvorty.weaversparadise.init.WeaversParadiseItems;

/** Замена NeoForge-глобальных loot-модификаторов (fabric-loot-api-v3). */
public final class WPLoot {
    private WPLoot() {}

    public static void register() {
        WPPlushieFunction.registerCodec();

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!key.location().getPath().startsWith("chests/")) return;

            // ArmorLootboxLootModifier
            tableBuilder.pool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .when(LootItemRandomChanceCondition.randomChance(
                            CommonConfig.ARMOR_LOOTBOX_CHANCE.get().floatValue()))
                    .add(LootItem.lootTableItem(WeaversParadiseItems.ARMOR_LOOTBOX))
                    .build());

            // PlushieLootModifier (стак с NBT — кастомная LootItemFunction)
            tableBuilder.pool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .when(LootItemRandomChanceCondition.randomChance(
                            CommonConfig.PLUSHIE_CHANCE.get().floatValue()))
                    .add(LootItem.lootTableItem(WeaversParadiseItems.BLAHAJ)
                            .apply(() -> WPPlushieFunction.INSTANCE))
                    .build());
        });
    }
}
