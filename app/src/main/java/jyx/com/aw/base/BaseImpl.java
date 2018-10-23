package jyx.com.aw.base;

import android.os.Bundle;
import android.view.View;

import jyx.com.aw.view.ReloadView;


/**
 * Base类中复用方法接口定义
 * Created by apple on 15/8/14.
 */
public interface BaseImpl {
    /**
     * 设置加载View上间距
     * @param views  作为间距参考的View(可以多个)，方法内会自动算出该View的高度
     */
    void setLoadViewTopMargin(View... views);
    /**
     * 设置加载View上间距
     * @param expHeight 额外高度
     * @param views  作为间距参考的View(可以多个)，方法内会自动算出该View的高度
     */
    void setLoadViewTopMargin(int expHeight, View... views);
    /**
     * 设置加载View下间距
     * @param views  作为间距参考的View(可以多个)，方法内会自动算出该View的高度
     */
    void setLoadViewBottomMargin(int expHeight, View... views);


    void turnToActivity(Class<?> clazz, boolean isFinish);
    void turnToActivity(Class<?> clazz, Bundle bundle, boolean isFinish);
    void turnToActivityForResult(Class<?> clazz, int requestCode);
    void turnToActivityForResult(Class<?> clazz, int requestCode, Bundle bundle);


    void showWaitingDialog();
    void showWaitingDialog(String msg);
    void dismissWaitingDialog();


    /**
     * 显示LoadView
     */
    void showLoadingView();
    /**
     * 显示LoadView
     * @param msg load时显示的内容
     */
    void showLoadingView(String msg);
    /**
     * 显示重试View
     */
    void showLoadErrorView();
    /**
     * 显示加载失败
     * @param msg 错误msg
     */
    void showLoadErrorView(String msg);
    /**
     * 显示加载失败
     * @param msg 错误msg
     * @param isHideIcon 为false时不显示加载错误图标（适用于load位置在很小的地方）
     */
    void showLoadErrorView(String msg, boolean isHideIcon);
    /**
     * 设置加载失败点击事件
     * @param listener OnClickListener
     */
    void setClickRelodListener(View.OnClickListener listener);
    /**
     * 隐藏LoadView
     */
    void dismissLoadingView();
    /**
     * 隐藏LoadView
     * @param isShowFadeAnim 是否显示激变动画
     */
    void dismissLoadingView(boolean isShowFadeAnim);
    /**
     * 设置ReloadView
     * @param mReloadView
     */
    void setReloadView(ReloadView mReloadView);
    /**
     * 获取当前ReloadView
     * @return ReloadView
     */
    ReloadView getReloadView();

}
