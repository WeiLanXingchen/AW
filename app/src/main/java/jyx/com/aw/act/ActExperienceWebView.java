package jyx.com.aw.act;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
import jyx.com.aw.R;
import jyx.com.aw.base.BaseRefreshActivity;
import jyx.com.aw.global.Constants;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.util.MLog;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.CustomWebView;
import jyx.com.aw.view.swipeback.SwipeBackModeEnum;

/**
 * Created by JiangYunxian on 2017/12/7 0007.
 * 功能：
 */
@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
public class ActExperienceWebView extends BaseRefreshActivity {
    @Bind(R.id.forum_web_view)
    protected CustomWebView mWebView;
    private boolean isSelfHandlerLoadingView = false;
    private boolean isRefresh = false;
    private boolean isUseWebPageTitle = false;
    private String mOriginalUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_detail);
        initViews();

        setClickRelodListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mWebView.getWebView())mWebView.getWebView().reload();
            }
        });
    }

    @Override
    protected void addFooterView(View v) {
        super.addFooterView(v);
        View comment_view = LayoutInflater.from(this).inflate(R.layout.footer_comment, null);
        mWebView.addView(comment_view);
    }

    @Override
    protected void addFooterView(int layoutResID) {
        super.addFooterView(layoutResID);
        View comment_view = LayoutInflater.from(this).inflate(R.layout.experience_comment, null);
        mWebView.addView(comment_view);
    }

    private void initViews() {
        setSwipeMode(SwipeBackModeEnum.LEFT);
        mWebView.setReadCache(false);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString(Constants.TAG, "资讯详情");
            int id = getIntent().getIntExtra("ID",2);
            String mOriginalUrl="http://api-v2.okaoyan.com:8010/api/v2/general/article/"+String.valueOf(id)+"/show";
//            mOriginalUrl = bundle.getString(Constants.OBJECT);
            mOriginalUrl = mOriginalUrl.trim();
            isRefresh = bundle.getBoolean("isRefresh", false);
//            isUseWebPageTitle = bundle.getBoolean(Properties.IS_USEWEBPAGETITLE, false);
            if (!isUseWebPageTitle) {
                mBar.setTitle(title);
                mBar.setRightImage(R.drawable.share);
            }
            if (!TextUtils.isEmpty(mOriginalUrl)) {
                MLog.e("weburl", mOriginalUrl);
                mWebView.getWebView().loadUrl(mOriginalUrl);
            }
        } else {
            AppUtil.showToast("参数有误");
        }

        setRefreshEnabled(isRefresh);


        mWebView.addCustomWebViewListener(new CustomWebView.SimpleCustomWebViewListener() {
            @Override
            public void onPageFinished(String url, boolean isLoadErr) {
                if (!isSelfHandlerLoadingView) {
                    dismissLoadingView();
                }

                if (!isLoadErr && isUseWebPageTitle) {
                    String title = mWebView.getWebView().getTitle();
                    mBar.setTitle(title);
                    mBar.setRightImage(R.drawable.share);
                }
                if (isRefresh) {
                    refreshComplete();
                }
                if (isLoadErr) {
                    showLoadErrorView();
                }


            }

        });
    }

    /**
     * 设置继承类自己去处理LoadingView显示隐藏逻辑(默认false)
     *
     * @param isSelfHandlerLoadingView
     */
    protected void setSelfHandlerLoadingView(boolean isSelfHandlerLoadingView) {
        this.isSelfHandlerLoadingView = isSelfHandlerLoadingView;
    }

    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {
        mWebView.getWebView().reload();
    }

    private void back() {
        if (mWebView.getWebView() != null && mWebView.getWebView().canGoBack()) {
            MLog.e("地址", mWebView.getWebView().getUrl());
            mWebView.getWebView().goBack();
        } else {
            finish();
        }


    }

    public void setUseWebPageTitle(boolean isUseWebPageTitle) {
        this.isUseWebPageTitle = isUseWebPageTitle;
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @OnClick({R.id.layout_left, R.id.layout_right})
    public void onClick(View v) {
        if (AppUtil.isFastClick()) return;
        switch (v.getId()) {
            case R.id.layout_left:
                back();
                break;
        }
    }

    /**
     * @param url       url
     * @param title     显示标题
     * @param isRefresh 是否可以下拉刷新（下拉刷新操作是重新加载页面）
     * @return
     */
    public static Bundle getJumpBundle(String url, String title, boolean isRefresh) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.OBJECT, url);
        bundle.putString(Constants.TAG, title);
        bundle.putBoolean("isRefresh", isRefresh);
        return bundle;
    }



}

