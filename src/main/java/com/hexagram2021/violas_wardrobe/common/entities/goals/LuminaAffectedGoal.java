package com.hexagram2021.violas_wardrobe.common.entities.goals;

import com.hexagram2021.violas_wardrobe.common.entities.ILuminaAffectable;
import net.minecraft.Util;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;

import java.util.EnumSet;

/**
 * 受到流光效果影响的目标行为，使生物随机挑选一个周围的怪物进行索敌喵~
 *
 * @author liudongyu
 */
public class LuminaAffectedGoal extends TargetGoal {
	private static final TargetingConditions LUMINA_TARGETING_CONDITIONS = TargetingConditions.forCombat()
			.range(16.0D)
			.selector(Monster.class::isInstance)
			.ignoreLineOfSight()
			.ignoreInvisibilityTesting();

	/** 存储更新时间刻，用于判断目标是否需要改变喵~ */
	private int timestamp = -1;

	public LuminaAffectedGoal(Mob mob) {
		super(mob, true, true);
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	/**
	 * 以下条件是否满足喵？
	 * <br/>
	 * 1. 生物实体必须被流光效果干扰
	 * @return 是否满足可用条件喵~
	 */
	@Override
	public boolean canUse() {
		if(this.mob instanceof ILuminaAffectable luminaAffectable && luminaAffectable.violas_wardrobe$isLuminaAffected()) {
			return luminaAffectable.violas_wardrobe$getLuminaActivatedTick() != this.timestamp;
		}
		return false;
	}

	/**
	 * 满足条件后，将目标随机设置为周围的怪物喵~
	 */
	@Override
	public void start() {
		if(this.mob instanceof ILuminaAffectable luminaAffectable) {
			Util.getRandomSafe(this.mob.level().getNearbyEntities(
					Monster.class, LUMINA_TARGETING_CONDITIONS, this.mob,
					this.mob.getBoundingBox().inflate(12.0D, 8.0D, 12.0D)
			), this.mob.getRandom()).ifPresentOrElse(target -> {
				// 随机选择一个目标喵~
				this.mob.setTarget(target);
				this.targetMob = this.mob.getTarget();
				this.unseenMemoryTicks = 300;
			}, () -> {
				// 没有符合条件的目标喵~
				this.mob.setTarget(null);
				this.targetMob = null;
				luminaAffectable.violas_wardrobe$deactivateLumina();
			});
			this.timestamp = luminaAffectable.violas_wardrobe$getLuminaActivatedTick();
		}
		super.start();
	}
}
