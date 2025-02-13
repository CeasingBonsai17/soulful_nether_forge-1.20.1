package net.bon.soulfulnether.block.sapling;

import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class HugeFrightFungusGrower extends AbstractTreeGrower {

    public HugeFrightFungusGrower() {
    }

    @Nullable
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return SoulfulConfiguredFeatures.FRIGHT_FUNGUS_PLANTED;
    }
}