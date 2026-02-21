package com.hexagram2021.violas_wardrobe.common.items.curios;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.compat.curios.ViolasWardrobeCuriosRenderer;
import com.hexagram2021.violas_wardrobe.client.models.ISkirtModel;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import com.hexagram2021.violas_wardrobe.common.items.MaidOutfitItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.List;

/**
 * 女仆装 Curios 物品类，实现 Curios 兼容的女仆装喵~
 *
 * @author liudongyu
 */
public class MaidOutfitCuriosItem extends MaidOutfitItem implements VWCuriosItem {
	/**
	 * 构造女仆装物品喵~
	 *
	 * @param slot       装备槽位喵~
	 * @param properties 物品属性喵~
	 */
	public MaidOutfitCuriosItem(EquipmentSlot slot, Properties properties) {
		super(slot, properties);
	}

	/**
	 * 创建女仆装的 Curios 渲染器喵~
	 *
	 * @return Curios 渲染器喵~
	 */
	@OnlyIn(Dist.CLIENT)
	@Override
	public ICurioRenderer makeRenderer() {
		EntityModelSet entityModelSet = Minecraft.getInstance().getEntityModels();
		return new ViolasWardrobeCuriosRenderer(List.of(
				new ViolasWardrobeCuriosRenderer.ModelWithTexture(
						new HumanoidArmorModel<>(entityModelSet.bakeLayer(VWLayerLocations.MAID_MAIN)),
						this.getInnerTexture(),
						(model, outfitItem) -> {
							model.setAllVisible(false);
							EquipmentSlot slot = outfitItem.getEquipmentSlot();
							switch (slot) {
								case HEAD -> {
									model.head.visible = true;
									model.hat.visible = true;
								}
								case CHEST -> {
									model.body.visible = true;
									model.rightArm.visible = true;
									model.leftArm.visible = true;
								}
								case LEGS, FEET -> {
									model.rightLeg.visible = true;
									model.leftLeg.visible = true;
								}
								default -> throw new IllegalArgumentException("Unexpected slot: " + slot);
							}
						}
				),
				new ViolasWardrobeCuriosRenderer.ModelWithTexture(
						new MaidHeadbandAndSkirtModel<>(entityModelSet.bakeLayer(VWLayerLocations.MAID_HNS)),
						this.getOuterTexture(),
						(model, outfitItem) -> {
							model.setAllVisible(false);
							EquipmentSlot slot = outfitItem.getEquipmentSlot();
							switch (slot) {
								case HEAD -> {
									model.head.visible = true;
									model.hat.visible = true;
								}
								case CHEST -> {
									model.body.visible = true;
									((ISkirtModel)model).getSkirt().visible = true;
									model.rightArm.visible = true;
									model.leftArm.visible = true;
								}
								case LEGS, FEET -> {
									model.rightLeg.visible = true;
									model.leftLeg.visible = true;
								}
								default -> throw new IllegalArgumentException("Unexpected slot: " + slot);
							}
						}
				)
		));
	}
}
