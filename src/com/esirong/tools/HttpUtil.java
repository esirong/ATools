package com.esirong.tools;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class HttpUtil {
    
    private static String TAG = HttpUtil.class.getSimpleName();
    
    private static final int CONNECTION_TIMEOUT = 120000;
    
    private static final int SOCKET_TIMEOUT = 120000;
    
    //一个超时的配置
    public static HttpParams getTimeoutHttpParams() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);
        
        return params;
    }
    
    //从给定url获取输入流
    public static InputStream getInputStreamFromUrl(String url) throws ClientProtocolException, IOException {
        HttpGet request = new HttpGet(url);
        DefaultHttpClient client = new DefaultHttpClient(getTimeoutHttpParams());
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        
        if (statusCode != 200) {
            Log.e(TAG, "Failed to get stream for: " + url);
            throw new IOException("Failed to get stream for: " + url);
        }
        
        return response.getEntity().getContent();
    }
    
    //从指定url获取文件内容
    public static String fetchContent(String url) throws ClientProtocolException, IOException {
        InputStream input = getInputStreamFromUrl(url);
        
        try {
            int c;
            byte[] buffer = new byte[8192];
            StringBuilder sb = new StringBuilder();
            
            while ((c = input.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, c));
            }
            
            return sb.toString();
        } finally {
            IOUtil.closeStream(input);
        }
    }
}
