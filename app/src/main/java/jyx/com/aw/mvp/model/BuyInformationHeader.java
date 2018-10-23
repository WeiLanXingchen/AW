package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/12/15 0015.
 * 功能：
 */
public class BuyInformationHeader {

    /**
     * code : 200
     * banner : [{"image":"https://api.okaoyan.com:9293/images/shop/oto.png","inner_image":"https://api.okaoyan.com:9293/images/shop/oto_inner.jpg","title":"考研派一对一辅导"},{"image":"https://api.okaoyan.com:9293/images/shop/aboutus.png","inner_image":"https://api.okaoyan.com:9293/images/shop/aboutus_inner.jpg","title":"关于考研派"},{"image":"https://api.okaoyan.com:9293/images/shop/notice.png","inner_image":"https://api.okaoyan.com:9293/images/shop/notice_inner.jpg","title":"购买须知"}]
     * buy_record : ["张同学 购买了南京大学636哲学综合（B）考研真题资料全套","bruce\t购买了四川大学635文学评论写作+914中国文学考研真题资料全套","鑫\t购买了2018考研红宝书之百问百答","mandy\t购买了武汉大学821工商管理基本理论考研真题资料全套","刘同学\t购买了武汉大学941地理信息系统基础考研真题资料全套","仔仔\t购买了浙江大学850工程力学考研真题资料全套","童童\t购买了苏州大学828翻译与写作考研资料","三岁梦\t购买了中国传媒大学336艺术基础+826艺术综合考研资料全套","张同学\t购买了复旦大学814英语语言学理论考研资料","刘同学\t购买了河海大学水文学原理笔记","李同学\t购买了华中科技大学852管理经济学考研真题资料全套","周同学\t购买了华东师范大学856管理学(A)考研真题资料全套","三山居士\t购买了华东政法大学615民商法学考研真题资料全套","雪花\t购买了吉林大学环境法学考研资料全套","路飞\t购买了暨南大学822工程力学考研真题资料全套","王同学\t购买了上海交通大学822基本电路理论考研资料全套","海明\t购买了南开大学735法学综合考研资料全套","yummy\t购买了上海财经大学431金融学综合考研资料全套","蒲公英的梦想\t购买了同济大学820环境工程考研真题资料全套","夏同学\t购买了浙江大学850工程力学考研真题资料全套"]
     * bottom_text : ["如果没有找到你想要的资料，请微信联系","考研派秦学姐: kaoyan5200","考研派韩学姐: kaoyan5203","我们会为您尽快整理"]
     */

    private int code;
    private List<BannerBean> banner;
    private List<String> buy_record;
    private List<String> bottom_text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<String> getBuy_record() {
        return buy_record;
    }

    public void setBuy_record(List<String> buy_record) {
        this.buy_record = buy_record;
    }

    public List<String> getBottom_text() {
        return bottom_text;
    }

    public void setBottom_text(List<String> bottom_text) {
        this.bottom_text = bottom_text;
    }

    public static class BannerBean {
        /**
         * image : https://api.okaoyan.com:9293/images/shop/oto.png
         * inner_image : https://api.okaoyan.com:9293/images/shop/oto_inner.jpg
         * title : 考研派一对一辅导
         */

        private String image;
        private String inner_image;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getInner_image() {
            return inner_image;
        }

        public void setInner_image(String inner_image) {
            this.inner_image = inner_image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
