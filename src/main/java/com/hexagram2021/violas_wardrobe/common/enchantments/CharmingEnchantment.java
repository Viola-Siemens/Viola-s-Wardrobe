package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.utils.SuitUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 妩媚附魔，降低玩家与村民交易的价格喵~
 *
 * @author liudongyu
 */
public class CharmingEnchantment extends Enchantment {
	public CharmingEnchantment() {
		super(Rarity.RARE, VWEnchantmentCategories.VIOLAS_WARDROBE_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
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
	 * 获取玩家与村民交易威望的加成喵~
	 * @param player 玩家喵~
	 * @return 玩家与村民交易威望的加成喵~
	 */
	public static int getReputationBonus(Player player) {
		return SuitUtils.getMaxEnchantLevel(player, VWEnchantments.CHARMING.get()) * 5;
	}
}
