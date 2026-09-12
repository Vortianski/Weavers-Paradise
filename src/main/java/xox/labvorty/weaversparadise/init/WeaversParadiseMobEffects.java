package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.effects.ChromaticShiftEffect;

public class WeaversParadiseMobEffects {
    public static final Holder<MobEffect> CHROMATIC_SHIFT = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "chromatic_shift"),
            new ChromaticShiftEffect()
    );

    public static void register() {
    }
}
