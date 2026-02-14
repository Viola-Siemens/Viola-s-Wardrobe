package com.hexagram2021.violas_wardrobe.common.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.Locale;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 思考帽物品类，实现思考帽的特殊行为喵~
 *
 * @author liudongyu
 */
public class ThinkingHatItem extends BaseOutfitItem {
	private final Variant variant;

	/**
	 * 构造思考帽物品喵~
	 *
	 * @param variant 颜色变体喵~
	 * @param properties 物品属性喵~
	 */
	public ThinkingHatItem(Variant variant, Properties properties) {
		super(EquipmentSlot.HEAD, properties);
		this.variant = variant;
	}

	/**
	 * 获取装备时的音效，使用皮革盔甲的装备音效喵~
	 *
	 * @return 装备音效喵~
	 */
	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_LEATHER;
	}

	/**
	 * 获取装备的内层贴图喵~
	 *
	 * @return 装备的内层贴图喵~
	 */
	@Override
	public ResourceLocation getInnerTexture() {
		return new ResourceLocation(MODID, "textures/models/thinking_hat/" + this.variantName() + ".png");
	}

	/**
	 * 获取装备的外层贴图喵~
	 *
	 * @return 装备的外层贴图喵~
	 */
	@Override
	public ResourceLocation getOuterTexture() {
		return new ResourceLocation(MODID, "textures/models/thinking_hat/" + this.variantName() + ".png");
	}

	public String variantName() {
		return this.variant.name().toLowerCase(Locale.ROOT);
	}

	public enum Variant {
		/**
		 * 蓝色喵~
		 */
		BLUE,
		/**
		 * 黄色喵~
		 */
		YELLOW,
		/**
		 * 黑色喵~
		 */
		BLACK,
		/**
		 * 红色喵~
		 */
		RED,
		/**
		 * 白色喵~
		 */
		WHITE,
		/**
		 * 绿色喵~
		 */
		GREEN
	}
}
