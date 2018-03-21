package com.macsoftex.android_tools.stream;

import android.content.Context;

import java.io.File;

/**
 * Created by alex on 19.03.18.
 */

public class ObjectStreamTools {
    public static Object loadObjectFromFile(File file) {
        return StreamTools.readObject(StreamTools.openFileInputStream(file));
    }

    public static boolean saveObjectToFile(File file, Object object) {
        return StreamTools.writeObject(StreamTools.openFileOutputStream(file), object);
    }

    public static Object loadObjectFromAsset(Context context, String fileName) {
        return StreamTools.readObject(StreamTools.openAssetInputStream(context, fileName));
    }

    public static Object loadObjectFromRaw(Context context, int rawResId) {
        return StreamTools.readObject(StreamTools.openRawInputStream(context, rawResId));
    }

    public static Object loadObjectFromInternalStorage(Context context, String fileName) {
        return StreamTools.readObject(StreamTools.openInternalStorageInputStream(context, fileName));
    }

    public static boolean saveObjectToInternalStorage(Context context, String fileName, Object object) {
        return StreamTools.writeObject(StreamTools.openInternalStorageOutputStream(context, fileName), object);
    }
}
