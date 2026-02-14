package com.hexagram2021.violas_wardrobe.common;

import com.hexagram2021.violas_wardrobe.common.registries.VWCreativeModeTabs;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * 模组内容初始化类，负责注册所有游戏内容喵~
 *
 * @author liudongyu
 */
public final class VWContent {
	/**
	 * 模组构造时调用，初始化所有注册表喵~
	 *
	 * @param modBus 模组事件总线喵~
	 */
	public static void modConstruction(IEventBus modBus) {
		VWEnchantmentCategories.init();
		VWItems.init(modBus);
		VWEnchantments.init(modBus);
		VWCreativeModeTabs.init(modBus);
	}

	/**
	 * 配置原版兼容性，为投掷器注册服装物品的行为喵~
	 */
	public static void vanillaCompat() {
		DispenserBlock.registerBehavior(VWItems.MAID_HEADBAND, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.MAID_DRESS, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.MAID_THIGH_HIGHS, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.MAID_BLACK_THIGH_HIGHS, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.JK_UNIFORM_PURPLISH_BLUE_SUMMER, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.JK_UNIFORM_PURPLISH_BLUE_SKIRT, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.JK_UNIFORM_PURPLISH_BLUE_SHOES, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.JK_UNIFORM_CREAM_SUMMER, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.JK_UNIFORM_CREAM_SKIRT, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.JK_UNIFORM_CREAM_SHOES, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
	}

	private VWContent() {
	}
}
