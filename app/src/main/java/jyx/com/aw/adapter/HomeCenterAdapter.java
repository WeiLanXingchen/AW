package jyx.com.aw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.global.Config;
import jyx.com.aw.impl.HomeCenterClickListener;
import jyx.com.aw.mvp.model.HomeCenterItem;

/**
 * Created by JiangYunxian on 2017/10/9 0009.
 * 功能：
 */

public class HomeCenterAdapter extends RecyclerView.Adapter<HomeCenterAdapter.MyViewHolder>{
    private Context context;
    private List<HomeCenterItem> data;
    private HomeCenterClickListener homeCenterClickListener;

    public void setHomeCenterClickListener(HomeCenterClickListener mHomeCenterClickListener) {
        this.homeCenterClickListener = mHomeCenterClickListener;
    }

    public HomeCenterAdapter(Context context, List<HomeCenterItem> data) {
        this.context = context;
        this.data =data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.i("@@@", data.toString());
        View view=LayoutInflater.from(context).inflate(R.layout.home_center_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        Log.i("@@@", data.toString());
        holder.mImageViewHomeCenterIcon.setImageBitmap(data.get(position).getHomeItemPic());
        holder.mTextViewHomeCenterName.setText(data.get(position).getHomeItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeCenterClickListener!=null){
                    homeCenterClickListener.itemDataListener(position);
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

