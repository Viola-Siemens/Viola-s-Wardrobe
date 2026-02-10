package com.hexagram2021.violas_wardrobe.common;

import com.hexagram2021.violas_wardrobe.ViolasWardrobeForge;
import net.minecraftforge.fml.common.Mod;

/**
 * 游戏事件处理类，负责游戏阶段的事件监听和处理喵~
 *
 * @author liudongyu
 */
@Mod.EventBusSubscriber(modid = ViolasWardrobeForge.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeEventHandler {
	private ForgeEventHandler() {
	}
}
