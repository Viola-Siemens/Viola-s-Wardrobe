package com.hexagram2021.violas_wardrobe.common.registries;

import com.google.common.collect.Sets;
import com.hexagram2021.violas_wardrobe.common.items.MaidOutfitItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组物品注册表喵~
 *
 * @author liudongyu
 */
public final class VWItems {
	private static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	/**
	 * 女仆头饰，装备在头部槽位喵~
	 */
	public static final ItemEntry<MaidOutfitItem> MAID_HEADBAND = ItemEntry.register(
			"maid_headband", () -> new MaidOutfitItem(EquipmentSlot.HEAD, new Item.Properties().stacksTo(1))
	);
	/**
	 * 女仆裙子，装备在胸部槽位喵~
	 */
	public static final ItemEntry<MaidOutfitItem> MAID_DRESS = ItemEntry.register(
			"maid_dress", () -> new MaidOutfitItem(EquipmentSlot.CHEST, new Item.Properties().stacksTo(1))
	);
	/**
	 * 女仆长筒袜，装备在脚部槽位喵~
	 */
	public static final ItemEntry<MaidOutfitItem> MAID_THIGH_HIGHS = ItemEntry.register(
			"maid_thigh_highs", () -> new MaidOutfitItem(EquipmentSlot.FEET, new Item.Properties().stacksTo(1))
	);

	private VWItems() {
	}

	/**
	 * 模组构造时注册物品喵~
	 *
	 * @param modBus 模组事件总线喵~
	 */
	public static void init(IEventBus modBus) {
		REGISTER.register(modBus);
	}

	/**
	 * 物品条目记录类，包装注册对象并提供便捷访问喵~
	 *
	 * @param <T> 物品类型喵~
	 * @param item 注册对象喵~
	 */
	public record ItemEntry<T extends Item>(RegistryObject<T> item) implements Supplier<T>, ItemLike {
		private static final Set<ItemEntry<?>> ITEMS = Sets.newIdentityHashSet();

		/**
		 * 注册物品并创建物品条目喵~
		 *
		 * @param <T> 物品类型喵~
		 * @param name 物品注册名喵~
		 * @param make 物品构造器喵~
		 * @return 物品条目喵~
		 */
		private static <T extends Item> ItemEntry<T> register(String name, Supplier<? extends T> make) {
			ItemEntry<T> item = new ItemEntry<>(REGISTER.register(name, make));
			ITEMS.add(item);
			return item;
		}

		/**
		 * 获取物品实例喵~
		 *
		 * @return 物品实例喵~
		 */
		@Override
		public T get() {
			return this.item.get();
		}

		/**
		 * 将物品条目转换为物品喵~
		 *
		 * @return 物品实例喵~
		 */
		@Override
		public Item asItem() {
			return this.item.get();
		}

		/**
		 * 获取所有已注册的物品条目喵~
		 *
		 * @return 不可修改的物品条目集合喵~
		 */
		static Set<ItemEntry<?>> getItems() {
			return Collections.unmodifiableSet(ITEMS);
		}
	}
}
