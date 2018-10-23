package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/12/14 0014.
 * 功能：
 */
public class BuyInformation {
    /**
     * code : 200
     * oto_litpic : {"litpic":"http://shop.okaoyan.com/images/oto.png","url":"https://www.baidu.com"}
     * public_litpic : {"litpic":"http://shop.okaoyan.com/images/general.png","url":"https://www.baidu.com"}
     * schools : [{"name":"南京师范大学","id":5410,"litpic":"http://api.okaoyan.com:9292/images/paper/%E5%8D%97%E4%BA%AC%E5%B8%88%E8%8C%83%E5%A4%A7%E5%AD%A6.png"},{"name":"浙江大学","id":5406,"litpic":"http://api.okaoyan.com:9292/images/paper/%E6%B5%99%E5%A4%A7.png"},{"name":"四川大学","id":5408,"litpic":"http://api.okaoyan.com:9292/images/paper/%E5%B7%9D%E5%A4%A7.jpg"},{"name":"南京大学","id":5409,"litpic":"http://api.okaoyan.com:9292/images/paper/%E5%8D%97%E5%A4%A7.png"},{"name":"暨南大学","id":5413,"litpic":"http://api.okaoyan.com:9292/images/paper/%E6%9A%A8%E5%8D%97.png"},{"name":"复旦大学","id":5412,"litpic":"http://api.okaoyan.com:9292/images/paper/%E5%A4%8D%E6%97%A6%E5%A4%A7%E5%AD%A6.png"},{"name":"苏州大学","id":5411,"litpic":"http://api.okaoyan.com:9292/images/paper/%E8%8B%8F%E5%B7%9E%E5%A4%A7%E5%AD%A6.png"},{"name":"华东政法大学","id":5414,"litpic":"http://shop.okaoyan.com/images/ico/hdzf.png"},{"name":"华东师范大学","id":5407,"litpic":"http://shop.okaoyan.com/images/ico/hdsf.png"},{"name":"上海财经大学","id":5415,"litpic":"http://shop.okaoyan.com/images/ico/shcj.png"},{"name":"武汉大学","id":5416,"litpic":"http://shop.okaoyan.com/images/ico/whdx.png"},{"name":"河海大学","id":6202,"litpic":"http://shop.okaoyan.com/images/ico/hhdx.png"},{"name":"南京农业大学","id":6180,"litpic":"http://shop.okaoyan.com/images/ico/njnydx.png"},{"name":"中国政法大学","id":6161,"litpic":"http://shop.okaoyan.com/images/ico/zgzfdx.png"},{"name":"中南大学","id":6111,"litpic":"http://shop.okaoyan.com/images/ico/zndx.png"},{"name":"南京理工大学","id":6225,"litpic":"http://shop.okaoyan.com/images/ico/njlgdx.png"},{"name":"上海交通大学","id":5816,"litpic":"http://api.okaoyan.com:9292/images/paper/%E4%B8%8A%E6%B5%B7%E4%BA%A4%E9%80%9A%E5%A4%A7%E5%AD%A6.png"},{"name":"对外经济贸易大学","id":5997,"litpic":"http://shop.okaoyan.com/images/ico/dwjmdx.png"},{"name":"南开大学","id":6027,"litpic":"http://shop.okaoyan.com/images/ico/nkdx.png"},{"name":"吉林大学","id":5940,"litpic":"http://shop.okaoyan.com/images/ico/jldx.png"},{"name":"华中科技大学","id":6061,"litpic":"http://shop.okaoyan.com/images/ico/hzkjdx.png"},{"name":"同济大学","id":5906,"litpic":"http://api.okaoyan.com:9292/images/paper/%E5%90%8C%E6%B5%8E.png"},{"name":"东南大学","id":5872,"litpic":"http://api.okaoyan.com:9292/images/paper/%E4%B8%9C%E5%8D%97%E5%A4%A7%E5%AD%A6.png"},{"name":"中国传媒大学","id":6246,"litpic":"http://shop.okaoyan.com/images/ico/cm.png"}]
     */

    private int code;
    private OtoLitpicBean oto_litpic;
    private PublicLitpicBean public_litpic;
    private List<SchoolsBean> schools;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public OtoLitpicBean getOto_litpic() {
        return oto_litpic;
    }

    public void setOto_litpic(OtoLitpicBean oto_litpic) {
        this.oto_litpic = oto_litpic;
    }

    public PublicLitpicBean getPublic_litpic() {
        return public_litpic;
    }

    public void setPublic_litpic(PublicLitpicBean public_litpic) {
        this.public_litpic = public_litpic;
    }

    public List<SchoolsBean> getSchools() {
        return schools;
    }

    public void setSchools(List<SchoolsBean> schools) {
        this.schools = schools;
    }

    public static class OtoLitpicBean {
        /**
         * litpic : http://shop.okaoyan.com/images/oto.png
         * url : https://www.baidu.com
         */

        private String litpic;
        private String url;

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class PublicLitpicBean {
        /**
         * litpic : http://shop.okaoyan.com/images/general.png
         * url : https://www.baidu.com
         */

        private String litpic;
        private String url;

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class SchoolsBean {
        /**
         * name : 南京师范大学
         * id : 5410
         * litpic : http://api.okaoyan.com:9292/images/paper/%E5%8D%97%E4%BA%AC%E5%B8%88%E8%8C%83%E5%A4%A7%E5%AD%A6.png
         */

        private String name;
        private int id;
        private String litpic;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
    }
}
