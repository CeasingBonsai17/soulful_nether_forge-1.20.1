package net.bon.soulfulnether.entity.type;

import net.bon.soulfulnether.sound.SoulfulSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class Wallow extends FlyingMob implements Enemy {
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(Wallow.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState idleAnimState = new AnimationState();
    private int idleAnimTimeout = 0;

    public Wallow(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.moveControl = new Wallow.WallowMoveControl(this);
    }

    protected void registerGoals() {
        goalSelector.addGoal(5, new Wallow.RandomFloatAroundGoal(this));
        goalSelector.addGoal(6, new Wallow.WallowLookGoal(this));
        goalSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, 10, true, false, (entity) ->
                Math.abs(entity.getY() - getY()) <= 4.0));
    }

    public boolean isCharging() {
        return entityData.get(DATA_IS_CHARGING);
    }

    public void setCharging(boolean p_32759_) {
        entityData.set(DATA_IS_CHARGING, p_32759_);
    }

    public int getExplosionPower() {
        return 1;
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    public void tick() {
        if(level().isClientSide()) {
            setupAnimStates();
        }
        super.tick();
    }

    private static boolean isReflectedFireball(DamageSource p_238408_) {
        return p_238408_.getDirectEntity() instanceof LargeFireball && p_238408_.getEntity() instanceof Player;
    }

    public boolean isInvulnerableTo(DamageSource p_238289_) {
        return !isReflectedFireball(p_238289_) && super.isInvulnerableTo(p_238289_);
    }

    public boolean hurt(DamageSource p_32730_, float p_32731_) {
        if (isReflectedFireball(p_32730_)) {
            super.hurt(p_32730_, 1000.0F);
            return true;
        } else {
            return !isInvulnerableTo(p_32730_) && super.hurt(p_32730_, p_32731_);
        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
    }

    private void setupAnimStates() {
        if(idleAnimTimeout <= 0) {
            idleAnimTimeout = random.nextInt(40) + 80;
            idleAnimState.start(tickCount);
        } else {
            -- idleAnimTimeout;
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.FOLLOW_RANGE, 100.0)
                .add(Attributes.FLYING_SPEED, 0.2)
                .add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    protected SoundEvent getAmbientSound() {
        return SoulfulSoundEvents.WALLOW_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damage) {
        return SoulfulSoundEvents.WALLOW_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return SoulfulSoundEvents.WALLOW_DEATH.get();
    }

    protected float getSoundVolume() {
        return 5.0F;
    }

    public static boolean checkWallowSpawnRules(EntityType<Wallow> p_218985_, LevelAccessor p_218986_, MobSpawnType p_218987_, BlockPos p_218988_, RandomSource p_218989_) {
        return p_218986_.getDifficulty() != Difficulty.PEACEFUL && p_218989_.nextInt(20) == 0 && checkMobSpawnRules(p_218985_, p_218986_, p_218987_, p_218988_, p_218989_);
    }

    public int getMaxSpawnClusterSize() {
        return 2;
    }

    public void addAdditionalSaveData(CompoundTag p_32744_) {
        super.addAdditionalSaveData(p_32744_);
    }

    public void readAdditionalSaveData(CompoundTag p_32733_) {
        super.readAdditionalSaveData(p_32733_);
    }

    protected float getStandingEyeHeight(Pose p_32741_, EntityDimensions p_32742_) {
        return 2.6F;
    }

    private static class WallowMoveControl extends MoveControl {
        private final Wallow wallow;
        private int floatDuration;

        public WallowMoveControl(Wallow p_32768_) {
            super(p_32768_);
            this.wallow = p_32768_;
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.wallow.getRandom().nextInt(5) + 2;
                    Vec3 $$0 = new Vec3(this.wantedX - this.wallow.getX(), this.wantedY - this.wallow.getY(), this.wantedZ - this.wallow.getZ());
                    double $$1 = $$0.length();
                    $$0 = $$0.normalize();
                    if (this.canReach($$0, Mth.ceil($$1))) {
                        this.wallow.setDeltaMovement(this.wallow.getDeltaMovement().add($$0.scale(0.1)));
                    } else {
                        this.operation = Operation.WAIT;
                    }
                }

            }
        }



        private boolean canReach(Vec3 p_32771_, int p_32772_) {
            AABB $$2 = this.wallow.getBoundingBox();

            for(int $$3 = 1; $$3 < p_32772_; ++$$3) {
                $$2 = $$2.move(p_32771_);
                if (!this.wallow.level().noCollision(this.wallow, $$2)) {
                    return false;
                }
            }

            return true;
        }
    }

    private static class RandomFloatAroundGoal extends Goal {
        private final Wallow Wallow;

        public RandomFloatAroundGoal(Wallow p_32783_) {
            this.Wallow = p_32783_;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movement = Wallow.getMoveControl();
            if (!movement.hasWanted()) {
                return true;
            } else {
                double moveX = movement.getWantedX() - this.Wallow.getX();
                double moveY = movement.getWantedY() - this.Wallow.getY();
                double moveZ = movement.getWantedZ() - this.Wallow.getZ();
                double destination = moveX * moveX + moveY * moveY + moveZ * moveZ;
                return destination < 1.0 || destination > 3600.0;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource random = Wallow.getRandom();
            double $$1 = Wallow.getX() + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double $$2 = Wallow.getY() + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double $$3 = Wallow.getZ() + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            Wallow.getMoveControl().setWantedPosition($$1, $$2, $$3, 0.1);
        }
    }

    static class WallowLookGoal extends Goal {
        private final Wallow Wallow;

        public WallowLookGoal(Wallow wallow) {
            Wallow = wallow;
            setFlags(EnumSet.of(Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.Wallow.getTarget() == null) {
                Vec3 vector = Wallow.getDeltaMovement();
                Wallow.setYRot(-((float)Mth.atan2(vector.x, vector.z)) * 57.295776F);
                Wallow.yBodyRot = Wallow.getYRot();
            } else {
                LivingEntity target = Wallow.getTarget();
                if (target.distanceToSqr(Wallow) < 4096.0) {
                    double targetX = target.getX() - Wallow.getX();
                    double targetZ = target.getZ() - Wallow.getZ();
                    Wallow.setYRot(-((float)Mth.atan2(targetX, targetZ)) * 57.295776F);
                    Wallow.yBodyRot = Wallow.getYRot();
                }
            }
        }
    }

    private static class WallowScreamGoal extends Goal {
        private final Wallow Wallow;
        public int chargeTime;

        public WallowScreamGoal(Wallow p_32776_) {
            Wallow = p_32776_;
        }

        public boolean canUse() {
            return Wallow.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void stop() {
            Wallow.setCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity $$0 = Wallow.getTarget();
            if ($$0 != null) {
                double $$1 = 64.0;
                if ($$0.distanceToSqr(Wallow) < 4096.0 && Wallow.hasLineOfSight($$0)) {
                    Level $$2 = Wallow.level();
                    ++this.chargeTime;
                    if (this.chargeTime == 10 && !Wallow.isSilent()) {
                        $$2.levelEvent(null, 1015, Wallow.blockPosition(), 0);
                    }

                    if (this.chargeTime == 20) {
                        double $$3 = 4.0;
                        Vec3 $$4 = Wallow.getViewVector(1.0F);
                        double $$5 = $$0.getX() - (Wallow.getX() + $$4.x * 4.0);
                        double $$6 = $$0.getY(0.5) - (0.5 + Wallow.getY(0.5));
                        double $$7 = $$0.getZ() - (Wallow.getZ() + $$4.z * 4.0);
                        if (!Wallow.isSilent()) {
                            $$2.levelEvent(null, 1016, Wallow.blockPosition(), 0);
                        }

                        LargeFireball $$8 = new LargeFireball($$2, Wallow, $$5, $$6, $$7, Wallow.getExplosionPower());
                        $$8.setPos(Wallow.getX() + $$4.x * 4.0, Wallow.getY(0.5) + 0.5, $$8.getZ() + $$4.z * 4.0);
                        $$2.addFreshEntity($$8);
                        this.chargeTime = -40;
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                Wallow.setCharging(this.chargeTime > 10);
            }
        }
    }
}