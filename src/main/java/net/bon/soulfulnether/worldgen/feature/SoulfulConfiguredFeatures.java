package net.bon.soulfulnether.worldgen.feature;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SoulfulConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGHT_FUNGUS_PLANTED = registerKey("fright_forest/fright_fungus_planted");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGHT_FUNGUS = registerKey("fright_forest/fright_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH_BONE_MEAL = registerKey("fright_forest/lichoss_patch_bone_meal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH = registerKey("fright_forest/lichoss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_LICHOSS = registerKey("fright_forest/hanging_lichoss");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH_CEILING = registerKey("fright_forest/lichoss_patch_ceiling");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHOSS_PATCH_CEILING_BONE_MEAL = registerKey("fright_forest/lichoss_patch_ceiling_bone_meal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIRALING_VINES = registerKey("fright_forest/spiraling_vines");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGHT_FUNGUS_VEGETATION = registerKey("fright_forest/fright_fungus_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_EMBER_ROOTS = registerKey("fright_forest/hanging_ember_roots");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_VEGETATION = registerKey("fright_forest/basalt_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_FIRE_PATCH_LICHOSS = registerKey("fright_forest/patch_soul_fire_lichoss");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_ROCK = registerKey("fright_forest/basalt_rock");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASHEN_DELTA = registerKey("ashen_deltas/ashen_delta");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASHEN_BASALT_BLOBS = registerKey("ashen_deltas/ashen_basalt_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_ASHEN_BASALT_COLUMNS = registerKey("ashen_deltas/large_ashen_basalt_columns");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_ASHEN_BASALT_COLUMNS = registerKey("ashen_deltas/small_ashen_basalt_columns");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_PATCH = registerKey("ashen_deltas/magma_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_FIRE_PATCH_ASHEN = registerKey("ashen_deltas/patch_soul_fire_ashen");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASHEN_SNOW_PATCH = registerKey("ashen_deltas/ashen_snow_patch");



    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}