package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class WeaversParadiseEnchantments {
    public static final ResourceKey<Enchantment> WATER_STRIDER = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "water_strider")
    );
    public static final ResourceKey<Enchantment> SOUND_MUFFLER = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "sound_muffler")
    );
    public static final ResourceKey<Enchantment> VAMPIRISM = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "vampirism")
    );
    public static final ResourceKey<Enchantment> GRACEFUL = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "graceful")
    );
    public static final ResourceKey<Enchantment> TOUGH_AS_NAILS = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "tough_as_nails")
    );
    public static final ResourceKey<Enchantment> SOFT_AND_COZY = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "soft_and_cozy")
    );
    public static final ResourceKey<Enchantment> BREATHING_EXERCISE = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath("weaversparadise", "breathing_exercise")
    );
}
