package net.bon.soulfulnether.worldgen.feature.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;

import javax.annotation.Nullable;
import java.util.Iterator;

public class AshenBasaltColumnsFeature extends Feature<AshenColumnFeatureConfiguration> {
    private static final ImmutableList<Block> CANNOT_PLACE_ON;
    private static final int CLUSTERED_REACH = 5;
    private static final int CLUSTERED_SIZE = 50;
    private static final int UNCLUSTERED_REACH = 8;
    private static final int UNCLUSTERED_SIZE = 15;

    public AshenBasaltColumnsFeature(Codec<AshenColumnFeatureConfiguration> p_65153_) {
        super(p_65153_);
    }

    public boolean place(FeaturePlaceContext<AshenColumnFeatureConfiguration> p_159444_) {
        int $$1 = p_159444_.chunkGenerator().getSeaLevel();
        BlockPos $$2 = p_159444_.origin();
        WorldGenLevel $$3 = p_159444_.level();
        RandomSource $$4 = p_159444_.random();
        AshenColumnFeatureConfiguration $$5 = (AshenColumnFeatureConfiguration)p_159444_.config();
        if (!canPlaceAt($$3, $$1, $$2.mutable())) {
            return false;
        } else {
            int $$6 = $$5.height().sample($$4);
            boolean $$7 = $$4.nextFloat() < 0.9F;
            int $$8 = Math.min($$6, $$7 ? 5 : 8);
            int $$9 = $$7 ? 50 : 15;
            boolean $$10 = false;
            Iterator var12 = BlockPos.randomBetweenClosed($$4, $$9, $$2.getX() - $$8, $$2.getY(), $$2.getZ() - $$8, $$2.getX() + $$8, $$2.getY(), $$2.getZ() + $$8).iterator();

            while(var12.hasNext()) {
                BlockPos $$11 = (BlockPos)var12.next();
                int $$12 = $$6 - $$11.distManhattan($$2);
                if ($$12 >= 0) {
                    $$10 |= this.placeColumn($$3, $$1, $$11, $$12, $$5.reach().sample($$4));
                }
            }

            return $$10;
        }
    }

    private boolean placeColumn(LevelAccessor p_65168_, int p_65169_, BlockPos p_65170_, int p_65171_, int p_65172_) {
        boolean $$5 = false;
        Iterator var7 = BlockPos.betweenClosed(p_65170_.getX() - p_65172_, p_65170_.getY(), p_65170_.getZ() - p_65172_, p_65170_.getX() + p_65172_, p_65170_.getY(), p_65170_.getZ() + p_65172_).iterator();

        while(true) {
            int $$7;
            BlockPos $$8;
            do {
                if (!var7.hasNext()) {
                    return $$5;
                }

                BlockPos $$6 = (BlockPos)var7.next();
                $$7 = $$6.distManhattan(p_65170_);
                $$8 = isAirOrLavaOcean(p_65168_, p_65169_, $$6) ? findSurface(p_65168_, p_65169_, $$6.mutable(), $$7) : findAir(p_65168_, $$6.mutable(), $$7);
            } while($$8 == null);

            int $$9 = p_65171_ - $$7 / 2;

            for(BlockPos.MutableBlockPos $$10 = $$8.mutable(); $$9 >= 0; --$$9) {
                if (isAirOrLavaOcean(p_65168_, p_65169_, $$10)) {
                    this.setBlock(p_65168_, $$10, SoulfulBlocks.ASHEN_BASALT.get().defaultBlockState());
                    $$10.move(Direction.UP);
                    $$5 = true;
                } else {
                    if (!p_65168_.getBlockState($$10).is(SoulfulBlocks.ASHEN_BASALT.get())) {
                        break;
                    }

                    $$10.move(Direction.UP);
                }
            }
        }
    }

    @Nullable
    private static BlockPos findSurface(LevelAccessor p_65159_, int p_65160_, BlockPos.MutableBlockPos p_65161_, int p_65162_) {
        while(p_65161_.getY() > p_65159_.getMinBuildHeight() + 1 && p_65162_ > 0) {
            --p_65162_;
            if (canPlaceAt(p_65159_, p_65160_, p_65161_)) {
                return p_65161_;
            }

            p_65161_.move(Direction.DOWN);
        }

        return null;
    }

    private static boolean canPlaceAt(LevelAccessor p_65155_, int p_65156_, BlockPos.MutableBlockPos p_65157_) {
        if (!isAirOrLavaOcean(p_65155_, p_65156_, p_65157_)) {
            return false;
        } else {
            BlockState $$3 = p_65155_.getBlockState(p_65157_.move(Direction.DOWN));
            p_65157_.move(Direction.UP);
            return !$$3.isAir() && !CANNOT_PLACE_ON.contains($$3.getBlock());
        }
    }

    @Nullable
    private static BlockPos findAir(LevelAccessor p_65174_, BlockPos.MutableBlockPos p_65175_, int p_65176_) {
        while(p_65175_.getY() < p_65174_.getMaxBuildHeight() && p_65176_ > 0) {
            --p_65176_;
            BlockState $$3 = p_65174_.getBlockState(p_65175_);
            if (CANNOT_PLACE_ON.contains($$3.getBlock())) {
                return null;
            }

            if ($$3.isAir() || $$3.is(SoulfulBlocks.ASHEN_SNOW_LAYER.get())) {
                return p_65175_;
            }

            p_65175_.move(Direction.UP);
        }

        return null;
    }

    private static boolean isAirOrLavaOcean(LevelAccessor p_65164_, int p_65165_, BlockPos p_65166_) {
        BlockState $$3 = p_65164_.getBlockState(p_65166_);
        return $$3.isAir() || $$3.is(Blocks.LAVA) || $$3.is(SoulfulBlocks.ASHEN_SNOW_LAYER.get()) && p_65166_.getY() <= p_65165_;
    }

    static {
        CANNOT_PLACE_ON = ImmutableList.of(Blocks.LAVA, Blocks.BEDROCK, Blocks.MAGMA_BLOCK, Blocks.SOUL_SAND, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_WART, Blocks.CHEST, Blocks.SPAWNER, Blocks.SOUL_FIRE, SoulfulBlocks.ASHEN_SNOW_LAYER.get());
    }
}
