package com.macsoftex.android_tools.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by alex on 08.11.17.
 */

public class AppCrashManager implements Thread.UncaughtExceptionHandler {
    private static final String CRASH_FLAG_KEY = "AppCrashed";

    private static AppCrashManager ourInstance = new AppCrashManager();

    private SharedPreferences settings;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private Handler handler;

    public interface Handler {
        void onCrash();
    }

    public static AppCrashManager getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        this.settings = PreferenceManager.getDefaultSharedPreferences(context);
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public boolean isCrashed() {
        return settings.getBoolean(CRASH_FLAG_KEY, false);
    }

    public void resetCrashedFlag() {
        settings.edit().putBoolean(CRASH_FLAG_KEY, false).apply();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        settings.edit().putBoolean(CRASH_FLAG_KEY, true).apply();

        if (handler != null)
            handler.onCrash();

        if (defaultHandler != null)
            defaultHandler.uncaughtException(thread, throwable);
    }
}
