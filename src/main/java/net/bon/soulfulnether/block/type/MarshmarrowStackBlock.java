package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class MarshmarrowStackBlock extends Block {
    private final BlockState nextState;
    private final Float fall;
    public MarshmarrowStackBlock(BlockBehaviour.Properties settings, Float fall, Block block) {
        super(settings);
        this.fall = fall;
        this.nextState = block.defaultBlockState();
    }

    @Override
    public void randomTick(BlockState p_222954_, ServerLevel p_222955_, BlockPos p_222956_, RandomSource p_222957_) {
        int r = p_222957_.nextInt(2);
        if (r == 0 && p_222954_.is(SoulfulBlockTags.SOUL_CONVERTING_BLOCKS)) {
            p_222955_.setBlock(p_222956_, nextState, UPDATE_CLIENTS);
        }
        else super.randomTick(p_222954_, p_222955_, p_222956_, p_222957_);
    }

    @Override
    public boolean isRandomlyTicking(BlockState floor) {
        return floor.is(SoulfulBlockTags.SOUL_CONVERTING_BLOCKS);
    }

    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, fall, world.damageSources().fall());
    }
}