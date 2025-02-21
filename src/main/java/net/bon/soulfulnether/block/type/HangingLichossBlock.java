package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingLichossBlock extends GrowingPlantHeadBlock {
    protected static final VoxelShape SHAPE = Block.box(1.0, 3.0, 1.0, 15.0, 16.0, 15.0);

    public HangingLichossBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1);
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource source) {
        return NetherVines.getBlocksToGrowWhenBonemealed(source);
    }

    protected Block getBodyBlock() {
        return SoulfulBlocks.HANGING_LICHOSS_PLANT.get();
    }

    protected boolean canGrowInto(BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }
}