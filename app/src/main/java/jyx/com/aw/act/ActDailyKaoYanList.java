package jyx.com.aw.act;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseListActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.global.Constants;
import jyx.com.aw.mvp.model.DailyList;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;

import static jyx.com.aw.util.DateFormatUtil.transForDate;

/**
 * Created by JiangYunxian on 2017/11/10 0010.
 * 功能：
 */
public class ActDailyKaoYanList extends BaseListActivity<DailyList.ArticleBean> implements AdapterView.OnItemClickListener {
    private int page = 1;
    private View noDataFooterView;
    private void addNODataFooterView() {
        noDataFooterView = LayoutInflater.from(this).inflate(R.layout.footer_load_more, null);
        TextView tv_footerLoadMoreState1 = (TextView) noDataFooterView.findViewById(R.id.tv_footerLoadMoreState1);
        tv_footerLoadMoreState1.setText("没有数据了");
        mListView.addFooterView(noDataFooterView);
    }
    @Override
    protected void initViews() {
        super.initViews();
        mListView.setOnItemClickListener(this);
        mBar.setTitle("考研日报");
        initList(0, page++);
    }

    private void initList(final int opt, int page) {
        addSubscription(mHttpApi1.getDailyListData(Config.M_CODE,page),new SubscriberCallBack<DailyList>(new ApiCallback<DailyList>() {
            @Override
            public void onSuccess(DailyList model) {
                getSuccess(model.getArticle(), opt);
                dismissLoadingView();
                refreshComplete();
            }
            @Override
            public void onFailure(int code, String msg) {
                AppUtil.showToast(msg);
                dismissLoadingView();
                refreshComplete();
                switch (opt) {
                    case 0:
                        // 初始化加载失败
                        showLoadErrorView();
                        setLoadMoreEnabled(false);
                        break;
                    case 1:
                        // 下拉刷新失败
                        AppUtil.showToast(msg);
                        refreshComplete();
                        break;
                    case 2:
                        // 加载更多失败
                        mAdapter.morePageIndexDoubleSub(); // 让pageindex之前相加的还原到未相加
                        AppUtil.showToast(msg);
                        loadMoreComplete();
                        break;
                }
            }

            @Override
            public void onCompleted() {

            }
        }));

    }

    private void getSuccess(List<DailyList.ArticleBean> article, int opt) {
        if (mAdapter.getPageIndex() == 1) {
            setLoadMoreEnabled(true);
        }
        if (article != null && article.size() > 0) {
            mAdapter.putData(article);

            if (article.size() < Constants.LEAST_LOAD_NUM) {
                setLoadMoreEnabled(false);
                addNODataFooterView();
            } else {
                setLoadMoreEnabled(true);
            }
        } else {
            if (opt == 1 && mAdapter.getCount() > 0) {
                mAdapter.clear();
            }
            setLoadMoreEnabled(false);
        }

        switch (opt) {
            case 0:
                // 初始化加载成功
                dismissLoadingView();
                break;
            case 1:
                // 下拉刷新成功
                refreshComplete();
                break;
            case 2:
                // 加载更多完成
                loadMoreComplete();
                break;
        }
    }

    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {
        if (noDataFooterView != null) {
            mListView.removeFooterView(noDataFooterView);
        }
        mAdapter.initPageIndex();
        initList(1, 1);
        page = 2;
    }
    @Override
    protected void onLoadMoreBegin() {
        mAdapter.morePageIndexDoublePlus();
        initList(2, page++);

    }
    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this).inflate(R.layout.daily_list, parent, false);
        }
        ImageView mImageViewDailyListIcon = ViewHolder.get(convertView, R.id.mImageViewDailyListIcon);
        TextView mTextViewDailyListTitle = ViewHolder.get(convertView, R.id.mTextViewDailyListTitle);
        TextView mTextViewDailyListUpdateTime = ViewHolder.get(convertView, R.id.mTextViewDailyListUpdateTime);
        TextView mTextViewDailyListViews = ViewHolder.get(convertView, R.id.mTextViewDailyListViews);
        LinearLayout mLinearLayout = ViewHolder.get(convertView, R.id.mLinearLayout);
        LinearLayout mLinearLayoutFirst = ViewHolder.get(convertView, R.id.mLinearLayoutFirst);
            mLinearLayoutFirst.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            DailyList.ArticleBean item = mAdapter.getItem(position);
            if (item != null) {
                Glide.with(this)
                        .load(item.getPic())
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImageViewDailyListIcon);
                mTextViewDailyListTitle.setText(item.getTitle());
                mTextViewDailyListUpdateTime.setText(transForDate(item.getPubdate()));
                mTextViewDailyListViews.setText(item.getViews() + "个浏览");
            }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (AppUtil.isFastClick()) {
            return;
        }
        DailyList.ArticleBean item = mAdapter.getItem(position);
        Bundle bundle = new Bundle();
        int ID = item.getId();
        String pic = item.getPic();
//        Log.i("@@@",ID+" "+pic+" "+position);
        bundle.putInt("ID", ID);
        bundle.putString("pic",pic);
        turnToActivity(ActWebView.class, bundle, false);
    }
}
