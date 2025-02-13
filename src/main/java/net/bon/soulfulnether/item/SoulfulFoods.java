package net.bon.soulfulnether.item;

import net.bon.soulfulnether.effect.SoulfulEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class SoulfulFoods {

    public static final FoodProperties FROSTBITTEN_SOULROOT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).effect(new MobEffectInstance(SoulfulEffects.FROSTBITE.get(), 300, 0), 1.0F).build();
    public static final FoodProperties FROSTBITTEN_POPSICLE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 0.8F).build();
    public static final FoodProperties SOULROOT = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties CHARRED_SOULROOT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F).build();
    public static final FoodProperties SOULROOT_FOODS = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.4F).build();
    public static final FoodProperties MARSHMARROW = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties TOASTY_MARSHMARROW = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F).build();
    public static final FoodProperties ROASTED_MARSHMARROW = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
    public static final FoodProperties MARSHMARROW_FOODS = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
    }