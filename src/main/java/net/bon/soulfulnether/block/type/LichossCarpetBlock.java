package net.bon.soulfulnether.block.type;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class LichossCarpetBlock extends Block {
public static final BooleanProperty HANGING;
protected static final VoxelShape SHAPE;
protected static final VoxelShape HANGING_SHAPE;
    public LichossCarpetBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HANGING, false)) ;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction[] var3 = context.getNearestLookingDirections();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Direction $$2 = var3[var5];
            if ($$2.getAxis() == Direction.Axis.Y) {
                BlockState $$3 = (BlockState)this.defaultBlockState().setValue(HANGING, $$2 == Direction.UP);
                if ($$3.canSurvive(context.getLevel(), context.getClickedPos()));
                return (BlockState)$$3.setValue(HANGING, $$2 == Direction.UP);
            }
        }

        return null;
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return (Boolean)state.getValue(HANGING) ? HANGING_SHAPE : SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{HANGING});
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction $$3 = getConnectedDirection(state).getOpposite();
        return !level.isEmptyBlock(pos.relative($$3));
    }

    protected static Direction getConnectedDirection(BlockState state) {
        return (Boolean)state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return getConnectedDirection(state).getOpposite() == direction && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    static {
        HANGING = BlockStateProperties.HANGING;
        SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
        HANGING_SHAPE = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    }
}
