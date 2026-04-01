package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class AshenBasaltBlock extends RotatedPillarBlock {
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    public AshenBasaltBlock(Properties properties) {
        super(properties);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return direction == Direction.UP ? state.setValue(SNOWY, isSnowSource(blockState)) : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    private boolean isSnowSource(BlockState state) {
        return state.is(SoulfulBlocks.ASHEN_SNOW_BLOCK.get()) || state.is(SoulfulBlocks.ASHEN_SNOW_BLOCK.get());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{SNOWY, AXIS});
    }
}
