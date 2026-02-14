package com.hexagram2021.violas_wardrobe.client.models;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

/**
 * 女仆头饰和裙子模型类，定义女仆装的头饰和裙子部分的 3D 模型喵~
 *
 * @param <T> 生物实体类型喵~
 * @author liudongyu
 */
public class MaidHeadbandAndSkirtModel<T extends LivingEntity> extends HumanoidModel<T> implements ISkirtModel {
	private final ModelPart skirt;

	/**
	 * 构造女仆头饰和裙子模型喵~
	 *
	 * @param root 模型根部件喵~
	 */
	public MaidHeadbandAndSkirtModel(ModelPart root) {
		super(root);
		this.skirt = root.getChild("skirt");
	}

	/**
	 * 创建身体图层定义，定义头饰、裙子和袖子的模型结构喵~
	 *
	 * @return 图层定义喵~
	 */
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
				"body", CubeListBuilder.create(),
				PartPose.ZERO
		);
		partdefinition.addOrReplaceChild(
				"skirt", CubeListBuilder.create().texOffs(16, 26)
						.addBox(-4.0F, 11.25F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(1.125F)),
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

	/**
	 * 获取所有身体部件喵~
	 *
	 * @return 所有身体部件喵~
	 */
	@Override
	protected Iterable<ModelPart> bodyParts() {
		return Iterables.concat(super.bodyParts(), ImmutableList.of(this.skirt));
	}

	/**
	 * 设置所有部件的可见性喵~
	 *
	 * @param visible 是否可见喵~
	 */
	@Override
	public void setAllVisible(boolean visible) {
		super.setAllVisible(visible);
		this.skirt.visible = visible;
	}

	/**
	 * 设置模型的动画状态喵~
	 *
	 * @param entity 实体喵~
	 * @param limbSwing 肢体摆动喵~
	 * @param limbSwingAmount 肢体摆动幅度喵~
	 * @param ageInTicks 总刻数喵~
	 * @param netHeadYaw 头部偏航角喵~
	 * @param headPitch 头部俯仰角喵~
	 */
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.skirt.copyFrom(this.body);
	}

	/**
	 * 获取裙子部件喵~
	 *
	 * @return 裙子部件喵~
	 */
	@Override
	public ModelPart getSkirt() {
		return this.skirt;
	}
}