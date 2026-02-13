package com.hexagram2021.violas_wardrobe.common.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.Locale;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * JK 制服物品类，实现 JK 制服的特殊行为喵~
 *
 * @author liudongyu
 */
public class JKUniformItem extends BaseOutfitItem {
	private final Variant variant;

	/**
	 * 构造 JK 制服物品喵~
	 *
	 * @param slot 装备槽位喵~
	 * @param properties 物品属性喵~
	 */
	public JKUniformItem(Variant variant, EquipmentSlot slot, Properties properties) {
		super(slot, properties);
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
		return new ResourceLocation(MODID, "textures/models/jk_uniform/jk_" + this.variantName() + "_inner.png");
	}

	/**
	 * 获取装备的外层贴图喵~
	 *
	 * @return 装备的外层贴图喵~
	 */
	@Override
	public ResourceLocation getOuterTexture() {
		return new ResourceLocation(MODID, "textures/models/jk_uniform/jk_" + this.variantName() + "_outer.png");
	}

	public String variantName() {
		return this.variant.name().toLowerCase(Locale.ROOT);
	}

	public enum Variant {
		/**
		 * 藏青色喵~
		 */
		PURPLISH_BLUE,
		/**
		 * 米色喵~
		 */
		CREAM
	}
}
