package net.bon.soulfulnether.util;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class SoulfulBlockTags {
        public static final TagKey<Block> SOUL_CONVERTING_BLOCKS = createTag("soul_converting_blocks");
        public static final TagKey<Block> FRIGHT_STEMS = createTag("fright_stems");
        public static final TagKey<Block> FRIGHT_WART_BLOCKS = createTag("fright_wart_blocks");
        public static final TagKey<Block> LICHOSS = createTag("lichoss");
        public static final TagKey<Block> LICHOSS_REPLACEABLE = createTag("lichoss_replaceable");
        public static final TagKey<Block> LICHOSS_PATCH_REPLACEABLE = createTag("lichoss_patch_replaceable");
        public static final TagKey<Block> FRIGHT_FOREST_GROUND_BLOCKS = createTag("fright_forest_ground_blocks");
        public static final TagKey<Block> VALID_ROOT_BASES = createTag("valid_root_bases");
        public static final TagKey<Block> MAGMA_PATCH_REPLACEABLE = createTag("magma_patch_replaceable");

    private SoulfulBlockTags() {
    }

    private static TagKey<Block> createTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}
