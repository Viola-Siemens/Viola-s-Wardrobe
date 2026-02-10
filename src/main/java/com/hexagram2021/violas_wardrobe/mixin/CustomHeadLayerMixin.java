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

/**
 * 自定义头部图层 Mixin，忽略特定物品在头部图层的渲染喵~
 *
 * @author liudongyu
 */
@Mixin(CustomHeadLayer.class)
@SuppressWarnings("java:S100")
public class CustomHeadLayerMixin {
	/**
	 * 包装渲染物品方法，忽略在头部图层中被标记为忽略的物品（如女仆头饰）喵~
	 *
	 * @param instance 手持物品渲染器实例喵~
	 * @param entity 生物实体喵~
	 * @param itemStack 物品堆叠喵~
	 * @param displayContext 显示上下文喵~
	 * @param leftHand 是否为左手喵~
	 * @param transform 姿态变换矩阵喵~
	 * @param buffer 多缓冲区源喵~
	 * @param seed 种子喵~
	 * @param original 原始操作喵~
	 */
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
