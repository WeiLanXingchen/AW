package jyx.com.aw.util;

import android.util.Log;

import jyx.com.aw.global.Config;


/**
 * 统一管理log类
 * 
 */
public class MLog {
    private static final String TAG = "LogUtils";

    public static void v(String tag, String msg)
    {
        if (Config.DEBUG)
        {
            Log.v(TAG, tag + "-->" + msg);
        }
    }

    public static void d(String tag, String msg)
    {
        if (Config.DEBUG)
        {
            Log.d(TAG, tag + "-->" + msg);
        }
    }

    public static void i(String tag, String msg)
    {
        if (Config.DEBUG)
        {
            Log.i(TAG, tag + "-->" + msg);
        }
    }

    public static void w(String tag, String msg)
    {
        if (Config.DEBUG)
        {
            Log.v(TAG, tag + "-->" + msg);
        }
    }

    public static void e(String tag, String msg)
    {
        if (Config.DEBUG)
        {
            Log.e(TAG, tag + "-->" + msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr)
    {
        if (Config.DEBUG)
        {
            Log.e(TAG, tag + "-->" + msg);
        }
    }
}
