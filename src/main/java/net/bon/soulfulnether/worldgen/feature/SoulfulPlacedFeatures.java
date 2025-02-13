package net.bon.soulfulnether.worldgen.feature;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class SoulfulPlacedFeatures {

    public static final ResourceKey<PlacedFeature> FRIGHT_FUNGUS = registerKey("fright_forest/fright_fungus");
    public static final ResourceKey<PlacedFeature> LICHOSS_PATCH = registerKey("fright_forest/lichoss_patch");
    public static final ResourceKey<PlacedFeature> LICHOSS_PATCH_CEILING = registerKey("fright_forest/lichoss_patch_ceiling");
    public static final ResourceKey<PlacedFeature> HANGING_LICHOSS = registerKey("fright_forest/hanging_lichoss");
    public static final ResourceKey<PlacedFeature> HANGING_LICHOSS_2 = registerKey("fright_forest/hanging_lichoss_2");
    public static final ResourceKey<PlacedFeature> SPIRALING_VINES = registerKey("fright_forest/spiraling_vines");
    public static final ResourceKey<PlacedFeature> FRIGHT_FUNGUS_VEGETATION = registerKey("fright_forest/fright_fungus_vegetation");
    public static final ResourceKey<PlacedFeature> HANGING_EMBER_ROOTS = registerKey("fright_forest/hanging_hanging_ember_roots");
    public static final ResourceKey<PlacedFeature> BASALT_VEGETATION = registerKey("fright_forest/basalt_vegetation");
    public static final ResourceKey<PlacedFeature> SOUL_FIRE_PATCH_LICHOSS = registerKey("fright_forest/patch_soul_fire_lichoss");
    public static final ResourceKey<PlacedFeature> BASALT_ROCK = registerKey("fright_forest/basalt_rock");

    public static final ResourceKey<PlacedFeature> ASHEN_DELTA = registerKey("ashen_deltas/ashen_delta");
    public static final ResourceKey<PlacedFeature> ASHEN_BASALT_BLOBS = registerKey("ashen_deltas/ashen_basalt_blobs");
    public static final ResourceKey<PlacedFeature> LARGE_ASHEN_BASALT_COLUMNS = registerKey("ashen_deltas/large_ashen_basalt_columns");
    public static final ResourceKey<PlacedFeature> SMALL_ASHEN_BASALT_COLUMNS = registerKey("ashen_deltas/small_ashen_basalt_columns");
    public static final ResourceKey<PlacedFeature> MAGMA_PATCH = registerKey("ashen_deltas/magma_patch");
    public static final ResourceKey<PlacedFeature> SOUL_FIRE_PATCH_ASHEN = registerKey("ashen_deltas/patch_soul_fire_ashen");
    public static final ResourceKey<PlacedFeature> ASHEN_SNOW_PATCH = registerKey("ashen_deltas/ashen_snow_patch");



    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}
