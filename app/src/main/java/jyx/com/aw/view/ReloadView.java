package jyx.com.aw.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import jyx.com.aw.R;


/**
 * Created by aman on 15/4/2.
 */
public class ReloadView extends LinearLayout implements View.OnClickListener {
    private static final String DEF_SHOW_MSG = "数据加载失败";

    private TextView mTvLoadingShow;
    private TextView mTvErrMsg;
    private ImageView mTvNetErrIcon;
    private LinearLayout mLlLoadingContainer;
    private LinearLayout mLlLoadErrContainer;

    private OnClickListener onReloadCLickListener;
    private boolean isLoading = true;

    private Animation hideAnim;

    public ReloadView(Context context) {
        this(context, null);
    }

    public ReloadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    private void initViews(Context context) {
        inflate(context, R.layout.common_reloading, this);

        hideAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out);
        hideAnim.setDuration(100);

        mLlLoadingContainer = (LinearLayout) findViewById(R.id.ll_loadingContainer);
        mLlLoadErrContainer = (LinearLayout) findViewById(R.id.ll_loadErrContainer);
        mTvLoadingShow = (TextView) findViewById(R.id.tv_loading_show);
        mTvErrMsg = (TextView) findViewById(R.id.tv_errMsg);
        mTvNetErrIcon = (ImageView) findViewById(R.id.tv_netErrIcon);
        findViewById(R.id.btn_retry).setOnClickListener(this);
    }

    public void setClickRelodListener(OnClickListener onReloadCLickListener) {
        this.onReloadCLickListener = onReloadCLickListener;
    }

    public void setDisplayNetErrIcon(boolean isHideIcon) {
        if (isHideIcon) {
            mTvNetErrIcon.setVisibility(View.GONE);
        } else {
            mTvNetErrIcon.setVisibility(View.VISIBLE);
        }
    }

    public void showReload() {
        showReload(null);
    }

    public void showReload(String msg) {
        isLoading = false;

        setVisibility(View.VISIBLE);
        mLlLoadErrContainer.setVisibility(View.VISIBLE);
        mLlLoadingContainer.setVisibility(View.GONE);

        mTvErrMsg.setText(TextUtils.isEmpty(msg) ? DEF_SHOW_MSG : msg);
    }

    public void showLoad() {
        showLoad("加载中...");
    }

    public void showLoad(String msg) {
        if (getVisibility() == View.VISIBLE &&
                mLlLoadingContainer.getVisibility() == View.VISIBLE) {
            return;
        }

        if (TextUtils.isEmpty(msg)) {
            showLoad();
            return;
        }

        isLoading = true;

        setVisibility(View.VISIBLE);
        mLlLoadErrContainer.setVisibility(View.GONE);
        mLlLoadingContainer.setVisibility(View.VISIBLE);
        mTvLoadingShow.setText(msg);
    }

    public void hideAll() {
        hideAll(true);
    }

    public void hideAll(boolean isShowFadeAnim) {
        if (isShowFadeAnim && getVisibility() == View.VISIBLE) {
            // 动画隐藏
            startAnimation(hideAnim);
        }
        setVisibility(View.GONE);
        isLoading = false;
    }

    @Override
    public void onClick(final View v) {
        if (null == onReloadCLickListener || mLlLoadErrContainer.getVisibility() != View.VISIBLE) {
            return;
        }

        showLoad();
        // 转动2秒后再结束
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onReloadCLickListener.onClick(v);
            }
        }, 2000);
    }

    public boolean isLoading() {
        return isLoading;
    }

}
