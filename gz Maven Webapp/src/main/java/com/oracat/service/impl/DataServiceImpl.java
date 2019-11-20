package com.oracat.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;


import com.oracat.dao.*;
import com.oracat.model.*;
import com.oracat.service.DataService;
import com.oracat.util.DynamicDataSourceHolder;
import com.oracat.util.excel.ExcelBean;
import com.oracat.util.excel.ExcelUtil;
import com.oracat.util.tag.PageModel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	public List<String> selectShengfen()
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");
		return reportDayDao.selectShengfen();
	}

	@Override
	public List<String> selectChengshi(String shengfen)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");
		return reportDayDao.selectChengshi( shengfen);
	}

	@Override
	public List<String> selectQuyufl(String shengfen,String chengshi)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");
		return reportDayDao.selectQuyufl(shengfen,chengshi);
	}







	//实时图表

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


    @Override
    public XSSFWorkbook exportExcelInfo(B2bPrice b2bprice) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        //根据条件查询数据，把数据装载到一个list中

        /** 特殊字段格式化
        for(int i=0;i<list.size();i++){
            //查询财务名字
            int adminId = list.get(i).getAdminId();
            String adminName = salarymanageDao.selectAdminNameById(adminId);
            list.get(i).setAdminName(adminName);
            list.get(i).setId(i+1);
        }
         **/
        DynamicDataSourceHolder.setDataSource("sqlserver");
        List<B2bPrice> list = b2bPriceDao.selectB2bPrice(b2bprice.getId(),b2bprice.getNo(),b2bprice.getName());
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("商品id","id",0));
        excel.add(new ExcelBean("商品编码","no",0));
        excel.add(new ExcelBean("商品名称","name",0));
        excel.add(new ExcelBean("规格","spec",0));
        excel.add(new ExcelBean("产家","manufacturer",0));
        excel.add(new ExcelBean("电商价格","pfpj",0));
        excel.add(new ExcelBean("终端近7天平均开票价","hshj",0));
        excel.add(new ExcelBean("与终端销价对比率","abs_rate",0));
        excel.add(new ExcelBean("进价","cankcbj",0));
        excel.add(new ExcelBean("最低销售价","zdxshj",0));
        excel.add(new ExcelBean("电商库存","stock_num",0));



        map.put(0, excel);
        String sheetName = "电商价格对比";
        //调用ExcelUtil的方法
        xssfWorkbook = ExcelUtil.createExcelFile(B2bPrice.class, list, map, sheetName);
        return xssfWorkbook;
    }





}