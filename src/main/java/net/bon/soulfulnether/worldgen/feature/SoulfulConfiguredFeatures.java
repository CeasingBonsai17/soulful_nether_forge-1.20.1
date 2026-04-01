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
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH_CEILING_BONEMEAL = registerKey("fright_forest/lichoss_patch_ceiling_bone_meal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASHEN_SNOW_LAYERS = registerKey("ashen_deltas/ashen_snow_layers");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_GLOOM_FUNGUS = registerKey("gloomy_glades/base_features/huge_gloom_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_GLOOM_FUNGUS = registerKey("gloomy_glades/base_features/large_gloom_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOOMY_FIDDLEHEAD_PATCH = registerKey("gloomy_glades/gloomy_fiddlehead_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOOMY_AMBER_VINES_BONEMEAL = registerKey("gloomy_glades/gloomy_amber_vines_bonemeal");




    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}