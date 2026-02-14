package com.hexagram2021.violas_wardrobe.client.layers;

import com.google.common.collect.Maps;
import com.hexagram2021.violas_wardrobe.client.models.ThinkingHatModel;
import com.hexagram2021.violas_wardrobe.common.items.BaseOutfitItem;
import com.hexagram2021.violas_wardrobe.common.items.ThinkingHatItem;
import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;

/**
 * 思考帽渲染层喵~
 *
 * @param <T> 生物实体类型喵~
 * @param <M> 父模型类型喵~
 * @param <O> 帽子模型类型喵~
 * @author liudongyu
 */
@SuppressWarnings("UnstableApiUsage")
public class ThinkingHatLayer<T extends LivingEntity, M extends HumanoidModel<T>, O extends ThinkingHatModel<T>> extends RenderLayer<T, M> {
	private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
	protected final O hatModel;

	/**
	 * 构造思考帽渲染层喵~
	 *
	 * @param renderer 渲染器父对象喵~
	 * @param hatModel 思考帽模型喵~
	 */
	public ThinkingHatLayer(RenderLayerParent<T, M> renderer, O hatModel) {
		super(renderer);
		this.hatModel = hatModel;
	}

	/**
	 * 渲染思考帽的各个部件喵~
	 *
	 * @param transform 姿态变换矩阵喵~
	 * @param buffer 多缓冲区源喵~
	 * @param packedLight 打包的光照值喵~
	 * @param entity 实体喵~
	 * @param limbSwing 肢体摆动喵~
	 * @param limbSwingAmount 肢体摆动幅度喵~
	 * @param partialTicks 部分刻喵~
	 * @param ageInTicks 年龄（刻）喵~
	 * @param netHeadYaw 头部偏航角喵~
	 * @param headPitch 头部俯仰角喵~
	 */
	@Override
	public void render(PoseStack transform, MultiBufferSource buffer, int packedLight, T entity,
					   float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);
		Item item = itemstack.getItem();
		if (item instanceof ThinkingHatItem thinkingHatItem && thinkingHatItem.getEquipmentSlot() == EquipmentSlot.HEAD) {
			this.getParentModel().copyPropertiesTo(this.hatModel);
			this.hatModel.setAllVisible(false);
			this.hatModel.head.visible = true;
			this.hatModel.hat.visible = true;
			Model outer = ForgeHooksClient.getArmorModel(entity, itemstack, EquipmentSlot.HEAD, this.hatModel);
			renderModel(transform, buffer, packedLight, outer, this.getOuterArmorResource(entity, itemstack, EquipmentSlot.HEAD, null));
		}
	}

	/**
	 * 渲染模型到缓冲区喵~
	 *
	 * @param transform 姿态变换矩阵喵~
	 * @param buffer 多缓冲区源喵~
	 * @param packedLight 打包的光照值喵~
	 * @param model 模型喵~
	 * @param armorResource 盔甲纹理资源位置喵~
	 */
	private static void renderModel(PoseStack transform, MultiBufferSource buffer, int packedLight, Model model, ResourceLocation armorResource) {
		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(armorResource));
		model.renderToBuffer(transform, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * 获取外层盔甲纹理资源位置，允许物品对纹理有更多控制喵~
	 *
	 * @param entity 穿着盔甲的实体喵~
	 * @param stack 盔甲物品堆叠喵~
	 * @param slot 物品所在的槽位 ID 喵~
	 * @param type 子类型，可以为 null 或 "overlay" 喵~
	 * @return 指向盔甲纹理的资源位置喵~
	 */
	public ResourceLocation getOuterArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
		BaseOutfitItem item;
		if(stack.getItem() instanceof BaseOutfitItem baseOutfitItem) {
			item = baseOutfitItem;
		} else {
			// Should NEVER reach...
			item = VWItems.MAID_DRESS.get();
		}
		String location = String.format(Locale.ROOT, item.getOuterTexture().toString());
		location = ForgeHooksClient.getArmorTexture(entity, stack, location, slot, type);
		return ARMOR_LOCATION_CACHE.computeIfAbsent(location, ResourceLocation::new);
	}
}
