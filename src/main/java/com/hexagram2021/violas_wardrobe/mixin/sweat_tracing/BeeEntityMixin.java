package com.hexagram2021.violas_wardrobe.mixin.sweat_tracing;

import com.hexagram2021.violas_wardrobe.common.enchantments.SweatTracingEnchantment;
import net.minecraft.world.entity.animal.Bee;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 蜜蜂实体 Mixin，实现芳踪附魔效果喵~
 *
 * @author liudongyu
 */
@Mixin(Bee.class)
@SuppressWarnings("java:S100")
public class BeeEntityMixin {
	@Shadow
	Bee.BeePollinateGoal beePollinateGoal;

	@Inject(method = "tick", at = @At(value = "TAIL"))
	private void violas_wardrobe$tick(CallbackInfo ci) {
		Bee self = (Bee)(Object)this;
		if(!self.level().isClientSide && self.tickCount % 20 == 0 && this.beePollinateGoal.isPollinating()) {
			int bonus = SweatTracingEnchantment.getPollinatingTicksBonus(self);
			if(bonus > 0) {
				if(bonus > 10) {
					bonus = 10;
				}
				this.beePollinateGoal.pollinatingTicks += bonus * 2;
				this.beePollinateGoal.successfulPollinatingTicks += bonus * 3 - 1;
			}
		}
	}
}
