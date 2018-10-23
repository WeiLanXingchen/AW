package jyx.com.aw.act;

import android.os.Bundle;

import jyx.com.aw.R;
import jyx.com.aw.adapter.MyGridViewAdapter;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.base.BaseRefreshActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.mvp.model.SeniorRecommendation;
import jyx.com.aw.retrofit.HttpApi;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.DividerGridView;
import jyx.com.aw.view.FixedGridView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JiangYunxian on 2017/11/10 0010.
 * 功能：
 */
public class ActGoodsRecommend extends BaseActivity{
    private DividerGridView mFixedGridViewGoodsRecommend;
    private MyGridViewAdapter mMyGridViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_goods_recommend);
        mFixedGridViewGoodsRecommend= (DividerGridView) findViewById(R.id.mFixedGridViewGoodsRecommend);
        mBar.setTitle("商品推荐");
        getGridViewData();
        mMyGridViewAdapter = new MyGridViewAdapter(this,0);
        mFixedGridViewGoodsRecommend.setAdapter(mMyGridViewAdapter);
        initViews();
    }

    private void getGridViewData() {
        addSubscription(mHttpApi2.getSeniorReData(Config.M_CODE), new SubscriberCallBack<>(new ApiCallback<SeniorRecommendation>() {
            @Override
            public void onSuccess(SeniorRecommendation model) {
//                Log.i("@@@",model.toString());
                mMyGridViewAdapter.setList(model.getProducts());
                dismissLoadingView();
            }

            @Override
            public void onFailure(int code, String msg) {
                AppUtil.showToast(msg);
                dismissLoadingView();
            }

            @Override
            public void onCompleted() {

            }
        }));
    }
    private void initViews() {

    }


}
