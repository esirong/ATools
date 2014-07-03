package com.esirong.tools;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;

import android.os.Environment;
import android.util.Log;

/**
 * 输入输出工具类
 * @author lanzheng
 * @date 2014年6月26日
 * @project COM.TOOLS
 * @package com.esirong.tools
 * @package IOUtil.java
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class IOUtil {
    
    private static final String TAG = IOUtil.class.getSimpleName();
    
    /**
     * 读取流字符
     * 
     * @param is
     * @return
     */
    public static String readStream(InputStream is) {
        if (is == null) {
            return null;
        }
        
        try {
            int c;
            byte[] buffer = new byte[8192];
            StringBuilder sb = new StringBuilder();
            
            while ((c = is.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, c));
            }
            
            return sb.toString();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeStream(is);
        }
        
        return null;
    }
    
    /**
     * 从流写入到文件
     * 
     * @param is
     * @param file
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void writeFile(InputStream is, File file) throws ClientProtocolException, IOException {
        FileOutputStream os = new FileOutputStream(file);
        
        try {
            int c;
            byte[] buffer = new byte[8192];
            
            while ((c = is.read(buffer)) != -1) {
                os.write(buffer, 0, c);
            }
        } finally {
            closeStream(is);
            closeStream(os);
        }
    }
    
    /**
     * 关闭流
     * 
     * @param stream
     */
    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
                stream = null;
            } catch (IOException e) {
                Log.w(TAG, "Close stream exception", e);
            }
        }
    }
    
    /**
     * 是不是可以写
     * 
     * @return
     */
    public static boolean isDeviceWritable() {
        String sdState = Environment.getExternalStorageState();
        
        if (Environment.MEDIA_MOUNTED.equals(sdState)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 是不是可以读
     * 
     * @return
     */
    public static boolean isDeviceReadable() {
        String sdState = Environment.getExternalStorageState();
        
        if (Environment.MEDIA_MOUNTED.equals(sdState) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(sdState)) {
            return true;
        } else {
            return false;
        }
    }
}
