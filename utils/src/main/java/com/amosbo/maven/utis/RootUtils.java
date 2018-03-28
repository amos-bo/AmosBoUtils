package com.amosbo.maven.utis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis
 * @Created Time: 2018/3/28 14:17
 * @Changed Time: 2018/3/28 14:17
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //TODO 添加描述
 */

public class RootUtils {

    /**
     * 获取随机值
     *
     * @param min int
     * @param max int
     * @return int
     */
    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    /**
     * 颜色值计算
     *
     * @param fraction   float 百分比  开始颜色到结束颜色的百分比
     * @param startValue Integer
     * @param endValue   Integer
     * @return Integer  过渡颜色值
     */
    public static Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;
        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
                | (int) ((startR + (int) (fraction * (endR - startR))) << 16)
                | (int) ((startG + (int) (fraction * (endG - startG))) << 8)
                | (int) ((startB + (int) (fraction * (endB - startB))));
    }

}
