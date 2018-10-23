package jyx.com.aw.act;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import jyx.com.aw.R;
import jyx.com.aw.adapter.BuyInformationAdapter;
import jyx.com.aw.adapter.HeaderBottomAdapter;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.HomeCenterClickListener;
import jyx.com.aw.impl.OnBuyInformationInnerClickListener;
import jyx.com.aw.impl.OnBuyOfSchoolListener;
import jyx.com.aw.mvp.model.BuyInformation;
import jyx.com.aw.mvp.model.BuyInformationHeader;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;

/**
 * Created by JiangYunxian on 2017/12/14 0014.
 * 功能：
 */

public class ActBuyInformation extends BaseActivity implements OnBuyInformationInnerClickListener,OnBuyOfSchoolListener{
//    private BuyInformationAdapter adapter;
//    private RecyclerViewHeader header;
    @Bind(R.id.mRecyclerViewBuyInformation)
    protected RecyclerView mRecyclerViewBuyInformation;
    private HeaderBottomAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_buy_information);
        mBar.setTitle("买资料");
        initList();

    }
    private void initHeader(final BuyInformation model) {
        addSubscription(mHttpApi.getBuyInformationHeaderData(), new SubscriberCallBack<>(new ApiCallback<BuyInformationHeader>() {
            @Override
            public void onSuccess(BuyInformationHeader header) {
                if (header!=null){
                    getSuccess(model.getSchools(),model.getOto_litpic(),model.getPublic_litpic(),header);
                }
                dismissLoadingView();
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onCompleted() {

            }
        }));
    }

    private void initList() {
        addSubscription(mHttpApi2.getBuyInformationData(Config.M_CODE), new SubscriberCallBack<>(new ApiCallback<BuyInformation>() {
            @Override
            public void onSuccess(BuyInformation model) {
//                Log.i("@@@", model.getSchools().get(0).getName());
                initHeader(model);
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


    /*private void getSuccess(List<BuyInformation.SchoolsBean> schools) {
        if (schools != null) {
            adapter = new BuyInformationAdapter(this, schools);
            //初始化RecyclerViewHeader
            header = RecyclerViewHeader.fromXml(this, R.layout.buy_information_footer);
//            initData(id);
            mRecyclerViewBuyInformation.setLayoutManager(new GridLayoutManager(this, 3));
            //把RecyclerViewHeader赋予RecyclerView,这个一定要写在setLayoutManager之后
//            header.attachTo(mRecyclerViewBuyInformation);
            mRecyclerViewBuyInformation.setAdapter(adapter);
//            adapter.setProvinceOfSchoolClickListener(this);

        }
    }*/
    private void getSuccess(List<BuyInformation.SchoolsBean> schools,BuyInformation.OtoLitpicBean oto_litpic,
                            BuyInformation.PublicLitpicBean public_litpic,BuyInformationHeader header) {
        if (schools != null) {
            gridLayoutManager = new GridLayoutManager(this, 3);
            mRecyclerViewBuyInformation.setLayoutManager(gridLayoutManager);//这里用网格宫格显示 类似于grid view
            mRecyclerViewBuyInformation.setAdapter(adapter = new HeaderBottomAdapter(this,schools,oto_litpic,public_litpic,header));
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (adapter.isHeaderView(position) || adapter.isBottomView(position)) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
            adapter.setOnBuyInformationInnerClickListener(this);
            adapter.setBuyOfSchoolClickListener(this);
        }
    }


    @Override
    public void onBuyInformationInner(String inner_image,String title,int position) {
//        Log.i("@@@",position+"");
        if (position==1){
            turnToActivity(ActPublicMaterial.class,false);
        }else {
            Bundle bundle=new Bundle();
            bundle.putString("inner_image",inner_image);
            bundle.putString("title",title);
            turnToActivity(ActBuyInformationInner.class,bundle,false);
        }
    }

    @Override
    public void buyOfSchoolClickListener(int id,String title) {
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        bundle.putString("title",title);
        turnToActivity(ActBuyOfSchoolDetail.class,bundle,false);

    }
}
