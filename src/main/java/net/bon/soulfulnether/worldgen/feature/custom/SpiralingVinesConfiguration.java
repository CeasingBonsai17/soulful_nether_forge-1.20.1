package net.bon.soulfulnether.worldgen.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record SpiralingVinesConfiguration(int spreadWidth, int spreadHeight, int maxHeight) implements FeatureConfiguration {
    public static final Codec<SpiralingVinesConfiguration> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(ExtraCodecs.POSITIVE_INT.fieldOf("spread_width").forGetter(SpiralingVinesConfiguration::spreadWidth), ExtraCodecs.POSITIVE_INT.fieldOf("spread_height").forGetter(SpiralingVinesConfiguration::spreadHeight), ExtraCodecs.POSITIVE_INT.fieldOf("max_height").forGetter(SpiralingVinesConfiguration::maxHeight)).apply(instance, SpiralingVinesConfiguration::new);
    });

    public int spreadWidth() {
        return this.spreadWidth;
    }

    public int spreadHeight() {
        return this.spreadHeight;
    }

    public int maxHeight() {
        return this.maxHeight;
    }
}
