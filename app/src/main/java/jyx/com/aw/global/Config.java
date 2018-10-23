package jyx.com.aw.global;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.BuildConfig;
import jyx.com.aw.act.ActSchool;

/**
 * 作者：
 * 邮箱：
 * 功能：配置信息
 */
public class Config {
    public static boolean DEBUG = BuildConfig.LOG_DEBUG;

//    public static String BASE_URL = Config.DEBUG ? "http://192.168.16.119:8003/" : "http://123.56.187.96:9005/";

    public static String BASE_HTML = "";
    //    public static String BASE_URL ="http://api.okaoyan.com:9292/";
//    public static String BASE_URL ="http://api-v2.okaoyan.com:8010/";
    public static String[] BASE_HEAD_URL = new String[]{"http://api.okaoyan.com:9292", "http://api-v2.okaoyan.com:8010"};
    public static String M_CODE = "8686dd8bafbd4327";
    public static String[] BASE_URL = new String[]{"http://api.okaoyan.com:9292/",
            "http://api-v2.okaoyan.com:8010/",
            "http://shop.okaoyan.com/",
            "http://114.55.174.129:8010/"
    };
    public static String[] TITLE = {
            "院校",
            "专业",
            "经验",
            "题库",
            "下真题",
            "买资料",
            "背单词",
            "撩学长",
    };
    //http://api.okaoyan.com:9292/upload/zte5iv3dvcvw.jpg
    //http://api.okaoyan.com:9292/api/v4/time?mcode=8686dd8bafbd4327

    //http://shop.okaoyan.com/api/index/promotion?mcode=8686dd8bafbd4327
    //http://api-v2.okaoyan.com:8010/api/v3/general/kaoyan_daily/list?mcode=8686dd8bafbd4327&page=1
    //http://api-v2.okaoyan.com:8010/api/v3/general/kaoyan_daily/list?mcode=8686dd8bafbd4327&page=2
    //http://shop.okaoyan.com/api/index/promotion?mcode=8686dd8bafbd4327
    //http://api-v2.okaoyan.com:8010/uploads/allimg/170724/170911/171026/1-1G0261A431a4.jpg

    //http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100862/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171109/1-1G109111QJ33.png
    //http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100861/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171108/1-1G10Q04544448.png
    //http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100860/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171107/1-1G10G01523E2.png
    //http://114.55.174.129:8010/api/v3/general/kaoyan_daily/100858/show?litpic=https://api-v2.okaoyan.com:8011/uploads/allimg/170911/171106/1-1G106112331618.png
    //院校
    //http://api-v2.okaoyan.com:8010/api/v2/school/list_with_area?mcode=8686dd8bafbd4327
    //专业
    //http://api-v2.okaoyan.com:8010/api/v2/major/list?mcode=8686dd8bafbd4327
    //http://api-v2.okaoyan.com:8010/api/v2/general/type/58?mcode=8686dd8bafbd4327&page=-1
    //http://api-v2.okaoyan.com:8010/api/v2/general/type/59?mcode=8686dd8bafbd4327&page=-1
    //经验
    //http://api-v2.okaoyan.com:8010/api/v3/experience/list?mcode=8686dd8bafbd4327&page=1
    //http://api.okaoyan.com:9292/api/v3/article/100872/comment?mcode=8686dd8bafbd4327
    //http://api.okaoyan.com:9292/api/v3/article/100871/comment?mcode=8686dd8bafbd4327

    //http://api-v2.okaoyan.com:8010/api/v2/general/article/100871/show
    //http://api-v2.okaoyan.com:8010/api/v2/general/article/100843/show
    //http://api.okaoyan.com:9292/upload/0tzsxj6mshcw.jpg

    //http://api-v2.okaoyan.com:8010/api/v3/general/type/127/article_list?mcode=8686dd8bafbd4327&page=1
    //http://api-v2.okaoyan.com:8010/api/v3/general/type/136/article_list?mcode=8686dd8bafbd4327&page=1

    //http://api-v2.okaoyan.com:8010/api/v2/school/31?mcode=8686dd8bafbd4327
    //http://api-v2.okaoyan.com:8010/api/v3/general/type/1128/article_list?page=1&mcode=8686dd8bafbd4327

    //https://api-v2.okaoyan.com:8011/api/v2/general/article/33433/show
    //http://api-v2.okaoyan.com:8010/api/v2/school/44?mcode=8686dd8bafbd4327
    //买资料
    //http://shop.okaoyan.com/api/index?mcode=8686dd8bafbd4327
    //http://api.okaoyan.com:9292/api/v4/general/shop_initial

    //https://api.okaoyan.com:9293/images/shop/oto_inner.jpg

    //http://shop.okaoyan.com/api/index/school_desc?sid=5409
    //http://shop.okaoyan.com/api/index/school/5409?mcode=8686dd8bafbd4327

    //公共课资料
    //http://shop.okaoyan.com/api/index/product/general?mcode=8686dd8bafbd4327
    //学院
    //http://shop.okaoyan.com/api/index/school_desc?sid=5410
    //http://shop.okaoyan.com/api/index/school/5410?mcode=8686dd8bafbd4327
    //http://shop.okaoyan.com/api/index/contact
    //http://shop.okaoyan.com/api/index/product/38888?mcode=8686dd8bafbd4327

    //http://shop.okaoyan.com/api/index/school/major/57973?mcode=8686dd8bafbd4327
    //问答
    //http://api.okaoyan.com:9292/api/v3/questions?page=1&mcode=8686dd8bafbd4327
    //全部回答
    //http://api.okaoyan.com:9292/api/v3/questions/5a4c457a3b59205d7e43a2fd/answers?mcode=8686dd8bafbd4327&page=1
    //http://api.okaoyan.com:9292/upload/1ar7y7si2er3.jpg
     //商品推荐详情
    //http://api.okaoyan.com:9292/api/v3/forum/detail?forum_id=58227e923b59206c57d40da9&mcode=8686dd8bafbd4327
    //坛子
    //http://api.okaoyan.com:9292/api/v3/forum/recommend_posts?forum_id=58227e923b59206c57d40da9&mcode=8686dd8bafbd4327
    //http://api.okaoyan.com:9292/api/v3/forum/posts?forum_id=58227e923b59206c57d40da9&mcode=8686dd8bafbd4327&page=1
    //http://api.okaoyan.com:9292/api/v3/forum/detail?forum_id=58227e923b59206c57d40da9&mcode=8686dd8bafbd4327
    //学长推荐
    //http://shop.okaoyan.com/api/index/promotion/100950?mcode=8686dd8bafbd4327
    //注册
    //http://api.okaoyan.com:9292/api/v2/get_captcha?mcode=8686dd8bafbd4327 //图形验证码
    //http://api.okaoyan.com:9292/api/v2/auth/send_code?mcode=8686dd8bafbd4327&captcha_id=5oMiGIHPpNXWXqChlQU3&solution=3745//post发送验证码
    //http://api.okaoyan.com:9292/api/v1/auth/register?mcode=8686dd8bafbd4327//注册
    //http://api.okaoyan.com:9292/api/v3/user/mine_v3?mcode=8686dd8bafbd4327//我的
    //找研友
    //http://api.okaoyan.com:9292/api/v5/friend/banner?mcode=8686dd8bafbd4327 //banner
    //http://api.okaoyan.com:9292/api/v1/school/all_school_data?mcode=8686dd8bafbd4327 //院校选择
    //http://api.okaoyan.com:9292/api/v5/friend/most_popular?mcode=8686dd8bafbd4327 //最受欢迎
    //http://api.okaoyan.com:9292/api/v5/friend/best_match?mcode=8686dd8bafbd4327&page=1 //最佳匹配
    //

}
