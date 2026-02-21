package com.hexagram2021.violas_wardrobe.common.items.curios;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.compat.curios.ViolasWardrobeCuriosRenderer;
import com.hexagram2021.violas_wardrobe.client.models.ThinkingHatModel;
import com.hexagram2021.violas_wardrobe.common.items.ThinkingHatItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.List;

/**
 * 思考帽 Curios 物品类，实现 Curios 兼容的思考帽喵~
 *
 * @author liudongyu
 */
public class ThinkingHatCuriosItem extends ThinkingHatItem implements VWCuriosItem {
	/**
	 * 构造思考帽物品喵~
	 *
	 * @param variant    颜色变体喵~
	 * @param properties 物品属性喵~
	 */
	public ThinkingHatCuriosItem(Variant variant, Properties properties) {
		super(variant, properties);
	}

	/**
	 * 创建思考帽的 Curios 渲染器喵~
	 *
	 * @return Curios 渲染器喵~
	 */
	@OnlyIn(Dist.CLIENT)
	@Override
	public ICurioRenderer makeRenderer() {
		EntityModelSet entityModelSet = Minecraft.getInstance().getEntityModels();
		return new ViolasWardrobeCuriosRenderer(List.of(
				new ViolasWardrobeCuriosRenderer.ModelWithTexture(
						new ThinkingHatModel<>(entityModelSet.bakeLayer(VWLayerLocations.THINKING_HAT)),
						this.getOuterTexture(),
						(model, outfitItem) -> {
							model.setAllVisible(false);
							EquipmentSlot slot = outfitItem.getEquipmentSlot();
							if (slot == EquipmentSlot.HEAD) {
								model.head.visible = true;
								model.hat.visible = true;
							}
						}
				)
		));
	}
}
