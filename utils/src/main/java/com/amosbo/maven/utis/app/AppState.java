package com.amosbo.maven.utis.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import com.amosbo.maven.utis.Constants;
import com.amosbo.maven.utis.LogUtils;
import com.amosbo.maven.utis.ToastUtils;

import java.io.File;
import java.util.List;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.app
 * @Created Time: 2018/3/27 15:45
 * @Changed Time: 2018/3/27 15:45
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //app相关状态
 */

public class AppState {

    /**
     * activity是否在顶层
     *
     * @param activity Activity
     * @return boolean
     */
    public static boolean isTopActivity(Activity activity) {
        boolean isTop = false;
        ActivityManager am = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        LogUtils.d(Constants.TAG, "isTopActivity = " + cn.getClassName());
        if (cn.getClassName().contains(activity.getComponentName().getClassName())) {
            isTop = true;
        }
        LogUtils.d(Constants.TAG, "isTop = " + isTop);
        return isTop;
    }

    /**
     * 判断应用是否在后台
     *
     * @param context Context
     * @return boolean
     */
    public static boolean isAppInBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo
                        .IMPORTANCE_BACKGROUND;
            }
        }
        return false;
    }

    /**
     * 选择打开url的浏览器
     *
     * @param context Context
     * @param url     String
     */
    public static void openUrlbyWeb(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(url));
        context.startActivity(Intent.createChooser(intent, "打开"));
    }

    /**
     * 跳转应用市场
     *
     * @param context
     * @param packageName
     */
    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context  Context
     * @param filePath String
     * @return int  0 不完整 1完整但包名错误 2 完整且正确
     */
    public static int isApkCanInstall(Context context, String filePath) {
        int result = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
            if (info != null && !TextUtils.isEmpty(info.packageName)) {
                if (info.packageName.equals(context.getPackageName())) {
                    //完整
                    result = 2;
                } else {
                    //完整
                    result = 1;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 是否安装应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * 安装apk
     */
    public void instanceApk(Application application, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        application.getApplicationContext().startActivity(intent);
    }

    /**
     * 启动App
     */
    public static void launchApp(Context context, String packageName) {
        // 判断是否安装过App，否则去市场下载
        if (isAppInstalled(context, packageName)) {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage
                    (packageName));
        } else {
            goToMarket(context, packageName);
        }
    }

    /**
     * @param
     * @return String
     * @description 获取APPLICATION metadata的数据
     * @date 2015-09-12
     * @Exception
     */
    public static Object getApplicationMetaData(Context context, String key) {
        Object sRst = null;
        ApplicationInfo appInfo = null;
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            sRst = "" + appInfo.metaData.get(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return sRst;
    }

}
