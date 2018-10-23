package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/11/17 0017.
 * 功能：
 */

public class MajorLeft {

    /**
     * code : 200
     * majors : [{"id":58,"typename":"哲学"},{"id":59,"typename":"经济学"},{"id":60,"typename":"法学"},{"id":61,"typename":"教育学"},{"id":62,"typename":"文学"},{"id":63,"typename":"历史学"},{"id":64,"typename":"理学"},{"id":65,"typename":"工学"},{"id":66,"typename":"农学"},{"id":67,"typename":"医学"},{"id":68,"typename":"军事学"},{"id":69,"typename":"管理学"},{"id":70,"typename":"艺术学"}]
     * major_rank : {"id":1272,"typename":"专业排名"}
     */

    private int code;
    private MajorRankBean major_rank;
    private List<MajorsBean> majors;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MajorRankBean getMajor_rank() {
        return major_rank;
    }

    public void setMajor_rank(MajorRankBean major_rank) {
        this.major_rank = major_rank;
    }

    public List<MajorsBean> getMajors() {
        return majors;
    }

    public void setMajors(List<MajorsBean> majors) {
        this.majors = majors;
    }

    public static class MajorRankBean {
        /**
         * id : 1272
         * typename : 专业排名
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

    public static class MajorsBean {
        /**
         * id : 58
         * typename : 哲学
         */

        private int id;
        private String typename;

        @Override
        public String toString() {
            return "MajorsBean{" +
                    "id=" + id +
                    '}';
        }

        private  List<MajorListBean> mList;

        public List<MajorListBean> getmList() {
            return mList;
        }

        public void setmList(List<MajorListBean> mList) {
            this.mList = mList;
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
