package jyx.com.aw.act;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jyx.com.aw.R;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.base.BaseRefreshActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.view.CustomSwipeToRefresh;

/**
 * Created by JiangYunxian on 2017/11/9 0009.
 * 功能：
 */
public class DailyDetail extends BaseRefreshActivity{
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_detail);
        webView= (WebView) findViewById(R.id.forum_web_view);
        dismissLoadingView();
        mBar.setVisibility(View.GONE);
        int id = getIntent().getIntExtra("ID",2);
        String pic = getIntent().getStringExtra("pic");
        String detailPic="http://114.55.174.129:8010/api/v3/general/kaoyan_daily/"+String.valueOf(id)+"/show?"+pic;
        webView.setWebChromeClient(new WebChromeClient() {
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(detailPic);
//        Log.i("@@@",id+" "+pic);
//        Log.i("@@@","http://114.55.174.129:8010/api/v3/general/kaoyan_daily/"+String.valueOf(id)+"/show?"+pic);


    }

    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {

    }
}
