/**
 * <p>FileName:FixedGridView.java</p>
 * <p>ProjectName:BIMPAPP</p>
 * <p>Company:嘉丽科技
 * <p>Copyright: 2013 chinamrgl.com Inc. All Right Reserved.
 * <P>Version:3.1.1
 *
 * @author Victor
 * @date 2013-12-3 下午7:33:52
 */
package jyx.com.aw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 固定的GridView
 */
public class FixedGridView extends DividerGridView {
    public FixedGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedGridView(Context context) {
        super(context);
    }

    public FixedGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
