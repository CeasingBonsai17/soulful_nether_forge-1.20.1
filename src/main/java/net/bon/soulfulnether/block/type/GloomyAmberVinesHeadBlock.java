package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.sound.SoulfulSoundEvents;
import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class GloomyAmberVinesHeadBlock extends Block {
    public static final BooleanProperty GROWING = SoulfulProperties.GROWING;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;


    private static final VoxelShape[] SHAPE_BY_AGE  = new VoxelShape[]{
            Block.box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0),
            Block.box(4.0, 4.0, 4.0, 12.0, 16.0, 12.0),
            Block.box(4.0, 2.0, 4.0, 12.0, 16.0, 12.0)};

    public GloomyAmberVinesHeadBlock(BlockBehaviour.Properties properties)  {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext ctx) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean airBelow = level.getBlockState(pos.below()).isAir();
        if(state.getValue(GROWING) && airBelow) {
            growNewHead(SoulfulBlocks.GLOOMY_AMBER_VINES.get().defaultBlockState()
                    .setValue(GROWING, random.nextDouble() > 0.3), level, pos);

        } if (!state.getValue(GROWING) && random.nextInt(2) == 0) {
            BlockState setAge = state.setValue(AGE, state.getValue(AGE) + 1);
            level.setBlockAndUpdate(pos, setAge);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(setAge));
        }
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && state.getValue(AGE) >= 1) {
            entity.makeStuckInBlock(state, new Vec3(0.8, 0.75, 0.8));
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 2;
    }

    public void growNewHead(BlockState state, Level level, BlockPos pos) {
        level.setBlock(pos.below(), state, 11);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState above = level.getBlockState(pos.above());
        return above.is(SoulfulBlocks.GLOOMY_AMBER_VINES_PLANT.get()) || above.isFaceSturdy(level, pos.above(), Direction.DOWN) || above.is(this);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos pos, BlockPos blockPos) {
        if(level.getBlockState(pos.below()).is(this)) {
            return SoulfulBlocks.GLOOMY_AMBER_VINES_PLANT.get().defaultBlockState();
        } if(level.getBlockState(pos.above()).isAir()) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return state;
        }
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        SoundEvent squish = SoulfulSoundEvents.GLOOMY_AMBER_SQUISH.get();

        if(player.getAbilities().mayBuild && player.getItemInHand(hand).is(Items.SHEARS) && state.getValue(GROWING)) {
            level.setBlock(pos, state.setValue(GROWING, false), 11);
            level.playSound(null, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0F, 1.0F);
            if(!player.isCreative()) {
                itemStack.hurtAndBreak(1, player, p ->
                        p.broadcastBreakEvent(hand));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } if (player.getAbilities().mayBuild && state.getValue(AGE)==2) {
            level.setBlock(pos, state.setValue(AGE, 0), 11);
            popResource(level, pos, new ItemStack(SoulfulBlocks.GLOOMY_AMBER_CLUMP.get()));
            level.playSound(null, pos, squish, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        else {
            return InteractionResult.PASS;
        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double r = random.nextDouble();
        if (state.getValue(AGE) == 2) {
            level.addParticle(SoulfulParticleTypes.FALLING_GLOOMY_RAIN.get(), pos.getX() + r, pos.getY() + 0.3, pos.getZ() + r, 0, 0, 0);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(new Property[]{GROWING, AGE});
    }
}
