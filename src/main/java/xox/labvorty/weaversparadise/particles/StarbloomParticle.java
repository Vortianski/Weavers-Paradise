package xox.labvorty.weaversparadise.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LightTexture;
import org.jetbrains.annotations.NotNull;

public class StarbloomParticle extends TextureSheetParticle {
    public StarbloomParticle(
            ClientLevel level,
            double x,
            double y,
            double z,
            SpriteSet sprites
    ) {
        super(level, x, y, z);

        this.lifetime = 30 + this.random.nextInt(15);

        this.quadSize = 0.15F + this.random.nextFloat() * 0.1F;

        this.gravity = 0.0F;

        this.xd = (this.random.nextDouble() - 0.5D) * 0.01D;
        this.yd = 0.005D + this.random.nextDouble() * 0.01D;
        this.zd = (this.random.nextDouble() - 0.5D) * 0.01D;

        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.removed) {
            return;
        }

        float progress = (float) this.age / (float) this.lifetime;

        this.quadSize = this.quadSize * (1.0F - progress);
        this.alpha = 1.0F - progress;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public int getLightColor(float partialTick) {
        return LightTexture.FULL_BRIGHT;
    }
}