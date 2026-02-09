package com.hexagram2021.violas_wardrobe.mixin;

import com.hexagram2021.violas_wardrobe.common.registries.VWItemTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CustomHeadLayer.class)
@SuppressWarnings("java:S100")
public class CustomHeadLayerMixin {
	@WrapOperation(
			method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V")
	)
	private void violas_wardrobe$ignoreHeadband(ItemInHandRenderer instance, LivingEntity entity, ItemStack itemStack, ItemDisplayContext displayContext, boolean leftHand,
												PoseStack transform, MultiBufferSource buffer, int seed, Operation<Void> original) {
		if(!itemStack.is(VWItemTags.IGNORED_IN_HEAD_LAYER)) {
			original.call(instance, entity, itemStack, displayContext, leftHand, transform, buffer, seed);
		}
	}
}
