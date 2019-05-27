package com.cif.accumulate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liuxincai
 * @Description: the time util about project
 * @Date: 2019/5/27 20:49
 */
public class TimeUtil {

    /**
     * get the second time about now to zero point in night
     *
     * @return
     */
    public static long getNowToEndTimes() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String dateStr = sdf.format(now);
        Date startTime = now;
        try {
            startTime = sdf.parse(dateStr);
        } catch (ParseException e) {
        }
        Long expireTime = 24 * 60 * 60 - ((now.getTime() - startTime.getTime()) / 1000);
        return Integer.valueOf("" + expireTime);
    }
}
