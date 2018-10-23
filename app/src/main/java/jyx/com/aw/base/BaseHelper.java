package jyx.com.aw.base;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import jyx.com.aw.view.ReloadView;
import jyx.com.aw.view.WaitingDialog;


/**
 * Base类中复用方法工具类
 * Created by wg on 15/8/14.
 */
public class BaseHelper {
    // 加载中弹出框
    private WaitingDialog mWaitingDialog;
    private ReloadView mReloadView;
    private Activity mActivity;

    public void init(Activity mActivity, ReloadView mReloadView) {
        this.mReloadView = mReloadView;
        this.mActivity = mActivity;
    }

    public void setLoadViewTopMargin(View... views) {
        setLoadViewTopMargin(0, views);
    }

    public void setLoadViewTopMargin(int expHeight, View... views) {
        setLoadViewMargin(mReloadView, expHeight, 1, views);
    }

    public void setLoadViewBottomMargin(int expHeight, View... views) {
        setLoadViewMargin(mReloadView, expHeight, 2, views);
    }

    /**
     * 设置LoadView间距
     * @param mReloadView ReloadView
     * @param expHeight   扩展高度
     * @param direction   方向 1 上 2 下 3 左 4 右
     * @param views
     */
    private void setLoadViewMargin(final ReloadView mReloadView, final int expHeight, final int direction, final View... views) {
        if (null != mReloadView && null != views && views.length > 0) {
            views[0].getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        views[0].getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        views[0].getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                    int height = 0;
                    for (View v : views) {
                        height += v.getMeasuredHeight();
                    }

                    if (expHeight > 0) {
                        height += expHeight;
                    }

                    ViewGroup.MarginLayoutParams params = ((ViewGroup.MarginLayoutParams) mReloadView.getLayoutParams());
                    switch (direction) {
                        case 1: // 上
                            params.setMargins(params.leftMargin, height, params.rightMargin, params.bottomMargin);
                            break;
                        case 2: // 下
                            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, height);
                            break;
                        case 3: // 左
                            params.setMargins(height, params.topMargin, params.rightMargin, params.bottomMargin);
                            break;
                        case 4: // 右
                            params.setMargins(params.leftMargin, params.topMargin, height, params.bottomMargin);
                            break;
                    }
                }
            });
        }
    }

    public void showWaitingDialog() {
        showWaitingDialog("加载中，请稍后...");
    }

    public void showWaitingDialog(String msg) {
        if (mWaitingDialog == null) {
            mWaitingDialog = new WaitingDialog(mActivity);
            mWaitingDialog.setCanceledOnTouchOutside(false);
            mWaitingDialog.setMessage(msg);
        }
        if (!mWaitingDialog.isShowing()) {
            mWaitingDialog.show();
        }
    }

    public void dismissWaitingDialog() {
        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }

    public void showLoadingView() {
        showLoadingView(null);
    }

    public void showLoadingView(String msg) {
        if (null != mReloadView) {
            mReloadView.showLoad(msg);
        }
    }

    public void showLoadErrorView() {
        showLoadErrorView(null);
    }

    public void showLoadErrorView(String msg) {
        if (null != mReloadView) {
            mReloadView.showReload();
        }
    }

    public void showLoadErrorView(String msg, boolean isHideIcon) {
        if (null != mReloadView) {
            mReloadView.setDisplayNetErrIcon(isHideIcon);
            mReloadView.showReload(msg);
        }
    }

    protected boolean isShowLoading() {
        if (null != mReloadView) {
            return mReloadView.isLoading();
        }

        return false;
    }

    public void setClickRelodListener(View.OnClickListener listener) {
        if (null != mReloadView) {
            mReloadView.setClickRelodListener(listener);
        }
    }

    public void dismissLoadingView() {
        dismissLoadingView(true);
    }

    public void dismissLoadingView(boolean isShowFadeAnim) {
        if (null != mReloadView) {
            mReloadView.hideAll(isShowFadeAnim);
        }
    }

    public void setReloadView(ReloadView mReloadView) {
        if (null != this.mReloadView) {
            this.mReloadView.setVisibility(View.GONE);
        }

        this.mReloadView = mReloadView;
    }

    public ReloadView getReloadView() {
        return this.mReloadView;
    }

}
