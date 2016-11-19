package com.example.lin.demo.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;

/**
 * Created by lin on 2016/9/27.
 */
public class DisplayUtil {

    /**
     * 将px值转换为dip或dp值，尺寸大小不变
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    /**
     * 将dp或dip值转换为px值， 尺寸大小不变
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5f);
    }

    public static Point getPoint(Activity context) {
        Point x = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(x);
        return x;
    }
}
