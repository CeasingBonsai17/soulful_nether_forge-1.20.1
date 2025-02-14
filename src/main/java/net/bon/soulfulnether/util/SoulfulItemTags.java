package net.bon.soulfulnether.util;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class SoulfulItemTags {
    public static final TagKey<Item> MARROWS = createTag("marrows");
    public static final TagKey<Item> FRIGHT_STEMS = createTag("fright_stems");

    private SoulfulItemTags() {
    }

    private static TagKey<Item> createTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(SoulfulNether.MOD_ID, name));
    }
}