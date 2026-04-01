package net.bon.soulfulnether.entity.client;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class SoulfulModelLayers {
    public static final ModelLayerLocation WALLOW = new ModelLayerLocation(
            new ResourceLocation(SoulfulNether.MOD_ID, "wallow"), "main");
    public static final ModelLayerLocation BONE_WYRM = new ModelLayerLocation(
            new ResourceLocation(SoulfulNether.MOD_ID, "bone_wyrm"), "main");
    public static final ModelLayerLocation BONE_WYRM_SADDLE = new ModelLayerLocation(
            new ResourceLocation(SoulfulNether.MOD_ID, "bone_wyrm_saddle"), "main");
}
