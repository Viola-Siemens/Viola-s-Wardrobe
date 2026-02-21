package com.hexagram2021.violas_wardrobe.client.compat.curios;

import com.hexagram2021.violas_wardrobe.common.items.curios.VWCuriosItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.List;

/**
 * Viola's Wardrobe 模组的 Curios 渲染器，负责渲染服装物品在 Curios 槽位中的显示效果喵~
 *
 * @param models 用于渲染的人形模型列表喵~
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public record ViolasWardrobeCuriosRenderer(List<ModelWithTexture> models) implements ICurioRenderer {
	/**
	 * 渲染 Curios 物品，使模型跟随实体的身体旋转喵~
	 *
	 * @param stack 物品堆叠喵~
	 * @param slotContext 槽位上下文喵~
	 * @param matrixStack 矩阵堆栈喵~
	 * @param renderLayerParent 渲染层父对象喵~
	 * @param renderTypeBuffer 渲染类型缓冲区喵~
	 * @param light 光照等级喵~
	 * @param limbSwing 肢体摆动喵~
	 * @param limbSwingAmount 肢体摆动幅度喵~
	 * @param partialTicks 部分刻喵~
	 * @param ageInTicks 实体年龄（刻）喵~
	 * @param netHeadYaw 头部偏航角喵~
	 * @param headPitch 头部俯仰角喵~
	 */
	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		Item item = stack.getItem();
		if(item instanceof VWCuriosItem) {
			for (ModelWithTexture model : this.models) {
				ICurioRenderer.followBodyRotations(slotContext.entity(), model.model());
				VertexConsumer vertexConsumer = renderTypeBuffer.getBuffer(RenderType.entityCutout(model.texture()));
				model.model().renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}
		}
	}

	/**
	 * 模型与贴图的配对记录，用于在 Curios 渲染中关联模型和对应的纹理资源喵~
	 *
	 * @param model 人形模型喵~
	 * @param texture 纹理资源位置喵~
	 * @author liudongyu
	 */
	@OnlyIn(Dist.CLIENT)
	public record ModelWithTexture(HumanoidModel<LivingEntity> model, ResourceLocation texture) {
	}
}
