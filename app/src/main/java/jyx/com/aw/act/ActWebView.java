package jyx.com.aw.act;

/**
 * Created by JiangYunxian on 2017/11/10 0010.
 * 功能：
 */
        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.webkit.JavascriptInterface;


        import butterknife.Bind;
        import butterknife.OnClick;
        import jyx.com.aw.R;
        import jyx.com.aw.base.BaseRefreshActivity;
        import jyx.com.aw.global.Constants;
        import jyx.com.aw.global.Properties;
        import jyx.com.aw.util.AppUtil;
        import jyx.com.aw.util.MLog;
        import jyx.com.aw.view.CustomSwipeToRefresh;
        import jyx.com.aw.view.CustomWebView;
        import jyx.com.aw.view.swipeback.SwipeBackModeEnum;

/**
 * 公用网页显示页面
 * Created by apple on 15/3/25.
 */
@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
public class ActWebView extends BaseRefreshActivity {

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
                if (null != mWebView.getWebView()) mWebView.getWebView().reload();
            }
        });
    }



    private void initViews() {

        setSwipeMode(SwipeBackModeEnum.LEFT);
        mWebView.setReadCache(false);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString(Constants.TAG, "考研日报");
            int id = getIntent().getIntExtra("ID",2);
            String pic = getIntent().getStringExtra("pic");
            String mOriginalUrl="http://114.55.174.129:8010/api/v3/general/kaoyan_daily/"+String.valueOf(id)+"/show?"+pic;
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
