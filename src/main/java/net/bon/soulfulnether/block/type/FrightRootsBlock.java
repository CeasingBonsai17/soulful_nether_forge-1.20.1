package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FrightRootsBlock extends RootsBlock {
    public FrightRootsBlock(Properties properties) {
        super(properties);
    }
    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return state.is(SoulfulBlocks.FRIGHT_WART_BLOCK.get()) || state.is(SoulfulBlockTags.VALID_ROOT_BASES) || super.mayPlaceOn(state, blockGetter, pos);
    }
}
