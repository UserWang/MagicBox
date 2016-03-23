package com.android.common.utils;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wangjundong on 2015/12/9.
 */
public class TimeUtils {

    /**
     * 毫秒转换成日期格式
     *
     * @param milliseconds
     * @return
     */
    public static String formatMillisencond(long milliseconds) {
        Date dat = new Date(milliseconds);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sb = format.format(gc.getTime());
        return sb;
    }

    /**
     * 毫秒转换成日期格式
     *
     * @param milliseconds
     * @return
     */
    public static String formatMillisencond2(long milliseconds) {
        Date dat = new Date(milliseconds);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
        String sb = format.format(gc.getTime());
        return sb;
    }
}
