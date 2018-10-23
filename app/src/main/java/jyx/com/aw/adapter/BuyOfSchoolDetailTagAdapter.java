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
import jyx.com.aw.mvp.model.BuyOfSchoolDetail;
import jyx.com.aw.mvp.model.Schools;

/**
 * User:
 * Date:
 * Time:
 * describe:  学校标签
 */
public class BuyOfSchoolDetailTagAdapter extends TagAdapter<BuyOfSchoolDetail.AcademiesBean.MajorsBean> {

	private LayoutInflater mInflater;

	public BuyOfSchoolDetailTagAdapter(Context context, List<BuyOfSchoolDetail.AcademiesBean.MajorsBean> datas) {
		super(datas);
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(FlowLayout parent, int position, BuyOfSchoolDetail.AcademiesBean.MajorsBean md) {
		TextView tv = (TextView) mInflater.inflate(R.layout.item_school_of_detail_tv,
				parent, false);
		if(md.isCheck()){
			tv.setBackgroundResource(R.drawable.selector_school_of_detail_tag);
			tv.setTextColor(Color.parseColor("#40a5f3"));
		}else{
			tv.setBackgroundResource(R.drawable.selector_school_of_detail_tag);
			tv.setTextColor(Color.parseColor("#333333"));
		}
		tv.setText(md.getTitle());
		return tv;
	}
}
