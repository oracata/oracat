package com.oracat.dao;

import java.util.List;

import com.oracat.model.OverViewCatagory;

public interface OverViewCatagoryDao {
	/**
     * @return OverViewCatagory
     */
    public List<OverViewCatagory> selectOverViewCatagory(String date);
    
}
