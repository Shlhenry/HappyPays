package com.futeng.happypays.activity.kit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/7.
 */

public class MTools {

    /**
     * 转换double，保留两位四舍五入
     *
     * @param num
     * @return
     */
    public static double getDoubleTwo(String num) {
        Double dounum = 0.00;
        try {
            if (!num.isEmpty()) {
                double f = Double.valueOf(num);
                BigDecimal b = new BigDecimal(f);
                dounum = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        } catch (Exception e) {
            dounum = 0.00;
        }
        return dounum;
    }

    /**
     * 转换double，保留两位四舍五入
     *
     * @param num
     * @return
     */
    public static double getDoubleTwo(double num) {
        Double dounum = 0.00;
        try {
            double f = Double.valueOf(num);
            BigDecimal b = new BigDecimal(f);
            dounum = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            dounum = 0.00;
        }
        return dounum;
    }
    /**
     * 获取适配px
     *
     * @param context
     * @param id
     * @return
     */
    public static int getPx(Context context, int id) {
        return context.getResources().getDimensionPixelOffset(id);
    }

    /**
     * 获取自定义颜色
     *
     * @param context
     * @param id
     * @return
     */
    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }

    /**
     * 设置setMargins
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    /**
     * 设置宽高
     *
     * @param activity
     * @param view
     * @param width
     * @param height
     */
    public static void setViewParams(Context activity, View view, int width, int height) {
        ViewGroup.LayoutParams lp;
        lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);
    }


}