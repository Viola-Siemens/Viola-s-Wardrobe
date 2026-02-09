package com.hexagram2021.violas_wardrobe.common.registries;

import com.hexagram2021.violas_wardrobe.common.enchantments.TamingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

public final class VWEnchantments {
	public static final DeferredRegister<Enchantment> REGISTER = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

	public static final RegistryObject<Enchantment> TAMING = REGISTER.register("taming", TamingEnchantment::new);

	private VWEnchantments() {
	}

	/**
	 * Register the enchantments on mod constructing.
	 * @param modBus	the mod bus
	 */
	public static void init(IEventBus modBus) {
		REGISTER.register(modBus);
	}
}
