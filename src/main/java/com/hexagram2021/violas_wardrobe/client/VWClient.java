package com.hexagram2021.violas_wardrobe.client;

import com.hexagram2021.violas_wardrobe.client.config.VWClientConfig;
import com.hexagram2021.violas_wardrobe.client.models.JKUniformModel;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import com.hexagram2021.violas_wardrobe.client.models.ThinkingHatModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组客户端初始化类喵~
 *
 * @author liudongyu
 */
@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VWClient {
	/**
	 * 注册模组实体的图层定义喵~
	 *
	 * @param event 注册图层定义事件喵~
	 */
	@SubscribeEvent
	public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(
				VWLayerLocations.MAID_MAIN,
				() -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(VWClientConfig.UNIFORM_INNER_GROWTH.get().floatValue())), 64, 32)
		);
		event.registerLayerDefinition(
				VWLayerLocations.MAID_HNS,
				MaidHeadbandAndSkirtModel::createBodyLayer
		);
		event.registerLayerDefinition(
				VWLayerLocations.JK_UNIFORM_MAIN,
				() -> LayerDefinition.create(HumanoidArmorModel.createBodyLayer(new CubeDeformation(VWClientConfig.UNIFORM_INNER_GROWTH.get().floatValue())), 64, 32)
		);
		event.registerLayerDefinition(
				VWLayerLocations.JK_UNIFORM_OUTER,
				JKUniformModel::createBodyLayer
		);
		event.registerLayerDefinition(
				VWLayerLocations.THINKING_HAT,
				ThinkingHatModel::createBodyLayer
		);
	}

	/**
	 * 客户端设置事件处理，注册 Curios 渲染器喵~
	 *
	 * @param event 客户端设置事件喵~
	 */
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			if(ModList.get().isLoaded("curios")) {
				VWCuriosRenderers.registerRenderers();
			}
		});
	}

	private VWClient() {
	}
}
