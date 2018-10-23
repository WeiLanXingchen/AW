package jyx.com.aw.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JiangYunxian on 2017/11/6 0006.
 * 功能：
 */

public class DailyList {

    /**
     * code : 200
     * article : [{"id":100858,"title":"【经验贴】考研伪学霸的那些\u201c鸡汤\u201d","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171106/1-1G106112331618.png","pubdate":1509937782,"abstract":"【经验贴】对于一个骨子里不是个爱学习的人，收到考研录取通知书的那刻，想和大家一起分享我这种伪学霸的毒鸡汤。并不是每个人都有资本做自己感兴趣的事，你唯一能做的就是用你的职业来养活你的兴趣。其实一开始，我","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100858/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171106/1-1G106112331618.png","views":1343},{"id":100857,"title":"速存！考研冲刺阶段复习时间表！","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171104/1-1G104102602441.png","pubdate":1509762383,"abstract":"  ","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100857/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171104/1-1G104102602441.png","views":3004},{"id":100856,"title":"考研派APP功能升级啦！快去下载新版本吧～","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171103/1-1G1031050593J.png","pubdate":1509676063,"abstract":"考研派APP升级啦！学弟学妹快去升级体验吧～升级内容：1.注册登录功能优化升级，短信验证通知更及时2.优化打卡功能，每天打卡更顺畅3.新增搜索功能，查看考研信息更快捷应用市场搜索考研派即可下载升级，一","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100856/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171103/1-1G1031050593J.png","views":701},{"id":100855,"title":"2018考研冲刺期焦虑如何克制？","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171103/1-1G1030955425Q.png","pubdate":1509673678,"abstract":"18考研已经进入冲刺期，很多考生容易焦虑，着急、紧张、复习没效果，整个人都不好了，该怎么办？情绪问题是冲刺阶段最大的敌人，即使你本来积累的很好，心态调整不好，极有可能满盘皆输。下面分享五个方法，希望能","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100855/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171103/1-1G1030955425Q.png","views":373},{"id":100854,"title":"2018考研：冲刺阶段最适合的3个记忆方法","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/171102/1-1G10209320Va.png","pubdate":1509584899,"abstract":"考研进入冲刺阶段，广大考生要注意把握好时间快速记忆知识点，下面小派总结分享3个记忆方法，正适合现在备战的你们，一起来Get下~~　　1、要相信内隐记忆这种神奇的存在所谓内隐记忆是指过去经验对个体当前活","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100854/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/171102/1-1G10209320Va.png","views":2657},{"id":100852,"title":"19考研 | 特色领域比肩985的十大低调高校","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171101/1-1G101094SH94.png","pubdate":1509499664,"abstract":"如果你以为所有优质高校都叫985或211，那你肯定没了解过这十所低调高能的大学。今天，大家就来了解一下吧。中北大学：国防技术孵化基地一所由山西省人民政府与国家国防科技工业局共建、山西省人民政府管理的多","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100852/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171101/1-1G101094SH94.png","views":3144},{"id":100850,"title":"听说你得了一学习就困的病？","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171031/1-1G0310930134S.png","pubdate":1509412536,"abstract":"春困秋乏夏打盹，睡不醒的冬三月，很多考研er用这句话为自己的昏昏欲睡找借口，可是各位考研er最不能忘记的就是时间不等人，再不清醒起来考研就要到了。从立秋后，天气早晚转凉；到了处暑，更是秋高气爽。为什么","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100850/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171031/1-1G0310930134S.png","views":2402},{"id":100849,"title":"【19考研】 避开这些考研复习的误区，你就离成功近了一半","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/171030/1-1G03010025E13.png","pubdate":1509327121,"abstract":"误区一：目标散漫，抓不住要害有的考生看似很认真，整天攻书本，抓到哪一科就学哪一科，缺少系统的学习计划，这样的复习不可能有效果。比如英语阅读，一些考生听说考研阅读文章都是来自国外著名报刊杂志，就花钱买了","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100849/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/171030/1-1G03010025E13.png","views":5054},{"id":100847,"title":"为考研的女生打call！","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171027/1-1G02G03333360.png","pubdate":1509070424,"abstract":"考研的路上，充分满了困难与坚持；考研的人儿是坚强的，踩着时间拼到最后；同时考研的人儿又是脆弱的，一句鼓励和一点爱意就让他们感动。有人说，选择了考研，就不要谈感情；也有人说，考研路是一条爱情路，一起走过","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100847/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171027/1-1G02G03333360.png","views":3863},{"id":100845,"title":"支招：最后一个月专业课应该怎么背？","pic":"https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171026/1-1G026094003445.png","pubdate":1508980640,"abstract":"对于很多考研党来说，背书是冲刺阶段的必修课，也是最让人头疼的事情。有的小伙伴因为记不住而没有了自信，有的因为背诵太辛苦而选择了放弃，还有的因为记不住而考了低分。专业课究竟该怎么背？快来看看吧。专业课背","url":"http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100845/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171026/1-1G026094003445.png","views":5412}]
     * pagedata : {"page_total":24,"page_per":10}
     */

    private int code;
    private PagedataBean pagedata;
    private List<ArticleBean> article;

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

    public List<ArticleBean> getArticle() {
        return article;
    }

    public void setArticle(List<ArticleBean> article) {
        this.article = article;
    }

    public static class PagedataBean {
        /**
         * page_total : 24
         * page_per : 10
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

    public static class ArticleBean {
        /**
         * id : 100858
         * title : 【经验贴】考研伪学霸的那些“鸡汤”
         * pic : https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171106/1-1G106112331618.png
         * pubdate : 1509937782
         * abstract : 【经验贴】对于一个骨子里不是个爱学习的人，收到考研录取通知书的那刻，想和大家一起分享我这种伪学霸的毒鸡汤。并不是每个人都有资本做自己感兴趣的事，你唯一能做的就是用你的职业来养活你的兴趣。其实一开始，我
         * url : http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100858/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171106/1-1G106112331618.png
         * views : 1343
         */

        private int id;
        private String title;
        private String pic;
        private int pubdate;
        @SerializedName("abstract")
        private String abstractX;
        private String url;
        private int views;

        @Override
        public String toString() {
            return "ArticleBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", pic='" + pic + '\'' +
                    ", pubdate=" + pubdate +
                    ", abstractX='" + abstractX + '\'' +
                    ", url='" + url + '\'' +
                    ", views=" + views +
                    '}';
        }

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPubdate() {
            return pubdate;
        }

        public void setPubdate(int pubdate) {
            this.pubdate = pubdate;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }
    }
}
