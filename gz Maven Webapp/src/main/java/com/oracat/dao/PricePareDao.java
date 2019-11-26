package com.oracat.dao;

import com.oracat.dao.provider.PricePareSqlProvider;

import com.oracat.model.PricePare;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface PricePareDao {

    @SelectProvider(type= PricePareSqlProvider.class,method="selectWhitParam")
    List<PricePare> selectByPage(Map<String, Object> params);
    @SelectProvider(type=PricePareSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);
}
