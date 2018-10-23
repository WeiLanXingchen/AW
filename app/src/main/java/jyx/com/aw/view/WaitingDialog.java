package jyx.com.aw.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jyx.com.aw.R;

public class WaitingDialog extends Dialog {
    @Bind(R.id.tv_waiting)
    TextView mTvMsg;

    public WaitingDialog(Activity context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initViews(context);
    }

    public void initViews(Activity context) {
        setContentView(R.layout.dialog_wating);
        ButterKnife.bind(this);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCanceledOnTouchOutside(false);
    }

    public void setMessage(String msg) {
        mTvMsg.setText(msg);
    }

}
