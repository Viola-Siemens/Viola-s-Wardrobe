package com.hexagram2021.violas_wardrobe.client.models;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class MaidHeadbandAndSkirtModel<T extends LivingEntity> extends HumanoidModel<T> {
	public MaidHeadbandAndSkirtModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild(
				"head", CubeListBuilder.create(),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"hat", CubeListBuilder.create().texOffs(0, 0)
						.addBox(-5.0F, -10.0F, -0.75F, 10.0F, 2.0F, 1.0F, new CubeDeformation(-0.125F)),
				PartPose.offset(0.0F, -3.0F, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"body", CubeListBuilder.create().texOffs(16, 26)
						.addBox(-4.0F, 11.25F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(1.0F)),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"right_arm", CubeListBuilder.create().texOffs(40, 16)
						.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"left_arm", CubeListBuilder.create().texOffs(40, 16).mirror()
						.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(5.0F, 2.0F, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"right_leg", CubeListBuilder.create().texOffs(0, 16)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F)
		);
		partdefinition.addOrReplaceChild(
				"left_leg", CubeListBuilder.create().texOffs(0, 16).mirror()
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(1.9F, 12.0F, 0.0F)
		);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}