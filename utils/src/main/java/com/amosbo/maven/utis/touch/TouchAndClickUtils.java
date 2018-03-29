package com.amosbo.maven.utis.touch;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.touch
 * @Created Time: 2018/3/28 10:29
 * @Changed Time: 2018/3/28 10:29
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //点击和触摸
 */

public class TouchAndClickUtils {
    private static TouchAndClickUtils util;

    synchronized public static TouchAndClickUtils getInstance() {
        if (util == null) {
            util = new TouchAndClickUtils();
        }
        return util;
    }

    private TouchAndClickUtils() {
        super();
    }


    /**
     * 给view设置改变Alpha的Touch效果
     *
     * @param v
     */
    public void setOnTouchAlphaListener(final View v) {
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return setAlphaTouch(v, motionEvent);
            }
        });
    }

    /**
     * 给view设置改变大小的Touch效果
     *
     * @param v
     */
    public void setOnTouchSmall2BigListener(final View v) {
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return setSmall2BigTouch(v, motionEvent);
            }
        });
    }

    /**
     * 按下alpha变小，松开变大
     *
     * @param v
     * @param event
     * @return
     */
    private boolean setAlphaTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.5f);
                //   为了兼容11以下的设备，使用nineoldandroids设置alpha
                // ViewHelper.setAlpha(v, 0.5f);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                v.setAlpha(0.5f);
                //   为了兼容11以下的设备，使用nineoldandroids设置alpha
                // ViewHelper.setAlpha(v, 1f);
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 按下变小，松开变大
     *
     * @param v
     * @param event
     * @return
     */
    private boolean setSmall2BigTouch(View v, MotionEvent event) {
        float start = 1.0f;
        float end = 0.9f;
        Animation scaleAnimation = new ScaleAnimation(start, end, start, end,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        Animation endAnimation = new ScaleAnimation(end, start, end, start,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setFillAfter(true);
        endAnimation.setDuration(200);
        endAnimation.setFillAfter(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.startAnimation(scaleAnimation);
                v.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                v.startAnimation(endAnimation);
                v.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                v.startAnimation(endAnimation);
                v.invalidate();
                break;
                default:
                    break;
        }
        return false;
    }

    /**
     * 防止多次点击
     *
     * @param view View
     */
    public static void forbidRepeatClick(final View view) {
        if (view == null) {
            return;
        }
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view.getContext() == null) {
                    return;
                }
                view.setEnabled(true);
            }
        }, 500);
    }
}
