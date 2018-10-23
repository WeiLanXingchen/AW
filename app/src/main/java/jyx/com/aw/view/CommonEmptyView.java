package jyx.com.aw.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jyx.com.aw.R;
import jyx.com.aw.util.AppUtil;


/**
 * 统一EmptyView
 * Created by wg on 15/4/27.
 */
public class CommonEmptyView extends FrameLayout {

    @Bind(R.id.iv_emptyTipsImg)
    ImageView mTvEmptyTipsImg;
    @Bind(R.id.tv_emptyTipsText)
    TextView mTvEmptyTipsText;
    @Bind(R.id.ll_container)
    LinearLayout mLlContainer;

    public CommonEmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public CommonEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CommonEmptyView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.common_empty_view, this);
        ButterKnife.bind(this);
    }

    public void setEmptyViewHeight(final int height) {
        mLlContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLlContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                int curHeight = mLlContainer.getMeasuredHeight();

                if (curHeight > height) {
                    mTvEmptyTipsImg.setVisibility(View.GONE);
                } else {
                    mTvEmptyTipsImg.setVisibility(View.VISIBLE);
                }

                ListView.LayoutParams layoutParams = new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
                CommonEmptyView.this.setLayoutParams(layoutParams);
            }
        });
    }

    public void setTipsText(String txt) {
        mTvEmptyTipsText.setText(txt);
    }

    public void setTipsText(int resID) {
        mTvEmptyTipsText.setText(getResources().getString(resID));
    }

    public void setTipsEmptyImg(int resID) {
        mTvEmptyTipsImg.setImageResource(resID);
    }

    public void setTipsEmptyImg(int resID, float dipW, float dipH) {
        mTvEmptyTipsImg.setImageResource(resID);
        mTvEmptyTipsImg.getLayoutParams().width = AppUtil.dip2px(getContext(), dipW);
        mTvEmptyTipsImg.getLayoutParams().height = AppUtil.dip2px(getContext(), dipH);
    }

    /**
     * 设置空数据时显示内容
     *
     * @param emptyTypeEnum
     */
    public void setTipsEmptyType(EmptyTypeEnum emptyTypeEnum) {
        setTipsEmptyType(emptyTypeEnum, null);
    }

    /**
     * 设置空数据时显示内容
     *
     * @param emptyTypeEnum
     * @param tipsText      提示文字
     */
    public void setTipsEmptyType(EmptyTypeEnum emptyTypeEnum, String tipsText) {
        switch (emptyTypeEnum) {
            case PL_RECORD:
                // 评论记录
                setTipsEmptyImg(R.drawable.data_empty, 50.5f, 54.5f);
                if (!TextUtils.isEmpty(tipsText)) {
                    setTipsText(tipsText);
                } else {
                    setTipsText("还没有人评论,来吃第一个螃蟹吧~");
                }
                break;
        }
    }

    public void reShowTipsEmptyImg() {
        mTvEmptyTipsImg.setVisibility(View.VISIBLE);
    }

    public enum EmptyTypeEnum {
        /**
         * 默认
         */
        DEF,
        /**
         * 评论记录列表
         */
        PL_RECORD,
    }
}
