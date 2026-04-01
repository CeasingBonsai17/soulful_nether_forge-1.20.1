package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.entity.SoulfulEntityTypes;
import net.bon.soulfulnether.entity.type.BoneWyrm;
import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.state.property.SoulfulProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Objects;

public class BoneWyrmNestBlock extends RotatedPillarBlock {
    public static final IntegerProperty GRUBS = SoulfulProperties.GRUB_COUNT;
    public BoneWyrmNestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(player.getItemInHand(hand).is(SoulfulItems.BONE_WYRM_LARVA.get()) && state.getValue(GRUBS) < 3) {
            player.getMainHandItem().shrink(1);
            level.setBlockAndUpdate(pos, state.setValue(GRUBS, state.getValue(GRUBS) + 1));
            return InteractionResult.SUCCESS;
        } else if(player.getItemInHand(hand).isEmpty() && state.getValue(GRUBS) > 0) {
            player.addItem(SoulfulItems.BONE_WYRM_LARVA.get().getDefaultInstance());
            level.setBlockAndUpdate(pos, state.setValue(GRUBS, state.getValue(GRUBS) - 1));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(GRUBS) > 0 && random.nextFloat() > 0.6) {
            level.setBlockAndUpdate(pos, state.setValue(GRUBS, state.getValue(GRUBS) -1));
            BoneWyrm bonewyrm = SoulfulEntityTypes.BONE_WYRM.get().create(level);
            assert bonewyrm != null;
            BlockPos newPos = findAirPos(level, pos);
            bonewyrm.moveTo(newPos.getX() +0.5, newPos.getY(), newPos.getZ() +0.5);
            bonewyrm.setBaby(true);
            bonewyrm.setPersistenceRequired();
            level.addFreshEntity(bonewyrm);
        }
    }

    public BlockPos findAirPos(ServerLevel level, BlockPos pos) {
        BlockPos blockPos = null;
        for(Direction dir : Direction.values()) {
            if(level.getBlockState(pos.relative(dir)).isAir()) {
                blockPos = pos.relative(dir);
            }
        }
        assert blockPos != null;
        return level.getBlockState(blockPos).isAir() ? blockPos : pos.above();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, GRUBS);
    }
}
