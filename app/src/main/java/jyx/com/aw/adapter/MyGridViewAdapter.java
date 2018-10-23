package jyx.com.aw.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.GridViewItemClickListener;
import jyx.com.aw.impl.HomeCenterClickListener;
import jyx.com.aw.mvp.model.SeniorRecommendation;

/**
 * Created by JiangYunxian on 2017/11/7 0007.
 * 功能：
 */

public class MyGridViewAdapter extends BaseAdapter{
    private Context context;
    private List<SeniorRecommendation.ProductsBean> list = new ArrayList<>();
    private LayoutInflater inflater;
    private int num;
    private GridViewItemClickListener gridViewItemClickListener;
    public void setGridViewItemClickListener(GridViewItemClickListener gridViewItemClickListener) {
        this.gridViewItemClickListener = gridViewItemClickListener;
    }
    public MyGridViewAdapter(Context context,int num) {
        this.context = context;
        this.num=num;
        inflater=LayoutInflater.from(context);
    }
    //刷新数据
    public void setList(List<SeniorRecommendation.ProductsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (num==2){
            return num;
        }else {
            return list.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_senior_recommendation_grid,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (list.size()!=0){
            Glide.with(context)
                    .load(Config.BASE_HEAD_URL[1]+list.get(position).getLitpic())
                    .asBitmap()
                    .skipMemoryCache(true)
                    .into(viewHolder.mImageViewSeniorRePic);
            viewHolder.mTextViewSeniorReTitle.setText(list.get(position).getTitle());
            viewHolder.mTextViewSeniorReTruePrice.setText("￥"+list.get(position).getTrueprice());
            viewHolder.mTextViewSeniorRePrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            viewHolder.mTextViewSeniorRePrice.setText(list.get(position).getPrice()+"");
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gridViewItemClickListener!=null){
                        gridViewItemClickListener.itemGridViewClickListener(list.get(position).getAid());
                    }
                }
            });
        }
        return convertView;
    }
    static class ViewHolder{
        ImageView mImageViewSeniorRePic;
        TextView mTextViewSeniorReTitle,mTextViewSeniorReTruePrice,mTextViewSeniorRePrice;
        View itemView;
        public ViewHolder(View itemView) {
            this.itemView = itemView;
            mImageViewSeniorRePic = (ImageView) itemView.findViewById(R.id.mImageViewSeniorRePic);
            mTextViewSeniorReTitle = (TextView) itemView.findViewById(R.id.mTextViewSeniorReTitle);
            mTextViewSeniorReTruePrice = (TextView) itemView.findViewById(R.id.mTextViewSeniorReTruePrice);
            mTextViewSeniorRePrice = (TextView) itemView.findViewById(R.id.mTextViewSeniorRePrice);
        }
    }
}
