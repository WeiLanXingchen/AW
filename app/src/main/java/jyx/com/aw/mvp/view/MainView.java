package jyx.com.aw.mvp.view;


import jyx.com.aw.mvp.model.login.UserBean;

/**
 * 作者：yongzhou on 16/6/15
 * 邮箱：zhouyong@cdzmd.com
 * 功能：处理业务的方法
 */
public interface MainView {

    void getDataSuccess(UserBean model);

    void getDataFail(String msg);

    void showLoading();

    void showLoadErr();

    void hideLoading();
}
