package com.hexagram2021.violas_wardrobe.common.utils;

import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public final class SuitUtils {
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
