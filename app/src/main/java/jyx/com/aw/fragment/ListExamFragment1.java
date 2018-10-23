package jyx.com.aw.fragment;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.ViewHolder;
import jyx.com.aw.base.BaseFixedListFragment;
import jyx.com.aw.mvp.model.QuestionBank;
import jyx.com.aw.view.CustomSwipeToRefresh;

/**
 * Created by JiangYunxian on 2018/1/8 0008.
 * 功能：
 */
public class ListExamFragment1 extends BaseFixedListFragment<QuestionBank> {
    private List<QuestionBank> examPaperTitle=new ArrayList<>();
    private QuestionBank questionBank;
    private String title;
//    private static ListExamFragment1 instance=null;
//    public static ListExamFragment1 newInstance() {
//        if(instance==null){
//            instance= new ListExamFragment1();
//        }
//        return instance;
//    }
//    public ListExamFragment1(){
//
//    }
    @Override
    protected void initViews() {
        super.initViews();
        initList();
    }

    private void initList() {
        for (int i =12; i>0; i--) {
            questionBank=new QuestionBank();
            questionBank.setName(String.valueOf(2005+i)+"年"+title+"真题");
            questionBank.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_with_backline));
            examPaperTitle.add(questionBank);
        }
        mAdapter.putData(examPaperTitle);
        setLoadMoreEnabled(false);
        setRefreshEnabled(false);
        dismissLoadingView();
    }
    public void initData(String title) {
        this.title=title;
    }
    @Override
    protected void onRefreshBegin(CustomSwipeToRefresh customSwipeToRefresh) {

    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.politics, parent, false);
        }
        TextView mTextViewPolitics = ViewHolder.get(convertView, R.id.mTextViewPolitics);
        ImageView mImageViewPolitics = ViewHolder.get(convertView, R.id.mImageViewPolitics);
        QuestionBank item = mAdapter.getItem(position);
        if (item!=null){
            mTextViewPolitics.setText(item.getName());
            mImageViewPolitics.setImageBitmap(item.getIcon());
        }
        return convertView;
    }
}
