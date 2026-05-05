package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.effects.ChromaticShiftEffect;

public class WeaversParadiseMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, "weaversparadise");
    public static final RegistryObject<MobEffect> CHROMATIC_SHIFT = MOB_EFFECTS.register("chromatic_shift", ChromaticShiftEffect::new);
}
