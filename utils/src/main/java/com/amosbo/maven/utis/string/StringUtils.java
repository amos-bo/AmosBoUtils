package com.amosbo.maven.utis.string;

import android.text.TextUtils;

import com.amosbo.maven.utis.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.String
 * @Created Time: 2018/3/28 11:01
 * @Changed Time: 2018/3/28 11:01
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //TODO 添加描述
 */

public class StringUtils {

    /**
     * @param tags list<String>
     * @return string
     */
    public static String listToString(List<String> tags) {
        String tagStr = "";
        for (String tag : tags) {
            if (TextUtils.isEmpty(tagStr)) {
                tagStr += tag;
            } else {
                tagStr += "," + tag;
            }
        }
        return tagStr;
    }

    /**
     * 转utf-8
     *
     * @param s String
     * @return String
     */
    private static String toUtf8String(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 字符串转数组
     *
     * @param s   源字符
     * @param tag 分隔符
     * @return
     */
    public static List<String> strings2ListByTag(String s, String tag) {
        return Arrays.asList(s.split(tag));
    }

    /**
     * 截取数字
     *
     * @param content String
     * @return String
     */
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "0";
    }

    /**
     * 处理url中的空白符和中文
     *
     * @param url String
     * @return String
     */
    public static String dealWithStringSpace(String url) {
        try {
            url = toUtf8String(url);
            if (url.contains(" ")) {
                url = url.replaceAll(" ", "%20");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 是否是邮箱
     *
     * @param email CharSequence
     * @return boolean
     */
    public static boolean isValidEmail(CharSequence email) {
        return email != null && Constants.EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 是否是数字
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * @param string
     * @return
     * @description判断String是否为空,true代表空，false代表非空
     * @date 2015年8月19日
     */
    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        } else if ("".equalsIgnoreCase(string.trim())) {
            return true;
        }
        return false;
    }
}
