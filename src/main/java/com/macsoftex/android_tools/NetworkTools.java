package com.macsoftex.android_tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by alex-v on 29.08.16.
 */
public class NetworkTools {
    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo == null)
                return false;

            return networkInfo.isConnected();
        } catch (Exception e) {
            return false;
        }
    }
}
