package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.utils.SuitUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 动物亲和附魔，提高驯服动物的成功率喵~
 *
 * @author liudongyu
 */
public class TamingEnchantment extends Enchantment {
	/**
	 * 构造动物亲和附魔喵~
	 */
	public TamingEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, VWEnchantmentCategories.VIOLAS_WARDROBE_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
	}

	/**
	 * 返回附魔的最大等级喵~
	 *
	 * @return 最大等级，固定为 3 喵~
	 */
	@Override
	public int getMaxLevel() {
		return 3;
	}

	/**
	 * 返回指定附魔等级所需的最小附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最小附魔能力值喵~
	 */
	@Override
	public int getMinCost(int level) {
		return 6 + level * 9;
	}

	/**
	 * 返回指定附魔等级所需的最大附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最大附魔能力值喵~
	 */
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}

	/**
	 * 包装驯服概率计算，根据玩家装备的动物亲和附魔等级提高驯服成功率喵~
	 *
	 * @param instance 随机数生成器喵~
	 * @param bound 随机数上界喵~
	 * @param player 玩家喵~
	 * @param ret 原始随机数结果喵~
	 * @return 调整后的随机数结果喵~
	 */
	public static int wrapTamingChance(RandomSource instance, int bound, Player player, int ret) {
		int rolls = SuitUtils.getMaxEnchantLevel(player, VWEnchantments.TAMING.get());
		if(rolls > 10) {
			rolls = 10;
		}
		for(int i = 0; i < rolls; i++) {
			if (ret == 0) {
				return ret;
			}
			ret = instance.nextInt(bound);
		}
		return ret;
	}
}
