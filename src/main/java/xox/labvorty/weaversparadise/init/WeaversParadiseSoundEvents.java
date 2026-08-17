package xox.labvorty.weaversparadise.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.WeaversParadise;

public class WeaversParadiseSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, WeaversParadise.MODID);
    public static final DeferredHolder<SoundEvent, SoundEvent> HURT_OLD = SOUND_EVENTS.register("weaversparadise.hurt_old", () -> SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(
                    WeaversParadise.MODID,
                    "weaversparadise.hurt_old"
            )
    ));
    public static final DeferredHolder<SoundEvent, SoundEvent> FALL_BIG_OLD = SOUND_EVENTS.register("weaversparadise.fall_big_old", () -> SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(
                    WeaversParadise.MODID,
                    "weaversparadise.fall_big_old"
            )
    ));
    public static final DeferredHolder<SoundEvent, SoundEvent> FALL_SMALL_OLD = SOUND_EVENTS.register("weaversparadise.fall_small_old", () -> SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(
                    WeaversParadise.MODID,
                    "weaversparadise.fall_small_old"
            )
    ));
}
