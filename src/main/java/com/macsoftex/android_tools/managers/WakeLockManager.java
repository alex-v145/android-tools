package com.macsoftex.android_tools.managers;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by alex on 06.02.18.
 */

public class WakeLockManager {
    private PowerManager.WakeLock wakeLock;
    private PowerManager manager;

    public WakeLockManager(Context context) {
        manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    synchronized public void wakeUp() {
        if (! isTurnOn()) {
            try {
                PowerManager.WakeLock wakeLock = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Wakeup");
                wakeLock.acquire(3000);
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    synchronized public void turnOn() {
        if (wakeLock == null) {
            wakeLock = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Wakelock");
            wakeLock.acquire();
        }
    }

    synchronized public void turnOff() {
        if (wakeLock != null) {
            try {
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }

            wakeLock = null;
        }
    }

    public boolean isTurnOn() {
        return wakeLock != null;
    }
}
