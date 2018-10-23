package jyx.com.aw.act;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.base.BaseListActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.global.Constants;
import jyx.com.aw.mvp.model.DailyList;
import jyx.com.aw.mvp.model.Experience;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;
import rx.Observable;

/**
 * Created by JiangYunxian on 2017/11/15 0015.
 * 功能：
 */

public class ActExperience extends BaseListActivity<Experience.ArticleBean>implements AdapterView.OnItemClickListener {
    private int page = 1;
    private View noDataFooterView;
    private int id;
    @Override
    protected void initViews() {
        super.initViews();
        mListView.setOnItemClickListener(this);
        String title = getIntent().getExtras().getString("title");
        mBar.setTitle(title);
        id = getIntent().getIntExtra("typeId", 0);
//        Log.i("@@@",id+" "+id);
        if (id==0){
            initList(0,mHttpApi1.getExperienceData(Config.BASE_URL[1], page++));
        }else {
            initList(0,mHttpApi1.getTypeData(id,Config.BASE_URL[1], page++));
        }
    }
    private void addNODataFooterView() {
        noDataFooterView = LayoutInflater.from(this).inflate(R.layout.footer_load_more, null);
        TextView tv_footerLoadMoreState1 = (TextView) noDataFooterView.findViewById(R.id.tv_footerLoadMoreState1);
        tv_footerLoadMoreState1.setText("没有数据了");
        mListView.addFooterView(noDataFooterView);
    }
    private void initList(final int opt, Observable observable) {
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<Experience>() {
            @Override
            public void onSuccess(Experience model) {
//                Log.i("@@@", model.toString());
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
                        mAdapter.morePageIndexDoubleSub();//让pageindex之前相加的还原到未相加
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

    private void getSuccess(List<Experience.ArticleBean> article, int opt) {
        if (mAdapter.getPageIndex() == 1) {
            setLoadMoreEnabled(true);
        }
        if (article != null && article.size() >0) {
            mAdapter.putData(article);
            if (article.size() < Constants.LEAST_EXPERIENCE_LOAD_NUM) {
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
        mAdapter.initPageIndex();
        if (noDataFooterView != null) {
            mListView.removeFooterView(noDataFooterView);
        }
        mAdapter.initPageIndex();
        if (id==0){
            initList(1,mHttpApi1.getExperienceData(Config.BASE_URL[1],1));
        }else {
            initList(1,mHttpApi1.getTypeData(id,Config.BASE_URL[1],1));
        }
        page = 2;
    }

    @Override
    protected void onLoadMoreBegin() {
        mAdapter.morePageIndexDoublePlus();
        if (id==0){
            initList(2,mHttpApi1.getExperienceData(Config.BASE_URL[1], page++));
        }else {
            initList(2,mHttpApi1.getTypeData(id,Config.BASE_URL[1], page++));
        }
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this).inflate(R.layout.experience_list, parent, false);
        }
        TextView mTextViewExperienceTitle = ViewHolder.get(convertView, R.id.mTextViewExperienceTitle);
        TextView mTextViewExperienceViews = ViewHolder.get(convertView, R.id.mTextViewExperienceViews);
        TextView mTextViewExperienceAbstract = ViewHolder.get(convertView, R.id.mTextViewExperienceAbstract);
        Experience.ArticleBean item = mAdapter.getItem(position);
        if (item != null) {
            mTextViewExperienceTitle.setText(item.getTitle());
            mTextViewExperienceAbstract.setText(item.getAbstractX());
            mTextViewExperienceViews.setText(item.getViews() + "");
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (AppUtil.isFastClick()) {
            return;
        }
        Experience.ArticleBean item = mAdapter.getItem(position);
        Bundle bundle = new Bundle();
        int ID = item.getId();
        bundle.putInt("ID", ID);
        turnToActivity(ActExperienceWebView1.class, bundle, false);
    }
}
