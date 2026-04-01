package net.bon.soulfulnether.mixin;

import net.bon.soulfulnether.util.SoulfulTags;
import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NyliumBlock.class)
public class NyliumBlockMixin {

    @Inject(
            method = "performBonemeal",
            at = @At(value = "TAIL")
    )
    private void soulfulnether$changePerformBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (level.getBiome(pos).is(SoulfulTags.GLOOMY_WARPED_NYLIUM_BONEMEAL) && level.getBlockState(pos).is(Blocks.WARPED_NYLIUM)) {
            level.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) ->
                    registry.getHolder(SoulfulConfiguredFeatures.GLOOMY_FIDDLEHEAD_PATCH)).ifPresent((reference) -> {
                        reference.value().place(level, level.getChunkSource().getGenerator(), source, pos.above());});
        }
    }
}