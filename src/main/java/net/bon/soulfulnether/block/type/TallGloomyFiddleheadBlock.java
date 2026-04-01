package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.util.SoulfulTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class TallGloomyFiddleheadBlock extends BushBlock implements BonemealableBlock {
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public TallGloomyFiddleheadBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, false).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean b) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        popResource(level, pos, new ItemStack(this));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        if (direction.getAxis() != Direction.Axis.Y || state.getValue(HALF) == DoubleBlockHalf.LOWER != (direction == getConnectedDirection(state)) || blockState.is(this) && blockState.getValue(HALF) != state.getValue(HALF)) {
            return state.getValue(HALF) == DoubleBlockHalf.LOWER && direction == getConnectedDirection(state).getOpposite() && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, level, pos, blockPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction[] var3 = context.getNearestLookingDirections();
        int var4 = var3.length;
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();

        for(int var5 = 0; var5 < var4; ++var5) {
            Direction direction = var3[var5];
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (blockState.canSurvive(context.getLevel(), context.getClickedPos()) && level.getBlockState(blockpos.below()).canBeReplaced(context)) {
                    return blockState.setValue(HANGING, direction == Direction.UP);
                } if (blockState.canSurvive(context.getLevel(), context.getClickedPos()) && level.getBlockState(blockpos.above()).canBeReplaced(context)) {
                    return this.defaultBlockState();
                }
            }
        }
        return null;
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return validLocation(state, level, pos);
        } else {
            BlockState blockstate = level.getBlockState(pos.relative(getConnectedDirection(state).getOpposite()));
            if (state.getBlock() != this) {
                return validLocation(state, level, pos);
            } else {
                return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
            }
        }
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        if (state.getValue(HANGING)) {
            level.setBlock(pos.below(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(HANGING, true), 3);
        } if (!state.getValue(HANGING)) {
            level.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(HANGING, false), 3);
        }
    }

    public static void placeAt(LevelAccessor level, BlockState state, BlockPos pos, int i) {
        level.setBlock(pos, state.setValue(HALF, DoubleBlockHalf.LOWER), i);
        level.setBlock(pos.relative(getConnectedDirection(state)), state.setValue(HALF, DoubleBlockHalf.UPPER), i);
    }

    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventCreativeDropFromBottomPart(level, pos, state, player);
            } else {
                dropResources(state, level, pos, null, player, player.getMainHandItem());
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }

    }
    
    public void playerDestroy(Level p_52865_, Player p_52866_, BlockPos p_52867_, BlockState p_52868_, @Nullable BlockEntity p_52869_, ItemStack p_52870_) {
        super.playerDestroy(p_52865_, p_52866_, p_52867_, Blocks.AIR.defaultBlockState(), p_52869_, p_52870_);
    }

    public boolean validLocation(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = getConnectedDirection(state).getOpposite();
        return mayPlaceOn(level, pos.relative(direction), direction.getOpposite());
    }

    public static boolean mayPlaceOn(LevelReader level, BlockPos pos, Direction direction) {
        return level.getBlockState(pos).is(SoulfulTags.VALID_GLOOMY_FIDDLEHEAD_SOILS);
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            entity.makeStuckInBlock(state, new Vec3(0.8, 0.75, 0.8));
        }
    }

    protected static Direction getConnectedDirection(BlockState state) {
        return state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HANGING, HALF});
    }
}
