package net.bon.soulfulnether.sound;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class SoulfulSoundType {
    public static final SoundType GLOOM_CAP = new SoundType(1.0F, 1.0F,
            SoulfulSoundEvents.GLOOM_CAP_BREAK.get(), SoundEvents.WART_BLOCK_STEP,
            SoundEvents.SHROOMLIGHT_PLACE, SoundEvents.WART_BLOCK_HIT, SoundEvents.SHROOMLIGHT_FALL);
}
