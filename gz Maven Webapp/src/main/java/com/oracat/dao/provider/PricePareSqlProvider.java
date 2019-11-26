package com.oracat.dao.provider;

import com.oracat.model.PricePare;
import com.oracat.model.ReportDay;
import com.oracat.util.tag.PageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class PricePareSqlProvider {

    // 分页动态查询
    public String selectWhitParam(final Map<String, Object> params){
        final String sql =  new SQL(){
            {
                SELECT(   " a.date,a.no,a.name,a.spec,a.manufacturer,a.pfpj,b.price,b.spec as yz_spec,b.active_type, b.active_name ");

                FROM("  jnd_goods a \n" +
                        "inner JOIN goods_for_yz c on a.id=c.jnd_spid\n" +
                        "inner join yz_puyao b on c.yz_goods_id=b.goods_id and a.date=b.date and b.date between '2019-10-26' and  '2019-11-26' ");


                WHERE(" a.date between '2019-10-26' and  '2019-11-26'  and a.STATE=1");

                if(params.get("ReportDay") != null){
                    PricePare pricepare = (PricePare) params.get("PricePare");


                    if(pricepare.getBegin_date() != null && !pricepare.getBegin_date().equals("")){
                        WHERE("  rq  between   '" +pricepare.getBegin_date()+"'  and   '"+pricepare.getEnd_date()+"'");

                    }

                    if(pricepare.getNo() != null && !pricepare.getNo().equals("")){
                        WHERE(" a.no like  '%"+pricepare.getNo()+"%'");

                    }









                }
            }
        }.toString();




        String sql2 =  new SQL(){
            {
                SELECT(" rq                                                                  \n" +
                        ",shengfen                                                            \n" +
                        ",chengshi                                                            \n" +
                        ",quyufl                                                              \n" +
                        ",custom_num                                                          \n" +
                        ",login_rate \n" +
                        ",login_pay_custom                                                    \n" +
                        ",login_nopay_custom                                                  \n" +
                        ",order_pay_custom                                                    \n" +
                        ",order_pay_price                                                     \n" +
                        ",order_nopay_custom                                                  \n" +
                        ",order_nopay_price                                                   \n" +
                        ",shopping_cart_custom                                                \n" +
                        ",shopping_cart_price  ");

                FROM("(" + sql + ") b");

                if (params.get("pageModel") != null) {
                    PageModel pageModel = (PageModel) params.get("pageModel");
                    WHERE("  RowNumber BETWEEN   " + (pageModel.getFirstLimitParam()+1) + " and  " + (pageModel.getFirstLimitParam()+pageModel.getPageSize())+  " ");
                }

            }

        }.toString();



        sql2 +="   order by shengfen,chengshi,quyufl ,rq   ";



        return sql2;
    }
    // 动态查询总数量
    public String count(final Map<String, Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM("  report_b2b_data a(nolock)  ");
                if(params.get("ReportDay") != null){
                    ReportDay reportday = (ReportDay) params.get("ReportDay");


                    if(reportday.getBegin_date() != null && !reportday.getBegin_date().equals("")){
                        WHERE("  rq  between   '" +reportday.getBegin_date()+"'  and   '"+reportday.getEnd_date()+"'");

                    }

                    if(reportday.getShengfen() != null && !reportday.getShengfen().equals("")){
                        WHERE(" shengfen like  '%"+reportday.getShengfen()+"%'");

                    }



                    if(reportday.getChengshi() != null && !reportday.getChengshi().equals("")){
                        WHERE(" Chengshi='"+reportday.getChengshi()+"'");

                    }


                    if(reportday.getQuyufl() != null && !reportday.getQuyufl().equals("")){
                        WHERE(" Quyufl='"+reportday.getQuyufl()+"'");

                    }



                }
            }
        }.toString();





    }

}
