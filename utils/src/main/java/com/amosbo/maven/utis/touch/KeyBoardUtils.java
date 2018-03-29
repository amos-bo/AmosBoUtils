package com.amosbo.maven.utis.touch;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.touch
 * @Created Time: 2018/3/29 11:05
 * @Changed Time: 2018/3/29 11:05
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //TODO 添加描述
 */

public class KeyBoardUtils {
    /**
     * 切换软键盘（开- 关，关 - 开）
     *
     * @param context 上下文对象
     */
    public static void showSoftInput(Context context) {
        try {
            InputMethodManager m = (InputMethodManager) context.getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        //强制隐藏键盘
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * 隐藏软键盘
     *
     * @param act Activity
     */
    public static void hideSoftInput(Activity act) {
        try {
            if (act == null) {
                return;
            }
            final View v = act.getWindow().peekDecorView();
            if (v != null && v.getWindowToken() != null) {
                InputMethodManager imm = (InputMethodManager) act.getSystemService(Context
                        .INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
