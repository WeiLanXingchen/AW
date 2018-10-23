package jyx.com.aw.act;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import jyx.com.aw.R;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.fragment.ListExamFragment;

/**
 * Created by JiangYunxian on 2017/12/13 0013.
 * 功能：
 */

public class ActQuestionBank extends BaseActivity{
    ViewPager mViewPager;

    ListExamFragment mFragment1;

    ListExamFragment mFragment2;
    private TabLayout mTabLayout;
    PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_question_bank);
        initView(savedInstanceState);
        mBar.setTitle("题库");
        mBar.setRightText("错题集");
    }

    private void initView(Bundle savedInstanceState) {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout = (TabLayout) findViewById(R.id.toolbar_tab);

        if (savedInstanceState == null) {
            mFragment1 = new ListExamFragment();
            mFragment1.initData("英语");
            mFragment2 = new ListExamFragment();
            mFragment2.initData("政治");
        }

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        dismissLoadingView();
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
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
