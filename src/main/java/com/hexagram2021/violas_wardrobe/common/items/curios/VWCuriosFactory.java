package com.hexagram2021.violas_wardrobe.common.items.curios;

import com.hexagram2021.violas_wardrobe.common.items.BlackMaidOutfitItem;
import com.hexagram2021.violas_wardrobe.common.items.JKUniformItem;
import com.hexagram2021.violas_wardrobe.common.items.MaidOutfitItem;
import com.hexagram2021.violas_wardrobe.common.items.ThinkingHatItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

/**
 * Curios 物品工厂类，用于创建 Curios 兼容的服装物品喵~
 *
 * @author liudongyu
 */
public final class VWCuriosFactory {
	/**
	 * 创建黑色女仆装 Curios 物品喵~
	 *
	 * @param slot 装备槽位喵~
	 * @param properties 物品属性喵~
	 * @return 黑色女仆装 Curios 物品喵~
	 */
	public static BlackMaidOutfitItem blackMaidOutfit(EquipmentSlot slot, Item.Properties properties) {
		return new BlackMaidOutfitCuriosItem(slot, properties);
	}

	/**
	 * 创建 JK 制服 Curios 物品喵~
	 *
	 * @param variant 颜色变体喵~
	 * @param slot 装备槽位喵~
	 * @param properties 物品属性喵~
	 * @return JK 制服 Curios 物品喵~
	 */
	public static JKUniformItem jKUniform(JKUniformItem.Variant variant, EquipmentSlot slot, Item.Properties properties) {
		return new JKUniformCuriosItem(variant, slot, properties);
	}

	/**
	 * 创建女仆装 Curios 物品喵~
	 *
	 * @param slot 装备槽位喵~
	 * @param properties 物品属性喵~
	 * @return 女仆装 Curios 物品喵~
	 */
	public static MaidOutfitItem maidOutfit(EquipmentSlot slot, Item.Properties properties) {
		return new MaidOutfitCuriosItem(slot, properties);
	}

	/**
	 * 创建思考帽 Curios 物品喵~
	 *
	 * @param variant 颜色变体喵~
	 * @param properties 物品属性喵~
	 * @return 思考帽 Curios 物品喵~
	 */
	public static ThinkingHatItem thinkingHat(ThinkingHatItem.Variant variant, Item.Properties properties) {
		return new ThinkingHatCuriosItem(variant, properties);
	}

	/**
	 * 获取实体饰品栏中衣服附魔最大等级喵~
	 *
	 * @param entity 实体喵~
	 * @param enchantment 附魔喵~
	 * @return 附魔最大等级喵~
	 */
	public static int getMaxLevel(LivingEntity entity, Enchantment enchantment) {
		return CuriosApi.getCuriosInventory(entity).map(c -> c.getCurios().values().stream().mapToInt(stacks -> {
			IDynamicStackHandler handler = stacks.getStacks();
			int maxLevel = 0;
			for(int i = 0; i < handler.getSlots(); ++i) {
				ItemStack itemStack = handler.getStackInSlot(i);
				maxLevel = Math.max(itemStack.getEnchantmentLevel(enchantment), maxLevel);
			}
			return maxLevel;
		}).max().orElse(0)).orElse(0);
	}

	private VWCuriosFactory() {
	}
}
