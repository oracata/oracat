<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.oracat.model.ReportMonth" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oracat.util.FusionCharts" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>日报</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="./css/css.css" type="text/css" rel="stylesheet" />




    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


    <link href="./css/pager.css" type="text/css" rel="stylesheet" />

    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>


    <script  >


        //在页面未加载完毕之前显示的loading Html自定义内容
        var _LoadingHtml = '<div id="loadingDiv" style="display: none; "><div id="over" style=" position: absolute;top: 0;left: 0; width: 100%;height: 100%; background-color: #f5f5f5;opacity:0.5;z-index: 1000;"></div><div id="layout" style="position: absolute;top: 40%; left: 40%;width: 20%; height: 20%;  z-index: 1001;text-align:center;"><img src="./images/timg.gif" /></div></div>';
        //呈现loading效果
        document.write(_LoadingHtml);

        //移除loading效果
        function completeLoading() {
            document.getElementById("loadingDiv").style.display="none";
        }
        //展示loading效果http://localhost:2006/
        function showLoading()
        {
            document.getElementById("loadingDiv").style.display="block";
        }



        $(document).ready(function(){
                //任选以form结束的form进行处理
                $("form[id$='form']").submit(function(){
                    showLoading();
                    return true;
                });

                //导出excel
                $("a[id$='export']").click(function(){

                    var content1 = document.getElementById("begin_date");
                    var content2 = document.getElementById("end_date");


                    window.location.href="exportreportmonth?"+"begin_date="+content1.value+"&end_date="+content2.value;

                    showLoading();
                    return true;
                });

            }

        );

    </script>








    <script  >
        $(function () {
            $("#begin_date").datepicker();
            $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
        });
        jQuery(function ($) {
            $.datepicker.regional['zh-CN'] = {
                closeText: '关闭',
                prevText: '<上月',
                nextText: '下月>',
                currentText: '今天',
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
                monthNamesShort: ['一', '二', '三', '四', '五', '六',
                    '七', '八', '九', '十', '十一', '十二'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                weekHeader: '周',
                dateFormat: 'yy-mm-dd',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: true,
                yearSuffix: '年'
            };
            $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
        });
    </script>


    <script >
        $(function () {
            $("#end_date").datepicker();
            $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
        });
        jQuery(function ($) {
            $.datepicker.regional['zh-CN'] = {
                closeText: '关闭',
                prevText: '<上月',
                nextText: '下月>',
                currentText: '今天',
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
                monthNamesShort: ['一', '二', '三', '四', '五', '六',
                    '七', '八', '九', '十', '十一', '十二'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                weekHeader: '周',
                dateFormat: 'yy-mm-dd',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: true,
                yearSuffix: '年'
            };
            $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
        });
    </script>







</head>
<body   >
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"></td>
        <td class="main_locbg font2">&nbsp;&nbsp;&nbsp;当前位置：报表 &gt; 月报</td>
        <td width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <td height="30">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr>
                    <td class="fftd">
                        <form name="reportmonthform" method="post" id="form" action="reportmonth.do">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">

                                <tr>
                                    <td class="font3">
                                        开始日期：<input type="text" id="begin_date" name="begin_date" value="${reportmonth_con.begin_date}"  />
                                        结束日期：<input type="text" id="end_date"   name="end_date" value="${reportmonth_con.end_date}" />

                                        <input type="submit" value="查询"/>

                                    </td>

                                    <td class="font3">

                                        <a  id="export">导出excel</a>


                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>


    <!-- 图表展示区 -->
    <tr><td>
        <div id="chart" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig = new HashMap<String, String>();
            chartConfig.put("caption", "月报");
            chartConfig.put("subcaption", "各月数据比较");
            chartConfig.put("showvalues", "0");
            chartConfig.put("labeldisplay", "ROTATE");
            chartConfig.put("rotatelabels", "1");
            chartConfig.put("plothighlighteffect", "fadeout");
            chartConfig.put("plottooltext", "$seriesName in $label : <b>$dataValue</b>");
            chartConfig.put("theme", "fusion");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Double[]> dataValuePair = new LinkedHashMap<String, Double[]>();


            //遍历List
            Object re = request.getAttribute("reportmonth");
            List<ReportMonth> ol= (List)re;
            for(int i=0;i<ol.size();i++){
                ReportMonth      ov = ol.get(i);

                if(!("合计".equals(ov.getRq()))) {
                    Double[] dt = {ov.getHsje(), ov.getCankmll()};

                    dataValuePair.put("" + ov.getRq() + "", dt);
                }
            }
            StringBuilder jsonData = new StringBuilder();
            StringBuilder data = new StringBuilder();

            // json data to use as chart data source
            jsonData.append("{'chart':{");
            for(Map.Entry conf:chartConfig.entrySet())
            {
                jsonData.append("'" + conf.getKey()+"':'"+conf.getValue() + "',");
            }

            jsonData.replace(jsonData.length() - 1, jsonData.length() ,"},");

            // build  categories

            data.append("'axis':[  {                  \n" +
                    "     'title': \"含税金额\",\n" +
                    "      'titlepos': \"left\",\n" +
                    "      'numberprefix': \"￥\",\n" +
                    "      'divlineisdashed': \"1\",\n" +
                    "      'maxvalue': \"200000\",\n" +
                    "      'dataset': [ " +
                    " {\n" +
                    "          'seriesname': \"含税金额\",\n" +
                    "          'linethickness': \"3\",\n" +
                    "          'data': [ \n");



            for(Map.Entry pair:dataValuePair.entrySet())
            {
                Double[]  val=(Double[])pair.getValue();
                data.append("{'value':'" +val[0] +"'},");

            }
            data.replace(data.length() - 1, data.length(),"] }  ] },");




            data.append("{                  \n" +
                    "     'title': \"毛利率\",\n" +
                    "      'titlepos': \"RIGHT\",\n" +
                    "      'numberprefix': \"%\",\n" +
                    "     'axisonleft': \"0\",\n" +
                    "      'numdivlines': \"5\","+
                    "      'divlineisdashed': \"1\",\n" +
                    "      'maxvalue': \"5\",\n" +
                    "      'dataset': [ " +
                    " {\n" +
                    "          'seriesname': \"毛利率\",\n" +
                    "          'linethickness': \"3\",\n" +
                    "          'data': [ \n");



            for(Map.Entry pair:dataValuePair.entrySet())
            {
                Double[]  val=(Double[])pair.getValue();
                data.append("{'value':'" +val[1] +"'},");

            }
            data.replace(data.length() - 1, data.length(),"]   }    ]\n" +
                    "    }\n" +
                    "  ],");

            data.append("'categories':[  {                  \n" +
                    "      \"category\": [  \n");

            for(Map.Entry pair:dataValuePair.entrySet())
            {
                data.append("{'label':'" + pair.getKey() + "'},");
            }

            data.replace(data.length() - 1, data.length() ,"         ]  \n" +
                    "        }    \n" +
                    "    ]  ");


            jsonData.append(data.toString());
            jsonData.append("}");

            System.out.println( jsonData.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart = new FusionCharts(
                    "multiaxisline",
                    "first_chart",
                    "1024",
                    "700",
                    "chart",
                    "json",
                    jsonData.toString()
            );
        %>
        <%= firstChart.render() %>

    </td></tr>


    <!-- 数据展示区 -->
    <tr valign="top">
        <td height="20">
            <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr class="main_trbg_tit" align="center">

                    <td>日期</td>
                    <td>含税金额</td>
                    <td>参考毛利</td>
                    <td>参考毛利率</td>
                    <td>电商客户数          </td>
                    <td>登录客户数           </td>
                    <td>登录率           </td>
                    <td>支付客户数     </td>
                    <td>未支付客户数   </td>
                    <td>未支付金额    </td>
                    <td>购物车客户数       </td>
                    <td>购物车金额         </td>


                </tr>
                <c:forEach items="${requestScope.reportmonth}" var="reportmonth" varStatus="stat">
                    <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">

                        <td>${reportmonth.rq                }</td>
                        <td>${reportmonth.hsje              }</td>
                        <td>${reportmonth.cankml            }</td>
                        <td>${reportmonth.cankmll           }</td>
                        <td>${reportmonth.cust_num          }</td>
                        <td>${reportmonth.login_num         }</td>
                        <td>${String.format("%.2f",reportmonth.login_num*100/reportmonth.cust_num)       }%</td>
                        <td>${reportmonth.pay_cust          }</td>
                        <td>${reportmonth.not_pay_cust      }</td>
                        <td>${reportmonth.not_pay           }</td>
                        <td>${reportmonth.cart_cust         }</td>
                        <td>${reportmonth.cart_price        }</td>



                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>


</table>
<div style="height:10px;"></div>
</body>
</html>