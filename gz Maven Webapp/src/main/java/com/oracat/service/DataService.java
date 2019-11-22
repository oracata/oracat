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
	 * ������в��ţ���ҳ��ѯ
	 * @return Dept�����List����
	 * */
	List<Goods> findDcGoods(Goods goods,PageModel pageModel)  ;
	
	/**
	 * ������в���
	 * @return Dept�����List����
	 * */
	List<Goods> findAllDcGoods(String date);
	
	Goods findDcGoodById(Integer goods_id);

	
	List<Goods> findYzGoods(Goods goods,PageModel pageModel)  ;


	/**ʵʱ����
	 * **
	 * @return
	 */

	List<RealTime> selectRealTime();

	/** ������erp�۸�Ա�
	**/
    List<B2bPrice> selectB2bPrice(B2bPrice b2bprice);
	//**����excel**/
	XSSFWorkbook exportExcelInfo(B2bPrice b2bprice) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;


    /**�ձ�**/
    List<ReportDay> findReportDay(ReportDay reportday, PageModel pageModel);
    List<String> selectShengfen();
	List<String> selectChengshi(String shengfen);
	List<String> selectQuyufl(String shengfen,String chengshi);
   /**��ȱ���**/
   List<ReportYear> selectReportYear(String begin_date,String end_date);
    //**����excel**/
    XSSFWorkbook exportReportYear() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;

	/** ������Ʒ��Ӧ��ϵ
	 **/
	List<GoodsForYz> selectGoodsForYz(GoodsForYz goodsforyz);
	GoodsForYz findGoodsForYzById(String jnd_spid);
	void modifyGoodsForYz(GoodsForYz goodsforyz);
	void addGoodsForYz(GoodsForYz goodsforyz);
	void removeGoodsForYzById(String jnd_spid);

}
