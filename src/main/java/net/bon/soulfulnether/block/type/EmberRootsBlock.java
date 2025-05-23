package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")

public class EmberRootsBlock extends Block {
    public static final BooleanProperty HANGING;
    protected static final VoxelShape SHAPE;
    protected static final VoxelShape HANGING_SHAPE;

    public EmberRootsBlock(BlockBehaviour.Properties properties) {
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

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction $$3 = getConnectedDirection(state).getOpposite();
        return mayPlaceOn(level, pos.relative($$3), $$3.getOpposite());
    }

    public static boolean mayPlaceOn(LevelReader level, BlockPos pos, Direction direction) {
        BlockState blockstate = level.getBlockState(pos);
        return direction == Direction.DOWN && blockstate.is(Blocks.BASALT) || blockstate.is(SoulfulBlocks.FRIGHT_WART_BLOCK.get()) ||
                blockstate.is(SoulfulBlockTags.VALID_ROOT_BASES) || blockstate.is(BlockTags.DIRT) || blockstate.is(Blocks.FARMLAND);
    }

    protected static Direction getConnectedDirection(BlockState state) {
        return (Boolean)state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return getConnectedDirection(state).getOpposite() == direction && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{HANGING});
    }
    static {
        HANGING = BlockStateProperties.HANGING;
        SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
        HANGING_SHAPE = Block.box(2.0, 3.0, 2.0, 14.0, 16.0, 14.0);
    }
}