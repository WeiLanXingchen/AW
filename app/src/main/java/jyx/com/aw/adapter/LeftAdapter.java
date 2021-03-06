package jyx.com.aw.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.mvp.model.Schools;

/**
 * User:
 * Date:
 * Time: 15:19
 * describe:  左侧适配器
 */
public class LeftAdapter extends BaseQuickAdapter<Schools.SchoolsBean> {
	private int selectPos=0;
	public LeftAdapter( List<Schools.SchoolsBean> data) {
		super(R.layout.item_main_left, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, Schools.SchoolsBean bean) {
		if(selectPos==helper.getAdapterPosition()){
			helper.setVisible(R.id.item_main_left_bg,true);
			helper.convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
			helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#40a5f3"));
		}else{
			helper.convertView.setBackgroundColor(Color.parseColor("#f7f7f7"));
			helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#333333"));
			helper.setVisible(R.id.item_main_left_bg,false);
		}

		helper.setText(R.id.item_main_left_type,bean.getProvince());

	}


	public int getSelectPos() {
		return selectPos;
	}

	public void setSelectPos(int selectPos) {
		this.selectPos = selectPos;
	}
}
