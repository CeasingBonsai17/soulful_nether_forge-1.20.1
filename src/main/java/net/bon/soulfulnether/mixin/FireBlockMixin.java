package net.bon.soulfulnether.mixin;

import net.bon.soulfulnether.util.SoulfulTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    @Inject(
            method = "canSurvive",
            at = @At(value = "TAIL"),
            cancellable = true
    )
    private void soulfulnether$changeCanSurvive(BlockState floor, LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);
        if (belowState.is(SoulfulTags.INVALID_FIRE_BASE)) {
            cir.setReturnValue(false);
        }
    }
}
