package net.bon.soulfulnether.mixin;

import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.sound.SoulfulSoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Shadow public abstract ItemStack getItem();

    @Shadow public abstract void setItem(ItemStack stack);

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void patchlands$isValid(CallbackInfo ci) {
        if (isInPowderSnow && getItem().is(SoulfulItems.SOULROOT.get()) && !level().isClientSide) {
                setItem(SoulfulItems.FROZEN_SOULROOT.get().getDefaultInstance());
        }
    }
}
