package com.oracat.dao.provider;

import java.util.Map;
import static com.oracat.util.Constants.DCTABLE;

import org.apache.ibatis.jdbc.SQL;

import com.oracat.model.Goods;


public class DcGoodsSqlProvider {
	// 分页动态查询
		public String selectWhitParam(final Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(DCTABLE);
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
			sql +="    order by date  desc ";
			
			if(params.get("pageModel") != null){
				sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			}
			
			return sql;
		}	
		// 动态查询总数量
		public String count(final Map<String, Object> params){
			return new SQL(){
				{
					SELECT("count(*)");
					FROM(DCTABLE);
					if(params.get("Goods") != null){
						Goods goods = (Goods) params.get("Goods");
						System.out.println("2********************:"+goods.getBegin_date());
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
		}	
}
