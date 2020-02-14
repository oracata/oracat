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


    @Autowired  //×Ô¶¯×°Åä
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
}
