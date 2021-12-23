package com.commonutility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceConnector {
    public static final String PREF_NAME = "app_prefrences";
    public static final int MODE = Context.MODE_PRIVATE;

    public static final String AUTOLOGIN = "autologin";

    public static final String WEBHEADING = "webhead";
    public static final String WEBURL = "weburl";

    public static final String SPANNEDHEAD = "spannedhead";
    public static final String SPANNEDTITLE = "spannedtitle";
    public static final String SPANNEDDESC = "spanneddesc";

    public static final String LANGUAGE = "language";
    public static final String FCMID = "fcmid";
    public static final String ISNOTIFICATION = "isnotification";

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String LOCATION = "location";

    public static final String ISDARKTHEME = "isdarktheme";
    public static final String ISINTROSHOW = "isintroshow";

    public static final String SELECTEDLANGUAGE = "selectedlanguage";
    public static final String LOGINUSERID        = "login";
    public static final String USERFIRSTNAME      = "user_firstname";
    public static final String USERLASTNAME       = "user_lastname";
    public static final String USEREMAIL          = "user_email";
    public static final String USERPHONE          = "user_phone";
    public static final String USERIMAGE          = "user_image";

    public static final String RANDOMOTP        = "randomotp";
    public static final String OTPNEXT          = "otptext";

    public static final String PASSCODE         = "passcode";
    public static final String SELECTEDEXAMID   = "examid";
    public static final String ISPASSCODESET      = "ispasscodeset";
    public static final String CURRENTPASSCODE      = "passcode";
    public static final String OTPPHONENUMBER       = "otpregisterphonenumber";


    public static void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static boolean readBoolean(Context context, String key, boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void writeFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public static float readFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public static long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void cleanPrefrences(Context context) {
        getPreferences(context).edit().clear().commit();
    }
}
