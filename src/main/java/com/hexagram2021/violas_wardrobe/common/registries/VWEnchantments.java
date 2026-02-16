package com.hexagram2021.violas_wardrobe.common.registries;

import com.hexagram2021.violas_wardrobe.common.enchantments.*;
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

	/** 轻飘飘附魔喵~ */
	public static final RegistryObject<BuoyantEnchantment> BUOYANT = REGISTER.register("buoyant", BuoyantEnchantment::new);

	/** 妩媚附魔喵~ */
	public static final RegistryObject<CharmingEnchantment> CHARMING = REGISTER.register("charming", CharmingEnchantment::new);

	/** 光合滋养附魔喵~ */
	public static final RegistryObject<DaylightBloomingEnchantment> DAYLIGHT_BLOOMING = REGISTER.register("daylight_blooming", DaylightBloomingEnchantment::new);

	/** 纤护附魔喵~ */
	public static final RegistryObject<LacewardEnchantment> LACEWARD = REGISTER.register("laceward", LacewardEnchantment::new);

	/** 步伐轻盈附魔喵~ */
	public static final RegistryObject<LightFootingEnchantment> LIGHT_FOOTING = REGISTER.register("light_footing", LightFootingEnchantment::new);

	/** 流光附魔喵~ */
	public static final RegistryObject<LuminaEnchantment> LUMINA = REGISTER.register("lumina", LuminaEnchantment::new);

	/** 芳踪附魔喵~ */
	public static final RegistryObject<SweatTracingEnchantment> SWEAT_TRACING = REGISTER.register("sweat_tracing", SweatTracingEnchantment::new);

	/** 动物亲和附魔喵~ */
	public static final RegistryObject<TamingEnchantment> TAMING = REGISTER.register("taming", TamingEnchantment::new);

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
