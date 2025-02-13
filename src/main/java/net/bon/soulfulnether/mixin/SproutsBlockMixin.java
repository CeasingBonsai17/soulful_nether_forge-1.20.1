package net.bon.soulfulnether.mixin;

import net.bon.soulfulnether.util.SoulfulBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.NetherSproutsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherSproutsBlock.class)
public class SproutsBlockMixin {

    @Inject(
            method = "mayPlaceOn",
            at = @At(value = "TAIL"),
            cancellable = true
    )
    private void soulfulnether$changeMayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (floor.is(SoulfulBlockTags.LICHOSS)) {
            cir.setReturnValue(true);
        }
    }
}
