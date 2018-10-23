package jyx.com.aw.fragment;

import android.os.Bundle;
import android.util.Log;
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
import jyx.com.aw.act.ActAllQAndADetail;
import jyx.com.aw.act.ActWebView;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseListFragment;
import jyx.com.aw.global.Config;
import jyx.com.aw.global.Constants;
import jyx.com.aw.mvp.model.AllQAndA;
import jyx.com.aw.mvp.model.DailyList;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;


/**
 * Created by JiangYunxian on 2018/1/3 0003.
 * 功能：
 */
public class ListAllQAndAFragment extends BaseListFragment<AllQAndA.QuestionsBean> implements AdapterView.OnItemClickListener {
    private int page = 1;
    private View noDataFooterView;

    private void addNODataFooterView() {
        noDataFooterView = LayoutInflater.from(getContext()).inflate(R.layout.footer_load_more, null);
        TextView tv_footerLoadMoreState1 = (TextView) noDataFooterView.findViewById(R.id.tv_footerLoadMoreState1);
        tv_footerLoadMoreState1.setText("没有数据了");
        mListView.addFooterView(noDataFooterView);
    }
    @Override
    protected void initViews() {
        super.initViews();
        mListView.setOnItemClickListener(this);
        initList(0, page++);

    }



    private void initList(final int opt, int page) {
        addSubscription(mHttpApi.getAllQAndAData(page, Config.M_CODE), new SubscriberCallBack<>(new ApiCallback<AllQAndA>() {
            @Override
            public void onSuccess(AllQAndA model) {
                getSuccess(model.getQuestions(), opt);
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

    private void getSuccess(List<AllQAndA.QuestionsBean> questions, int opt) {
        if (mAdapter.getPageIndex() == 1) {
            setLoadMoreEnabled(true);
        }
        if (questions != null && questions.size() > 0) {
            mAdapter.putData(questions);

            if (questions.size() < Constants.LEAST_LOAD_NUM) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.all_q_and_a_list, parent, false);
        }
        TextView mTextViewAllQAndATitle = ViewHolder.get(convertView, R.id.mTextViewAllQAndATitle);
        TextView mTextViewAllQAndAAnswerNum = ViewHolder.get(convertView, R.id.mTextViewAllQAndAAnswerNum);
        TextView mTextViewAllQAndAViewsCount = ViewHolder.get(convertView, R.id.mTextViewAllQAndAViewsCount);
        AllQAndA.QuestionsBean item = mAdapter.getItem(position);
        if (item != null) {
            mTextViewAllQAndATitle.setText(item.getTitle());
            mTextViewAllQAndAAnswerNum.setText(item.getAnswer_num() + " 回答");
            mTextViewAllQAndAViewsCount.setText(item.getViews_count() + " 浏览");
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (AppUtil.isFastClick()) {
            return;
        }
        AllQAndA.QuestionsBean item = mAdapter.getItem(position);
        Bundle bundle = new Bundle();
        String ID = item.getId();
        String title = item.getTitle();

//        Log.i("@@@",id+" "+position);
        bundle.putString("ID", ID);
        bundle.putString("title", title);
        if (item.getSchools() == null||item.getSchools().size()==0) {

        }else {
            String schoolName = item.getSchools().get(0).getName();
            bundle.putString("schoolName", schoolName);
        }
        if (item.getMajors() == null||item.getMajors().size()==0) {

        }else {
            String majorName = item.getMajors().get(0).getName();
            bundle.putString("majorName", majorName);
        }
        turnToActivity(ActAllQAndADetail.class, bundle, false);
    }
}
