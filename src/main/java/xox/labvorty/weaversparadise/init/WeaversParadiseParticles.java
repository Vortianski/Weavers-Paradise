package xox.labvorty.weaversparadise.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xox.labvorty.weaversparadise.WeaversParadise;
import xox.labvorty.weaversparadise.particles.providers.StarbloomParticleProvider;

public class WeaversParadiseParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_PROVIDERS = DeferredRegister.create(Registries.PARTICLE_TYPE, WeaversParadise.MODID);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> STARBLOOM_PARTICLE = PARTICLE_PROVIDERS.register(
            "starbloom",
            () -> new SimpleParticleType(false)
    );

    @EventBusSubscriber
    public static class Providers {
        @SubscribeEvent
        public static void registerProviders(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(WeaversParadiseParticles.STARBLOOM_PARTICLE.get(), StarbloomParticleProvider::new);
        }
    }
}
