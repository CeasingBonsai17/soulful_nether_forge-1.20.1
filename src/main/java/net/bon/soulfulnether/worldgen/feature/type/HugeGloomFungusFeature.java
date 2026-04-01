package net.bon.soulfulnether.worldgen.feature.type;

import com.mojang.serialization.Codec;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.block.type.GloomStemBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeGloomFungusFeature extends AbstractHugeMushroomFeature {
    public HugeGloomFungusFeature(Codec<HugeMushroomFeatureConfiguration> properties) {
        super(properties);
    }

    protected void placeTrunk(LevelAccessor level, RandomSource source, BlockPos pos, HugeMushroomFeatureConfiguration config, int i, BlockPos.MutableBlockPos blockPos) {
        for(int Integer = 0; Integer < i; ++Integer) {
            blockPos.set(pos).move(Direction.UP, Integer);
            if (!level.getBlockState(blockPos).isSolidRender(level, blockPos)) {
                this.setBlock(level, blockPos, SoulfulBlocks.GLOOM_FUNGUS_STEM.get().defaultBlockState());
            } if ((level.getBlockState(blockPos.above()).is(SoulfulBlocks.GLOOM_FUNGUS_CAP.get()))) {
                this.setBlock(level, blockPos, SoulfulBlocks.GLOOM_FUNGUS_STEM.get().defaultBlockState().setValue(GloomStemBlock.LIT, true));
            }
        }

    }
    protected void makeCap(LevelAccessor level, RandomSource random, BlockPos pos, int p_225046_, BlockPos.MutableBlockPos p_225047_, HugeMushroomFeatureConfiguration p_225048_) {
        int $$6 = p_225048_.foliageRadius;

        for(int count = -$$6; count <= $$6; ++count) {
            for(int $$8 = -$$6; $$8 <= $$6; ++$$8) {
                boolean $$9 = count == -$$6;
                boolean $$10 = count == $$6;
                boolean $$11 = $$8 == -$$6;
                boolean $$12 = $$8 == $$6;
                boolean $$13 = $$9 || $$10;
                boolean $$14 = $$11 || $$12;
                if (!$$13 || !$$14) {
                    p_225047_.setWithOffset(pos, count, p_225046_, $$8);
                    if (!level.getBlockState(p_225047_).isSolidRender(level, p_225047_)) {
                        BlockState $$19 = p_225048_.capProvider.getState(random, pos);
                        this.setBlock(level, p_225047_, $$19);
                    }
                }
            }
        }

    }


    protected int getTreeHeight(RandomSource random) {
        return random.nextInt(5, 8);
    }

    protected int getTreeRadiusForHeight(int p_65881_, int p_65882_, int p_65883_, int p_65884_) {
        return p_65884_ <= 3 ? 0 : p_65883_;
    }

}
