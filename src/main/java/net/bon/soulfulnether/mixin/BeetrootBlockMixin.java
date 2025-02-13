package net.bon.soulfulnether.mixin;


import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("deprecation")

@Mixin(BeetrootBlock.class)
public class BeetrootBlockMixin extends CropBlock {

    public BeetrootBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        if (this == Blocks.BEETROOTS && floor.is(SoulfulBlockTags.SOUL_CONVERTING_BLOCKS)) {
            return true;
        }
        return super.mayPlaceOn(floor, world, pos);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify) {
        BlockState floor = world.getBlockState(pos.below());
        if (state.is(Blocks.BEETROOTS) && floor.is(SoulfulBlockTags.SOUL_CONVERTING_BLOCKS)) {
            world.setBlock(pos, SoulfulBlocks.SOULROOTS.get().defaultBlockState(), UPDATE_ALL);
        }
    }
}