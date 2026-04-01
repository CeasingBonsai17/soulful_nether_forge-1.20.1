package net.bon.soulfulnether.util;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class SoulfulTags {
    public static final TagKey<EntityType<?>> BOOSTED_BY_MARSHMARROW = createEntityTag("marshmarrow_stick_can_boost");

    private static TagKey<EntityType<?>> createEntityTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }

    public static final TagKey<Item> BONE_WYRM_FOODS = createItemTag("bone_wyrm_foods");
    public static final TagKey<Item> BONE_WYRM_LURE = createItemTag("bone_wyrm_lure");

    private static TagKey<Item> createItemTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }

    public static final TagKey<Block> SOUL_CONVERTING_BLOCKS = createBlockTag("soul_converting_blocks");
    public static final TagKey<Block> VALID_ROOT_BASES = createBlockTag("valid_root_bases");
    public static final TagKey<Block> VOLCANIC_COLD_SOURCES = createBlockTag("volcanic_cold_sources");
    public static final TagKey<Block> MARSHMARROW_HEAT_SOURCES = createBlockTag("marshmarrow_heat_sources");
    public static final TagKey<Block> INVALID_FIRE_BASE = createBlockTag("invalid_fire_base");
    public static final TagKey<Block> VALID_GLOOMY_FIDDLEHEAD_SOILS = createBlockTag("valid_gloomy_fiddlehead_soils");

    private static TagKey<Block> createBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }

    public static final TagKey<Biome> GLOOMY_WARPED_NYLIUM_BONEMEAL = createBiomeTag("gloomy_warped_nylium_bonemeal");

    private static TagKey<Biome> createBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}