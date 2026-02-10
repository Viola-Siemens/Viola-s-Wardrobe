package com.hexagram2021.violas_wardrobe.common.registries;

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
	/**
	 * 附魔延迟注册器喵~
	 */
	public static final DeferredRegister<Enchantment> REGISTER = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

	/**
	 * 动物亲和附魔喵~
	 */
	public static final RegistryObject<Enchantment> TAMING = REGISTER.register("taming", TamingEnchantment::new);

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
