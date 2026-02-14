package com.hexagram2021.violas_wardrobe.client.layers;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

/**
 * 女仆装套装的渲染层喵~
 *
 * @param <T> 生物实体类型喵~
 * @param <M> 父模型类型喵~
 * @param <I> 内层模型类型喵~
 * @param <O> 外层模型类型喵~
 * @author liudongyu
 */
public class MaidHeadbandAndSkirtLayer<T extends LivingEntity, M extends HumanoidModel<T>, I extends HumanoidModel<T>, O extends HumanoidModel<T>> extends ViolasWardrobeSkirtLayer<T, M, I, O> {
	/**
	 * 构造女仆装套装渲染层喵~
	 *
	 * @param renderer 渲染器父对象喵~
	 * @param innerModel 内层模型喵~
	 * @param outerModel 外层模型喵~
	 */
	public MaidHeadbandAndSkirtLayer(RenderLayerParent<T, M> renderer, I innerModel, O outerModel) {
		super(renderer, innerModel, outerModel);
	}

	/**
	 * 根据装备槽位设置内层模型部件的可见性喵~
	 *
	 * @param slot 装备槽位喵~
	 */
	@Override
	protected void setInnerPartVisibility(EquipmentSlot slot) {
		this.innerModel.setAllVisible(false);
		switch (slot) {
			case HEAD -> {
				this.innerModel.head.visible = true;
				this.innerModel.hat.visible = true;
			}
			case CHEST -> {
				this.innerModel.body.visible = true;
				this.innerModel.rightArm.visible = true;
				this.innerModel.leftArm.visible = true;
			}
			case LEGS, FEET -> {
				this.innerModel.rightLeg.visible = true;
				this.innerModel.leftLeg.visible = true;
			}
			default -> throw new IllegalArgumentException("Unexpected slot: " + slot);
		}
	}

	/**
	 * 根据装备槽位设置外层模型部件的可见性喵~
	 *
	 * @param slot 装备槽位喵~
	 */
	@Override
	protected void setOuterPartVisibility(EquipmentSlot slot) {
		this.outerModel.setAllVisible(false);
		switch (slot) {
			case HEAD -> {
				this.outerModel.head.visible = true;
				this.outerModel.hat.visible = true;
			}
			case CHEST -> {
				this.outerModel.body.visible = true;
				this.outerModel.rightArm.visible = true;
				this.outerModel.leftArm.visible = true;
			}
			case LEGS, FEET -> {
				this.outerModel.rightLeg.visible = true;
				this.outerModel.leftLeg.visible = true;
			}
			default -> throw new IllegalArgumentException("Unexpected slot: " + slot);
		}
	}
}
