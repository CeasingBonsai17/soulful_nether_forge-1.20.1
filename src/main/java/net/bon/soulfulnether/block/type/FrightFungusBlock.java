package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;

public class FrightFungusBlock extends FungusBlock implements BonemealableBlock {

    public FrightFungusBlock(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> feature, Block requiredBlock) {
        super(properties, feature, requiredBlock);
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || state.is(SoulfulBlocks.LICHOSS_BLOCK.get()) || state.is(Blocks.MYCELIUM) || state.is(Blocks.SOUL_SOIL) || super.mayPlaceOn(state, blockGetter, pos);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState blockState, boolean bl) {
        BlockState blockstate = level.getBlockState(pos.below());
        return blockstate.is(Blocks.SOUL_SOIL) || blockstate.is(SoulfulBlocks.LICHOSS_BLOCK.get());
    }

    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        level.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) -> {
            return registry.getHolder(SoulfulConfiguredFeatures.FRIGHT_FUNGUS_PLANTED);

        }).ifPresent((reference) -> {
            ((ConfiguredFeature)reference.value()).place(level, level.getChunkSource().getGenerator(), source, pos.above());
        });
    }
}