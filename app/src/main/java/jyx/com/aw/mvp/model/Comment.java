package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/12/8 0008.
 * 功能：
 */

public class Comment {

    /**
     * code : 200
     * comments : [{"content":"utest728","article_id":100872,"username":"张杰韦","useravatar":"/upload/0tzsxj6mshcw.jpg"},{"content":"utest510","article_id":100872,"username":"张杰韦","useravatar":"/upload/0tzsxj6mshcw.jpg"}]
     * pagedata : {"current_page":1,"total_pages":1,"total_count":2,"current_total":10}
     */

    private int code;
    private PagedataBean pagedata;
    private List<CommentsBean> comments;

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

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
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

    public static class CommentsBean {
        /**
         * content : utest728
         * article_id : 100872
         * username : 张杰韦
         * useravatar : /upload/0tzsxj6mshcw.jpg
         */

        private String content;
        private int article_id;
        private String username;
        private String useravatar;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getArticle_id() {
            return article_id;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUseravatar() {
            return useravatar;
        }

        public void setUseravatar(String useravatar) {
            this.useravatar = useravatar;
        }
    }
}
