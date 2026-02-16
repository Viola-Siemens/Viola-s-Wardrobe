package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 光合滋养附魔，在阳光下为生物提供增益效果喵~
 *
 * @author liudongyu
 */
public class DaylightBloomingEnchantment extends Enchantment {
	/**
	 * 构造光合滋养附魔喵~
	 */
	public DaylightBloomingEnchantment() {
		super(Enchantment.Rarity.COMMON, VWEnchantmentCategories.VIOLAS_WARDROBE_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
	}

	/**
	 * 返回附魔的最大等级喵~
	 *
	 * @return 最大等级，固定为 2 喵~
	 */
	@Override
	public int getMaxLevel() {
		return 2;
	}

	/**
	 * 返回指定附魔等级所需的最小附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最小附魔能力值喵~
	 */
	@Override
	public int getMinCost(int level) {
		return 20 * level - 10;
	}

	/**
	 * 返回指定附魔等级所需的最大附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最大附魔能力值喵~
	 */
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 50;
	}
}