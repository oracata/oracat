package com.oracat.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.oracat.dao.provider.*;
import static com.oracat.util.Constants.GFTABLE;
import com.oracat.model.*;

import java.util.List;
import java.util.Map;

public class GoodsForGoodsDao {

	// ��̬��ѯ
	@SelectProvider(type=GoodsForGoodsSqlProvider.class,method="selectWhitParam")
	List<Goodsforgoods> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=GoodsForGoodsSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+GFTABLE+" ")
	List<Goodsforgoods> selectAllGoodsforgoods();
	
	@Select("select * from "+GFTABLE+" where ID = #{id}")
	Goodsforgoods selectById(int id);

	// ����idɾ������
	@Delete(" delete from "+GFTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// ��̬���벿��
	@SelectProvider(type=GoodsForGoodsSqlProvider.class,method="insertGoodsForGoods")
	void save(Goodsforgoods goodsforgoods);
	
	// ��̬�޸��û�
	@SelectProvider(type=GoodsForGoodsSqlProvider.class,method="updateGoodsForGoods")
	void update(Goodsforgoods goodsforgoods);
	
}
