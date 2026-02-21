package com.hexagram2021.violas_wardrobe.common.items.curios;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.compat.curios.ViolasWardrobeCuriosRenderer;
import com.hexagram2021.violas_wardrobe.client.models.JKUniformModel;
import com.hexagram2021.violas_wardrobe.common.items.JKUniformItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.List;

/**
 * JK 制服 Curios 物品类，实现 Curios 兼容的 JK 制服喵~
 *
 * @author liudongyu
 */
public class JKUniformCuriosItem extends JKUniformItem implements VWCuriosItem {
	/**
	 * 构造 JK 制服物品喵~
	 *
	 * @param variant    颜色变体喵~
	 * @param slot       装备槽位喵~
	 * @param properties 物品属性喵~
	 */
	public JKUniformCuriosItem(Variant variant, EquipmentSlot slot, Properties properties) {
		super(variant, slot, properties);
	}

	/**
	 * 创建 JK 制服的 Curios 渲染器喵~
	 *
	 * @return Curios 渲染器喵~
	 */
	@OnlyIn(Dist.CLIENT)
	@Override
	public ICurioRenderer makeRenderer() {
		EntityModelSet entityModelSet = Minecraft.getInstance().getEntityModels();
		return new ViolasWardrobeCuriosRenderer(List.of(
				new ViolasWardrobeCuriosRenderer.ModelWithTexture(
						new HumanoidArmorModel<>(entityModelSet.bakeLayer(VWLayerLocations.JK_UNIFORM_MAIN)),
						this.getInnerTexture()
				),
				new ViolasWardrobeCuriosRenderer.ModelWithTexture(
						new JKUniformModel<>(entityModelSet.bakeLayer(VWLayerLocations.JK_UNIFORM_OUTER)),
						this.getOuterTexture()
				)
		));
	}
}
