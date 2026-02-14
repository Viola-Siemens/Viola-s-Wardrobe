package com.hexagram2021.violas_wardrobe.client.models;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class ThinkingHatModel<T extends LivingEntity> extends HumanoidModel<T> {
	public ThinkingHatModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild(
				"hat", CubeListBuilder.create().texOffs(0, 0)
						.addBox(-5.0F, -11.0F, -5.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(-0.25F))
						.texOffs(0, 14)
						.addBox(-5.0F, -7.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.75F)),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"head", CubeListBuilder.create(),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"body", CubeListBuilder.create(),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"right_arm", CubeListBuilder.create(),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"left_arm", CubeListBuilder.create(),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"right_leg", CubeListBuilder.create(),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"left_leg", CubeListBuilder.create(),
				PartPose.ZERO
		);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}