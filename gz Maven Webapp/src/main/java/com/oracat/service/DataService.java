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

	
	List<Goods> findYzGoods(Goods goods,PageModel pageModel)  ;


	/**实时数据
	 * **
	 * @return
	 */

	List<RealTime> selectRealTime();

	/** 电商与erp价格对比
	**/
    List<B2bPrice> selectB2bPrice(B2bPrice b2bprice);


    List<ReportDay> findReportDay(ReportDay reportday, PageModel pageModel);
}
