package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FrightFungusBlock extends SaplingBlock implements BonemealableBlock {
    private final Block nylium;
    public FrightFungusBlock(AbstractTreeGrower generator, BlockBehaviour.Properties settings, Block nylium) {
        super(generator, settings);
        this.nylium = nylium;
    }

    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(BlockTags.NYLIUM) || floor.is(SoulfulBlockTags.LICHOSS) || floor.is(Blocks.MYCELIUM) || floor.is(Blocks.SOUL_SOIL) || super.mayPlaceOn(floor, world, pos);
    }
    public boolean isValidBonemealTarget (LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
        BlockState blockState = world.getBlockState(pos.below());
        return blockState.is(this.nylium);
    }

}
