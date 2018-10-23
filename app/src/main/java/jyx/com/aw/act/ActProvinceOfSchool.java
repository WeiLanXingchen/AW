package jyx.com.aw.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import jyx.com.aw.R;
import jyx.com.aw.adapter.ProvinceOfSchoolAdapter;
import jyx.com.aw.base.BaseRefreshActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.HomeCenterClickListener;
import jyx.com.aw.impl.ProvinceOfSchoolClickListener;
import jyx.com.aw.mvp.model.ProvinceOfSchool;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;

import static jyx.com.aw.mvp.model.HomeCenterItem.initViewProvinceOfSchool;
import static jyx.com.aw.mvp.model.HomeCenterItem.provinceOfSchoolData;

/**
 * Created by JiangYunxian on 2017/12/12 0012.
 * 功能：
 */

public class ActProvinceOfSchool extends BaseRefreshActivity implements ProvinceOfSchoolClickListener {
//    @Bind(R.id.mImageViewProvinceOfSchool)
    protected ImageView  mImageViewProvinceOfSchool;
    @Bind(R.id.mRecyclerViewProvinceOfSchool)
    protected RecyclerView mRecyclerViewProvinceOfSchool;
    private ProvinceOfSchoolAdapter adapter;
    private RecyclerViewHeader header;
    private ProvinceOfSchool provinceOfSchool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_province_of_school);
        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        mBar.setTitle(title);
        initViewProvinceOfSchool(this);
        if (provinceOfSchoolData != null) {
            adapter = new ProvinceOfSchoolAdapter(this, provinceOfSchoolData);
            //初始化RecyclerViewHeader
            header = RecyclerViewHeader.fromXml(this, R.layout.province_of_school_header);
            mImageViewProvinceOfSchool= (ImageView) header.findViewById(R.id.mImageViewProvinceOfSchool);
            initData(id);
            mRecyclerViewProvinceOfSchool.setLayoutManager(new GridLayoutManager(this, 3));
            //把RecyclerViewHeader赋予RecyclerView,这个一定要写在setLayoutManager之后
            header.attachTo(mRecyclerViewProvinceOfSchool);
            mRecyclerViewProvinceOfSchool.setAdapter(adapter);
            adapter.setProvinceOfSchoolClickListener(this);

        }
    }

    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {

    }

    private void initData(String id) {
        addSubscription(mHttpApi1.getProvinceOfSchoolData(id,Config.M_CODE), new SubscriberCallBack<>(new ApiCallback<ProvinceOfSchool>() {
            @Override
            public void onSuccess(ProvinceOfSchool model) {
                Glide.with(ActProvinceOfSchool.this)
                        .load(model.getSchool().getThumb())
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImageViewProvinceOfSchool);
                provinceOfSchool=model;
                dismissLoadingView();
                final String[] split = provinceOfSchool.getSchool().getIntro().split("[/]");
                mImageViewProvinceOfSchool.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Log.i("@@@",split[5]);
                        Bundle bundle=new Bundle();
                        bundle.putInt("ID",Integer.valueOf(split[5]).intValue());
                        turnToActivity(ActExperienceWebView1.class,bundle,false);
                    }
                });
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
    public void provinceOfSchoolListener(int position,String name) {
        if (provinceOfSchool!=null){
            for (int i = 0; i <provinceOfSchool.getType().size() ; i++) {
                if (provinceOfSchool.getType().get(i).getTypename().equals(name)){
//                    Log.i("@@@",position+" "+name+" "+provinceOfSchool.getType().get(i).getTypename()
//                            +" "+provinceOfSchool.getType().get(i).getId());
                    Bundle bundle=new Bundle();
                    bundle.putString("title",name);
                    bundle.putInt("typeId",provinceOfSchool.getType().get(i).getId());
                    turnToActivity(ActExperience.class,bundle,false);
                }
            }
        }
    }
}
