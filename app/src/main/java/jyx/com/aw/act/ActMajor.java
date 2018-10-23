package jyx.com.aw.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.LeftAdapter;
import jyx.com.aw.adapter.MajorLeftAdapter;
import jyx.com.aw.adapter.MajorRightAdapter;
import jyx.com.aw.adapter.RightAdapter;
import jyx.com.aw.base.BaseActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.ActivityJump;
import jyx.com.aw.mvp.model.MajorLeft;
import jyx.com.aw.mvp.model.MajorListBean;
import jyx.com.aw.mvp.model.MajorRight;
import jyx.com.aw.mvp.model.Schools;
import jyx.com.aw.retrofit.HttpApi;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JiangYunxian on 2017/11/15 0015.
 * 功能：
 */

public class ActMajor extends BaseActivity implements ActivityJump {
    private RecyclerView mLeftRvRecyclerView;
    private RecyclerView mRightRvRecyclerView;

    private List<MajorLeft.MajorsBean>  majorsBeanList = new ArrayList<>();

    private MajorLeftAdapter leftAdapter;
    private MajorRightAdapter rightAdapter;
    private List<MajorListBean> listBeanList = new ArrayList<>();
    private int id=0;
    MajorLeft.MajorsBean majorLeft = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_school);
        dismissLoadingView();
        String title = getIntent().getExtras().getString("title");
        mBar.setTitle(title);
        mLeftRvRecyclerView = (RecyclerView) findViewById(R.id.main_left_rv);
        mRightRvRecyclerView = (RecyclerView) findViewById(R.id.main_right_rv);
        initData();
        if (majorsBeanList != null) {
            leftAdapter = new MajorLeftAdapter(majorsBeanList);
        }
        if (listBeanList != null) {
            rightAdapter = new MajorRightAdapter(listBeanList);
        }
        mLeftRvRecyclerView.setAdapter(leftAdapter);
        mRightRvRecyclerView.setAdapter(rightAdapter);
        mLeftRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRightRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightAdapter.setJumpActClickListener(this);
        mLeftRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MajorLeft.MajorsBean majorsBean = majorsBeanList.get(i);
                if (majorsBean!=null){
                    listBeanList.clear();
                    listBeanList.addAll(majorsBean.getmList());
                    leftAdapter.setSelectPos(i);
                    leftAdapter.notifyDataSetChanged();
                    rightAdapter.notifyDataSetChanged();

                }
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

    private void initData() {
        addSubscription(mHttpApi1.getMajorData(Config.M_CODE), new SubscriberCallBack<MajorLeft>(new ApiCallback<MajorLeft>() {
            @Override
            public void onSuccess(MajorLeft model) {
                if (model != null) {
                    for (int i = 0; i <model.getMajors().size(); i++) {
                        String typename = model.getMajors().get(i).getTypename();
                        majorLeft = new MajorLeft.MajorsBean();
                        majorLeft.setTypename(typename);
                        majorLeft.setId(model.getMajors().get(i).getId());
                        majorsBeanList.add(majorLeft);
                        id = model.getMajors().get(i).getId();
                        if (id!=0&&majorLeft!=null){
                            getMajorType(id,majorLeft);
                            leftAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onCompleted() {

            }
        }));
    }

    private void getMajorType(int id, final MajorLeft.MajorsBean majorLeft) {
        final List<MajorListBean> li1=new ArrayList<>();
        final MajorListBean l1=new MajorListBean();
        final List<MajorRight.TypeBean> list1=new ArrayList<>();
        addSubscription(mHttpApi1.getMajorTypeData(id,Config.M_CODE,"-1"), new SubscriberCallBack<MajorRight>(new ApiCallback<MajorRight>() {
            @Override
            public void onSuccess(MajorRight model) {
                if (model != null) {
                    for (int j = 0; j <model.getType().size() ; j++) {
                        MajorRight.TypeBean majorRight=new MajorRight.TypeBean();
                        majorRight.setTypename(model.getType().get(j).getTypename());
                        majorRight.setId(model.getType().get(j).getId());
                        list1.add(majorRight);
                        l1.setmList(list1);

                    }
                    li1.add(l1);
                    majorLeft.setmList(li1);
                    listBeanList.clear();
                    listBeanList.addAll(majorsBeanList.get(0).getmList());
                    rightAdapter.notifyDataSetChanged();
                }
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
    public void jumpActivity(int id, String title) {
//        Log.i("@@@",id+" "+title);
        Bundle bundle = new Bundle();
        bundle.putInt("typeId",id);
        bundle.putString("title",title);
        turnToActivity(ActExperience.class,bundle,false);
    }
}
