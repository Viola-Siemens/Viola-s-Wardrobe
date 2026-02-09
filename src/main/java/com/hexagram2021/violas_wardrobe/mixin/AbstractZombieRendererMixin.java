package com.hexagram2021.violas_wardrobe.mixin;

import com.hexagram2021.violas_wardrobe.client.VWLayerLocations;
import com.hexagram2021.violas_wardrobe.client.layers.MaidHeadbandAndSkirtLayer;
import com.hexagram2021.violas_wardrobe.client.models.MaidHeadbandAndSkirtModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractZombieRenderer.class)
@SuppressWarnings({"unchecked", "java:S100"})
public class AbstractZombieRendererMixin<T extends Zombie, M extends ZombieModel<T>> {
	@Inject(method = "<init>", at = @At("TAIL"))
	private void violas_wardrobe$addModelLayers(EntityRendererProvider.Context context, M model,
												M innerModel, M outerModel, CallbackInfo ci) {
		AbstractZombieRenderer<T, M> self = (AbstractZombieRenderer<T, M>)(Object)this;
		self.addLayer(new MaidHeadbandAndSkirtLayer<>(
				self,
				new HumanoidArmorModel<>(context.bakeLayer(VWLayerLocations.MAID_MAIN)),
				new MaidHeadbandAndSkirtModel<>(context.bakeLayer(VWLayerLocations.MAID_HNS))
		));
	}
}
