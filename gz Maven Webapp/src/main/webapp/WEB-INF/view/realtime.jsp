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

<!--
    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>


    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
-->


    <!-- FusionCharts Library -->
    <script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/3.15.0-sr.1/fusioncharts.js"></script>
    <script type="text/javascript"
            src="https://cdn.fusioncharts.com/fusioncharts/3.15.0-sr.1/themes/fusioncharts.theme.fusion.js"></script>
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



<!-- 柱图 -->
<table>
    <tr>
<td>
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
            "700",
            "400",
            "chart",
            "json",
            jsonData.toString()
    );
%>
<%= firstChart.render() %>
    </td>
<td>
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
            "700",
            "400",
            "chart2",
            "json",
            jsonData2.toString()
    );
%>
<%= firstChart2.render() %>

    </td>
    </tr>

    <tr>
        <td>

            <script type="javascript">
                const dataSource = {
                    chart: {
                        caption: "Process Involved During Course Design",
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
                                    x: "15",
                                    y: "1000",
                                    label: "Kick Off{br}meeting",
                                    shape: "rectangle",
                                    color: "#5D62B5",
                                    width: "100",
                                    height: "60",
                                    hovercolor: "#1A237E"
                                },
                                {
                                    id: "02",
                                    x: "15",
                                    y: "800",
                                    label: "Review existing{br}course materials",
                                    color: "#29C3BE",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "03",
                                    x: "15",
                                    y: "600",
                                    label:
                                        "Collaborate on{br}course topics,{br}outcomes,{br}objectives,etc.",
                                    color: "#F2726F",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "04",
                                    x: "15",
                                    y: "350",
                                    label: "Finalize outcomes{br}& objectives.",
                                    color: "#FFC533",
                                    shape: "polygon",
                                    radius: "60"
                                },
                                {
                                    id: "05",
                                    x: "15",
                                    y: "100",
                                    label: "Complete Course{br}blueprint.",
                                    color: "#62B58F",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "06",
                                    x: "45",
                                    y: "100",
                                    label: "Discuss{br}assessments of{br} course outcomes.",
                                    color: "#BC95DF",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "07",
                                    x: "45",
                                    y: "350",
                                    label: "Align assessments{br} to outcomes.",
                                    color: "#F2726F",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "08",
                                    x: "45",
                                    y: "600",
                                    label: "Develop{br}assessments for{br}online delivery.",
                                    color: "#FFC533",
                                    shape: "polygon",
                                    radius: "60"
                                },
                                {
                                    id: "09",
                                    x: "45",
                                    y: "800",
                                    label: "Update Course{br} blueprint with{br} assessment info.",
                                    color: "#C7D631",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "10",
                                    x: "45",
                                    y: "1000",
                                    label: "Determine weekly{br} activities and{br}materials",
                                    color: "#FFC533",
                                    shape: "polygon",
                                    radius: "60"
                                },
                                {
                                    id: "11",
                                    x: "75",
                                    y: "1000",
                                    label: "Update Course{br}blueprint with{br}weekly activities",
                                    color: "#C7D631",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "12",
                                    x: "75",
                                    y: "800",
                                    label: "Build course{br}carmen",
                                    color: "#BC95DF",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "13",
                                    x: "75",
                                    y: "600",
                                    label: "Complete syllabus{br}templete",
                                    color: "#C7D631",
                                    shape: "rectangle",
                                    width: "100",
                                    height: "60"
                                },
                                {
                                    id: "14",
                                    x: "75",
                                    y: "350",
                                    label: "Review course{br}(Faculty)",
                                    color: "#FFC533",
                                    shape: "polygon",
                                    radius: "60"
                                },
                                {
                                    id: "15",
                                    x: "75",
                                    label: "Course{br}complete",
                                    y: "100",
                                    shape: "rectangle",
                                    color: "#5D62B5",
                                    width: "100",
                                    height: "60"
                                }
                            ]
                        }
                    ],
                    connectors: [
                        {
                            connector: [
                                {
                                    from: "01",
                                    to: "02",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "02",
                                    to: "03",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "03",
                                    to: "04",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "04",
                                    to: "05",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "05",
                                    to: "06",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "06",
                                    to: "07",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "07",
                                    to: "08",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "08",
                                    to: "09",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "09",
                                    to: "10",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "10",
                                    to: "11",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "11",
                                    to: "12",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "12",
                                    to: "13",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "13",
                                    to: "14",
                                    strength: "2",
                                    arrowatstart: "0",
                                    arrowatend: "1",
                                    alpha: "50"
                                },
                                {
                                    from: "14",
                                    to: "15",
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
                        renderAt: "chart-container",
                        width: "100%",
                        height: "100%",
                        dataFormat: "json",
                        dataSource
                    }).render();
                });


            </script>
            <div id="chart-container"></div>
        </td>
    </tr>

</table>




</body>
</html>
