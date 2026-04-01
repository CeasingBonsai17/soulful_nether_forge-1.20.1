package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GloomyAmberVinesPlantBlock extends Block {
    public GloomyAmberVinesPlantBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ctx) {
        return Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState above = level.getBlockState(pos.above());
        BlockState below = level.getBlockState(pos.below());
        return below.is(SoulfulBlocks.GLOOMY_AMBER_VINES.get()) || below.is(this) ||
                above.isFaceSturdy(level, pos.above(), Direction.DOWN) || above.is(this);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        if(level.getBlockState(pos.below()).isAir()) {
            return SoulfulBlocks.GLOOMY_AMBER_VINES.get().defaultBlockState();
        } if(level.getBlockState(pos.above()).isAir()) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, direction, blockState, level, pos, blockPos);
        }
    }

    public ItemStack getCloneItemStack(BlockGetter block, BlockPos pos, BlockState state) {
        return new ItemStack(SoulfulBlocks.GLOOMY_AMBER_VINES.get());
    }
}