package jyx.com.aw.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import jyx.com.aw.R;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.OnBackPressedListener;
import jyx.com.aw.impl.OnUserLoginChangeListener;
import jyx.com.aw.retrofit.AppClient;
import jyx.com.aw.retrofit.AppClient1;
import jyx.com.aw.retrofit.AppClient2;
import jyx.com.aw.retrofit.AppClient3;
import jyx.com.aw.retrofit.HttpApi;
import jyx.com.aw.view.ReloadView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jerry on 15/3/3.
 * ReloadView的ID名请统一叫load_view
 */
public class BaseFragment extends Fragment implements OnBackPressedListener, BaseImpl,OnUserLoginChangeListener {
    // 加载中、重试 View
    private ReloadView mReloadView;
    private BaseHelper mBaseHelper;

    private FrameLayout mFmRootContainer;

    public HttpApi mHttpApi = AppClient.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口
    public HttpApi mHttpApi1 = AppClient1.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口
    public HttpApi mHttpApi2 = AppClient2.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口
    public HttpApi mHttpApi3 = AppClient3.retrofit().create(HttpApi.class);//retrofit绑定ApiStores接口

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseHelper = new BaseHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFmRootContainer != null) {
            mFmRootContainer.removeAllViews();
            mFmRootContainer = null;
        }
        mFmRootContainer = new FrameLayout(getActivity());
        mFmRootContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return mFmRootContainer;
    }


    @Override
    public void onDestroy() {
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



    protected void setContentView(int layoutId) {
        this.setContentView(View.inflate(getActivity(), layoutId, null));
    }

    protected void setContentView(View view) {
        mFmRootContainer.removeAllViews();
        mFmRootContainer.addView(view);

        ButterKnife.bind(this, mFmRootContainer);

        View tempReloadView = view.findViewById(R.id.load_view);
        if (null != tempReloadView) {
            mReloadView = (ReloadView) tempReloadView;
        }

        mBaseHelper.init(getActivity(), mReloadView);
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

    protected boolean isShowLoading() {

        return mBaseHelper.isShowLoading();
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
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinish) {
            getActivity().finish();
        }
    }

    @Override
    public void turnToActivityForResult(Class<?> clazz, int requestCode) {
        turnToActivityForResult(clazz, requestCode, null);
    }

    @Override
    public void turnToActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void onLogin() {

    }

    @Override
    public void onLogout() {

    }
}
