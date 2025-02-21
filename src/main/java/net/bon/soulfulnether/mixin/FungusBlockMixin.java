package net.bon.soulfulnether.mixin;

import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FungusBlock.class)
public class FungusBlockMixin {

    @Inject(
            method = "mayPlaceOn",
            at = @At(value = "TAIL"),
            cancellable = true
    )
    private void soulfulnether$changeMayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (floor.is(SoulfulBlocks.LICHOSS_BLOCK.get())) {
            cir.setReturnValue(true);
        }
    }
}
