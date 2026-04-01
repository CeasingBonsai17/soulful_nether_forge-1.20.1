package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.util.SoulfulTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")

public class SoulrootsBlock extends BushBlock implements BonemealableBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 11.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0)};

    public SoulrootsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(AGE, 0));
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return state.is(SoulfulTags.SOUL_CONVERTING_BLOCKS);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return mayPlaceOn(level.getBlockState(blockpos), level, blockpos);
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }


    public int getAge(BlockState state) {
        return state.getValue(getAgeProperty());
    }

    public int getMaxAge() {
        return 5;
    }


    protected ItemLike getBaseSeedId() {
        return SoulfulItems.SOULROOT_SEEDS.get();
    }

    public ItemStack getCloneItemStack(BlockGetter block, BlockPos pos, BlockState state) {
        return new ItemStack(getBaseSeedId());
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[getAge(state)];
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 5;
    }

    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age < 5 && random.nextInt(5) == 0) {
            BlockState blockState = state.setValue(AGE, age + 1);
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
        return state.getValue(AGE) < 5;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource randomSource, BlockPos pos, BlockState state) {
        int i = Math.min(5, state.getValue(AGE) + 2);
        world.setBlockAndUpdate(pos, state.setValue(AGE, i));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{AGE});
    }
}
