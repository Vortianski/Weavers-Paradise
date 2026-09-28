package xox.labvorty.weaversparadise.particles.providers;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import xox.labvorty.weaversparadise.particles.StarbloomParticle;

public class StarbloomParticleProvider implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet sprites;

    public StarbloomParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }

    @Override
    public Particle createParticle(
            SimpleParticleType type,
            ClientLevel level,
            double x,
            double y,
            double z,
            double xd,
            double yd,
            double zd
    ) {
        return new StarbloomParticle(level, x, y, z, this.sprites);
    }
}