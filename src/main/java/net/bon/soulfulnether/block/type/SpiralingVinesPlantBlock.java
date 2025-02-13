package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpiralingVinesPlantBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
    public SpiralingVinesPlantBlock(BlockBehaviour.Properties settings) {
        super(settings, Direction.UP, SHAPE, false);
    }
        protected GrowingPlantHeadBlock getHeadBlock() {
            return (GrowingPlantHeadBlock) SoulfulBlocks.SPIRALING_VINES.get();
    }
}
