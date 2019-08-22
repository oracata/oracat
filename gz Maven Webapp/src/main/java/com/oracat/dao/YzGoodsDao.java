package com.oracat.dao;

import static com.oracat.util.Constants.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.oracat.dao.provider.*;
import com.oracat.model.Goods;

public interface YzGoodsDao {
	// ¶¯Ì¬²éÑ¯
		@SelectProvider(type=YzGoodsSqlProvider.class,method="selectWhitParam")
		List<Goods> selectByPage(Map<String, Object> params);
		
		@SelectProvider(type=YzGoodsSqlProvider.class,method="count")
		Integer count(Map<String, Object> params);
		
		@Select("select * from "+YZTABLE+" where date=#{begin_date} ")
		List<Goods> selectAllGoods(String date);
		
		@Select("select * from "+YZTABLE+" where goods_id = #{goods_id}")
		Goods selectByGoods_id(int goods_id);
}
