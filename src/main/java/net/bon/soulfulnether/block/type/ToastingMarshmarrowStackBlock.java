package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;

public class ToastingMarshmarrowStackBlock extends MarshmarrowStackBlock {
    private final BlockState nextState;
    private final Float fall;
    public ToastingMarshmarrowStackBlock(BlockBehaviour.Properties properties, Float fall, Block block) {
        super(properties);
        this.fall = fall;
        this.nextState = block.defaultBlockState();
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(TOASTY, false));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(TOASTY);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (state.getValue(TOASTY)) {
            level.setBlockAndUpdate(pos, nextState.setValue(ToastingMarshmarrowBlock.TOASTY, state.getValue(ToastingMarshmarrowBlock.TOASTY)));
        }
    }

    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float f) {
        entity.causeFallDamage(f, fall, world.damageSources().fall());
    }
}