package com.hktstudio.bongda24h.util;
/**
 * Created by Hai on 27/02/2018.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.res.Resources;

import com.hktstudio.bongda24h.R;

public class UtilTime {
    public static final String TIME_AGO_PREFIX="";
    public static final String TIME_AGO_SUFFIX="trước";
    public static final String TIME_AGO_NOW="Vài giây";
    public static final String TIME_AGO_SECOND="giây";
    public static final String TIME_AGO_MINUTE="phút";
    public static final String TIME_AGO_HOUR="giờ";


    
    public static String timeAgo(long millis) {
        long diff = new Date().getTime() - millis;


        String prefix = TIME_AGO_PREFIX;
        String suffix = TIME_AGO_SUFFIX;

        double seconds = Math.abs(diff) / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;

        String words="";

        if (seconds < 45) {
            words =TIME_AGO_NOW;
        } else if (seconds < 90) {
            words = 1+" "+TIME_AGO_MINUTE;
        } else if (minutes < 45) {
            words = Math.round(minutes)+" "+TIME_AGO_MINUTE ;
        } else if (minutes < 90) {
            words = 1+" "+TIME_AGO_HOUR;
        } else if (hours < 24) {
            words = Math.round(hours)+" "+TIME_AGO_HOUR ;
        } else  {
            return getDateFormat(millis);
        }

        StringBuilder sb = new StringBuilder();

        if (prefix != null && prefix.length() > 0) {
            sb.append(prefix).append(" ");
        }

        sb.append(words);

        if (suffix != null && suffix.length() > 0) {
            sb.append(" ").append(suffix);
        }

        return sb.toString().trim();
    }
    public static String getDateFormat(long milisecond){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(milisecond);
        return dateFormat.format(date);
    }
}