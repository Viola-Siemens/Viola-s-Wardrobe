package com.hexagram2021.violas_wardrobe.mixin;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.layers.JKSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.layers.MaidHeadbandAndSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.models.JKUniformModel;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 猪灵渲染器 Mixin，为猪灵添加女仆装、JK 制服渲染层喵~
 *
 * @author liudongyu
 */
@Mixin(PiglinRenderer.class)
@SuppressWarnings("java:S100")
public class PiglinRendererMixin {
	/**
	 * 在猪灵渲染器构造方法末尾注入，添加女仆装、JK 制服模型图层喵~
	 *
	 * @param context 实体渲染器提供者上下文喵~
	 * @param layerLocation 模型图层位置喵~
	 * @param armorInner 内层盔甲模型图层位置喵~
	 * @param armorOuter 外层盔甲模型图层位置喵~
	 * @param noRightEar 是否没有右耳喵~
	 * @param ci 回调信息喵~
	 */
	@Inject(method = "<init>", at = @At("TAIL"))
	private void violas_wardrobe$addModelLayers(EntityRendererProvider.Context context, ModelLayerLocation layerLocation,
												ModelLayerLocation armorInner, ModelLayerLocation armorOuter,
												boolean noRightEar, CallbackInfo ci) {
		PiglinRenderer self = (PiglinRenderer)(Object)this;
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