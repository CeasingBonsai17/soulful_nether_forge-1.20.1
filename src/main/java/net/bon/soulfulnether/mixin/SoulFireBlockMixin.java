package net.bon.soulfulnether.mixin;

import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoulFireBlock.class)
public class SoulFireBlockMixin {

    @Inject(
            method = "canSurvive",
            at = @At(value = "TAIL"),
            cancellable = true
    )
    private void soulfulnether$changeCanSurvive(BlockState floor, LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);
        if (!belowState.isFaceSturdy(world, belowPos, Direction.UP)) {
            cir.setReturnValue(false);
        }
    }
}