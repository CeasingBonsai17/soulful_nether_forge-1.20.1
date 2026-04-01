package net.bon.soulfulnether.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bon.soulfulnether.entity.animations.BoneWyrmAnimationDefinitions;
import net.bon.soulfulnether.entity.type.BoneWyrm;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BoneWyrmModel<T extends BoneWyrm> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart segment1;
	private final ModelPart segment2;
	private final ModelPart segment3;
	private final ModelPart back_legs;
	private final ModelPart head;
	private final ModelPart flame;
	private final ModelPart front_legs;

	public BoneWyrmModel(ModelPart root) {
		this.root = root.getChild("root");
		this.segment1 = this.root.getChild("segment1");
		this.segment2 = this.segment1.getChild("segment2");
		this.segment3 = this.segment2.getChild("segment3");
		this.back_legs = this.segment1.getChild("back_legs");
		this.head = this.root.getChild("head");
		this.flame = this.head.getChild("flame");
		this.front_legs = this.root.getChild("front_legs");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition segment1 = root.addOrReplaceChild("segment1", CubeListBuilder.create().texOffs(0, 22).addBox(-5.0F, -4.5F, 0.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, 6.0F));

		PartDefinition segment2 = segment1.addOrReplaceChild("segment2", CubeListBuilder.create().texOffs(40, 37).addBox(-4.0F, -3.5F, 0.0F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition segment3 = segment2.addOrReplaceChild("segment3", CubeListBuilder.create().texOffs(48, 12).addBox(-3.0F, -2.5F, 0.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition back_legs = segment1.addOrReplaceChild("back_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 4.5F, 5.0F));

		PartDefinition cube_r1 = back_legs.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(0.0F, 0.0F, -5.0F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		PartDefinition cube_r2 = back_legs.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, 0.0F, -5.0F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 41).addBox(0.0F, -10.0F, -11.0F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(40, 22).addBox(-5.0F, -4.0F, -7.0F, 10.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -6.0F));

		PartDefinition flame = head.addOrReplaceChild("flame", CubeListBuilder.create().texOffs(34, 41).addBox(-1.5F, -4.0F, 0.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -3.0F));

		PartDefinition front_legs = root.addOrReplaceChild("front_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = front_legs.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 41).addBox(0.0F, 0.0F, -6.0F, 0.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2217F));

		PartDefinition cube_r4 = front_legs.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(0.0F, 0.0F, -6.0F, 0.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.2217F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}

	@Override
	public void setupAnim(BoneWyrm entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		applyHeadRot(netHeadYaw, headPitch, ageInTicks);

		animate(entity.idleAnimState, BoneWyrmAnimationDefinitions.BONE_WYRM_IDLE, ageInTicks, 1f);
		animateWalk(BoneWyrmAnimationDefinitions.BONE_WYRM_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
	}

	public void applyHeadRot(float yaw, float pitch, float tick) {
		yaw = Mth.clamp(yaw, -30, 30);
		pitch = Mth.clamp(pitch, -25, 45);

		this.head.yRot = yaw * ((float)Math.PI / 180f);
		this.head.xRot = pitch * ((float)Math.PI / 180f);
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