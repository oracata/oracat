package com.oracat.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;


import com.oracat.model.*;

import com.oracat.util.tag.PageModel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	//**导出excel**/
	XSSFWorkbook exportExcelInfo(B2bPrice b2bprice) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;


    /**日报**/
    List<ReportDay> findReportDay(ReportDay reportday, PageModel pageModel);
    List<String> selectShengfen();
	List<String> selectChengshi(String shengfen);
	List<String> selectQuyufl(String shengfen,String chengshi);
   /**年度报表**/
   List<ReportYear> selectReportYear(String begin_date,String end_date);
    //**导出excel**/
    XSSFWorkbook exportReportYear() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;

	/** 云中商品对应关系
	 **/
	List<GoodsForYz> selectGoodsForYz(GoodsForYz goodsforyz);
	GoodsForYz findGoodsForYzById(String jnd_spid);
	void modifyGoodsForYz(GoodsForYz goodsforyz);
	void addGoodsForYz(GoodsForYz goodsforyz);
	void removeGoodsForYzById(String jnd_spid);

}
