package net.bon.soulfulnether.worldgen.feature.type;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

import java.util.Iterator;

public class VolcanicIceCubesFeature extends Feature<BlockStateConfiguration> {
    public VolcanicIceCubesFeature(Codec<BlockStateConfiguration> p_65248_) {
        super(p_65248_);
    }

    public boolean place(FeaturePlaceContext<BlockStateConfiguration> p_159471_) {
        BlockPos $$1 = p_159471_.origin();
        WorldGenLevel $$2 = p_159471_.level();
        RandomSource $$3 = p_159471_.random();

        BlockStateConfiguration $$4;
        for($$4 = (BlockStateConfiguration)p_159471_.config(); $$1.getY() > $$2.getMinBuildHeight() + 3; $$1 = $$1.below()) {
            if (!$$2.isEmptyBlock($$1.below())) {
                BlockState $$5 = $$2.getBlockState($$1.below());
                if ($$5.is(Blocks.SOUL_SAND) || $$5.is(Blocks.SOUL_SOIL) || isStone($$5)) {
                    break;
                }
            }
        }

        if ($$1.getY() <= $$2.getMinBuildHeight() + 3) {
            return false;
        } else {
            for(int $$6 = 0; $$6 < 3; ++$$6) {
                int $$7 = $$3.nextInt(2);
                int $$8 = $$3.nextInt(2);
                int $$9 = $$3.nextInt(2);
                float $$10 = (float)($$7 + $$8 + $$9) * 0.333F + 0.5F;
                Iterator var11 = BlockPos.betweenClosed($$1.offset(-$$7, -$$8, -$$9), $$1.offset($$7, $$8, $$9)).iterator();

                while(var11.hasNext()) {
                    BlockPos $$11 = (BlockPos)var11.next();
                    if ($$11.distSqr($$1) <= (double)($$10 * $$10)) {
                        $$2.setBlock($$11, $$4.state, 3);
                    }
                }

                $$1 = $$1.offset(-1 + $$3.nextInt(2), -$$3.nextInt(2), -1 + $$3.nextInt(2));
            }

            return true;
        }
    }
}