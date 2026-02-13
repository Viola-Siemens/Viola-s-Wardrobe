package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.enchantment.Enchantment;

/**
 * 芳踪附魔喵~
 *
 * @author liudongyu
 */
public class SweatTracingEnchantment extends Enchantment {
	public SweatTracingEnchantment() {
		super(Rarity.UNCOMMON, VWEnchantmentCategories.VIOLAS_WARDROBE_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
	}

	/**
	 * 返回指定附魔等级所需的最小附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最小附魔能力值喵~
	 */
	@Override
	public int getMinCost(int level) {
		return 20 * level - 15;
	}

	/**
	 * 返回指定附魔等级所需的最大附魔能力值喵~
	 *
	 * @param level 附魔等级喵~
	 * @return 最大附魔能力值喵~
	 */
	@Override
	public int getMaxCost(int level) {
		return super.getMinCost(level) + 50;
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

	private static final TargetingConditions POLLINATING_BONUS_CONDITIONS = TargetingConditions.forNonCombat().range(32.0D);

	/**
	 * 获取周围穿戴芳踪附魔衣服的玩家对蜜蜂采蜜速度的贡献喵~
	 * @param bee 蜜蜂喵~
	 * @return 玩家对蜜蜂采蜜速度的贡献喵~
	 */
	public static int getPollinatingTicksBonus(Bee bee) {
		return bee.level().getNearbyPlayers(POLLINATING_BONUS_CONDITIONS, bee, bee.getBoundingBox().inflate(32.0D, 16.0D, 32.0D))
				.stream()
				.flatMap(player -> player.getInventory().armor.stream())
				.mapToInt(itemStack -> itemStack.getEnchantmentLevel(VWEnchantments.SWEAT_TRACING.get()))
				.max().orElse(0);
	}
}
