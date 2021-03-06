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








<table align="center">
    <tr>

        <td>
            <div id="chart5"  ></div>
            <%
                // store chart config name-config value pair
                Map<String, String> chartConfig = new HashMap<String, String>();
                chartConfig.put("caption", "新增客户");
                chartConfig.put("yaxisname", "");
                chartConfig.put("subcaption", "近15天");
                chartConfig.put("showhovereffect", "1");
                chartConfig.put("numbersuffix", "");
                chartConfig.put("drawcrossline", "1");
                chartConfig.put("plottooltext", " $seriesName : <b>$dataValue</b>");
                chartConfig.put("theme", "fusion");



                //store label-value pair
                //LinkedHashMap 保证数据顺序
                Map<String, Integer[]> dataValuePair = new LinkedHashMap<String, Integer[]>();


                //遍历List
                Object re = request.getAttribute("custadd");
                List<Cust> ol= (List)re;
                for(int i=0;i<ol.size();i++){
                    Cust      ov = ol.get(i);


                    Integer[] dt = {ov.getNum()};

                    dataValuePair.put("" + ov.getRq() + "", dt);

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



                data7.append("'categories':[  {                  \n" +
                        "     'category': [  \n");

                for(Map.Entry pair:dataValuePair.entrySet())
                {
                    data7.append("{'label':'" + pair.getKey() + "'},");
                }

                data7.replace(data7.length() - 1, data7.length(),"  ] } ],\n");

                data7.append("  'dataset': [\n" +
                        "    {\n" +
                        "      'seriesname': '新增客户',\n" +
                        "      'data': [\n");




                for(Map.Entry pair7:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair7.getValue();
                    data7.append("{'value':'" +val[0] +"'},");

                }


    /*

                data7.replace(data7.length() - 1, data7.length(),"] },");




                data7.append(" {\n" +
                        "      'seriesname': '登录下单支付',\n" +
                        "      'data': [\n");

                for(Map.Entry pair7:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair7.getValue();
                    data7.append("{'value':'" +val[1] +"'},");

                }

                /*
                data7.replace(data7.length() - 1, data7.length(),"] },");


                data7.append(" {\n" +
                        "      'seriesname': '登录未下单支付',\n" +
                        "      'data': [\n");

                for(Map.Entry pair7:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair7.getValue();
                    data7.append("{'value':'" +val[2] +"'},");

                }

           */
                data7.replace(data7.length() - 1, data7.length() ,"         ]  \n" +
                        "        }    \n" +
                        "    ]  ");





                jsonData5.append(data7.toString());
                jsonData5.append("}");

                System.out.println( jsonData5.toString());


                // Create chart instance
                // charttype, chartID, width, height,containerid, data format, data
                FusionCharts firstChart5 = new FusionCharts(
                        "msline",
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
     <td>
    <div id="chart2"  ></div>
    <%
        // store chart config name-config value pair
        Map<String, String> chartConfig2 = new HashMap<String, String>();
        chartConfig2.put("caption", "总客户数");
        chartConfig2.put("yaxisname", "");
        chartConfig2.put("subcaption", "近15天");
        chartConfig2.put("showhovereffect", "1");
        chartConfig2.put("numbersuffix", "");
        chartConfig2.put("drawcrossline", "1");
        chartConfig2.put("plottooltext", " $seriesName : <b>$dataValue</b>");
        chartConfig2.put("theme", "fusion");



        //store label-value pair
        //LinkedHashMap 保证数据顺序
        Map<String, Integer[]> dataValuePair2 = new LinkedHashMap<String, Integer[]>();


        //遍历List
        Object re2 = request.getAttribute("custadd");
        List<Cust> ol2= (List)re2;
        for(int i=0;i<ol2.size();i++){
            Cust      ov2 = ol2.get(i);


            Integer[] dt2 = {ov2.getAllnum()};

            dataValuePair2.put("" + ov2.getRq() + "", dt2);

        }
        StringBuilder jsonData2 = new StringBuilder();
        StringBuilder data4 = new StringBuilder();

        // json data to use as chart data source
        jsonData2.append("{'chart':{");
        for(Map.Entry conf4:chartConfig2.entrySet())
        {
            jsonData2.append("'" + conf4.getKey()+"':'"+conf4.getValue() + "',");
        }

        jsonData2.replace(jsonData2.length() - 1, jsonData2.length() ,"},\n");



        data4.append("'categories':[  {                  \n" +
                "     'category': [  \n");

        for(Map.Entry pair:dataValuePair2.entrySet())
        {
            data4.append("{'label':'" + pair.getKey() + "'},");
        }

        data4.replace(data4.length() - 1, data4.length(),"  ] } ],\n");

        data4.append("  'dataset': [\n" +
                "    {\n" +
                "      'seriesname': '客户数',\n" +
                "      'data': [\n");




        for(Map.Entry pair4:dataValuePair2.entrySet())
        {
            Integer[]  val=(Integer[])pair4.getValue();
            data4.append("{'value':'" +val[0] +"'},");

        }


    /*

                data4.replace(data4.length() - 1, data4.length(),"] },");




                data4.append(" {\n" +
                        "      'seriesname': '登录下单支付',\n" +
                        "      'data': [\n");

                for(Map.Entry pair4:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair4.getValue();
                    data4.append("{'value':'" +val[1] +"'},");

                }

                /*
                data4.replace(data4.length() - 1, data4.length(),"] },");


                data4.append(" {\n" +
                        "      'seriesname': '登录未下单支付',\n" +
                        "      'data': [\n");

                for(Map.Entry pair4:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair4.getValue();
                    data4.append("{'value':'" +val[2] +"'},");

                }

           */
        data4.replace(data4.length() - 1, data4.length() ,"         ]  \n" +
                "        }    \n" +
                "    ]  ");





        jsonData2.append(data4.toString());
        jsonData2.append("}");

        System.out.println( jsonData2.toString());


        // Create chart instance
        // charttype, chartID, width, height,containerid, data format, data
        FusionCharts firstChart2 = new FusionCharts(
                "msline",
                "first_chart2",
                "800",
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
            chartConfig3.put("caption", "客户订单分析");
            chartConfig3.put("yaxisname", "订单金额");
            chartConfig3.put("subcaption", "");
            chartConfig3.put("showhovereffect", "1");
            chartConfig3.put("numbersuffix", "元");
            chartConfig3.put("drawcrossline", "1");
            chartConfig3.put("basefontsize", "14");
            chartConfig3.put("showvalues", "1");
            chartConfig3.put("theme", "fusion");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Double[]> dataValuePair3 = new LinkedHashMap<String, Double[]>();


            //遍历List
            Object re3 = request.getAttribute("custaddorder");
            List<Cust> ol3= (List)re3;
            for(int i=0;i<ol3.size();i++){
                Cust      ov3 = ol3.get(i);


                Double[] dt3 = { ov3.getValue()};

                dataValuePair3.put("" + ov3.getType()+ "", dt3);

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
                    "      'seriesname': '线上',\n" +
                    "      'data': [\n");




            for(Map.Entry pair1:dataValuePair3.entrySet())
            {
                Double[]  val=(Double[])pair1.getValue();
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
                    "1000",
                    "500",
                    "chart3",
                    "json",
                    jsonData3.toString()
            );
        %>
        <%= firstChart3.render() %>


    </td>


</tr>









</table>







</body>
</html>
