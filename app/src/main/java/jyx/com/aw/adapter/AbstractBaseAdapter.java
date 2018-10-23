package jyx.com.aw.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 15/3/3.
 */
public abstract class AbstractBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;

    private List<T> mDatas = new ArrayList<T>();
    private int mPageIndex = 1;

    public AbstractBaseAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public T genericClass() {
        return null;
    }

    @Override
    abstract public View getView(int position, View convertView, ViewGroup parent);

    public void putData(List<T> datas) {
        if(getPageIndex() == 1) {
            clear();
        }

        if(datas != null) {
            mDatas.addAll(datas);
        }

        notifyDataSetChanged();

    }

    public void clear() {
        clear(true);
    }

    public void clear(boolean isNotifyData) {
        mDatas.clear();
        if (isNotifyData) {
            notifyDataSetChanged();
        }
    }

    /**
     * 在list尾部添加数据
     * @param list
     */
    public void appendToList(List<T> list) {
        if (list == null) {
            return;
        }
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 在list头部增加数据
     * @param list
     */
    public void appendToTopList(List<T> list) {
        if (list == null) {
            return;
        }
        mDatas.addAll(0, list);
        notifyDataSetChanged();
    }

    /**
     * 在list尾部添加数据
     * @param t
     */
    public void appendToList(T t) {
        if (t == null) {
            return;
        }
        mDatas.add(t);
        notifyDataSetChanged();
    }

    /**
     * 在list头部增加数据
     * @param t
     */
    public void appendToTopList(T t) {
        if (t == null) {
            return;
        }
        mDatas.add(0, t);
        notifyDataSetChanged();
    }

    /**
     * 获取适配器数据列表
     * @return
     */
    public List<T> getData() {
        return mDatas;
    }

    public void remove(T t) {
        remove(t, true);
    }

    public void remove(T t, boolean isNotifyData) {
        mDatas.remove(t);

        if (isNotifyData) {
            notifyDataSetChanged();
        }
    }

    protected void turnToActivity(Class<?> clazz) {
        turnToActivity(clazz, null);
    }

    protected void turnToActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        if(bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }

    public void initPageIndex() {
        mPageIndex = 1;
    }

    /**
     * pagerIndex ++
     */
    public void morePageIndexDoublePlus() {
        this.mPageIndex ++;
    }

    /**
     * pagerIndex --
     */
    public void morePageIndexDoubleSub() {
        this.mPageIndex --;
    }

    public int getPageIndex() {
        return this.mPageIndex;
    }

}
