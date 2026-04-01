package net.bon.soulfulnether.block.type;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ToastingMarshmarrowBlock extends MarshmarrowBlock implements SimpleWaterloggedBlock {
    private final Float fall;
    private final BlockState nextState;

    public ToastingMarshmarrowBlock(BlockBehaviour.Properties properties, Float fall, Block block) {
        super(properties);
        this.fall = fall;
        this.nextState = block.defaultBlockState();
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, fall, level.damageSources().fall());
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(TOASTY);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (state.getValue(TOASTY)) {
            level.setBlockAndUpdate(pos, nextState.setValue(MarshmarrowBlock.MARSHMARROWS, state.getValue(MarshmarrowBlock.MARSHMARROWS)).setValue(MarshmarrowBlock.WATERLOGGED, state.getValue(MarshmarrowBlock.WATERLOGGED)).setValue(ToastingMarshmarrowBlock.TOASTY, state.getValue(ToastingMarshmarrowBlock.TOASTY)));
        }
    }
}