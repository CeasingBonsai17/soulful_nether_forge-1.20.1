package net.bon.soulfulnether.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.entity.type.BoneWyrm;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BoneWyrmRenderer extends MobRenderer<BoneWyrm, BoneWyrmModel<BoneWyrm>> {
    public BoneWyrmRenderer(EntityRendererProvider.Context context) {
        super(context, new BoneWyrmModel<>(context.bakeLayer(SoulfulModelLayers.BONE_WYRM)), 0.7f);
        this.addLayer(new SaddleLayer<>(this, new BoneWyrmModel<>(context.bakeLayer(SoulfulModelLayers.BONE_WYRM_SADDLE)),
                new ResourceLocation(SoulfulNether.MOD_ID, "textures/entity/bone_wyrm/bone_wyrm_saddle.png")));
        this.addLayer(new BoneWyrmFlameLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BoneWyrm boneWyrm) {
        return new ResourceLocation(SoulfulNether.MOD_ID, "textures/entity/bone_wyrm/bone_wyrm.png");
    }

    @Override
    public void render(BoneWyrm entity, float f, float g, PoseStack pose, MultiBufferSource source, int i) {

        if(entity.isBaby()) {
            pose.scale(0.6f, 0.6f, 0.6f);
        }

        super.render(entity, f, g, pose, source, i);
    }
}
