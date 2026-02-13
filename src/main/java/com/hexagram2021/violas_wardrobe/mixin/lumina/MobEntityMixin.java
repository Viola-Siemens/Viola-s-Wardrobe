package com.hexagram2021.violas_wardrobe.mixin.lumina;

import com.hexagram2021.violas_wardrobe.common.entities.ILuminaAffectable;
import com.hexagram2021.violas_wardrobe.common.entities.goals.LuminaAffectedGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

/**
 * Mob 实体 Mixin，实现流光附魔效果喵~
 *
 * @author liudongyu
 */
@Mixin(Mob.class)
@SuppressWarnings({"java:S100", "java:S116"})
public class MobEntityMixin implements ILuminaAffectable {
	@Shadow
	@Final
	public GoalSelector targetSelector;

	@Unique
	private int violas_wardrobe$luminaAffectedTick = -1;

	@Inject(method = "<init>", at = @At(value = "TAIL"))
	private void violas_wardrobe$registerLuminaAffectedGoals(EntityType<? extends Mob> entityType, @Nullable Level level, CallbackInfo ci) {
		if (level != null && !level.isClientSide) {
			this.targetSelector.addGoal(0, new LuminaAffectedGoal((Mob)(Object) this));
		}
	}

	/**
	 * 流光效果是否生效喵？
	 *
	 * @return 流光效果是否生效喵~
	 */
	@Override
	public boolean violas_wardrobe$isLuminaAffected() {
		return this.violas_wardrobe$luminaAffectedTick >= 0;
	}

	/**
	 * 设置当前时刻为流光效果影响开始时刻喵~
	 *
	 * @param current 当前时刻喵~
	 */
	@Override
	public void violas_wardrobe$setLuminaActivatedTick(int current) {
		this.violas_wardrobe$luminaAffectedTick = current;
	}

	/**
	 * 获取流光效果被激活的时间刻喵~
	 *
	 * @return 流光效果被激活的时间刻喵~
	 */
	@Override
	public int violas_wardrobe$getLuminaActivatedTick() {
		return 0;
	}
}
