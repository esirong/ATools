package com.touchwise.androidtool;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * 图像工具类
 * Created by esirong on 2016/1/28.
 */
public class BitmapUtils {

    /**
     * 以最省内存的方式读取本地资源的图片<p></p>
     * 通过资源对象获得指定图像的资源输入流，读取图像数据<p>
     * 通常用于读取其他APK的资源文件.
     *
     * @param resources 资源对象 (指定资源的位置)
     * @param resId     图像资源id
     * @return 图像
     */
    public static Bitmap readBitMap(Resources resources, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        //图像解码配置，如果对图像质量要求高，可以替换ARGB_8888
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        //  获取资源图片输入流
        InputStream is = resources.openRawResource(resId);

        return BitmapFactory.decodeStream(is, null, opt);

    }


    /**
     * 铺满缩放图像<p></>
     * 即缩放后的图像能全满覆盖，并且不能拉伸(为了规避拉伸，图像的高宽比不相同时，图像会有部分超出.）
     *
     * @param bitmap    将要缩放的图像
     * @param reqWidth  要求缩放后的宽
     * @param reqHeight 要求缩放后的高
     * @return 缩放完成的图像
     */
    public static Bitmap scaleToCoverBitmap(Bitmap bitmap, int reqWidth, int reqHeight) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int dstWidth = reqWidth;
        int dstHeight = reqHeight;
        //宽高比
        double outAspectRatio = Math.round((float) bitmapWidth / (float) bitmapHeight);
        double reqAspectRatio = Math.round((float) reqWidth / (float) reqHeight);
        if (outAspectRatio > reqAspectRatio) {
            // 压缩比例
            double heightRatio = Math.round((float) bitmapHeight / (float) reqHeight);
            dstWidth = (int) (reqWidth * heightRatio);
            dstHeight = (int) (reqHeight * heightRatio);

        } else if (outAspectRatio < reqAspectRatio) {
            double widthRatio = Math.round((float) bitmapWidth / (float) reqWidth);
            dstWidth = (int) (reqWidth * widthRatio);
            dstHeight = (int) (reqHeight * widthRatio);
        }

        // 压缩比例
        bitmap = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, true);

        return bitmap;
    }

    /**
     * 裁剪图像
     * <p>默认裁剪的起始点左上角（0,0)</p>
     *
     * @param bitmap    将要裁剪的图像
     * @param reqWidth  裁剪宽
     * @param reqHeight 裁剪高
     * @return 裁剪的图像
     */
    public static Bitmap cropBitmap(Bitmap bitmap, int reqWidth, int reqHeight) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int dstWidth = reqWidth;
        int dstHeight = reqHeight;

        if (reqWidth > bitmapWidth || reqWidth < 0) {
            dstWidth = bitmapWidth;
        }
        if (reqHeight > bitmapHeight || reqHeight < 0) {
            dstHeight = bitmapHeight;
        }

        int retX = 0;//基于原图左上角（0,0）
        int retY = 0;

        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, dstWidth, dstHeight, null, false);
    }

}
