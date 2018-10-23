package jyx.com.aw.mvp.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.R;
import jyx.com.aw.adapter.HomeCenterAdapter;

/**
 * Created by JiangYunxian on 2017/10/9 0009.
 * 功能：
 */

public class HomeCenterItem {
    String homeItemName;
    Bitmap homeItemPic;

    public HomeCenterItem(String homeItemName, Bitmap homeItemPic) {
        this.homeItemName = homeItemName;
        this.homeItemPic = homeItemPic;
    }

    public HomeCenterItem() {
    }

    public String getHomeItemName() {
        return homeItemName;
    }

    public void setHomeItemName(String homeItemName) {
        this.homeItemName = homeItemName;
    }

    public Bitmap getHomeItemPic() {
        return homeItemPic;
    }

    public void setHomeItemPic(Bitmap homeItemPic) {
        this.homeItemPic = homeItemPic;
    }
    public static List<HomeCenterItem> data;

    public static void initView(Context context) {
        HomeCenterItem items[] = new HomeCenterItem[]{
                new HomeCenterItem("院校", BitmapFactory.decodeResource(context.getResources(),R.drawable.college)),
                new HomeCenterItem("专业", BitmapFactory.decodeResource(context.getResources(), R.drawable.major)),
                new HomeCenterItem("经验", BitmapFactory.decodeResource(context.getResources(), R.drawable.experience)),
                new HomeCenterItem("题库", BitmapFactory.decodeResource(context.getResources(), R.drawable.baike)),
                new HomeCenterItem("下真题", BitmapFactory.decodeResource(context.getResources(), R.drawable.download_exercise)),
                new HomeCenterItem("买资料", BitmapFactory.decodeResource(context.getResources(), R.drawable.data)),
                new HomeCenterItem("背单词", BitmapFactory.decodeResource(context.getResources(), R.drawable.one)),
                new HomeCenterItem("撩学长", BitmapFactory.decodeResource(context.getResources(), R.drawable.blessing)),
        };
        data = new ArrayList<>();
        for (HomeCenterItem item : items) {
            data.add(item);
        }
    }
    public static List<HomeCenterItem> provinceOfSchoolData;
    public static void initViewProvinceOfSchool(Context context) {
        HomeCenterItem items[] = new HomeCenterItem[]{
                new HomeCenterItem("研究生院", BitmapFactory.decodeResource(context.getResources(),R.drawable.school_1)),
                new HomeCenterItem("考研分数线", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_2)),
                new HomeCenterItem("专业排名", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_3)),
                new HomeCenterItem("参考节目", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_4)),
                new HomeCenterItem("研究生专业", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_5)),
                new HomeCenterItem("考研经验", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_6)),
                new HomeCenterItem("招生简章", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_7)),
                new HomeCenterItem("考研问题", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_8)),
                new HomeCenterItem("考研报录比", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_9)),
                new HomeCenterItem("考研复试", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_10)),
                new HomeCenterItem("研究生导师", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_11)),
                new HomeCenterItem("考研调剂", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_12)),
                new HomeCenterItem("联系方式", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_13)),
                new HomeCenterItem("真题资料", BitmapFactory.decodeResource(context.getResources(), R.drawable.school_14)),
        };
        provinceOfSchoolData = new ArrayList<>();
        for (HomeCenterItem item : items) {
            provinceOfSchoolData.add(item);
        }
    }
    @Override
    public String toString() {
        return "HomeCenterItem{" +
                "homeItemName='" + homeItemName + '\'' +
                ", homeItemPic=" + homeItemPic +
                '}';
    }
}

