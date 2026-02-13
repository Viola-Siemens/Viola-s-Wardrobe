package com.hexagram2021.violas_wardrobe.client.layers;

import com.google.common.collect.Maps;
import com.hexagram2021.violas_wardrobe.common.items.BaseOutfitItem;
import com.hexagram2021.violas_wardrobe.common.items.MaidOutfitItem;
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
 * 模组各种带裙子套装的渲染层，可以渲染：
 * <ol>
 * <li>女仆装的头饰、裙子和袖子部分喵~</li>
 * <li>JK 制服的领带、裙子和鞋子部分喵~</li>
 * </ol>
 *
 * @param <T> 生物实体类型喵~
 * @param <M> 父模型类型喵~
 * @param <I> 内层模型类型喵~
 * @param <O> 外层模型类型喵~
 * @author liudongyu
 */
@SuppressWarnings("UnstableApiUsage")
public class ViolasWardrobeSkirtLayer<T extends LivingEntity, M extends HumanoidModel<T>, I extends HumanoidModel<T>, O extends HumanoidModel<T>> extends RenderLayer<T, M> {
	private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
	private final I innerModel;
	private final O outerModel;

	/**
	 * 构造女仆头饰和裙子渲染层喵~
	 *
	 * @param renderer 渲染器父对象喵~
	 * @param innerModel 内层模型喵~
	 * @param outerModel 外层模型喵~
	 */
	public ViolasWardrobeSkirtLayer(RenderLayerParent<T, M> renderer, I innerModel, O outerModel) {
		super(renderer);
		this.innerModel = innerModel;
		this.outerModel = outerModel;
	}

	/**
	 * 渲染女仆装的各个部件喵~
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
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.CHEST, packedLight);
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.LEGS, packedLight);
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.FEET, packedLight);
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.HEAD, packedLight);
	}

	/**
	 * 渲染指定槽位的盔甲部件喵~
	 *
	 * @param transform 姿态变换矩阵喵~
	 * @param buffer 多缓冲区源喵~
	 * @param entity 实体喵~
	 * @param slot 装备槽位喵~
	 * @param packedLight 打包的光照值喵~
	 */
	private void renderArmorPiece(PoseStack transform, MultiBufferSource buffer, T entity, EquipmentSlot slot, int packedLight) {
		ItemStack itemstack = entity.getItemBySlot(slot);
		Item item = itemstack.getItem();
		if (item instanceof MaidOutfitItem maidOutfitItem && maidOutfitItem.getEquipmentSlot() == slot) {
			this.getParentModel().copyPropertiesTo(this.innerModel);
			this.getParentModel().copyPropertiesTo(this.outerModel);
			setPartVisibility(this.innerModel, slot);
			setPartVisibility(this.outerModel, slot);
			Model inner = ForgeHooksClient.getArmorModel(entity, itemstack, slot, this.innerModel);
			renderModel(transform, buffer, packedLight, inner, this.getInnerArmorResource(entity, itemstack, slot, null));
			Model outer = ForgeHooksClient.getArmorModel(entity, itemstack, slot, this.outerModel);
			renderModel(transform, buffer, packedLight, outer, this.getOuterArmorResource(entity, itemstack, slot, null));

			// Do we really need renderGlint?
		}
	}

	/**
	 * 根据装备槽位设置模型部件的可见性喵~
	 *
	 * @param <T> 生物实体类型喵~
	 * @param model 人形模型喵~
	 * @param slot 装备槽位喵~
	 */
	protected static <T extends LivingEntity> void setPartVisibility(HumanoidModel<T> model, EquipmentSlot slot) {
		model.setAllVisible(false);
		switch (slot) {
			case HEAD -> {
				model.head.visible = true;
				model.hat.visible = true;
			}
			case CHEST -> {
				model.body.visible = true;
				model.rightArm.visible = true;
				model.leftArm.visible = true;
			}
			case LEGS -> {
				model.body.visible = true;
				model.rightLeg.visible = true;
				model.leftLeg.visible = true;
			}
			case FEET -> {
				model.rightLeg.visible = true;
				model.leftLeg.visible = true;
			}
			default -> throw new IllegalArgumentException("Unexpected slot: " + slot);
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
	 * 获取内层盔甲纹理资源位置，允许物品对纹理有更多控制喵~
	 *
	 * @param entity 穿着盔甲的实体喵~
	 * @param stack 盔甲物品堆叠喵~
	 * @param slot 物品所在的槽位 ID 喵~
	 * @param type 子类型，可以为 null 或 "overlay" 喵~
	 * @return 指向盔甲纹理的资源位置喵~
	 */
	public ResourceLocation getInnerArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
		BaseOutfitItem item;
		if(stack.getItem() instanceof BaseOutfitItem baseOutfitItem) {
			item = baseOutfitItem;
		} else {
			// Should NEVER reach...
			item = VWItems.MAID_DRESS.get();
		}
		String location = String.format(Locale.ROOT, item.getInnerTexture().toString());
		location = ForgeHooksClient.getArmorTexture(entity, stack, location, slot, type);
		return ARMOR_LOCATION_CACHE.computeIfAbsent(location, ResourceLocation::new);
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
