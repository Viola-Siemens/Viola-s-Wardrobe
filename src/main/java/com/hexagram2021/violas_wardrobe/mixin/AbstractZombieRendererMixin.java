package com.hexagram2021.violas_wardrobe.mixin;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.layers.JKSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.layers.MaidHeadbandAndSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.layers.ThinkingHatLayer;
import com.hexagram2021.violas_wardrobe.client.models.JKUniformModel;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import com.hexagram2021.violas_wardrobe.client.models.ThinkingHatModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 僵尸渲染器 Mixin，为僵尸添加女仆装、JK 制服渲染层喵~
 *
 * @param <T> 僵尸实体类型喵~
 * @param <M> 僵尸模型类型喵~
 * @author liudongyu
 */
@Mixin(AbstractZombieRenderer.class)
@SuppressWarnings({"unchecked", "java:S100"})
public class AbstractZombieRendererMixin<T extends Zombie, M extends ZombieModel<T>> {
	/**
	 * 在僵尸渲染器构造方法末尾注入，添加女仆装、JK 制服模型图层喵~
	 *
	 * @param context 实体渲染器提供者上下文喵~
	 * @param model 模型喵~
	 * @param innerModel 内层模型喵~
	 * @param outerModel 外层模型喵~
	 * @param ci 回调信息喵~
	 */
	@Inject(method = "<init>", at = @At("TAIL"))
	private void violas_wardrobe$addModelLayers(EntityRendererProvider.Context context, M model,
												M innerModel, M outerModel, CallbackInfo ci) {
		AbstractZombieRenderer<T, M> self = (AbstractZombieRenderer<T, M>)(Object)this;
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
		self.addLayer(new ThinkingHatLayer<>(
				self,
				new ThinkingHatModel<>(context.bakeLayer(VWLayerLocations.THINKING_HAT))
		));
	}
}
