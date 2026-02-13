package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWDamageTypeTags;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 纤护附魔，降低受到的魔法伤害喵~
 *
 * @author liudongyu
 */
public class LacewardEnchantment extends Enchantment {
	public LacewardEnchantment() {
		super(Rarity.UNCOMMON, VWEnchantmentCategories.VIOLAS_WARDROBE, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
	}

	/**
	 * 返回附魔的最大等级喵~
	 *
	 * @return 最大等级，固定为 4 喵~
	 */
	@Override
	public int getMaxLevel() {
		return 4;
	}

	/**
	 * 返回指定附魔等级所需的最小附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最小附魔能力值喵~
	 */
	@Override
	public int getMinCost(int level) {
		return 8 * level - 3;
	}

	/**
	 * 返回指定附魔等级所需的最大附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最大附魔能力值喵~
	 */
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 8;
	}

	/**
	 * 根据伤害来源和附魔等级计算伤害保护值喵~
	 * @param level 附魔等级喵~
	 * @param damageSource 伤害来源喵~
	 */
	@Override
	public int getDamageProtection(int level, DamageSource damageSource) {
		return damageSource.is(VWDamageTypeTags.LACEWARD_PROTECT) ? level * 2 : 0;
	}
}
