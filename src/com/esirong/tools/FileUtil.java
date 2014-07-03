package com.esirong.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.util.Log;

import com.esirong.tools.exception.ToolException;

/**
 * 文件操作工具类
 * @author lanzheng
 * @date 2014年6月26日
 * @project COM.TOOLS
 * @package com.esirong.tools
 * @package FileUtil.java
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FileUtil {
    //新建
    
    private static final String TAG = FileUtil.class.getSimpleName();
    //删除
    //复制
  //创建文件夹(返回是否创建成功)
    public static void createParentDirectory(File dest) throws ToolException {
        if (dest == null) {
            throw new ToolException("createParentDirectory: dest is null");
        }

        File dir = dest.getParentFile();

        if (dir != null && !dir.exists()) {
            createParentDirectory(dir);
        }

        if (dir != null && !dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e(TAG, "Could not create dirs: " + dir.getAbsolutePath());

                throw new ToolException("Could not create dirs: " + dir.getAbsolutePath());
            }
        }
    }
//重命名
    public static void renameFile(File origFile, File newFile, boolean overwrite) {
        if (!origFile.exists()) {
            Log.e(TAG, "Error renaming file: " + origFile + " does not exist");

            throw new ToolException("Error renaming file: " + origFile + " does not exist");
        }

        createParentDirectory(newFile);

        if (overwrite && newFile.exists()) {
            if (!newFile.delete()) {
                Log.e(TAG, "Error renaming file: failed to delete " + newFile);

                throw new ToolException("Error renaming file: failed to delete " + newFile);
            }
        }

        if (!origFile.renameTo(newFile)) {
            Log.e(TAG, "Error renaming " + origFile + " to " + newFile);

            throw new ToolException("Error renaming " + origFile + " to " + newFile);
        }
    }
//复制文件
    public static void copyFile(File origFile, File newFile, boolean overwrite) {
        if (!origFile.exists()) {
            Log.e(TAG, "Error renaming file: " + origFile + " does not exist");

            throw new ToolException("Error copying file: " + origFile + " does not exist");
        }

        createParentDirectory(newFile);

        if (!overwrite && newFile.exists()) {
            Log.e(TAG, "Error copying file: destination exists: " + newFile);

            throw new ToolException("Error copying file: destination exists: " + newFile);
        }

        try {
            FileInputStream fis = new FileInputStream(origFile);
            FileOutputStream fos = new FileOutputStream(newFile);
            FileChannel in = fis.getChannel();
            fos.getChannel().transferFrom(in, 0, in.size());
            fis.close();
            fos.close();
        } catch (Exception e) {
            Log.e(TAG, "Error copying " + origFile + " to " + newFile);

            throw new ToolException("Error copying " + origFile + " to " + newFile, e);
        }
    }

}
  
