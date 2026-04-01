package net.bon.soulfulnether.particle.type;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class SapFallAndLandParticle extends SoulfulDripParticle {
    private static final Vector3f ROTATION_VECTOR = (new Vector3f(0.5F, 0.5F, 0.5F)).normalize();
    private static final Vector3f TRANSFORM_VECTOR = new Vector3f(-1.0F, -1.0F, 0.0F);
    private final int delay;

    protected SapFallAndLandParticle(ClientLevel level, double x, double y, double z, Fluid fluid, SpriteSet spriteSet) {
        super(level, x, y, z, fluid);
        setSpriteFromAge(spriteSet);
        this.delay = 0;
        this.gravity = 0.08F;
        this.lifetime = (int)(64.0 / (Math.random() * 0.8 + 0.2));
        this.setColor(1.0f, 1.0f, 1.0f);
    }

    public void render(VertexConsumer vertexConsumer, Camera camera, float f) {
        if (this.delay <= 0) {
            this.alpha = 1.0F - Mth.clamp((age + f) / lifetime, 0.0F, 1.0F);
            this.renderRotatedParticle(vertexConsumer, camera, f, (consumer) -> {
                consumer.mul((new Quaternionf()).rotateZ(Mth.lerp(f, oRoll, roll)));
            });
            this.renderRotatedParticle(vertexConsumer, camera, f, (consumer) -> {
                consumer.mul((new Quaternionf()).rotationYXZ(0f, -3.1415927f, 0.0f));
            });
        }
    }

    private void renderRotatedParticle(VertexConsumer vertexConsumer, Camera camera, float f, Consumer<Quaternionf> consumer) {
        Vec3 pos = camera.getPosition();
        Quaternionf matrix = (new Quaternionf()).setAngleAxis(0.0F, ROTATION_VECTOR.x(), ROTATION_VECTOR.y(), ROTATION_VECTOR.z());
        consumer.accept(matrix);
        matrix.transform(TRANSFORM_VECTOR);
        Vector3f[] vec = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float size = getQuadSize(f);

        int color;
        for(color = 0; color < 4; ++color) {
            Vector3f vec3f = vec[color];
            vec3f.rotate(matrix);
            vec3f.mul(size);
            vec3f.add((float)(Mth.lerp(f, xo, x) - pos.x()), (float)(Mth.lerp(f, yo, y) - pos.y()), (float)(Mth.lerp(f, zo, z) - pos.z()));
        }

        color = getLightColor(f);
        makeCornerVertex(vertexConsumer, vec[0], getU1(), getV1(), color);
        makeCornerVertex(vertexConsumer, vec[1], getU1(), getV0(), color);
        makeCornerVertex(vertexConsumer, vec[2], getU0(), getV0(), color);
        makeCornerVertex(vertexConsumer, vec[3], getU0(), getV1(), color);
    }

    private void makeCornerVertex(VertexConsumer vertexConsumer, Vector3f vector3f, float f, float e, int d) {
        vertexConsumer.vertex(vector3f.x(), vector3f.y(), vector3f.z()).uv(f, e).color(rCol, gCol, bCol, alpha).uv2(d).endVertex();
    }

    protected void postMoveUpdate() {
        if (onGround) {
            remove();
            level.playSound(null, x, y, z, SoundEvents.ANCIENT_DEBRIS_FALL, SoundSource.WEATHER, 0.2f, 1.0f);
            level.addParticle(SoulfulParticleTypes.LANDING_GLOOMY_RAIN.get(), x, y, z, 0.0, 0.0, 0.0);
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SapFallAndLandParticle(level, x, y, z, Fluids.EMPTY, spriteSet);
        }
    }
}
