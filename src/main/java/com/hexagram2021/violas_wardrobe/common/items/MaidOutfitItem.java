package com.hexagram2021.violas_wardrobe.common.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class MaidOutfitItem extends BaseOutfitItem {
	public MaidOutfitItem(EquipmentSlot slot, Properties properties) {
		super(slot, properties);
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_LEATHER;
	}

	/**
	 * Checks if item stack is enchantable.
	 * @param itemStack item stack to be checked
	 */
	@Override
	public boolean isEnchantable(ItemStack itemStack) {
		return itemStack.getCount() == 1;
	}
}
