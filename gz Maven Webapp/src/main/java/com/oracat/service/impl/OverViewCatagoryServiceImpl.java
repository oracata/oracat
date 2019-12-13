package com.oracat.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<Map<String,Integer>> selectYzFenlei() {

        DynamicDataSourceHolder.setDataSource("mysql");
        return overViewCatagoryDao.selectYzFenlei();
    }

    public List<Map<String,Integer>> selectJndfl() {

        DynamicDataSourceHolder.setDataSource("mysql");
        return overViewCatagoryDao.selectJndfl();
    }


}