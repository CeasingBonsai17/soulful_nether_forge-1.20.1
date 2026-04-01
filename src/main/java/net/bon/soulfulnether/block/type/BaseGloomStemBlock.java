package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;

public class BaseGloomStemBlock extends RotatedPillarBlock {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public BaseGloomStemBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false).setValue(AXIS, Direction.Axis.Y));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(LIT, this.isCap(levelaccessor.getBlockState(blockpos.above())));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return direction == Direction.UP ? (BlockState)state.setValue(LIT, this.isCap(blockState)) : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    private boolean isCap(BlockState state) {
        return state.is(SoulfulBlocks.GLOOM_FUNGUS_CAP.get());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{LIT, AXIS});
    }
}