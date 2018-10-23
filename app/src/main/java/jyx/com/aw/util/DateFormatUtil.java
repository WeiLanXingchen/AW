package jyx.com.aw.util;

import java.text.SimpleDateFormat;

/**
 * Created by JiangYunxian on 2017/11/6 0006.
 * 功能：
 */

public class DateFormatUtil {
    /**
     * 时间戳转日期
     * @param ms
     * @return
     */
    public static String transForDate(Integer ms){
        String str = "";
        if(ms!=null){
            long msl=(long)ms*1000;
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

            if(ms!=null){
                try {
                    str=sdf.format(msl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }
}
