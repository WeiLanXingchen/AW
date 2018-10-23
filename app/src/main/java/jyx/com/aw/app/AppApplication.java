package jyx.com.aw.app;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

import java.io.File;

import jyx.com.aw.R;
import jyx.com.aw.global.Constants;


/**
 * 作者：yongzhou on 16/6/14
 * 邮箱：zhouyong@cdzmd.com
 * 功能：application
 */
public class AppApplication extends Application {
    private static AppApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        //解决Glide已经默认为ImageView设置的Tag
        ViewTarget.setTagId(R.id.zmd_glide_tag);

        //判断创建APP根目录
        File appDir = new File(Constants.APP_SDCARD_BASE_DIR_PATH);
        if (!appDir.exists()) {
            appDir.mkdir();
        }

    }

    /**
     * 返回全局App
     *
     * @return
     */
    public static AppApplication getAppContext() {
        return mInstance;
    }

}
