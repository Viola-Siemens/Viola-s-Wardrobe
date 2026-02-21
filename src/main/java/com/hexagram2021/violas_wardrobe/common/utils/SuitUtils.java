package com.hexagram2021.violas_wardrobe.common.utils;

import com.hexagram2021.violas_wardrobe.common.items.curios.VWCuriosFactory;
import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.ModList;

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
						entity.getItemBySlot(EquipmentSlot.FEET).getItem() == VWItems.MAID_THIGH_HIGHS.get() ||
								entity.getItemBySlot(EquipmentSlot.FEET).getItem() == VWItems.MAID_BLACK_THIGH_HIGHS.get()
				);
	}

	/**
	 * 检查实体是否穿着完整的 JK 制服喵~
	 *
	 * @param entity 待检查的生物实体喵~
	 * @return 是否穿着完整的 JK 制服喵~
	 */
	public static boolean isInJKUniformSuit(LivingEntity entity) {
		return (
				entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == VWItems.JK_UNIFORM_PURPLISH_BLUE_SUMMER.get() &&
				entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == VWItems.JK_UNIFORM_PURPLISH_BLUE_SKIRT.get() &&
				entity.getItemBySlot(EquipmentSlot.FEET).getItem() == VWItems.JK_UNIFORM_PURPLISH_BLUE_SHOES.get()
		) || (
				entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == VWItems.JK_UNIFORM_CREAM_SUMMER.get() &&
				entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == VWItems.JK_UNIFORM_CREAM_SKIRT.get() &&
				entity.getItemBySlot(EquipmentSlot.FEET).getItem() == VWItems.JK_UNIFORM_CREAM_SHOES.get()
		);
	}

	/**
	 * 获取实体物品栏中衣服附魔最大等级喵~
	 *
	 * @param entity 实体喵~
	 * @param enchantment 附魔喵~
	 * @return 附魔最大等级喵~
	 */
	public static int getMaxEnchantLevel(LivingEntity entity, Enchantment enchantment) {
		int maxLevel = 0;
		for (ItemStack itemStack: entity.getArmorSlots()) {
			maxLevel = Math.max(maxLevel, itemStack.getEnchantmentLevel(enchantment));
		}
		if(ModList.get().isLoaded("curios")) {
			maxLevel = Math.max(maxLevel, VWCuriosFactory.getMaxLevel(entity, enchantment));
		}
		return maxLevel;
	}

	private SuitUtils() {
	}
}
