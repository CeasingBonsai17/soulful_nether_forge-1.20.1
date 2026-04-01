package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public class DrowsyVinesBlock extends BushBlock implements BonemealableBlock {
    public static final BooleanProperty GROWING;
    public static final BooleanProperty HEAD;

    protected static final VoxelShape SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    protected static final VoxelShape HEAD_SHAPE = Block.box(2.0, 2.0, 2.0, 14.0, 16.0, 14.0);

    public DrowsyVinesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HEAD, true));
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(state.getValue(HEAD) && random.nextDouble() < 0.7) {
            level.addParticle(SoulfulParticleTypes.DROWSY_DUST.get(),
                    pos.getX() + random.nextDouble(), pos.getY() + 0.5, pos.getZ() + random.nextDouble(),
                    0.0, 0.0, 0.0);
        }
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState blockState = SoulfulBlocks.DROWSY_VINES.get().defaultBlockState();
        SoundEvent growSound = SoundEvents.FUNGUS_FALL;
        if (level.getBlockState(pos.below()).isAir()) {
            if (state.getValue(HEAD) && random.nextDouble() < 0.4 && state.getValue(GROWING)) {
                 growNewHead(blockState, level, pos, growSound);
            }
            if (state.getValue(HEAD) && random.nextDouble() < 0.1 && state.getValue(GROWING)) {
                growNewHead(blockState.setValue(GROWING, false), level, pos, growSound);
            }
        }
    }

    public void growNewHead(BlockState state, Level level, BlockPos pos, SoundEvent sound) {
        level.setBlock(pos.below(), state, 11);
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (this.defaultBlockState().setValue(HEAD, true).setValue(GROWING, true));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        return direction == Direction.DOWN ? state.setValue(HEAD, !blockState.is(SoulfulBlocks.DROWSY_VINES.get())) : super.updateShape(state, direction, blockState, level, pos, blockPos);
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return state.getValue(HEAD) ? HEAD_SHAPE : SHAPE;
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos $$3 = pos.relative(Direction.DOWN.getOpposite());
        BlockState $$4 = level.getBlockState($$3);

        return $$4.is(this) || $$4.isFaceSturdy(level, $$3, Direction.DOWN);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.getAbilities().mayBuild && player.getItemInHand(hand).is(Items.SHEARS) && state.getValue(GROWING)) {
            setGrowingFalse(player, state, level, pos);
            if(!player.isCreative()) {
                itemStack.hurtAndBreak(1, player, p ->
                        p.broadcastBreakEvent(hand));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }else {
            return InteractionResult.PASS;
        }
    }

    public static void setGrowingFalse(@Nullable Player player, BlockState state, LevelAccessor level, BlockPos pos) {
        level.setBlock(pos, state.setValue(GROWING, false), 11);
        level.playSound(null, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean b) {
        return state.getValue(HEAD) && level.getBlockState(pos.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        growNewHead(SoulfulBlocks.DROWSY_VINES.get().defaultBlockState(), level, pos, SoundEvents.FUNGUS_FALL);
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{GROWING, HEAD});
    }

    static {
        GROWING = SoulfulProperties.GROWING;
        HEAD = SoulfulProperties.HEAD;
    }
}
