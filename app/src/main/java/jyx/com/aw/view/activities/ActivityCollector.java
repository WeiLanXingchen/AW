package jyx.com.aw.view.activities;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import jyx.com.aw.act.ActBuyInformation;
import jyx.com.aw.act.ActExperience;
import jyx.com.aw.act.ActMajor;
import jyx.com.aw.act.ActQuestionBank;
import jyx.com.aw.act.ActSchool;

/**
 * Created by JiangYunxian on 2017/11/15 0015.
 * 功能：
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();
    public static List<Activity> addActivity(){
        activities.add(new ActSchool());
        activities.add(new ActMajor());
        activities.add(new ActExperience());
        activities.add(new ActQuestionBank());
        activities.add(new ActSchool());
        activities.add(new ActBuyInformation());
        activities.add(new ActSchool());
        activities.add(new ActSchool());
        return activities;
    }

}
