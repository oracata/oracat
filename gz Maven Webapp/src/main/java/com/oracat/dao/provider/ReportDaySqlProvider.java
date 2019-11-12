package com.oracat.dao.provider;

import com.oracat.model.ReportDay;
import com.oracat.util.tag.PageModel;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ReportDaySqlProvider {

    // 分页动态查询
    public String selectWhitParam(final Map<String, Object> params){
        final String sql =  new SQL(){
            {
                SELECT(   " ROW_NUMBER() OVER(Order by shengfen,chengshi,quyufl ) AS RowNumber" +
                        ",rq                                                                  \n" +
                        ",shengfen                                                            \n" +
                        ",chengshi                                                            \n" +
                        ",quyufl                                                              \n" +
                        ",custom_num                                                          \n" +
                        ",round((login_pay_custom+login_nopay_custom)*1.00*100/custom_num,2) login_rate \n" +
                        ",login_pay_custom                                                    \n" +
                        ",login_nopay_custom                                                  \n" +
                        ",order_pay_custom                                                    \n" +
                        ",order_pay_price                                                     \n" +
                        ",order_nopay_custom                                                  \n" +
                        ",order_nopay_price                                                   \n" +
                        ",shopping_cart_custom                                                \n" +
                        ",shopping_cart_price  ");

                FROM(" report_b2b_data a(nolock)  ");

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
                    WHERE("  RowNumber BETWEEN   " + pageModel.getFirstLimitParam() + " and  " + pageModel.getPageSize() + " ");
                }

            }

        }.toString();



        sql2 +="   order by shengfen,chengshi,quyufl    ";



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
