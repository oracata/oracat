package com.oracat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
 

import com.oracat.dao.DcGoodsDao;
import com.oracat.model.Goods;
import com.oracat.service.DataService;
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
	
 
	

	
	/*****************东昌服务接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Goods> findAllDcGoods(String date) {
		
		return dcGoodsDao.selectAllGoods(date);
	}
	
 
	@Transactional(readOnly=true)
	@Override
	public List<Goods> findDcGoods(Goods goods,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("Goods", goods);
		int recordCount = dcGoodsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
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

 

 
 

	


	
}