package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/11/7 0007.
 * 功能：学长推荐
 */

public class SeniorRecommendation {

    /**
     * code : 200
     * products : [{"aid":100846,"price":398,"trueprice":398,"title":"易休light黄金小睡助手苹果版/安卓版","litpic":"/uploads/allimg/170724/170911/171026/1-1G0261A431a4.jpg"},{"aid":100763,"price":3000,"trueprice":2180,"title":"考研派高分研究生一对一辅导","litpic":"/uploads/allimg/170724/170901/170905/1-1FZ5102SK11.jpg"},{"aid":100646,"price":3000,"trueprice":2180,"title":"高分学长一对一辅导","litpic":"/uploads/allimg/170724/170901/170905/1-1FZ5102SK11.jpg"},{"aid":88008,"price":1,"trueprice":1,"title":"文都网课优惠券（咨询学姐免费索取）","litpic":"/uploads/170601/170803/1-1FP3200452N9.png"},{"aid":88007,"price":1,"trueprice":1,"title":"新东方网课优惠券（咨询学姐免费索取）","litpic":"/uploads/160721/170803/1-1FP320051S05.png"},{"aid":87947,"price":56.8,"trueprice":41.8,"title":"【正版现货】2018肖秀荣考研政治 命题人1000题[考研派]","litpic":"/uploads/170609/1-1F609202039356.jpg"},{"aid":85876,"price":189.6,"trueprice":129,"title":"英语浪漫大礼包(2018张剑英语05-17全套)","litpic":"/uploads/170523/1-1F5231I3191a.jpg"},{"aid":85817,"price":166.5,"trueprice":112.8,"title":"【正版】2018肖秀荣政治三件套","litpic":"/uploads/170607/1-1F60H01Q0195.jpg"},{"aid":72909,"price":79.8,"trueprice":55.8,"title":"张剑考研黄皮书历年考研英语真题解析及复习思路（精编版）（2013-2017）","litpic":"/uploads/allimg/170214/170424/1-1F424145AA19.jpg"},{"aid":72770,"price":109.8,"trueprice":80.8,"title":"2018珍藏版张剑考研英语黄皮书(2005-2012)真题解析及复习思路","litpic":"/uploads/170519/1-1F51Z92RS05.jpg"},{"aid":72769,"price":59.8,"trueprice":54.2,"title":"2018肖秀荣考研政治知识点精讲精练","litpic":"/uploads/170519/1-1F519100529255.jpg"},{"aid":72704,"price":46.8,"trueprice":35.8,"title":"肖秀荣考研政治命题人讲真题（上册+下册）","litpic":"/uploads/allimg/170214/170503/1-1F50314222Bc.jpg"},{"aid":72703,"price":89.8,"trueprice":58.8,"title":"2018张剑考研英语阅读黄皮书150基础篇","litpic":"/uploads/170609/1-1F609202205C3.jpg"},{"aid":72578,"price":189.6,"trueprice":133.8,"title":"2018考研||张剑英语两件套","litpic":"/uploads/170417/1-1F41GPF4536.jpg"},{"aid":57965,"price":43.6,"trueprice":33.8,"title":"新东方考研英语词汇词根+联想记忆法(乱序版)","litpic":"/uploads/allimg/170214/170419/1-1F419125050294.jpg"},{"aid":57938,"price":88,"trueprice":23,"title":"2018考研微课堂第4批优惠学员限额招募","litpic":"/uploads/170524/1-1F524102950619.jpg"},{"aid":57937,"price":46,"trueprice":39.9,"title":"2018考研||恋练有词","litpic":"/uploads/allimg/170214/170417/1-1F41GR306332.jpg"}]
     */

    private int code;
    private List<ProductsBean> products;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * aid : 100846
         * price : 398.0
         * trueprice : 398.0
         * title : 易休light黄金小睡助手苹果版/安卓版
         * litpic : /uploads/allimg/170724/170911/171026/1-1G0261A431a4.jpg
         */

        private int aid;
        private double price;
        private double trueprice;
        private String title;
        private String litpic;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getTrueprice() {
            return trueprice;
        }

        public void setTrueprice(double trueprice) {
            this.trueprice = trueprice;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }
    }
}
