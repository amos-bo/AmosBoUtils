package com.amosbo.maven.utis.crash;

import android.content.Context;
import android.text.TextUtils;

import com.amosbo.maven.utis.LogUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.TimeoutException;

/**
 * @author amos_bo 284285624@qq.com
 * @version V1.0
 * @Title: Roadbusiness
 * @Package com.ktcd.malc.utilslibrary.utils
 * @Description: UncaughtException处理类, 当程序发生Uncaught异常的时候, 有该类来接管程序, 并记录发送错误报告.
 * @date 2016/12/2 10:44
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler INSTANCE;
    @SuppressWarnings("unused")
    private Context mContext;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    synchronized public static CrashHandler getInstance() {
        if (INSTANCE==null){
            INSTANCE=new CrashHandler();
        }
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            if (ex instanceof TimeoutException) {
                return;
            }
            if (!TextUtils.isEmpty(ex.getMessage()) && ex.getMessage().contains("shutdown")) {
                return;
            }
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            pw.flush();
            String stackTrace = sw.toString();
            LogUtils.e("Amosbo_error", "发生异常：" + stackTrace);
            String throwType = ex.getClass().getName();
            String throwCause = ex.getMessage();
            String throwClassName;
            String throwMethodName;
            int throwLineNumber;
            if (ex.getStackTrace().length > 0) {
                StackTraceElement trace = ex.getStackTrace()[0];
                throwClassName = trace.getClassName();
                throwMethodName = trace.getMethodName();
                throwLineNumber = trace.getLineNumber();
            } else {
                throwClassName = "unknown";
                throwMethodName = "unknown";
                throwLineNumber = 0;
            }
            CrashInfo crashInfo=new CrashInfo(throwClassName,throwMethodName,throwType,stackTrace,throwCause,throwLineNumber);
            //获取PID
            android.os.Process.killProcess(android.os.Process.myPid());
            //常规java、c#的标准退出法，返回值为0代表正常退出
            System.exit(0);
        } catch (Exception e) {
            LogUtils.e("CrashHandler", "error : " + e);
        }
    }
}
