package jyx.com.aw.mvp.model;

/**
 * 作者：-zy- on 16/6/30
 * 邮箱：zhouyong@cdzmd.com
 * 功能：响应基类
 */
public class CommRes {
    private int code; //0 成功,其他失败
    private String message; //结果说明
    private int total; //符合条件的记录数量

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
