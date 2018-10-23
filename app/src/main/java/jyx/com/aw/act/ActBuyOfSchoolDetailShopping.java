package jyx.com.aw.act;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.BuyOfSchoolDetailShoppingAdapter1;
import jyx.com.aw.adapter.BuyOfSchoolDetailShoppingAdapter2;
import jyx.com.aw.adapter.BuyOfSchoolDetailShoppingAdapter3;
import jyx.com.aw.adapter.BuyOfSchoolDetailShoppingAdapter4;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseListActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.mvp.model.BuyOfSchoolDetailShopping;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CustomSwipeToRefresh;
import jyx.com.aw.view.FixedListView;

/**
 * Created by JiangYunxian on 2017/12/22 0022.
 * 功能：
 */
public class ActBuyOfSchoolDetailShopping extends BaseListActivity<BuyOfSchoolDetailShopping>{


     @Override
    protected void initViews() {
        super.initViews();
        String title = getIntent().getStringExtra("title");
        int id = getIntent().getIntExtra("id", 0);
        if (id!=0&&title!=null){
            mBar.setTitle(title);
            initList(id);
//            Log.i("@@@",title+" "+id);
        }

    }

    private void initList(int id) {
        final List<BuyOfSchoolDetailShopping> datas=new ArrayList<>();
        addSubscription(mHttpApi2.getBuyOfSchoolDetailShoppingData(id, Config.M_CODE),new SubscriberCallBack<BuyOfSchoolDetailShopping>(new ApiCallback<BuyOfSchoolDetailShopping>() {
            @Override
            public void onSuccess(BuyOfSchoolDetailShopping model) {
                if (model!=null){
//                    Log.i("@@@",model.getVideo().get(0).getTitle());
                    setLoadMoreEnabled(false);
                    setRefreshEnabled(false);
                    datas.add(model);
                    mAdapter.putData(datas);
                    dismissLoadingView();
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
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {

    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(this).inflate(R.layout.test_item,null);
        }
        TextView mTvType= ViewHolder.get(convertView,R.id.tv_type_name);
        FixedListView mCourseList=ViewHolder.get(convertView,R.id.list_course);
//        TestAdapter testAdapter=new TestAdapter(this);
        BuyOfSchoolDetailShoppingAdapter1 adapter1=new BuyOfSchoolDetailShoppingAdapter1(this);
        BuyOfSchoolDetailShoppingAdapter2 adapter2=new BuyOfSchoolDetailShoppingAdapter2(this);
        BuyOfSchoolDetailShoppingAdapter3 adapter3=new BuyOfSchoolDetailShoppingAdapter3(this);
        BuyOfSchoolDetailShoppingAdapter4 adapter4=new BuyOfSchoolDetailShoppingAdapter4(this);
        mCourseList.setAdapter(adapter1);
//        mCourseList.setAdapter(adapter2);
//        mCourseList.setAdapter(adapter3);
//        mCourseList.setAdapter(adapter4);
        BuyOfSchoolDetailShopping item = mAdapter.getItem(position);
        if (item!=null){
            mTvType.setText(item.getCode()+"");
            adapter1.putData(item.getGeneral());
            adapter2.putData(item.getOto());
            adapter3.putData(item.getProducts());
            adapter4.putData(item.getVideo());
        }
        return convertView;
    }
}
