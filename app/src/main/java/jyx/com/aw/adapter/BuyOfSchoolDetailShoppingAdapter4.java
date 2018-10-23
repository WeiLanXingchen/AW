package jyx.com.aw.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import jyx.com.aw.R;
import jyx.com.aw.mvp.model.BuyOfSchoolDetailShopping;
import jyx.com.aw.view.CounterView;

/**
 * Created by JiangYunxian on 2017/12/27 0027.
 * 功能：
 */
public class BuyOfSchoolDetailShoppingAdapter4 extends AbstractBaseAdapter<BuyOfSchoolDetailShopping.VideoBean>{

    public BuyOfSchoolDetailShoppingAdapter4(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shopcart_item_test, parent, false);
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
        final BuyOfSchoolDetailShopping.VideoBean item = getItem(position);
        if (item != null) {
            if (position==0){
                mLinearLayoutHeader.setVisibility(View.VISIBLE);
                mViewLine.setVisibility(View.VISIBLE);
            }else {
                mLinearLayoutHeader.setVisibility(View.GONE);
                mViewLine.setVisibility(View.GONE);
            }
//            products = mAdapter.getData();
//            setListener(mCheckBoxShopCart, mTextViewShopCartDesc, mTextViewShopCartAsc, position, products, mCounterView);
//            totalMoney();
            //修改TextView部分文字的颜色
            ForegroundColorSpan redSpan = new ForegroundColorSpan(mContext.getResources().getColor(R.color.red));
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
//    private void setListener(final CheckBox mCheckBoxShopCart,
//                             TextView mTextViewShopCartDesc,
//                             TextView mTextViewShopCartAsc,
//                             final int position,
//                             final List<PublicMaterial.ProductsBean> products,
//                             CounterView mCounterView) {
//        mCounterView.setCallback(new IChangeCoutCallback() {
//            @Override
//            public void change(int count) {
//                products.get(position).setNum(count);
//                mAdapter.notifyDataSetChanged();
//                totalMoney();
//            }
//        });
//        mCheckBoxShopCart.setChecked(map.get(position));
//        mCheckBoxShopCart.setTag(position);
//        mCheckBoxShopCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ItemChecked(mCheckBoxShopCart, position);
//                totalMoney();
//            }
//        });
////        mTextViewShopCartDesc.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                int num = products.get(position).getNum();
////                if (num > 1) {
////                    num--;
////                    products.get(position).setNum(num);
////                    mAdapter.notifyDataSetChanged();
////                }
////                totalMoney();
////            }
////        });
////        mTextViewShopCartAsc.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                int num = products.get(position).getNum();
////                num++;
////                products.get(position).setNum(num);
////                mAdapter.notifyDataSetChanged();
////                totalMoney();
////            }
////        });
//        mCheckBoxShoppingCarTotal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0; i < products.size(); i++) {
//                    map.put(i, mCheckBoxShoppingCarTotal.isChecked());
//                }
//                //设置点击发生变化刷新
//                mAdapter.notifyDataSetChanged();
//                totalMoney();
//
//            }
//        });
//
//    }
//    private void totalMoney() {
//        double totalMoney = 0;
//        for (int i = 0; i < products.size(); i++) {
//            if (map != null) {
//                if (map.get(i) == true) {
//                    PublicMaterial.ProductsBean productsBean = products.get(i);
//                    //BigDecimal解决失精度(ps:double必须转成String)
//                    BigDecimal b1 = new BigDecimal(Double.toString(totalMoney));
//                    BigDecimal b2 = new BigDecimal(Double.toString(productsBean.getTrueprice())).
//                            multiply(new BigDecimal(Double.toString(productsBean.getNum())));//单价mul数量
//                    totalMoney = b1.add(b2).doubleValue();
//                }
//            }
//        }
//        mTextViewShoppingCarTotalMoney.setText("合计：￥" + totalMoney);
//    }
//    private void ItemChecked(CheckBox checkBox, int position) {
//        int trueCount = 0;
//        if (checkBox.isChecked()) {
//
//            //选中，
//            if (checkBox.getTag() != null
//                    && checkBox.getTag().equals(position)) {
//                map.put(position, true);
//            }
//            //统计true的个数
//            for (int i = 0; i < map.size(); i++) {
//                boolean flag = map.get(i);
//                if (flag == true) {
//                    trueCount++;
//                }
//            }
//            if (trueCount == products.size()) {
//                //表示全选按钮要选中
//                mCheckBoxShoppingCarTotal.setChecked(true);
//            }
//        } else {
//            //不选中，
//            if (checkBox.getTag() != null
//                    && checkBox.getTag().equals(position)) {
//                map.put(position, false);
//            }
//            //表示全选按钮要取消
//            mCheckBoxShoppingCarTotal.setChecked(false);
//        }
//    }
}
