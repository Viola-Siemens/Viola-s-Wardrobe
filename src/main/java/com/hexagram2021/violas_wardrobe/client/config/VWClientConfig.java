package com.hexagram2021.violas_wardrobe.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * 模组客户端配置类喵~
 *
 * @author liudongyu
 */
public final class VWClientConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec SPEC;

	/**
	 * 内层衣服的膨胀值喵~
	 */
	public static final ForgeConfigSpec.DoubleValue UNIFORM_INNER_GROWTH;

	public static ForgeConfigSpec getSpec() {
		return SPEC;
	}

	private VWClientConfig() {
	}

	static {
		BUILDER.push("violas_wardrobe-client");
		UNIFORM_INNER_GROWTH = BUILDER.comment("The scale of the inner clothes.")
				.defineInRange("UNIFORM_INNER_GROWTH", 0.125D, 0.0D, 1.0D);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}
