package jyx.com.aw.act;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseListActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.global.Constants;
import jyx.com.aw.mvp.model.AllQAndA;
import jyx.com.aw.mvp.model.AllResponseList;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;

/**
 * Created by JiangYunxian on 2018/1/3 0003.
 * 功能：
 */
public class ActAllQAndADetail extends BaseListActivity<AllResponseList.AnswersBean> {
    private View noDataFooterView;
    private int page = 1;
    private String id;
    private void addNODataFooterView() {
        noDataFooterView = LayoutInflater.from(this).inflate(R.layout.footer_load_more, null);
        TextView tv_footerLoadMoreState1 = (TextView) noDataFooterView.findViewById(R.id.tv_footerLoadMoreState1);
        tv_footerLoadMoreState1.setText("没有数据了");
        mListView.addFooterView(noDataFooterView);
    }
    @Override
    protected void initViews() {
        super.initViews();
        mBar.setTitle("问答详情");
        mBar.setRightImage(R.drawable.share);
        String majorName = getIntent().getStringExtra("majorName");
        String schoolName = getIntent().getStringExtra("schoolName");
        id = getIntent().getStringExtra("ID");
        String title = getIntent().getStringExtra("title");

        if (majorName != null) {
            Log.i("@@@", majorName);
        }
        if (schoolName != null) {
            Log.i("@@@", schoolName);
        }
        Log.i("@@@", id + title);
        initList(0, page++,id);
    }

    private void initList(final int opt, int page,String id) {
        addSubscription(mHttpApi.getAllQAndADetailData(id, Config.M_CODE,page), new SubscriberCallBack<>(new ApiCallback<AllResponseList>() {
            @Override
            public void onSuccess(AllResponseList model) {
                getSuccess(model.getAnswers(), opt);
                Log.i("@@@", model.getAnswers().get(0).getContext());
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

    private void getSuccess(List<AllResponseList.AnswersBean> answers, int opt) {
        if (mAdapter.getPageIndex() == 1) {
            setLoadMoreEnabled(true);
        }
        if (answers != null && answers.size() > 0) {
            mAdapter.putData(answers);

            if (answers.size() < Constants.LEAST_LOAD_NUM) {
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
        initList(1, 1,id);
        page = 2;
    }

    @Override
    protected void onLoadMoreBegin() {
        mAdapter.morePageIndexDoublePlus();
        initList(2, page++,id);

    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this).inflate(R.layout.all_response_list, parent, false);
        }
        TextView mTextViewAllResponseUserName = ViewHolder.get(convertView, R.id.mTextViewAllResponseUserName);
        TextView mTextViewAllResponseContext = ViewHolder.get(convertView, R.id.mTextViewAllResponseContext);
        TextView mTextViewAllResponseUpdateTime = ViewHolder.get(convertView, R.id.mTextViewAllResponseUpdateTime);
        TextView mTextViewAllResponseAgree = ViewHolder.get(convertView, R.id.mTextViewAllResponseAgree);
        TextView mTextViewAllResponseDisAgree = ViewHolder.get(convertView, R.id.mTextViewAllResponseDisAgree);
        ImageView mImageViewAllResponseIcon = ViewHolder.get(convertView, R.id.mImageViewAllResponseIcon);
        AllResponseList.AnswersBean item = mAdapter.getItem(position);
        if (item != null) {
//            Glide.with(this)
//                    .load(Config.BASE_HEAD_URL[0]+item.getUser_avatar())
//                    .skipMemoryCache(true)
//                    .placeholder(R.mipmap.ic_launcher)
//                    .into(mImageViewAllResponseIcon);
            mTextViewAllResponseUserName.setText(item.getUser_name());
            mTextViewAllResponseContext.setText(item.getContext());
            mTextViewAllResponseUpdateTime.setText(item.getUpdated_at());
            mTextViewAllResponseAgree.setText(item.getAgree()+"赞同");
            mTextViewAllResponseDisAgree.setText(item.getDisagree()+"反对");
        }
        return convertView;
    }
}
