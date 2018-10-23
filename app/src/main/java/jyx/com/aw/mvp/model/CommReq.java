package jyx.com.aw.mvp.model;


/**
 * 作者：-zy- on 16/6/30
 * 邮箱：zhouyong@cdzmd.com
 * 功能：请求基类
 */
public class CommReq {

    private String timespan; //时间 格式为：时间戳,单位秒。
    private String sign; //签名

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(String timespan) {
        this.timespan = timespan;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
