package com.hexagram2021.violas_wardrobe.mixin.light_footing;

import com.hexagram2021.violas_wardrobe.common.enchantments.LightFootingEnchantment;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 玩家实体 Mixin，实现步伐轻盈附魔效果喵~
 *
 * @author liudongyu
 */
@Mixin(Player.class)
@SuppressWarnings("java:S100")
public class PlayerEntityMixin {
	@WrapOperation(method = {"jumpFromGround", "checkMovementStatistics"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
	private void violas_wardrobe$jumpFromGround(Player self, float exhaustion, Operation<Void> original) {
		exhaustion = LightFootingEnchantment.wrapExhaustion(self, exhaustion);
		original.call(self, exhaustion);
	}
}
