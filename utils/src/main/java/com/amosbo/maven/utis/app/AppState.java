package com.amosbo.maven.utis.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.amosbo.maven.utis.Constants;
import com.amosbo.maven.utis.LogUtils;

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

}
