package com.hexagram2021.violas_wardrobe.common.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 基础服装物品类，所有服装物品的基类喵~
 *
 * @author liudongyu
 */
public abstract class BaseOutfitItem extends Item implements Equipable {
	protected static final Properties OUTFIT_PROPERTIES = new Item.Properties().stacksTo(1);

	private final EquipmentSlot slot;

	/**
	 * 构造基础服装物品喵~
	 *
	 * @param slot 装备槽位喵~
	 * @param properties 物品属性喵~
	 */
	protected BaseOutfitItem(EquipmentSlot slot, Properties properties) {
		super(properties);
		this.slot = slot;
	}

	/**
	 * 返回物品的附魔能力值，通常基于材料决定喵~
	 *
	 * @return 附魔能力值，固定为 10 喵~
	 */
	@SuppressWarnings("deprecation")
	@Override
	public int getEnchantmentValue() {
		return 10;
	}

	/**
	 * 检查物品堆叠是否可附魔，仅当堆叠数为 1 时可附魔喵~
	 *
	 * @param itemStack 待检查的物品堆叠喵~
	 * @return 是否可附魔喵~
	 */
	@Override
	public boolean isEnchantable(ItemStack itemStack) {
		return itemStack.getCount() == 1;
	}

	/**
	 * 使用物品时的处理逻辑，将物品装备到对应槽位喵~
	 *
	 * @param level 世界喵~
	 * @param player 玩家喵~
	 * @param hand 使用的手喵~
	 * @return 交互结果喵~
	 */
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return this.swapWithEquipmentSlot(this, level, player, hand);
	}

	/**
	 * 获取装备槽位喵~
	 *
	 * @return 装备槽位喵~
	 */
	@Override
	public EquipmentSlot getEquipmentSlot() {
		return this.slot;
	}

	/**
	 * 获取物品堆叠对应的装备槽位喵~
	 *
	 * @param itemStack 物品堆叠喵~
	 * @return 装备槽位喵~
	 */
	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack itemStack) {
		return this.slot;
	}

	/**
	 * 获取装备的内层贴图喵~
	 *
	 * @return 装备的内层贴图喵~
	 */
	public abstract ResourceLocation getInnerTexture();

	/**
	 * 获取装备的外层贴图喵~
	 *
	 * @return 装备的外层贴图喵~
	 */
	public abstract ResourceLocation getOuterTexture();
}
