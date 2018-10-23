package jyx.com.aw.mvp.model;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/12/22 0022.
 * 功能：
 */
public class BuyOfSchoolDetailShopping {


    /**
     * code : 200
     * products : [{"id":67113,"title":"浙江大学互联网金融学考研真题资料全套","litpic":"http://shop.okaoyan.com/images/default_product.png","price":450,"customerize_type":"default","trueprice":368}]
     * oto : [{"id":58523,"title":"（预定）研究生学长考研辅导一对一","litpic":"http://shop.okaoyan.com/images/default_product.png","price":2000,"customerize_type":"oto","trueprice":2000},{"id":100737,"title":"高分研究生学姐考研一对一（考前答疑）","litpic":"http://shop.okaoyan.com/images/default_product.png","price":2000,"customerize_type":"oto","trueprice":998},{"id":100738,"title":"高分研究生学姐考研一对一（冲刺辅导）","litpic":"http://shop.okaoyan.com/images/default_product.png","price":10000,"customerize_type":"oto","trueprice":4500},{"id":100739,"title":"考研复试一对一辅导强化班（预报名）","litpic":"http://shop.okaoyan.com/images/default_product.png","price":8800,"customerize_type":"oto","trueprice":5000}]
     * video : []
     * general : [{"id":38886,"title":"2018张剑考研英语阅读黄皮书150基础篇","litpic":"http://www.okaoyan.com/uploads/170601/1-1F601154Z0603.jpg","price":89.8,"customerize_type":"default","trueprice":44.9},{"id":38887,"title":"2018新东方朱伟恋练有词考研英语词汇识记与应用大全","litpic":"http://www.okaoyan.com/uploads/170519/1-1F51Z9330X50.jpg","price":46,"customerize_type":"default","trueprice":39.9},{"id":38888,"title":"2018张剑考研黄皮书历年考研英语真题解析及复习思路(精编版)(2013-2017)","litpic":"http://www.okaoyan.com/uploads/170519/1-1F51ZZ25V00.jpg","price":79.8,"customerize_type":"default","trueprice":55.8},{"id":38889,"title":"2018珍藏版张剑考研英语黄皮书(2005-2012)真题解析及复习思路","litpic":"http://www.okaoyan.com/uploads/170519/1-1F51Z92RS05.jpg","price":109.8,"customerize_type":"default","trueprice":80.8},{"id":38890,"title":"数学甜蜜大礼包(李永乐数学+张宇数学36讲)","litpic":"http://www.okaoyan.com/uploads/allimg/170517/1-1F51H030501V-lp.jpg","price":295.8,"customerize_type":"default","trueprice":241.8},{"id":39011,"title":"新东方考研英语词汇词根+联想记忆法(乱序版)","litpic":"http://www.okaoyan.com/uploads/170518/1-1F51Q94913620.jpg","price":43.6,"customerize_type":"default","trueprice":33.8},{"id":39049,"title":"英语浪漫大礼包(2018张剑英语05-17全套)","litpic":"http://www.okaoyan.com/uploads/170523/1-1F5231J92U07.jpg","price":189.6,"customerize_type":"default","trueprice":129},{"id":39053,"title":"政治温馨大礼包(肖秀荣知识点精讲精练+讲真题上下册)","litpic":"http://www.okaoyan.com/uploads/allimg/170517/1-1F51G64441C4-lp.jpg","price":106.6,"customerize_type":"default","trueprice":88.5},{"id":39070,"title":"【正版现货】2018肖秀荣考研政治 命题人1000题[考研派]","litpic":"http://www.okaoyan.com/uploads/170609/1-1F609195P3194.png","price":56.8,"customerize_type":"default","trueprice":41.8},{"id":39111,"title":"2018张宇高等数学36讲","litpic":"http://www.okaoyan.com/uploads/allimg/170517/1-1F51H01444X6-lp.png","price":106.4,"customerize_type":"default","trueprice":92.8},{"id":39112,"title":"2018李永乐数学三复习大全","litpic":"http://www.okaoyan.com/uploads/allimg/170517/1-1F51G60Z19B-lp.png","price":189.4,"customerize_type":"default","trueprice":151},{"id":39119,"title":"2018肖秀荣考研政治知识点精讲精练","litpic":"http://www.okaoyan.com/uploads/170519/1-1F51Z94355164.jpg","price":59.8,"customerize_type":"default","trueprice":54.2},{"id":39122,"title":"2018肖秀荣考研政治命题人讲真题上下册","litpic":"http://www.okaoyan.com/uploads/170519/1-1F51Z94212J6.jpg","price":46.8,"customerize_type":"default","trueprice":35.8},{"id":39127,"title":"2018李永乐数学一复习大全","litpic":"http://www.okaoyan.com/uploads/allimg/170517/1-1F51G60322549-lp.png","price":189.4,"customerize_type":"default","trueprice":161.8},{"id":39149,"title":"2018李永乐数学二复习大全","litpic":"http://www.okaoyan.com/uploads/allimg/170517/1-1F51G553049A-lp.png","price":182.4,"customerize_type":"default","trueprice":145.8},{"id":39150,"title":"政英幸福大礼包(肖秀荣政治两件套+2018张剑英语05-17)","litpic":"http://www.okaoyan.com/uploads/allimg/170517/1-1F51GF640354-lp.jpg","price":296.2,"customerize_type":"default","trueprice":215.2},{"id":87519,"title":"2018考研微课堂第12批优惠学员限额招募","litpic":"http://www.okaoyan.com/uploads/170524/1-1F524102950619.jpg","price":88,"customerize_type":"default","trueprice":23},{"id":88033,"title":"【正版】2018肖秀荣政治三件套","litpic":"http://www.okaoyan.com/uploads/170607/1-1F60H01Q0195.jpg","price":166.5,"customerize_type":"default","trueprice":112.8}]
     */

    private int code;
    private List<ProductsBean> products;
    private List<OtoBean> oto;
    private List<VideoBean> video;
    private List<GeneralBean> general;

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

    public List<OtoBean> getOto() {
        return oto;
    }

    public void setOto(List<OtoBean> oto) {
        this.oto = oto;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public List<GeneralBean> getGeneral() {
        return general;
    }

    public void setGeneral(List<GeneralBean> general) {
        this.general = general;
    }

    public static class ProductsBean {
        /**
         * id : 67113
         * title : 浙江大学互联网金融学考研真题资料全套
         * litpic : http://shop.okaoyan.com/images/default_product.png
         * price : 450.0
         * customerize_type : default
         * trueprice : 368.0
         */

        private int id;
        private String title;
        private String litpic;
        private double price;
        private String customerize_type;
        private double trueprice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCustomerize_type() {
            return customerize_type;
        }

        public void setCustomerize_type(String customerize_type) {
            this.customerize_type = customerize_type;
        }

        public double getTrueprice() {
            return trueprice;
        }

        public void setTrueprice(double trueprice) {
            this.trueprice = trueprice;
        }
    }

    public static class OtoBean {
        /**
         * id : 58523
         * title : （预定）研究生学长考研辅导一对一
         * litpic : http://shop.okaoyan.com/images/default_product.png
         * price : 2000.0
         * customerize_type : oto
         * trueprice : 2000.0
         */

        private int id;
        private String title;
        private String litpic;
        private double price;
        private String customerize_type;
        private double trueprice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCustomerize_type() {
            return customerize_type;
        }

        public void setCustomerize_type(String customerize_type) {
            this.customerize_type = customerize_type;
        }

        public double getTrueprice() {
            return trueprice;
        }

        public void setTrueprice(double trueprice) {
            this.trueprice = trueprice;
        }
    }

    public static class VideoBean {
        /**
         * id : 38886
         * title : 2018张剑考研英语阅读黄皮书150基础篇
         * litpic : http://www.okaoyan.com/uploads/170601/1-1F601154Z0603.jpg
         * price : 89.8
         * customerize_type : default
         * trueprice : 44.9
         */

        private int id;
        private String title;
        private String litpic;
        private double price;
        private String customerize_type;
        private double trueprice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCustomerize_type() {
            return customerize_type;
        }

        public void setCustomerize_type(String customerize_type) {
            this.customerize_type = customerize_type;
        }

        public double getTrueprice() {
            return trueprice;
        }

        public void setTrueprice(double trueprice) {
            this.trueprice = trueprice;
        }
    }
    public static class GeneralBean {
        /**
         * id : 38886
         * title : 2018张剑考研英语阅读黄皮书150基础篇
         * litpic : http://www.okaoyan.com/uploads/170601/1-1F601154Z0603.jpg
         * price : 89.8
         * customerize_type : default
         * trueprice : 44.9
         */

        private int id;
        private String title;
        private String litpic;
        private double price;
        private String customerize_type;
        private double trueprice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCustomerize_type() {
            return customerize_type;
        }

        public void setCustomerize_type(String customerize_type) {
            this.customerize_type = customerize_type;
        }

        public double getTrueprice() {
            return trueprice;
        }

        public void setTrueprice(double trueprice) {
            this.trueprice = trueprice;
        }
    }
}
