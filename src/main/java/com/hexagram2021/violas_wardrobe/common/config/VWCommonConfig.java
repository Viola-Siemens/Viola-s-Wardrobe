package com.hexagram2021.violas_wardrobe.common.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

/**
 * 模组通用配置类喵~
 *
 * @author liudongyu
 */
public final class VWCommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec SPEC;

	/**
	 * 生成时可以自带衣服的怪物喵~
	 */
	@SuppressWarnings("java:S4968")
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> MOBS_SPAWN_WITH_CLOTHES;
	/**
	 * 自带衣服的概率喵~
	 */
	public static final ForgeConfigSpec.DoubleValue POSSIBILITY_WITH_CLOTHES;
	/**
	 * 杀死怪物衣服的概率喵~
	 */
	public static final ForgeConfigSpec.DoubleValue CLOTHES_DROP_CHANCE;
	/**
	 * 村庄箱子战利品出现衣服的概率喵~
	 */
	public static final ForgeConfigSpec.DoubleValue VILLAGE_CHEST_CHANCE;

	public static ForgeConfigSpec getSpec() {
		return SPEC;
	}

	private VWCommonConfig() {
	}

	static {
		BUILDER.push("violas_wardrobe-client");
		MOBS_SPAWN_WITH_CLOTHES = BUILDER.comment("Entity types that may spawn with mod clothes.")
				.defineListAllowEmpty("MOBS_SPAWN_WITH_CLOTHES", () -> List.of(
						new ResourceLocation("zombie").toString(),
						new ResourceLocation("skeleton").toString(),
						new ResourceLocation("husk").toString(),
						new ResourceLocation("zombie_villager").toString()
				), o -> o instanceof String str && ResourceLocation.isValidResourceLocation(str));
		POSSIBILITY_WITH_CLOTHES = BUILDER.comment("The possibility of spawning with clothes.")
				.defineInRange("POSSIBILITY_WITH_CLOTHES", 0.025D, 0.0D, 1.0D);
		CLOTHES_DROP_CHANCE = BUILDER.comment("The possibility of clothes drop.")
				.defineInRange("CLOTHES_DROP_CHANCE", 0.085D, 0.0D, 1.0D);
		VILLAGE_CHEST_CHANCE = BUILDER.comment("The possibility of clothes show up in villagers' chests.")
				.defineInRange("VILLAGE_CHEST_CHANCE", 0.375D, 0.0D, 1.0D);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}
