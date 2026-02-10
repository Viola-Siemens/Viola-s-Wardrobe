package com.hexagram2021.violas_wardrobe.common.utils;

/**
 * 数学工具类，提供常用数学函数喵~
 *
 * @author liudongyu
 */
public final class MathUtils {
	/**
	 * Sigmoid 函数喵~
	 * @param x	输入值喵~
	 * @return sigmoid(x) 的值喵~
	 */
	public static double sigmoid(double x) {
		return 1.0D / (1.0D + Math.exp(-x));
	}

	private MathUtils() {
	}
}
