package jyx.com.aw.mvp.model;

/**
 * Created by JiangYunxian on 2017/11/3 0003.
 * 功能：
 */

public class Banner {

    /**
     * code : 200
     * day : 49
     * percent : 86
     * banner : {"image":"/upload/zte5iv3dvcvw.jpg","text":"我们不是看到了希望才去坚持，而是坚持才看到了希望！","banner_id":"59e062a03b59204ee49c4204","is_agreed":false,"agreed_count":1}
     */

    private int code;
    private int day;
    private int percent;
    private BannerBean banner;

    @Override
    public String toString() {
        return "Banner{" +
                "code=" + code +
                ", day=" + day +
                ", percent=" + percent +
                ", banner=" + banner +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * image : /upload/zte5iv3dvcvw.jpg
         * text : 我们不是看到了希望才去坚持，而是坚持才看到了希望！
         * banner_id : 59e062a03b59204ee49c4204
         * is_agreed : false
         * agreed_count : 1
         */

        private String image;
        private String text;
        private String banner_id;
        private boolean is_agreed;
        private int agreed_count;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(String banner_id) {
            this.banner_id = banner_id;
        }

        public boolean isIs_agreed() {
            return is_agreed;
        }

        public void setIs_agreed(boolean is_agreed) {
            this.is_agreed = is_agreed;
        }

        public int getAgreed_count() {
            return agreed_count;
        }

        public void setAgreed_count(int agreed_count) {
            this.agreed_count = agreed_count;
        }
    }
}
