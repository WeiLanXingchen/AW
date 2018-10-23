package jyx.com.aw.impl;


import jyx.com.aw.view.CommonEmptyView;

/**
 * Created by apple on 15/6/15.
 */
public interface EmptyDataTag {

    boolean isShowEmptyView();

    CommonEmptyView.EmptyTypeEnum getEmptyTypeEnum();

}
