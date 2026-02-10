package com.hexagram2021.violas_wardrobe.common.registries;

import com.hexagram2021.violas_wardrobe.common.enchantments.BuoyantEnchantment;
import com.hexagram2021.violas_wardrobe.common.enchantments.TamingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组附魔注册表喵~
 *
 * @author liudongyu
 */
public final class VWEnchantments {
	private static final DeferredRegister<Enchantment> REGISTER = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

	/** 动物亲和附魔喵~ */
	public static final RegistryObject<TamingEnchantment> TAMING = REGISTER.register("taming", TamingEnchantment::new);

	/** 轻飘飘附魔喵~ */
	public static final RegistryObject<BuoyantEnchantment> BUOYANT = REGISTER.register("buoyant", BuoyantEnchantment::new);

	private VWEnchantments() {
	}

	/**
	 * 模组构造时注册附魔喵~
	 *
	 * @param modBus 模组事件总线喵~
	 */
	public static void init(IEventBus modBus) {
		REGISTER.register(modBus);
	}
}
