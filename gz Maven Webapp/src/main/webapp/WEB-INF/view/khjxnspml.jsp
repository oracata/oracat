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
            chartConfig2.put("caption", "上架商品状态");
            chartConfig2.put("subcaption", "");
            chartConfig2.put("showvalues", "1");
            chartConfig2.put("numbersuffix", "个");
            chartConfig2.put("showpercentintooltip", "1");
            //   chartConfig2.put("enablemultislicing", "1");
            chartConfig2.put("formatNumberScale", "0");
            chartConfig2.put("basefontsize", "18");
            chartConfig2.put("theme", "fusion");

            chartConfig2.put("showNames", "1");
            chartConfig2.put("showPercentValues", "0");


            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Integer> dataValuePair2 = new LinkedHashMap<String, Integer>();


            //遍历List
            Object re2 = request.getAttribute("xnspml");
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






    </tr>

</table>






</body>
</html>
