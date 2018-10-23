package jyx.com.aw.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;


import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.app.AppApplication;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.util.MLog;


/**
 * 自定义WebView，处理一些基础设置相关的逻辑
 * 此类非WebView，而是进行一层包装，需要使用WebView的方法请调用getWebView()获取WebView对象
 * Created by wg on 15/9/22.
 */
@SuppressLint("SetJavaScriptEnabled")
public class CustomWebView extends FrameLayout {
    private List<CustomWebViewListener> mCustomWebViewListener;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private boolean isLoadErr;

    public CustomWebView(Context context) {
        this(context, null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCustomWebViewListener = new ArrayList<>();
        initWebView();
    }

    private void initWebView() {
        mWebView = new WebView(AppApplication.getAppContext());
        mWebView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mWebView);
        initSetting();
    }

    private void initSetting() {
        mWebSettings = mWebView.getSettings();
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);

        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.setWebChromeClient(new OwnWebChromeClient());
        mWebView.setWebViewClient(new OwnWebViewClient());
    }

    public WebView getWebView() {
        return mWebView;
    }

    public void setReadCache(boolean isReadCache) {
        if (isReadCache) {
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        } else {
            mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
    }

    public class OwnWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (null != mCustomWebViewListener && mCustomWebViewListener.size() > 0) {
                for (CustomWebViewListener l : mCustomWebViewListener) {
                    l.onProgressChanged(newProgress);
                }
            }
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            AppUtil.showToast(message);
            return true;
        }

//        @Override
//        public boolean onConsoleMessage(ConsoleMessage cm) {
//
//            MLog.e("onConsoleMessage", cm.message() + " -- From line "
//                    + cm.lineNumber() + " of "
//                    + cm.sourceId());
//
//            return true;
//        }
    }

    public class OwnWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 处此进行一些网页自动跳转
            if (!TextUtils.isEmpty(url) && !url.startsWith("taobao:") && !url.startsWith("tmall:")) {
                view.loadUrl(url);
            }

            if (null != mCustomWebViewListener && mCustomWebViewListener.size() > 0) {
                for (CustomWebViewListener l : mCustomWebViewListener) {
                    l.shouldOverrideUrlLoading(url);
                }
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            isLoadErr = false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (null != mCustomWebViewListener && mCustomWebViewListener.size() > 0) {
                for (CustomWebViewListener l : mCustomWebViewListener) {
                    l.onPageFinished(url, isLoadErr);
                }
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            MLog.e("已经监听的错误:", description);
            isLoadErr = true;

            if (null != mCustomWebViewListener && mCustomWebViewListener.size() > 0) {
                for (CustomWebViewListener l : mCustomWebViewListener) {
                    l.onReceivedError(errorCode, description, failingUrl);
                }
            }
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            MLog.e("onReceivedError:", error.toString());
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            MLog.e("onReceivedSslError:", error.toString());
            handler.proceed();
        }



    }

    public void addCustomWebViewListener(CustomWebViewListener customWebViewListener) {
        if (null != customWebViewListener && !mCustomWebViewListener.contains(customWebViewListener)) {
            this.mCustomWebViewListener.add(customWebViewListener);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroy();
    }

    private void destroy() {
        if (mCustomWebViewListener != null) {
            mCustomWebViewListener.clear();
            mCustomWebViewListener = null;
        }
        removeAllViews();
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }

    }

    public interface CustomWebViewListener {
        void onProgressChanged(int progress);

        void onPageStarted(String url, Bitmap favicon);

        void onPageFinished(String url, boolean isLoadErr);

        void shouldOverrideUrlLoading(String url);

        void onReceivedError(int errorCode, String description, String failingUrl);
    }

    public static class SimpleCustomWebViewListener implements CustomWebViewListener {
        @Override
        public void onPageFinished(String url, boolean isLoadErr) {
        }

        @Override
        public void onPageStarted(String url, Bitmap favicon) {
        }

        @Override
        public void onProgressChanged(int progress) {
        }

        @Override
        public void shouldOverrideUrlLoading(String url) {
        }

        @Override
        public void onReceivedError(int errorCode, String description, String failingUrl) {
        }
    }

}
