package com.amosbo.maven.utis.hardware;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.hardware
 * @Created Time: 2018/3/28 14:18
 * @Changed Time: 2018/3/28 14:18
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //TODO 添加描述
 */

public class NetUtils {

    /**
     * 得到网络类型，0是未知或未连上网络，1为WIFI，2为2g，3为3g，4为4g
     *
     * @return int
     */
    public int getNetType(Application app) {
        int type = 1;
        try {
            ConnectivityManager connectMgr = (ConnectivityManager) app.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectMgr.getActiveNetworkInfo();
            if (info == null || !info.isConnected()) {
                return 0;
            }
            switch (info.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    type = 1;
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    type = getNetworkClass(info.getSubtype());
                    break;
                default:
                    type = 0;
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return type;
    }
    /**
     * 判断数据连接的类型
     *
     * @param networkType int
     * @return int
     */
    public int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return 2;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return 3;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return 4;
            default:
                return 0;
        }
    }
}
