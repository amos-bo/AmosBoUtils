package com.amosbo.maven.utis.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.amosbo.maven.utis.Constants;
import com.amosbo.maven.utis.LogUtils;
import com.amosbo.maven.utis.RootUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * @author : Amos_bo
 * @package: com.amosbo.maven.utis.file
 * @Created Time: 2018/3/28 11:39
 * @Changed Time: 2018/3/28 11:39
 * @email: 284285624@qq.com
 * @Org: SZKT
 * @version: V1.0
 * @describe: 文件工具
 */

public class FileUtils {

    /**
     * 打开文件
     *
     * @param context
     * @param filePath
     */
    public static void openFile(Context context, String filePath) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(filePath)), getMimeType
                    (filePath));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤文件名
     *
     * @param file
     * @return
     */
    private static String getSuffix(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        String fileName = file.getName();
        if (fileName.equals("") || fileName.endsWith(".")) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index + 1).toLowerCase(Locale.US);
        } else {
            return null;
        }
    }

    /**
     * 获取文件类型
     *
     * @param filePath
     * @return
     */
    public static String getMimeType(String filePath) {
        File file = new File(filePath);
        String suffix = getSuffix(file);
        if (suffix == null) {
            return "file/*";
        }
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(suffix);
        if (!TextUtils.isEmpty(type)) {
            return type;
        }
        return "file/*";
    }

    /**
     * 是否能写入文件
     *
     * @param file File
     * @return boolean
     */
    private static boolean isWritable(@NonNull final File file) {
        boolean isExisting = file.exists();
        try {
            FileOutputStream output = new FileOutputStream(file, true);
            try {
                output.close();
            } catch (IOException e) {
                LogUtils.e(Constants.TAG, e.toString());
            }
        } catch (FileNotFoundException e) {
            LogUtils.e(Constants.TAG, e.toString());
            return false;
        }
        boolean result = file.canWrite();

        // Ensure that file is not created during this process.
        if (!isExisting) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        return result;
    }

    /**
     * 是否有读写权限
     *
     * @param folder File
     * @return boolean
     */
    public static boolean isWritableNormalOrSaf(@Nullable final File folder) {
        // Verify that this is a directory.
        if (folder == null) {
            return false;
        }
        // Find a non-existing file in this directory.
        int i = RootUtils.randomWithRange(100, 100000);
        File file;
        do {
            String fileName = "TestFile" + (++i);
            file = new File(folder, fileName);
        }
        while (file.exists());

        // First check regular writability
        if (isWritable(file)) {
            return true;
        }
        return false;
    }

    /**
     * 缓存文件夹
     *
     * @param context Context
     * @return File
     */
    public static File getCacheDir(Context context) {
        File file = null;
        try {
            file = context.getExternalCacheDir();
        } catch (Throwable e) {

            e.printStackTrace();
        }
        boolean isSuccess = false;
        if (file != null && file.canRead() && file.canWrite()) {
            isSuccess = true;
        }
        if (!isSuccess) {
            file = context.getCacheDir();
        }
        return file;
    }
}
