package com.hexagram2021.violas_wardrobe.mixin.taming;

import com.hexagram2021.violas_wardrobe.common.enchantments.TamingEnchantment;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 狼实体 Mixin，为狼的驯服过程应用动物亲和附魔效果喵~
 *
 * @author liudongyu
 */
@Mixin(Wolf.class)
@SuppressWarnings("java:S100")
public class WolfEntityMixin {
	/**
	 * 包装驯服概率计算，根据玩家装备的动物亲和附魔等级提高驯服成功率喵~
	 *
	 * @param instance 随机数生成器实例喵~
	 * @param bound 随机数上界喵~
	 * @param original 原始操作喵~
	 * @param player 玩家喵~
	 * @return 调整后的随机数结果喵~
	 */
	@WrapOperation(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
	private int violas_wardrobe$wrapTamingChance(RandomSource instance, int bound, Operation<Integer> original,
												 @Local(argsOnly = true) Player player) {
		return TamingEnchantment.wrapTamingChance(instance, bound, player, original.call(instance, bound));
	}
}
