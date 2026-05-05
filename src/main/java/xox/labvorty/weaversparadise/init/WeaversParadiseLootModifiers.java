package xox.labvorty.weaversparadise.init;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.data.loot_modifiers.ArmorLootboxModifier;
import xox.labvorty.weaversparadise.data.loot_modifiers.PlayerPlushieModifier;

public class WeaversParadiseLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS , WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ARMOR_LOOTBOX_MODIFIER = LOOT_MODIFIERS.register("armor_lootbox_modifier", () -> ArmorLootboxModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> PLAYER_PLUSHIE_MODIFIER = LOOT_MODIFIERS.register("player_plushie_modifier", () -> PlayerPlushieModifier.CODEC);
}
