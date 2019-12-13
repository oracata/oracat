package com.oracat.service;

import java.util.List;
import java.util.Map;

import com.oracat.model.OverViewCatagory;;

public interface OverViewCatagoryService {
	List<OverViewCatagory> selectOverViewCatagory();
	List<Map<String,Integer>> selectYzFenlei();
	List<Map<String,Integer>> selectJndfl();
}