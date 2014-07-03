package com.esirong.tools.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;

import com.esirong.tools.Logger;

/**
 * 程序崩溃异常捕获
 * @author lanzheng
 * @date 2014年6月26日
 * @project COM.TOOLS
 * @package com.esirong.tools.exception
 * @package UnCatchException.java
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UnCatchException implements UncaughtExceptionHandler {

    private static final String TAG = "UnCatchException";
    
    public void init(Context context) {
        
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
       Logger.e(TAG, ""); 
       
       //退出程序
       android.os.Process.killProcess(android.os.Process.myPid());
       System.exit(1);
 
    }
    
}
