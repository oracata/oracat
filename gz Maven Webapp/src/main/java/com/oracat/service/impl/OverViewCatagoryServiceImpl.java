package com.oracat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracat.dao.OverViewCatagoryDao;
import com.oracat.model.OverViewCatagory;
import com.oracat.service.OverViewCatagoryService;

@Service  
public class OverViewCatagoryServiceImpl implements OverViewCatagoryService {

    @Autowired  
    private OverViewCatagoryDao overViewCatagoryDao;  

    public OverViewCatagory selectOverViewCatagory() {  
        return overViewCatagoryDao.selectOverViewCatagory();
    }  
}