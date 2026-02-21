package com.hexagram2021.violas_wardrobe.common.items;

import com.hexagram2021.violas_wardrobe.common.items.curios.VWCuriosFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.fml.ModList;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 黑色女仆装物品类，实现黑色女仆装的特殊行为喵~
 *
 * @author liudongyu
 */
public class BlackMaidOutfitItem extends MaidOutfitItem {
	/**
	 * 构造黑色女仆装物品喵~
	 *
	 * @param slot 装备槽位喵~
	 * @param properties 物品属性喵~
	 */
	public BlackMaidOutfitItem(EquipmentSlot slot, Properties properties) {
		super(slot, properties);
	}

	/**
	 * 获取装备的内层贴图喵~
	 *
	 * @return 装备的内层贴图喵~
	 */
	@Override
	public ResourceLocation getInnerTexture() {
		return new ResourceLocation(MODID, "textures/models/maid/maid_black_outfit.png");
	}

	/**
	 * 获取装备的外层贴图喵~
	 *
	 * @return 装备的外层贴图喵~
	 */
	@Override
	public ResourceLocation getOuterTexture() {
		return new ResourceLocation(MODID, "textures/models/maid/maid_black_outfit_hns.png");
	}

	/**
	 * 创建黑色女仆装物品实例，如果加载了 Curios 模组则创建 Curios 版本喵~
	 *
	 * @param slot 装备槽位喵~
	 * @return 黑色女仆装物品实例喵~
	 */
	public static BlackMaidOutfitItem of(EquipmentSlot slot) {
		if(ModList.get().isLoaded("curios")) {
			return VWCuriosFactory.blackMaidOutfit(slot, OUTFIT_PROPERTIES);
		}
		return new BlackMaidOutfitItem(slot, OUTFIT_PROPERTIES);
	}
}
