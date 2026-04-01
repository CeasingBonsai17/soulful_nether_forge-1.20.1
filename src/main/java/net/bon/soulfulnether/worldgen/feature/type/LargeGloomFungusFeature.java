package net.bon.soulfulnether.worldgen.feature.type;

import com.mojang.serialization.Codec;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.block.type.GloomStemBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class LargeGloomFungusFeature extends AbstractHugeMushroomFeature {
    public LargeGloomFungusFeature(Codec<HugeMushroomFeatureConfiguration> p_65975_) {
        super(p_65975_);
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


    protected void makeCap(LevelAccessor level, RandomSource source, BlockPos pos, int i, BlockPos.MutableBlockPos blockPos, HugeMushroomFeatureConfiguration config) {
        for(int Integer = i; Integer <= i; ++Integer) {
            int $$7 = Integer < i ? config.foliageRadius : config.foliageRadius - 1;
            int $$8 = config.foliageRadius - 2;

            for(int $$9 = -$$7; $$9 <= $$7; ++$$9) {
                for(int $$10 = -$$7; $$10 <= $$7; ++$$10) {
                    boolean $$11 = $$9 == -$$7;
                    boolean $$12 = $$9 == $$7;
                    boolean $$13 = $$10 == -$$7;
                    boolean $$14 = $$10 == $$7;
                    boolean $$15 = $$11 || $$12;
                    boolean $$16 = $$13 || $$14;
                    if (Integer >= i || $$15 != $$16) {
                        blockPos.setWithOffset(pos, $$9, Integer, $$10);
                        if (!level.getBlockState(blockPos).isSolidRender(level, blockPos)) {
                            BlockState $$17 = config.capProvider.getState(source, pos);
                            this.setBlock(level, blockPos, $$17);
                        }
                    }
                }
            }
        }

    }

    protected int getTreeRadiusForHeight(int p_65977_, int p_65978_, int p_65979_, int p_65980_) {
        int Integer = 0;
        if (p_65980_ == p_65978_) {
            Integer = p_65979_;
        }

        return Integer;
    }

    @Override
    protected boolean isValidPosition(LevelAccessor p_65099_, BlockPos p_65100_, int p_65101_, BlockPos.MutableBlockPos p_65102_, HugeMushroomFeatureConfiguration p_65103_) {
        int $$5 = p_65100_.getY();
        if ($$5 >= p_65099_.getMinBuildHeight() + 1 && $$5 + p_65101_ + 1 < p_65099_.getMaxBuildHeight()) {
            BlockState state = p_65099_.getBlockState(p_65100_.below());
            if (!state.is(BlockTags.NYLIUM)) {
                return false;
            } else {
                for(int $$7 = 0; $$7 <= p_65101_; ++$$7) {
                    int $$8 = getTreeRadiusForHeight(-1, -1, p_65103_.foliageRadius, $$7);

                    for(int $$9 = -$$8; $$9 <= $$8; ++$$9) {
                        for(int $$10 = -$$8; $$10 <= $$8; ++$$10) {
                            BlockState $$11 = p_65099_.getBlockState(p_65102_.setWithOffset(p_65100_, $$9, $$7, $$10));
                            if (!$$11.isAir() && !$$11.is(BlockTags.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }


    protected int getTreeHeight(RandomSource random) {
        return random.nextInt(2, 4);
    }
}