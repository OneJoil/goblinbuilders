package net.anessan.goblinbuilders.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class GoblinBuilderModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leg_left;
	private final ModelPart leg_right;
	private final ModelPart arm_right;
	private final ModelPart arm_left;

	public GoblinBuilderModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leg_left = root.getChild("leg_left");
		this.leg_right = root.getChild("leg_right");
		this.arm_right = root.getChild("arm_right");
		this.arm_left = root.getChild("arm_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(26, 9).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition laces = body.addOrReplaceChild("laces", CubeListBuilder.create().texOffs(2, 2).addBox(-2.0F, -9.0F, -1.25F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 2).addBox(1.0F, -9.0F, -1.25F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(33, 33).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(2, 2).addBox(-3.25F, -3.0F, -2.25F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 2).addBox(2.25F, -3.0F, -2.25F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));

		PartDefinition ear_left = head.addOrReplaceChild("ear_left", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition cube_r1 = ear_left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 2).addBox(3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 17).addBox(2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(4, 17).addBox(1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(17, 24).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(22, 1).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(15, 28).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -11.0F, 2.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition ear_right = head.addOrReplaceChild("ear_right", CubeListBuilder.create(), PartPose.offsetAndRotation(0.25F, 9.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r2 = ear_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(3.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 2).addBox(2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 0).addBox(1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 1).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(4, 0).addBox(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(4, 1).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(4, 1).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -11.0F, 2.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(22, 18).addBox(-5.0F, -14.0F, -3.25F, 10.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-5.0F, -14.0F, 4.0F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-4.0F, -13.0F, 5.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-4.0F, -9.0F, 3.0F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(2, 11).addBox(-4.0F, -9.0F, 2.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(2, 11).addBox(4.0F, -9.0F, 2.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 26).addBox(-4.0F, -6.0F, 1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(4.0F, -6.0F, 1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(19, 21).addBox(-3.0F, -6.0F, 2.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(28, 6).addBox(-4.0F, -15.0F, -3.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(26, 17).addBox(-4.0F, -15.0F, 5.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition cube_r3 = hair.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(18, 26).addBox(-3.0F, -14.0F, 4.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 7).addBox(-2.0F, -15.0F, 5.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(28, 8).addBox(-2.0F, -15.0F, -5.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-7, 21).addBox(-3.0F, -15.0F, -4.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(-7, 21).addBox(4.0F, -15.0F, -4.0F, 1.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(-9, 19).addBox(-2.0F, -15.25F, -5.0F, 6.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-3.0F, -14.0F, -5.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition leg_left = partdefinition.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 20.0F, 0.5F));

		PartDefinition leg_right = partdefinition.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(33, 26).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 20.0F, 0.5F));

		PartDefinition arm_right = partdefinition.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(13, 29).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 15.0F, 0.5F));

		PartDefinition arm_left = partdefinition.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(23, 29).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 15.0F, 0.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = netHeadYaw / (180F / (float) Math.PI);
		this.leg_right.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.arm_right.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.leg_left.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.arm_left.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}