package jyx.com.aw.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.impl.OnBuyOfSchoolDetailListener;
import jyx.com.aw.impl.OnProvinceOfSchoolListener;
import jyx.com.aw.mvp.model.BuyOfSchoolDetail;
import jyx.com.aw.mvp.model.Schools;

/**
 * User:
 * Date:
 * Time: 15:51
 * describe:  右边适配器
 */
public class BuyOfSchoolDetailRightAdapter extends BaseQuickAdapter<BuyOfSchoolDetail.AcademiesBean> {
    private OnBuyOfSchoolDetailListener onBuyOfSchoolDetailListener;
	public BuyOfSchoolDetailRightAdapter(List<BuyOfSchoolDetail.AcademiesBean> data) {
		super(R.layout.item_main_right, data);
	}
	public void setBuyOfSchoolDetailListener(OnBuyOfSchoolDetailListener onBuyOfSchoolDetailListener){
		this.onBuyOfSchoolDetailListener=onBuyOfSchoolDetailListener;
	}
	@Override
	protected void convert(final BaseViewHolder helper, final BuyOfSchoolDetail.AcademiesBean listBean) {
//		helper.setText(R.id.item_main_right_title,listBean.getType());
		 TagFlowLayout flowlayout = helper.getView(R.id.item_main_right_taglayout);
		final List<BuyOfSchoolDetail.AcademiesBean.MajorsBean> majorsBeen = listBean.getMajors();
		final BuyOfSchoolDetailTagAdapter buyOfSchoolDetailTagAdapter=new BuyOfSchoolDetailTagAdapter(mContext,majorsBeen);
		flowlayout.setAdapter(buyOfSchoolDetailTagAdapter);
		flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
			@Override
			public boolean onTagClick(View view, int position, FlowLayout parent) {
				BuyOfSchoolDetail.AcademiesBean.MajorsBean majorsBean = majorsBeen.get(position);
				for (BuyOfSchoolDetail.AcademiesBean.MajorsBean b:
				     majorsBeen) {
					b.setCheck(false);
				}
				buyOfSchoolDetailTagAdapter.notifyDataChanged();
//				drugItemBean.setCheck(true);
//				String id = drugItemBean.getId();
				onBuyOfSchoolDetailListener.onBuyOfSchoolDetailClickListener(majorsBean.getId(),majorsBean.getTitle());
//				Snackbar.make(helper.convertView, "点击了"+drugItemBean.getName()+" "+drugItemBean.getId(), Snackbar.LENGTH_SHORT).show();
				return false;
			}
		});



	}
}
