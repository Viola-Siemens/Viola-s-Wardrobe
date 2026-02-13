package com.hexagram2021.violas_wardrobe.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组创意模式标签页喵~
 *
 * @author liudongyu
 */
public final class VWCreativeModeTabs {
	private static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

	/**
	 * 主标签页，包含模组的所有物品喵~
	 */
	public static final RegistryObject<CreativeModeTab> MAIN = REGISTER.register(
			"main", () -> CreativeModeTab.builder()
					.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
					.title(Component.translatable("itemGroup.violas_wardrobe"))
					.icon(() -> new ItemStack(VWItems.MAID_DRESS.get()))
					.displayItems((flags, output) -> VWItems.ItemEntry.getItems().forEach(output::accept))
					.build()
	);

	private VWCreativeModeTabs() {
	}

	/**
	 * 模组构造时注册创意模式标签页喵~
	 *
	 * @param modBus 模组事件总线喵~
	 */
	public static void init(IEventBus modBus) {
		REGISTER.register(modBus);
	}
}
