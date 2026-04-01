package net.bon.soulfulnether.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bon.soulfulnether.entity.animations.WallowAnimationDefinitions;
import net.bon.soulfulnether.entity.type.Wallow;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class WallowModel<T extends Wallow> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "guh"), "main");
	private final ModelPart root;
	private final ModelPart mouth_closed;
	private final ModelPart open_mouth;
	private final ModelPart hair_probably;

	public WallowModel(ModelPart root) {
		this.root = root.getChild("root");
		this.mouth_closed = this.root.getChild("mouth_closed");
		this.open_mouth = this.root.getChild("open_mouth");
		this.hair_probably = this.root.getChild("hair_probably");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition mouth_closed = root.addOrReplaceChild("mouth_closed", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition open_mouth = root.addOrReplaceChild("open_mouth", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 13.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition hair_probably = root.addOrReplaceChild("hair_probably", CubeListBuilder.create().texOffs(32, 36).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 48);
	}
	
	@Override
	public void setupAnim(Wallow entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(entity.idleAnimState, WallowAnimationDefinitions.WALLOW_IDLE, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}