package com.oracat.dao.provider;

import java.util.Map;
import static com.oracat.util.Constants.DCTABLE;

import org.apache.ibatis.jdbc.SQL;

import com.oracat.model.Goods;


public class DcGoodsSqlProvider {
	// ��ҳ��̬��ѯ
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(DCTABLE);
					if(params.get("goods") != null){
						Goods goods = (Goods) params.get("goods");
						if(goods.getGoods_name() != null && !goods.getGoods_name().equals("")){
							WHERE("  goods_name LIKE CONCAT ('%',#{goods.getGoods_name},'%') ");
						}
					}
				}
			}.toString();
			
			if(params.get("pageModel") != null){
				sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			}
			
			return sql;
		}	
		// ��̬��ѯ������
		public String count(Map<String, Object> params){
			return new SQL(){
				{
					SELECT("count(*)");
					FROM(DCTABLE);
					if(params.get("goods") != null){
						Goods goods = (Goods) params.get("goods");
						if(goods.getGoods_name() != null && !goods.getGoods_name().equals("")){
							WHERE("  name LIKE CONCAT ('%',#{goods.getGoods_name},'%') ");
						}
					}
				}
			}.toString();
		}	
}
