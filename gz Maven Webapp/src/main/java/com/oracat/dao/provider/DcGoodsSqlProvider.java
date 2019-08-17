package com.oracat.dao.provider;

import java.util.Map;
import static com.oracat.util.Constants.DCTABLE;

import org.apache.ibatis.jdbc.SQL;

import com.oracat.model.Goods;


public class DcGoodsSqlProvider {
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(DCTABLE);
					if(params.get("goods") != null){
						Goods goods = (Goods) params.get("goods");
						System.out.println(goods.getDate());
						System.out.println("1********************:"+goods.getDate());
						if(goods.getDate() != null && !goods.getDate().equals("")){
							WHERE("  date =#{goods.getDate() ");
						}
					}
				}
			}.toString();
			
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
					FROM(DCTABLE);
					if(params.get("goods") != null){
						Goods goods = (Goods) params.get("goods");
						System.out.println("2********************:"+goods.getDate());
						if(goods.getDate() != null && !goods.getDate().equals("")){
							WHERE("   date =#{goods.getDate() ");
						}
					}
				}
			}.toString();
		}	
}
