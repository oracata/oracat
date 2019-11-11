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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="refresh" content="60" />

    <title>FusionCharts | My First Chart</title>


    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>


    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
</head>
<body>



<!-- 柱图 -->

<div id="chart"></div>
<%
    // store chart config name-config value pair
    Map<String, String> chartConfig = new HashMap<String, String>();
    chartConfig.put("caption", "实时订单总金额");
    chartConfig.put("subCaption", "");
    chartConfig.put("xAxisName", "时间");
    chartConfig.put("yAxisName", "下单总金额");
    chartConfig.put("formatNumberScale", "0");
    chartConfig.put("numberSuffix", "");
    chartConfig.put("theme", "fusion");
    chartConfig.put("labelStep", "5");


    //store label-value pair
    //LinkedHashMap 保证数据顺序
    Map<String, Double> dataValuePair = new LinkedHashMap<String, Double>();


    //遍历List
    Object re = request.getAttribute("realtime");
    List<RealTime> ol= (List)re;
    for(int i=0;i<ol.size();i++){
        RealTime      ov = ol.get(i);


        dataValuePair.put(""+ov.getRq().substring(11,21)+"", ov.getOrder_pay_price());

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

    // build  data object from label-value pair
    data.append("'data':[");

    for(Map.Entry pair:dataValuePair.entrySet())
    {

        data.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");

    }
    data.replace(data.length() - 1, data.length(),"]");

    jsonData.append(data.toString());
    jsonData.append("}");


    // Create chart instance
    // charttype, chartID, width, height,containerid, data format, data
    FusionCharts firstChart = new FusionCharts(
            "area2d",
            "first_chart",
            "700",
            "400",
            "chart",
            "json",
            jsonData.toString()
    );
%>
<%= firstChart.render() %>


<!--分割线**************************** -->


<!-- 柱图 -->

<div id="chart2"></div>
<%
    // store chart config name-config value pair
    Map<String, String> chartConfig2 = new HashMap<String, String>();
    chartConfig2.put("caption", "实时登录客户数");
    chartConfig2.put("subCaption", "");
    chartConfig2.put("xAxisName", "时间");
    chartConfig2.put("yAxisName", "登录客户数");
    chartConfig2.put("formatNumberScale", "0");
    chartConfig2.put("numberSuffix", "");
    chartConfig2.put("theme", "fusion");
    chartConfig2.put("labelStep", "5");


    //store label-value pair
    //LinkedHashMap 保证数据顺序
    Map<String, Double> dataValuePair2 = new LinkedHashMap<String, Double>();


    //遍历List
    Object re2 = request.getAttribute("realtime");
    List<RealTime> ol2= (List)re2;
    for(int i=0;i<ol2.size();i++){
        RealTime      ov2 = ol2.get(i);


        dataValuePair2.put(""+ov2.getRq().substring(11,21)+"", (double) (ov2.getLogin_nopay_custom()+ov2.getLogin_pay_custom()));

    }
    StringBuilder jsonData2 = new StringBuilder();
    StringBuilder data2 = new StringBuilder();

    // json data to use as chart data source
    jsonData2.append("{'chart':{");
    for(Map.Entry conf2:chartConfig2.entrySet())
    {
        jsonData2.append("'" + conf2.getKey()+"':'"+conf2.getValue() + "',");
    }

    jsonData2.replace(jsonData2.length() - 1, jsonData2.length() ,"},");

    // build  data object from label-value pair
    data2.append("'data':[");

    for(Map.Entry pair2:dataValuePair2.entrySet())
    {

        data2.append("{'label':'" + pair2.getKey() + "','value':'" + pair2.getValue() +"'},");

    }
    data2.replace(data2.length() - 1, data2.length(),"]");

    jsonData2.append(data2.toString());
    jsonData2.append("}");


    // Create chart instance
    // charttype, chartID, width, height,containerid, data format, data
    FusionCharts firstChart2 = new FusionCharts(
            "spline",
            "first_chart2",
            "700",
            "400",
            "chart2",
            "json",
            jsonData2.toString()
    );
%>
<%= firstChart2.render() %>







</body>
</html>
