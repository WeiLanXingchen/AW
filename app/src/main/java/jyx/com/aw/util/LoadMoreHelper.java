package jyx.com.aw.util;

import android.widget.ListView;

import jyx.com.aw.app.AppApplication;
import jyx.com.aw.view.GridViewWithHeaderAndFooter;


/**
 * 加载更多辅助类
 * Created by wg on 15/11/04.
 */
public abstract class LoadMoreHelper implements FooterScrollListener.OnLoadMoreListener {
    private ListView mListView;
    private GridViewWithHeaderAndFooter mGridViewWithHeaderAndFooter;
    private FooterScrollListener mScrollListener;

    public LoadMoreHelper(ListView listview) {
        if (listview == null) return;

        this.mListView = listview;
        mScrollListener = new FooterScrollListener(AppApplication.getAppContext(), mListView, this);
        mListView.setOnScrollListener(mScrollListener);
    }

    public LoadMoreHelper(GridViewWithHeaderAndFooter gridViewWithHeaderAndFooter) {
        if (gridViewWithHeaderAndFooter == null) return;

        this.mGridViewWithHeaderAndFooter = gridViewWithHeaderAndFooter;
        mScrollListener = new FooterScrollListener(AppApplication.getAppContext(), mGridViewWithHeaderAndFooter, this);
        mGridViewWithHeaderAndFooter.setOnScrollListener(mScrollListener);
    }

    public FooterScrollListener getScrollListener() {
        return mScrollListener;
    }

    /**
     * 设置加载更多状态
     * @param state
     */
    public void setFooterState(FooterScrollListener.FooterState state) {
        mScrollListener.setFootState(state);
    }

    /**
     * 设置加载更多是否可用
     * @param enabled
     */
    public void setFooterEnabled(boolean enabled) {
        mScrollListener.setEnabled(enabled);
    }

    @Override
    public void onLoad() {
        onLoadMoreBegin();
    }

    public abstract void onLoadMoreBegin();

}
