package net.bon.soulfulnether.effect;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.effect.type.FrostbiteEffect;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SoulfulNether.MOD_ID);

    public static final RegistryObject<MobEffect> FROSTBITE = MOB_EFFECT.register("frostbite", ()->
            new FrostbiteEffect(MobEffectCategory.HARMFUL, 7440021).addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.1800002135, AttributeModifier.Operation.MULTIPLY_TOTAL));


    public static void register(IEventBus eventBus) {
        MOB_EFFECT.register(eventBus);
    }
}
