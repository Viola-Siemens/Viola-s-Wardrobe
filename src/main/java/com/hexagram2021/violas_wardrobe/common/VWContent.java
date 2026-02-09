package com.hexagram2021.violas_wardrobe.common;

import com.hexagram2021.violas_wardrobe.common.registries.VWCreativeModeTabs;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantmentCategories;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * Mod Content
 * @author liudongyu
 */
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VWContent {
	/**
	 * Called on mod constructing.
	 * @param modBus	the mod bus
	 */
	public static void modConstruction(IEventBus modBus) {
		VWEnchantmentCategories.init();
		VWItems.init(modBus);
		VWEnchantments.init(modBus);
		VWCreativeModeTabs.init(modBus);
	}

	public static void vanillaCompat() {
		DispenserBlock.registerBehavior(VWItems.MAID_HEADBAND, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.MAID_DRESS, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
		DispenserBlock.registerBehavior(VWItems.MAID_THIGH_HIGHS, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
	}

	private VWContent() {
	}
}
