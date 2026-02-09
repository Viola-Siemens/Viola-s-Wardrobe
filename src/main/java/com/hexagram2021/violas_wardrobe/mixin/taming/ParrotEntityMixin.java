package com.hexagram2021.violas_wardrobe.mixin.taming;

import com.hexagram2021.violas_wardrobe.common.enchantments.TamingEnchantment;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Parrot.class)
@SuppressWarnings("java:S100")
public class ParrotEntityMixin {
	@WrapOperation(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
	private int violas_wardrobe$wrapTamingChance(RandomSource instance, int bound, Operation<Integer> original,
												 @Local(argsOnly = true) Player player) {
		return TamingEnchantment.wrapTamingChance(instance, bound, player, original.call(instance, bound));
	}
}
