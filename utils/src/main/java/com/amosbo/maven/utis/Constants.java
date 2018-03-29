package com.amosbo.maven.utis;

import java.util.regex.Pattern;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis
 * @Created Time: 2018/3/28 10:47
 * @Changed Time: 2018/3/28 10:47
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: 常量
 */

public class Constants {

    public static final String TAG = "Amos_Bo";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    public static final Pattern PHONENUMBER_PATTERN = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
    public static final Pattern NUMBER_PATTERN =Pattern.compile("[0-9]*");
}
