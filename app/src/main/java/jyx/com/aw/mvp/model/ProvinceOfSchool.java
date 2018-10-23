package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/12/12 0012.
 * 功能：
 */

public class ProvinceOfSchool {

    /**
     * code : 200
     * type : [{"id":1235,"typename":"考研经验"},{"id":3123,"typename":"考研真题"},{"id":1121,"typename":"研究生院"},{"id":1122,"typename":"招生简章"},{"id":1123,"typename":"联系方式"},{"id":1124,"typename":"考研复试"},{"id":1125,"typename":"考研调剂"},{"id":1126,"typename":"研究生专业"},{"id":1127,"typename":"考研报录比"},{"id":1128,"typename":"考研分数线"},{"id":1129,"typename":"专业排名"},{"id":1130,"typename":"参考书目"},{"id":1131,"typename":"研究生导师"},{"id":1132,"typename":"考研问题"}]
     * school : {"id":33443,"thumb":"https://api-v2.okaoyan.com:8011/uploads/allimg/150822/160722/1-160H2160421X5.png","intro":"/api/v2/general/article/33443/show"}
     */

    private int code;
    private SchoolBean school;
    private List<TypeBean> type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SchoolBean getSchool() {
        return school;
    }

    public void setSchool(SchoolBean school) {
        this.school = school;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class SchoolBean {
        /**
         * id : 33443
         * thumb : https://api-v2.okaoyan.com:8011/uploads/allimg/150822/160722/1-160H2160421X5.png
         * intro : /api/v2/general/article/33443/show
         */

        private int id;
        private String thumb;
        private String intro;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }

    public static class TypeBean {
        /**
         * id : 1235
         * typename : 考研经验
         */

        private int id;
        private String typename;

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
