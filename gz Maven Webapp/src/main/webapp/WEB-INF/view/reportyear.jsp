<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.oracat.model.RealTime" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oracat.util.FusionCharts" %>
<%@ page import="com.oracat.model.ReportYear" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>年表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="./css/css.css" type="text/css" rel="stylesheet" />

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
                    window.location = "${ctx }/reportyear";
                });

            }

        );

    </script>



    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.maps.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/maps/fusioncharts.yunnan.js"></script>

</head>
<body  >
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"></td>
        <td class="main_locbg font2">&nbsp;&nbsp;&nbsp;当前位置：报表 &gt; 年表</td>
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
                        <form name="reportyearform" method="post" id="form" action="reportyear.do">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">

                                        <a href="exportreportyear" id="export">导出excel</a>


                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
<tr  ><td  align="center">

    <div id="chart-container" >

    </div>
    <%
        // store chart config name-config value pair
        Map<String, String> chartConfig3 = new HashMap<String, String>();
        chartConfig3.put("theme", "fusion");
        chartConfig3.put("caption", "全省全年金额分布");
        chartConfig3.put("showlabels", "1");
        chartConfig3.put("numbersuffix", "(千元)");
        chartConfig3.put("nullentitycolor", "#4FCAF6");
        chartConfig3.put("legendcaption", "金额范围");
        chartConfig3.put("entitytooltext", "$lname {br} 金额: $datavalue");



        //store label-value pair
        //LinkedHashMap 保证数据顺序
        Map<String, Double> dataValuePair3 = new LinkedHashMap<String, Double>();


        //遍历List
        Object re3 = request.getAttribute("reportyear");
        List<ReportYear> ol3= (List)re3;
        for(int i=0;i<ol3.size();i++){
            ReportYear      ov3 = ol3.get(i);


            if ("曲靖".equals(ov3.getArea()))        { dataValuePair3.put("2", ov3.getYear());
            } else if ("昆明".equals(ov3.getArea())) { dataValuePair3.put("1", ov3.getYear());
            } else if ("昭通".equals(ov3.getArea())) { dataValuePair3.put("5", ov3.getYear());
            } else if ("楚雄".equals(ov3.getArea())) { dataValuePair3.put("13", ov3.getYear());
            } else if ("临沧".equals(ov3.getArea())) { dataValuePair3.put("8", ov3.getYear());
            } else if ("版纳".equals(ov3.getArea())) { dataValuePair3.put("16", ov3.getYear());
            } else if ("普洱".equals(ov3.getArea())) { dataValuePair3.put("7", ov3.getYear());
            } else if ("保山".equals(ov3.getArea())) { dataValuePair3.put("4", ov3.getYear());
            } else if ("红河".equals(ov3.getArea())) { dataValuePair3.put("14", ov3.getYear());
            } else if ("玉溪".equals(ov3.getArea())) { dataValuePair3.put("3", ov3.getYear());
            } else if ("文山".equals(ov3.getArea())) { dataValuePair3.put("15", ov3.getYear());
            } else if ("大理".equals(ov3.getArea())) { dataValuePair3.put("12", ov3.getYear());
            } else if ("丽江".equals(ov3.getArea())) { dataValuePair3.put("6", ov3.getYear());
            } else if ("德宏".equals(ov3.getArea())) { dataValuePair3.put("9", ov3.getYear());
            } else if ("怒江".equals(ov3.getArea())) { dataValuePair3.put("10", ov3.getYear());
            } else if ("迪庆".equals(ov3.getArea())) { dataValuePair3.put("11", ov3.getYear());
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
                "  {                                        \n" +
                "        'minvalue': '0',                \n" +
                "        'maxvalue': '700',               \n" +
                "        'displayvalue': '0 - 700',   \n" +
                "        'code': '#F0F1F9'                   \n" +
                "      },                                  \n" +
                "  {                                        \n" +
                "        'minvalue': '700',                \n" +
                "        'maxvalue': '10000',               \n" +
                "        'displayvalue': '700 - 10000',   \n" +
                "        'code': '#FFE0B2'                   \n" +
                "      },                                  \n" +
                "      {                                   \n" +
                "        'minvalue': '10000',               \n" +
                "        'maxvalue': '30000',               \n" +
                "        'displayvalue': '10000 - 30000',  \n" +
                "        'code': '#FB8C00'                   \n" +
                "      },                                  \n" +
                "      {                                   \n" +
                "        'minvalue': '30000',               \n" +
                "        'maxvalue': '100000',              \n" +
                "        'displayvalue': '30000 - 100000', \n" +
                "        'code': '#FD8963'                   \n" +
                "      },                                  \n" +
                "      {                                   \n" +
                "        'minvalue': '100000',              \n" +
                "        'maxvalue': '200000',              \n" +
                "        'displayvalue': '100000 - 200000',\n" +
                "        'code': '#EF5350'                   \n" +
                "      },                                  \n" +
                "      {                                   \n" +
                "        'minvalue': '200000',              \n" +
                "        'maxvalue': '1000000',              \n" +
                "        'displayvalue': '200000 - 1000000',\n" +
                "        'code': '#D60100'                   \n" +
                "      },                                  \n" +
                "      {                                   \n" +
                "        'minvalue': '1000000',              \n" +
                "        'maxvalue': '5000000',              \n" +
                "        'displayvalue': '> 1000000',        \n" +
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


</td></tr>
    <!-- 数据展示区 -->
    <tr valign="top">
        <td height="20">
            <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr class="main_trbg_tit" align="center">

                    <td>区域</td>
                    <td>201906</td>
                    <td>201907</td>
                    <td>201908</td>
                    <td>201909</td>
                    <td>201910</td>
                    <td>201911</td>
                    <td>201912</td>
                    <td>全年</td>
                    <td>毛利额</td>
                    <td>毛利率</td>


                </tr>
                <c:forEach items="${requestScope.reportyear}" var="reportyear" varStatus="stat">
                    <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">

                        <td>${reportyear.area              }</td>
                        <td>${reportyear.je201906              }</td>
                        <td>${reportyear.je201907              }</td>
                        <td>${reportyear.je201908            }</td>
                        <td>${reportyear.je201909                        }</td>
                        <td>${reportyear.je201910                                }</td>
                        <td>${reportyear.je201911                                }</td>
                        <td>${reportyear.je201912                                }</td>
                        <td>${reportyear.year                                }</td>
                        <td>${reportyear.ml                                }</td>
                        <td>${reportyear.mll                                }</td>




                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>



</table>
<div style="height:10px;"></div>
</body>
</html>