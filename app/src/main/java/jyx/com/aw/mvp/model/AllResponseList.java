package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2018/1/3 0003.
 * 功能：
 */
public class AllResponseList {

    /**
     * code : 200
     * answers : [{"id":"5a4c6d513b5920153b45d940","agree":0,"disagree":0,"context":"呵呵","question_id":"5a4c457a3b59205d7e43a2fd","user_id":"59f97d0a3b59206481b51852","user_name":"流氓兔","user_avatar":"","is_agree":false,"updated_at":"2小时前","comments_num":0,"question_title":"想问问我是大专毕业生两年了，想考四川大学在职研究生考试，可以考吗","has_marked":"false","images":[],"css":""},{"id":"5a4c4ce43b59205d7e43a40e","agree":0,"disagree":0,"context":"可以的，方便的话加下考研派学姐微信kaoyan5200提供更多咨询","question_id":"5a4c457a3b59205d7e43a2fd","user_id":"5772d9023b59207ed76562b8","user_name":"大鲨鱼","user_avatar":"/upload/1ar7y7si2er3.jpg","is_agree":false,"updated_at":"5小时前","comments_num":0,"question_title":"想问问我是大专毕业生两年了，想考四川大学在职研究生考试，可以考吗","has_marked":"false","images":[],"css":""}]
     * pagedata : {"current_page":1,"total_pages":1,"total_count":2,"current_total":10}
     */

    private int code;
    private PagedataBean pagedata;
    private List<AnswersBean> answers;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PagedataBean getPagedata() {
        return pagedata;
    }

    public void setPagedata(PagedataBean pagedata) {
        this.pagedata = pagedata;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static class PagedataBean {
        /**
         * current_page : 1
         * total_pages : 1
         * total_count : 2
         * current_total : 10
         */

        private int current_page;
        private int total_pages;
        private int total_count;
        private int current_total;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getCurrent_total() {
            return current_total;
        }

        public void setCurrent_total(int current_total) {
            this.current_total = current_total;
        }
    }

    public static class AnswersBean {
        /**
         * id : 5a4c6d513b5920153b45d940
         * agree : 0
         * disagree : 0
         * context : 呵呵
         * question_id : 5a4c457a3b59205d7e43a2fd
         * user_id : 59f97d0a3b59206481b51852
         * user_name : 流氓兔
         * user_avatar :
         * is_agree : false
         * updated_at : 2小时前
         * comments_num : 0
         * question_title : 想问问我是大专毕业生两年了，想考四川大学在职研究生考试，可以考吗
         * has_marked : false
         * images : []
         * css :
         */

        private String id;
        private int agree;
        private int disagree;
        private String context;
        private String question_id;
        private String user_id;
        private String user_name;
        private String user_avatar;
        private boolean is_agree;
        private String updated_at;
        private int comments_num;
        private String question_title;
        private String has_marked;
        private String css;
        private List<?> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAgree() {
            return agree;
        }

        public void setAgree(int agree) {
            this.agree = agree;
        }

        public int getDisagree() {
            return disagree;
        }

        public void setDisagree(int disagree) {
            this.disagree = disagree;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public boolean isIs_agree() {
            return is_agree;
        }

        public void setIs_agree(boolean is_agree) {
            this.is_agree = is_agree;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getComments_num() {
            return comments_num;
        }

        public void setComments_num(int comments_num) {
            this.comments_num = comments_num;
        }

        public String getQuestion_title() {
            return question_title;
        }

        public void setQuestion_title(String question_title) {
            this.question_title = question_title;
        }

        public String getHas_marked() {
            return has_marked;
        }

        public void setHas_marked(String has_marked) {
            this.has_marked = has_marked;
        }

        public String getCss() {
            return css;
        }

        public void setCss(String css) {
            this.css = css;
        }

        public List<?> getImages() {
            return images;
        }

        public void setImages(List<?> images) {
            this.images = images;
        }
    }
}
