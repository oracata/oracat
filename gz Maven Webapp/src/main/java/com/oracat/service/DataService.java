package com.oracat.service;

import java.util.List;


import com.oracat.model.*;

import com.oracat.util.tag.PageModel;

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


    List<ReportDay> findReportDay(ReportDay reportday, PageModel pageModel);
}
