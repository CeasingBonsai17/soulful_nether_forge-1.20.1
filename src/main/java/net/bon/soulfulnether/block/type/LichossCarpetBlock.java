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
    public LichossCarpetBlock(Properties p_152915_) {
        super(p_152915_);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HANGING, false)) ;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_153467_) {
        Direction[] var3 = p_153467_.getNearestLookingDirections();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Direction $$2 = var3[var5];
            if ($$2.getAxis() == Direction.Axis.Y) {
                BlockState $$3 = (BlockState)this.defaultBlockState().setValue(HANGING, $$2 == Direction.UP);
                if ($$3.canSurvive(p_153467_.getLevel(), p_153467_.getClickedPos()));
                return (BlockState)$$3.setValue(HANGING, $$2 == Direction.UP);
            }
        }

        return null;
    }

    public VoxelShape getShape(BlockState p_153474_, BlockGetter p_153475_, BlockPos p_153476_, CollisionContext p_153477_) {
        return (Boolean)p_153474_.getValue(HANGING) ? HANGING_SHAPE : SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153490_) {
        p_153490_.add(new Property[]{HANGING});
    }

    public boolean canSurvive(BlockState p_153479_, LevelReader p_153480_, BlockPos p_153481_) {
        Direction $$3 = getConnectedDirection(p_153479_).getOpposite();
        return !p_153480_.isEmptyBlock(p_153481_.relative($$3));
    }

    protected static Direction getConnectedDirection(BlockState p_153496_) {
        return (Boolean)p_153496_.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public BlockState updateShape(BlockState p_153483_, Direction p_153484_, BlockState p_153485_, LevelAccessor p_153486_, BlockPos p_153487_, BlockPos p_153488_) {
        return getConnectedDirection(p_153483_).getOpposite() == p_153484_ && !p_153483_.canSurvive(p_153486_, p_153487_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_153483_, p_153484_, p_153485_, p_153486_, p_153487_, p_153488_);
    }

    static {
        HANGING = BlockStateProperties.HANGING;
        SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
        HANGING_SHAPE = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    }
}
