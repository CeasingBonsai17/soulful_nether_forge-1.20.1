package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GloomCapBlock extends Block {

    public static final BooleanProperty UP;
    public GloomCapBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(UP, false));
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        for(int count = 0; count < 5; ++count) {
            level.addParticle(SoulfulParticleTypes.DROWSY_DUST.get(),
                    pos.getX() + level.random.nextDouble(),
                    pos.getY() + level.random.nextDouble(),
                    pos.getZ() + level.random.nextDouble(),
                    0, 0, 0);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(UP, this.isCap(levelaccessor.getBlockState(blockpos.below())));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return direction == Direction.DOWN ? state.setValue(UP, this.isCap(blockState)) : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, state, pos, entity, f);
        } else {
            entity.causeFallDamage(f, 0.6F, level.damageSources().fall());
        }
    }

    public void updateEntityAfterFallOn(BlockGetter block, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(block, entity);
        } else {
            bounceUp(entity);
        }
    }

    private void bounceUp(Entity entity) {
        Vec3 delta = entity.getDeltaMovement();
        if (delta.y < 0.0) {
            double getBounce = entity instanceof LivingEntity ? 0.4  : 0.2;
            entity.setDeltaMovement(delta.x, -delta.y * getBounce, delta.z);
        }
    }

    private boolean isCap(BlockState state) {
        return state.is(SoulfulBlocks.GLOOM_FUNGUS_CAP.get());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{UP});
    }

    static {
        UP = BlockStateProperties.UP;
    }
}
