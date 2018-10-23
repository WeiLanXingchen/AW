package jyx.com.aw.util;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import jyx.com.aw.R;
import jyx.com.aw.view.GridViewWithHeaderAndFooter;


/**
 * Created by apple on 15/3/30.
 */
public class FooterScrollListener implements AbsListView.OnScrollListener, View.OnClickListener {

    public enum FooterState {NORMAL, LOADING}

    private FrameLayout mFooterLoadMore;
    private ListView mListView;
    private GridViewWithHeaderAndFooter mGridViewWithHeaderAndFooter;
    private OnLoadMoreListener mOnLoadListener;
    private OnScrollUpDownListener mScrollUpDownListener;
    private FooterState mState = FooterState.NORMAL;
    private TextView mTvFooterLoadMoreState1;
    private LinearLayout mLlFooterLoadMoreState2;
    private boolean mEnabled = true;

    private int mLastScrollY;
    private int mPreviousFirstVisibleItem;
    private int mScrollThreshold = 10;

    private int type = 1; // 1 ListView 2 GridView

    public FooterScrollListener(Context context, ListView listview, OnLoadMoreListener listener) {
        type = 1;
        this.mListView = listview;
        this.mOnLoadListener = listener;
        mListView.addFooterView(getFooterLoadMoreView(context));
    }

    public FooterScrollListener(Context context, GridViewWithHeaderAndFooter gridViewWithHeaderAndFooter, OnLoadMoreListener listener) {
        type = 2;
        this.mGridViewWithHeaderAndFooter = gridViewWithHeaderAndFooter;
        this.mOnLoadListener = listener;
        mGridViewWithHeaderAndFooter.addFooterView(getFooterLoadMoreView(context));
    }

    private View getFooterLoadMoreView(Context context) {
        mFooterLoadMore = (FrameLayout) View.inflate(context, R.layout.footer_load_more, null);
        mTvFooterLoadMoreState1 = (TextView)mFooterLoadMore.findViewById(R.id.tv_footerLoadMoreState1);
        mLlFooterLoadMoreState2 = (LinearLayout)mFooterLoadMore.findViewById(R.id.ll_footerLoadMoreState2);
        mFooterLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnLoadListener) {
                    setFootState(FooterState.LOADING);
                    mOnLoadListener.onLoad();
                }
            }
        });
        return mFooterLoadMore;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState == SCROLL_STATE_IDLE) {
            // 滚动时到了最底部也可以加载更多
            if (isBottom() && mOnLoadListener != null && mEnabled && mState == FooterState.NORMAL) {
                setFootState(FooterState.LOADING);
                mOnLoadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(totalItemCount == 0 || mScrollUpDownListener == null) return;
        if (firstVisibleItem == mPreviousFirstVisibleItem) {
            int newScrollY = getTopItemScrollY();
            boolean isSignificantDelta = Math.abs(mLastScrollY - newScrollY) > mScrollThreshold;
            if (isSignificantDelta) {
                if (mLastScrollY > newScrollY) {
                    mScrollUpDownListener.onScrollUp();
                } else {
                    mScrollUpDownListener.onScrollDown();
                }
            }
            mLastScrollY = newScrollY;
        } else {
            if (firstVisibleItem > mPreviousFirstVisibleItem) {
                mScrollUpDownListener.onScrollUp();
            } else {
                mScrollUpDownListener.onScrollDown();
            }

            mLastScrollY = getTopItemScrollY();
            mPreviousFirstVisibleItem = firstVisibleItem;
        }
        if(firstVisibleItem==0 && getTopItemScrollY() == 0) {
            mScrollUpDownListener.onTop();
        }
    }

    private int getTopItemScrollY() {
        View topChild = null;
        switch (type) {
            case 1:
                // ListView
                if (mListView == null || mListView.getChildAt(0) == null) return 0;
                topChild = mListView.getChildAt(0);
                break;
            case 2:
                // GridView
                if (mGridViewWithHeaderAndFooter == null || mGridViewWithHeaderAndFooter.getChildAt(0) == null) return 0;
                topChild = mGridViewWithHeaderAndFooter.getChildAt(0);
                break;
        }

        return null == topChild ? 0 : topChild.getTop();
    }

    public void setScrollUpDownListener(OnScrollUpDownListener onScrollUpDownListener) {
        mScrollUpDownListener = onScrollUpDownListener;
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {
        switch (type) {
            case 1:
                // ListView
                if (mListView != null && mListView.getAdapter() != null) {
                    return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
                }
                break;
            case 2:
                // GridView
                if (mGridViewWithHeaderAndFooter != null && mGridViewWithHeaderAndFooter.getAdapter() != null) {
                    return mGridViewWithHeaderAndFooter.getLastVisiblePosition() == (mGridViewWithHeaderAndFooter.getAdapter().getCount() - 1);
                }
                break;
        }

        return false;
    }

    /**
     * 设置加载更多状态
     * @param state
     */
    public void setFootState(FooterState state) {
        if (mState == state) return ;

        mState = state;
        switch (mState) {
            case LOADING:
                showFooterLoadMore(2);
                break;
            case NORMAL:
                showFooterLoadMore(1);
                break;
        }
    }

    /**
     * 设置加载更多是否可用
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        if (mEnabled == enabled) return ;

        if (enabled) {
            switch (type) {
                case 1:
                    // ListView
                    if (mListView != null) {
                        mListView.addFooterView(mFooterLoadMore);
                    }
                    break;
                case 2:
                    // GridView
                    if (mGridViewWithHeaderAndFooter != null) {
                        mGridViewWithHeaderAndFooter.addFooterView(mFooterLoadMore);
                    }
                    break;
            }
        } else {
            switch (type) {
                case 1:
                    // ListView
                    if (mListView != null) {
                        mListView.removeFooterView(mFooterLoadMore);
                    }
                    break;
                case 2:
                    // GridView
                    if (mGridViewWithHeaderAndFooter != null) {
                        mGridViewWithHeaderAndFooter.removeFooterView(mFooterLoadMore);
                    }
                    break;
            }
        }

        this.mEnabled = enabled;
    }

    /**
     * 设置显示footer加载更多显示内容
     * @param state 1 显示提示点击加载更多，2 加载更多中
     */
    private void showFooterLoadMore(int state) {
        if (state == 1) {
            mTvFooterLoadMoreState1.setVisibility(View.VISIBLE);
            mLlFooterLoadMoreState2.setVisibility(View.GONE);
        } else {
            mLlFooterLoadMoreState2.setVisibility(View.VISIBLE);
            mTvFooterLoadMoreState1.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 加载更多的监听器
     *
     * @author mrsimple
     */
    public interface OnLoadMoreListener {
        void onLoad();
    }

    public interface OnScrollUpDownListener {
        void onScrollUp();
        void onScrollDown();
        void onTop();
    }


}
