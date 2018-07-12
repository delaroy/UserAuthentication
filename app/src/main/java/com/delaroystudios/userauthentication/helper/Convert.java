package com.delaroystudios.userauthentication.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by delaroy on 7/11/18.
 */

public class Convert {
    public static String convertToTwentyfourHrs(String time) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm aa");
        Date date = dateFormat.parse(time);
        String convertedTime = new SimpleDateFormat("HH:mm").format(date);

        return convertedTime;
    }

    public static String convertToTwelveHrs(String time) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = dateFormat.parse(time);
        String convertedTime = new SimpleDateFormat("h:mm aa").format(date);

        return convertedTime;
    }


}
