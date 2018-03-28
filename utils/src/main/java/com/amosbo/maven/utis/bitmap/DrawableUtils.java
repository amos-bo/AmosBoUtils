package com.amosbo.maven.utis.bitmap;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.bitmap
 * @Created Time: 2018/3/28 15:28
 * @Changed Time: 2018/3/28 15:28
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //TODO 添加描述
 */

public class DrawableUtils {
    /**
     * 改变Drawable颜色
     *
     * @param drawable Drawable
     * @param color    int
     * @return Drawable
     */
    public static Drawable tintDrawable(Drawable drawable, int color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(color));
        return wrappedDrawable;
    }
}
