package jyx.com.aw.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import jyx.com.aw.R;

/**
 * Created by ytx on 2016/10/8.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener{
    private int [] mImgList;
    private String[]mTag;
    private Context context;
    private List<Integer> lists;
    private MyItemClickListener mOnItemClickListener = null;
    public MyRecyclerViewAdapter(Context context, int [] mImgList, String[]mTag) {
        this.mImgList = mImgList;
        this.mTag = mTag;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gv_active, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImg.setBackgroundResource(mImgList[position]);
        holder.mTextView.setText(mTag[position]);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mImgList.length;
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements MyItemClickListener {
        TextView mTextView;
        ImageView mImg;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_active);
            mImg = (ImageView)itemView.findViewById(R.id.img_active);
        }

        @Override
        public void onItemClick(View view, int postion) {

        }
    }

}
