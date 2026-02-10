package com.hexagram2021.violas_wardrobe.common.utils;

import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

/**
 * 套装工具类，提供套装检测功能喵~
 *
 * @author liudongyu
 */
public final class SuitUtils {
	/**
	 * 检查实体是否穿着完整的女仆装喵~
	 *
	 * @param entity 待检查的生物实体喵~
	 * @return 是否穿着完整的女仆装喵~
	 */
	public static boolean isInMaidSuit(LivingEntity entity) {
		return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == VWItems.MAID_HEADBAND.get() &&
				entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == VWItems.MAID_DRESS.get() && (
						entity.getItemBySlot(EquipmentSlot.FEET).getItem() == VWItems.MAID_THIGH_HIGHS.get()
						//TODO black thigh highs?
				);
	}

	private SuitUtils() {
	}
}
