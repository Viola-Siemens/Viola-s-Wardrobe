package com.hexagram2021.violas_wardrobe.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

public final class VWItemTags {
	public static final TagKey<Item> IGNORED_IN_HEAD_LAYER = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "ignored_in_head_layer"));

	private VWItemTags() {
	}
}
