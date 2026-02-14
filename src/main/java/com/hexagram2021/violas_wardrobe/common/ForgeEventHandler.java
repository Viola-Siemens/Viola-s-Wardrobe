package com.hexagram2021.violas_wardrobe.common;

import com.hexagram2021.violas_wardrobe.ViolasWardrobeForge;
import com.hexagram2021.violas_wardrobe.common.entities.ILuminaAffectable;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.utils.MathUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 游戏事件处理类，负责游戏阶段的事件监听和处理喵~
 *
 * @author liudongyu
 */
@Mod.EventBusSubscriber(modid = ViolasWardrobeForge.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeEventHandler {
	/**
	 * 玩家攻击事件处理，如果玩家穿戴流光附魔的衣服，攻击怪物时会随机让怪物切换索敌目标喵~
	 *
	 * @param event 玩家攻击事件喵~
	 */
	@SubscribeEvent
	public static void onPlayerAttack(AttackEntityEvent event) {
		Player player = event.getEntity();
		int maxLevel = player.getInventory().armor.stream()
				.mapToInt(itemStack -> itemStack.getEnchantmentLevel(VWEnchantments.LUMINA.get()))
				.max().orElse(0);
		if(maxLevel > 0 && event.getTarget() instanceof ILuminaAffectable monster &&
				player.getRandom().nextDouble() < MathUtils.sigmoid(maxLevel * 0.25D) - 0.5D) {
			monster.violas_wardrobe$setLuminaActivatedTick(event.getTarget().tickCount);
		}
	}

	/**
	 * 生命实体更新事件处理，如果生命实体穿着流光效果的衣服，则渲染星光粒子喵~
	 *
	 * @param event 生命实体更新事件喵~
	 */
	@SubscribeEvent
	public static void onLivingEntityTick(LivingEvent.LivingTickEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if(livingEntity.level().isClientSide) {
			int maxLevel = 0;
			for(ItemStack itemStack: livingEntity.getArmorSlots()) {
				maxLevel = Math.max(maxLevel, itemStack.getEnchantmentLevel(VWEnchantments.LUMINA.get()));
			}
			if(maxLevel > 0 && livingEntity.tickCount % (12 / maxLevel) == 0) {
				livingEntity.level().addParticle(
						ParticleTypes.WAX_OFF,
						livingEntity.getX() + livingEntity.getRandom().nextDouble() * 0.4D - 0.2D,
						livingEntity.getY(0.4D) + livingEntity.getRandom().nextDouble() * 0.4D - 0.2D,
						livingEntity.getZ() + livingEntity.getRandom().nextDouble() * 0.4D - 0.2D,
						0.0D, 0.0D, 0.0D
				);
			}
		}
	}

	private ForgeEventHandler() {
	}
}
