package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 流光附魔，减缓生物下坠、提供下落保护喵~
 *
 * @author liudongyu
 */
public class LuminaEnchantment extends Enchantment {
	/**
	 * 构造流光附魔喵~
	 */
	public LuminaEnchantment() {
		super(Rarity.VERY_RARE, VWEnchantmentCategories.VIOLAS_WARDROBE_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
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
