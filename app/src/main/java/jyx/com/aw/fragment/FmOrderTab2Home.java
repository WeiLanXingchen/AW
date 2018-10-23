package jyx.com.aw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.MyViewPagerAdapter;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseFragment;
import jyx.com.aw.base.BaseListFragment;
import jyx.com.aw.base.BaseRefreshFragment;
import jyx.com.aw.global.Config;
import jyx.com.aw.mvp.model.FindFriendsBanner;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.test.CourseBean;
import jyx.com.aw.test.TestAdapter;
import jyx.com.aw.test.TestBean;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.FixedListView;


/**
 * 作者：
 * 邮箱：
 * 功能：
 */

public class FmOrderTab2Home  extends BaseRefreshFragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fm_order_tab2);
        ViewPager mViewPager = (ViewPager)view. findViewById(R.id.view_pager);
        ImageView mImageViewFindFriendsBanner = (ImageView) view. findViewById(R.id.mImageViewFindFriendsBanner);
        TextView mTextViewFindFriendsStatementFirst = (TextView) view. findViewById(R.id.mTextViewFindFriendsStatementFirst);
        TextView mTextViewFindFriendsStatementSecond = (TextView) view. findViewById(R.id.mTextViewFindFriendsStatementSecond);
        TextView mTextViewFindFriendsStatementThird = (TextView) view. findViewById(R.id.mTextViewFindFriendsStatementThird);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(ListFriendsFragment.newInstance(), "最受欢迎");//添加Fragment
        viewPagerAdapter.addFragment(ListFriendsFragment1.newInstance(), "最佳匹配");//添加Fragment
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("最受欢迎"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("最佳匹配"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题
        setBannerData(mImageViewFindFriendsBanner,mTextViewFindFriendsStatementFirst,mTextViewFindFriendsStatementSecond,mTextViewFindFriendsStatementThird);//设置滚动条数据
    }

    private void setBannerData(final ImageView mImageViewFindFriendsBanner, final TextView mTextViewFindFriendsStatementFirst, final TextView mTextViewFindFriendsStatementSecond,
                               final TextView mTextViewFindFriendsStatementThird      ) {
        addSubscription(mHttpApi.getFindFriendsBannerData(Config.M_CODE),new SubscriberCallBack<>(new ApiCallback<FindFriendsBanner>() {
            @Override
            public void onSuccess(FindFriendsBanner model) {
//                Log.i("@@@",model.getStatus().getBanner());
                if (model!=null)
                Glide.with(getContext())
                        .load(model.getStatus().getBanner())
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImageViewFindFriendsBanner);
                mTextViewFindFriendsStatementFirst.setText(model.getStatus().getStatement_first());
                mTextViewFindFriendsStatementSecond.setText(model.getStatus().getStatement_second());
                mTextViewFindFriendsStatementThird.setText(model.getStatus().getStatement_third());
                dismissLoadingView();
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

    }




}

