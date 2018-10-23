package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/11/17 0017.
 * 功能：
 */

public class MajorRight {

    /**
     * code : 200
     * type : [{"id":126,"typename":"哲学考研"},{"id":127,"typename":"马克思主义哲学考研"},{"id":128,"typename":"中国哲学考研"},{"id":129,"typename":"外国哲学考研"},{"id":130,"typename":"逻辑学考研"},{"id":131,"typename":"伦理学考研"},{"id":132,"typename":"美学考研"},{"id":133,"typename":"宗教学考研"},{"id":134,"typename":"科学技术哲学考研"}]
     * pagedata : {"page_total":1,"page_per":10000}
     */

    private int code;
    private PagedataBean pagedata;
    private List<TypeBean> type;

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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class PagedataBean {
        /**
         * page_total : 1
         * page_per : 10000
         */

        private int page_total;
        private int page_per;

        public int getPage_total() {
            return page_total;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }

        public int getPage_per() {
            return page_per;
        }

        public void setPage_per(int page_per) {
            this.page_per = page_per;
        }
    }

    public static class TypeBean {
        /**
         * id : 126
         * typename : 哲学考研
         */

        private int id;
        private String typename;
        private boolean isCheck;

        @Override
        public String toString() {
            return "TypeBean{" +
                    "typename='" + typename + '\'' +
                    '}';
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
