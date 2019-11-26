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
	 * �Զ�ע��־ò�Dao����
	 * */

	@Autowired
	private DcGoodsDao dcGoodsDao;
	
	
	@Autowired
	private YzGoodsDao yzGoodsDao;

	@Autowired
	private ReportRealTimeDao reportRealTimeDao ;

    @Autowired  //�Զ�װ��
    private B2bPriceDao b2bPriceDao ;


	@Autowired  //�Զ�װ��
	private ReportYearDao reportYearDao ;

    @Autowired
	private  ReportDayDao  reportDayDao;

	@Autowired  //�Զ�װ��
	private GoodsForYzDao goodsForYzDao ;
	
	/*****************��������ӿ�ʵ��*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Goods> findAllDcGoods(String date) {
		
		return dcGoodsDao.selectAllGoods(date);
	}
	
 
	@Transactional(readOnly=true)
	@Override
	public List<Goods> findDcGoods(Goods goods,PageModel pageModel) {
		DynamicDataSourceHolder.setDataSource("mysql");
		/** ��ǰ��Ҫ��ҳ������������  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("Goods", goods);
		int recordCount = dcGoodsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** ��ʼ��ҳ��ѯ���ݣ���ѯ�ڼ�ҳ������ */
		    params.put("pageModel", pageModel);
	    }
		System.out.println("***********begindate:"+goods.getBegin_date());
		List<Goods> dcgoods = dcGoodsDao.selectByPage(params);
		 
		return dcgoods;
	}
	
 
	/**
	 * HrmServiceImpl�ӿ�findDeptById����ʵ��
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
		/** ��ǰ��Ҫ��ҳ������������  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("Goods", goods);
		int recordCount = yzGoodsDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** ��ʼ��ҳ��ѯ���ݣ���ѯ�ڼ�ҳ������ */
		    params.put("pageModel", pageModel);
	    }
		System.out.println("***********begindate:"+goods.getBegin_date());
		List<Goods> yzgoods = yzGoodsDao.selectByPage(params);
		 
		return yzgoods;
	}

	//�ձ�
	@Transactional(readOnly=true)
	@Override
	public List<ReportDay> findReportDay(ReportDay reportday,PageModel pageModel) {
		DynamicDataSourceHolder.setDataSource("sqlserver");
		/** ��ǰ��Ҫ��ҳ������������  */
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ReportDay", reportday);
		int recordCount = reportDayDao.count(params);
		pageModel.setRecordCount(recordCount);

		if(recordCount > 0){
			/** ��ʼ��ҳ��ѯ���ݣ���ѯ�ڼ�ҳ������ */

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




   /** ��ȱ���**/

	@Override
	public List<ReportYear> selectReportYear(String begin_date,String end_date)
	{
		DynamicDataSourceHolder.setDataSource("sqlserver");

		return  reportYearDao.selectReportYear(begin_date,end_date);

	}

	@Override
	public XSSFWorkbook exportReportYear() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
		//����������ѯ���ݣ�������װ�ص�һ��list��

		/** �����ֶθ�ʽ��
		 for(int i=0;i<list.size();i++){
		 //��ѯ��������
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
		//���ñ�����
		excel.add(new ExcelBean("����","area",0));
		excel.add(new ExcelBean("201907","je201907",0));
		excel.add(new ExcelBean("201908","je201908",0));
		excel.add(new ExcelBean("201909","je201909",0));
		excel.add(new ExcelBean("201910","je201910",0));
		excel.add(new ExcelBean("201911","je201911",0));
		excel.add(new ExcelBean("ȫ��","year",0));
		excel.add(new ExcelBean("ë����","ml",0));
		excel.add(new ExcelBean("ë����","mll",0));

		map.put(0, excel);
		String sheetName = "�������";
		//����ExcelUtil�ķ���
		xssfWorkbook = ExcelUtil.createExcelFile(ReportYear.class, list, map, sheetName);
		return xssfWorkbook;
	}





	//ʵʱͼ��

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
        //����������ѯ���ݣ�������װ�ص�һ��list��

        /** �����ֶθ�ʽ��
        for(int i=0;i<list.size();i++){
            //��ѯ��������
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
        //���ñ�����
        excel.add(new ExcelBean("��Ʒid","id",0));
        excel.add(new ExcelBean("��Ʒ����","no",0));
        excel.add(new ExcelBean("��Ʒ����","name",0));
        excel.add(new ExcelBean("���","spec",0));
        excel.add(new ExcelBean("����","manufacturer",0));
        excel.add(new ExcelBean("���̼۸�","pfpj",0));
        excel.add(new ExcelBean("�ն˽�7��ƽ����Ʊ��","hshj",0));
        excel.add(new ExcelBean("���ն����۶Ա���","abs_rate",0));
        excel.add(new ExcelBean("����","cankcbj",0));
        excel.add(new ExcelBean("������ۼ�","zdxshj",0));
        excel.add(new ExcelBean("���̿��","stock_num",0));



        map.put(0, excel);
        String sheetName = "���̼۸�Ա�";
        //����ExcelUtil�ķ���
        xssfWorkbook = ExcelUtil.createExcelFile(B2bPrice.class, list, map, sheetName);
        return xssfWorkbook;
    }



   /**������Ʒ��Ӧ��ϵ**/

	@Override
	public List<GoodsForYz> selectGoodsForYz(GoodsForYz goodsforyz)
	{
		DynamicDataSourceHolder.setDataSource("mysql");

		return goodsForYzDao.selectGoodsForYz(goodsforyz.getJnd_spid(),goodsforyz.getJnd_spname());

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



}