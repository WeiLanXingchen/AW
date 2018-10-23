package jyx.com.aw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.impl.ProvinceOfSchoolClickListener;
import jyx.com.aw.mvp.model.BuyInformation;
import jyx.com.aw.mvp.model.HomeCenterItem;

/**
 * Created by JiangYunxian on 2017/10/9 0009.
 * 功能：
 */

public class BuyInformationAdapter extends RecyclerView.Adapter<BuyInformationAdapter.MyViewHolder>{
    private Context context;
    private List<BuyInformation.SchoolsBean> data;
    private ProvinceOfSchoolClickListener provinceOfSchoolClickListener;

    public void setProvinceOfSchoolClickListener(ProvinceOfSchoolClickListener provinceOfSchoolClickListener) {
        this.provinceOfSchoolClickListener = provinceOfSchoolClickListener;
    }

    public BuyInformationAdapter(Context context, List<BuyInformation.SchoolsBean> data) {
        this.context = context;
        this.data =data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.buy_information,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(data.get(position).getLitpic())
                .skipMemoryCache(true)
                .placeholder(R.mipmap.ic_launcher)
                .into( holder.mImageViewHomeCenterIcon);
//        holder.mImageViewHomeCenterIcon.setImageBitmap();
        holder.mTextViewHomeCenterName.setText(data.get(position).getName());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (provinceOfSchoolClickListener!=null){
//                    provinceOfSchoolClickListener.provinceOfSchoolListener(position,data.get(position).getHomeItemName());
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewHomeCenterName;
        ImageView mImageViewHomeCenterIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageViewHomeCenterIcon= (ImageView) itemView.findViewById(R.id.mImageViewHomeCenterIcon);
            mTextViewHomeCenterName= (TextView) itemView.findViewById(R.id.mTextViewHomeCenterName);
        }
    }
}

