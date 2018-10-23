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
import jyx.com.aw.mvp.model.Schools;

/**
 * User:
 * Date:
 * Time:
 * describe:  学校标签
 */
public class SchoolTagAdapter extends TagAdapter<Schools.SchoolsBean.CollegesBean> {

	private LayoutInflater mInflater;

	public SchoolTagAdapter(Context context, List<Schools.SchoolsBean.CollegesBean> datas) {
		super(datas);
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(FlowLayout parent, int position, Schools.SchoolsBean.CollegesBean md) {
		TextView tv = (TextView) mInflater.inflate(R.layout.item_school_tv,
				parent, false);
		if(md.isCheck()){
			tv.setBackgroundResource(R.drawable.shape_wiki_school_check);
			tv.setTextColor(Color.parseColor("#40a5f3"));
		}else{
			tv.setBackgroundResource(R.drawable.shape_wiki_school_normal);
			tv.setTextColor(Color.parseColor("#333333"));
		}
		tv.setText(md.getName());
		return tv;
	}
}
