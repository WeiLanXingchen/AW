package jyx.com.aw.mvp.model;

/**
 * Created by JiangYunxian on 2018/1/18 0018.
 * 功能：
 */
public class FindFriendsBanner {

    /**
     * code : 200
     * status : {"statement_first":"研友相伴，不再孤单","statement_second":"「考研派APP找研友，帮你找到志同道合的TA」","statement_third":"温馨提示：为帮助您准确找到研友，使用找研友功能需要注册登录并完善信息","logined":false,"has_complete_userinfo":false,"banner":"http://api.okaoyan.com:9292/images/friend_banner_v2.jpg","images":"/images/head2.jpg"}
     */

    private int code;
    private StatusBean status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public static class StatusBean {
        /**
         * statement_first : 研友相伴，不再孤单
         * statement_second : 「考研派APP找研友，帮你找到志同道合的TA」
         * statement_third : 温馨提示：为帮助您准确找到研友，使用找研友功能需要注册登录并完善信息
         * logined : false
         * has_complete_userinfo : false
         * banner : http://api.okaoyan.com:9292/images/friend_banner_v2.jpg
         * images : /images/head2.jpg
         */

        private String statement_first;
        private String statement_second;
        private String statement_third;
        private boolean logined;
        private boolean has_complete_userinfo;
        private String banner;
        private String images;

        public String getStatement_first() {
            return statement_first;
        }

        public void setStatement_first(String statement_first) {
            this.statement_first = statement_first;
        }

        public String getStatement_second() {
            return statement_second;
        }

        public void setStatement_second(String statement_second) {
            this.statement_second = statement_second;
        }

        public String getStatement_third() {
            return statement_third;
        }

        public void setStatement_third(String statement_third) {
            this.statement_third = statement_third;
        }

        public boolean isLogined() {
            return logined;
        }

        public void setLogined(boolean logined) {
            this.logined = logined;
        }

        public boolean isHas_complete_userinfo() {
            return has_complete_userinfo;
        }

        public void setHas_complete_userinfo(boolean has_complete_userinfo) {
            this.has_complete_userinfo = has_complete_userinfo;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }
}
