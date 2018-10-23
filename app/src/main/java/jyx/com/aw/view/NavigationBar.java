package jyx.com.aw.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import jyx.com.aw.R;


/**
 * 统一的导航栏
 */
public class NavigationBar extends FrameLayout {
    private TextView mTvLeft;
    private TextView mTvRight;
    private TextView mTvTitle;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    private ImageView mIvCenter;
    private View mLeft;
    private View mRight;
    private View mViewBottomLine;

    private int background;

    public NavigationBar(Context context) {
        this(context, null);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public NavigationBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        inflate(context, R.layout.navigation_bar, this);

        // 取自定义值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NavigationBar);
        String textLeft = ta.getString(R.styleable.NavigationBar_textLeft);
        String textRight = ta.getString(R.styleable.NavigationBar_textRight);
        String title = ta.getString(R.styleable.NavigationBar_textTitle);
        int imageLeft = ta.getResourceId(R.styleable.NavigationBar_imageLeft, 0);
        int imageRight = ta.getResourceId(R.styleable.NavigationBar_imageRight, 0);
        int imageCenter = ta.getResourceId(R.styleable.NavigationBar_imageCenter, 0);
        int textColor = ta.getColor(R.styleable.NavigationBar_textColor, 0);
        background = ta.getResourceId(R.styleable.NavigationBar_android_background, -1);

        // 初始化View
        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mTvTitle = (TextView) findViewById(R.id.tv_title_bar);
        mIvLeft = (ImageView) findViewById(R.id.iv_left);
        mIvRight = (ImageView) findViewById(R.id.iv_right);
        mIvCenter = (ImageView) findViewById(R.id.iv_center);
        mViewBottomLine = findViewById(R.id.view_bottomLine);
        mLeft = findViewById(R.id.layout_left);
        mRight = findViewById(R.id.layout_right);
        setRightEnable(false);

        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }

        if (!TextUtils.isEmpty(textLeft)) {
            mTvLeft.setText(textLeft);
        }

        if (imageLeft != 0) {
            setLeftImage(imageLeft);
        } else {
            // 设置默认返回按钮
            setLeftImage(R.drawable.back_left);
        }

        if (!TextUtils.isEmpty(textRight)) {
            setRightText(textRight);
        }

        if (imageRight != 0) {
            setRightImage(imageRight);
        }

        if (imageCenter != 0) {
            setCenterImage(imageCenter);
        }

        if (background == -1) {
            background = ta.getColor(R.styleable.NavigationBar_android_background, 0);
            if (background == 0) {
                // 使用默认bar背景，可以使用图标
                background = getResources().getColor(R.color.white);
            }
        }

        setBackgroundColor(background);

        if (textColor != 0) {
            mTvLeft.setTextColor(textColor);
            mTvRight.setTextColor(textColor);
            mTvTitle.setTextColor(textColor);
        }

        ta.recycle();
    }

    public void setTitle(int titleRes) {
        mTvTitle.setText(titleRes);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public String getTitle() {
        return mTvTitle.getText().toString();
    }


    public void hideTitleShowDown() {
        mTvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    public void setLeftImage(int resId) {
        mIvLeft.setImageResource(resId);
        mLeft.setVisibility(View.VISIBLE);
    }

    public void setLeftClick(OnClickListener listener) {
        mLeft.setOnClickListener(listener);
    }

    public void setRightText(String text) {
        mRight.setVisibility(View.VISIBLE);
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(text);
        setRightEnable(true);
    }

    public void setLeftEnable(boolean isEnable) {
        mLeft.setEnabled(isEnable);
    }

    public void setRightEnable(boolean isEnable) {
        mRight.setEnabled(isEnable);
    }

    public void setRightImage(int resId) {
        mRight.setVisibility(View.VISIBLE);

        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setImageResource(resId);
        setRightEnable(true);

        mTvRight.setVisibility(View.GONE);
    }

    public void setCenterImage(int resId) {
        mIvCenter.setImageResource(resId);
        mIvCenter.setVisibility(View.VISIBLE);
    }

    public void showRight() {
        mRight.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏右侧按钮
     */
    public void hideRight() {
        mRight.setVisibility(View.GONE);
    }

    /**
     * 隐藏右侧按钮
     */
    public void hideLeft() {
        mLeft.setVisibility(View.GONE);
    }

    public void setRightClick(OnClickListener listener) {
        mRight.setOnClickListener(listener);
    }

    /**
     * 设置标题栏背景颜色
     *
     * @param color
     */
    public void setBackColor(int color) {
        setBackgroundColor(getResources().getColor(color));
    }


    public void setTitleBarAlpha(int alpha) {
        getBackground().setAlpha(alpha);
        // 如果修改标题栏颜色值，此处的后3位参数也需要修改
        mTvTitle.setTextColor(Color.argb(alpha, 255, 255, 255));
    }

    public void setTitleColor(int color) {
        mTvTitle.setTextColor(color);
    }


    /**
     * 隐藏底部分割线
     */
    public void hideBottomLine() {
        mViewBottomLine.setVisibility(View.GONE);
    }

    /**
     * 显示底部分割线
     */
    public void showBottomLine() {
        mViewBottomLine.setVisibility(View.VISIBLE);
    }

    public int getBackgroundColor() {
        return background;
    }

}
