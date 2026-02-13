package com.hexagram2021.violas_wardrobe.mixin.charming;

import com.hexagram2021.violas_wardrobe.common.enchantments.CharmingEnchantment;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 村民实体 Mixin，实现妩媚附魔效果喵~
 *
 * @author liudongyu
 */
@Mixin(Villager.class)
@SuppressWarnings("java:S100")
public class VillagerEntityMixin {
	@ModifyExpressionValue(method = "updateSpecialPrices", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/Villager;getPlayerReputation(Lnet/minecraft/world/entity/player/Player;)I"))
	private int violas_wardrobe$modifyReputation(int original, Player player) {
		return original + CharmingEnchantment.getReputationBonus(player);
	}
}
