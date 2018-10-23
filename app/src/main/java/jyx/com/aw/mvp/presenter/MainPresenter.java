package jyx.com.aw.mvp.presenter;


import jyx.com.aw.base.BasePresenter;
import jyx.com.aw.mvp.model.login.LoginReq;
import jyx.com.aw.mvp.model.login.LoginRes;
import jyx.com.aw.mvp.view.MainView;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;

/**
 * 作者：yongzhou on 16/6/15
 * 邮箱：zhouyong@cdzmd.com
 * 功能：主Presenter,负责完成View于Model间的交互
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }


    public void submitLogin() {
        mvpView.showLoading();

        LoginReq req = new LoginReq();
        req.setUserName("test1");
        req.setPassword("25F9E794323B453885F5181F1B624D0B");
        req.setTimespan(AppUtil.getHttpReqTime());
        req.setSign(AppUtil.getHttpReqSign(req));

//        addSubscription(mHttpApi.submitLogin(req),
//                new SubscriberCallBack<>(new ApiCallback<LoginRes>() {
//
//                    @Override
//                    public void onSuccess(LoginRes model) {
//                        if (model.getData() != null) {
//                            mvpView.getDataSuccess(model.getData());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        mvpView.showLoadErr();
//                        mvpView.getDataFail(msg);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        mvpView.hideLoading();
//                    }
//                }));
    }
}
