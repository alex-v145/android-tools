package com.macsoftex.android_tools.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by alex-v on 05.10.17.
 */

public class BitmapTools {
    public static Bitmap bitmapFromView(View view) {
        view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final int width = view.getMeasuredWidth();
        final int height = view.getMeasuredHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.draw(canvas);

        return bitmap;
    }

    public static Bitmap bitmapFromView(View view, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, width, height);
        view.draw(canvas);

        return bitmap;
    }

    public static Bitmap bitmapFromResource(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;

        return BitmapFactory.decodeResource(context.getResources(), resId, opt);
    }

    public static void drawBitmapOnBitmap(Bitmap backgroundBitmap, Bitmap topBitmap, int left, int top) {
        Canvas canvas = new Canvas(backgroundBitmap);
        canvas.drawBitmap(topBitmap, left, top, null);
    }
}
