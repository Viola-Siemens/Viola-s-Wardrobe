package com.hexagram2021.violas_wardrobe.common.entities;

/**
 * 可受流光影响的实体接口喵~
 *
 * @author liudongyu
 */
@SuppressWarnings("java:S100")
public interface ILuminaAffectable {
	int LUMINA_DEACTIVATE_TICK = -1;

	/**
	 * 流光效果是否生效喵？
	 *
	 * @return 流光效果是否生效喵~
	 */
	boolean violas_wardrobe$isLuminaAffected();

	/**
	 * 设置当前时刻为流光效果影响开始时刻喵~
	 *
	 * @param current 当前时刻喵~
	 */
	void violas_wardrobe$setLuminaActivatedTick(int current);

	/**
	 * 停止受流光效果影响喵~
	 */
	default void violas_wardrobe$deactivateLumina() {
		this.violas_wardrobe$setLuminaActivatedTick(LUMINA_DEACTIVATE_TICK);
	}

	/**
	 * 获取流光效果被激活的时间刻喵~
	 *
	 * @return 流光效果被激活的时间刻喵~
	 */
	int violas_wardrobe$getLuminaActivatedTick();
}
