<%--
  Created by IntelliJ IDEA.
  User: oracat
  Date: 2019-11-11
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="java.util.*" %>
<%@page import="com.oracat.util.FusionCharts" %>
<%@page import="com.oracat.model.*" %>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

%>

<%
    //遍历List 取得记录数
    int num=0;
    //遍历List 取得记录数
    if(request.getAttribute("saleflow")!=null) {
        Object re = request.getAttribute("saleflow");
        List<SaleFlow> ol = (List) re;
        num = ol.size();
    }
%>

<%
    //遍历List 取得记录数
    int numerp=0;
    //遍历List 取得记录数
    if(request.getAttribute("saleflowerp")!=null) {
        Object re = request.getAttribute("saleflowerp");
        List<SaleFlow> ol = (List) re;
        numerp = ol.size();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <!--
<meta http-equiv="refresh" content="300" />
-->
    <title>FusionCharts | My First Chart</title>

    <!--
        <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>


        <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
    -->


    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>


    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>



    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/gumshoe/3.5.1/js/gumshoe.min.js"></script>

    <!-- GEO IP -->
    <script src="//js.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"></script>
    <script type="text/javascript" src="https://assets.calendly.com/assets/external/widget.js"></script>

    <!-- Mixpanel -->
    <script src="/public/js/mixpanel.js"></script>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="./css/pager.css" type="text/css" rel="stylesheet" />




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
                    showLoading();
                    return true;
                });

            }

        );

    </script>




</head>
<body>



<table><tr>


    <td>
        <!-- 秒杀饼图 -->

        <div id="chart2" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig2 = new HashMap<String, String>();
            chartConfig2.put("caption", "搜索命中率");
            chartConfig2.put("subcaption", "线上订单");
            chartConfig2.put("showvalues", "1");
            chartConfig2.put("numbersuffix", "%");
            chartConfig2.put("showpercentintooltip", "0");
            chartConfig2.put("enablemultislicing", "1");
            chartConfig2.put("basefontsize", "14");
            chartConfig2.put("theme", "fusion");
            chartConfig2.put("showNames", "1");
            chartConfig2.put("showPercentValues", "0");


            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Double> dataValuePair2 = new LinkedHashMap<String, Double>();


            //遍历List
            Object re2 = request.getAttribute("searchbingo");
            List<Search> ol2= (List)re2;
            for(int i=0;i<ol2.size();i++){
                Search      ov2 = ol2.get(i);




                dataValuePair2.put("" + ov2.getType()+ "", ov2.getValue());

            }
            StringBuilder jsonData2 = new StringBuilder();
            StringBuilder data4 = new StringBuilder();

            // json data to use as chart data source
            jsonData2.append("{'chart':{");
            for(Map.Entry conf1:chartConfig2.entrySet())
            {
                jsonData2.append("'" + conf1.getKey()+"':'"+conf1.getValue() + "',");
            }

            jsonData2.replace(jsonData2.length() - 1, jsonData2.length() ,"},\n");


            data4.append("'data':[");

            for(Map.Entry pair:dataValuePair2.entrySet())
            {
                data4.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");
            }
            data4.replace(data4.length() - 1, data4.length(),"]");

            jsonData2.append(data4.toString());
            jsonData2.append("}");






            System.out.println( jsonData2.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart2 = new FusionCharts(
                    "doughnut2d",
                    "first_chart2",
                    "700",
                    "400",
                    "chart2",
                    "json",
                    jsonData2.toString()
            );
        %>
        <%= firstChart2.render() %>


    </td>







</tr><tr>
    <td>
        <!-- 柱图 -->

        <div id="chart3" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig3 = new HashMap<String, String>();
            chartConfig3.put("caption", "搜索关键词排名");
            chartConfig3.put("yaxisname", "次数");
            chartConfig3.put("subcaption", "前5");
            chartConfig3.put("showhovereffect", "1");
            chartConfig3.put("numbersuffix", "次");
            chartConfig3.put("drawcrossline", "1");
            chartConfig3.put("basefontsize", "14");
            chartConfig3.put("showvalues", "1");
            chartConfig3.put("theme", "fusion");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Integer[]> dataValuePair3 = new LinkedHashMap<String, Integer[]>();


            //遍历List
            Object re3 = request.getAttribute("searchtop5");
            List<Search> ol3= (List)re3;
            for(int i=0;i<ol3.size();i++){
                Search      ov3 = ol3.get(i);


                Integer[] dt3 = { ov3.getN()};

                dataValuePair3.put("" + ov3.getSearch_key()+ "", dt3);

            }
            StringBuilder jsonData3 = new StringBuilder();
            StringBuilder data1 = new StringBuilder();

            // json data to use as chart data source
            jsonData3.append("{'chart':{");
            for(Map.Entry conf1:chartConfig3.entrySet())
            {
                jsonData3.append("'" + conf1.getKey()+"':'"+conf1.getValue() + "',");
            }

            jsonData3.replace(jsonData3.length() - 1, jsonData3.length() ,"},\n");



            data1.append("'categories':[  {                  \n" +
                    "     'category': [  \n");

            for(Map.Entry pair:dataValuePair3.entrySet())
            {
                data1.append("{'label':'" + pair.getKey() + "'},");
            }

            data1.replace(data1.length() - 1, data1.length(),"  ] } ],\n");

            data1.append("  'dataset': [\n" +
                    "    {\n" +
                    "      'seriesname': '关键词',\n" +
                    "      'data': [\n");




            for(Map.Entry pair1:dataValuePair3.entrySet())
            {
                Integer[]  val=(Integer[])pair1.getValue();
                data1.append("{'value':'" +val[0] +"'},");

            }




            data1.replace(data1.length() - 1, data1.length() ,"         ]  \n" +
                    "        }    \n" +
                    "    ]  ");





            jsonData3.append(data1.toString());
            jsonData3.append("}");

            System.out.println( jsonData3.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart3 = new FusionCharts(
                    "stackedcolumn2d",
                    "first_chart3",
                    "800",
                    "400",
                    "chart3",
                    "json",
                    jsonData3.toString()
            );
        %>
        <%= firstChart3.render() %>


    </td>

    <td>
        <!-- 柱图 -->

        <div id="chart5" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig5 = new HashMap<String, String>();
            chartConfig5.put("caption", "未命中关键词排名");
            chartConfig5.put("yaxisname", "");
            chartConfig5.put("subcaption", "前5");
            chartConfig5.put("showhovereffect", "1");
            chartConfig5.put("numbersuffix", "次");
            chartConfig5.put("drawcrossline", "1");
            chartConfig5.put("basefontsize", "14");
            chartConfig5.put("showvalues", "1");
            chartConfig5.put("theme", "fusion");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Integer[]> dataValuePair5 = new LinkedHashMap<String, Integer[]>();


            //遍历List
            Object re5 = request.getAttribute("searchno");
            List<Search> ol5= (List)re5;
            for(int i=0;i<ol5.size();i++){
                Search      ov5 = ol5.get(i);


                Integer[] dt5 = { ov5.getN()};

                dataValuePair5.put("" + ov5.getSearch_key()+ "", dt5);

            }
            StringBuilder jsonData5 = new StringBuilder();
            StringBuilder data7 = new StringBuilder();

            // json data to use as chart data source
            jsonData5.append("{'chart':{");
            for(Map.Entry conf7:chartConfig5.entrySet())
            {
                jsonData5.append("'" + conf7.getKey()+"':'"+conf7.getValue() + "',");
            }

            jsonData5.replace(jsonData5.length() - 1, jsonData5.length() ,"},\n");



            data7.append("'categories':[  {                  \n" +
                    "     'category': [  \n");

            for(Map.Entry pair:dataValuePair5.entrySet())
            {
                data7.append("{'label':'" + pair.getKey() + "'},");
            }

            data7.replace(data7.length() - 1, data7.length(),"  ] } ],\n");

            data7.append("  'dataset': [\n" +
                    "    {\n" +
                    "      'seriesname': '关键词',\n" +
                    "      'data': [\n");




            for(Map.Entry pair7:dataValuePair5.entrySet())
            {
                Integer[]  val=(Integer[])pair7.getValue();
                data7.append("{'value':'" +val[0] +"'},");

            }




            data7.replace(data7.length() - 1, data7.length() ,"         ]  \n" +
                    "        }    \n" +
                    "    ]  ");





            jsonData5.append(data7.toString());
            jsonData5.append("}");

            System.out.println( jsonData5.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart5 = new FusionCharts(
                    "stackedcolumn2d",
                    "first_chart5",
                    "800",
                    "400",
                    "chart5",
                    "json",
                    jsonData5.toString()
            );
        %>
        <%= firstChart5.render() %>


    </td>


</tr></table>

<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
    <tr>
        <td class="fftd">
            <form name="dcform" method="post" id="form" action="khjsearch.do">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="font3">
                            开始日期：<input type="text" id="begin_date" name="begin_date" value="${search_con.begin_date}"  />
                            结束日期：<input type="text" id="end_date"   name="end_date" value="${search_con.end_date}" />
                            <input type="submit" value="查询"/>

                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
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




</body>
</html>
