package com.hexagram2021.violas_wardrobe.common.items;

import com.hexagram2021.violas_wardrobe.common.items.curios.VWCuriosFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.fml.ModList;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 女仆装物品类，实现女仆装的特殊行为喵~
 *
 * @author liudongyu
 */
public class MaidOutfitItem extends BaseOutfitItem {
	/**
	 * 构造女仆装物品喵~
	 *
	 * @param slot 装备槽位喵~
	 * @param properties 物品属性喵~
	 */
	public MaidOutfitItem(EquipmentSlot slot, Properties properties) {
		super(slot, properties);
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
		return new ResourceLocation(MODID, "textures/models/maid/maid_outfit.png");
	}

	/**
	 * 获取装备的外层贴图喵~
	 *
	 * @return 装备的外层贴图喵~
	 */
	@Override
	public ResourceLocation getOuterTexture() {
		return new ResourceLocation(MODID, "textures/models/maid/maid_outfit_hns.png");
	}

	/**
	 * 创建女仆装物品实例，如果加载了 Curios 模组则创建 Curios 版本喵~
	 *
	 * @param slot 装备槽位喵~
	 * @return 女仆装物品实例喵~
	 */
	public static MaidOutfitItem of(EquipmentSlot slot) {
		if(ModList.get().isLoaded("curios")) {
			return VWCuriosFactory.maidOutfit(slot, OUTFIT_PROPERTIES);
		}
		return new MaidOutfitItem(slot, OUTFIT_PROPERTIES);
	}
}
