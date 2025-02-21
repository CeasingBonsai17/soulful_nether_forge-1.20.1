package net.bon.soulfulnether.worldgen.feature;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.worldgen.feature.custom.AshenBasaltColumnsFeature;
import net.bon.soulfulnether.worldgen.feature.custom.AshenColumnFeatureConfiguration;
import net.bon.soulfulnether.worldgen.feature.custom.HugeFrightFungusFeature;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SoulfulNether.MOD_ID);


    public static final RegistryObject<Feature<AshenColumnFeatureConfiguration>> ASHEN_BASALT_COLUMNS = FEATURES.register("ashen_basalt_columns", () ->
            new AshenBasaltColumnsFeature(AshenColumnFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<HugeMushroomFeatureConfiguration>> HUGE_FRIGHT_FUNGUS = FEATURES.register("huge_fright_fungus", () ->
            new HugeFrightFungusFeature(HugeMushroomFeatureConfiguration.CODEC));


    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
