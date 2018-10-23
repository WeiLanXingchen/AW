package jyx.com.aw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import jyx.com.aw.R;
import jyx.com.aw.impl.IChangeCoutCallback;


/**
 * 购物车，计数
 */
public class CounterView extends LinearLayout implements View.OnClickListener, TextWatcher {
    /**
     * 最大的数量
     **/
    public static final int MAX_VALUE = 100;

    /**
     * 最小的数量
     **/
    public static final int MIN_VALUE = 1;

    private int countValue = 1;//数量

    private TextView ivAdd, ivMinu;

    private EditText etCount;

    private IChangeCoutCallback callback;

    private int maxValue = MAX_VALUE;


    public void setCallback(IChangeCoutCallback c) {
        this.callback = c;
    }

    private Context mContext;

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context, attrs);
    }

    /**
     * 功能描述：设置最大数量
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/3 18:33
     * 参数：
     */
    public void setMaxValue(int max) {
        this.maxValue = max;
    }


    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.my_counter_size);

        int maxValue = a.getInt(R.styleable.my_counter_size_count_max, 100);

        setMaxValue(maxValue);

        LayoutInflater.from(mContext).inflate(R.layout.model_count_view, this);

        ivMinu = (TextView) findViewById(R.id.mTextViewShopCartDesc);
        ivMinu.setOnClickListener(this);

        ivAdd = (TextView) findViewById(R.id.mTextViewShopCartAsc);
        ivAdd.setOnClickListener(this);

        etCount = (EditText) findViewById(R.id.mEditTextShopCartNum);
        etCount.addTextChangedListener(this);
        a.recycle();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTextViewShopCartAsc:
                addAction();
                break;
            case R.id.mTextViewShopCartDesc:
                minuAction();
                break;


        }
    }

    /**
     * 添加操
     */
    private void addAction() {
        countValue++;
        btnChangeWord();
    }


    /**
     * 删除操作
     */
    private void minuAction() {
        countValue--;
        btnChangeWord();
    }

    /**
     * 功能描述：
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/12 10:29
     * 参数：boolean 是否需要重新赋值
     */
    private void changeWord(boolean needUpdate) {
        if (needUpdate) {
            etCount.removeTextChangedListener(this);
            if (!TextUtils.isEmpty(etCount.getText().toString().trim())) {  //不为空的时候才需要赋值
                etCount.setText(String.valueOf(countValue));
            }
            etCount.addTextChangedListener(this);
        }
        etCount.setSelection(etCount.getText().toString().trim().length());
        callback.change(countValue);
    }

    private void btnChangeWord() {
        ivMinu.setEnabled(countValue > MIN_VALUE);
        ivAdd.setEnabled(countValue < maxValue);
        etCount.setText(String.valueOf(countValue));
        etCount.setSelection(etCount.getText().toString().trim().length());
        callback.change(countValue);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
/*方法二：推荐，速度最快
  * 判断是否为整数
  * @param str 传入的字符串
  * @return 是整数返回true,否则返回false
*/

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");//正则
        return pattern.matcher(str).matches();
    }
    @Override
    public void afterTextChanged(Editable s) {
        boolean needUpdate = false;
        if (!TextUtils.isEmpty(s)) {
            if (isInteger(s.toString())){
                countValue = Integer.valueOf(s.toString());
                if (countValue <= MIN_VALUE) {
                    countValue = MIN_VALUE;
                    ivMinu.setEnabled(false);
                    ivAdd.setEnabled(true);
                    needUpdate = true;
                    Toast.makeText(mContext, String.format("最少添加%s个数量", MIN_VALUE), Toast.LENGTH_SHORT).show();
                } else if (countValue >= maxValue) {
                    countValue = maxValue;
                    ivMinu.setEnabled(true);
                    ivAdd.setEnabled(false);
                    needUpdate = true;
                    Toast.makeText(mContext, String.format("最多只能添加%s个数量", maxValue), Toast.LENGTH_SHORT).show();

                } else {
                    ivMinu.setEnabled(true);
                    ivAdd.setEnabled(true);
                }

            }else {
                Toast.makeText(mContext, "只能输入数字", Toast.LENGTH_SHORT).show();
                countValue = 1;
                ivMinu.setEnabled(false);
                ivAdd.setEnabled(true);
                needUpdate = true;
            }
        } else {  //如果编辑框被清空了，直接填1
            countValue = 1;
            ivMinu.setEnabled(false);
            ivAdd.setEnabled(true);
            needUpdate = true;
            Toast.makeText(mContext, String.format("最少添加%s个数量", MIN_VALUE), Toast.LENGTH_SHORT).show();

        }
        changeWord(needUpdate);
    }
}


