package com.amosbo.maven.utis.bitmap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.amosbo.maven.utis.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.bitmap
 * @Created Time: 2018/3/28 11:37
 * @Changed Time: 2018/3/28 11:37
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: //图片工具
 */

public class BitmapUtils {

    /**
     * view 映射成bitmap
     *
     * @param view
     * @return Bitmap
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View
                .MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        if (bitmap != null) {
            return bitmap;
        }
        bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        view.draw(new Canvas(bitmap));

        return bitmap;
    }

    /**
     * Compress by quality,  and generate image to the path specified
     * 图片质量压缩
     * @param image
     * @param outPath
     * @param maxSize target will be compressed to be smaller than this size.(kb)
     * @throws IOException
     */
    public static void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int options = 100;
        image.compress(Bitmap.CompressFormat.JPEG, options, os);
        LogUtils.e("图片压缩前大大小=", os.toByteArray().length / 1024 + "kb");
        while (os.toByteArray().length / 1024 > maxSize) {
            os.reset();
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, os);
            LogUtils.e("图片压缩后大大小=", os.toByteArray().length / 1024 + "kb");
        }
        FileOutputStream fos = new FileOutputStream(outPath);
        fos.write(os.toByteArray());
        fos.flush();
        fos.close();
    }

    /**
     * 裁剪图片方法实现
     *
     * @param context Activity
     * @param uri     Uri
     * @param out     Uri
     */
    public static void crop(Activity context, Uri uri, Uri out) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 1);
        intent.putExtra("outputY", 1);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, out);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        context.startActivity(intent);
    }

    /**
     * 保存图片到sdcard
     *
     * @param context Context
     * @param bitmap  Bitmap
     * @param path    String
     * @return boolean
     */
    public static boolean saveBitmapToSdCard(Context context, Bitmap bitmap, String path) {
        boolean save = false;
        FileOutputStream out = null;
        File f = null;
        try {
            f = new File(path);
            if (!f.exists()) {
                boolean createNewFile = f.createNewFile();
                if (!createNewFile) {
                    return false;
                }
            }

            out = new FileOutputStream(f);
            save = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (save) {
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(f);
            intent.setData(uri);
            context.sendBroadcast(intent);
        }
        return save;
    }

    /**
     * 保存图片到sdcard
     *
     * @param bitmap Bitmap
     * @return boolean
     */
    public static boolean saveBitmapToSdCard(Context context, Bitmap bitmap, File parent) {

        if (bitmap == null) {
            return false;
        }
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return false;
        }

        long millis = Calendar.getInstance().getTimeInMillis();
        File f = new File(parent, millis + ".jpg");
        return saveBitmapToSdCard(context, bitmap, f.getAbsolutePath());
    }
}
