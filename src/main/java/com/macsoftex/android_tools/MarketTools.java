package com.macsoftex.android_tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by alex-v on 13.11.14.
 */
public class MarketTools {
    public static boolean openAppPage(Context context, String packageName) {
        try {
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            try {
                Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=" + packageName);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            } catch (Exception e1) {
                return false;
            }
        }

        return true;
    }
}
