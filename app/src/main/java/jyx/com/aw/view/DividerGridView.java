package jyx.com.aw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import jyx.com.aw.R;


/**
 * 支持分割线的GridView
 * Created by apple on 15/9/18.
 */
public class DividerGridView extends GridView {
    private static final int DEF_DIVIDER_COLOR = 0xFFd7d7d7;

    private float mDividerVerticalSize;
    private float mDividerHorizontalSize;
    private int mDividerVerticalColor = -99;
    private int mDividerHorizontalColor = -99;
    private boolean isShowDivider;

    private Paint mDividerVerticalPaint;
    private Paint mdividerHorizontalPaint;

    public DividerGridView(Context context) {
        this(context, null);
    }

    public DividerGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DividerGridView);

        float mDividerAllSize = attributes.getDimension(R.styleable.DividerGridView_dividerAllSize, 0);

        if (mDividerAllSize > 0) {
            if (mDividerAllSize < 1) {
                mDividerAllSize = 1;
            }

            mDividerVerticalSize = mDividerAllSize;
            mDividerHorizontalSize = mDividerAllSize;
        } else {
            mDividerVerticalSize = attributes.getDimension(R.styleable.DividerGridView_dividerVerticalSize, 0);
            mDividerHorizontalSize = attributes.getDimension(R.styleable.DividerGridView_dividerHorizontalSize, 0);
        }

        if (mDividerVerticalSize > 0 || mDividerHorizontalSize > 0) {
            isShowDivider = true;
        }

        if (isShowDivider) {
            int mDividerAllColor = attributes.getColor(R.styleable.DividerGridView_dividerAllColor, -99);

            if (mDividerAllColor != -99) {
                mDividerVerticalColor = mDividerAllColor;
                mDividerHorizontalColor = mDividerAllColor;
            } else {
                mDividerVerticalColor = attributes.getColor(R.styleable.DividerGridView_dividerVerticalColor, -99);
                mDividerHorizontalColor = attributes.getColor(R.styleable.DividerGridView_dividerHorizontalColor, -99);

                if (mDividerVerticalColor == -99 && mDividerHorizontalColor == -99) {
                    mDividerVerticalColor = DEF_DIVIDER_COLOR;
                    mDividerHorizontalColor = DEF_DIVIDER_COLOR;
                } else if (mDividerVerticalColor == -99 && mDividerHorizontalColor != -99) {
                    mDividerVerticalColor = mDividerHorizontalColor;
                } else if (mDividerVerticalColor != -99 && mDividerHorizontalColor == -99) {
                    mDividerHorizontalColor = mDividerVerticalColor;
                }
            }
        }

        attributes.recycle();

        initPaint();
    }

    private void initPaint() {
        mDividerVerticalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerVerticalPaint.setStyle(Paint.Style.STROKE);
        mDividerVerticalPaint.setStrokeWidth(mDividerVerticalSize);
        mDividerVerticalPaint.setColor(mDividerVerticalColor);

        mdividerHorizontalPaint = new Paint(Paint.ANTI_ALIAS_FLAG );
        mdividerHorizontalPaint.setStyle(Paint.Style.STROKE);
        mdividerHorizontalPaint.setStrokeWidth(mDividerHorizontalSize);
        mdividerHorizontalPaint.setColor(mDividerHorizontalColor);
    }

    public void setDividerAllSize(float size) {
        if (size > 0) {
            if (size < 1) {
                size = 1;
            }

            mDividerVerticalSize = size;
            mDividerHorizontalSize = size;

            isShowDivider = true;

            if (mDividerVerticalColor == -99) {
                mDividerVerticalColor = DEF_DIVIDER_COLOR;
            }

            if (mDividerHorizontalColor == -99) {
                mDividerHorizontalColor = DEF_DIVIDER_COLOR;
            }

            mDividerVerticalPaint.setColor(mDividerVerticalColor);
            mDividerVerticalPaint.setStrokeWidth(mDividerVerticalSize);

            mdividerHorizontalPaint.setColor(mDividerHorizontalColor);
            mdividerHorizontalPaint.setStrokeWidth(mDividerHorizontalSize);
        }
    }

    public void setDividerAllColor(int color) {
        mDividerVerticalColor = color;
        mDividerHorizontalColor = color;

        mDividerVerticalPaint.setColor(mDividerVerticalColor);
        mdividerHorizontalPaint.setColor(mDividerHorizontalColor);
    }

    public void setDividerVerticalSize(float size) {
        if (size > 0) {
            if (size < 1) {
                size = 1;
            }

            mDividerVerticalSize = size;

            isShowDivider = true;

            if (mDividerVerticalColor == -99) {
                mDividerVerticalColor = DEF_DIVIDER_COLOR;
            }

            mDividerVerticalPaint.setColor(mDividerVerticalColor);
            mDividerVerticalPaint.setStrokeWidth(mDividerVerticalSize);
        }
    }

    public void setDividerVerticalColor(int color) {
        mDividerVerticalColor = color;
        mDividerVerticalPaint.setColor(mDividerVerticalColor);
    }

    public void setDividerHorizontalSize(float size) {
        if (size > 0) {
            if (size < 1) {
                size = 1;
            }

            mDividerHorizontalSize = size;

            isShowDivider = true;

            if (mDividerHorizontalColor == -99) {
                mDividerHorizontalColor = DEF_DIVIDER_COLOR;
            }

            mdividerHorizontalPaint.setColor(mDividerHorizontalColor);
            mdividerHorizontalPaint.setStrokeWidth(mDividerHorizontalSize);
        }
    }

    public void setDividerHorizontalColor(int color) {
        mDividerHorizontalColor = color;
        mdividerHorizontalPaint.setColor(mDividerHorizontalColor);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (!isShowDivider || getChildCount() == 0 || getWidth() <= 0) return ;

        // 处理分割线
        int column = getWidth() / getChildAt(0).getWidth();
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View cellView = getChildAt(i);
            if ((i + 1) % column == 0) {
                if (mDividerVerticalSize > 0) {
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), mDividerVerticalPaint);
                }
            } else if ((i + 1) > (childCount - (childCount % column))) {
                if (mDividerHorizontalSize > 0) {
                    canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), mdividerHorizontalPaint);
                }
            } else {
                if (mDividerVerticalSize > 0) {
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), mDividerVerticalPaint);
                }

                if (mDividerHorizontalSize > 0) {
                    canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), mdividerHorizontalPaint);
                }
            }
        }

//        if (childCount % column != 0) {
//            for (int j = 0; j < (column - childCount % column); j++) {
//                View lastView = getChildAt(childCount - 1);
//                canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth() * j, lastView.getBottom(), mdividerHorizontalPaint);
//            }
//        }
    }

}
