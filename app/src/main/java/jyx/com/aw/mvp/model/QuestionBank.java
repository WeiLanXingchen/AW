package jyx.com.aw.mvp.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by JiangYunxian on 2017/12/13 0013.
 * 功能：
 */
public class QuestionBank implements Serializable{
    private String name;
    private Bitmap icon;

    public QuestionBank() {
    }

    public QuestionBank(String name, Bitmap icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
