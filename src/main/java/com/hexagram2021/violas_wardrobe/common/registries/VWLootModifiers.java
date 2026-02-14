package com.hexagram2021.violas_wardrobe.common.registries;

import com.hexagram2021.violas_wardrobe.common.loot.VillageChestModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组战利品修改器注册表喵~
 *
 * @author liudongyu
 */
public final class VWLootModifiers {
	private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTER = DeferredRegister.create(
			ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID
	);
	public static final RegistryObject<Codec<VillageChestModifier>> VILLAGE_CHEST_MODIFIER = REGISTER.register(
			"village_chest_modifier", VillageChestModifier::factory
	);

	private VWLootModifiers() {}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
