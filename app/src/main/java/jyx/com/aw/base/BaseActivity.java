package jyx.com.aw.base;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;



import butterknife.ButterKnife;
import butterknife.OnClick;
import jyx.com.aw.R;
import jyx.com.aw.act.ActWebView;
import jyx.com.aw.global.Config;
import jyx.com.aw.retrofit.AppClient;
import jyx.com.aw.retrofit.AppClient1;
import jyx.com.aw.retrofit.AppClient2;
import jyx.com.aw.retrofit.AppClient3;
import jyx.com.aw.retrofit.HttpApi;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.NavigationBar;
import jyx.com.aw.view.ReloadView;
import jyx.com.aw.view.swipeback.SwipeBackActivityHelper;
import jyx.com.aw.view.swipeback.SwipeBackLayout;
import jyx.com.aw.view.swipeback.SwipeBackModeEnum;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
public class BaseActivity extends AppCompatActivity implements BaseImpl {
    protected NavigationBar mBar;

    private ReloadView mReloadView;
    private FrameLayout mFlBodyContainer;
    private RelativeLayout mRlRootContainer;

    private int mStatusBarHeight;
    private boolean isImmersiveMode;
    private BaseHelper mBaseHelper;
    private SwipeBackActivityHelper mSwipeBackHelper;

    public HttpApi mHttpApi = AppClient.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口
    public HttpApi mHttpApi1 = AppClient1.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口
    public HttpApi mHttpApi2 = AppClient2.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口
    public HttpApi mHttpApi3 = AppClient3.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口
//    private void checkCurPermission() {
//        // 先判断是否有权限。
//        if (AndPermission.hasPermission(this, Manifest.permission.CAMERA,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE)) {
//            // 有权限，直接do anything.
//            choosePic(picCount);
//        } else {
//            // 申请权限。
//            AndPermission.with(this)
//                    .requestCode(100)
//                    .permission(Manifest.permission.CAMERA,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                            Manifest.permission.READ_EXTERNAL_STORAGE)
//                    .send();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
//        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
//    }
//
//    private PermissionListener listener = new PermissionListener() {
//        @Override
//        public void onSucceed(int requestCode, List<String> grantedPermissions) {
//            // 权限申请成功回调。
//            if (requestCode == 100) {
//                choosePic(picCount);
//            }
//        }
//
//        @Override
//        public void onFailed(int requestCode, List<String> deniedPermissions) {
//            // 权限申请失败回调。
//
//            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
//            if (AndPermission.hasAlwaysDeniedPermission(ActWebView.this, deniedPermissions)) {
//                // 第一种：用默认的提示语。
////                AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING).show();
//
//                // 第二种：用自定义的提示语。
//                AndPermission.defaultSettingDialog(ActWebView.this, REQUEST_CODE_SETTING)
//                        .setTitle("权限申请失败")
//                        .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
//                        .setPositiveButton("好，去设置")
//                        .show();
//
//                // 第三种：自定义dialog样式。
//                // SettingService settingService =
//                //    AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//                // 你的dialog点击了确定调用：
//                // settingService.execute();
//                // 你的dialog点击了取消调用：
//                // settingService.cancel();
//            }
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.act_base);

        mBar = (NavigationBar) findViewById(R.id.nb_bar);
        mFlBodyContainer = (FrameLayout) findViewById(R.id.fl_bodyContainer);
        mRlRootContainer = (RelativeLayout) findViewById(R.id.rl_rootContainer);
        mReloadView = (ReloadView) findViewById(R.id.load_view);

        mBaseHelper = new BaseHelper();

        if (!isNotSwipe()) {
            mSwipeBackHelper = new SwipeBackActivityHelper(this);
            mSwipeBackHelper.onActivityCreate();
            mSwipeBackHelper.getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
            mSwipeBackHelper.getSwipeBackLayout().setSimpleSwipeListener(new SwipeBackLayout.SimpleSwipeListener() {
                @Override
                public void onSwipe(float slideOffset) {
                    // 沉浸模式下，滑动退出后，解决状态栏有个背景会延迟消失问题
                    if (isImmersiveMode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int alp = (int) ((1f - slideOffset) * 100);
                        if (alp >= 0 && alp <= 40) {
                            String colorStr;
                            if (alp < 10) {
                                colorStr = "#0" + alp + "000000";
                            } else {
                                colorStr = "#" + alp + "000000";
                            }
                            getWindow().setStatusBarColor(Color.parseColor(colorStr));
                        }
                    }
                }

                @Override
                public void onFinish() {
                    // 沉浸模式下，滑动退出后，解决状态栏有个背景会延迟消失问题
                    if (isImmersiveMode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(Color.TRANSPARENT);
                    }
                }
            });

            overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_right);
        }

        // 初始化处理沉浸设置
        initHandlerImmersiveModeSetting();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (!isNotSwipe()) {
            mSwipeBackHelper.onPostCreate();
        }
    }


    /**
     * 获取通知栏高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        if (mStatusBarHeight <= 0) {
            mStatusBarHeight = AppUtil.getStatusBarHeight(this);
        }

        return mStatusBarHeight;
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        handlerContentView(getLayoutInflater().inflate(layoutResID, null), new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }


    @Override
    public void setContentView(View view) {
        handlerContentView(view, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        handlerContentView(view, params);

    }

    private void handlerContentView(View view, ViewGroup.LayoutParams params) {
        mFlBodyContainer.removeAllViews();
        mFlBodyContainer.addView(view, params);

        ButterKnife.bind(this);
        mBaseHelper.init(this, mReloadView);
    }


    @Override
    protected void onDestroy() {
        onUnsubscribe();
        ButterKnife.unbind(this);
        super.onDestroy();
    }


    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    /**
     * 注册监听
     *
     * @param observable
     * @param subscriber
     */
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }


    @Override
    public void setLoadViewTopMargin(View... views) {
        mBaseHelper.setLoadViewTopMargin(views);
    }

    @Override
    public void setLoadViewTopMargin(int expHeight, View... views) {
        mBaseHelper.setLoadViewTopMargin(expHeight, views);
    }

    @Override
    public void setLoadViewBottomMargin(int expHeight, View... views) {
        mBaseHelper.setLoadViewBottomMargin(expHeight, views);
    }


    @Override
    public void showWaitingDialog() {
        mBaseHelper.showWaitingDialog();
    }

    @Override
    public void showWaitingDialog(String msg) {
        mBaseHelper.showWaitingDialog(msg);
    }

    @Override
    public void dismissWaitingDialog() {
        mBaseHelper.dismissWaitingDialog();
    }

    @Override
    public void showLoadingView() {
        mBaseHelper.showLoadingView();
    }

    @Override
    public void showLoadingView(String msg) {
        mBaseHelper.showLoadingView(msg);
    }

    @Override
    public void showLoadErrorView() {
        mBaseHelper.showLoadErrorView();
    }

    @Override
    public void showLoadErrorView(String msg) {
        mBaseHelper.showLoadErrorView(msg);
    }

    @Override
    public void showLoadErrorView(String msg, boolean isHideIcon) {
        mBaseHelper.showLoadErrorView(msg, isHideIcon);
    }

    @Override
    public void setClickRelodListener(View.OnClickListener listener) {
        mBaseHelper.setClickRelodListener(listener);
    }

    @Override
    public void dismissLoadingView() {
        mBaseHelper.dismissLoadingView();
    }

    @Override
    public void dismissLoadingView(boolean isShowFadeAnim) {
        mBaseHelper.dismissLoadingView(isShowFadeAnim);
    }

    @Override
    public void setReloadView(ReloadView mReloadView) {
        mBaseHelper.setReloadView(mReloadView);
    }

    @Override
    public ReloadView getReloadView() {
        return mBaseHelper.getReloadView();
    }

    @Override
    public void turnToActivity(Class<?> clazz, boolean isFinish) {
        turnToActivity(clazz, null, isFinish);
    }

    @Override
    public void turnToActivity(Class<?> clazz, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }

    @Override
    public void turnToActivityForResult(Class<?> clazz, int requestCode) {
        turnToActivityForResult(clazz, requestCode, null);
    }

    @Override
    public void turnToActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 设置不让滑动退出的View（即触摸事情在设置的View范围内时，不会触发滑动退出的事件）
     *
     * @param cannotSwipeBackViews views
     */
    protected void setCannotSwipeBackViews(View... cannotSwipeBackViews) {
        if (!isNotSwipe()) {
            mSwipeBackHelper.getSwipeBackLayout().setCanotSwipeBackViews(cannotSwipeBackViews);
        }
    }

    /**
     * 是否在显示loading中
     *
     * @return
     */
    protected boolean isShowLoading() {
        if (null != mReloadView) {
            return mReloadView.isLoading();
        }

        return false;
    }


    /**
     * 是否不允许滑动关闭Activity (如果需要不让滑动关闭，请重写此类并返回true)
     *
     * @return true：不允许滑动关闭，false: 允许滑动关闭 (默认)
     */
    protected boolean isNotSwipe() {
        return false;
    }

    /**
     * 设置滑动模式
     *
     * @param swipeModeEnum
     */
    protected void setSwipeMode(SwipeBackModeEnum swipeModeEnum) {
        if (!isNotSwipe() && null != mSwipeBackHelper) {
            switch (swipeModeEnum) {
                case ALL:
                    mSwipeBackHelper.getSwipeBackLayout().setEnableGesture(true);
                    mSwipeBackHelper.getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
                    break;
                case LEFT:
                    mSwipeBackHelper.getSwipeBackLayout().setEnableGesture(true);
                    mSwipeBackHelper.getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
                    break;
                case NONE:
                    mSwipeBackHelper.getSwipeBackLayout().setEnableGesture(false);
                    break;
            }
        }
    }

    protected SwipeBackLayout getSwipeBackLayout() {
        if (!isNotSwipe()) {
            return mSwipeBackHelper.getSwipeBackLayout();
        }

        return null;
    }

    @Override
    public void finish() {
        super.finish();

        if (!isNotSwipe()) {
            overridePendingTransition(R.anim.act_slide_in_right, R.anim.act_slide_out_right);
        }
    }

    @OnClick({R.id.layout_left})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_left:
                // 退出
                finish();
                break;
        }
    }

     /* --------- 沉浸 start --------- */

    /**
     * 初始化处理沉浸设置
     */
    private void initHandlerImmersiveModeSetting() {
        if (getImmersiveModeEnum() != ImmerseModeEnum.NO && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            isImmersiveMode = true;
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                switch (getImmersiveModeEnum()) {
                    case ALL:
                        // 通知栏和导航栏全沉浸
                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                        window.setStatusBarColor(Color.parseColor("#40000000"));
                        window.setNavigationBarColor(Color.TRANSPARENT);
                        break;
                    case OLNY_STATUS_BAR:
                        // 只沉浸通知栏
                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                        window.setStatusBarColor(Color.parseColor("#40000000"));
                        break;
                    case OLNY_NAVIGATION_BAR:
                        // 只沉浸导航栏
                        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                        window.setNavigationBarColor(Color.TRANSPARENT);
                        break;
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 4.4沉浸状态设置
                switch (getImmersiveModeEnum()) {
                    case ALL:
                        // 通知栏和导航栏全沉浸
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                        break;
                    case OLNY_STATUS_BAR:
                        // 只沉浸通知栏
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        break;
                    case OLNY_NAVIGATION_BAR:
                        // 只沉浸导航栏
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                        break;
                }
            }

            // 沉浸模式处理
            if (!isCustomStatusBar()) {
                // 设置通知栏底色
                mRlRootContainer.setBackgroundColor(mBar.getBackgroundColor());
                mRlRootContainer.setFitsSystemWindows(true);
            }
        }
    }

    /**
     * 设置通知栏颜色(仅在沉浸模式下有效)
     *
     * @param color
     */
    protected void setStatusBarColor(int color) {
        if (isImmersiveMode() && !isCustomStatusBar()) {
            mRlRootContainer.setBackgroundColor(color);
        }
    }

    /**
     * 是否沉浸模式
     *
     * @return
     */
    protected boolean isImmersiveMode() {
        return isImmersiveMode;
    }

    /**
     * 是否自定义状态栏 (只对滑动退出的界面有效果)
     * 为true时不设置setFitsSystemWindows(true)，界面会自动向上到通知栏的位置，请自行处理通知栏
     * 使用 getStatusBarHeight获取通知栏高度
     *
     * @return
     */
    protected boolean isCustomStatusBar() {
        return false;
    }

    /**
     * 获取沉浸模式 (默认false不沉浸)
     *
     * @return ImmerseModeEnum
     */
    protected ImmerseModeEnum getImmersiveModeEnum() {
        return ImmerseModeEnum.NO;
    }

    /**
     * 沉浸模式
     */
    public enum ImmerseModeEnum {
        /**
         * 不沉浸
         */
        NO,
        /**
         * 全部沉浸（通知栏和导航栏）
         */
        ALL,
        /**
         * 只沉浸通知栏
         */
        OLNY_STATUS_BAR,
        /**
         * 只沉浸导航栏
         */
        OLNY_NAVIGATION_BAR
    }
    /* --------- 沉浸 end --------- */
}
