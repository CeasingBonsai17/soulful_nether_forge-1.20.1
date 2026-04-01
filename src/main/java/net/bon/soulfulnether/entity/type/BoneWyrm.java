package net.bon.soulfulnether.entity.type;

import com.google.common.collect.UnmodifiableIterator;
import net.bon.soulfulnether.sound.SoulfulSoundEvents;
import net.bon.soulfulnether.util.SoulfulTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogAi;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;

public class BoneWyrm extends Animal implements ItemSteerable, Saddleable {
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final Ingredient FOOD_ITEMS;
    private final ItemBasedSteering steering;
    public final AnimationState idleAnimState = new AnimationState();
    private int idleAnimTimeout = 0;

    public BoneWyrm(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.setMaxUpStep(1.3f);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.steering = new ItemBasedSteering(entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);
    }

    public boolean canBreed() {
        return false;
    }

    public boolean isOnFire() {
        return false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new TemptGoal(this, 0.2, Ingredient.of(SoulfulTags.BONE_WYRM_FOODS), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new RandomStrollGoal(this, 0.2));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ARMOR_TOUGHNESS, 0.3)
                .add(Attributes.ATTACK_KNOCKBACK, 0.4)
                .add(Attributes.FOLLOW_RANGE, 4)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3);
    }

    @Override
    public void tick() {
        if(this.level().isClientSide()) {
            setupAnimStates();
        }
        super.tick();
    }

    @javax.annotation.Nullable
    public LivingEntity getControllingPassenger() {
        if (this.isSaddled()) {
            Entity entity = this.getFirstPassenger();
            if (entity instanceof Player player) {
                if (player.getMainHandItem().is(SoulfulTags.BONE_WYRM_LURE) || player.getOffhandItem().is(SoulfulTags.BONE_WYRM_LURE)) {
                    return player;
                }
            }
        }
        return null;
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        if (DATA_BOOST_TIME.equals(data) && level().isClientSide) {
            steering.onSynced();
        }

        super.onSyncedDataUpdated(data);
    }

    private void setupAnimStates() {
        if(idleAnimTimeout <= 0) {
            idleAnimTimeout = random.nextInt(40) + 80;
            idleAnimState.start(tickCount);
        } else {
            -- idleAnimTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float tick) {
        float f;
        if(getPose() == Pose.STANDING) {
            f = Math.min(tick * 6f, 1f);
        } else {
            f = 0.0f;
        }
        walkAnimation.update(f, 0.2f);
    }
    
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_SADDLE_ID, false);
        entityData.define(DATA_BOOST_TIME, 0);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        steering.addAdditionalSaveData(tag);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        steering.readAdditionalSaveData(tag);
    }

    protected SoundEvent getAmbientSound() {
        return SoulfulSoundEvents.BONE_WYRM_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damage) {
        return SoulfulSoundEvents.BONE_WYRM_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return SoulfulSoundEvents.BONE_WYRM_DEATH.get();
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        boolean flag = this.isFood(player.getItemInHand(hand));

        if (!flag && this.isSaddled() && !this.isVehicle() && !player.isSecondaryUseActive()) {
            if (!this.level().isClientSide) player.startRiding(this);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        if (player.getItemInHand(hand).isEmpty() && this.isSaddled() && !this.isVehicle() && player.isSecondaryUseActive()) {
            steering.setSaddle(false);
            level().playSound(null, this, SoulfulSoundEvents.BONE_WYRM_SADDLE.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
            player.setItemInHand(hand, Items.SADDLE.getDefaultInstance());
            return InteractionResult.SUCCESS;
        }
        else {
            InteractionResult interactionresult = super.mobInteract(player, hand);
            if (!interactionresult.consumesAction()) {
                ItemStack itemstack = player.getItemInHand(hand);
                return itemstack.is(Items.SADDLE) ? itemstack.interactLivingEntity(player, this, hand) : InteractionResult.PASS;
            } else {
                return interactionresult;
            }
        }
    }

    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }

    }

    public boolean isSaddled() {
        return this.steering.hasSaddle();
    }

    public void equipSaddle(@javax.annotation.Nullable SoundSource p_29476_) {
        this.steering.setSaddle(true);
        if (p_29476_ != null) {
            this.level().playSound(null, this, SoulfulSoundEvents.BONE_WYRM_SADDLE.get(), p_29476_, 0.5F, 1.0F);
        }

    }

    public Vec3 getDismountLocationForPassenger(LivingEntity p_29487_) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() == Direction.Axis.Y) {
            return super.getDismountLocationForPassenger(p_29487_);
        } else {
            int[][] aint = DismountHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (Pose pose : p_29487_.getDismountPoses()) {
                AABB aabb = p_29487_.getLocalBoundsForPose(pose);

                for (int[] aint1 : aint) {
                    blockpos$mutableblockpos.set(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                    if (DismountHelper.isBlockFloorValid(d0)) {
                        Vec3 vec3 = Vec3.upFromBottomCenterOf(blockpos$mutableblockpos, d0);
                        if (DismountHelper.canDismountTo(this.level(), p_29487_, aabb.move(vec3))) {
                            p_29487_.setPose(pose);
                            return vec3;
                        }
                    }
                }
            }

            return super.getDismountLocationForPassenger(p_29487_);
        }
    }

    protected void tickRidden(Player p_278330_, Vec3 p_278267_) {
        super.tickRidden(p_278330_, p_278267_);
        this.setRot(p_278330_.getYRot(), p_278330_.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        this.steering.tickBoost();
    }

    public double getPassengersRidingOffset() {
        return 0.45;
    }

    protected Vec3 getRiddenInput(Player p_278309_, Vec3 p_275479_) {
        return new Vec3(0.0, 0.0, 1.0);
    }

    protected float getRiddenSpeed(Player p_278258_) {
        return (float)(this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225 * (double)this.steering.boostFactor());
    }

    public boolean boost() {
        return this.steering.boost(this.getRandom());
    }
    

    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    public Vec3 getLeashOffset() {
        return new Vec3(0.0, (0.6F * this.getEyeHeight()), (this.getBbWidth() * 0.4F));
    }

    static {
        DATA_SADDLE_ID = SynchedEntityData.defineId(BoneWyrm.class, EntityDataSerializers.BOOLEAN);
        DATA_BOOST_TIME = SynchedEntityData.defineId(BoneWyrm.class, EntityDataSerializers.INT);
        FOOD_ITEMS = Ingredient.of(SoulfulTags.BONE_WYRM_FOODS);
    }
}
