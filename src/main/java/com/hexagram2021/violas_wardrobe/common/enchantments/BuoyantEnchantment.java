package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 轻飘飘附魔，使用缓降效果喵~
 *
 * @author liudongyu
 */
public class BuoyantEnchantment extends Enchantment {
	/**
	 * 构造轻飘飘附魔喵~
	 */
	public BuoyantEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, VWEnchantmentCategories.VIOLAS_WARDROBE_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
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
