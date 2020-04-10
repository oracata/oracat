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
    <meta http-equiv="refresh" content="300" />

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
        <!-- 饼图 -->

        <div id="chart2" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig2 = new HashMap<String, String>();
            chartConfig2.put("caption", "商品状态");
            chartConfig2.put("subcaption", "");
            chartConfig2.put("showvalues", "1");
            chartConfig2.put("numbersuffix", "个");
            chartConfig2.put("showpercentintooltip", "0");
         //   chartConfig2.put("enablemultislicing", "1");
            chartConfig2.put("formatNumberScale", "0");
            chartConfig2.put("basefontsize", "18");
            chartConfig2.put("theme", "fusion");





            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Integer> dataValuePair2 = new LinkedHashMap<String, Integer>();


            //遍历List
            Object re2 = request.getAttribute("spml");
            List<Spml> ol2= (List)re2;
            for(int i=0;i<ol2.size();i++){
                Spml      ov2 = ol2.get(i);




                dataValuePair2.put("" + ov2.getType() + "", ov2.getNum());

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
                    "800",
                    "600",
                    "chart2",
                    "json",
                    jsonData2.toString()
            );
        %>
        <%= firstChart2.render() %>


    </td>








    <td>
        <!-- 饼图 -->

        <div id="chart6" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig6 = new HashMap<String, String>();
            chartConfig6.put("caption", "缺项商品");
            chartConfig6.put("subcaption", "");
            chartConfig6.put("showvalues", "1");
            chartConfig6.put("numbersuffix", "个");
            chartConfig6.put("showpercentintooltip", "0");
            //   chartConfig6.put("enablemultislicing", "1");
            chartConfig6.put("formatNumberScale", "0");
            chartConfig6.put("basefontsize", "18");
            chartConfig6.put("theme", "fusion");





            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Integer> dataValuePair6 = new LinkedHashMap<String, Integer>();


            //遍历List
            Object re6 = request.getAttribute("spmlmiss");
            List<Spml> ol6= (List)re6;
            for(int i=0;i<ol6.size();i++){
                Spml      ov6 = ol6.get(i);




                dataValuePair6.put("" + ov6.getType() + "", ov6.getNum());

            }
            StringBuilder jsonData6 = new StringBuilder();
            StringBuilder data8 = new StringBuilder();

            // json data to use as chart data source
            jsonData6.append("{'chart':{");
            for(Map.Entry conf1:chartConfig6.entrySet())
            {
                jsonData6.append("'" + conf1.getKey()+"':'"+conf1.getValue() + "',");
            }

            jsonData6.replace(jsonData6.length() - 1, jsonData6.length() ,"},\n");


            data8.append("'data':[");

            for(Map.Entry pair:dataValuePair6.entrySet())
            {
                data8.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");
            }
            data8.replace(data8.length() - 1, data8.length(),"]");

            jsonData6.append(data8.toString());
            jsonData6.append("}");






            System.out.println( jsonData6.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart6 = new FusionCharts(
                    "doughnut2d",
                    "first_chart6",
                    "800",
                    "600",
                    "chart6",
                    "json",
                    jsonData6.toString()
            );
        %>
        <%= firstChart6.render() %>


    </td>

</tr>
<tr>
    <td>
        <!-- 饼图 -->

        <div id="chart3" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig3 = new HashMap<String, String>();
            chartConfig3.put("caption", "未上架有库存");
            chartConfig3.put("subcaption", "");
            chartConfig3.put("showvalues", "1");
            chartConfig3.put("numbersuffix", "个");
            chartConfig3.put("showpercentintooltip", "0");
            //   chartConfig3.put("enablemultislicing", "1");
            chartConfig3.put("formatNumberScale", "0");
            chartConfig3.put("basefontsize", "15");
            chartConfig3.put("theme", "fusion");





            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Integer> dataValuePair3 = new LinkedHashMap<String, Integer>();


            //遍历List
            Object re3 = request.getAttribute("spmlstock");
            List<Spml> ol3= (List)re3;
            for(int i=0;i<ol3.size();i++){
                Spml      ov3 = ol3.get(i);




                dataValuePair3.put("" + ov3.getType() + "", ov3.getNum());

            }
            StringBuilder jsonData3 = new StringBuilder();
            StringBuilder data5 = new StringBuilder();

            // json data to use as chart data source
            jsonData3.append("{'chart':{");
            for(Map.Entry conf1:chartConfig3.entrySet())
            {
                jsonData3.append("'" + conf1.getKey()+"':'"+conf1.getValue() + "',");
            }

            jsonData3.replace(jsonData3.length() - 1, jsonData3.length() ,"},\n");


            data5.append("'data':[");

            for(Map.Entry pair:dataValuePair3.entrySet())
            {
                data5.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");
            }
            data5.replace(data5.length() - 1, data5.length(),"]");

            jsonData3.append(data5.toString());
            jsonData3.append("}");






            System.out.println( jsonData3.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart3 = new FusionCharts(
                    "doughnut2d",
                    "first_chart3",
                    "800",
                    "600",
                    "chart3",
                    "json",
                    jsonData3.toString()
            );
        %>
        <%= firstChart3.render() %>


    </td>




    <td>
        <!-- 饼图 -->

        <div id="chart7" align="center"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig7 = new HashMap<String, String>();
            chartConfig7.put("caption", "未上加无库存");
            chartConfig7.put("subcaption", "");
            chartConfig7.put("showvalues", "1");
            chartConfig7.put("numbersuffix", "个");
            chartConfig7.put("showpercentintooltip", "0");
            //   chartConfig7.put("enablemultislicing", "1");
            chartConfig7.put("formatNumberScale", "0");
            chartConfig7.put("basefontsize", "19");
            chartConfig7.put("theme", "fusion");





            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Integer> dataValuePair7 = new LinkedHashMap<String, Integer>();


            //遍历List
            Object re7 = request.getAttribute("spmlnostock");
            List<Spml> ol7= (List)re7;
            for(int i=0;i<ol7.size();i++){
                Spml      ov7 = ol7.get(i);




                dataValuePair7.put("" + ov7.getType() + "", ov7.getNum());

            }
            StringBuilder jsonData7 = new StringBuilder();
            StringBuilder data9 = new StringBuilder();

            // json data to use as chart data source
            jsonData7.append("{'chart':{");
            for(Map.Entry conf1:chartConfig7.entrySet())
            {
                jsonData7.append("'" + conf1.getKey()+"':'"+conf1.getValue() + "',");
            }

            jsonData7.replace(jsonData7.length() - 1, jsonData7.length() ,"},\n");


            data9.append("'data':[");

            for(Map.Entry pair:dataValuePair7.entrySet())
            {
                data9.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");
            }
            data9.replace(data9.length() - 1, data9.length(),"]");

            jsonData7.append(data9.toString());
            jsonData7.append("}");






            System.out.println( jsonData7.toString());


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart7 = new FusionCharts(
                    "doughnut2d",
                    "first_chart7",
                    "800",
                    "600",
                    "chart7",
                    "json",
                    jsonData7.toString()
            );
        %>
        <%= firstChart7.render() %>


    </td>

</tr>

</table>






</body>
</html>
