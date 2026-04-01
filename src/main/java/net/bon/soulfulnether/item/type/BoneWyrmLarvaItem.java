package net.bon.soulfulnether.item.type;

import net.bon.soulfulnether.sound.SoulfulSoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoneWyrmLarvaItem extends Item {
    public BoneWyrmLarvaItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onDestroyed(ItemEntity entity, DamageSource source) {
        entity.level().playSound(null, entity.blockPosition(), SoulfulSoundEvents.BONE_WYRM_LARVA_DEATH.get(), SoundSource.NEUTRAL, 1f, 1f);

        super.onDestroyed(entity, source);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        level.playSound(null, entity.blockPosition(), SoulfulSoundEvents.BONE_WYRM_LARVA_DEATH.get(), SoundSource.NEUTRAL, 1f, 1f);

        return super.finishUsingItem(stack, level, entity);
    }
}
