package com.macsoftex.android_tools;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.content.Context;
import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

/**
 * Created by alex-v on 10.09.15.
 */
public class AppTools {
    public static String getAppName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    public static String getAppPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            if (packageInfo != null)
                return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isRemoteProcess(Context context) {
        Context applicationContext = context.getApplicationContext();
        int myPid = Process.myPid();
        String remotePackageName = getAppPackageName(context) + ":remote";
        ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();

        if (runningAppProcessInfoList != null) {
            for (ActivityManager.RunningAppProcessInfo appProcessInfo: runningAppProcessInfoList) {
                if (appProcessInfo.pid == myPid && remotePackageName.equals(appProcessInfo.processName))
                    return true;
            }
        }

        return false;
    }
}
