package net.bon.soulfulnether.worldgen;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class SoulfulBiomes {
    public static final ResourceKey<Biome> FRIGHT_FOREST = register("fright_forest");
    public static final ResourceKey<Biome> ASHEN_DELTAS = register("ashen_deltas");
    public static final ResourceKey<Biome> GLOOM_FOREST = register("gloom_forest");

    private static ResourceKey<Biome> register(String string) {
        return ResourceKey.create(Registries.BIOME,  new ResourceLocation(SoulfulNether.MOD_ID, string));
    }
}
