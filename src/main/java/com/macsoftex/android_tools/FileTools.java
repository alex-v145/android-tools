package com.macsoftex.android_tools;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by alex-v on 23.09.14.
 */
public class FileTools {
    public static String getValidFileName(String fileName) {
        return fileName.replaceAll("\\W+", "_").trim();
    }

    public static String getFileNameWithoutExtension(String fileName) {
        final int pos = fileName.lastIndexOf('.');

        if (pos > 0)
            return fileName.substring(0, pos);

        return fileName;
    }

    public static String getExtensionFromFileName(String fileName) {
        final int pos = fileName.lastIndexOf('.');

        if (pos > 0)
            return fileName.substring(pos + 1);

        return null;
    }

    public static boolean copyFile(File fileFrom, File fileTo, boolean overwrite) {
        if (!overwrite && fileTo.exists())
            return false;

        boolean success = true;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(fileFrom);
            fileOutputStream = new FileOutputStream(fileTo);

            byte[] buf = new byte[1024];
            int len;

            while ((len = fileInputStream.read(buf)) > 0) {
                fileOutputStream.write(buf, 0, len);
            }
        } catch (Exception e) {
            success = false;
        }

        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (Exception e) {
            }
        }

        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                success = false;
            }
        }

        return success;
    }
}
