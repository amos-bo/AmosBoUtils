/**
 * @项目名称：DateScrollPicker
 * @文件名：DeviceUitls.java
 * @版本信息：
 * @日期：2015年7月30日
 * @Copyright 2015 www.517na.com Inc. All rights reserved.
 */
package com.amosbo.maven.utis.hardware;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.amosbo.maven.utis.Constants;
import com.amosbo.maven.utis.LogUtils;
import com.amosbo.maven.utis.string.StringUtils;

import java.lang.reflect.Method;


/**
 * @author xiangbo
 * @version 1.0
 * @作者 E-mail:284285624@qq.com
 * @date 创建时间：2015年8月19日 下午3:21:53
 * @描述：设备处理
 */
public class DeviceUtils {
    private static PhoneInfo mPhoneInfo;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }

    /**
     * px转换成dp
     */
    private int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转换成px
     */
    private int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转换成sp
     */
    private int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration
                .SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获取当前设备SDK版本号
     *
     * @return
     */
    public static int isThisSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return int[0] width,int[1] height
     */
    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }

    /**
     * 获取手机信息
     *
     * @return PhoneInfo(手机信息)
     */
    synchronized public static PhoneInfo getPhoneInfo(Application application) {
        if (mPhoneInfo == null) {
            mPhoneInfo = new PhoneInfo();
            long appVersionCode = getVersionCode(application);
            String appVersion = getVersion(application);
            String brand = getBrand();
            String simOperatorName = getSimOperatorName(application);
            String model = getModel();
            String SDK = Build.VERSION.SDK_INT + "";
            String release = getRelease();
            String manufacturer = getManufacturer();
            String platform = "Android";
            simOperatorName = StringUtils.dealWithStringSpace(simOperatorName);
            LogUtils.i(Constants.TAG, "Version:" + appVersion + " VersionCode:" + appVersionCode
                    + " Brand:" + brand + "" +
                    " SimOperatorName:" + simOperatorName
                    + " Model:" + model + " Release" + release + " Manufacturer" + manufacturer +
                    " " +
                    "SDK:" + SDK + " Platform:" + platform);
            mPhoneInfo.setAppVersionCode(appVersionCode);
            mPhoneInfo.setAppVersion(appVersion);
            mPhoneInfo.setBrand(brand);
            mPhoneInfo.setSimOperatorName(simOperatorName);
            mPhoneInfo.setModel(model);
            mPhoneInfo.setSDK(SDK);
            mPhoneInfo.setRelease(release);
            mPhoneInfo.setManufacturer(manufacturer);
            mPhoneInfo.setPlatform(platform);
        }
        return mPhoneInfo;
    }

    /**
     * Retrieves application's version number from the manifest
     *
     * @return long
     */
    private static long getVersionCode(Application app) {
        long version = 0;
        PackageManager packageManager = app.getApplicationContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    app.getApplicationContext().getPackageName(), 0);
            version = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * Retrieves application's version number from the manifest
     *
     * @return String
     */
    private static String getVersion(Application app) {
        String version = "0.0.0";
        try {
            PackageManager packageManager = app.getApplicationContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    app.getApplicationContext().getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获得手机品牌
     *
     * @return String
     */
    private static String getBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机运营商
     */
    private static String getSimOperatorName(Application app) {
        TelephonyManager tm = (TelephonyManager) app.getApplicationContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimOperatorName();
    }

    /**
     * 获得手机型号
     *
     * @return String
     */
    private static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获得固件版本
     *
     * @return String
     */
    private static String getRelease() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 生产商家
     *
     * @return String
     */
    private static String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * @param context
     * @return 是否存在虚拟按键
     */
    public static boolean hasNavBar(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                Resources res = context.getResources();
                int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
                if (resourceId != 0) {
                    boolean hasNav = res.getBoolean(resourceId);
                    // check override flag
                    String sNavBarOverride = getNavBarOverride();
                    if ("1".equals(sNavBarOverride)) {
                        hasNav = false;
                    } else if ("0".equals(sNavBarOverride)) {
                        hasNav = true;
                    }
                    return hasNav;
                } else { // fallback
                    return !ViewConfiguration.get(context).hasPermanentMenuKey();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return sNavBarOverride;
    }

    /**
     * 获取虚拟按键栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        try {
            if (hasNavBar(context)) {
                Resources res = context.getResources();
                int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    result = res.getDimensionPixelSize(resourceId);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result;
    }
}
