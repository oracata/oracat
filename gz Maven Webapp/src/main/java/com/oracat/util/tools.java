package com.oracat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class tools {
    public static String getTimeDay( int index){
    	Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,  index);
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    String date = fmt.format(cal.getTime());
    return date;
    	}
}
