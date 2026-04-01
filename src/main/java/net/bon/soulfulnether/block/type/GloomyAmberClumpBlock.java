package net.bon.soulfulnether.block.type;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.item.SoulfulItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockState;

public class GloomyAmberClumpBlock extends MultifaceBlock {
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);
    public GloomyAmberClumpBlock(Properties properties) {
        super(properties);
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext ctx) {
        return ctx.getItemInHand().is(this.asItem()) || super.canBeReplaced(state, ctx);
    }

    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }
}
