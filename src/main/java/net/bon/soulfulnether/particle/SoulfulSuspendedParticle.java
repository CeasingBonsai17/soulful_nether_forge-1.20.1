package net.bon.soulfulnether.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SoulfulSuspendedParticle extends TextureSheetParticle {

    SoulfulSuspendedParticle(ClientLevel p_172403_, SpriteSet p_172404_, double p_172405_, double p_172406_, double p_172407_) {
        super(p_172403_, p_172405_, p_172406_ - 0.125, p_172407_);
        this.setSize(0.01F, 0.01F);
        this.pickSprite(p_172404_);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.2F;
        this.lifetime = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }

    SoulfulSuspendedParticle(ClientLevel p_172409_, SpriteSet p_172410_, double p_172411_, double p_172412_, double p_172413_, double p_172414_, double p_172415_, double p_172416_) {
        super(p_172409_, p_172411_, p_172412_ - 0.125, p_172413_, p_172414_, p_172415_, p_172416_);
        this.setSize(0.01F, 0.01F);
        this.pickSprite(p_172410_);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
        this.lifetime = (int) (16.0 / (Math.random() * 0.8 + 0.2));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class FrightEmberProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FrightEmberProvider(SpriteSet p_108084_) {
            this.sprite = p_108084_;
        }

        public Particle createParticle(SimpleParticleType p_108095_, ClientLevel p_108096_, double p_108097_, double p_108098_, double p_108099_, double p_108100_, double p_108101_, double p_108102_) {
            double $$8 = (double) p_108096_.random.nextFloat() * -1.9 * (double) p_108096_.random.nextFloat() * 0.1;
            SoulfulSuspendedParticle $$9 = new SoulfulSuspendedParticle(p_108096_, this.sprite, p_108097_, p_108098_, p_108099_, 0.0, $$8, 0.0);
            $$9.setColor(0.0F, 1.0F, 1.9F);
            $$9.setSize(0.001F, 0.001F);
            return $$9;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FrightSporeProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FrightSporeProvider(SpriteSet p_108084_) {
            this.sprite = p_108084_;
        }

        public Particle createParticle(SimpleParticleType p_108095_, ClientLevel p_108096_, double p_108097_, double p_108098_, double p_108099_, double p_108100_, double p_108101_, double p_108102_) {
            double $$8 = (double) p_108096_.random.nextFloat() * -1.9 * (double) p_108096_.random.nextFloat() * 0.1;
            SoulfulSuspendedParticle $$9 = new SoulfulSuspendedParticle(p_108096_, this.sprite, p_108097_, p_108098_, p_108099_, 0.0, $$8, 0.0);
            $$9.setColor(0.5F, 0.6F, 1.0F);
            $$9.setSize(0.001F, 0.001F);
            return $$9;
        }
    }
}
