package net.bon.soulfulnether.util;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class SoulfulBlockTags {
    public static final TagKey<Block> SOUL_CONVERTING_BLOCKS = createTag("soul_converting_blocks");
    public static final TagKey<Block> VALID_ROOT_BASES = createTag("valid_root_bases");
    public static final TagKey<Block> VOLCANIC_COLD_SOURCES = createTag("volcanic_cold_sources");
    public static final TagKey<Block> MARSHMARROW_HEAT_SOURCES = createTag("marshmarrow_heat_sources");
    public static final TagKey<Block> INVALID_FIRE_BASE = createTag("invalid_fire_base");


    private SoulfulBlockTags() {
    }

    private static TagKey<Block> createTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}
