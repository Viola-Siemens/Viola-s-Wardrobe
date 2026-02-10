package com.hexagram2021.violas_wardrobe.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * 模组图层位置定义喵~
 *
 * @author liudongyu
 */
@OnlyIn(Dist.CLIENT)
public class VWLayerLocations {
	/**
	 * 女仆装主体盔甲模型图层位置喵~
	 */
	public static final ModelLayerLocation MAID_MAIN = new ModelLayerLocation(new ResourceLocation(MODID, "maid"), "armor");
	/**
	 * 女仆头饰和裙子模型图层位置喵~
	 */
	public static final ModelLayerLocation MAID_HNS = new ModelLayerLocation(new ResourceLocation(MODID, "maid"), "hns");

	private VWLayerLocations() {
	}
}
