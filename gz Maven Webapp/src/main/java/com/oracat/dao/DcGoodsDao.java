package com.oracat.dao;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.oracat.dao.provider.*;
import static com.oracat.util.Constants.DCTABLE;
import com.oracat.model.*;

import java.util.List;
import java.util.Map;
public interface DcGoodsDao {
	// ¶¯Ì¬²éÑ¯
	@SelectProvider(type=DcGoodsSqlProvider.class,method="selectWhitParam")
	List<Goods> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=DcGoodsSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+DCTABLE+" where date=#{begin_date} ")
	List<Goods> selectAllGoods(String date);
	
	@Select("select * from "+DCTABLE+" where goods_id = #{goods_id}")
	Goods selectByGoods_id(int goods_id);

}
