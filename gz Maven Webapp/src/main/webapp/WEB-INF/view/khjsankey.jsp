<%--
  Created by IntelliJ IDEA.
  User: oracat
  Date: 2019-11-11
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.oracat.util.FusionCharts" %>
<%@page import="com.oracat.model.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <!--
    <meta http-equiv="refresh" content="300" />
-->
    <title>FusionCharts | My First Chart</title>








    <script src="https://cdn.fusioncharts.com/fusioncharts/fusioncharts.powercharts.js"></script>


        <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>


        <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>




    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="./css/pager.css" type="text/css" rel="stylesheet" />







</head>
<body>

        <div id="chart5"></div>


        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig = new HashMap<String, String>();
            chartConfig.put("caption", "订单流程效率");
            chartConfig.put("subcaption", "线上线下");
            chartConfig.put("showvalues", "1");
            chartConfig.put("orientation", "horizontal");
            chartConfig.put("linkalpha", "30");
            chartConfig.put("linkhoveralpha", "60");
            chartConfig.put("nodelabelposition", "start");

            chartConfig.put("numbersuffix", "元");

            chartConfig.put("showpercentintooltip", "1");

            chartConfig.put("formatNumberScale", "0");
            chartConfig.put("basefontsize", "18");


            chartConfig.put("showNames", "1");
            chartConfig.put("showPercentValues", "0");

            chartConfig.put("theme", "fusion");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Object[]> dataValuePair = new LinkedHashMap<String, Object[]>();

            Map<String, Integer> dataValuePair3 = new LinkedHashMap<String,Integer>();

            //遍历List
            Object re = request.getAttribute("flow");
            List<Flow> ol= (List)re;
            for(int i=0;i<ol.size();i++){
                Flow      ov = ol.get(i);


                Object[] dt = { ov.getBegin_name(),ov.getEnd_name(),ov.getValue()};

                dataValuePair.put("" + i + "", dt);

                dataValuePair3.put("" + ov.getBegin_name() + "",0);
                dataValuePair3.put("" + ov.getEnd_name() + "",0);


            }
            StringBuilder jsonData5 = new StringBuilder();
            StringBuilder data7 = new StringBuilder();

            // json data to use as chart data source
            jsonData5.append("{'chart':{");
            for(Map.Entry conf7:chartConfig.entrySet())
            {
                jsonData5.append("'" + conf7.getKey()+"':'"+conf7.getValue() + "',");
            }

            jsonData5.replace(jsonData5.length() - 1, jsonData5.length() ,"},\n");



            data7.append(  "'nodes': [ \n");


            for(Map.Entry pair:dataValuePair3.entrySet())
            {
                data7.append("{'label':'" + pair.getKey() + "'},");
            }




            data7.replace(data7.length() - 1, data7.length(),"   ],\n");

            data7.append(" 'links': [\n");




            for(Map.Entry pair7:dataValuePair.entrySet())
            {
                Object[]  val=(Object[])pair7.getValue();
                data7.append(" {\n" +
                        "        'from': '" + (String)val[0] + "',\n" +
                        "        'to':'" + (String)val[1]  + "',\n" +
                        "        'value': '" + (Double)val[2]  + "'\n" +
                        "        },");

            }




            data7.replace(data7.length() - 1, data7.length() ,"         ]  \n");





            jsonData5.append(data7.toString());
            jsonData5.append("}");

            System.out.println( jsonData5.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart5 = new FusionCharts(
                    "sankey",
                    "first_chart5",
                    "1300",
                    "700",
                    "chart5",
                    "json",
                    jsonData5.toString()
            );
        %>
        <%= firstChart5.render() %>



<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
    <tr>
        <td class="fftd">
            <form name="dcform" method="post" id="form" action="khjsankey.do">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="font3">
                            开始日期：<input type="text" id="begin_date" name="begin_date" value="${flow_con.begin_date}"  />
                            结束日期：<input type="text" id="end_date"   name="end_date" value="${flow_con.end_date}" />
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
