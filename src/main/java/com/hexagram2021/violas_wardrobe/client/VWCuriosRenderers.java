package com.hexagram2021.violas_wardrobe.client;

import com.hexagram2021.violas_wardrobe.common.items.BaseOutfitItem;
import com.hexagram2021.violas_wardrobe.common.items.curios.VWCuriosItem;
import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

/**
 * Curios 渲染器注册类，负责注册所有服装物品的 Curios 渲染器喵~
 *
 * @author liudongyu
 */
public final class VWCuriosRenderers {
	/**
	 * 注册所有服装物品的 Curios 渲染器喵~
	 */
	public static void registerRenderers() {
		VWItems.ItemEntry.getItems().forEach(item -> {
			if(item.get() instanceof BaseOutfitItem outfitItem && outfitItem instanceof VWCuriosItem curiosItem) {
				CuriosRendererRegistry.register(outfitItem, curiosItem::makeRenderer);
			}
		});
	}

	private VWCuriosRenderers() {
	}
}
