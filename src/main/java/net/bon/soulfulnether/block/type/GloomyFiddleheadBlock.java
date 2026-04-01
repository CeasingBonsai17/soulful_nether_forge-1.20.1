package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.SoulfulTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class GloomyFiddleheadBlock extends BushBlock implements BonemealableBlock {
    public static final BooleanProperty HANGING;
    protected static final VoxelShape SHAPE;
    public GloomyFiddleheadBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(HANGING, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction[] var3 = context.getNearestLookingDirections();

        for (Direction direction : var3) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return defaultBlockState().setValue(HANGING, direction == Direction.UP);
            }
        }
        return null;
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = getConnectedDirection(state).getOpposite();
        return mayPlaceOn(level, pos.relative(direction), direction.getOpposite());
    }

    public static boolean mayPlaceOn(LevelReader level, BlockPos pos, Direction direction) {
        BlockState floor = level.getBlockState(pos);
        TagKey<Block> validSoil = SoulfulTags.VALID_GLOOMY_FIDDLEHEAD_SOILS;

        return floor.is(validSoil);
    }

    protected static Direction getConnectedDirection(BlockState state) {
        return state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HANGING});
    }

    static {
        HANGING = BlockStateProperties.HANGING;
        SHAPE = Block.box(2, 0.0, 2, 14, 16, 14);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean b) {
        return pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.relative(getConnectedDirection(state))).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        BlockState blockState = SoulfulBlocks.TALL_GLOOMY_FIDDLEHEAD.get().defaultBlockState();
        level.setBlock(pos, blockState.setValue(HANGING, state.getValue(HANGING)), 11);
        level.setBlock(pos.relative(getConnectedDirection(state)), blockState.setValue(HANGING, state.getValue(HANGING)).setValue(TallGloomyFiddleheadBlock.HALF, DoubleBlockHalf.UPPER), 11);
    }
}