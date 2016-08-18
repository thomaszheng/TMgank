package com.thomas.tmgank.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by thomas on 2015/10/11.
 */
public class TimeKit {

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    public static List<Integer> getTime() {
        Calendar calendar = Calendar.getInstance();
        List<Integer> list= new ArrayList<Integer>();
        list.add(calendar.get(Calendar.YEAR));
        list.add(calendar.get(Calendar.MONTH));
        list.add(calendar.get(Calendar.DAY_OF_MONTH));
        return list;
    }

    public static String format(Date data){

        return  simpleDateFormat.format(data);
    }

    public static String toDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }
    public static String toDate(Date date, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, add);
        return toDate(calendar.getTime());
    }


    public static Date getLastdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
