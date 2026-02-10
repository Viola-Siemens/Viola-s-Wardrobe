package com.hexagram2021.violas_wardrobe.mixin;

import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.utils.MathUtils;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 生命实体注入，实现轻飘飘附魔效果喵~
 *
 * @author liudongyu
 */
@Mixin(LivingEntity.class)
@SuppressWarnings({"java:S100", "java:S116"})
public class LivingEntityMixin {
	@Unique
	private int violas_wardrobe$buoyantCheckTicks = 0;

	@Inject(method = "checkFallDamage", at = @At(value = "TAIL"))
	private void violas_wardrobe$checkBuoyant(double y, boolean onGround, BlockState blockState, BlockPos blockPos, CallbackInfo ci) {
		LivingEntity self = (LivingEntity)(Object)this;
		if(self.level().isClientSide) {
			return;
		}
		if(onGround || y >= 0.0D) {
			this.violas_wardrobe$buoyantCheckTicks = SharedConstants.TICKS_PER_SECOND;
		} else if(this.violas_wardrobe$buoyantCheckTicks > 0) {
			this.violas_wardrobe$buoyantCheckTicks -= 1;
		} else {
			int maxLevel = 0;
			for(ItemStack itemStack: self.getArmorSlots()) {
				maxLevel = Math.max(maxLevel, itemStack.getEnchantmentLevel(VWEnchantments.BUOYANT.get()));
			}
			int factor = 5 + maxLevel;
			this.violas_wardrobe$buoyantCheckTicks = 4 * SharedConstants.TICKS_PER_SECOND - 4 * maxLevel;
			if(maxLevel > 0 && self.fallDistance >= 6.0F - 2.0F * MathUtils.sigmoid(maxLevel - 1.0D)) {
				self.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 4 * factor));
				self.addEffect(new MobEffectInstance(MobEffects.LEVITATION, factor - 2));
			}
		}
	}
}
