package com.hexagram2021.violas_wardrobe.common.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BaseOutfitItem extends Item implements Equipable {
	private final EquipmentSlot slot;

	public BaseOutfitItem(EquipmentSlot slot, Properties properties) {
		super(properties);
		this.slot = slot;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int getEnchantmentValue() {
		return 10;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return this.swapWithEquipmentSlot(this, level, player, hand);
	}

	@Override
	public EquipmentSlot getEquipmentSlot() {
		return this.slot;
	}

	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack itemStack) {
		return this.slot;
	}
}
