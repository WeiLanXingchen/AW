package jyx.com.aw.util;

import android.content.Context;
import android.content.SharedPreferences;

import jyx.com.aw.app.AppApplication;
import jyx.com.aw.global.Properties;


/**
 * 作者：-zy- on 16/7/1
 * 邮箱：zhouyong@cdzmd.com
 * 功能：偏好参数工具类
 */
public class AppSharedUtil {

    private static final String COOKIE_FILE = "cookie_file";

    public static boolean isLogined() {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        return cookie.getBoolean(Properties.LOGIN_AUTH, false);
    }

    public static boolean isReloadUrl() {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        return cookie.getBoolean(Properties.LOGIN_AUTH, false);
    }

    public static void setReloadURL(boolean value) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cookie.edit();
        editor.putBoolean(Properties.RELOAD_URL, value);
        editor.apply();
    }


    public static void setLoginAuth(boolean value) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cookie.edit();
        editor.putBoolean(Properties.LOGIN_AUTH, value);
//        final String userId = AppSharedUtil.getString(Properties.TOKEN);


        editor.apply();
    }


//    public static String getUserId() {
//        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
//                COOKIE_FILE, Context.MODE_PRIVATE);
//        return cookie.getString(Properties.UID, "");
//    }


    public static void saveString(String tag, String value) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cookie.edit();
        editor.putString(tag, value);
        editor.apply();
    }


    public static void saveInt(String tag, int value) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cookie.edit();
        editor.putInt(tag, value);
        editor.apply();
    }

    public static int getInt(String tag) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        return cookie.getInt(tag, -1);
    }

    public static String getString(String tag, String defaultValue) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        return cookie.getString(tag, defaultValue);
    }

    public static String getString(String tag) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        return cookie.getString(tag, "");
    }

    public static Boolean getBoolean(String tag, boolean defaultValue) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        return cookie.getBoolean(tag, defaultValue);
    }

    public static void saveBoolean(String tag, boolean value) {
        SharedPreferences cookie = AppApplication.getAppContext().getSharedPreferences(
                COOKIE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cookie.edit();
        editor.putBoolean(tag, value);
        editor.apply();
    }


}
