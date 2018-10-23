package jyx.com.aw.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import jyx.com.aw.R;
import jyx.com.aw.adapter.BuyOfSchoolDetailLeftAdapter;
import jyx.com.aw.adapter.BuyOfSchoolDetailRightAdapter;
import jyx.com.aw.adapter.LeftAdapter;
import jyx.com.aw.adapter.RightAdapter;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.OnBuyOfSchoolDetailListener;
import jyx.com.aw.mvp.model.BuyOfSchoolDetail;
import jyx.com.aw.mvp.model.BuyOfSchoolDetailHeader;
import jyx.com.aw.mvp.model.Schools;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;

/**
 * Created by JiangYunxian on 2017/12/21 0021.
 * 功能：
 */
public class ActBuyOfSchoolDetail extends BaseActivity implements OnBuyOfSchoolDetailListener{
    @Bind(R.id.mTextViewBuyOfSchoolDetail)
    TextView mTextViewBuyOfSchoolDetail;
    private RecyclerView mLeftRvRecyclerView;
    private RecyclerView mRightRvRecyclerView;
    private BuyOfSchoolDetailLeftAdapter leftAdapter;

    private BuyOfSchoolDetailRightAdapter rightAdapter;
    private List<BuyOfSchoolDetail.AcademiesBean> buyOfSchoolDetailList=new ArrayList<>();
    private List<BuyOfSchoolDetail.AcademiesBean> listBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_buy_of_school_detail);
        int id = getIntent().getIntExtra("id", 0);
        String title = getIntent().getStringExtra("title");
        if (title!=null&&id!=0){
//            Log.i("@@@",id+" "+title);
            mBar.setTitle(title);
            initHeader(id);
            initData(id);
            mLeftRvRecyclerView = (RecyclerView) findViewById(R.id.main_left_rv);
            mRightRvRecyclerView = (RecyclerView) findViewById(R.id.main_right_rv);
            if (buyOfSchoolDetailList!=null){
                leftAdapter=new BuyOfSchoolDetailLeftAdapter(buyOfSchoolDetailList);
            }
            if (listBeanList!=null){
                rightAdapter=new BuyOfSchoolDetailRightAdapter(listBeanList);
            }
            mLeftRvRecyclerView.setAdapter(leftAdapter);
            mRightRvRecyclerView.setAdapter(rightAdapter);

            mLeftRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRightRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            rightAdapter.setBuyOfSchoolDetailListener(this);
            mLeftRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    BuyOfSchoolDetail.AcademiesBean academiesBean = buyOfSchoolDetailList.get(i);
                    listBeanList.clear();
                    listBeanList.add(academiesBean);
                    leftAdapter.setSelectPos(i);
                    leftAdapter.notifyDataSetChanged();
                    rightAdapter.notifyDataSetChanged();
                }

                @Override
                public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                }

                @Override
                public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                }
            });
        }
    }

    private void initHeader(int id) {
        addSubscription(mHttpApi2.getBuyOfSchoolDetailHeaderData(id),new SubscriberCallBack<>(new ApiCallback<BuyOfSchoolDetailHeader>() {
            @Override
            public void onSuccess(BuyOfSchoolDetailHeader model) {
                if (model!=null){
                    mTextViewBuyOfSchoolDetail.setText(model.getText());
                }
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

    private void initData(int id) {
        addSubscription(mHttpApi2.getBuyOfSchoolDetailData(id,Config.M_CODE),new SubscriberCallBack<>(new ApiCallback<BuyOfSchoolDetail>() {
            @Override
            public void onSuccess(BuyOfSchoolDetail model) {
                if (model!=null){
                    for (int i = 0; i <model.getAcademies().size() ; i++) {
                        String typeName = model.getAcademies().get(i).getTypename();
                        BuyOfSchoolDetail.AcademiesBean d1=new BuyOfSchoolDetail.AcademiesBean();
                        d1.setTypename(typeName);
                        buyOfSchoolDetailList.add(d1);
                        List<BuyOfSchoolDetail.AcademiesBean.MajorsBean> majors = model.getAcademies().get(i).getMajors();
                        d1.setMajors(majors);
                    }
                    listBeanList.add(buyOfSchoolDetailList.get(0));
                    leftAdapter.notifyDataSetChanged();
                    rightAdapter.notifyDataSetChanged();

                }
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

    @Override
    public void onBuyOfSchoolDetailClickListener(int id, String title) {
//        Log.i("@@@",id+" "+title);
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        bundle.putInt("id",id);
        turnToActivity(ActBuyOfSchoolDetailShopping.class,bundle,false);
    }
}
