package com.amosbo.maven.utis.hardware;

import java.io.Serializable;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.hardware
 * @Created Time: 2018/3/28 14:38
 * @Changed Time: 2018/3/28 14:38
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //TODO 添加描述
 */

public class PhoneInfo implements Serializable {

    /**
     * app版本VersionCode
     */
    private long appVersionCode;
    /**
     * app版本号码
     */
    private String appVersion;
    /**
     * 手机品牌
     */
    private String brand;
    /**
     * 手机运营商
     */
    private String simOperatorName;
    /**
     * 手机型号
     */
    private String model;
    /**
     * 手机当前SDK版本
     */
    private String SDK;
    /**
     * 手机获得固件版本
     */
    private String release;
    /**
     * 手机生产商家
     */
    private String manufacturer;
    /**
     * 手机操作平台
     */
    private String platform;
    /**
     * 手机IMEI
     */
    private String imei;
    /**
     * 手机IP
     */
    private String ip;

    public PhoneInfo() {
    }

    public PhoneInfo(long appVersionCode, String appVersion, String brand, String
            simOperatorName, String model, String SDK, String release, String manufacturer,
                     String platform, String imei, String ip) {
        this.appVersionCode = appVersionCode;
        this.appVersion = appVersion;
        this.brand = brand;
        this.simOperatorName = simOperatorName;
        this.model = model;
        this.SDK = SDK;
        this.release = release;
        this.manufacturer = manufacturer;
        this.platform = platform;
        this.imei = imei;
        this.ip = ip;
    }

    public long getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(long appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSDK() {
        return SDK;
    }

    public void setSDK(String SDK) {
        this.SDK = SDK;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
