package jyx.com.aw.act;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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
 * Created by JiangYunxian on 2017/12/15 0015.
 * 功能：
 */
@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
public class ActBuyInformationInner extends BaseRefreshActivity implements View.OnClickListener{
    @Bind(R.id.forum_web_view)
    protected CustomWebView mWebView;

    private boolean isSelfHandlerLoadingView = false;
    private boolean isRefresh = false;
    private boolean isUseWebPageTitle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_detail);
        addBottomView();
        String inner_image = getIntent().getStringExtra("inner_image");
        String title = getIntent().getStringExtra("title");

        initViews(inner_image,title);
        setClickRelodListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mWebView.getWebView()) mWebView.getWebView().reload();
            }
        });
    }

    private void addBottomView() {
        View buy_inner_footer = LayoutInflater.from(this).inflate(R.layout.buy_inner_footer, null);
        Button mButtonBuyOnTel= (Button) buy_inner_footer.findViewById(R.id.mButtonBuyOnTel);
        Button mButtonBuyOnQQ= (Button) buy_inner_footer.findViewById(R.id.mButtonBuyOnQQ);
        mButtonBuyOnTel.setOnClickListener(this);
        mButtonBuyOnQQ.setOnClickListener(this);
        addFooterView(buy_inner_footer);
    }


    private void initViews(String mOriginalUrl,String title) {
        setSwipeMode(SwipeBackModeEnum.LEFT);
        mWebView.setReadCache(false);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
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
            case R.id.mButtonBuyOnTel:
                // 获取号码
                String number = "18652007735";
                if (TextUtils.isEmpty(number)){
                    // 提醒用户
                    AppUtil.showToast("号码不能为空");
                }else{
                    // 拨号：激活系统的拨号组件
                    Intent intent = new Intent(); // 意图对象：动作 + 数据
                    intent.setAction(Intent.ACTION_CALL); // 设置动作
                    Uri data = Uri.parse("tel:" + number); // 设置数据
                    intent.setData(data);
                    startActivity(intent); // 激活Activity组件
                }
                break;
            case R.id.mButtonBuyOnQQ:
                String qqNum="2267347251";
                if (checkApkExist(this, "com.tencent.mobileqq")){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+qqNum+"&version=1")));
                }else{
                    AppUtil.showToast("本机未安装QQ应用");
                }
                break;
        }
    }
    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
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
