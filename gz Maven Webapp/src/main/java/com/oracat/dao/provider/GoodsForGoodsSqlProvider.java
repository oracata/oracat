package com.oracat.dao.provider;

import static com.oracat.util.Constants.GFTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.oracat.model.Goods;
import com.oracat.model.Goodsforgoods;

public class GoodsForGoodsSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(GFTABLE);
				if(params.get("Goods") != null){
					Goods goods = (Goods) params.get("Goods");
					System.out.println(goods.getBegin_date());
					System.out.println("********************begin_date:"+goods.getBegin_date());
					System.out.println("********************end_date:"+goods.getEnd_date());

					if(goods.getBegin_date() != null && !goods.getBegin_date().equals("")){
						WHERE("  date  between   '" +goods.getBegin_date()+"'  and   '"+goods.getEnd_date()+"'");
						                  
					}
					
					if(goods.getGoods_name() != null && !goods.getGoods_name().equals("")){
						WHERE(" goods_name like  '%"+goods.getGoods_name()+"%'");
						                  
					}
					
					
					
					if(goods.getGoods_id() != null && !goods.getGoods_id().equals("")){
						WHERE(" goods_id='"+goods.getGoods_id()+"'");
						                  
					}
					
					
					
					
					
				}
			}
		}.toString();
		sql +="    order by date  ";
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(GFTABLE);
				if(params.get("Goodsforgoods") != null){
					Goodsforgoods goodsforgoods = (Goodsforgoods) params.get("Goodsforgoods");

	
					
					if(goodsforgoods.getJnd_goods_name() != null && !goodsforgoods.getJnd_goods_name().equals("")){
						WHERE(" jnd_goods_name like  '%"+goodsforgoods.getJnd_goods_name()+"%'");
						                  
					}
					
					
					
					if(goodsforgoods.getJnd_id() != null && !goodsforgoods.getJnd_id().equals("")){
						WHERE(" goods_id='"+goodsforgoods.getJnd_id()+"'");
						                  
					}
					
					
					
					
				}
			}
		}.toString();
	}
	
	
	
	// 动态插入
	public String insertGoodsForGoods(Goodsforgoods goodsforgoods){
		
		return new SQL(){
			{
				INSERT_INTO(GFTABLE);
				if(goodsforgoods.getJnd_goods_name() != null && !goodsforgoods.getJnd_goods_name().equals("")){
					VALUES("name", "#{name}");
				}
			 
			}
		}.toString();
	}
	// 动态更新
	public String updateGoodsForGoods(Goodsforgoods goodsforgoods){
		
		return new SQL(){
			{
				UPDATE(GFTABLE);
				if(goodsforgoods.getJnd_goods_name() != null){
					SET(" name = #{name} ");
				}

				WHERE(" id = #{id} ");
			}
		}.toString();
	}

}
