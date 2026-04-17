package xox.labvorty.weaversparadise.init;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.data.loot_modifiers.ArmorLootboxLootModifier;
import xox.labvorty.weaversparadise.data.loot_modifiers.PlushieLootModifier;

import java.util.function.Supplier;

public class WeaversParadiseLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(
            NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, WeaversParadise.MODID
    );
    public static final Supplier<MapCodec<PlushieLootModifier>> PLUSHIE_LOOT_MODIFIER =
            LOOT_MODIFIERS.register("plushie_loot_modifier", () -> PlushieLootModifier.CODEC);
    public static final Supplier<MapCodec<ArmorLootboxLootModifier>> ARMOR_LOOTBOX_MODIFIER =
            LOOT_MODIFIERS.register("armor_lootbox_modifier", () -> ArmorLootboxLootModifier.CODEC);
}
