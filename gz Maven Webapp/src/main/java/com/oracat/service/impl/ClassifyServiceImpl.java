package com.oracat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oracat.dao.ClassifyDao;
import com.oracat.dao.JobDao;
import com.oracat.model.Classify;
import com.oracat.model.ScheduleJob;
import com.oracat.service.ClassifyService;
import com.oracat.util.DataGridView;
import com.oracat.util.DynamicDataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("classifyService")
public class ClassifyServiceImpl implements ClassifyService {


    @Autowired  //�Զ�װ��
    private ClassifyDao classifyDao ;

    @Override
    public DataGridView selectClassify(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
        Page<Object> page= PageHelper.startPage(classify.getPage(),classify.getLimit());
        List<Classify> data=classifyDao.queryAllClassify(classify);
        return new DataGridView(page.getTotal(),data);

    }



    @Override
    public DataGridView findFenleibybm(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
        Page<Object> page= PageHelper.startPage(classify.getPage(),classify.getLimit());
        List<Classify> data=classifyDao.queryfenleibybm(classify);
        return new DataGridView(page.getTotal(),data);

    }




    @Override
    public DataGridView findFenlei1(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
        Page<Object> page= PageHelper.startPage(0,999);
        List<Classify> data=classifyDao.queryfenlei1(classify);
        return new DataGridView(page.getTotal(),data);

    }

    @Override
    public DataGridView findFenlei2(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
        Page<Object> page= PageHelper.startPage(0,999);
        List<Classify> data=classifyDao.queryfenlei2(classify);
        return new DataGridView(page.getTotal(),data);

    }

    @Override
    public DataGridView findFenlei3(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
        Page<Object> page= PageHelper.startPage(0,999);
        List<Classify> data=classifyDao.queryfenlei3(classify);
        return new DataGridView(page.getTotal(),data);

    }

    @Override
    public DataGridView findFenlei4(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
        Page<Object> page= PageHelper.startPage(0,999);
        List<Classify> data=classifyDao.queryfenlei4(classify);
        return new DataGridView(page.getTotal(),data);

    }


    @Override
    public DataGridView findFenleibm(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
        Page<Object> page= PageHelper.startPage(0,999);
        List<Classify> data=classifyDao.queryfenleibm(classify);
        return new DataGridView(page.getTotal(),data);

    }


    @Override
    public void updateAndSaveClassify(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");



        List<Classify> data=classifyDao.queryByFlbm(classify.getFlbm());
        if(data.size()==0){
            classifyDao.insertFenlei(classify);
        }else{
            classifyDao.updateFenlei(classify);
        }


    }


    @Override
    public void updateAndSaveClassify2(Classify classify)
    {
        DynamicDataSourceHolder.setDataSource("sqlserver");
       


        List<Classify> data=classifyDao.queryByFlbm(classify.getFlbm());
        if(data.size()==0){
            classifyDao.insertFenlei(classify);
            DynamicDataSourceHolder.setDataSource("mysql");
            classifyDao.insertFenlei(classify);
        }else{
            classifyDao.updateFenlei(classify);
        }



    }


}
