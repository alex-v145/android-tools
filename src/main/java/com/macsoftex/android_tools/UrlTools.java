package com.macsoftex.android_tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

import java.io.File;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex-v on 25.09.14.
 */
public class UrlTools {
    public interface ValidHostHandler {
        void onHandle(boolean valid);
    }

    public static String getFileName(String url) {
        try {
            File file = new File(Uri.parse(url).getPath());
            return file.getName();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getFileExtension(String url) {
        String fileName = getFileName(url);

        if (fileName != null)
            return FileTools.getExtensionFromFileName(fileName);

        return null;
    }

    public static void isValidHost(final String host, final ValidHostHandler handler) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                boolean valid = true;

                try {
                    InetAddress.getByName(host);
                } catch (Exception e) {
                    valid = false;
                }

                if (handler != null)
                    handler.onHandle(valid);
            }
        }).start();
    }
}
