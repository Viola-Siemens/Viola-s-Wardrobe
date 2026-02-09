package com.hexagram2021.violas_wardrobe;

import com.hexagram2021.violas_wardrobe.common.VWContent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Mod Main Class
 * @author liudongyu
 */
@SuppressWarnings("java:S1118")
@Mod(ViolasWardrobeForge.MODID)
public class ViolasWardrobeForge {
	public static final String MODID = "violas_wardrobe";

	public ViolasWardrobeForge() {
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		VWContent.modConstruction(modBus);
		modBus.addListener(ViolasWardrobeForge::onCommonSetup);
	}

	public static void onCommonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(VWContent::vanillaCompat);
	}
}
