package jyx.com.aw.adapter;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.impl.OnProvinceOfSchoolListener;
import jyx.com.aw.mvp.model.Schools;

/**
 * User:
 * Date:
 * Time: 15:51
 * describe:  右边适配器
 */
public class RightAdapter extends BaseQuickAdapter<Schools.SchoolsBean> {
    private OnProvinceOfSchoolListener onProvinceOfSchoolListener;
	public RightAdapter(List<Schools.SchoolsBean> data) {
		super(R.layout.item_main_right, data);
	}
	public void setProvinceOfSchoolListener(OnProvinceOfSchoolListener onProvinceOfSchoolListener){
		this.onProvinceOfSchoolListener=onProvinceOfSchoolListener;
	}
	@Override
	protected void convert(final BaseViewHolder helper, final Schools.SchoolsBean listBean) {
//		helper.setText(R.id.item_main_right_title,listBean.getType());
		 TagFlowLayout flowlayout = helper.getView(R.id.item_main_right_taglayout);
		final List<Schools.SchoolsBean.CollegesBean> colleges = listBean.getColleges();
		final SchoolTagAdapter schoolTagAdapter=new SchoolTagAdapter(mContext,colleges);
		flowlayout.setAdapter(schoolTagAdapter);
		flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
			@Override
			public boolean onTagClick(View view, int position, FlowLayout parent) {
				Schools.SchoolsBean.CollegesBean drugItemBean = colleges.get(position);
				for (Schools.SchoolsBean.CollegesBean b:
				     colleges) {
					b.setCheck(false);
				}
				schoolTagAdapter.notifyDataChanged();
//				drugItemBean.setCheck(true);
//				String id = drugItemBean.getId();
				onProvinceOfSchoolListener.onProvinceOfSchoolClickListener(drugItemBean.getId(),drugItemBean.getName());
//				Snackbar.make(helper.convertView, "点击了"+drugItemBean.getName()+" "+drugItemBean.getId(), Snackbar.LENGTH_SHORT).show();
				return false;
			}
		});



	}
}
