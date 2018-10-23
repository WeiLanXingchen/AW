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
import jyx.com.aw.impl.OnProvinceOfSchoolListener;
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

public class ActSchool extends BaseActivity implements OnProvinceOfSchoolListener{

    private RecyclerView mLeftRvRecyclerView;
    private RecyclerView mRightRvRecyclerView;

    private LeftAdapter leftAdapter;

    private RightAdapter rightAdapter;
    private List<Schools.SchoolsBean> schoolsBeanList=new ArrayList<>();
    private List<Schools.SchoolsBean> listBeanList=new ArrayList<>();
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
        if (schoolsBeanList!=null){
            leftAdapter=new LeftAdapter(schoolsBeanList);
        }
        if (listBeanList!=null){
            rightAdapter=new RightAdapter(listBeanList);
        }
        mLeftRvRecyclerView.setAdapter(leftAdapter);
        mRightRvRecyclerView.setAdapter(rightAdapter);

        mLeftRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRightRvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rightAdapter.setProvinceOfSchoolListener(this);
        mLeftRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Schools.SchoolsBean schoolsBean = schoolsBeanList.get(i);
                listBeanList.clear();
                listBeanList.add(schoolsBean);
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

    /**
     * 初始化数据
     */
    private void initData() {
        addSubscription(mHttpApi1.getSchoolsData(Config.M_CODE),new SubscriberCallBack<>(new ApiCallback<Schools>() {
            @Override
            public void onSuccess(Schools model) {
                if (model!=null){
                    for (int i = 0; i <model.getSchools().size() ; i++) {
//                        Log.i("@@@",model.toString());
                        String province = model.getSchools().get(i).getProvince();
                        Schools.SchoolsBean d1=new Schools.SchoolsBean();
                        d1.setProvince(province);
                        schoolsBeanList.add(d1);
                        List<Schools.SchoolsBean.CollegesBean> colleges = model.getSchools().get(i).getColleges();
                        d1.setColleges(colleges);
                    }
                    listBeanList.add(schoolsBeanList.get(0));
                    leftAdapter.notifyDataSetChanged();
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
    public void onProvinceOfSchoolClickListener(String id,String title) {
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        bundle.putString("title",title);
        turnToActivity(ActProvinceOfSchool.class,bundle,false);
    }
}