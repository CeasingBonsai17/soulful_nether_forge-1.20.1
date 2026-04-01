package net.bon.soulfulnether.entity.type;

import net.bon.soulfulnether.entity.SoulfulEntityTypes;
import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class AshenSnowball extends ThrowableItemProjectile {
    public AshenSnowball(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }



    public AshenSnowball(Level level, LivingEntity entity) {
        super(SoulfulEntityTypes.ASHEN_SNOWBALL.get(), entity, level);
    }

    protected Item getDefaultItem() {
        return SoulfulItems.ASHEN_SNOWBALL.get();
    }


    @Override
    protected float getGravity() {
        return 0.05f;
    }

    private ParticleOptions getParticle() {
        ItemStack item = this.getItemRaw();
        return (item.isEmpty() ?
                new ItemParticleOption(ParticleTypes.ITEM, SoulfulItems.ASHEN_SNOWBALL.get().getDefaultInstance()) :
                new ItemParticleOption(ParticleTypes.ITEM, item));
    }

    public void handleEntityEvent(byte b) {
        if (b == 3) {
            ParticleOptions particle = getParticle();

            for(int count = 0; count < 8; ++count) {
                float speed = (float) level().random.nextInt(0, 3) / 10;
                level().addParticle(particle, getX(), getY(), getZ(), speed, 0.0f, speed);
//                level().addParticle(SoulfulParticleTypes.ASHEN_SNOW.get(), getX(), getY(), getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    public void tick() {
        super.tick();
        if (level().isClientSide) {
            level().addParticle(SoulfulParticleTypes.ASHEN_SNOW.get(), getRandomX(0.5), getRandomY(), getRandomZ(0.5), 0, 0, 0);;
        }

    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        float value = entity instanceof Blaze ? 4 : 2;
        entity.hurt(damageSources().thrown(this, getOwner()), value);
    }

    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!level().isClientSide) {
            level().broadcastEntityEvent(this, (byte)3);
            discard();
        }

    }
}
