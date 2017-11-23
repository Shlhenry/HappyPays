package com.futeng.happypays.activity.aaa;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Toast统一管理类
 *
 */
public class T {
	/**
	 * 分段打印出较长log文本
	 *
	 * @param log
	 *            原log文本
	 * @param showCount
	 *            规定每段显示的长度（最好不要超过eclipse限制长度）
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	public static void L(String log) {
		if (log.length() > 5000) {
			String show = log.substring(0, 5000);
			Log.i("main", show + "");
			if ((log.length() - 5000) > 5000) {// 剩下的文本还是大于规定长度
				String partLog = log.substring(5000, log.length());
				L(partLog);
			} else {
				String surplusLog = log.substring(5000, log.length());
				Log.i("main", surplusLog + "");
			}

		} else {
			Log.i("main", log + "");
		}
	}

	private T() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isShow = true;

	/**
	 * 短时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (isShow)
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 短时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, int message) {
		if (isShow)
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 长时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (isShow)
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * 长时间显示Toast
	 *
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
		if (isShow)
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * 自定义显示Toast时间
	 *
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		if (isShow)
			Toast.makeText(context, message, duration).show();
	}

	/**
	 * 自定义显示Toast零时间
	 *
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show0(Context context, CharSequence message) {
		if (isShow)
			Toast.makeText(context, message, 0).show();
	}

	/**
	 * 自定义显示Toast时间
	 *
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
		if (isShow)
			Toast.makeText(context, message, duration).show();
	}

}