package jyx.com.aw.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JiangYunxian on 2018/1/3 0003.
 * 功能：
 */
public class AllQAndA implements Serializable{

    /**
     * code : 200
     * questions : [{"id":"5a4c47dc3b59205d7e43a3a0","created_at":1514948572,"title":"考浙大的笔试通过了，MBA面试要准备些什么，英语口语吗","updated_at":1514950608,"description":"","start_user":{"id":"5a4c47513b59205d7e43a395","username":"","avatar":"/images/head0.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[{"id":"575e773a6df0500844178910","name":"浙江大学  "}],"majors":[],"tag_ids":[],"answer_num":1,"views_count":12,"marked":false},{"id":"5a4c457a3b59205d7e43a2fd","created_at":1514947962,"title":"想问问我是大专毕业生两年了，想考四川大学在职研究生考试，可以考吗","updated_at":1514950665,"description":"","start_user":{"id":"5a4c446a3b59205d7e43a2e7","username":"","avatar":"/images/head1.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[{"id":"575e773b6df05008441789bf","name":"四川大学"}],"majors":[{"id":"575e773f6df0500844178cbd","name":"工商管理（专硕）"}],"tag_ids":[],"answer_num":1,"views_count":16,"marked":false},{"id":"5a4c1d3d3b59205d7e439e2c","created_at":1514937661,"title":"MBA复试需要考哪些科目，如何准备，需要选择导师吗？","updated_at":1514950248,"description":"","start_user":{"id":"5a4c1cd83b59205d7e439e2a","username":"","avatar":"/images/head2.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[{"id":"575e773b6df0500844178949","name":"江西财经大学  "}],"majors":[{"id":"575e773f6df0500844178cbd","name":"工商管理（专硕）"}],"tag_ids":[],"answer_num":1,"views_count":23,"marked":false},{"id":"5a4b9cf33b59205d7e439a10","created_at":1514904819,"title":"跨专业考研？","updated_at":1514950751,"description":"老师们好！我是一名大三学生，我的专业是信息与计算科学，然后大学一直学的数学，想以后研究人工智能方面的，但不知道考研考什么专业，以及报考院校，请指教！！！","start_user":{"id":"5a4b9ba83b59205d7e4399a9","username":"四级不过不改名","avatar":"/upload/5a4b9ba83b59205d7e4399a9.png","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[],"majors":[],"tag_ids":[],"answer_num":1,"views_count":30,"marked":false},{"id":"5a4b8bbb3b59205d7e43986d","created_at":1514900411,"title":"改革后的在职研究生考试临床类是参加一月份的统考吗","updated_at":1514950201,"description":"考试内容和全日制的一样吗？","start_user":{"id":"598d43f23b59204f527f2d42","username":"川大用户","avatar":"","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":true},"schools":[{"id":"575e773b6df05008441789bf","name":"四川大学"}],"majors":[{"id":"575e773e6df0500844178cb3","name":"临床医学（专硕）"}],"tag_ids":[],"answer_num":1,"views_count":28,"marked":false},{"id":"5a4b78613b59205d7e439577","created_at":1514895457,"title":"同等学历","updated_at":1514944924,"description":"","start_user":{"id":"5a42459f3b592071f71e4d40","username":"","avatar":"/images/head2.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[],"majors":[],"tag_ids":[],"answer_num":2,"views_count":13,"marked":false},{"id":"5a4b749d3b59205d7e439558","created_at":1514894493,"title":"上海交大法学专硕考试科目","updated_at":1514950658,"description":"","start_user":{"id":"5a4b74753b59205d7e439556","username":"","avatar":"/images/head3.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[{"id":"575e77396df05008441787f1","name":"上海交通大学"}],"majors":[],"tag_ids":[],"answer_num":2,"views_count":25,"marked":false},{"id":"5a4b66683b59205d7e43949d","created_at":1514890856,"title":"大专生 英语没过四级可以考研吗？","updated_at":1514950756,"description":"","start_user":{"id":"5a4b61023b59205d7e43942c","username":"","avatar":"/images/head3.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[{"id":"575e773a6df05008441788dc","name":"鞍山师范学院"}],"majors":[],"tag_ids":[],"answer_num":1,"views_count":79,"marked":false},{"id":"5a4af1ec3b592071f71f159e","created_at":1514861036,"title":"山西医科大学影像学超声方向","updated_at":1514949093,"description":"我本科是临床医学妇产方向，后考取执业医师证注册在内科，现要报考山西医科大学影像超声方向，专硕学硕都能报考吗？考学硕毕业了得规培吗？要想保留原工作，是不是得报定向生了？有知道的研友吗？麻烦了。","start_user":{"id":"5a4851593b592071f71ee729","username":"玲玲","avatar":"/images/head0.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false},"schools":[{"id":"575e773a6df05008441788b0","name":"山西医科大学"}],"majors":[{"id":"575e773d6df0500844178bcb","name":"影像医学与核医学"}],"tag_ids":[],"answer_num":4,"views_count":24,"marked":false},{"id":"5a4a50463b592071f71f0f63","created_at":1514819654,"title":"请问中南大学护理考研的资料哪里有卖的 求官方版的 谢谢","updated_at":1514950965,"description":null,"start_user":{"id":"5a4a4fe53b592071f71f0f57","username":"中南用户","avatar":"","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":true},"schools":[{"id":"575e773b6df0500844178981","name":"中南大学  "}],"majors":[{"id":"575e773d6df0500844178b1a","name":"护理学"}],"tag_ids":[],"answer_num":3,"views_count":60,"marked":false}]
     * pagedata : {"current_page":1,"total_pages":763,"total_count":7623,"current_total":10}
     */

    private int code;
    private PagedataBean pagedata;
    private List<QuestionsBean> questions;

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

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class PagedataBean {
        /**
         * current_page : 1
         * total_pages : 763
         * total_count : 7623
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

    public static class QuestionsBean implements Serializable{
        /**
         * id : 5a4c47dc3b59205d7e43a3a0
         * created_at : 1514948572
         * title : 考浙大的笔试通过了，MBA面试要准备些什么，英语口语吗
         * updated_at : 1514950608
         * description :
         * start_user : {"id":"5a4c47513b59205d7e43a395","username":"","avatar":"/images/head0.jpg","role":1,"self_sign":"莫愁前路无知己，天下谁人不识君！","is_anonymous":false}
         * schools : [{"id":"575e773a6df0500844178910","name":"浙江大学  "}]
         * majors : []
         * tag_ids : []
         * answer_num : 1
         * views_count : 12
         * marked : false
         */

        private String id;
        private int created_at;
        private String title;
        private int updated_at;
        private String description;
        private StartUserBean start_user;
        private int answer_num;
        private int views_count;
        private boolean marked;
        private List<SchoolsBean> schools;
        private List<MajorsBean> majors;
        private List<?> tag_ids;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public StartUserBean getStart_user() {
            return start_user;
        }

        public void setStart_user(StartUserBean start_user) {
            this.start_user = start_user;
        }

        public int getAnswer_num() {
            return answer_num;
        }

        public void setAnswer_num(int answer_num) {
            this.answer_num = answer_num;
        }

        public int getViews_count() {
            return views_count;
        }

        public void setViews_count(int views_count) {
            this.views_count = views_count;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked(boolean marked) {
            this.marked = marked;
        }

        public List<SchoolsBean> getSchools() {
            return schools;
        }

        public void setSchools(List<SchoolsBean> schools) {
            this.schools = schools;
        }

        public List<MajorsBean> getMajors() {
            return majors;
        }

        public void setMajors(List<MajorsBean> majors) {
            this.majors = majors;
        }

        public List<?> getTag_ids() {
            return tag_ids;
        }

        public void setTag_ids(List<?> tag_ids) {
            this.tag_ids = tag_ids;
        }

        public static class StartUserBean {
            /**
             * id : 5a4c47513b59205d7e43a395
             * username :
             * avatar : /images/head0.jpg
             * role : 1
             * self_sign : 莫愁前路无知己，天下谁人不识君！
             * is_anonymous : false
             */
            private String id;
            private String username;
            private String avatar;
            private int role;
            private String self_sign;
            private boolean is_anonymous;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public String getSelf_sign() {
                return self_sign;
            }

            public void setSelf_sign(String self_sign) {
                this.self_sign = self_sign;
            }

            public boolean isIs_anonymous() {
                return is_anonymous;
            }

            public void setIs_anonymous(boolean is_anonymous) {
                this.is_anonymous = is_anonymous;
            }
        }

        public static class SchoolsBean implements Serializable{
            /**
             * id : 575e773a6df0500844178910
             * name : 浙江大学
             */

            private String id;
            private String name;
           /* public static final Parcelable.Creator<SchoolsBean> CREATOR = new Creator(){

                @Override
                public SchoolsBean createFromParcel(Parcel source) {
                    // TODO Auto-generated method stub
                    // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
                    SchoolsBean p = new SchoolsBean();
                    p.setId(source.readString());
                    p.setName(source.readString());
                    return p;
                }

                @Override
                public SchoolsBean[] newArray(int size) {
                    // TODO Auto-generated method stub
                    return new SchoolsBean[size];
                }
            };*/
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

           /* @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(name);

            }*/
        }
        public static class MajorsBean implements Serializable{
            /**
             * id : 575e773a6df0500844178910
             * name : 浙江大学
             */

            private String id;
            private String name;
           /* public static final Parcelable.Creator<MajorsBean> CREATOR = new Creator(){

                @Override
                public MajorsBean createFromParcel(Parcel source) {
                    // TODO Auto-generated method stub
                    // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
                    MajorsBean p = new MajorsBean();
                    p.setId(source.readString());
                    p.setName(source.readString());
                    return p;
                }

                @Override
                public MajorsBean[] newArray(int size) {
                    // TODO Auto-generated method stub
                    return new MajorsBean[size];
                }
            };*/
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            /*@Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(name);
            }*/
        }
    }
}
