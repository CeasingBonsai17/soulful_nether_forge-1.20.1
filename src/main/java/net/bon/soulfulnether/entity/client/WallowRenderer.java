package net.bon.soulfulnether.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.entity.type.Wallow;
import net.bon.soulfulnether.entity.client.WallowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WallowRenderer extends MobRenderer<Wallow, WallowModel<Wallow>> {
    public WallowRenderer(EntityRendererProvider.Context context) {
        super(context, new WallowModel<>(context.bakeLayer(SoulfulModelLayers.WALLOW)), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Wallow boneWyrm) {
        return new ResourceLocation(SoulfulNether.MOD_ID, "textures/entity/wallow/wallow.png");
    }

    @Override
    public void render(Wallow entity, float f, float g, PoseStack pose, MultiBufferSource source, int i) {
        super.render(entity, f, g, pose, source, i);
    }
}
