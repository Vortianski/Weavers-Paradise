package xox.labvorty.weaversparadise.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import xox.labvorty.weaversparadise.WeaversParadise;

public class WeaversParadiseParticles {
    public static final SimpleParticleType STARBLOOM_PARTICLE = Registry.register(
            BuiltInRegistries.PARTICLE_TYPE,
            ResourceLocation.fromNamespaceAndPath(WeaversParadise.MODID, "starbloom"),
            new SimpleParticleType(false) {}
    );

    public static void register() {
    }
}
