package com.hexagram2021.violas_wardrobe.mixin;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.layers.JKSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.layers.MaidHeadbandAndSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.models.JKUniformModel;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GiantMobRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 巨人渲染器 Mixin，为巨人添加女仆装、JK 制服渲染层喵~
 *
 * @author liudongyu
 */
@Mixin(GiantMobRenderer.class)
@SuppressWarnings("java:S100")
public class GiantMobRendererMixin {
	/**
	 * 在巨人渲染器构造方法末尾注入，添加女仆装、JK 制服模型图层喵~
	 *
	 * @param context 实体渲染器提供者上下文喵~
	 * @param scale 缩放比例喵~
	 * @param ci 回调信息喵~
	 */
	@Inject(method = "<init>", at = @At("TAIL"))
	private void violas_wardrobe$addModelLayers(EntityRendererProvider.Context context, float scale, CallbackInfo ci) {
		GiantMobRenderer self = (GiantMobRenderer)(Object)this;
		self.addLayer(new MaidHeadbandAndSkirtLayer<>(
				self,
				new HumanoidArmorModel<>(context.bakeLayer(VWLayerLocations.MAID_MAIN)),
				new MaidHeadbandAndSkirtModel<>(context.bakeLayer(VWLayerLocations.MAID_HNS))
		));
		self.addLayer(new JKSkirtLayer<>(
				self,
				new HumanoidArmorModel<>(context.bakeLayer(VWLayerLocations.JK_UNIFORM_MAIN)),
				new JKUniformModel<>(context.bakeLayer(VWLayerLocations.JK_UNIFORM_OUTER))
		));
	}
}