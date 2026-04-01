package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GloomFungusBlock extends BushBlock implements BonemealableBlock {
    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);
    private final AbstractTreeGrower treeGrower;

    public GloomFungusBlock(AbstractTreeGrower tree, BlockBehaviour.Properties properties) {
        super(properties);
        treeGrower = tree;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || state.is(BlockTags.BASE_STONE_NETHER) || state.is(SoulfulBlocks.LICHOSS_BLOCK.get()) || state.is(SoulfulBlocks.GLOOM_FUNGUS_CAP.get()) || state.is(Blocks.MYCELIUM) || state.is(Blocks.SOUL_SOIL) || super.mayPlaceOn(state, blockGetter, pos);
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource source) {
            treeGrower.growTree(level, level.getChunkSource().getGenerator(), pos, state, source);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean bl) {
        BlockState blockstate = level.getBlockState(pos.below());
        return blockstate.is(BlockTags.NYLIUM) || blockstate.is(Blocks.MYCELIUM);
    }

    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state) {
        return level.random.nextFloat() < 0.45;
    }

    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        advanceTree(level, pos, state, source);
    }
}