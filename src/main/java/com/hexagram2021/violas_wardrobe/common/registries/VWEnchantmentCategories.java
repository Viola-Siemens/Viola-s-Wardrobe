package com.hexagram2021.violas_wardrobe.common.registries;

import com.hexagram2021.violas_wardrobe.common.items.BaseOutfitItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * 模组附魔分类喵~
 *
 * @author liudongyu
 */
public final class VWEnchantmentCategories {
	/**
	 * 模组物品的附魔分类喵~
	 */
	public static final EnchantmentCategory VIOLAS_WARDROBE = EnchantmentCategory.create(
			"violas_wardrobe",
			BaseOutfitItem.class::isInstance
	);

	/**
	 * 模组衣物的附魔分类，仅适用于头部槽位的服装物品喵~
	 */
	public static final EnchantmentCategory VIOLAS_WARDROBE_HEAD = EnchantmentCategory.create(
			"violas_wardrobe_head",
			item -> item instanceof BaseOutfitItem baseOutfitItem && baseOutfitItem.getEquipmentSlot() == EquipmentSlot.HEAD
	);

	/**
	 * 模组衣物的附魔分类，仅适用于胸部槽位的服装物品喵~
	 */
	public static final EnchantmentCategory VIOLAS_WARDROBE_CHEST = EnchantmentCategory.create(
			"violas_wardrobe_chest",
			item -> item instanceof BaseOutfitItem baseOutfitItem && baseOutfitItem.getEquipmentSlot() == EquipmentSlot.CHEST
	);

	/**
	 * 模组衣物的附魔分类，仅适用于腿部槽位的服装物品喵~
	 */
	public static final EnchantmentCategory VIOLAS_WARDROBE_LEGS = EnchantmentCategory.create(
			"violas_wardrobe_legs",
			item -> item instanceof BaseOutfitItem baseOutfitItem && baseOutfitItem.getEquipmentSlot() == EquipmentSlot.LEGS
	);

	/**
	 * 模组衣物的附魔分类，仅适用于脚部槽位的服装物品喵~
	 */
	public static final EnchantmentCategory VIOLAS_WARDROBE_FEET = EnchantmentCategory.create(
			"violas_wardrobe_feet",
			item -> item instanceof BaseOutfitItem baseOutfitItem && baseOutfitItem.getEquipmentSlot() == EquipmentSlot.FEET
	);

	private VWEnchantmentCategories() {
	}

	/**
	 * 初始化附魔分类，触发静态字段的懒加载喵~
	 */
	public static void init() {
		// Lazy init
	}
}
