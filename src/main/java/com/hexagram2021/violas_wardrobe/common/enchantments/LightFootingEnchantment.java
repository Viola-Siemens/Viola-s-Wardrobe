package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.utils.SuitUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 步伐轻盈附魔喵~
 *
 * @author liudongyu
 */
public class LightFootingEnchantment extends Enchantment {
	/**
	 * 构造步伐轻盈附魔喵~
	 */
	public LightFootingEnchantment() {
		super(Enchantment.Rarity.COMMON, VWEnchantmentCategories.VIOLAS_WARDROBE_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
	}

	/**
	 * 返回附魔的最大等级喵~
	 *
	 * @return 最大等级，固定为 5 喵~
	 */
	@Override
	public int getMaxLevel() {
		return 5;
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
		return this.getMinCost(level) + 20;
	}

	/**
	 * 包装疲劳度计算，根据玩家装备的步伐轻盈附魔等级降低饥饿值消耗喵~
	 *
	 * @param player 玩家喵~
	 * @param exhaustion 疲劳度喵~
	 * @return 调整后的疲劳度喵~
	 */
	public static float wrapExhaustion(Player player, float exhaustion) {
		int maxLevel = SuitUtils.getMaxEnchantLevel(player, VWEnchantments.LIGHT_FOOTING.get());
		if(maxLevel == 0) {
			return exhaustion;
		}
		return exhaustion / (1.0F + 0.25F * maxLevel);
	}
}