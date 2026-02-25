package com.hexagram2021.violas_wardrobe.common;

import com.hexagram2021.violas_wardrobe.ViolasWardrobeForge;
import com.hexagram2021.violas_wardrobe.common.config.VWCommonConfig;
import com.hexagram2021.violas_wardrobe.common.entities.ILuminaAffectable;
import com.hexagram2021.violas_wardrobe.common.registries.VWEnchantments;
import com.hexagram2021.violas_wardrobe.common.registries.VWItems;
import com.hexagram2021.violas_wardrobe.common.utils.MathUtils;
import com.hexagram2021.violas_wardrobe.common.utils.SuitUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

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
		int maxLevel = SuitUtils.getMaxEnchantLevel(player, VWEnchantments.LUMINA.get());
		if(maxLevel > 0 && event.getTarget() instanceof ILuminaAffectable monster &&
				player.getRandom().nextDouble() < MathUtils.sigmoid(maxLevel * 0.25D) - 0.5D) {
			monster.violas_wardrobe$setLuminaActivatedTick(event.getTarget().tickCount);
		}
	}

	/**
	 * 生命实体更新事件处理喵~
	 * <ol>
	 * <li>如果生命实体穿着流光效果的衣服，则渲染星光粒子喵~</li>
	 * <li>如果生命实体穿着光合滋养效果的衣服，则添加生命恢复状态效果喵~</li>
	 * </ol>
	 *
	 * @param event 生命实体更新事件喵~
	 */
	@SubscribeEvent
	public static void onLivingEntityTick(LivingEvent.LivingTickEvent event) {
		LivingEntity livingEntity = event.getEntity();
		Level level = livingEntity.level();
		if(level.isClientSide) {
			// 检查流光条件，渲染星光粒子喵~
			int maxLevel = SuitUtils.getMaxEnchantLevel(livingEntity, VWEnchantments.LUMINA.get());
			if(maxLevel > 0 && livingEntity.tickCount % (24 / (maxLevel + 1)) == 0) {
				level.addParticle(
						ParticleTypes.WAX_OFF,
						livingEntity.getX() + livingEntity.getRandom().nextDouble() * 0.4D - 0.2D,
						livingEntity.getY(0.4D) + livingEntity.getRandom().nextDouble() * 0.4D - 0.2D,
						livingEntity.getZ() + livingEntity.getRandom().nextDouble() * 0.4D - 0.2D,
						0.0D, 0.0D, 0.0D
				);
			}
		} else {
			// 检查光合滋养条件，添加生命恢复状态效果喵~
			if(level.isDay()) {
				int maxLevel = SuitUtils.getMaxEnchantLevel(livingEntity, VWEnchantments.DAYLIGHT_BLOOMING.get());
				if (maxLevel > 0 && livingEntity.tickCount % (480 / (maxLevel + 1)) == 0 &&
						level.canSeeSky(BlockPos.containing(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ()))) {
					livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40 + (int) (80 * MathUtils.sigmoid(maxLevel - 1.0D)), 0, false, false));
				}
			}
		}
	}

	/**
	 * 实体加入世界事件处理，随机给一些怪物装备衣服喵~
	 * @param event 实体加入世界事件喵~
	 */
	@SubscribeEvent
	public static void onJoinLevel(EntityJoinLevelEvent event) {
		Entity entity = event.getEntity();
		RandomSource random = entity.level().getRandom();
		if(!event.getLevel().isClientSide && !event.loadedFromDisk() && entity instanceof Mob mob) {
			ResourceLocation entityType = ForgeRegistries.ENTITY_TYPES.getKey(mob.getType());
			if(entityType != null && VWCommonConfig.MOBS_SPAWN_WITH_CLOTHES.get().contains(entityType.toString())) {
				if(random.nextDouble() >= VWCommonConfig.POSSIBILITY_WITH_CLOTHES.get()) {
					return;
				}
				int kind = random.nextInt(5);
				switch (kind) {
					case 0 -> setMaidClothing(mob, random);
					case 1 -> setPurplishBlueJKUniform(mob, random);
					case 2 -> setCreamJKUniform(mob, random);
					case 3 -> setBlackJKUniform(mob, random);
					case 4 -> {
						// 思考帽喵~
						int hatType = random.nextInt(6);
						ItemStack hat;
						switch (hatType) {
							case 0 -> hat = new ItemStack(VWItems.BLUE_THINKING_HAT);
							case 1 -> hat = new ItemStack(VWItems.YELLOW_THINKING_HAT);
							case 2 -> hat = new ItemStack(VWItems.BLACK_THINKING_HAT);
							case 3 -> hat = new ItemStack(VWItems.RED_THINKING_HAT);
							case 4 -> hat = new ItemStack(VWItems.WHITE_THINKING_HAT);
							case 5 -> hat = new ItemStack(VWItems.GREEN_THINKING_HAT);
							default -> {
								return;
							}
						}
						mob.setItemSlot(EquipmentSlot.HEAD, hat);
						mob.setDropChance(EquipmentSlot.HEAD, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
					}
					default -> {
						// Do nothing.
					}
				}
			}
		}
	}

	/**
	 * 为怪物装备米色 JK 制服喵~
	 *
	 * @param mob 怪物实体喵~
	 * @param random 随机数生成器喵~
	 */
	private static void setCreamJKUniform(Mob mob, RandomSource random) {
		// 米色 JK 制服喵~
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.CHEST, new ItemStack(VWItems.JK_UNIFORM_CREAM_SUMMER));
			mob.setDropChance(EquipmentSlot.CHEST, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.LEGS, new ItemStack(VWItems.JK_UNIFORM_CREAM_SKIRT));
			mob.setDropChance(EquipmentSlot.LEGS, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.FEET, new ItemStack(VWItems.JK_UNIFORM_CREAM_SHOES));
			mob.setDropChance(EquipmentSlot.FEET, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
	}

	/**
	 * 为怪物装备黑色 JK 制服喵~
	 *
	 * @param mob 怪物实体喵~
	 * @param random 随机数生成器喵~
	 */
	private static void setBlackJKUniform(Mob mob, RandomSource random) {
		// 米色 JK 制服喵~
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.CHEST, new ItemStack(VWItems.JK_UNIFORM_BLACK_SUMMER));
			mob.setDropChance(EquipmentSlot.CHEST, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.LEGS, new ItemStack(VWItems.JK_UNIFORM_BLACK_SKIRT));
			mob.setDropChance(EquipmentSlot.LEGS, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.FEET, new ItemStack(VWItems.JK_UNIFORM_BLACK_SHOES));
			mob.setDropChance(EquipmentSlot.FEET, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
	}

	/**
	 * 为怪物装备藏青色 JK 制服喵~
	 *
	 * @param mob 怪物实体喵~
	 * @param random 随机数生成器喵~
	 */
	private static void setPurplishBlueJKUniform(Mob mob, RandomSource random) {
		// 藏青色 JK 制服喵~
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.CHEST, new ItemStack(VWItems.JK_UNIFORM_PURPLISH_BLUE_SUMMER));
			mob.setDropChance(EquipmentSlot.CHEST, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.LEGS, new ItemStack(VWItems.JK_UNIFORM_PURPLISH_BLUE_SKIRT));
			mob.setDropChance(EquipmentSlot.LEGS, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.FEET, new ItemStack(VWItems.JK_UNIFORM_PURPLISH_BLUE_SHOES));
			mob.setDropChance(EquipmentSlot.FEET, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
	}

	/**
	 * 为怪物装备女仆装喵~
	 *
	 * @param mob 怪物实体喵~
	 * @param random 随机数生成器喵~
	 */
	private static void setMaidClothing(Mob mob, RandomSource random) {
		// 女仆装喵~
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.HEAD, new ItemStack(VWItems.MAID_HEADBAND));
			mob.setDropChance(EquipmentSlot.HEAD, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			mob.setItemSlot(EquipmentSlot.CHEST, new ItemStack(VWItems.MAID_DRESS));
			mob.setDropChance(EquipmentSlot.CHEST, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
		}
		if(random.nextBoolean()) {
			int thighHighsType = random.nextInt(2);
			if(thighHighsType == 0) {
				mob.setItemSlot(EquipmentSlot.FEET, new ItemStack(VWItems.MAID_THIGH_HIGHS));
				mob.setDropChance(EquipmentSlot.FEET, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
			} else {
				mob.setItemSlot(EquipmentSlot.FEET, new ItemStack(VWItems.MAID_BLACK_THIGH_HIGHS));
				mob.setDropChance(EquipmentSlot.FEET, VWCommonConfig.CLOTHES_DROP_CHANCE.get().floatValue());
			}
		}
	}

	private ForgeEventHandler() {
	}
}
