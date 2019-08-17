package com.oracat.service;

import java.util.List;


import com.oracat.model.*;

import com.oracat.util.tag.PageModel;

public interface DataService {
	/**
	 * 获得所有部门，分页查询
	 * @return Dept对象的List集合
	 * */
	List<Goods> findDcGoods(Goods goods,PageModel pageModel)  ;
	
	/**
	 * 获得所有部门
	 * @return Dept对象的List集合
	 * */
	List<Goods> findAllDcGoods(String date);
	
	Goods findDcGoodById(Integer goods_id);
}
