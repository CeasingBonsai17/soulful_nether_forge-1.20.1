package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GloomyAmberVineBlock extends Block implements BonemealableBlock {
    public GloomyAmberVineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean b) {
       return level.getBlockState(pos.below().below()).isAir() && level.getBlockState(pos.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) ->
                registry.getHolder(SoulfulConfiguredFeatures.GLOOMY_AMBER_VINES_BONEMEAL)).ifPresent((reference) -> {
                    reference.value().place(level, level.getChunkSource().getGenerator(), random, pos.below());});
    }
}
