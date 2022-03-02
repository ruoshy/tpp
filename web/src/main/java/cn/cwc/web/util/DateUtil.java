package cn.cwc.web.util;

import java.util.Calendar;

public class DateUtil {

    public static String getDayForWeek(Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            default:
                return "周六";
        }
    }
}
