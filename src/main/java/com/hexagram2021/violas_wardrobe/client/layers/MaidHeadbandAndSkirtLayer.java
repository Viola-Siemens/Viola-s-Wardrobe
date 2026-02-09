package com.hexagram2021.violas_wardrobe.client.layers;

import com.google.common.collect.Maps;
import com.hexagram2021.violas_wardrobe.common.items.MaidOutfitItem;
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

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

@SuppressWarnings("UnstableApiUsage")
public class MaidHeadbandAndSkirtLayer<T extends LivingEntity, M extends HumanoidModel<T>, I extends HumanoidModel<T>, O extends HumanoidModel<T>> extends RenderLayer<T, M> {
	private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
	private final I innerModel;
	private final O outerModel;

	public MaidHeadbandAndSkirtLayer(RenderLayerParent<T, M> renderer, I innerModel, O outerModel) {
		super(renderer);
		this.innerModel = innerModel;
		this.outerModel = outerModel;
	}

	@Override
	public void render(PoseStack transform, MultiBufferSource buffer, int packedLight, T entity,
					   float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.CHEST, packedLight);
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.LEGS, packedLight);
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.FEET, packedLight);
		this.renderArmorPiece(transform, buffer, entity, EquipmentSlot.HEAD, packedLight);
	}

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

	private static void renderModel(PoseStack transform, MultiBufferSource buffer, int packedLight, Model model, ResourceLocation armorResource) {
		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.armorCutoutNoCull(armorResource));
		model.renderToBuffer(transform, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * More generic ForgeHook version, which allows for Items to have more control over what texture they provide.
	 *
	 * @param entity Entity wearing the armor
	 * @param stack ItemStack for the armor
	 * @param slot Slot ID that the item is in
	 * @param type Subtype, can be null or "overlay"
	 * @return ResourceLocation pointing at the armor's texture
	 */
	public ResourceLocation getInnerArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
		String location = String.format(Locale.ROOT, MODID + ":textures/models/maid/maid_outfit.png");
		location = ForgeHooksClient.getArmorTexture(entity, stack, location, slot, type);
		return ARMOR_LOCATION_CACHE.computeIfAbsent(location, ResourceLocation::new);
	}

	/**
	 * More generic ForgeHook version, which allows for Items to have more control over what texture they provide.
	 *
	 * @param entity Entity wearing the armor
	 * @param stack ItemStack for the armor
	 * @param slot Slot ID that the item is in
	 * @param type Subtype, can be null or "overlay"
	 * @return ResourceLocation pointing at the armor's texture
	 */
	public ResourceLocation getOuterArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
		String location = String.format(Locale.ROOT, MODID + ":textures/models/maid/maid_outfit_hns.png");
		location = ForgeHooksClient.getArmorTexture(entity, stack, location, slot, type);
		return ARMOR_LOCATION_CACHE.computeIfAbsent(location, ResourceLocation::new);
	}
}
