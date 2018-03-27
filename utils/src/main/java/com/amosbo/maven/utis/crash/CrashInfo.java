package com.amosbo.maven.utis.crash;

import java.io.Serializable;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utils.Crash
 * @Created Time: 2018/3/27 15:06
 * @Changed Time: 2018/3/27 15:06
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: 异常捕获信息
 */

public class CrashInfo implements Serializable {

    private String throwClassName;
    private String throwMethodName;
    private String throwType;
    private String stackTrace;
    private String cause;
    private int throwLineNumber;

    public CrashInfo(String throwClassName, String throwMethodName, String throwType, String
            stackTrace, String cause, int throwLineNumber) {
        this.throwClassName = throwClassName;
        this.throwMethodName = throwMethodName;
        this.throwType = throwType;
        this.stackTrace = stackTrace;
        this.cause = cause;
        this.throwLineNumber = throwLineNumber;
    }

    public String getThrowClassName() {
        return throwClassName;
    }

    public String getThrowMethodName() {
        return throwMethodName;
    }

    public String getThrowType() {
        return throwType;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getCause() {
        return cause;
    }

    public int getThrowLineNumber() {
        return throwLineNumber;
    }
}
