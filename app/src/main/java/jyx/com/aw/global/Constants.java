package jyx.com.aw.global;

import android.os.Environment;

import java.io.File;

/**
 * 作者：yongzhou on 16/6/14 13:41
 * 邮箱：zhouyong@cdzmd.com
 * 功能：主要放一些常量
 */
public interface Constants {

    //网络缓存地址
    String NET_WORK_DIR = "netWordCache";
    //list最小加载数
    int LEAST_LOAD_NUM = 10;
    int LEAST_EXPERIENCE_LOAD_NUM = 20;

    /**
     * 友盟推送别称类型
     */
    String UMENG_PUSH_ALIAS_TYPE = "zmd";

    String STATUS_VOICE = "_voice"; //声音
    String STATUS_VIBRATE = "_vibrate";//振动
    String IS_NOTIFY_JUMP = "is_notify_jump"; // 是否是通知栏点击跳转


        /* 广播Action定义 start */
    /**
     * 用户登录广播
     */
    String BR_ACTION_USER_LOGIN = "com.zmd.base.action.user.login";
    /**
     * 用户登出广播
     */
    String BR_ACTION_USER_LOGOUT = "com.zmd.base.action.user.logout";

    /**
     * 用户登出广播
     */
    String BR_ACTION_DELETE_DB = "com.zmd.base.action.delete.db";
    /**
     * 新消息广播
     */
    String BR_ACTION_NEW_MSG = "com.zmd.base.action.msg.new_msg";
    /**
     * 授权失败(被登出)广播
     */
    String BR_ACTION_USER_AUTH_ERR = "com.zmd.base.action.user.auth_err";

    /**
     * 关闭GUIDE自身广播
     */
    String BR_ACTION_GUIDE_CLOSE_SELF = "com.zmd.base.action.guide.close.self";

    /**
     * 友盟推送消息广播
     */
    String BR_ACTION_UMENG_PUSH_MSG = "com.zmd.base.action.msg.umeng_push_msg";
    /* 广播Action定义 end */

    /**
     * 游客
     */
    String BR_ACTION_UN_LOGIN_UPDATE_BIRTHDATE = "com.zmd.base.action.un.login.update_birth";

    String TAG = "_tag";
    String OBJECT = "_obj";

    /* SD卡APP文件夹相关 start */
    /**
     * APP SD卡根目录名称
     */
    String APP_DIR_BASE_NAME = "lohos_card_base";
    /**
     * APP SD卡根目录路径
     */
    String APP_SDCARD_BASE_DIR_PATH = Environment.getExternalStorageDirectory() + File.separator + APP_DIR_BASE_NAME + File.separator;
    /**
     * Http数据缓存路径
     */
    String APP_DATA_CACHE_PATH = APP_SDCARD_BASE_DIR_PATH + "dCache";
    /**
     * 错误日志文件存储路径
     */
    String APP_CRASH_FILE_PATH = APP_SDCARD_BASE_DIR_PATH + "crash";

    /**
     * 上传图片临时目录
     */
    String APP_IMG_TEMP_FILE_PATH = APP_SDCARD_BASE_DIR_PATH + "imgTemp";

    /**
     * 更新apk地址
     */
    String APP_APK_CACHE_PATH = APP_SDCARD_BASE_DIR_PATH + "Apk";
    /* SD卡APP文件夹相关 end */

}
