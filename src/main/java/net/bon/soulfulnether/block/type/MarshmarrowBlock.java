package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.bon.soulfulnether.util.SoulfulBlockTags;
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
    public static final int MAX_MARSHMARROWS = 3;
    public static final IntegerProperty MARSHMARROWS;
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty TOASTY;
    protected static final VoxelShape ONE_AABB;
    protected static final VoxelShape TWO_AABB;

    public MarshmarrowBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TOASTY, false).setValue(MARSHMARROWS, 1)).setValue(WATERLOGGED, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState $$1 = context.getLevel().getBlockState(context.getClickedPos());
        if ($$1.is(this)) {
            return (BlockState)$$1.setValue(MARSHMARROWS, Math.min(3, (Integer)$$1.getValue(MARSHMARROWS) + 1));
        } else {
            FluidState $$2 = context.getLevel().getFluidState(context.getClickedPos());
            LevelAccessor levelaccessor = context.getLevel();
            BlockPos blockpos = context.getClickedPos();
            boolean $$3 = $$2.getType() == Fluids.WATER;
            return (BlockState)super.getStateForPlacement(context).setValue(WATERLOGGED, $$3).setValue(TOASTY, this.isHeatSource(levelaccessor.getBlockState(blockpos.below())));
        }
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return !state.getCollisionShape(blockGetter, pos).getFaceShape(Direction.UP).isEmpty() || state.isFaceSturdy(blockGetter, pos, Direction.UP);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos $$3 = pos.below();
        return this.mayPlaceOn(level.getBlockState($$3), level, $$3);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        if (!state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if ((Boolean)state.getValue(WATERLOGGED)) {
                level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }

            return direction == Direction.DOWN ? (BlockState)state.setValue(TOASTY, this.isHeatSource(blockState)) : super.updateShape(state, direction, blockState, level, pos, blockPos);
        }
    }

    private boolean isHeatSource(BlockState state) {
        return state.is(SoulfulBlockTags.MARSHMARROW_HEAT_SOURCES);
    }


    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && (Integer)state.getValue(MARSHMARROWS) < 3 ? true : super.canBeReplaced(state, context);
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        switch ((Integer)state.getValue(MARSHMARROWS)) {
            case 1:
            default:
                return ONE_AABB;
            case 2, 3:
                return TWO_AABB;
        }
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(new Property[]{MARSHMARROWS, WATERLOGGED, TOASTY});
    }

    public boolean isPathfindable(BlockState state, BlockGetter blockGetter, BlockPos pos, PathComputationType pathComputationType) {
        return false;
    }

    static {
        MARSHMARROWS = SoulfulProperties.MARSHMARROWS;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        TOASTY = SoulfulProperties.TOASTY;
        ONE_AABB = Block.box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0);
        TWO_AABB = Block.box(2.0, 0.0, 2.0, 14.0, 9.0, 14.0);
    }
}