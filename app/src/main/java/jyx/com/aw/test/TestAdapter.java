package jyx.com.aw.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import jyx.com.aw.R;
import jyx.com.aw.adapter.AbstractBaseAdapter;
import jyx.com.aw.adapter.ViewHolder;

/**
 * Created by hy on 2017/12/26.
 * <p>
 * 功能：
 * <p>
 * 注意事项：
 */

public class TestAdapter extends AbstractBaseAdapter<CourseBean> {

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.course_item,null);
        }
        TextView mTvName= ViewHolder.get(convertView,R.id.tv_course_name);
        TextView mTvOld=ViewHolder.get(convertView, R.id.tv_old_price);
        TextView mTvNew=ViewHolder.get(convertView,R.id.tv_new_price);
        CourseBean bean=getItem(position);
        if (bean!=null){
            mTvName.setText(bean.getCourseName());
            mTvOld.setText(bean.getOldPrice());
            mTvNew.setText(bean.getNewPrice());
        }
        return convertView;
    }
}
