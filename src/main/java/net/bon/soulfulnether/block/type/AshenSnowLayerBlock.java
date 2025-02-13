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

    public AshenSnowLayerBlock(BlockBehaviour.Properties p_56585_) {
        super(p_56585_);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 1));
    }

    public boolean isPathfindable(BlockState p_56592_, BlockGetter p_56593_, BlockPos p_56594_, PathComputationType p_56595_) {
        switch (p_56595_) {
            case LAND:
                return (Integer)p_56592_.getValue(LAYERS) < 5;
            case WATER:
                return false;
            case AIR:
                return false;
            default:
                return false;
        }
    }

    public VoxelShape getShape(BlockState p_56620_, BlockGetter p_56621_, BlockPos p_56622_, CollisionContext p_56623_) {
        return SHAPE_BY_LAYER[(Integer)p_56620_.getValue(LAYERS)];
    }

    public VoxelShape getCollisionShape(BlockState p_56625_, BlockGetter p_56626_, BlockPos p_56627_, CollisionContext p_56628_) {
        return SHAPE_BY_LAYER[(Integer)p_56625_.getValue(LAYERS) - 1];
    }

    public VoxelShape getBlockSupportShape(BlockState p_56632_, BlockGetter p_56633_, BlockPos p_56634_) {
        return SHAPE_BY_LAYER[(Integer)p_56632_.getValue(LAYERS)];
    }

    public VoxelShape getVisualShape(BlockState p_56597_, BlockGetter p_56598_, BlockPos p_56599_, CollisionContext p_56600_) {
        return SHAPE_BY_LAYER[(Integer)p_56597_.getValue(LAYERS)];
    }

    public boolean useShapeForLightOcclusion(BlockState p_56630_) {
        return true;
    }

    public float getShadeBrightness(BlockState p_222453_, BlockGetter p_222454_, BlockPos p_222455_) {
        return (Integer)p_222453_.getValue(LAYERS) == 8 ? 0.2F : 1.0F;
    }

    public boolean canSurvive(BlockState p_56602_, LevelReader p_56603_, BlockPos p_56604_) {
        BlockState $$3 = p_56603_.getBlockState(p_56604_.below());
        return Block.isFaceFull($$3.getCollisionShape(p_56603_, p_56604_.below()), Direction.UP) || $$3.is(this) && (Integer)$$3.getValue(LAYERS) == 8;
    }

    public BlockState updateShape(BlockState p_56606_, Direction p_56607_, BlockState p_56608_, LevelAccessor p_56609_, BlockPos p_56610_, BlockPos p_56611_) {
        return !p_56606_.canSurvive(p_56609_, p_56610_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_56606_, p_56607_, p_56608_, p_56609_, p_56610_, p_56611_);
    }

    public boolean canBeReplaced(BlockState p_56589_, BlockPlaceContext p_56590_) {
        int $$2 = (Integer)p_56589_.getValue(LAYERS);
        if (p_56590_.getItemInHand().is(this.asItem()) && $$2 < 8) {
            if (p_56590_.replacingClickedOnBlock()) {
                return p_56590_.getClickedFace() == Direction.UP;
            } else {
                return true;
            }
        } else {
            return $$2 == 1;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_56587_) {
        BlockState $$1 = p_56587_.getLevel().getBlockState(p_56587_.getClickedPos());
        if ($$1.is(this)) {
            int $$2 = (Integer)$$1.getValue(LAYERS);
            return (BlockState)$$1.setValue(LAYERS, Math.min(8, $$2 + 1));
        } else {
            return super.getStateForPlacement(p_56587_);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56613_) {
        p_56613_.add(new Property[]{LAYERS});
    }

    static {
        LAYERS = BlockStateProperties.LAYERS;
        SHAPE_BY_LAYER = new VoxelShape[]{Shapes.empty(), Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    }
}