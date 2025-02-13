package net.bon.soulfulnether.mixin;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    @Inject(
            method = "isValid",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void soulfulnether$isValid(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (BlockEntityType.SIGN.equals(this) && (state.getBlock() instanceof StandingSignBlock || state.getBlock() instanceof WallSignBlock)) {
            cir.setReturnValue(true);
        }
        if (BlockEntityType.HANGING_SIGN.equals(this) && (state.getBlock() instanceof CeilingHangingSignBlock || state.getBlock() instanceof WallHangingSignBlock)) {
            cir.setReturnValue(true);
        }
    }
}
