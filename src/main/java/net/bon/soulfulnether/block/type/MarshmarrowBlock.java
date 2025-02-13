package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MarshmarrowBlock extends BushBlock implements SimpleWaterloggedBlock {
    private final Float fall;
    public static final int MAX_MARSHMARROWS = 3;
    public static final IntegerProperty MARSHMARROWS;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape ONE_AABB;
    protected static final VoxelShape TWO_AABB;

    public MarshmarrowBlock(BlockBehaviour.Properties p_56082_, Float fall) {
        super(p_56082_);
        this.fall = fall;
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(MARSHMARROWS, 1)).setValue(WATERLOGGED, true));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_56089_) {
        BlockState $$1 = p_56089_.getLevel().getBlockState(p_56089_.getClickedPos());
        if ($$1.is(this)) {
            return (BlockState)$$1.setValue(MARSHMARROWS, Math.min(3, (Integer)$$1.getValue(MARSHMARROWS) + 1));
        } else {
            FluidState $$2 = p_56089_.getLevel().getFluidState(p_56089_.getClickedPos());
            boolean $$3 = $$2.getType() == Fluids.WATER;
            return (BlockState)super.getStateForPlacement(p_56089_).setValue(WATERLOGGED, $$3);
        }
    }

    protected boolean mayPlaceOn(BlockState p_56127_, BlockGetter p_56128_, BlockPos p_56129_) {
        return !p_56127_.getCollisionShape(p_56128_, p_56129_).getFaceShape(Direction.UP).isEmpty() || p_56127_.isFaceSturdy(p_56128_, p_56129_, Direction.UP);
    }

    public boolean canSurvive(BlockState p_56109_, LevelReader p_56110_, BlockPos p_56111_) {
        BlockPos $$3 = p_56111_.below();
        return this.mayPlaceOn(p_56110_.getBlockState($$3), p_56110_, $$3);
    }

    public BlockState updateShape(BlockState p_56113_, Direction p_56114_, BlockState p_56115_, LevelAccessor p_56116_, BlockPos p_56117_, BlockPos p_56118_) {
        if (!p_56113_.canSurvive(p_56116_, p_56117_)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if ((Boolean)p_56113_.getValue(WATERLOGGED)) {
                p_56116_.scheduleTick(p_56117_, Fluids.WATER, Fluids.WATER.getTickDelay(p_56116_));
            }

            return super.updateShape(p_56113_, p_56114_, p_56115_, p_56116_, p_56117_, p_56118_);
        }
    }

    public boolean canBeReplaced(BlockState p_56101_, BlockPlaceContext p_56102_) {
        return !p_56102_.isSecondaryUseActive() && p_56102_.getItemInHand().is(this.asItem()) && (Integer)p_56101_.getValue(MARSHMARROWS) < 3 ? true : super.canBeReplaced(p_56101_, p_56102_);
    }

    public VoxelShape getShape(BlockState p_56122_, BlockGetter p_56123_, BlockPos p_56124_, CollisionContext p_56125_) {
        switch ((Integer)p_56122_.getValue(MARSHMARROWS)) {
            case 1:
            default:
                return ONE_AABB;
            case 2, 3:
                return TWO_AABB;
        }
    }

    public FluidState getFluidState(BlockState p_56131_) {
        return (Boolean)p_56131_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56131_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56120_) {
        p_56120_.add(new Property[]{MARSHMARROWS, WATERLOGGED});
    }

    public boolean isPathfindable(BlockState p_56104_, BlockGetter p_56105_, BlockPos p_56106_, PathComputationType p_56107_) {
        return false;
    }

    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, fall, world.damageSources().fall());
    }

    static {
        MARSHMARROWS = SoulfulProperties.MARSHMARROWS;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        ONE_AABB = Block.box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0);
        TWO_AABB = Block.box(2.0, 0.0, 2.0, 14.0, 9.0, 14.0);
        }
}