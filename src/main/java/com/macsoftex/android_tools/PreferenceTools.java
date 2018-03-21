package com.macsoftex.android_tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.preference.PreferenceManager;

import org.xmlpull.v1.XmlPullParser;

import java.util.Arrays;

/**
 * Created by alex-v on 31.05.17.
 */

public class PreferenceTools {
    private static final String XML_NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static String preferenceXmlToString(Context context, int xmlResId) {
        try {
            String str = "";
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            XmlResourceParser xmlResourceParser = context.getResources().getXml(xmlResId);
            int event = xmlResourceParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String tag = xmlResourceParser.getName();

                if (event == XmlPullParser.START_TAG) {
                    String key = xmlResourceParser.getAttributeValue(XML_NAMESPACE, "key");
                    String title = xmlResourceParser.getAttributeValue(XML_NAMESPACE, "title");
                    String entries = xmlResourceParser.getAttributeValue(XML_NAMESPACE, "entries");
                    String entryValues = xmlResourceParser.getAttributeValue(XML_NAMESPACE, "entryValues");

                    if (title != null) {
                        String fixedTitle = getResourceString(context, title);

                        if (tag.equals("PreferenceScreen")) {
                            str += "\n[" + fixedTitle.toUpperCase() + "]\n";
                        } else if (key != null && sharedPreferences.contains(key)) {
                            String value = getPreferenceValue(sharedPreferences, key);

                            if (entries != null && entryValues != null && value != null) {
                                String[] entriesList = getResourceStringArray(context, entries);
                                String[] entryValuesList = getResourceStringArray(context, entryValues);

                                if (entriesList != null && entryValuesList != null) {
                                    int index = Arrays.asList(entryValuesList).indexOf(value);

                                    if (index >= 0)
                                        value = entriesList[index];
                                }
                            }

                            String fixedValue = (value == null) ? "null" : value;
                            str += fixedTitle + " = " + fixedValue + "\n";
                        }
                    }
                }

                event = xmlResourceParser.next();
            }

            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getResourceString(Context context, String xmlValue) {
        if (xmlValue.charAt(0) == '@') {
            int resId = Integer.parseInt(xmlValue.substring(1));
            return context.getString(resId);
        }

        return xmlValue;
    }

    private static String[] getResourceStringArray(Context context, String xmlValue) {
        if (xmlValue.charAt(0) == '@') {
            int resId = Integer.parseInt(xmlValue.substring(1));
            return context.getResources().getStringArray(resId);
        }

        return null;
    }

    private static String getPreferenceValue(SharedPreferences sharedPreferences, String key) {
        String value = null;

        try {
            value = sharedPreferences.getString(key, null);
        } catch (Exception e) {
        }

        if (value == null) {
            try {
                value = String.valueOf(sharedPreferences.getBoolean(key, false));
            } catch (Exception e) {
            }
        }

        if (value == null) {
            try {
                value = String.valueOf(sharedPreferences.getInt(key, 0));
            } catch (Exception e) {
            }
        }

        if (value == null) {
            try {
                value = String.valueOf(sharedPreferences.getFloat(key, 0));
            } catch (Exception e) {
            }
        }

        if (value == null) {
            try {
                value = String.valueOf(sharedPreferences.getLong(key, 0));
            } catch (Exception e) {
            }
        }

        return value;
    }
}
