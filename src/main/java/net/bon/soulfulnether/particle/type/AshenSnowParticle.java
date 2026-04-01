package net.bon.soulfulnether.particle.type;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AshenSnowParticle extends BaseAshSmokeParticle {
    private static final int COLOR_RGB24 = 16777216;

    protected AshenSnowParticle(ClientLevel level, double d, double o, double u, double b, double l, double e, float f, SpriteSet sprite) {
        super(level, d, o, u, 0.1F, -0.1F, 0.1F, b, l, e, f, sprite, 0.0F, 26, 0.0125F, false);
        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;
        this.hasPhysics = true;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprite) {
            this.sprites = sprite;
        }

        public Particle createParticle(SimpleParticleType particle, ClientLevel level, double d, double o, double u, double b, double l, double e) {
            RandomSource random = level.random;
            double $$9 = random.nextFloat() * -1.9 * random.nextFloat() * 0.1;
            double $$10 = random.nextFloat() * -0.5 * random.nextFloat() * 0.1 * 5.0;
            double $$11 = random.nextFloat() * -1.9 * random.nextFloat() * 0.1;
            return new AshenSnowParticle(level, d, o, u, $$9, $$10, $$11, 1.0F, this.sprites);
        }
    }
}