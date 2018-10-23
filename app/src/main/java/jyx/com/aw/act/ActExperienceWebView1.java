package jyx.com.aw.act;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jyx.com.aw.R;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseListActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.global.Constants;
import jyx.com.aw.mvp.model.Comment;
import jyx.com.aw.mvp.model.DailyList;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.util.MLog;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.CustomWebView;
import jyx.com.aw.view.swipeback.SwipeBackModeEnum;

import static jyx.com.aw.R.id.mImageViewDailyListIcon;
import static jyx.com.aw.R.id.main_right_rv;
import static jyx.com.aw.util.DateFormatUtil.transForDate;
/**
 * Created by JiangYunxian on 2017/12/8 0008.
 * 功能：
 */

public class ActExperienceWebView1 extends BaseListActivity<Comment.CommentsBean> {
    private CustomWebView mWebView;
    private boolean isSelfHandlerLoadingView = false;
    private boolean isRefresh = false;
    private boolean isUseWebPageTitle = false;
    @Override
    protected void initViews() {
        super.initViews();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString(Constants.TAG, "资讯详情");
            int id = getIntent().getIntExtra("ID", 2);
//            Log.i("@@@", id + "");
            initList(id);
        }

    }

    @Override
    protected View addHeaderView() {
        View experienceView = LayoutInflater.from(this).inflate(R.layout.daily_detail, null);
        mWebView= (CustomWebView) experienceView.findViewById(R.id.forum_web_view);
        addBottomView();
        initData(mWebView);
        setClickRelodListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mWebView.getWebView())mWebView.getWebView().reload();
            }
        });
        mBar.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        mBar.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return experienceView;
    }

    private void addBottomView() {
        View comment_view = LayoutInflater.from(this).inflate(R.layout.footer_comment, null);
        Button mButtonFooter= (Button) comment_view.findViewById(R.id.mButtonFooter);
        final EditText mEditTextFooter= (EditText) comment_view.findViewById(R.id.mEditTextFooter);
        mEditTextFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextFooter.setCursorVisible(true);
            }
        });
        addFooterView(comment_view);
    }
    private void initData(final CustomWebView mWebView) {
        setSwipeMode(SwipeBackModeEnum.LEFT);
        mWebView.setReadCache(false);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String title = bundle.getString(Constants.TAG, "资讯详情");
            int id = getIntent().getIntExtra("ID",2);
            String mOriginalUrl="http://api-v2.okaoyan.com:8010/api/v2/general/article/"+String.valueOf(id)+"/show";
//            mOriginalUrl = bundle.getString(Constants.OBJECT);
            mOriginalUrl = mOriginalUrl.trim();
            isRefresh = bundle.getBoolean("isRefresh", false);
//            isUseWebPageTitle = bundle.getBoolean(Properties.IS_USEWEBPAGETITLE, false);
            if (!isUseWebPageTitle) {
                mBar.setTitle(title);
                mBar.setRightImage(R.drawable.share);
            }
            if (!TextUtils.isEmpty(mOriginalUrl)) {
                MLog.e("weburl", mOriginalUrl);
                mWebView.getWebView().loadUrl(mOriginalUrl);
            }
        } else {
            AppUtil.showToast("参数有误");

        }
        setRefreshEnabled(isRefresh);
        mWebView.addCustomWebViewListener(new CustomWebView.SimpleCustomWebViewListener() {
            @Override
            public void onPageFinished(String url, boolean isLoadErr) {
                if (!isSelfHandlerLoadingView) {
                    dismissLoadingView();
                }
                if (!isLoadErr && isUseWebPageTitle) {
                    String title = mWebView.getWebView().getTitle();
                    mBar.setTitle(title);
                    mBar.setRightImage(R.drawable.share);
                }

                if (isRefresh) {
                    refreshComplete();
                }
                if (isLoadErr) {
                    showLoadErrorView();
                }
            }
        });
    }
    private void initList(int id) {
        addSubscription(mHttpApi.getCommentData(id, Config.M_CODE),
                new SubscriberCallBack<>(new ApiCallback<Comment>() {
                    @Override
                    public void onSuccess(Comment model) {
                        List<Comment.CommentsBean> comment = model.getComments();
                        if (comment != null && comment.size() > 0) {
                            Comment.CommentsBean commentsBean=new Comment.CommentsBean();
                            commentsBean.setUsername("klk");
                            comment.add(commentsBean);
                        } else {
                            Comment.CommentsBean commentsBean=new Comment.CommentsBean();
                            commentsBean.setUsername("");
                            comment.add(commentsBean);
                        }
                        mAdapter.putData(comment);
                        setLoadMoreEnabled(false);
                        setRefreshEnabled(false);
                    }
                    @Override
                    public void onFailure(int code, String msg) {
                        AppUtil.showToast(msg);
                        dismissLoadingView();
                    }

                    @Override
                    public void onCompleted() {
                    }

                }));

    }
    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {
        mWebView.getWebView().reload();
    }
    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (position==0){
                Comment.CommentsBean item = mAdapter.getItem(0);
                if (item.getUsername().equals("")){
                    convertView = LayoutInflater.from(this).inflate(R.layout.comment_list, parent, false);
                    ImageView mImageViewCommentUserAvatar = ViewHolder.get(convertView, R.id.mImageViewCommentUserAvatar);
                    TextView mTextViewCommentContent = ViewHolder.get(convertView, R.id.mTextViewCommentContent);
                    TextView mTextViewCommentUserName = ViewHolder.get(convertView, R.id.mTextViewCommentUserName);
                    mImageViewCommentUserAvatar.setVisibility(View.GONE);
                    mTextViewCommentContent.setVisibility(View.GONE);
                    mTextViewCommentUserName.setVisibility(View.GONE);
                }else {
                    convertView = LayoutInflater.from(this).inflate(R.layout.experience_comment, parent, false);
                }
            }else {
                convertView = LayoutInflater.from(this).inflate(R.layout.comment_list, parent, false);
                ImageView mImageViewCommentUserAvatar = ViewHolder.get(convertView, R.id.mImageViewCommentUserAvatar);
                TextView mTextViewCommentContent = ViewHolder.get(convertView, R.id.mTextViewCommentContent);
                TextView mTextViewCommentUserName = ViewHolder.get(convertView, R.id.mTextViewCommentUserName);
                Comment.CommentsBean item = mAdapter.getItem(position-1);
                if (item != null) {
                        Glide.with(this)
                                .load(Config.BASE_HEAD_URL[0]+item.getUseravatar())
                                .skipMemoryCache(true)
                                .placeholder(R.mipmap.ic_launcher)
                                .bitmapTransform(new CropCircleTransformation(this))//转圆形图片
                                .into(mImageViewCommentUserAvatar);
                        mTextViewCommentContent.setText(item.getContent());
                        mTextViewCommentUserName.setText(item.getUsername());
                }
            }
        }
        return convertView;
    }
    @Override
    public void onBackPressed() {
        back();
    }
    private void back() {
        if (mWebView.getWebView() != null && mWebView.getWebView().canGoBack()) {
            MLog.e("地址", mWebView.getWebView().getUrl());
            mWebView.getWebView().goBack();
        } else {
            finish();
        }
    }
}
