package net.bon.soulfulnether.block.type;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class StrippablePillarBlock extends RotatedPillarBlock {

    private final Supplier<Block> stripped;

    public StrippablePillarBlock(Properties properties, Supplier<Block> stripped) {
        super(properties);
        this.stripped = stripped;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean bl) {
        ItemStack stack = context.getItemInHand();
        if (stack.canPerformAction(toolAction)) {
            if (toolAction == ToolActions.AXE_STRIP) {
                return stripped.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, bl);
    }
}
