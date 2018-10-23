package jyx.com.aw.act;

import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jyx.com.aw.R;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseListActivity;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.IChangeCoutCallback;
import jyx.com.aw.mvp.model.PublicMaterial;
import jyx.com.aw.rxjava.ApiCallback;
import jyx.com.aw.rxjava.SubscriberCallBack;
import jyx.com.aw.util.AppUtil;
import jyx.com.aw.view.CounterView;
import jyx.com.aw.view.CustomSwipeToRefresh;

import static jyx.com.aw.R.id.mTextViewShopCartNum;
import static jyx.com.aw.R.id.mTextViewShoppingCarTotalMoney;

/**
 * Created by JiangYunxian on 2017/12/18 0018.
 * 功能：
 */
public class ActPublicMaterial extends BaseListActivity<PublicMaterial.ProductsBean> implements AdapterView.OnItemClickListener, View.OnClickListener {
    private Map<Integer, Boolean> map = new HashMap();
    private TextView mTextViewShoppingCarTotalMoney;
    private List<PublicMaterial.ProductsBean> products;
    private CheckBox mCheckBoxShoppingCarTotal;

    @Override
    protected void initViews() {
        super.initViews();
        mBar.setTitle("公共课资料");
        initList();
        mListView.setOnItemClickListener(this);
        View buy_now = LayoutInflater.from(this).inflate(R.layout.buy_now, null);
        Button mButtonShoppingPay = (Button) buy_now.findViewById(R.id.mButtonShoppingPay);
        mButtonShoppingPay.setOnClickListener(this);
        mTextViewShoppingCarTotalMoney = (TextView) buy_now.findViewById(R.id.mTextViewShoppingCarTotalMoney);
        mCheckBoxShoppingCarTotal = (CheckBox) buy_now.findViewById(R.id.mCheckBoxShoppingCarTotal);
        addFooterView(buy_now);
    }


    private void initList() {
        addSubscription(mHttpApi2.getPublicMaterialData(Config.M_CODE), new SubscriberCallBack<>(new ApiCallback<PublicMaterial>() {
            @Override
            public void onSuccess(PublicMaterial model) {
                List<PublicMaterial.ProductsBean> products = model.getProducts();
                if (products != null && products.size() > 0) {
//                    totalMoney(products);
                    for (int i = 0; i < products.size(); i++) {
                        map.put(i, false);
                        products.get(i).setNum(1);
                    }
                    mAdapter.putData(products);
                    setLoadMoreEnabled(false);
                    setRefreshEnabled(false);
                    dismissLoadingView();
                } else {
                    if (mAdapter.getCount() > 0) {
                        mAdapter.clear();
                    }
                    AppUtil.showToast("未知错误！");
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

    private void totalMoney() {
        double totalMoney = 0;
        for (int i = 0; i < products.size(); i++) {
            if (map != null) {
                if (map.get(i) == true) {
                    PublicMaterial.ProductsBean productsBean = products.get(i);
                    //BigDecimal解决失精度(ps:double必须转成String)
                    BigDecimal b1 = new BigDecimal(Double.toString(totalMoney));
                    BigDecimal b2 = new BigDecimal(Double.toString(productsBean.getTrueprice())).
                            multiply(new BigDecimal(Double.toString(productsBean.getNum())));//单价mul数量
                    totalMoney = b1.add(b2).doubleValue();
                }
            }
        }
        mTextViewShoppingCarTotalMoney.setText("合计：￥" + totalMoney);
    }

    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {

    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this).inflate(R.layout.shopcart_item_test, parent, false);
        }
        TextView mTextViewShopCartDescription = ViewHolder.get(convertView, R.id.mTextViewShopCartDescription);
        CheckBox mCheckBoxShopCart = ViewHolder.get(convertView, R.id.mCheckBoxShopCart);
        TextView mTextViewShopCarNowtPrice = ViewHolder.get(convertView, R.id.mTextViewShopCarNowtPrice);
        TextView mTextViewShopCartOriginalPrice = ViewHolder.get(convertView, R.id.mTextViewShopCartOriginalPrice);
        TextView mTextViewShopCartDesc = ViewHolder.get(convertView, R.id.mTextViewShopCartDesc);
        TextView mTextViewShopCartNum = ViewHolder.get(convertView, R.id.mTextViewShopCartNum);
        TextView mTextViewShopCartAsc = ViewHolder.get(convertView, R.id.mTextViewShopCartAsc);
        CounterView mCounterView = ViewHolder.get(convertView, R.id.mCounterView);
        LinearLayout mLinearLayoutHeader = ViewHolder.get(convertView, R.id.mLinearLayoutHeader);
        View mViewLine = ViewHolder.get(convertView, R.id.mViewLine);
        View mViewHeader = ViewHolder.get(convertView, R.id.mViewHeader);
        final PublicMaterial.ProductsBean item = mAdapter.getItem(position);
        if (item != null) {
            if (position==0){
                mLinearLayoutHeader.setVisibility(View.VISIBLE);
                mViewLine.setVisibility(View.VISIBLE);
                mViewHeader.setVisibility(View.VISIBLE);

            }else {
                mLinearLayoutHeader.setVisibility(View.GONE);
                mViewLine.setVisibility(View.GONE);
                mViewHeader.setVisibility(View.GONE);
            }
            products = mAdapter.getData();
            setListener(mCheckBoxShopCart, mTextViewShopCartDesc, mTextViewShopCartAsc, position, products, mCounterView);
//            totalMoney();
            //修改TextView部分文字的颜色
            ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
            SpannableStringBuilder nowtPrice = new SpannableStringBuilder(String.valueOf("￥" + item.getTrueprice()));
            nowtPrice.setSpan(redSpan, 0, nowtPrice.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextViewShopCarNowtPrice.setText(new SpannableStringBuilder("本月促销价").append(nowtPrice));
            mTextViewShopCartDescription.setText(item.getTitle());
            mTextViewShopCartOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
            mTextViewShopCartOriginalPrice.setText("原价" + item.getPrice());
//            mTextViewShopCartNum.setText(item.getNum()+"");
        }
        return convertView;
    }
/*
    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this).inflate(R.layout.shopcart_item, parent, false);
        }
        TextView mTextViewShopCartDescription = ViewHolder.get(convertView, R.id.mTextViewShopCartDescription);
        CheckBox mCheckBoxShopCart = ViewHolder.get(convertView, R.id.mCheckBoxShopCart);
        TextView mTextViewShopCarNowtPrice = ViewHolder.get(convertView, R.id.mTextViewShopCarNowtPrice);
        TextView mTextViewShopCartOriginalPrice = ViewHolder.get(convertView, R.id.mTextViewShopCartOriginalPrice);
        TextView mTextViewShopCartDesc = ViewHolder.get(convertView, R.id.mTextViewShopCartDesc);
        TextView mTextViewShopCartNum = ViewHolder.get(convertView, R.id.mTextViewShopCartNum);
        TextView mTextViewShopCartAsc = ViewHolder.get(convertView, R.id.mTextViewShopCartAsc);
        PublicMaterial.ProductsBean item = mAdapter.getItem(position);
        products=mAdapter.getData();
        setListener(mCheckBoxShopCart,mTextViewShopCartDesc,mTextViewShopCartAsc,position,products);
        if (item != null) {
//            totalMoney();
            //修改TextView部分文字的颜色
            ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
            SpannableStringBuilder nowtPrice = new SpannableStringBuilder(String.valueOf("￥" + item.getTrueprice()));
            nowtPrice.setSpan(redSpan, 0, nowtPrice.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextViewShopCarNowtPrice.setText(new SpannableStringBuilder("本月促销价 ").append(nowtPrice));
            mTextViewShopCartDescription.setText(item.getTitle());
            mTextViewShopCartOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
            mTextViewShopCartOriginalPrice.setText("原价" + item.getPrice());
            mTextViewShopCartNum.setText(item.getNum()+"");
        }
        return convertView;
    }
*/

    private void setListener(final CheckBox mCheckBoxShopCart,
                             TextView mTextViewShopCartDesc,
                             TextView mTextViewShopCartAsc,
                             final int position,
                             final List<PublicMaterial.ProductsBean> products,
                             CounterView mCounterView) {
        mCounterView.setCallback(new IChangeCoutCallback() {
            @Override
            public void change(int count) {
                products.get(position).setNum(count);
                mAdapter.notifyDataSetChanged();
                totalMoney();
            }
        });
        mCheckBoxShopCart.setChecked(map.get(position));
        mCheckBoxShopCart.setTag(position);
        mCheckBoxShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemChecked(mCheckBoxShopCart, position);
                totalMoney();
            }
        });
//        mTextViewShopCartDesc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int num = products.get(position).getNum();
//                if (num > 1) {
//                    num--;
//                    products.get(position).setNum(num);
//                    mAdapter.notifyDataSetChanged();
//                }
//                totalMoney();
//            }
//        });
//        mTextViewShopCartAsc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int num = products.get(position).getNum();
//                num++;
//                products.get(position).setNum(num);
//                mAdapter.notifyDataSetChanged();
//                totalMoney();
//            }
//        });
        mCheckBoxShoppingCarTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < products.size(); i++) {
                    map.put(i, mCheckBoxShoppingCarTotal.isChecked());
                }
                //设置点击发生变化刷新
                mAdapter.notifyDataSetChanged();
                totalMoney();

            }
        });

    }

    private void ItemChecked(CheckBox checkBox, int position) {
        int trueCount = 0;
        if (checkBox.isChecked()) {

            //选中，
            if (checkBox.getTag() != null
                    && checkBox.getTag().equals(position)) {
                map.put(position, true);
            }
            //统计true的个数
            for (int i = 0; i < map.size(); i++) {
                boolean flag = map.get(i);
                if (flag == true) {
                    trueCount++;
                }
            }
            if (trueCount == products.size()) {
                //表示全选按钮要选中
                mCheckBoxShoppingCarTotal.setChecked(true);
        }
        } else {
            //不选中，
            if (checkBox.getTag() != null
                    && checkBox.getTag().equals(position)) {
                map.put(position, false);
            }
            //表示全选按钮要取消
            mCheckBoxShoppingCarTotal.setChecked(false);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
