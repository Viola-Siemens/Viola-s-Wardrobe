package com.hexagram2021.violas_wardrobe.common.items.curios;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * Viola's Wardrobe 模组的 Curios 物品接口，用于创建 Curios 渲染器喵~
 *
 * @author liudongyu
 */
@FunctionalInterface
public interface VWCuriosItem extends ICurioItem {
	/**
	 * 创建 Curios 渲染器喵~
	 *
	 * @return Curios 渲染器喵~
	 */
	@OnlyIn(Dist.CLIENT)
	ICurioRenderer makeRenderer();
}
