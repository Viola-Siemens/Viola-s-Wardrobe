package com.hexagram2021.violas_wardrobe.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组物品标签喵~
 *
 * @author liudongyu
 */
public final class VWItemTags {
	/**
	 * 在头部图层中被忽略的物品标签喵~
	 */
	public static final TagKey<Item> IGNORED_IN_HEAD_LAYER = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "ignored_in_head_layer"));

	private VWItemTags() {
	}
}
