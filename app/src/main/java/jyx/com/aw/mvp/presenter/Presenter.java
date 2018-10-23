package jyx.com.aw.mvp.presenter;

/**
 * 作者：yongzhou on 16/6/15
 * 邮箱：zhouyong@cdzmd.com
 * 功能：负责完成View于Model间的交互
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
