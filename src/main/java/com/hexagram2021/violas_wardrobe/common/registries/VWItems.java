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
 * Mod Items
 * @author liudongyu
 */
public final class VWItems {
	private static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static final ItemEntry<MaidOutfitItem> MAID_HEADBAND = ItemEntry.register(
			"maid_headband", () -> new MaidOutfitItem(EquipmentSlot.HEAD, new Item.Properties().stacksTo(1))
	);
	public static final ItemEntry<MaidOutfitItem> MAID_DRESS = ItemEntry.register(
			"maid_dress", () -> new MaidOutfitItem(EquipmentSlot.CHEST, new Item.Properties().stacksTo(1))
	);
	public static final ItemEntry<MaidOutfitItem> MAID_THIGH_HIGHS = ItemEntry.register(
			"maid_thigh_highs", () -> new MaidOutfitItem(EquipmentSlot.FEET, new Item.Properties().stacksTo(1))
	);

	private VWItems() {
	}

	/**
	 * Register the items on mod constructing.
	 * @param modBus	the mod bus
	 */
	public static void init(IEventBus modBus) {
		REGISTER.register(modBus);
	}

	public record ItemEntry<T extends Item>(RegistryObject<T> item) implements Supplier<T>, ItemLike {
		private static final Set<ItemEntry<?>> ITEMS = Sets.newIdentityHashSet();

		private static <T extends Item> ItemEntry<T> register(String name, Supplier<? extends T> make) {
			ItemEntry<T> item = new ItemEntry<>(REGISTER.register(name, make));
			ITEMS.add(item);
			return item;
		}

		@Override
		public T get() {
			return this.item.get();
		}

		@Override
		public Item asItem() {
			return this.item.get();
		}

		static Set<ItemEntry<?>> getItems() {
			return Collections.unmodifiableSet(ITEMS);
		}
	}
}
