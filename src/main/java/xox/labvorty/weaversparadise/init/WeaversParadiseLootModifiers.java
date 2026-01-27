package xox.labvorty.weaversparadise.init;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.data.loot_modifiers.*;

import java.util.function.Supplier;

public class WeaversParadiseLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(
            NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, WeaversParadise.MODID
    );
    public static final Supplier<MapCodec<AstolfoWigLootModifier>> ASTOLFO_WIG_LOOT_MODIFIER =
            LOOT_MODIFIERS.register("astolfo_wig_loot_modifier", () -> AstolfoWigLootModifier.CODEC);
    public static final Supplier<MapCodec<AstolfoChestplateLootModifier>> ASTOLFO_CHESTPLATE_LOOT_MODIFIER =
            LOOT_MODIFIERS.register("astolfo_chestplate_loot_modifier", () -> AstolfoChestplateLootModifier.CODEC);
    public static final Supplier<MapCodec<AstolfoSkirtLootModifier>> ASTOLFO_SKIRT_LOOT_MODIFIER =
            LOOT_MODIFIERS.register("astolfo_skirt_loot_modifier", () -> AstolfoSkirtLootModifier.CODEC);
    public static final Supplier<MapCodec<AstolfoBootsLootModifier>> ASTOLFO_BOOTS_LOOT_MODIFIER =
            LOOT_MODIFIERS.register("astolfo_boots_loot_modifier", () -> AstolfoBootsLootModifier.CODEC);
    public static final Supplier<MapCodec<PlushieLootModifier>> PLUSHIE_LOOT_MODIFIER =
            LOOT_MODIFIERS.register("plushie_loot_modifier", () -> PlushieLootModifier.CODEC);
}
