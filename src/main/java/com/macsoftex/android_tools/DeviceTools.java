package com.macsoftex.android_tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;

import com.macsoftex.android_tools.stream.StreamTools;
import com.macsoftex.android_tools.stream.TextStreamTools;

import java.util.List;
import java.util.Locale;

/**
 * Created by alex on 15.03.18.
 */

public class DeviceTools {
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getDeviceName() {
        return android.os.Build.BRAND + " " + android.os.Build.MODEL;
    }

    public static String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getCurrentLanguageCode() {
        return Locale.getDefault().getLanguage();
    }

    public static boolean isPackageInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

        return true;
    }

    public static boolean isServiceRunning(Context context, String serviceClassName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        try {
            List<ActivityManager.RunningServiceInfo> runningServiceInfoList = activityManager.getRunningServices(Integer.MAX_VALUE);

            if (runningServiceInfoList != null) {
                for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfoList) {
                    if (serviceInfo.service.getClassName().equals(serviceClassName))
                        return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getSystemProperty(String propName) {
        try {
            Process process = Runtime.getRuntime().exec("getprop " + propName);
            return StreamTools.readText(process.getInputStream());
        } catch (Exception e) {
            return null;
        }
    }
}
