package jyx.com.aw.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import jyx.com.aw.R;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.ReloadView;

/**
 * 作者：yongzhou on 16/6/20
 * 邮箱：zhouyong@cdzmd.com
 * 功能：一般就一个标题栏的列表界面
 */
public abstract class BaseRefreshActivity extends BaseActivity {
    private CustomSwipeToRefresh mCvCustomSwipeToRefresh;
    private FrameLayout mFlTop;
    private FrameLayout mFlFooter;
    private View mRootContainer;
    private ReloadView mReloadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        mRootContainer = View.inflate(this, R.layout.fm_base_refresh, null);

        mCvCustomSwipeToRefresh = ButterKnife.findById(mRootContainer, R.id.cv_customSwipeToRefresh);
        mFlTop = ButterKnife.findById(mRootContainer, R.id.fl_topContainer);
        mFlFooter = ButterKnife.findById(mRootContainer, R.id.fl_bottomContainer);

        mReloadView = ButterKnife.findById(mRootContainer, R.id.load_view);

        mCvCustomSwipeToRefresh.setColorSchemeColors(Color.parseColor("#FF5000"), Color.RED, Color.YELLOW, Color.GREEN);
        mCvCustomSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseRefreshActivity.this.onRefreshBegin(mCvCustomSwipeToRefresh);
            }
        });
    }

    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        this.setContentView(view, new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT, SwipeRefreshLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mCvCustomSwipeToRefresh.addView(view, params);

        super.setContentView(mRootContainer);
        // 使用当前ReloadView
        setReloadView(mReloadView);
    }

    /**
     * 设置可以下拉刷新的View id
     * @param ids view ids
     */
    protected void setRefreshView(int... ids) {
        mCvCustomSwipeToRefresh.setSwipeableChildren(ids);
    }

    /**
     * 位置RefreshView的上面，RefreshView滚动不会将其往上顶
     */
    protected void addTopView(int layoutResID) {
        addTopView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    /**
     * 位置RefreshView的上面，RefreshView滚动不会将其往上顶
     */
    protected void addTopView(View v) {
        if (null != v) {
            mFlTop.addView(v);
            mFlTop.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 位置RefreshView的下面，RefreshView滚动不会将其往上顶
     */
    protected void addFooterView(int layoutResID) {
        addFooterView(LayoutInflater.from(this).inflate(layoutResID, null));
    }

    /**
     * 位置RefreshView的下面，RefreshView滚动不会将其往上顶
     */
    protected void addFooterView(View v) {
        if (null != v) {
            mFlFooter.addView(v);
            mFlFooter.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 下拉刷新
     * @param customSwipeToRefresh
     */
    protected abstract void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh);

    /**
     * 设置是否可以下拉刷新
     *
     * @param isEnabled
     */
    public void setRefreshEnabled(boolean isEnabled) {
        mCvCustomSwipeToRefresh.setEnabled(isEnabled);
    }

    /**
     * 是否支持下载刷新
     * @return
     */
    public boolean isCanRefresh() {
        return mCvCustomSwipeToRefresh.isEnabled();
    }

    /**
     * 自动刷新
     */
    protected void autoRefresh() {
        mCvCustomSwipeToRefresh.setRefreshing(true);
        // 间隔一秒后再回调
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BaseRefreshActivity.this.onRefreshBegin(mCvCustomSwipeToRefresh);
            }
        }, 1000);
    }

    /**
     * 下拉刷新完成
     */
    protected void refreshComplete() {
        mCvCustomSwipeToRefresh.setRefreshing(false);
    }

    /**
     * 是否正在下拉刷新
     * @return
     */
    protected boolean isRefreshing() {
        return mCvCustomSwipeToRefresh.isRefreshing();
    }
}
