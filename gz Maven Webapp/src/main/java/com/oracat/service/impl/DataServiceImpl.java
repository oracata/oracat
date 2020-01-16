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


	@Autowired  //自动装配
	private ReportYearDao reportYearDao ;

	@Autowired  //自动装配
	private ReportMonthDao reportMonthDao ;


	@Autowired
	private  ReportDayDao  reportDayDao;

	@Autowired  //自动装配
	private GoodsForYzDao goodsForYzDao ;

	@Autowired  //自动装配
	private PricePareDao pricePareDao ;

	@Autowired  //自动装配
	private ErpCustomDao erpCustomDao ;

	@Autowired  //自动装配
	private Top10CustDao top10CustDao ;


	@Autowired  //自动装配
	private JobDao jobDao ;
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


	/** 月报表**/

	@Override
	public List<ReportMonth> selectReportMonth(String begin_date,String end_date)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return  reportMonthDao.selectReportMonth(begin_date,end_date);

	}

	@Override
	public XSSFWorkbook exportReportMonth(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
		//根据条件查询数据，把数据装载到一个list中


		DynamicDataSourceHolder.setDataSource("sqlserver");
		List<ReportMonth> list = reportMonthDao.selectReportMonth(begin_date,end_date);
		List<ExcelBean> excel=new ArrayList<>();
		Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		XSSFWorkbook xssfWorkbook=null;
		//设置标题栏
		excel.add(new ExcelBean("日期","rq",0));
		excel.add(new ExcelBean("含税金额","hsje",0));
		excel.add(new ExcelBean("参考毛利","cankml",0));
		excel.add(new ExcelBean("参考毛利率","cankmll",0));
		excel.add(new ExcelBean("电商客户数","cust_num",0));
		excel.add(new ExcelBean("登录客户数","login_num",0));
		excel.add(new ExcelBean("登录率","login_rate",0));
		excel.add(new ExcelBean("支付客户数","pay_cust",0));
		excel.add(new ExcelBean("未支付客户数","not_pay_cust",0));
		excel.add(new ExcelBean("未支付金额","not_pay",0));
		excel.add(new ExcelBean("购物车客户数","cart_cust",0));
		excel.add(new ExcelBean("购物车金额","cart_price",0));

		map.put(0, excel);
		String sheetName = "电商月表";
		//调用ExcelUtil的方法
		xssfWorkbook = ExcelUtil.createExcelFile(ReportMonth.class, list, map, sheetName);
		return xssfWorkbook;
	}

   /** 年度报表**/

	@Override
	public List<ReportYear> selectReportYear(String begin_date,String end_date)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return  reportYearDao.selectReportYear(begin_date,end_date);

	}

	@Override
	public XSSFWorkbook exportReportYear() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
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
		List<ReportYear> list = reportYearDao.selectReportYear("","");
		List<ExcelBean> excel=new ArrayList<>();
		Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		XSSFWorkbook xssfWorkbook=null;
		//设置标题栏
		excel.add(new ExcelBean("区域","area",0));
		excel.add(new ExcelBean("201907","je201907",0));
		excel.add(new ExcelBean("201908","je201908",0));
		excel.add(new ExcelBean("201909","je201909",0));
		excel.add(new ExcelBean("201910","je201910",0));
		excel.add(new ExcelBean("201911","je201911",0));
		excel.add(new ExcelBean("201912","je201912",0));
		excel.add(new ExcelBean("全年","year",0));
		excel.add(new ExcelBean("毛利额","ml",0));
		excel.add(new ExcelBean("毛利率","mll",0));

		map.put(0, excel);
		String sheetName = "电商年表";
		//调用ExcelUtil的方法
		xssfWorkbook = ExcelUtil.createExcelFile(ReportYear.class, list, map, sheetName);
		return xssfWorkbook;
	}



	/** 客户top10报表**/

	@Override
	public List<Top10Cust> selectTop10Cust(String begin_date,String end_date)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return  top10CustDao.selectTop10Cust(begin_date,end_date);

	}

	@Override
	public XSSFWorkbook exportTop10cust(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
		//根据条件查询数据，把数据装载到一个list中


		DynamicDataSourceHolder.setDataSource("sqlserver");
		List<Top10Cust> list = top10CustDao.selectTop10Cust(begin_date,end_date);
		List<ExcelBean> excel=new ArrayList<>();
		Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		XSSFWorkbook xssfWorkbook=null;
		//设置标题栏
		excel.add(new ExcelBean("客户名称","wldwname",0));
		excel.add(new ExcelBean("含税金额","hsje",0));
		excel.add(new ExcelBean("毛利","cankml",0));
		excel.add(new ExcelBean("毛利率","cankmll",0));


		map.put(0, excel);
		String sheetName = "销售客户top10";
		//调用ExcelUtil的方法
		xssfWorkbook = ExcelUtil.createExcelFile(Top10Cust.class, list, map, sheetName);
		return xssfWorkbook;
	}



	/** 商品top10报表**/

	@Override
	public List<Top10Cust> selectTop10Goods(String begin_date,String end_date)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return  top10CustDao.selectTop10Goods(begin_date,end_date);

	}


	@Override
	public XSSFWorkbook exportTop10goods(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
		//根据条件查询数据，把数据装载到一个list中


		DynamicDataSourceHolder.setDataSource("sqlserver");
		List<Top10Cust> list = top10CustDao.selectTop10Goods(begin_date,end_date);
		List<ExcelBean> excel=new ArrayList<>();
		Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		XSSFWorkbook xssfWorkbook=null;
		//设置标题栏
		excel.add(new ExcelBean("商品名称","spmch",0));
		excel.add(new ExcelBean("含税金额","hsje",0));
		excel.add(new ExcelBean("毛利","cankml",0));
		excel.add(new ExcelBean("毛利率","cankmll",0));


		map.put(0, excel);
		String sheetName = "销售商品top10";
		//调用ExcelUtil的方法
		xssfWorkbook = ExcelUtil.createExcelFile(Top10Cust.class, list, map, sheetName);
		return xssfWorkbook;
	}


	/** 优惠券报表**/

	@Override
	public List<Top10Cust> selectCoupon(String begin_date,String end_date)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return  top10CustDao.selectCoupon(begin_date,end_date);

	}


	@Override
	public XSSFWorkbook exportCoupon(String begin_date,String end_date) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
		//根据条件查询数据，把数据装载到一个list中


		DynamicDataSourceHolder.setDataSource("sqlserver");
		List<Top10Cust> list = top10CustDao.selectCoupon(begin_date,end_date);
		List<ExcelBean> excel=new ArrayList<>();
		Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		XSSFWorkbook xssfWorkbook=null;
		//设置标题栏
		excel.add(new ExcelBean("优惠券类型","coupon",0));
		excel.add(new ExcelBean("使用单数","num",0));
		excel.add(new ExcelBean("含税金额","hsje",0));
		excel.add(new ExcelBean("毛利","cankml",0));
		excel.add(new ExcelBean("毛利率","cankmll",0));


		map.put(0, excel);
		String sheetName = "优惠券";
		//调用ExcelUtil的方法
		xssfWorkbook = ExcelUtil.createExcelFile(Top10Cust.class, list, map, sheetName);
		return xssfWorkbook;
	}


	//实时图表

    @Override
	public List<RealTime> selectRealTime()
   {
	   DynamicDataSourceHolder.setDataSource("sqlserver");
   	   return reportRealTimeDao.selectRealTime();

   }



	//实时图表

	@Override
	public List<RealTime> selectArea()
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");
		return reportRealTimeDao.selectArea();

	}




    @Override
    public List<B2bPrice> selectB2bPrice(B2bPrice b2bprice)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");

        return b2bPriceDao.selectB2bPrice(b2bprice.getId(),b2bprice.getNo(),b2bprice.getName());

    }


	@Override
	public List<B2bPrice> selectPriceNotin()
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return b2bPriceDao.selectPriceNotin();

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
		excel.add(new ExcelBean("专项价格","xsj",0));
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



   /**云中商品对应关系**/

	@Override
	public List<GoodsForYz> selectGoodsForYz(GoodsForYz goodsforyz)
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		return goodsForYzDao.selectGoodsForYz(goodsforyz.getJnd_spid(),goodsforyz.getJnd_spbm(),goodsforyz.getJnd_spname());

	}


	@Override
	public GoodsForYz findGoodsForYzById(String jnd_spid)
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		return goodsForYzDao.findGoodsForYzById(jnd_spid);

	}

	@Override
	public void modifyGoodsForYz(GoodsForYz goodsforyz)
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		 goodsForYzDao.modifyGoodsForYz(goodsforyz.getJnd_spid(),goodsforyz.getJnd_spname(),goodsforyz.getYz_goods_id(),goodsforyz.getYz_goods_name());

	}

	@Override
	public void addGoodsForYz(GoodsForYz goodsforyz)
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		goodsForYzDao.addGoodsForYz(goodsforyz.getJnd_spid(),goodsforyz.getJnd_spname(),goodsforyz.getYz_goods_id(),goodsforyz.getYz_goods_name());

	}

	@Override
	public void removeGoodsForYzById(String jnd_spid)
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		goodsForYzDao.deleteGoodsForYz(jnd_spid);

	}

	@Override
	public List<Map<String,Map<String,Map<String,String>>>>   findGoodsForYzNotin(String date)
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		return goodsForYzDao.findGoodsForYzNotin(date);

	}


    @Override
    public List<Map<String,Map<String,Map<String,String>>>>   findGoodsForYzId(String date,String jnd_spname,String manufacturer)
    {
        DynamicDataSourceHolder.setDataSource("mysql");

        return goodsForYzDao.findGoodsForYzId(date,jnd_spname,manufacturer);

    }



    @Override
    public List<String>   findGoodsForYzName(String date,String goods_id)
    {
        DynamicDataSourceHolder.setDataSource("mysql");

        return goodsForYzDao.findGoodsForYzName(date,goods_id);

    }


    @Override
    public int removeGoodsForYzId(String jnd_spid)
    {
        DynamicDataSourceHolder.setDataSource("mysql");

      return  goodsForYzDao.removeGoodsForYzId(jnd_spid);

    }


    /**价格对比**/
	@Transactional(readOnly=true)
	@Override
	public List<PricePare> findPricePare(PricePare pricepare,PageModel pageModel) {
		DynamicDataSourceHolder.setDataSource("mysql");
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("PricePare", pricepare);


		int recordCount = pricePareDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){

			params.put("pageModel", pageModel);
		}



		List<PricePare> yzpricepare = pricePareDao.selectByPage(params);

		return yzpricepare;
	}




	/**电商无资料终端开单客户**/

	@Override
	public List<ErpCustom> selectErpCustom(String begin_date,String end_date)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return erpCustomDao.selectErpCustom(begin_date,end_date);

	}

	@Override
	public List<ErpCustom> selectFgsCustom()
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return erpCustomDao.selectFgsCustom();

	}


	@Override
	public int modifyErpCustom(ErpCustom erpcustom)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return erpCustomDao.modifyErpCustom(erpcustom.getWldwid(),erpcustom.getLxdh(),erpcustom.getLxr(),erpcustom.getDs_lxdh(),erpcustom.getDs_lxr());

	}


	@Override
	public List<JobandTrigger> getJobAndTrigger()
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		return jobDao.getJobAndTrigger();

	}

	@Override
	public int queryJobCount()
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		return jobDao.queryJobCount();

	}


}