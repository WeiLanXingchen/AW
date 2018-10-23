package jyx.com.aw.mvp.model.login;


import jyx.com.aw.mvp.model.CommRes;

/**
 * 作者：-zy- on 16/8/19
 * 邮箱：zhouyong@cdzmd.com
 * 功能：
 */

public class LoginRes extends CommRes {
    private UserBean data;

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }
}
