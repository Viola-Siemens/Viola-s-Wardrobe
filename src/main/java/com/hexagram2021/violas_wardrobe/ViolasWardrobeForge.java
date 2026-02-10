package com.hexagram2021.violas_wardrobe;

import com.hexagram2021.violas_wardrobe.common.VWContent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Viola 的衣柜模组主类喵~
 *
 * @author liudongyu
 */
@SuppressWarnings("java:S1118")
@Mod(ViolasWardrobeForge.MODID)
public class ViolasWardrobeForge {
	/**
	 * 模组 ID 喵~
	 */
	public static final String MODID = "violas_wardrobe";

	/**
	 * 模组构造方法，初始化模组内容并注册事件监听器喵~
	 */
	public ViolasWardrobeForge() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		VWContent.modConstruction(modBus);
		modBus.addListener(ViolasWardrobeForge::onCommonSetup);
	}

	/**
	 * 通用设置事件处理方法，配置原版兼容性喵~
	 *
	 * @param event 通用设置事件喵~
	 */
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(VWContent::vanillaCompat);
	}
}
