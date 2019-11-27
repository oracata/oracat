package com.oracat.dao.provider;

import com.oracat.model.Goods;
import com.oracat.model.PricePare;
import com.oracat.model.ReportDay;
import com.oracat.util.tag.PageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.oracat.util.Constants.YZTABLE;

public class PricePareSqlProvider {

    // 分页动态查询
    public String selectWhitParam(final Map<String, Object> params){
        String   sql = new SQL() {
            {
                if (params.get("PricePare") != null) {

                    PricePare pricepare = (PricePare) params.get("PricePare");

                    SELECT(" a.date,a.no,a.name,a.spec,a.manufacturer,a.pfpj,b.price,b.spec as yz_spec,b.active_type, b.active_name ");

                    FROM("  jnd_goods a \n" +
                            "inner JOIN goods_for_yz c on a.id=c.jnd_spid\n" +
                            "inner join yz_puyao b on c.yz_goods_id=b.goods_id and a.date=b.date and b.date between '" + pricepare.getBegin_date() + "' and  '" + pricepare.getEnd_date() + "' ");


                    WHERE(" a.date between '" + pricepare.getBegin_date() + "' and  '" + pricepare.getEnd_date() + "'  and a.STATE=1");


                    if (pricepare.getBegin_date() != null && !pricepare.getBegin_date().equals("")) {
                        WHERE("  a.date  between   '" + pricepare.getBegin_date() + "'  and   '" + pricepare.getEnd_date() + "'");

                    }

                    if (pricepare.getNo() != null && !pricepare.getNo().equals("")) {
                        WHERE(" a.no like  '%" + pricepare.getNo() + "%'");

                    }


                    if (pricepare.getName() != null && !pricepare.getName().equals("")) {
                        WHERE(" a.name like  '%" + pricepare.getName() + "%'");

                    }

                }


            }
            }.toString();







          String  sql2 =sql+ "    order by a.date   ";

            if (params.get("pageModel") != null) {
        sql2 += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
            }


        return sql2;
    }
    // 动态查询总数量
    public String count(final Map<String, Object> params){
        return new SQL(){
            {
                if(params.get("PricePare") != null){
                    PricePare pricepare = (PricePare)params.get("PricePare");
                SELECT("count(*)");
                FROM("  jnd_goods a \n" +
                        "inner JOIN goods_for_yz c on a.id=c.jnd_spid\n" +
                        "inner join yz_puyao b on c.yz_goods_id=b.goods_id and a.date=b.date and b.date between '" + pricepare.getBegin_date() + "' and  '" + pricepare.getEnd_date() + "' ");


                    if (pricepare.getBegin_date() != null && !pricepare.getBegin_date().equals("")) {
                        WHERE("  a.date  between   '" + pricepare.getBegin_date() + "'  and   '" + pricepare.getEnd_date() + "'");

                    }

                    if (pricepare.getNo() != null && !pricepare.getNo().equals("")) {
                        WHERE(" a.no like  '%" + pricepare.getNo() + "%'");

                    }

                    if (pricepare.getName() != null && !pricepare.getName().equals("")) {
                        WHERE(" a.name like  '%" + pricepare.getName() + "%'");

                    }







                }
            }
        }.toString();
    }

}
