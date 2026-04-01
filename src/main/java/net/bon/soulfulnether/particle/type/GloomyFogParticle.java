package net.bon.soulfulnether.particle.type;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GloomyFogParticle extends TextureSheetParticle {
    GloomyFogParticle(ClientLevel level, double d, double e, double f, double x, double y, double z, boolean b) {
        super(level, d, e, f);
        this.scale(5.0F);
        this.setSize(0.40F, 0.80F);
        if (b) {
            this.lifetime = this.random.nextInt(50) + 280;
        } else {
            this.lifetime = this.random.nextInt(50) + 80;
        }

        this.gravity = 3.0E-6F;
        this.xd = x;
        this.yd = y + (double)(this.random.nextFloat() / 500.0F);
        this.zd = z;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }

        } else {
            this.remove();
        }
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprite) {
            this.sprites = sprite;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double d, double e, double f, double g, double h, double i) {
            GloomyFogParticle $$8 = new GloomyFogParticle(level, d, e, f, g, h, i, false);
            $$8.setAlpha(0.9F);
            $$8.pickSprite(this.sprites);
            return $$8;
        }
    }
}