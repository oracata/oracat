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
            chartConfig3.put("caption", "全省实时订单分布");
            chartConfig3.put("showlabels", "1");
            chartConfig3.put("numbersuffix", "(元)");
            chartConfig3.put("nullentitycolor", "#4FCAF6");
            chartConfig3.put("legendcaption", "金额范围");
            chartConfig3.put("entitytooltext", "$lname {br} 金额: $datavalue");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Double> dataValuePair3 = new LinkedHashMap<String, Double>();


            //遍历List
            Object re3 = request.getAttribute("area");
            List<RealTime> ol3= (List)re3;
            for(int i=0;i<ol3.size();i++){
                RealTime      ov3 = ol3.get(i);


                if ("曲靖".equals(ov3.getChengshi()))        { dataValuePair3.put("2", ov3.getOrder_pay_price());
                } else if ("昆明".equals(ov3.getChengshi())) { dataValuePair3.put("1", ov3.getOrder_pay_price());
                } else if ("昭通".equals(ov3.getChengshi())) { dataValuePair3.put("5", ov3.getOrder_pay_price());
                } else if ("楚雄".equals(ov3.getChengshi())) { dataValuePair3.put("13", ov3.getOrder_pay_price());
                } else if ("临沧".equals(ov3.getChengshi())) { dataValuePair3.put("8", ov3.getOrder_pay_price());
                } else if ("版纳".equals(ov3.getChengshi())) { dataValuePair3.put("16", ov3.getOrder_pay_price());
                } else if ("普洱".equals(ov3.getChengshi())) { dataValuePair3.put("7", ov3.getOrder_pay_price());
                } else if ("保山".equals(ov3.getChengshi())) { dataValuePair3.put("4", ov3.getOrder_pay_price());
                } else if ("红河".equals(ov3.getChengshi())) { dataValuePair3.put("14", ov3.getOrder_pay_price());
                } else if ("玉溪".equals(ov3.getChengshi())) { dataValuePair3.put("3", ov3.getOrder_pay_price());
                } else if ("文山".equals(ov3.getChengshi())) { dataValuePair3.put("15", ov3.getOrder_pay_price());
                } else if ("大理".equals(ov3.getChengshi())) { dataValuePair3.put("12", ov3.getOrder_pay_price());
                } else if ("丽江".equals(ov3.getChengshi())) { dataValuePair3.put("6", ov3.getOrder_pay_price());
                } else if ("德宏".equals(ov3.getChengshi())) { dataValuePair3.put("9", ov3.getOrder_pay_price());
                } else if ("怒江".equals(ov3.getChengshi())) { dataValuePair3.put("10", ov3.getOrder_pay_price());
                } else if ("迪庆".equals(ov3.getChengshi())) { dataValuePair3.put("11", ov3.getOrder_pay_price());
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
                    "        'maxvalue': '750',              \n" +
                    "        'displayvalue': '0 - 750', \n" +
                    "        'code': '#4FCAF6'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '750',              \n" +
                    "        'maxvalue': '2000',              \n" +
                    "        'displayvalue': '750 - 2000',\n" +
                    "        'code': '#EF5350'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '2000',              \n" +
                    "        'maxvalue': '8000',              \n" +
                    "        'displayvalue': '2000 - 8000',\n" +
                    "        'code': '#D60100'                   \n" +
                    "      },                                  \n" +
                    "      {                                   \n" +
                    "        'minvalue': '8000',              \n" +
                    "        'maxvalue': '20000',              \n" +
                    "        'displayvalue': '> 8000',        \n" +
                    "        'code': '#C62828'                   \n" +
                    "      }                                   \n" +
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
            <table><tr><td>
            <div id="chart"  ></div>
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
                        "600",
                        "400",
                        "chart",
                        "json",
                        jsonData.toString()
                );
            %>
            <%= firstChart.render() %>
            </td></tr>
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
                        "400",
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
<table>
    <tr>

        <td>
            <script>

                <!-- 360浏览器需要 用极速模式才能显示 -->

           var str='{\n' +
               '                    chart: {\n' +
               '                        caption: "销售流程状态",\n' +
               '                        yaxismaxvalue: "1100",\n' +
               '                        yaxisminvalue: "0",\n' +
               '                        theme: "fusion",\n' +
               '                        valuefontsize: "12",\n' +
               '                        viewmode: "1",\n' +
               '                        valuefontcolor: "#FFFFFF",\n' +
               '                        plotfillhovercolor: "#1A237E",\n' +
               '                        divlinealpha: "0"\n' +
               '                    },\n' +
               '                    dataset: [\n' +
               '                        {\n' +
               '                            data: [\n' ;
                var data='';
                var width=5;
                var color='#5D62B5';
                var state_code=0;

<c:forEach items="${saleflow}" var="saleflow">
                state_code=${saleflow.state_code};
                if(state_code===1){
                    color='#F2726F';
                }else if(state_code===2) {
                    color='#29C3BE';
                }else if(state_code===3) {
                    color='#FFC533';
                }else if(state_code===4) {
                    color='#62B58F';
                }else if(state_code===5) {
                    color='#BC95DF';
                }else if(state_code===6) {
                    color='#F2726F';
                }else if(state_code===7) {
                    color='#FFC533';
                }else if(state_code===8) {
                    color='#C7D631';
                }else if(state_code===9) {
                    color='#FFC533';
                }else{
                    color='#C7D631';
                }
                data= data+'                                {\n' +
               '                                    id: "01",\n' +
               '                                    x: "'+width+'",\n' +
               '                                    y: "200",\n' +
               '                                    label: "${saleflow.state}(${saleflow.num})",\n' +
               '                                    shape: "rectangle",\n' +
               '                                    color: "'+color+'",\n' +
               '                                    width: "100",\n' +
               '                                    height: "60",\n' +
               '                                    hovercolor: "#1A237E"\n' +
               '                                },\n' ;
                var width=width+5;
                </c:forEach>
                data=data.substr(0, data.length - 1);
                str=str+data;
                str=str+ '                            ]\n' +
               '                        }\n' +
               '                    ],\n' +
               '                    connectors: [\n' +
               '                        {\n' +
               '                            connector: [\n' ;
               var connector='';
               var last='0';

                var now='0';

<c:forEach items="${saleflow}" var="saleflow"   varStatus="stat" >

                now=${saleflow.state_code};
                var index=${stat.index};


              if(Number(index)!=0){

                connector=connector+'{\n' +
               '                                    from: "'+last+'",\n' +
               '                                    to: "'+now+'",\n' +
               '                                    strength: "2",\n' +
               '                                    arrowatstart: "0",\n' +
               '                                    arrowatend: "1",\n' +
               '                                    alpha: "50"\n' +
               '                                },\n' ;
              }
                last=now;
                </c:forEach>

                connector=connector.substr(0, connector.length - 1);
                str=str+connector;
                str=str+'                            ]\n' +
               '                        }\n' +
               '                    ]\n' +
               '                }' ;




              //  eval解析复杂json字符串 多层字符串普通的JSON.parse是解析不了的,不能一步到位解析到底的。
                const dataSource = eval("("+ str +")");
                const dataSource1 ={
                    chart: {
                        caption: "销售流程状态",
                            yaxismaxvalue: "1100",
                            yaxisminvalue: "0",
                            theme: "fusion",
                            valuefontsize: "12",
                            viewmode: "1",
                            valuefontcolor: "#FFFFFF",
                            plotfillhovercolor: "#1A237E",
                            divlinealpha: "0"
                    },
                    dataset: [
                        {
                            data: [
                                {
                                    id: "01",
                                    x: "5",
                                    y: "200",
                                    label: "未分配批号(14)",
                                    shape: "rectangle",
                                    color: "#F2726F",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                },
                                {
                                    id: "01",
                                    x: "10",
                                    y: "200",
                                    label: "单据等待传送至WMS(3)",
                                    shape: "rectangle",
                                    color: "#62B58F",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                },
                                {
                                    id: "01",
                                    x: "15",
                                    y: "200",
                                    label: "单据已传送WMS,等待波次(1)",
                                    shape: "rectangle",
                                    color: "#BC95DF",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                },
                                {
                                    id: "01",
                                    x: "20",
                                    y: "200",
                                    label: "正在拣货中(3)",
                                    shape: "rectangle",
                                    color: "#F2726F",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                },
                                {
                                    id: "01",
                                    x: "25",
                                    y: "200",
                                    label: "准备出库(30)",
                                    shape: "rectangle",
                                    color: "#FFC533",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                },
                                {
                                    id: "01",
                                    x: "30",
                                    y: "200",
                                    label: "已出库打单(478256)",
                                    shape: "rectangle",
                                    color: "#C7D631",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                },
                                {
                                    id: "01",
                                    x: "35",
                                    y: "200",
                                    label: "整单差异(21)",
                                    shape: "rectangle",
                                    color: "#FFC533",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                }
                                ]
                        }
                    ],
                        connectors: [
                           {
                        connector: [
                            {
                                from: "1",
                                to: "4",
                                strength: "2",
                                arrowatstart: "0",
                                arrowatend: "1",
                                alpha: "50"
                            },
                            {
                                from: "4",
                                to: "5",
                                strength: "2",
                                arrowatstart: "0",
                                arrowatend: "1",
                                alpha: "50"
                            },
                            {
                                from: "5",
                                to: "6",
                                strength: "2",
                                arrowatstart: "0",
                                arrowatend: "1",
                                alpha: "50"
                            },
                            {
                                from: "6",
                                to: "7",
                                strength: "2",
                                arrowatstart: "0",
                                arrowatend: "1",
                                alpha: "50"
                            },
                            {
                                from: "7",
                                to: "8",
                                strength: "2",
                                arrowatstart: "0",
                                arrowatend: "1",
                                alpha: "50"
                            },
                            {
                                from: "8",
                                to: "9",
                                strength: "2",
                                arrowatstart: "0",
                                arrowatend: "1",
                                alpha: "50"
                            }
                            ]
                    }
                ]
                };


                FusionCharts.ready(function() {

                    var myChart = new FusionCharts({
                        type: "dragnode",
                        renderAt: "chart-container2",
                        width: "100%",
                        height: "200",
                        dataFormat: "json",
                        dataSource
                    }).render();
                });


            </script>

            <div id="chart-container2" ></div>
        </td>
    </tr>

</table>



</body>
</html>
