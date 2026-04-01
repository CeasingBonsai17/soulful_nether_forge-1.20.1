package net.bon.soulfulnether.block.grower;

import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class HugeGloomFungusGrower extends AbstractTreeGrower {
    public HugeGloomFungusGrower() {
    }

    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource source, boolean bl) {
        if (source.nextInt(7) == 0) {
            return SoulfulConfiguredFeatures.LARGE_GLOOM_FUNGUS;
        } else {
            return SoulfulConfiguredFeatures.HUGE_GLOOM_FUNGUS;
        }
    }
}