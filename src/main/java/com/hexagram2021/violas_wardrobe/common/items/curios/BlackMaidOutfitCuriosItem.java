package com.hexagram2021.violas_wardrobe.common.items.curios;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.compat.curios.ViolasWardrobeCuriosRenderer;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import com.hexagram2021.violas_wardrobe.common.items.BlackMaidOutfitItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.List;

/**
 * 黑色女仆装 Curios 物品类，实现 Curios 兼容的黑色女仆装喵~
 *
 * @author liudongyu
 */
public class BlackMaidOutfitCuriosItem extends BlackMaidOutfitItem implements VWCuriosItem {
	/**
	 * 构造女仆装物品喵~
	 *
	 * @param slot       装备槽位喵~
	 * @param properties 物品属性喵~
	 */
	public BlackMaidOutfitCuriosItem(EquipmentSlot slot, Properties properties) {
		super(slot, properties);
	}

	/**
	 * 创建黑色女仆装的 Curios 渲染器喵~
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
						this.getInnerTexture()
				),
				new ViolasWardrobeCuriosRenderer.ModelWithTexture(
						new MaidHeadbandAndSkirtModel<>(entityModelSet.bakeLayer(VWLayerLocations.MAID_HNS)),
						this.getOuterTexture()
				)
		));
	}
}
