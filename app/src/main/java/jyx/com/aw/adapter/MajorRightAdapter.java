package jyx.com.aw.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.act.ActExperience;
import jyx.com.aw.act.ActExperienceWebView1;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.ActivityJump;
import jyx.com.aw.mvp.model.MajorLeft;
import jyx.com.aw.mvp.model.MajorListBean;
import jyx.com.aw.mvp.model.MajorRight;
import jyx.com.aw.mvp.model.Schools;
import jyx.com.aw.retrofit.HttpApi;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JiangYunxian on 2017/11/17 0017.
 * 功能：
 */

public class MajorRightAdapter extends BaseQuickAdapter<MajorListBean> {
    private ActivityJump activityJump;
    public void setJumpActClickListener(ActivityJump activityJump){
        this.activityJump=activityJump;
    }
    public MajorRightAdapter(List<MajorListBean> data) {
        super(R.layout.item_main_right, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MajorListBean listBean) {
//		helper.setText(R.id.item_main_right_title,listBean.getType());
        TagFlowLayout flowlayout = helper.getView(R.id.item_main_right_taglayout);
        final List<MajorRight.TypeBean> typeBeen = listBean.getmList();
        final MajorTagAdapter majorTagAdapter = new MajorTagAdapter(mContext, typeBeen);
        flowlayout.setAdapter(majorTagAdapter);
        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                MajorRight.TypeBean typeBean = typeBeen.get(position);
                for (MajorRight.TypeBean b:
                        typeBeen) {
                    b.setCheck(false);
                }
//                typeBean.setCheck(true);

                activityJump.jumpActivity(typeBean.getId(),typeBean.getTypename());
                majorTagAdapter.notifyDataChanged();
//                Snackbar.make(helper.convertView, "点击了"+typeBean.getTypename()+" "+typeBeen.get(position).getId(), Snackbar.LENGTH_SHORT).show();
                return false;
            }
        });

    }


}
