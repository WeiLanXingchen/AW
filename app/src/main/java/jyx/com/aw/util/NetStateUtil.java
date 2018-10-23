package jyx.com.aw.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import jyx.com.aw.app.AppApplication;


/**
 * 功能：网络状态助手
 */

public class NetStateUtil {
    /**
     * 没有网络
     */
    public static final int NET_DISABLE = -1;

    /**
     * 手机网络
     */
    public static final int NET_MOBILE = 2;

    /**
     * WIFI网络
     */
    public static final int NET_WIFI = 4;

    /**
     * 获取网络状态
     *
     * @return
     */
    public static int getNetState() {
        if (null != AppApplication.getAppContext()) {
            return getNetState(AppApplication.getAppContext());
        } else {
            return NET_DISABLE;
        }
    }

    /**
     * 获取网络状态
     *
     * @param context
     * @return
     */
    public static int getNetState(Context context) {
        int ns = NET_DISABLE;
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable() && info.isConnected()) {
                ns = NET_WIFI;
                if (info.getType() == ConnectivityManager.TYPE_MOBILE)
                    ns = NET_MOBILE;
            }
        } catch (Exception e) {
            // Log.e(e.getMessage());
            return ns;
        }
        return ns;
    }
}
