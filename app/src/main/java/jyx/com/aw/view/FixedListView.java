/**
 * <p>FileName:ForbidScrollListView.java</p>
 * <p>ProjectName:BIMPAPP</p>
 * <p>Company:嘉丽科技
 * <p>Copyright: 2013 chinamrgl.com Inc. All Right Reserved.
 * <P>Version:3.1.1
 * 
 * @author ZY
 * @date 2013-11-7 下午8:01:25
 */
package jyx.com.aw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 固定的ListView
 */
public class FixedListView extends ListView {

	public FixedListView(Context context) {
		super(context);
	}

	public FixedListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FixedListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
