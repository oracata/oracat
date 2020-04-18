package com.oracat.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


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
	List<RealTime> selectArea();
	List<RealTime> selectActiveLogin(String begin_date,String end_date);
	List<RealTime> selectActiveArea();
	List<Order> selectOrder(String begin_date,String end_date);
	List<Order> selectOrderTop10(String begin_date,String end_date);
    List<Order> selectOrderMs(String begin_date,String end_date);
	List<Order> selectOrderLine(String begin_date,String end_date);
	List<Sp> selectSp(String begin_date,String end_date);
	List<Sp> selectSpMs(String begin_date,String end_date);
    List<Sp> selectSpMsTop10(String begin_date,String end_date);
	List<Spml> selectSpml();
	List<Spml> selectSpmlMiss();
	List<Spml> selectSpmlStock();
	List<Spml> selectSpmlNoStock();
	List<Spml> selectXnSpml();
    List<Cust> selectCustAdd(String begin_date,String end_date);
	List<Cust> selectCustAddOrder(String begin_date,String end_date);
	List<Flow> selectFlow(String begin_date,String end_date);
	List<Search> selectSearchTop5(String begin_date,String end_date);
	List<Search> selectSearchBingo(String begin_date,String end_date);
	List<Search> selectSearchno(String begin_date,String end_date);




	List<SaleFlow> selectSaleFlow();
	List<SaleFlow> selectSaleFlowErp();

	/** 电商与erp价格对比
	**/
    List<B2bPrice> selectB2bPrice(B2bPrice b2bprice);
	//**导出excel**/
	XSSFWorkbook exportExcelInfo(B2bPrice b2bprice) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;
	List<B2bPrice> selectPriceNotin();

    /**日报**/
    List<ReportDay> findReportDay(ReportDay reportday, PageModel pageModel);
    List<String> selectShengfen();
	List<String> selectChengshi(String shengfen);
	List<String> selectQuyufl(String shengfen,String chengshi);

	/**月报表**/
	List<ReportMonth> selectReportMonth(String begin_date,String end_date);
	XSSFWorkbook exportReportMonth(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;
   /**年度报表**/
   List<ReportYear> selectReportYear(String begin_date,String end_date);

	/**top10cust**/
	List<Top10Cust> selectTop10Cust(String begin_date,String end_date);
	XSSFWorkbook exportTop10cust(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;
	/**top10goods**/
	List<Top10Cust> selectTop10Goods(String begin_date,String end_date);
	XSSFWorkbook exportTop10goods(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;

	/**coupon**/
	List<Top10Cust> selectCoupon(String begin_date,String end_date);
	XSSFWorkbook exportCoupon(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;
    //**导出excel**/
    XSSFWorkbook exportReportYear() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;

	/** 云中商品对应关系
	 **/
	List<GoodsForYz> selectGoodsForYz(GoodsForYz goodsforyz);
	GoodsForYz findGoodsForYzById(String jnd_spid);
	void modifyGoodsForYz(GoodsForYz goodsforyz);
	void addGoodsForYz(GoodsForYz goodsforyz);
	void removeGoodsForYzById(String jnd_spid);
    List<Map<String,Map<String,Map<String,String>>>>  findGoodsForYzNotin(String date);
    List<Map<String,Map<String,Map<String,String>>>>  findGoodsForYzId(String date,String jnd_spname,String manufacturer);
    List<String>  findGoodsForYzName(String date,String goods_id);
    int removeGoodsForYzId(String jnd_spid);

    /**价格对比**/
	List<PricePare> findPricePare(PricePare pricepare,PageModel pageModel)  ;

	/**无电商资料终端开单客户**/
	List<ErpCustom> selectErpCustom(String begin_date ,String end_date );
	List<ErpCustom> selectFgsCustom( );
	int modifyErpCustom(ErpCustom erpcustom );




}
