package jyx.com.aw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.base.BaseFragment;
import jyx.com.aw.base.BaseRefreshFragment;
import jyx.com.aw.view.CustomSwipeToRefresh;


/**
 * 作者：
 * 邮箱：
 * 功能：问答
 */

public class FmOrderTab4Home extends BaseRefreshFragment {

    ViewPager mViewPager;

    ListAllQAndAFragment mFragment1;

    ListExamFragment mFragment2;
    private List<Fragment> fragments;
    private TabLayout mTabLayout;
    PagerAdapter mPagerAdapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fm_order_tab4);
        initView(savedInstanceState,view);

    }
    private void initView(Bundle savedInstanceState,View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout = (TabLayout) view.findViewById(R.id.toolbar_tab);

        if (savedInstanceState == null) {
            mFragment1 = new ListAllQAndAFragment();
//            mFragment1.initData("英语");
            mFragment2 = new ListExamFragment();
            mFragment2.initData("政治");
        }
        fragments = new ArrayList<>();
        fragments.add(mFragment1);
        fragments.add(mFragment2);
        mPagerAdapter = new PagerAdapter(getChildFragmentManager(),fragments);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        dismissLoadingView();
    }

    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {

    }

    public class PagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;
        public PagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return mFragment1;
            } else if (position == 1) {
                return mFragment2;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}
