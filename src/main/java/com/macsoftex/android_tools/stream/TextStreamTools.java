package com.macsoftex.android_tools.stream;

import android.content.Context;

import java.io.File;

/**
 * Created by alex on 19.03.18.
 */

public class TextStreamTools {
    public static String loadTextFromFile(File file) {
        return StreamTools.readText(StreamTools.openFileInputStream(file));
    }

    public static boolean saveTextToFile(File file, String text) {
        return StreamTools.writeText(StreamTools.openFileOutputStream(file), text);
    }

    public static String loadTextFromAsset(Context context, String fileName) {
        return StreamTools.readText(StreamTools.openAssetInputStream(context, fileName));
    }

    public static String loadTextFromRaw(Context context, int rawResId) {
        return StreamTools.readText(StreamTools.openRawInputStream(context, rawResId));
    }

    public static String loadTextFromInternalStorage(Context context, String fileName) {
        return StreamTools.readText(StreamTools.openInternalStorageInputStream(context, fileName));
    }

    public static boolean saveTextToInternalStorage(Context context, String fileName, String text) {
        return StreamTools.writeText(StreamTools.openInternalStorageOutputStream(context, fileName), text);
    }
}
