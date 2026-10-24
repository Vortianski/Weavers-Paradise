package xox.labvorty.weaversparadise.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import xox.labvorty.weaversparadise.WeaversParadiseMod;
import xox.labvorty.weaversparadise.particles.providers.StarbloomParticleProvider;

public class WeaversParadiseParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_PROVIDERS = DeferredRegister.create(Registries.PARTICLE_TYPE, WeaversParadiseMod.MOD_ID);
    public static final RegistryObject<SimpleParticleType> STARBLOOM_PARTICLE = PARTICLE_PROVIDERS.register(
            "starbloom",
            () -> new SimpleParticleType(false)
    );

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class Providers {
        @SubscribeEvent
        public static void registerProviders(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(WeaversParadiseParticles.STARBLOOM_PARTICLE.get(), StarbloomParticleProvider::new);
        }
    }
}
