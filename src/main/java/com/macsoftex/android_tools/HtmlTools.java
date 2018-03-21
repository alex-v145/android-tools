package com.macsoftex.android_tools;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by alex on 20.03.18.
 */

public class HtmlTools {
    public static Spanned spannedFromHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
}
