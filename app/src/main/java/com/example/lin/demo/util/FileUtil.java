package com.example.lin.demo.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lin on 2016/9/26.
 */
public class FileUtil {

    /**
     * 获取图片的存储路径
     * @param activity
     * @return
     */
    public static String getSaveImagePath(Activity activity) {
        String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/" + getApplicationName(activity) + "/download/image";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return savePath;
    }

    /**
     * 获取存储路径
     * @return
     */
    public static String getSaveFilePath(Activity activity) {
        String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/" + getApplicationName(activity) + "/download";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return savePath;
    }

    /**
     * 获取应用名
     * @return
     */
    public static String getApplicationName(Activity activity) {
        PackageManager manager = null;
        ApplicationInfo info = null;
        try {
            manager = activity.getApplicationContext().getPackageManager();
            info = manager.getApplicationInfo(activity.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            info = null;
        }
        String applicationName = (String) manager.getApplicationLabel(info);
        return applicationName;
    }

    /**
     * 将得到的一个bitmap保存到sd上，得到一个绝对路径
     */
    public static String getPath(Bitmap bitmap, Activity activity) {
        File temDir = new File(getSaveImagePath(activity));
        if (!temDir.exists()) {
            temDir.mkdirs();
        }
        File image = new File(temDir.getAbsolutePath() + "/head.png");
        try {
            FileOutputStream outputStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, outputStream);
            outputStream.flush();
            outputStream.close();
            return image.getCanonicalPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件的路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null ) {
            data = uri.getPath();
        }
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
