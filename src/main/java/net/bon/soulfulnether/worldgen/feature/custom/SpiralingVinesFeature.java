package net.bon.soulfulnether.worldgen.feature.custom;

import com.mojang.serialization.Codec;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class SpiralingVinesFeature extends Feature<SpiralingVinesConfiguration> {
    public SpiralingVinesFeature(Codec<SpiralingVinesConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<SpiralingVinesConfiguration> featurePlaceContext) {
        WorldGenLevel worldGenLevel = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        if (isInvalidPlacementLocation(worldGenLevel, blockPos)) {
            return false;
        } else {
            RandomSource randomSource = featurePlaceContext.random();
            SpiralingVinesConfiguration spiralingVinesConfig = (SpiralingVinesConfiguration)featurePlaceContext.config();
            int i = spiralingVinesConfig.spreadWidth();
            int j = spiralingVinesConfig.spreadHeight();
            int k = spiralingVinesConfig.maxHeight();
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

            for(int l = 0; l < i * i; ++l) {
                mutableBlockPos.set(blockPos).move(Mth.nextInt(randomSource, -i, i), Mth.nextInt(randomSource, -j, j), Mth.nextInt(randomSource, -i, i));
                if (findFirstAirBlockAboveGround(worldGenLevel, mutableBlockPos) && !isInvalidPlacementLocation(worldGenLevel, mutableBlockPos)) {
                    int m = Mth.nextInt(randomSource, 1, k);
                    if (randomSource.nextInt(6) == 0) {
                        m *= 2;
                    }

                    if (randomSource.nextInt(5) == 0) {
                        m = 1;
                    }

                    boolean n = true;
                    boolean o = true;
                    placeWeepingVinesColumn(worldGenLevel, randomSource, mutableBlockPos, m, 17, 25);
                }
            }

            return true;
        }
    }

    private static boolean findFirstAirBlockAboveGround(LevelAccessor levelAccessor, BlockPos.MutableBlockPos mutableBlockPos) {
        do {
            mutableBlockPos.move(0, -1, 0);
            if (levelAccessor.isOutsideBuildHeight(mutableBlockPos)) {
                return false;
            }
        } while(levelAccessor.getBlockState(mutableBlockPos).isAir());

        mutableBlockPos.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(LevelAccessor levelAccessor, RandomSource randomSource, BlockPos.MutableBlockPos mutableBlockPos, int i, int j, int k) {
        for(int l = 1; l <= i; ++l) {
            if (levelAccessor.isEmptyBlock(mutableBlockPos)) {
                if (l == i || !levelAccessor.isEmptyBlock(mutableBlockPos.above())) {
                    levelAccessor.setBlock(mutableBlockPos, (BlockState) SoulfulBlocks.SPIRALING_VINES.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(randomSource, j, k)), 2);
                    break;
                }

                levelAccessor.setBlock(mutableBlockPos, SoulfulBlocks.SPIRALING_VINES_PLANT.get().defaultBlockState(), 2);
            }

            mutableBlockPos.move(Direction.UP);
        }

    }

    private static boolean isInvalidPlacementLocation(LevelAccessor levelAccessor, BlockPos blockPos) {
        if (!levelAccessor.isEmptyBlock(blockPos)) {
            return true;
        } else {
            BlockState blockState = levelAccessor.getBlockState(blockPos.below());
            return !blockState.is(Blocks.NETHERRACK) && !blockState.is(Blocks.WARPED_NYLIUM) && !blockState.is(Blocks.WARPED_WART_BLOCK);
        }
    }
}
