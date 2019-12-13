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


    @Select("select fenlei1,count(*) num from  yz_fl where  date in (\n" +
            "select  max(date)  from yz_fl    )\n" +
            "group by fenlei1 ")
    List<Map<String,Integer>> selectYzFenlei();



    @Select("select   leibie_2 ,count(*) num from jnd_goods where date='2019-12-13' and state=1\n" +
            "group by leibie_2  ")
    List<Map<String,Integer>> selectJndfl();


}
