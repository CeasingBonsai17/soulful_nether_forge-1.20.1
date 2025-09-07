package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.model.geometry.BlockGeometryBakingContext;

import java.util.Random;

@SuppressWarnings("deprecation")

public class SoulrootsBlock extends BushBlock implements BonemealableBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE;
    private static final VoxelShape[] AGE_TO_SHAPE;

    public SoulrootsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return state.is(SoulfulBlockTags.SOUL_CONVERTING_BLOCKS) && !state.is(BlockTags.DIRT) && !state.is(Blocks.FARMLAND);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return this.mayPlaceOn(level.getBlockState(blockpos), level, blockpos);
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }


    public int getAge(BlockState state) {
        return (Integer)state.getValue(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 5;
    }


    protected ItemLike getBaseSeedId() {
        return SoulfulItems.SOULROOT_SEEDS.get();
    }

    public ItemStack getCloneItemStack(BlockGetter block, BlockPos pos, BlockState state) {
        return new ItemStack(this.getBaseSeedId());
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        if (i < 5 && random.nextInt(5) == 0 && serverLevel.getRawBrightness(pos.above(), 0) >= 0) {
            BlockState blockState = state.setValue(AGE, i + 1);
            serverLevel.setBlockAndUpdate(pos, blockState);
            serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
        }
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int i = state.getValue(AGE);
        boolean bl = i == 5;
        if (!bl && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
    }

    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
        return (Integer)state.getValue(AGE) < 5;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource randomSource, BlockPos pos, BlockState state) {
        int i = Math.min(5, (Integer)state.getValue(AGE) + 2);
        world.setBlockAndUpdate(pos, (BlockState)state.setValue(AGE, i));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{AGE});
    }

    static {
        AGE = BlockStateProperties.AGE_5;
        AGE_TO_SHAPE = new VoxelShape[]{Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0)};
    }
}
