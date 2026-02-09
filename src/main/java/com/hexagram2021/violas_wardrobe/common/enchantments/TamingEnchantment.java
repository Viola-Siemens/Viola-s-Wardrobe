package com.hexagram2021.violas_wardrobe.common.enchantments;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;

public class TamingEnchantment extends Enchantment {
	public TamingEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, VWEnchantmentCategories.VIOLAS_WARDROBE_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
	}

	/**
	 * Returns the maximum level that the enchantment can have.
	 */
	@Override
	public int getMaxLevel() {
		return 3;
	}

	/**
	 * Returns the minimal value of enchantability needed on the enchantment level passed.
	 * @param level Enchantment level
	 */
	@Override
	public int getMinCost(int level) {
		return 6 + level * 9;
	}

	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 15;
	}

	public static int wrapTamingChance(RandomSource instance, int bound, Player player, int ret) {
		int rolls = player.getInventory().armor.stream()
				.mapToInt(itemStack -> itemStack.getEnchantmentLevel(VWEnchantments.TAMING.get()))
				.max().orElse(0);
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
