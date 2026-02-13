package com.hexagram2021.violas_wardrobe.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组伤害类型标签喵~
 *
 * @author liudongyu
 */
public final class VWDamageTypeTags {
	/**
	 * 可被纤护附魔保护的伤害类型标签喵~
	 */
	public static final TagKey<DamageType> LACEWARD_PROTECT = TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID, "laceward_protect"));

	private VWDamageTypeTags() {
	}
}
