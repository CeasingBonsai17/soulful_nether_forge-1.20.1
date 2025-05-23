package net.bon.soulfulnether.worldgen.feature;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.worldgen.feature.custom.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SoulfulNether.MOD_ID);


    public static final RegistryObject<Feature<AshenColumnFeatureConfiguration>> ASHEN_BASALT_COLUMNS = FEATURES.register("ashen_basalt_columns", () ->
            new AshenBasaltColumnsFeature(AshenColumnFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeMushroomFeatureConfiguration>> LARGE_GLOOM_FUNGUS = FEATURES.register("large_gloom_fungus", () ->
            new LargeGloomFungusFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeMushroomFeatureConfiguration>> HUGE_GLOOM_FUNGUS = FEATURES.register("huge_gloom_fungus", () ->
            new HugeGloomFungusFeature(HugeMushroomFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> VOLCANIC_ICE_CUBES = FEATURES.register("volcanic_ice_cubes", () ->
            new VolcanicIceCubesFeature(BlockStateConfiguration.CODEC));


    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
