package jyx.com.aw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import jyx.com.aw.R;
import jyx.com.aw.base.BaseFragment;
import jyx.com.aw.view.LoadMoreRecyclerView;

/**
 * Created by JiangYunxian on 2018/1/8 0008.
 * 功能：
 */
public class ListFriendsFragment extends BaseFragment{
    private static ListFriendsFragment instance=null;
    public static ListFriendsFragment newInstance() {
        if(instance==null){
            instance= new ListFriendsFragment();
        }
        return instance;
    }
    public ListFriendsFragment(){}
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContentView(R.layout.fm_list_friends);
        LoadMoreRecyclerView mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        int[]mImgList = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        String [] mTag = getActivity().getResources().getStringArray(R.array.test);
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(getActivity(), mImgList,mTag));
    }
}
