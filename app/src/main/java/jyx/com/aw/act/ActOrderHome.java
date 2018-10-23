package jyx.com.aw.act;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import jyx.com.aw.R;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.base.BaseFragment;
import jyx.com.aw.fragment.FmOrderTab1Home;
import jyx.com.aw.fragment.FmOrderTab2Home;
import jyx.com.aw.fragment.FmOrderTab3Home;
import jyx.com.aw.fragment.FmOrderTab4Home;
import jyx.com.aw.fragment.FmOrderTab5Home;
import jyx.com.aw.util.AppUtil;

/**
 * 作者：-zy- on 16/8/17
 * 邮箱：zhouyong@cdzmd.com
 * 功能：主页
 */

public class ActOrderHome extends BaseActivity {

    private static final int TAB_1_TAG = 101;
    private static final int TAB_2_TAG = 102;
    private static final int TAB_3_TAG = 103;
    private static final int TAB_4_TAG = 104;
    private static final int TAB_5_TAG = 105;
    @Bind(R.id.tv_tab1Home)
    TextView mTvTab1Home;
    @Bind(R.id.tv_tab2Home)
    TextView mTvTab2Home;
    @Bind(R.id.tv_tab3Home)
    TextView mTvTab3Home;
    @Bind(R.id.tv_tab4Home)
    TextView mTvTab4Home;
    @Bind(R.id.tv_tab5Home)
    TextView mTvTab5Home;

    private FragmentManager mFragmentManager;
    private SparseArray<BaseFragment> mFragments;
    private int oldTab = -1;
    private long mExitTime = 0;
    private FmOrderTab5Home curFmHomeTab5Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fm_order_home);
        dismissLoadingView();
        mBar.setVisibility(View.GONE);
        initTab();

    }


    private void initTab() {
        curFmHomeTab5Home = new FmOrderTab5Home();
        // 初始化TabHost
        mFragmentManager = getSupportFragmentManager();
        mFragments = new SparseArray<>();
        mFragments.put(TAB_1_TAG, new FmOrderTab1Home());
        mFragments.put(TAB_2_TAG, new FmOrderTab2Home());
        mFragments.put(TAB_3_TAG, new FmOrderTab3Home());
        mFragments.put(TAB_4_TAG, new FmOrderTab4Home());
        mFragments.put(TAB_5_TAG, curFmHomeTab5Home);
        // 设置默认选中页
        changeTabBtn(TAB_1_TAG);
    }


    /**
     * 切换Tab页
     *
     * @param clickTabTag 当前选中Tab按钮下标
     */
    public void changeTabBtn(int clickTabTag) {
        if (clickTabTag==TAB_1_TAG){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //取消透明状态栏
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
        if (clickTabTag == oldTab) {
            return;
        }

        // 还原原Tab选中状态
        setTabBtnEnabled(oldTab, true);
        // 设置当前选中按钮状态
        setTabBtnEnabled(clickTabTag, false);

        changeFragment(clickTabTag);
    }


    private void setTabBtnEnabled(int tab, boolean isEnabled) {
        switch (tab) {
            case TAB_1_TAG:
                mTvTab1Home.setEnabled(isEnabled);
                break;
            case TAB_2_TAG:
                mTvTab2Home.setEnabled(isEnabled);
                break;
            case TAB_3_TAG:
                mTvTab3Home.setEnabled(isEnabled);
                break;
            case TAB_4_TAG:
                mTvTab4Home.setEnabled(isEnabled);
                break;
            case TAB_5_TAG:
                mTvTab5Home.setEnabled(isEnabled);
                break;
        }
    }


    /**
     * 切换Fragment
     *
     * @param curClickTab 要切换到的Fragment Tab
     */
    public void changeFragment(int curClickTab) {
        if (oldTab == curClickTab) {
            return;
        }

        // 获取原Fragment
        Fragment oldFragment = null;
        if (oldTab > 0) {
            oldFragment = mFragments.get(oldTab);
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        if (null != oldFragment) {
            // 暂停原Fragment
            oldFragment.onPause();
        }

        // 获取当前切换到的Fragment
        Fragment curFragment = mFragments.get(curClickTab);
        if (curFragment.isAdded()) {
            curFragment.onResume();
        } else {
            ft.add(R.id.fl_content, curFragment);
        }

        if (null != oldFragment) {
            // 隐藏原Fragment
            ft.hide(oldFragment);
        }
        // 显示现Fragment
        ft.show(curFragment);

        ft.commit();

        oldTab = curClickTab;
    }


    @Override
    public void onDestroy() {

        System.exit(0);

        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        boolean isChildHandler = false;
        for (int i = 0; i < mFragments.size(); i++) {
            BaseFragment tempF = mFragments.get(mFragments.keyAt(i));
            if (tempF.onBackPressed()) {
                isChildHandler = true;
            }
        }

        if (!isChildHandler) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                AppUtil.showToast("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    @OnClick({R.id.tv_tab1Home, R.id.tv_tab2Home, R.id.tv_tab3Home, R.id.tv_tab4Home, R.id.tv_tab5Home})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tab1Home:
                changeTabBtn(TAB_1_TAG);
                break;
            case R.id.tv_tab2Home:
                changeTabBtn(TAB_2_TAG);
                break;
            case R.id.tv_tab3Home:
                changeTabBtn(TAB_3_TAG);
                break;
            case R.id.tv_tab4Home:
                changeTabBtn(TAB_4_TAG);
                break;
            case R.id.tv_tab5Home:
                changeTabBtn(TAB_5_TAG);
                break;
        }
    }

    /**
     * 是否不允许滑动关闭Activity (如果需要不让滑动关闭，请重写此类并返回true)
     * @return true:不允许滑动关闭，false: 允许滑动关闭 (默认)
     */
    @Override
    protected boolean isNotSwipe() {
        return true;
    }
}
