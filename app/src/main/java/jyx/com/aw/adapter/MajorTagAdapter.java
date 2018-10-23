package jyx.com.aw.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.mvp.model.MajorRight;
import jyx.com.aw.mvp.model.Schools;

/**
 * Created by JiangYunxian on 2017/11/17 0017.
 * 功能：
 */

public class MajorTagAdapter extends TagAdapter<MajorRight.TypeBean> {

    private LayoutInflater mInflater;

    public MajorTagAdapter(Context context, List<MajorRight.TypeBean> datas) {
        super(datas);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, MajorRight.TypeBean md) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_school_tv,
                parent, false);
        if(md.isCheck()){
            tv.setBackgroundResource(R.drawable.shape_wiki_school_check);
            tv.setTextColor(Color.parseColor("#40a5f3"));
        }else{
            tv.setBackgroundResource(R.drawable.shape_wiki_school_normal);
            tv.setTextColor(Color.parseColor("#333333"));
        }
        tv.setText(md.getTypename());
        return tv;
    }
}
