package com.oracat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
 

import com.oracat.dao.*;
import com.oracat.model.*;
import com.oracat.service.DataService;
import com.oracat.util.DynamicDataSourceHolder;
import com.oracat.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("DataService")
public class DataServiceImpl implements DataService{

	/**
	 * 自动注入持久层Dao对象
	 * */

	@Autowired
	private DcGoodsDao dcGoodsDao;
	
	
	@Autowired
	private YzGoodsDao yzGoodsDao;

	@Autowired
	private ReportRealTimeDao reportRealTimeDao ;

    @Autowired  //自动装配
    private B2bPriceDao b2bPriceDao ;

    @Autowired
	private  ReportDayDao  reportDayDao;
	
	/*****************东昌服务接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Goods> findAllDcGoods(String date) {
		
		return dcGoodsDao.selectAllGoods(date);
	}
	
 
	@Transactional(readOnly=true)
	@Override
	public List<Goods> findDcGoods(Goods goods,PageModel pageModel) {
		DynamicDataSourceHolder.setDataSource("mysql");
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("Goods", goods);
		int recordCount = dcGoodsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		System.out.println("***********begindate:"+goods.getBegin_date());
		List<Goods> dcgoods = dcGoodsDao.selectByPage(params);
		 
		return dcgoods;
	}
	
 
	/**
	 * HrmServiceImpl接口findDeptById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Goods findDcGoodById(Integer goods_id) {

		return dcGoodsDao.selectByGoods_id(goods_id);
	}

 

 
	
	@Transactional(readOnly=true)
	@Override
	public List<Goods> findYzGoods(Goods goods,PageModel pageModel) {
		DynamicDataSourceHolder.setDataSource("mysql");
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("Goods", goods);
		int recordCount = yzGoodsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		System.out.println("***********begindate:"+goods.getBegin_date());
		List<Goods> yzgoods = yzGoodsDao.selectByPage(params);
		 
		return yzgoods;
	}

	//日报
	@Transactional(readOnly=true)
	@Override
	public List<ReportDay> findReportDay(ReportDay reportday,PageModel pageModel) {
		DynamicDataSourceHolder.setDataSource("sqlserver");
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ReportDay", reportday);
		int recordCount = reportDayDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}

		List<ReportDay>  reportday_result= reportDayDao.selectByPage(params);

		return reportday_result;
	}




	@Override
	public List<RealTime> selectRealTime()
   {
	   DynamicDataSourceHolder.setDataSource("sqlserver");
   	   return reportRealTimeDao.selectRealTime();

   }


    @Override
    public List<B2bPrice> selectB2bPrice(B2bPrice b2bprice)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");

        return b2bPriceDao.selectB2bPrice(b2bprice.getId(),b2bprice.getNo(),b2bprice.getName());

    }







}