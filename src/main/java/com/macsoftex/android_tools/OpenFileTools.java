package com.macsoftex.android_tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by alex-v on 30.09.14.
 */
public class OpenFileTools {
    public static boolean openAudioFile(Context context, Uri uri) {
        return openAudioFile(context, uri, null);
    }

    public static boolean openAudioFile(Context context, Uri uri, String appPackageName) {
        return openFile(context, "audio/*", uri, appPackageName);
    }

    public static boolean openVideoFile(Context context, Uri uri) {
        return openVideoFile(context, uri, null);
    }

    public static boolean openVideoFile(Context context, Uri uri, String appPackageName) {
        return openFile(context, "video/*", uri, appPackageName);
    }

    public static boolean openImageFile(Context context, Uri uri) {
        return openImageFile(context, uri, null);
    }

    public static boolean openImageFile(Context context, Uri uri, String appPackageName) {
        return openFile(context, "image/*", uri, appPackageName);
    }

    private static boolean openFile(Context context, String type, Uri uri, String appPackageName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setType(type);

            if (appPackageName != null)
                intent.setPackage(appPackageName);

            context.startActivity(intent);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
