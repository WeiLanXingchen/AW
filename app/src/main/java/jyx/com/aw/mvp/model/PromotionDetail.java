package jyx.com.aw.mvp.model;

/**
 * Created by JiangYunxian on 2018/1/5 0005.
 * 功能：
 */
public class PromotionDetail {

    /**
     * code : 200
     * product : {"aid":100950,"price":40,"trueprice":29.8,"title":"《2019考研红宝书之百问百答》考研必备","body":"<img style=\"width: 100%;padding: 5px;\"  alt=\"\" src=\"https://api-v2.okaoyan.com:8011/uploads/171225/1-1G225104953625.jpg\"  />","litpic":"https://api-v2.okaoyan.com:8011/uploads/171225/1-1G225104Z2249.jpg","code":200,"surplus":15,"buy_num":350}
     */

    private int code;
    private ProductBean product;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public static class ProductBean {
        /**
         * aid : 100950
         * price : 40.0
         * trueprice : 29.8
         * title : 《2019考研红宝书之百问百答》考研必备
         * body : <img style="width: 100%;padding: 5px;"  alt="" src="https://api-v2.okaoyan.com:8011/uploads/171225/1-1G225104953625.jpg"  />
         * litpic : https://api-v2.okaoyan.com:8011/uploads/171225/1-1G225104Z2249.jpg
         * code : 200
         * surplus : 15
         * buy_num : 350
         */

        private int aid;
        private double price;
        private double trueprice;
        private String title;
        private String body;
        private String litpic;
        private int code;
        private int surplus;
        private int buy_num;

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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getSurplus() {
            return surplus;
        }

        public void setSurplus(int surplus) {
            this.surplus = surplus;
        }

        public int getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(int buy_num) {
            this.buy_num = buy_num;
        }
    }
}
