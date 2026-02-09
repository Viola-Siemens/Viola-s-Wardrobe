package com.hexagram2021.violas_wardrobe.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.hexagram2021.violas_wardrobe.ViolasWardrobeForge.MODID;

/**
 * Mod Layer Locations
 */
@OnlyIn(Dist.CLIENT)
public class VWLayerLocations {
	public static final ModelLayerLocation MAID_MAIN = new ModelLayerLocation(new ResourceLocation(MODID, "maid"), "armor");
	public static final ModelLayerLocation MAID_HNS = new ModelLayerLocation(new ResourceLocation(MODID, "maid"), "hns");

	private VWLayerLocations() {
	}
}
