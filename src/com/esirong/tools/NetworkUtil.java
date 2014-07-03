package com.esirong.tools;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * 网络工具包
 * @author lanzheng
 * @date 2014年6月26日
 * @project COM.TOOLS
 * @package com.esirong.tools
 * @package NetworkUtil.java
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NetworkUtil {
    //网络连接
  /**
   * 是不是在线状态
   * 
   * @param context
   * @return
   */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    
    
}
