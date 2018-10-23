package jyx.com.aw.retrofit;


import jyx.com.aw.mvp.model.AllQAndA;
import jyx.com.aw.mvp.model.AllResponseList;
import jyx.com.aw.mvp.model.Banner;
import jyx.com.aw.mvp.model.BuyInformation;
import jyx.com.aw.mvp.model.BuyInformationHeader;
import jyx.com.aw.mvp.model.BuyOfSchoolDetail;
import jyx.com.aw.mvp.model.BuyOfSchoolDetailHeader;
import jyx.com.aw.mvp.model.BuyOfSchoolDetailShopping;
import jyx.com.aw.mvp.model.Comment;
import jyx.com.aw.mvp.model.DailyList;
import jyx.com.aw.mvp.model.Experience;
import jyx.com.aw.mvp.model.FindFriendsBanner;
import jyx.com.aw.mvp.model.MajorLeft;
import jyx.com.aw.mvp.model.MajorRight;
import jyx.com.aw.mvp.model.PromotionDetail;
import jyx.com.aw.mvp.model.ProvinceOfSchool;
import jyx.com.aw.mvp.model.PublicMaterial;
import jyx.com.aw.mvp.model.Schools;
import jyx.com.aw.mvp.model.SeniorRecommendation;
import jyx.com.aw.mvp.model.login.LoginReq;
import jyx.com.aw.mvp.model.login.LoginRes;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：yongzhou on 16/6/15
 * 邮箱：zhouyong@cdzmd.com
 * 功能：定义请求接口
 */
public interface HttpApi {
      String q="";
    //用户登录
    @POST("/Api/User/Login")
    Observable<LoginRes> submitLogin(@Body LoginReq req);



    //首页图片滚动条
    @GET("api/v4/time")
    Observable<Banner> getBannerData(
            @Query("mcode") String mcode
    );
    //考研日报
    @GET("api/v3/general/kaoyan_daily/list")
    Observable<DailyList> getDailyListData(
            @Query("mcode") String mcode,
            @Query("page") int page
    );
    //学长推荐
    @GET("api/index/promotion")
    Observable<SeniorRecommendation> getSeniorReData(
            @Query("mcode") String mcode
    );
    //院校
    @GET("api/v2/school/list_with_area")
    Observable<Schools> getSchoolsData(
            @Query("mcode") String mcode
    );

    //专业
    @GET("api/v2/major/list")
    Observable<MajorLeft> getMajorData(
            @Query("mcode") String mcode
    );
    //专业类型
    @GET("api/v2/general/type/{id}")
    Observable<MajorRight> getMajorTypeData(
            @Path("id") int id,
            @Query("mcode") String mcode,
            @Query("page") String page
    );
    //经验
    @GET("api/v3/experience/list")
    Observable<Experience> getExperienceData(
            @Query("mcode") String mcode,
            @Query("page") int page
    );
    //经验评论
    @GET("api/v3/article/{id}/comment")
    Observable<Comment> getCommentData(
            @Path("id") int id,
            @Query("mcode") String mcode
            );
    //经验
    @GET("api/v3/general/type/{id}/article_list")
    Observable<Experience> getTypeData(
            @Path("id") int id,
            @Query("mcode") String mcode,
            @Query("page") int page
    );
    //院校
    @GET("api/v2/school/{id}")
    Observable<ProvinceOfSchool> getProvinceOfSchoolData(
            @Path("id") String id,
            @Query("mcode") String mcode
    );
    //买资料
    @GET("api/index")
    Observable<BuyInformation> getBuyInformationData(
            @Query("mcode") String mcode
    );
    //买资料头布局
    @GET("api/v4/general/shop_initial")
    Observable<BuyInformationHeader> getBuyInformationHeaderData(
    );
    //公共资料课
    @GET("api/index/product/general")
    Observable<PublicMaterial> getPublicMaterialData(
            @Query("mcode") String mcode
    );
    //买资料里面的院校
    @GET("api/index/school/{id}")
    Observable<BuyOfSchoolDetail> getBuyOfSchoolDetailData(
            @Path("id") int id,
            @Query("mcode") String mcode
    );
    //买资料里面的院校头
    @GET("api/index/school_desc")
    Observable<BuyOfSchoolDetailHeader> getBuyOfSchoolDetailHeaderData(
            @Query("sid") int sid
    );
    //学校资料
    @GET("api/index/school/major/{id}")
    Observable<BuyOfSchoolDetailShopping> getBuyOfSchoolDetailShoppingData(
            @Path("id") int id,
            @Query("mcode") String mcode
    );
    //全部问答
    @GET("api/v3/questions")
    Observable<AllQAndA> getAllQAndAData(
            @Query("page") int page,
            @Query("mcode") String mcode
            );
    //全部问答
    @GET("api/v3/questions/{id}/answers")
    Observable<AllResponseList> getAllQAndADetailData(
            @Path("id") String id,
            @Query("mcode") String mcode,
            @Query("page") int page
    );
    //全部问答
    @GET("api/index/promotion/{id}")
    Observable<PromotionDetail> getPromotionDetailData(
            @Path("id") int id,
            @Query("mcode") String mcode
    );
    //找研友滚动条
    @GET("api/v5/friend/banner")
    Observable<FindFriendsBanner> getFindFriendsBannerData(
            @Query("mcode") String mcode
    );
    //考研日报详情
    //api/v3/general/kaoyan_daily/100862/show
//    @GET("api/index/promotionapi/v3/general/kaoyan_daily/"+q+"/show")
//        Observable<SeniorRecommendation> getDailyDetailData(
//            @Query("litpic") String litpic
//    );
}
