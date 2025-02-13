package net.bon.soulfulnether.effect.type;

import net.bon.soulfulnether.effect.SoulfulEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class FrostbiteEffect extends MobEffect {
    public FrostbiteEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int i) {
        if (this == SoulfulEffects.FROSTBITE.get()) {
            entity.hurt(entity.damageSources().freeze(), 1.0F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        int i;
        if (this == SoulfulEffects.FROSTBITE.get()) {
            i = 30 >> p_19456_;
            if (i > 0) {
                return p_19455_ % i == 0;
            } else {
                return true;
            }
        } else {
            return this == MobEffects.HUNGER;
        }
    }
}