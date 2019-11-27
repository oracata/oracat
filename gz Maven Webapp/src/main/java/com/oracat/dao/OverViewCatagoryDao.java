package com.oracat.dao;

import java.util.List;
import java.util.Map;

import com.oracat.model.OverViewCatagory;
import com.oracat.model.RealTime;
import org.apache.ibatis.annotations.Select;

public interface OverViewCatagoryDao {
	/**
     * @return OverViewCatagory
     */
    public List<OverViewCatagory> selectOverViewCatagory(String date);


    @Select("select fenlei1,count(*) from  yz_fl where  date in (\n" +
            "select  max(date)  from yz_fl    )\n" +
            "group by fenlei1 ")
    List<Map<String,Integer>> selectYzFenlei();


}
