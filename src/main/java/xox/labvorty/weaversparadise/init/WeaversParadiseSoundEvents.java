package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import xox.labvorty.weaversparadise.WeaversParadise;

public class WeaversParadiseSoundEvents {
    public static final SoundEvent HURT_OLD = registerSound("weaversparadise.hurt_old");
    public static final SoundEvent FALL_BIG_OLD = registerSound("weaversparadise.fall_big_old");
    public static final SoundEvent FALL_SMALL_OLD = registerSound("weaversparadise.fall_small_old");

    private static SoundEvent registerSound(String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, path);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void register() {
    }
}
