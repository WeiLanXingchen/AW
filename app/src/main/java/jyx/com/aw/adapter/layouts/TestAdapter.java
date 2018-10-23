//package jyx.com.aw.adapter.layouts;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import java.util.List;
//
//import jyx.com.aw.R;
//
///**
// * 这是一个关于ListView多布局的测试，我在这里简单的写一个demo
// * 主要是对 getViewTypeCount() getItemViewType()的重写
// *
// * 以后我们要重写的话， 修改的地方是 布局类型(包括 layout  viewholder VIEW_TYPE )
// */
//
///**
// * Created by ${ZhangYan}[Squery] on 2015/10/20.
// */
//public class TestAdapter extends CommonAdapter<String> {
//
//    public TestAdapter(Context context, List mDatas) {
//        super(context, mDatas);
//    }
//
//    final int VIEW_TYPE = 2;
//    final int TYPE_1 = 0;
//    final int TYPE_2 = 1;
//
//    @Override
//    public int getViewTypeCount() {
//        return VIEW_TYPE;
//    }
//
//    /**
//     * Get the type of View that will be created by getView for the specified item.
//     * 每次调用 getView 都会调用这个方法来判断View的布局
//     *
//     * @param position
//     * @return
//     */
//    @Override
//    public int getItemViewType(int position) {
//        int ret = 0;
//        if (position == 0) {
//            ret = TYPE_1;
//        } else {
//            ret = TYPE_2;
//        }
//        return ret;
//    }
//
//    /**
//     * 这个方法的流程是这样的 1.刚开始ConverView 必定是null
//     * 我们就开始实例化ViewHolder，设置tag给ConverView
//     * 2.这个时候 我们要把每个控件进行赋值
//     * 3.等到convertview 不为空的时候  我们仍然要给控件赋值
//     * 综上所属必须把  赋值的代码块 独立出来
//     *
//     * @param position
//     * @param convertView
//     * @param parent
//     * @return
//     */
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        int type = getItemViewType(position);
//        ViewHolder viewHolder = new ViewHolder();
//        ViewHolder viewHolder1 = new ViewHolder();
//        // 在这里必须用2个convertview 可以用1个ViewHolder 因为布局比较简单且有重复的，
//
//        if (convertView == null) {
//            switch (type) {
//                case TYPE_1:
//                    convertView = mInflater.inflate(R.layout.shopcart_item_test, parent, false);
//                    viewHolder.imageView = (ImageView) convertView.findViewById(R.id.mIam);
//                    viewHolder.textView = (TextView) convertView.findViewById(R.id.mTextViewShopCartDescription);
//                    convertView.setTag(viewHolder);
//                    break;
//                case TYPE_2:
//                    convertView = mInflater.inflate(R.layout.list_item, parent, false);
//                    convertView.findViewById(R.id.img_item).setVisibility(View.GONE);
//                    viewHolder1.textView = (TextView) convertView.findViewById(R.id.txt_item);
//                    convertView.setTag(viewHolder1);
//                    break;
//            }
//        } else {
//            switch (type) {
//                case TYPE_1:
//                    viewHolder = (ViewHolder) convertView.getTag();
//                    break;
//                case TYPE_2:
//                    viewHolder1 = (ViewHolder) convertView.getTag();
//                    break;
//            }
//        }
//
//        // 给控件进行赋值
//        switch (type) {
//            case TYPE_1:
//                viewHolder.textView.setText(mList.get(position));
//                viewHolder.imageView.setBackgroundResource(R.mipmap.ic_launcher);
//                break;
//            case TYPE_2:
//                viewHolder1.textView.setText(mList.get(position));
//                break;
//        }
//        return convertView;
//    }
//
//    private class ViewHolder {
//        public TextView textView;
//        public ImageView imageView;
//    }
//
//
//}
