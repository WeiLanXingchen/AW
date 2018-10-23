package jyx.com.aw.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jyx.com.aw.R;
import jyx.com.aw.adapter.AbstractBaseAdapter;
import jyx.com.aw.impl.EmptyDataTag;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.util.FooterScrollListener;
import jyx.com.aw.util.LoadMoreHelper;
import jyx.com.aw.view.CommonEmptyView;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.ReloadView;


/**
 * Created by Jerry on 15/3/11.
 * 一般就一个标题栏的列表界面
 */
public abstract class BaseListFragment<T> extends BaseRefreshFragment {
    @Bind(R.id.lv_content)
    protected ListView mListView;
    @Bind(R.id.empty)
    protected CommonEmptyView mEmpty;

    protected ListAdapter mAdapter;
    private boolean isListViewFooterReloadView = false; // 是否ListViewFooterView中的ReloadView
    private ReloadView mFooterReloadView;
    private LoadMoreHelper mLoadMoreHelper;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.common_base_listview);
        ButterKnife.bind(this,view);
        initViews();
    }

    protected void initViews() {
        mListView.setHeaderDividersEnabled(false);

        View header = addHeaderView();
        View footer = addFooterView();
        if (header != null) {
            mListView.addHeaderView(header, null, false);
        }
        if (footer != null) {
            mListView.addFooterView(footer, null, false);
        }
        mLoadMoreHelper = new LoadMoreHelper(mListView) {
            @Override
            public void onLoadMoreBegin() {
                BaseListFragment.this.onLoadMoreBegin();
            }
        };
        mAdapter = new ListAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setEmptyView(mEmpty);
        setRefreshView(R.id.lv_content);

    }


    /**
     * 使用ListView Footer 中的ReloadView
     */
    protected void useListViewFooterReloadView() {
        isListViewFooterReloadView = true;

        mFooterReloadView = new ReloadView(getActivity());
        mFooterReloadView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2px(getActivity(), 180f)));
        mListView.addFooterView(mFooterReloadView);
        setReloadView(mFooterReloadView);
    }

    /**
     * 使用ListView Footer 中的ReloadView,reloadView 设置展示高度
     */
    protected void useListViewFooterReloadView(float height) {
        isListViewFooterReloadView = true;

        mFooterReloadView = new ReloadView(getActivity());
        mFooterReloadView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2px(getActivity(), height)));
        mListView.addFooterView(mFooterReloadView);
        setReloadView(mFooterReloadView);
    }

    @Override
    public void showLoadingView() {
        this.showLoadingView(null);
    }

    @Override
    public void showLoadingView(String msg) {
        if (isListViewFooterReloadView && null == getReloadView()) {
            mListView.addFooterView(mFooterReloadView);
            setReloadView(mFooterReloadView);
        } else {
            super.showLoadingView(msg);
        }
    }

    @Override
    public void dismissLoadingView() {
        ReloadView mReloadView = getReloadView();
        if (null != mReloadView) {
            mListView.removeFooterView(mReloadView);
            setReloadView(null);
        }
    }

    /**
     * 设置ListView Empty上间距
     *
     * @param views 作为间距参考的View(可以多个)，方法内会自动算出该View的高度
     */
    protected void setListEmptyViewTopMargin(final View... views) {
        if (null != mEmpty && null != views && views.length > 0) {
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
                    ViewGroup.MarginLayoutParams params = ((ViewGroup.MarginLayoutParams) mEmpty.getLayoutParams());
                    params.setMargins(params.leftMargin, height, params.rightMargin, params.bottomMargin);
                }
            });
        }
    }

    public class ListAdapter extends AbstractBaseAdapter<T> {

        public ListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // 判断显示空View (主要用于部分有head的页面，那种页面无法直接用setEmpty，否则head也会消失)
            if (getCount() == 1 && getItem(position) instanceof EmptyDataTag && ((EmptyDataTag) getItem(position)).isShowEmptyView()) {
                if (null == convertView || !(convertView instanceof CommonEmptyView)) {
                    CommonEmptyView mCommonEmptyView = new CommonEmptyView(getActivity());
                    mCommonEmptyView.setEmptyViewHeight(mListView.getHeight());
                    convertView = mCommonEmptyView;
                }

                return convertView;
            }

            // 如果已经有数据了，就将空View重置
            if (null != convertView && convertView instanceof CommonEmptyView) {
                convertView = null;
            }

            return BaseListFragment.this.getView(position, convertView, parent);
        }
    }


    /**
     * 下拉刷新
     *
     * @param customSwipeToRefresh
     */
    protected abstract void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh);

    /**
     * 加载更多
     */
    protected void onLoadMoreBegin() {
    }

    ;

    /**
     * 添加随listview一起滚动的header
     */
    protected View addHeaderView() {
        return null;
    }
    /**
     * 添加随listview一起滚动的footer
     */
    protected View addFooterView() {
        return null;
    }

    ;

    protected abstract View getView(int position, View convertView, ViewGroup parent);

    /**
     * 设置是否可以加载更多
     *
     * @param isEnabled
     */
    protected void setLoadMoreEnabled(boolean isEnabled) {
        mLoadMoreHelper.setFooterEnabled(isEnabled);
    }

    /**
     * 加载更多完成
     */
    protected void loadMoreComplete() {
        mLoadMoreHelper.setFooterState(FooterScrollListener.FooterState.NORMAL);
    }

    /**
     * 显示ListView分割线
     */
    protected void showListViewDivider() {
        mListView.setDividerHeight(AppUtil.dip2px(getActivity(), 0.5f));
    }

    /**
     * 隐藏ListView分割线
     */
    protected void hideListViewDivider() {
        mListView.setDividerHeight(0);
    }

    /**
     * 设置ListView分割线高度 （分割线为透明，只为中间隔开）
     *
     * @param dbHeight
     */
    protected void setListViewDividerHeight(float dbHeight) {
        setListViewDividerHeight(dbHeight, true, true);
    }

    /**
     * 设置ListView分割线高度 （分割线为透明，只为中间隔开）
     *
     * @param dbHeight
     * @param isPaddingTop
     * @param isPaddingBottom
     */
    protected void setListViewDividerHeight(float dbHeight, boolean isPaddingTop, boolean isPaddingBottom) {
        mListView.setDivider(new ColorDrawable(Color.TRANSPARENT));
        int dividerH = AppUtil.dip2px(getActivity(), dbHeight);
        mListView.setDividerHeight(dividerH);

        int mPaddingTop = 0;
        int mPaddingBottom = 0;

        if (isPaddingTop) {
            mPaddingTop = dividerH;
        }

        if (isPaddingBottom) {
            mPaddingBottom = dividerH;
        }

        if (mPaddingTop > 0 || mPaddingBottom > 0) {
            setListViewPadding(mPaddingTop, mPaddingBottom);
        }
    }

    /**
     * 设置ListViewPadding
     */
    protected void setListViewPadding(int paddingTop, int paddingBottom) {
        mListView.setPadding(0, paddingTop, 0, paddingBottom);
        mListView.setClipToPadding(false);
    }

    /**
     * 隐藏默认ListView的EmptyView
     */
    protected void hideDefListViewEmpty() {
        mListView.setEmptyView(null);
        mEmpty.setVisibility(View.GONE);
    }

}
