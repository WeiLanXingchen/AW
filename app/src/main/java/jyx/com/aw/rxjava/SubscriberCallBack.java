package jyx.com.aw.rxjava;


import android.content.Intent;

import jyx.com.aw.app.AppApplication;
import jyx.com.aw.global.Constants;
import jyx.com.aw.mvp.model.CommRes;
import jyx.com.aw.util.MLog;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 作者：
 * 邮箱：
 * 功能：观察者获得数据通过ApiCallback回调
 */
public class SubscriberCallBack<T> extends Subscriber<T> {
    private ApiCallback<T> apiCallback;

    public SubscriberCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = "网络繁忙,请重试";
            if (code == 112) {
                msg = "授权信息验证失效,请重新登录";
                AppApplication.getAppContext().sendBroadcast(new Intent(Constants.BR_ACTION_USER_AUTH_ERR));
            } else if (code == 504) {
                msg = "网络不给力";
            }
            apiCallback.onFailure(code, msg);
        } else {
            apiCallback.onFailure(0, "网络繁忙,请重试");
        }
        apiCallback.onCompleted();
    }

    @Override
    public void onNext(T t) {
        if (t instanceof CommRes) {
            CommRes res = (CommRes) t;
            if (res.getCode() == 0) {
                apiCallback.onSuccess(t);
            } else if (res.getCode() == 112) {
                AppApplication.getAppContext().sendBroadcast(new Intent(Constants.BR_ACTION_USER_AUTH_ERR));
                apiCallback.onFailure(res.getCode(), res.getMessage() + ",请重新登录");
            } else {
                apiCallback.onFailure(res.getCode(), res.getMessage());
            }
        } else {
            MLog.e("未判断code", "不是commRes子类");
            apiCallback.onSuccess(t);
        }
    }
}
