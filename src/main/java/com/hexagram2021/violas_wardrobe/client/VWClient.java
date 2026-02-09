package com.hexagram2021.violas_wardrobe.client;

import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * Mod Client
 * @author liudongyu
 */
@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VWClient {
	/**
	 * Register layer definitions for mod entities.
	 * @param event	register layer definitions event
	 */
	@SubscribeEvent
	public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(
				VWLayerLocations.MAID_MAIN,
				() -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.25F)), 64, 32)
		);
		event.registerLayerDefinition(
				VWLayerLocations.MAID_HNS,
				MaidHeadbandAndSkirtModel::createBodyLayer
		);
	}

	private VWClient() {
	}
}
