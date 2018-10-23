package jyx.com.aw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.impl.HomeCenterClickListener;
import jyx.com.aw.impl.ProvinceOfSchoolClickListener;
import jyx.com.aw.mvp.model.HomeCenterItem;

/**
 * Created by JiangYunxian on 2017/10/9 0009.
 * 功能：
 */

public class ProvinceOfSchoolAdapter extends RecyclerView.Adapter<ProvinceOfSchoolAdapter.MyViewHolder>{
    private Context context;
    private List<HomeCenterItem> data;
    private ProvinceOfSchoolClickListener provinceOfSchoolClickListener;

    public void setProvinceOfSchoolClickListener(ProvinceOfSchoolClickListener provinceOfSchoolClickListener) {
        this.provinceOfSchoolClickListener = provinceOfSchoolClickListener;
    }

    public ProvinceOfSchoolAdapter(Context context, List<HomeCenterItem> data) {
        this.context = context;
        this.data =data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.province_of_school,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mImageViewHomeCenterIcon.setImageBitmap(data.get(position).getHomeItemPic());
        holder.mTextViewHomeCenterName.setText(data.get(position).getHomeItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (provinceOfSchoolClickListener!=null){
                    provinceOfSchoolClickListener.provinceOfSchoolListener(position,data.get(position).getHomeItemName());
                }
            }
        });
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

