package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class LichossBlock extends Block implements BonemealableBlock {
    public LichossBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean bl) {
        return level.getBlockState(pos.above()).isAir() || level.getBlockState(pos.below()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState blockState) {
        level.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) -> {
            return registry.getHolder(SoulfulConfiguredFeatures.LICHOSS_PATCH_BONE_MEAL);

        }).ifPresent((reference) -> {
            ((ConfiguredFeature)reference.value()).place(level, level.getChunkSource().getGenerator(), source, pos.above());
        });
        level.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) -> {
            return registry.getHolder(SoulfulConfiguredFeatures.LICHOSS_PATCH_CEILING);

        }).ifPresent((reference) -> {
            ((ConfiguredFeature)reference.value()).place(level, level.getChunkSource().getGenerator(), source, pos.above());
        });
        level.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) -> {
            return registry.getHolder(SoulfulConfiguredFeatures.LICHOSS_PATCH_CEILING_BONE_MEAL);

        }).ifPresent((reference) -> {
            ((ConfiguredFeature)reference.value()).place(level, level.getChunkSource().getGenerator(), source, pos.above());
        });
    }
}
