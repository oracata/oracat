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


    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>

    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.maps.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/maps/fusioncharts.yunnan.js"></script>



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





<!-- 全省图 -->
<table align="center">


    <tr><td >

        <div id="chart-container" >

        </div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig3 = new HashMap<String, String>();
            chartConfig3.put("showborder", "0");
            chartConfig3.put("theme", "fusion");
            chartConfig3.put("caption", "全省登录率分布");
            chartConfig3.put("showlabels", "1");
            chartConfig3.put("includevalueinlabels", "1");
            chartConfig3.put("numbersuffix", "%");
            chartConfig3.put("nullentitycolor", "#4FCAF6");
            chartConfig3.put("legendcaption", "登录率%");
            chartConfig3.put("entitytooltext", "$lname {br} 登录率: $datavalue");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Double> dataValuePair3 = new LinkedHashMap<String, Double>();


            //遍历List
            Object re3 = request.getAttribute("activearea");
            List<RealTime> ol3= (List)re3;
            for(int i=0;i<ol3.size();i++){
                RealTime      ov3 = ol3.get(i);


                if ("曲靖".equals(ov3.getChengshi()))        { dataValuePair3.put("2", ov3.getLogin_rate());
                } else if ("昆明".equals(ov3.getChengshi())) { dataValuePair3.put("1", ov3.getLogin_rate());
                } else if ("昭通".equals(ov3.getChengshi())) { dataValuePair3.put("5", ov3.getLogin_rate());
                } else if ("楚雄".equals(ov3.getChengshi())) { dataValuePair3.put("13", ov3.getLogin_rate());
                } else if ("临沧".equals(ov3.getChengshi())) { dataValuePair3.put("8", ov3.getLogin_rate());
                } else if ("版纳".equals(ov3.getChengshi())) { dataValuePair3.put("16", ov3.getLogin_rate());
                } else if ("普洱".equals(ov3.getChengshi())) { dataValuePair3.put("7", ov3.getLogin_rate());
                } else if ("保山".equals(ov3.getChengshi())) { dataValuePair3.put("4", ov3.getLogin_rate());
                } else if ("红河".equals(ov3.getChengshi())) { dataValuePair3.put("14", ov3.getLogin_rate());
                } else if ("玉溪".equals(ov3.getChengshi())) { dataValuePair3.put("3", ov3.getLogin_rate());
                } else if ("文山".equals(ov3.getChengshi())) { dataValuePair3.put("15", ov3.getLogin_rate());
                } else if ("大理".equals(ov3.getChengshi())) { dataValuePair3.put("12", ov3.getLogin_rate());
                } else if ("丽江".equals(ov3.getChengshi())) { dataValuePair3.put("6", ov3.getLogin_rate());
                } else if ("德宏".equals(ov3.getChengshi())) { dataValuePair3.put("9", ov3.getLogin_rate());
                } else if ("怒江".equals(ov3.getChengshi())) { dataValuePair3.put("10", ov3.getLogin_rate());
                } else if ("迪庆".equals(ov3.getChengshi())) { dataValuePair3.put("11", ov3.getLogin_rate());
                }



            }
            StringBuilder jsonData3 = new StringBuilder();
            StringBuilder data3 = new StringBuilder();
            StringBuilder data4 = new StringBuilder();

            // json data to use as chart data source
            jsonData3.append("{'chart':{");
            for(Map.Entry conf3:chartConfig3.entrySet())
            {
                jsonData3.append("'" + conf3.getKey()+"':'"+conf3.getValue() + "',");
            }

            jsonData3.replace(jsonData3.length() - 1, jsonData3.length() ,"},");

            data4.append("  'colorrange': {\n" +
                    "    'gradient': '0',\n" +
                    "    'color': [" +

                    "      {                                   \n" +
                    "        'minvalue': '0',               \n" +
                    "        'maxvalue': '0',              \n" +
                    "        'displayvalue': '0', \n" +
                    "        'code': '#4FCAF6'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '0.01',               \n" +
                    "        'maxvalue': '1',              \n" +
                    "        'displayvalue': '0.01-1', \n" +
                    "        'code': '#A0D631'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '1',              \n" +
                    "        'maxvalue': '30',              \n" +
                    "        'displayvalue': '1 - 30',\n" +
                    "        'code': '#C7D631'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '30',              \n" +
                    "        'maxvalue': '50',              \n" +
                    "        'displayvalue': '30 - 50',\n" +
                    "        'code': '#FFC533'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '50',              \n" +
                    "        'maxvalue': '70',              \n" +
                    "        'displayvalue': '50 - 70',\n" +
                    "        'code': '#F2726F'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '70',              \n" +
                    "        'maxvalue': '90',              \n" +
                    "        'displayvalue': '70 - 90',\n" +
                    "        'code': '#BC95DF'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '90',              \n" +
                    "        'maxvalue': '100',              \n" +
                    "        'displayvalue': '90 - 100',\n" +
                    "        'code': '#C62828'                   \n" +
                    "      }  \n" +
                    "    ]                                     \n" +
                    "  },    ");
            jsonData3.append(data4.toString());
            // build  data object from label-value pair
            data3.append("'data':[");

            for(Map.Entry pair3:dataValuePair3.entrySet())
            {

                data3.append("{'id':'" + pair3.getKey() + "','value':'" + pair3.getValue() +"'},");

            }
            data3.replace(data3.length() - 1, data3.length(),"]");

            jsonData3.append(data3.toString());
            jsonData3.append("}");

            System.out.println(jsonData3.toString());
            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart3 = new FusionCharts(
                    "maps/yunnan",
                    "firstChart3",
                    "800",
                    "600",
                    "chart-container",
                    "json",
                    jsonData3.toString()
            );
        %>
        <%= firstChart3.render() %>



    </td>



        <td>
            <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
            <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
            <table><tr><td >

                <div id="chart-container2" >

                </div>
                <%
                    // store chart config name-config value pair
                    Map<String, String> chartConfig4 = new HashMap<String, String>();
                    chartConfig4.put("showborder", "0");
                    chartConfig4.put("theme", "fusion");
                    chartConfig4.put("caption", "全省客户分布");
                    chartConfig4.put("showlabels", "1");
               //    chartConfig4.put("includevalueinlabels", "1");
                    chartConfig4.put("numbersuffix", "");
                    chartConfig4.put("nullentitycolor", "#4FCAF6");
                    chartConfig4.put("legendcaption", "客户数");
                    chartConfig4.put("entitytooltext", "$lname {br} 客户数: $datavalue");



                    //store label-value pair
                    //LinkedHashMap 保证数据顺序
                    Map<String, Integer> dataValuePair4 = new LinkedHashMap<String, Integer>();


                    //遍历List
                    Object re4 = request.getAttribute("activearea");
                    List<RealTime> ol4= (List)re4;
                    for(int i=0;i<ol4.size();i++){
                        RealTime      ov4 = ol4.get(i);


                        if ("曲靖".equals(ov4.getChengshi()))        { dataValuePair4.put("2", ov4.getCustom_num());
                        } else if ("昆明".equals(ov4.getChengshi())) { dataValuePair4.put("1", ov4.getCustom_num());
                        } else if ("昭通".equals(ov4.getChengshi())) { dataValuePair4.put("5", ov4.getCustom_num());
                        } else if ("楚雄".equals(ov4.getChengshi())) { dataValuePair4.put("13", ov4.getCustom_num());
                        } else if ("临沧".equals(ov4.getChengshi())) { dataValuePair4.put("8", ov4.getCustom_num());
                        } else if ("版纳".equals(ov4.getChengshi())) { dataValuePair4.put("16", ov4.getCustom_num());
                        } else if ("普洱".equals(ov4.getChengshi())) { dataValuePair4.put("7", ov4.getCustom_num());
                        } else if ("保山".equals(ov4.getChengshi())) { dataValuePair4.put("4", ov4.getCustom_num());
                        } else if ("红河".equals(ov4.getChengshi())) { dataValuePair4.put("14", ov4.getCustom_num());
                        } else if ("玉溪".equals(ov4.getChengshi())) { dataValuePair4.put("3", ov4.getCustom_num());
                        } else if ("文山".equals(ov4.getChengshi())) { dataValuePair4.put("15", ov4.getCustom_num());
                        } else if ("大理".equals(ov4.getChengshi())) { dataValuePair4.put("12", ov4.getCustom_num());
                        } else if ("丽江".equals(ov4.getChengshi())) { dataValuePair4.put("6", ov4.getCustom_num());
                        } else if ("德宏".equals(ov4.getChengshi())) { dataValuePair4.put("9", ov4.getCustom_num());
                        } else if ("怒江".equals(ov4.getChengshi())) { dataValuePair4.put("10", ov4.getCustom_num());
                        } else if ("迪庆".equals(ov4.getChengshi())) { dataValuePair4.put("11", ov4.getCustom_num());
                        }



                    }
                    StringBuilder jsonData4 = new StringBuilder();
                    StringBuilder data5 = new StringBuilder();
                    StringBuilder data6 = new StringBuilder();

                    // json data to use as chart data source
                    jsonData4.append("{'chart':{");
                    for(Map.Entry conf4:chartConfig4.entrySet())
                    {
                        jsonData4.append("'" + conf4.getKey()+"':'"+conf4.getValue() + "',");
                    }

                    jsonData4.replace(jsonData4.length() - 1, jsonData4.length() ,"},");

                    data6.append("  'colorrange': {\n" +
                            "    'gradient': '0',\n" +
                            "    'color': [" +

                            "      {                                   \n" +
                            "        'minvalue': '0',               \n" +
                            "        'maxvalue': '0',              \n" +
                            "        'displayvalue': '0', \n" +
                            "        'code': '#4FCAF6'                   \n" +
                            "      },                                  \n" +
                            "      {                                   \n" +
                            "        'minvalue': '1',               \n" +
                            "        'maxvalue': '100',              \n" +
                            "        'displayvalue': '1-100', \n" +
                            "        'code': '#A0D631'                   \n" +
                            "      },                                  \n" +
                            "      {                                   \n" +
                            "        'minvalue': '100',              \n" +
                            "        'maxvalue': '300',              \n" +
                            "        'displayvalue': '100 - 300',\n" +
                            "        'code': '#C7D631'                   \n" +
                            "      },                                  \n" +
                            "      {                                   \n" +
                            "        'minvalue': '300',              \n" +
                            "        'maxvalue': '600',              \n" +
                            "        'displayvalue': '300 - 600',\n" +
                            "        'code': '#FFC533'                   \n" +
                            "      },                                  \n" +
                            "      {                                   \n" +
                            "        'minvalue': '600',              \n" +
                            "        'maxvalue': '1000',              \n" +
                            "        'displayvalue': '600 - 1000',\n" +
                            "        'code': '#F2726F'                   \n" +
                            "      } \n"+
                            "    ]                                     \n" +
                            "  },    ");
                    jsonData4.append(data6.toString());
                    // build  data object from label-value pair
                    data5.append("'data':[");

                    for(Map.Entry pair4:dataValuePair4.entrySet())
                    {

                        data5.append("{'id':'" + pair4.getKey() + "','value':'" + pair4.getValue() +"'},");

                    }
                    data5.replace(data5.length() - 1, data5.length(),"]");

                    jsonData4.append(data5.toString());
                    jsonData4.append("}");

                    System.out.println(jsonData4.toString());
                    // Create chart instance
                    // charttype, chartID, width, height,containerid, data format, data
                    FusionCharts firstChart4 = new FusionCharts(
                            "maps/yunnan",
                            "firstChart4",
                            "600",
                            "300",
                            "chart-container2",
                            "json",
                            jsonData4.toString()
                    );
                %>
                <%= firstChart4.render() %>



            </td>

            </tr>
                <tr><td>
                    <!--分割线**************************** -->


                    <!-- 柱图 -->

                    <div id="chart2"  ></div>
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
                                "600",
                                "300",
                                "chart2",
                                "json",
                                jsonData2.toString()
                        );
                    %>
                    <%= firstChart2.render() %>

                </td></tr></table>

        </td>
    </tr>



</table>





<table align="center">
    <tr>

        <td>
            <div id="chart5"  ></div>
            <%
                // store chart config name-config value pair
                Map<String, String> chartConfig = new HashMap<String, String>();
                chartConfig.put("caption", "登录情况");
                chartConfig.put("yaxisname", "登录数");
                chartConfig.put("subcaption", "近7天");
                chartConfig.put("showhovereffect", "1");
                chartConfig.put("numbersuffix", "");
                chartConfig.put("drawcrossline", "1");
                chartConfig.put("plottooltext", " $seriesName : <b>$dataValue</b>");
                chartConfig.put("theme", "fusion");



                //store label-value pair
                //LinkedHashMap 保证数据顺序
                Map<String, Integer[]> dataValuePair = new LinkedHashMap<String, Integer[]>();


                //遍历List
                Object re = request.getAttribute("activelogin");
                List<RealTime> ol= (List)re;
                for(int i=0;i<ol.size();i++){
                    RealTime      ov = ol.get(i);


                    Integer[] dt = {ov.getLogin_num(), ov.getLogin_pay_custom(),ov.getLogin_nopay_custom()};

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
                        "      'seriesname': '登录客户',\n" +
                        "      'data': [\n");




                for(Map.Entry pair7:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair7.getValue();
                    data7.append("{'value':'" +val[0] +"'},");

                }




                data7.replace(data7.length() - 1, data7.length(),"] },");




                data7.append(" {\n" +
                        "      'seriesname': '登录下单支付',\n" +
                        "      'data': [\n");

                for(Map.Entry pair7:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair7.getValue();
                    data7.append("{'value':'" +val[1] +"'},");

                }
                data7.replace(data7.length() - 1, data7.length(),"] },");


                data7.append(" {\n" +
                        "      'seriesname': '登录未下单支付',\n" +
                        "      'data': [\n");

                for(Map.Entry pair7:dataValuePair.entrySet())
                {
                    Integer[]  val=(Integer[])pair7.getValue();
                    data7.append("{'value':'" +val[2] +"'},");

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
                        "msline",
                        "first_chart5",
                        "1300",
                        "300",
                        "chart5",
                        "json",
                        jsonData5.toString()
                );
            %>
            <%= firstChart5.render() %>
        </td>
    </tr>








</table>







</body>
</html>
