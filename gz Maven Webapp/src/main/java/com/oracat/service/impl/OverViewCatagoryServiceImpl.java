package com.oracat.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.oracat.util.DynamicDataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracat.dao.OverViewCatagoryDao;
import com.oracat.model.OverViewCatagory;
import com.oracat.service.OverViewCatagoryService;

@Service  
public class OverViewCatagoryServiceImpl implements OverViewCatagoryService {

    @Autowired  
    private OverViewCatagoryDao overViewCatagoryDao;  

    public List<OverViewCatagory> selectOverViewCatagory() {  
       System.out.println(getTimeDay(-1));
        DynamicDataSourceHolder.setDataSource("mysql");
        return overViewCatagoryDao.selectOverViewCatagory(getTimeDay(-1));
    }
    
    
    public static String getTimeDay( int index){
    	Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,  index);
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    String date = fmt.format(cal.getTime());
    return date;
    	}
    
}