package net.bon.soulfulnether.worldgen.feature;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SoulfulConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGHT_FUNGUS_PLANTED = registerKey("fright_forest/fright_fungus_planted");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH_BONE_MEAL = registerKey("fright_forest/lichoss_patch_bone_meal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH_CEILING = registerKey("fright_forest/lichoss_patch_ceiling");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH_CEILING_BONE_MEAL = registerKey("fright_forest/lichoss_patch_ceiling_bone_meal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASHEN_SNOW_LAYERS = registerKey("ashen_deltas/ashen_snow_layers");




    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}