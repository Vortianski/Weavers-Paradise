package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.effects.ChromaticShiftEffect;

public class WeaversParadiseMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, "weaversparadise");
    public static final DeferredHolder<MobEffect, MobEffect> CHROMATIC_SHIFT = MOB_EFFECTS.register("chromatic_shift", ChromaticShiftEffect::new);
}
