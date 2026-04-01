package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.util.SoulfulTags;
import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;

public class PackedVolcanicIceBlock extends HalfTransparentBlock {
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    public PackedVolcanicIceBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(SNOWY, false));
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        int x = pos.getX();
        int y = pos.getY();
        int  z = pos.getZ();
        
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 14; ++i) {
            blockPos.set(x + Mth.nextInt(random, -4, 4), y - random.nextInt(4),  z + Mth.nextInt(random, -4, 4));
            if (state.getValue(SNOWY)) {
                level.addParticle(SoulfulParticleTypes.ASHEN_SNOW.get(), blockPos.getX() + random.nextDouble(), blockPos.getY() + random.nextDouble(), blockPos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
            }
        }

    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(SNOWY);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (state.getValue(SNOWY)) {
            level.registryAccess().registry(Registries.CONFIGURED_FEATURE).flatMap((registry) ->
                    registry.getHolder(SoulfulConfiguredFeatures.ASHEN_SNOW_LAYERS)).ifPresent((reference) -> {
                        reference.value().place(level, level.getChunkSource().getGenerator(), source, pos);});
        }
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (state.getValue(SNOWY) && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.hurt(level.damageSources().freeze(), 0.5F);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(SNOWY, this.isColdSource(context.getLevel().getBlockState(context.getClickedPos().below())));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return direction == Direction.DOWN ? state.setValue(SNOWY, this.isColdSource(blockState)) : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    private boolean isColdSource(BlockState state) {
        return state.is(SoulfulTags.VOLCANIC_COLD_SOURCES);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{SNOWY});
    }
}