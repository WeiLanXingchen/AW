package jyx.com.aw.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.LinkedHashMap;
import java.util.Map;

import jyx.com.aw.R;
import jyx.com.aw.util.AppUtil;


/**
 * 自定义底部显示的操作按钮Dialog
 * Created by wg on 15/9/23.
 */
public class CommonBottomButtonDialog extends Dialog {
	private Context context;
	private Map<String, View.OnClickListener> buttonMap;
	private LinearLayout containerView;
	private int ButtonHeight = 40;
	private int textSize = 14;

	public CommonBottomButtonDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.context = context;
		buttonMap = new LinkedHashMap<>();
		
		initRootView();
	}
	
	private void initRootView() {
		if (null == containerView) {
			containerView = new LinearLayout(context);
			containerView.setBackgroundColor(getContext().getResources().getColor(R.color.background));
			containerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			containerView.setOrientation(LinearLayout.VERTICAL);
			setContentView(containerView);
			
			// 显示在底部
			getWindow().setGravity(Gravity.BOTTOM);
			
			// 设置Dialog宽度全屏
			WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
			Display d = windowManager.getDefaultDisplay();
			DisplayMetrics dm = new DisplayMetrics();
			d.getMetrics(dm);
			WindowManager.LayoutParams lp = getWindow().getAttributes();
			// 设置宽度
			lp.width = dm.widthPixels;
			getWindow().setAttributes(lp);
			// 点击窗口以外的地方关闭
			setCanceledOnTouchOutside(true);
			// 设置背景透明，否则不能全屏
			getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			// 弹出动画
			getWindow().setWindowAnimations(R.style.BottomAnimation);
		}
	}
	
	public void addButton(String btnText, View.OnClickListener listener) {
		if (TextUtils.isEmpty(btnText) || null == listener) {
			return ;
		}
		
		buttonMap.put(btnText, listener);
	}

	public void setButtonHeight(int buttonHeight) {
		ButtonHeight = buttonHeight;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	@Override
	public void show() {
		if (containerView.getChildCount() > 0) {
			containerView.removeAllViews();
		}

		int mBtnHeight = AppUtil.dip2px(getContext(), ButtonHeight);
		int mMarginTop = AppUtil.dip2px(getContext(), 5);
		boolean isFirst = true;
		for (Map.Entry<String, View.OnClickListener> btn : buttonMap.entrySet()) {
			Button newBtn = new Button(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mBtnHeight);
			if (!isFirst) {
				params.setMargins(0, mMarginTop, 0, 0);
			} else {
				isFirst = false;
			}
			newBtn.setLayoutParams(params);
			newBtn.setBackgroundResource(R.drawable.sel_common_bg_white);
			newBtn.setGravity(Gravity.CENTER);
			newBtn.setTextColor(0xFF333333);
			newBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
			newBtn.setText(btn.getKey());
			newBtn.setOnClickListener(btn.getValue());
			containerView.addView(newBtn);
		}

		// 添加取消按钮
		Button cancelBtn = new Button(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mBtnHeight);
		params.setMargins(0, mMarginTop, 0, 0);
		cancelBtn.setBackgroundResource(R.drawable.sel_common_bg_white);
		cancelBtn.setLayoutParams(params);
		cancelBtn.setGravity(Gravity.CENTER);
		cancelBtn.setTextColor(0xFF3591f5);
		cancelBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
		cancelBtn.setText("取消");
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		containerView.addView(cancelBtn);

		super.show();
	}

}
