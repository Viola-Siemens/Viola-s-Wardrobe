package com.hexagram2021.violas_wardrobe.common.registries;

import com.hexagram2021.violas_wardrobe.common.items.BaseOutfitItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public final class VWEnchantmentCategories {
	public static final EnchantmentCategory VIOLAS_WARDROBE_CHEST = EnchantmentCategory.create(
			"violas_wardrobe_chest",
			item -> item instanceof BaseOutfitItem baseOutfitItem && baseOutfitItem.getEquipmentSlot() == EquipmentSlot.CHEST
	);

	private VWEnchantmentCategories() {
	}

	public static void init() {
		// Lazy init
	}
}
