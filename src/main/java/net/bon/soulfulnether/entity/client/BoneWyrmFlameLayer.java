package net.bon.soulfulnether.entity.client;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.entity.type.BoneWyrm;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoneWyrmFlameLayer<T extends BoneWyrm> extends EyesLayer<T, BoneWyrmModel<T>> {
    private static final RenderType BONE_WYRM_FLAME = RenderType.eyes(new ResourceLocation(SoulfulNether.MOD_ID, "textures/entity/bone_wyrm/flame.png"));

    public BoneWyrmFlameLayer(RenderLayerParent<T, BoneWyrmModel<T>> model) {
        super(model);
    }

    public RenderType renderType() {
        return BONE_WYRM_FLAME;
    }
}
