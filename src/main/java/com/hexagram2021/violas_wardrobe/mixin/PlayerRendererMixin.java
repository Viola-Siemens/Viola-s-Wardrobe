package com.hexagram2021.violas_wardrobe.mixin;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.layers.MaidHeadbandAndSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 玩家渲染器 Mixin，为玩家添加女仆装渲染层喵~
 *
 * @author liudongyu
 */
@Mixin(PlayerRenderer.class)
@SuppressWarnings("java:S100")
public class PlayerRendererMixin {
	/**
	 * 在玩家渲染器构造方法末尾注入，添加女仆装模型图层喵~
	 *
	 * @param context 实体渲染器提供者上下文喵~
	 * @param useSlimModel 是否使用纤细模型喵~
	 * @param ci 回调信息喵~
	 */
	@Inject(method = "<init>", at = @At("TAIL"))
	private void violas_wardrobe$addModelLayers(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
		PlayerRenderer self = (PlayerRenderer)(Object)this;
		self.addLayer(new MaidHeadbandAndSkirtLayer<>(
				self,
				new HumanoidArmorModel<>(context.bakeLayer(VWLayerLocations.MAID_MAIN)),
				new MaidHeadbandAndSkirtModel<>(context.bakeLayer(VWLayerLocations.MAID_HNS))
		));
	}
}
