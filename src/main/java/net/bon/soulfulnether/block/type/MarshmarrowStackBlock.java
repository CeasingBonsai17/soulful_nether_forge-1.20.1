package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.bon.soulfulnether.util.SoulfulTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.annotation.Nullable;
import java.util.Iterator;

public class MarshmarrowStackBlock extends Block {
    public MarshmarrowStackBlock(Properties properties) {
        super(properties);
    }

    public static final BooleanProperty TOASTY;

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(new Property[]{TOASTY});
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return state.setValue(TOASTY, isHeated(level, pos));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        return this.defaultBlockState().setValue(TOASTY, isHeated(level, pos));
    }

    private boolean isHeated(LevelAccessor level, BlockPos pos) {
        TagKey<Block> heatSource = SoulfulTags.MARSHMARROW_HEAT_SOURCES;


        for (Direction plane : Direction.Plane.HORIZONTAL) {
            if (level.getBlockState(pos.below()).is(heatSource) ||
                    level.getBlockState(pos.relative(plane)).is(heatSource) ||
                    level.getBlockState(pos.above()).is(heatSource)) {
                return true;
            }
        }
        return false;
    }

    static {
        TOASTY = SoulfulProperties.TOASTY;
    }
}
