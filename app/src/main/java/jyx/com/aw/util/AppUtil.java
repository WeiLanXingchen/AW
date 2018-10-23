package jyx.com.aw.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jyx.com.aw.R;
import jyx.com.aw.app.AppApplication;
import jyx.com.aw.global.Constants;


/**
 * 作者：yongzhou on 16/6/15
 * 邮箱：zhouyong@cdzmd.com
 * 功能：程序工具类
 */
public class AppUtil {

    /**
     * 得到设备屏幕的宽度
     */
    public static int getScW() {
        return getDisplayMetrics(AppApplication.getAppContext()).widthPixels;
    }

    /**
     * 得到设备屏幕的高度
     */
    public static int getScH() {
        return getDisplayMetrics(AppApplication.getAppContext()).heightPixels;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 得到设备屏幕
    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }


    /**
     * 获取通知栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int ret = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            ret = context.getResources().getDimensionPixelSize(resourceId);
        }
        return ret;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        Context context = AppApplication.getAppContext();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)
     */
    public static float sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 显示提示信息
     *
     * @param resId
     */
    public static Toast showToast(int resId) {
        String msg = AppApplication.getAppContext().getResources().getString(resId);
        return showToast(msg);
    }

    /**
     * 显示提示信息
     *
     * @param msg
     */
    public static Toast showToast(String msg) {
        return showToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示提示信息
     *
     * @param msg
     */
    public static Toast showToast(String msg, int duration) {
        Toast toast = Toast.makeText(AppApplication.getAppContext(), msg, duration);
        TextView textview = new TextView(AppApplication.getAppContext());
        textview.setBackgroundResource(R.drawable.btn_black_deep_tran_radius);
        textview.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        textview.setText(msg);
        textview.setGravity(Gravity.CENTER);
        textview.setTextColor(Color.WHITE);
        int padding = dip2px(AppApplication.getAppContext(), 12);
        textview.setPadding(padding, padding, padding, padding);
//        toast.setGravity(Gravity.TOP, 0, dip2px(AppApplication.getAppContext(), 72));
        toast.setView(textview);
        toast.show();
        return toast;
    }

    private static long lastClickTime; // 上一次点击的时间

    /**
     * 避免重复点击
     *
     * @return
     */
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 隐藏键盘
     *
     * @param context
     */
    public static void hideKeyBoard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isSuc = imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        if (!isSuc && v instanceof EditText) {
            v.clearFocus();
        }
    }

    /**
     * 判断是否属于手机号码
     *
     * @param num
     * @return
     */
    public static boolean isPhoneNum(String num) {
//        String str = "^1[3578]\\d{9}$";
        String str = "^1\\d{10}$";
        return getMatcher(str, num).matches();
    }

    // 获取正则Matcher
    private static Matcher getMatcher(String pattern, String tar) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(tar.replace(" ", ""));
    }

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean hasNetWork(Context context) {
        return NetStateUtil.getNetState(context) != -1;
    }

    /**
     * 返回HTTP请求时间格式
     *
     * @return
     */
    public static String getHttpReqTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 返回HTTP请求签名
     *
     * @return
     */
    public static String getHttpReqSign(Object obj) {
        return AppCoreEncryptionUtil.getSign(objToJson(obj));
    }


    /**
     * 对象转换为json字符串
     *
     * @param obj
     * @return
     */
    public static String objToJson(Object obj) {
        if (null == obj) {
            return null;
        }

        return new Gson().toJson(obj);
    }

    /**
     * json字符串转换为对象
     *
     * @param json
     * @param classEntity
     * @param <T>
     * @return
     */
    public static <T> T jsonToObj(String json, Class<T> classEntity) {
        if (TextUtils.isEmpty(json) || null == classEntity) {
            return null;
        }

        return new Gson().fromJson(json, classEntity);
    }

    /**
     * 获取进程名称
     *
     * @param cxt
     * @param pid
     * @return
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }


    /**
     * 获取图片缓存路径
     *
     * @param fileName 图片名
     * @return
     */
    public static String getTempImagePath(String fileName) {
        File file = new File(Constants.APP_IMG_TEMP_FILE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        return  file.getAbsolutePath() + File.separator + fileName;
    }
}
