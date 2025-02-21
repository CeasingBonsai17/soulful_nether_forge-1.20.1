package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AshenSnowLayerBlock extends Block {
    public static final int MAX_HEIGHT = 8;
    public static final IntegerProperty LAYERS;
    protected static final VoxelShape[] SHAPE_BY_LAYER;
    public static final int HEIGHT_IMPASSABLE = 5;

    public AshenSnowLayerBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 1));
    }

    public boolean isPathfindable(BlockState state, BlockGetter blockGetter, BlockPos pos, PathComputationType pathComputationType) {
        switch (pathComputationType) {
            case LAND:
                return (Integer)state.getValue(LAYERS) < 5;
            case WATER:
                return false;
            case AIR:
                return false;
            default:
                return false;
        }
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_LAYER[(Integer)state.getValue(LAYERS)];
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_LAYER[(Integer)state.getValue(LAYERS) - 1];
    }

    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return SHAPE_BY_LAYER[(Integer)state.getValue(LAYERS)];
    }

    public VoxelShape getVisualShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_LAYER[(Integer)state.getValue(LAYERS)];
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public float getShadeBrightness(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return (Integer)state.getValue(LAYERS) == 8 ? 0.2F : 1.0F;
    }

    public boolean canSurvive(BlockState state, LevelReader p_56603_, BlockPos pos) {
        BlockState $$3 = p_56603_.getBlockState(pos.below());
        return Block.isFaceFull($$3.getCollisionShape(p_56603_, pos.below()), Direction.UP) || $$3.is(this) && (Integer)$$3.getValue(LAYERS) == 8 || $$3.is(Blocks.SOUL_SAND);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        int $$2 = (Integer)state.getValue(LAYERS);
        if (context.getItemInHand().is(this.asItem()) && $$2 < 8) {
            if (context.replacingClickedOnBlock()) {
                return context.getClickedFace() == Direction.UP;
            } else {
                return true;
            }
        } else {
            return $$2 == 1;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState $$1 = context.getLevel().getBlockState(context.getClickedPos());
        if ($$1.is(this)) {
            int $$2 = (Integer)$$1.getValue(LAYERS);
            return (BlockState)$$1.setValue(LAYERS, Math.min(8, $$2 + 1));
        } else {
            return super.getStateForPlacement(context);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{LAYERS});
    }

    static {
        LAYERS = BlockStateProperties.LAYERS;
        SHAPE_BY_LAYER = new VoxelShape[]{Shapes.empty(), Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    }
}