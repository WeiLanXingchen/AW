package jyx.com.aw.mvp.model.login;


import jyx.com.aw.mvp.model.CommReq;

/**
 * 作者：-zy- on 16/8/19
 * 邮箱：zhouyong@cdzmd.com
 * 功能：
 */

public class LoginReq extends CommReq {
   private String UserName;
   private  String  Password;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
