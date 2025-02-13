package net.bon.soulfulnether.worldgen.feature;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.worldgen.feature.custom.AshenBasaltColumnsFeature;
import net.bon.soulfulnether.worldgen.feature.custom.AshenColumnFeatureConfiguration;
import net.bon.soulfulnether.worldgen.feature.custom.SpiralingVinesFeature;
import net.bon.soulfulnether.worldgen.feature.custom.SpiralingVinesConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SoulfulNether.MOD_ID);

    public static final RegistryObject<Feature<SpiralingVinesConfiguration>> SPIRALING_VINES = FEATURES.register("spiraling_vines", () ->
            new SpiralingVinesFeature(SpiralingVinesConfiguration.CODEC));

    public static final RegistryObject<Feature<AshenColumnFeatureConfiguration>> ASHEN_BASALT_COLUMNS = FEATURES.register("ashen_basalt_columns", () ->
            new AshenBasaltColumnsFeature(AshenColumnFeatureConfiguration.CODEC));


    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
