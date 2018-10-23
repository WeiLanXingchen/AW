package jyx.com.aw.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.mvp.model.MajorLeft;
import jyx.com.aw.mvp.model.Schools;

/**
 * Created by JiangYunxian on 2017/11/17 0017.
 * 功能：
 */

public class MajorLeftAdapter extends BaseQuickAdapter<MajorLeft.MajorsBean> {
    private int selectPos=0;
    public MajorLeftAdapter( List<MajorLeft.MajorsBean> data) {
        super(R.layout.item_main_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MajorLeft.MajorsBean bean) {
        if(selectPos==helper.getAdapterPosition()){
            helper.setVisible(R.id.item_main_left_bg,true);
            helper.convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#40a5f3"));
        }else{
            helper.convertView.setBackgroundColor(Color.parseColor("#f7f7f7"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#333333"));
            helper.setVisible(R.id.item_main_left_bg,false);
        }

        helper.setText(R.id.item_main_left_type,bean.getTypename());

    }


    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }
}
