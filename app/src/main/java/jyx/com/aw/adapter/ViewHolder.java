package jyx.com.aw.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by Jerry on 15/3/3.
 * 比较实用的一个ViewHolder
 */
public class ViewHolder {

    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if(viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if(childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
